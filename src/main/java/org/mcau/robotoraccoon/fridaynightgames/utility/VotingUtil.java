package org.mcau.robotoraccoon.fridaynightgames.utility;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mcau.robotoraccoon.fridaynightgames.Config;
import org.mcau.robotoraccoon.fridaynightgames.Main;
import org.mcau.robotoraccoon.fridaynightgames.games.MinigameMap;

import java.util.*;

public class VotingUtil {

    public static final HashMap<UUID, MinigameMap> voteList = new HashMap<>();
    public static final List<MinigameMap> mapList = new ArrayList<>();

    // Picks an amount of random maps to let people vote for.
    public static void generateList() {
        generateList(5);
    }

    public static void generateList(Integer mapCount) {

        voteList.clear();
        mapList.clear();

        // Add all the enabled maps into the pool.
        List<MinigameMap> maps = new ArrayList<>();
        for (MinigameMap map : Main.getMinigames().values())
            if (map.getEnabled())
                maps.add(map);

        // Amount of maps to remove from the list that have already been played this session
        int removeCount = Config.getConfig().getInt("removePlayedGames");
        if (maps.size() > mapCount) {
            // Get lowest value
            int maxLoops = maps.size() - mapCount;
            if (maxLoops > removeCount) maxLoops = removeCount;
            if (maxLoops > Main.getPlayedGames().size()) maxLoops = Main.getPlayedGames().size();

            for (int i = 0; i < maxLoops; i++) {
                maps.remove(Main.getPlayedGames().get(i));
            }
        }


        // Generate list.
        for (short i = 0; i < mapCount; i++) {
            if (maps.size() != 0) {
                Random random = new Random();
                MinigameMap randomMap = maps.get(random.nextInt(maps.size()));

                mapList.add(randomMap);
                maps.remove(randomMap);
            }
        }

    }

    public static void printList(CommandSender sender) {

        MessageUtil.colour(sender, " &7======= &5Available Maps &7======= ");

        for (short i = 0; i < mapList.size(); i++) {
            String pos = String.valueOf(i + 1);
            MessageUtil.colour(sender, "&5" + pos + ": &d" + mapList.get(i).getName()
                    + " &7&o(" + mapList.get(i).getType().getName() + ")");
        }

        MessageUtil.colour(sender, MessageUtil.getPrefix() + "To vote for a map, do: &5/FNG Vote <Number>");

    }

    // Handles the player voting.
    public static void vote(CommandSender sender, Integer index) {

        if (index >= mapList.size() || index < 0) {
            MessageUtil.colour(sender, MessageUtil.getError() + "This number does not exist!");
            printList(sender);
            return;
        }

        MinigameMap map = mapList.get(index);
        Player player = (Player) sender;

        if (voteList.containsKey(player.getUniqueId())) {
            MessageUtil.colour(sender, MessageUtil.getPrefix() + "You have changed your vote to &5" + map.getName());
        } else {
            MessageUtil.colour(sender, MessageUtil.getPrefix() + "You have successfully voted for &5" + map.getName());
        }

        voteList.put(player.getUniqueId(), map);

    }

    // Get the votes from <pUUID,gameKey> into <gameKey,Score>
    public static HashMap<MinigameMap, Integer> getVoteTally() {

        HashMap<MinigameMap, Integer> voteTally = new HashMap<>();

        for (UUID pUUID : voteList.keySet()) {

            MinigameMap map = voteList.get(pUUID);
            Integer gameScore;

            if (voteTally.containsKey(map)) {
                gameScore = voteTally.get(map) + 1;
            } else {
                gameScore = 1;
            }

            voteTally.put(map, gameScore);

        }

        return voteTally;
    }

    public static List<MinigameMap> getMostVoted() {

        List<MinigameMap> mostVoted = new ArrayList<>();
        HashMap<MinigameMap, Integer> voteTally = getVoteTally();
        Integer highestVote = 0;

        for (MinigameMap map : voteTally.keySet()) {

            if (voteTally.get(map) > highestVote) {
                highestVote = voteTally.get(map);
                mostVoted.clear();
                mostVoted.add(map);
            } else if (voteTally.get(map).equals(highestVote)) {
                mostVoted.add(map);
            }

        }

        return mostVoted;

    }

}
