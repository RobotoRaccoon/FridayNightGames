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
    public static void generateList( Integer mapCount ) {

        voteList.clear();
        mapList.clear();

        List<String> keys = new ArrayList<>();
        keys.addAll(uGameList.getKeys());

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

        sender.sendMessage( ChatColor.GRAY + " ===== " + ChatColor.DARK_PURPLE + "Available Maps" + ChatColor.GRAY + " ===== ");

        for( Integer i = 0; i < mapList.size(); i++ ) {
            String pos = String.valueOf(i + 1);
            sender.sendMessage( ChatColor.DARK_PURPLE + pos + ": " + ChatColor.LIGHT_PURPLE + uGameList.getGameName(mapList.get(i))
                    + ChatColor.GRAY + ChatColor.ITALIC + " (" + uGameList.getGameType(mapList.get(i)) + ")");
        }

        sender.sendMessage( mCommands.getPrefix() + "To vote for a map, do: " + ChatColor.DARK_PURPLE + "/FNG Vote <Number>" );

    }

    // Handles the player voting.
    public static void vote( CommandSender sender, Integer index ) {

        if( index >= mapList.size() || index < 0 ) {
            sender.sendMessage( mCommands.getError() + "This number does not exist!" );
            printList(sender);
            return;
        }

        String gameKey = mapList.get(index);
        Player player = (Player) sender;
        voteList.put(player.getUniqueId(), gameKey);
        player.sendMessage( mCommands.getPrefix() + "You have successfully voted for " + ChatColor.DARK_PURPLE + uGameList.getGameName(gameKey) );

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
