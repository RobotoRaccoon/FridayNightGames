package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.mCommands;
import org.mcau.robotoraccoon.fridaynightgames.mMain;
import org.mcau.robotoraccoon.fridaynightgames.utility.uGameList;
import org.mcau.robotoraccoon.fridaynightgames.utility.uTypeList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class cList {

    public static void list( CommandSender sender, String[] args ) {

        if( !sender.hasPermission( getPermission() ) ) {
            sender.sendMessage(mCommands.getDenied());
            return;
        }

        if( args.length < 2) {

            // Get games into an associative array.
            HashMap<String, List<String>> sortedGames = new HashMap<>();
            for (String game : uGameList.getKeys()) {
                String type = uGameList.getGameType(game);

                List<String> nameList = new ArrayList<>();
                if (sortedGames.containsKey(type)) {
                    nameList = sortedGames.get(type);
                }

                nameList.add(uGameList.getGameName(game));
                Collections.sort(nameList);
                sortedGames.put(type, nameList);
            }

            // Print the games under their respective heading
            for (String type : sortedGames.keySet()) {
                sender.sendMessage(ChatColor.DARK_PURPLE + type + ": " + ChatColor.YELLOW + sortedGames.get(type).toString());
            }
        }
        else {
            sender.sendMessage( ChatColor.DARK_PURPLE + "Name: " + ChatColor.YELLOW + uGameList.getGameName(args[1]));
            sender.sendMessage( ChatColor.DARK_PURPLE + "Type: " + ChatColor.YELLOW + uGameList.getGameType(args[1]));
            sender.sendMessage( ChatColor.DARK_PURPLE + "Join Command: " + ChatColor.YELLOW + uTypeList.getJoinCommand(uGameList.getGameType(args[1])));
            sender.sendMessage( ChatColor.DARK_PURPLE + "Quit Command: " + ChatColor.YELLOW + uTypeList.getQuitCommand(uGameList.getGameType(args[1])));
        }

    }

    public static String getPermission() { return "fng.player"; }

}
