package org.mcau.robotoraccoon.fridaynightgames.utility;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mcau.robotoraccoon.fridaynightgames.mCommands;
import org.mcau.robotoraccoon.fridaynightgames.mMain;

import java.util.*;

public class uVoting {

    public static final HashMap<UUID, String> voteList = new HashMap<>();
    public static final List<String> mapList = new ArrayList<>();

    // Picks an amount of random maps to let people vote for.
    public static void generateList() {
        generateList(5);
    }

    public static void generateList( Integer mapCount ) {

        voteList.clear();
        mapList.clear();

        List<String> keys = new ArrayList<>();
        keys.addAll(uGameList.getKeys());

        // Remove last played maps whilst not shortening the list to less than 5.
        //if( keys.size() > 5 ) {
        //    int maxLoops = Math.min(10, Math.min(keys.size() - 5, mMain.fngPlayedGames.size()));
        //    for( Integer i = maxLoops - 1; i >= 0; i-- ) {
        //        keys.remove( keys.indexOf(mMain.fngPlayedGames.get(i)) );
        //    }
        //}

        // Generate list.
        for( Integer i = 0; i < mapCount; i++ ) {
            if( keys.size() != 0 ) {
                Random random = new Random();
                String randomKey = keys.get(random.nextInt(keys.size()));

                mapList.add(randomKey);
                keys.remove(randomKey);
            }
        }

    }

    public static void printList( CommandSender sender ) {

        uBroadcast.colour(sender, " &7======= &5Available Maps &7======= ");

        for( Integer i = 0; i < mapList.size(); i++ ) {
            String pos = String.valueOf(i + 1);
            uBroadcast.colour(sender, "&5" + pos + ": &d" + uGameList.getGameName(mapList.get(i))
                    + " &7&o(" + uGameList.getGameType(mapList.get(i)) + ")");
        }

        uBroadcast.colour(sender, mCommands.getPrefix() + "To vote for a map, do: &5/FNG Vote <Number>");

    }

    // Handles the player voting.
    public static void vote( CommandSender sender, Integer index ) {

        if( index >= mapList.size() || index < 0 ) {
            uBroadcast.colour(sender, mCommands.getError() + "This number does not exist!");
            printList(sender);
            return;
        }

        String gameKey = mapList.get(index);
        Player player = (Player) sender;

        if( voteList.containsKey(player.getUniqueId()) ) {
            uBroadcast.colour(sender, mCommands.getPrefix() + "You have changed your vote to &5" + uGameList.getGameName(gameKey));
        }
        else {
            uBroadcast.colour(sender, mCommands.getPrefix() + "You have successfully voted for &5" + uGameList.getGameName(gameKey));
        }

        voteList.put(player.getUniqueId(), gameKey);

    }

    // Get the votes from <pUUID,gameKey> into <gameKey,Score>
    public static HashMap<String, Integer> getVoteTally() {

        HashMap<String, Integer> voteTally = new HashMap<>();

        for( UUID pUUID : voteList.keySet() ) {

            String gameKey = voteList.get(pUUID);
            Integer gameScore;

            if( voteTally.containsKey(gameKey) ) {
                gameScore = voteTally.get(gameKey) + 1;
            }
            else {
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

        for( String gameKey : voteTally.keySet() ) {

            if( voteTally.get(gameKey) > highestVote ) {
                highestVote = voteTally.get(gameKey);
                mostVoted.clear();
                mostVoted.add(gameKey);
            }
            else if( voteTally.get(gameKey).equals(highestVote) ) {
                mostVoted.add(gameKey);
            }

        }

        return mostVoted;

    }

}
