package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.mCommands;
import org.mcau.robotoraccoon.fridaynightgames.utility.uBroadcast;
import org.mcau.robotoraccoon.fridaynightgames.utility.uGameList;
import org.mcau.robotoraccoon.fridaynightgames.utility.uTypeList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class cList {

    public static void list( CommandSender sender, String[] args ) {

        if( !sender.hasPermission( getPermission() ) ) {
            uBroadcast.colour(sender, mCommands.getDenied());
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
                uBroadcast.colour(sender, "&5" + type + ": &e" + sortedGames.get(type).toString());
            }
        }
        else {
            uBroadcast.colour(sender, "&5Name: &e" + uGameList.getGameName(args[1]));
            uBroadcast.colour(sender, "&5Type: &e" + uGameList.getGameType(args[1]));
            uBroadcast.colour(sender, "&5Join Command: &e" + uTypeList.getJoinCommand(uGameList.getGameType(args[1])));
            uBroadcast.colour(sender, "&5Quit Command: &e" + uTypeList.getQuitCommand(uGameList.getGameType(args[1])));
        }

    }

    public static String getPermission() { return "fng.player"; }

}
