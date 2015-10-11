package org.mcau.robotoraccoon.fridaynightgames.utility;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mcau.robotoraccoon.fridaynightgames.Config;
import org.mcau.robotoraccoon.fridaynightgames.Main;

import java.util.*;

public class VotingUtil {

    public static final HashMap<UUID, String> voteList = new HashMap<>();
    public static final List<String> mapList = new ArrayList<>();

    // Picks an amount of random maps to let people vote for.
    public static void generateList() {
        generateList(5);
    }

    public static void generateList(Integer mapCount) {

        voteList.clear();
        mapList.clear();

        List<String> keys = new ArrayList<>();
        keys.addAll(GameListUtil.getKeys());

        // Amount of maps to remove from the list that have already been played this session
        int removeCount = Config.getConfig().getInt("removePlayedGames");
        if (keys.size() > mapCount) {
            // Get lowest value
            int maxLoops = keys.size() - mapCount;
            if (maxLoops > removeCount) maxLoops = removeCount;
            if (maxLoops > Main.getPlayedGames().size()) maxLoops = Main.getPlayedGames().size();

            for (int i = 0; i < maxLoops; i++) {
                keys.remove(Main.getPlayedGames().get(i));
            }
        }


        // Generate list.
        for (short i = 0; i < mapCount; i++) {
            if (keys.size() != 0) {
                Random random = new Random();
                String randomKey = keys.get(random.nextInt(keys.size()));

                mapList.add(randomKey);
                keys.remove(randomKey);
            }
        }

    }

    public static void printList(CommandSender sender) {

        MessageUtil.colour(sender, " &7======= &5Available Maps &7======= ");

        for (short i = 0; i < mapList.size(); i++) {
            String pos = String.valueOf(i + 1);
            MessageUtil.colour(sender, "&5" + pos + ": &d" + GameListUtil.getGameName(mapList.get(i))
                    + " &7&o(" + GameListUtil.getGameType(mapList.get(i)) + ")");
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

        String gameKey = mapList.get(index);
        Player player = (Player) sender;

        if (voteList.containsKey(player.getUniqueId())) {
            MessageUtil.colour(sender, MessageUtil.getPrefix() + "You have changed your vote to &5" + GameListUtil.getGameName(gameKey));
        } else {
            MessageUtil.colour(sender, MessageUtil.getPrefix() + "You have successfully voted for &5" + GameListUtil.getGameName(gameKey));
        }

        voteList.put(player.getUniqueId(), gameKey);

    }

    // Get the votes from <pUUID,gameKey> into <gameKey,Score>
    public static HashMap<String, Integer> getVoteTally() {

        HashMap<String, Integer> voteTally = new HashMap<>();

        for (UUID pUUID : voteList.keySet()) {

            String gameKey = voteList.get(pUUID);
            Integer gameScore;

            if (voteTally.containsKey(gameKey)) {
                gameScore = voteTally.get(gameKey) + 1;
            } else {
                gameScore = 1;
            }

            voteTally.put(gameKey, gameScore);

        }

        return voteTally;
    }

    public static List<String> getMostVoted() {

        List<String> mostVoted = new ArrayList<>();
        HashMap<String, Integer> voteTally = getVoteTally();
        Integer highestVote = 0;

        for (String gameKey : voteTally.keySet()) {

            if (voteTally.get(gameKey) > highestVote) {
                highestVote = voteTally.get(gameKey);
                mostVoted.clear();
                mostVoted.add(gameKey);
            } else if (voteTally.get(gameKey).equals(highestVote)) {
                mostVoted.add(gameKey);
            }

        }

        return mostVoted;

    }

}
