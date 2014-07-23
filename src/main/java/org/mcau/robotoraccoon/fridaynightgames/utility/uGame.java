package org.mcau.robotoraccoon.fridaynightgames.utility;

import org.bukkit.ChatColor;
import org.mcau.robotoraccoon.fridaynightgames.mCommands;
import org.mcau.robotoraccoon.fridaynightgames.mMain;

import java.util.UUID;

public class uGame {

    //Start a specified game
    public static void start(String gameName) {

        gameName = gameName.toLowerCase();
        String command = uTypeList.getJoinCommand(uGameList.getGameType(gameName)) + " " + gameName;

        for( UUID pUUID : uPlayerList.getKeys() ) {
            uRunAs.asPlayer(command, pUUID);
        }

        mMain.autoStartEnabled = false;
        mMain.fngPlayedGames.add(0, gameName);
        uVoting.generateList(5);
        uBroadcast.global(mCommands.getPrefix() + "Starting Game: " + ChatColor.RED + gameName.toUpperCase());
    }

    //Forces all users to quit their game
    public static void end(String gameName) {

        gameName = gameName.toLowerCase();
        String command = uTypeList.getQuitCommand(uGameList.getGameType(gameName.toLowerCase()));

        for( UUID pUUID : uPlayerList.getKeys() ) {
            uRunAs.asPlayer(command, pUUID);
        }

        uBroadcast.global(mCommands.getPrefix() + "Ending Game: " + ChatColor.RED + gameName.toUpperCase());
    }
}
