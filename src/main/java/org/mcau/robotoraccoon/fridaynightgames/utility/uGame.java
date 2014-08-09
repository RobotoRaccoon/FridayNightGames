package org.mcau.robotoraccoon.fridaynightgames.utility;

import org.bukkit.ChatColor;
import org.mcau.robotoraccoon.fridaynightgames.mCommands;
import org.mcau.robotoraccoon.fridaynightgames.mConfig;
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
        uVoting.generateList();
        addPlayCount(gameName);

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

    //Add plays to the games total play count for every FNG
    public static void addPlayCount(String gameKey) {
        addPlayCount(gameKey, 1);
    }

    public static void addPlayCount(String gameKey, Integer amount) {
        gameKey = gameKey.toLowerCase();
        Integer gamePlays = 0;

        if( mConfig.getGamesConfig().contains("games." + gameKey + ".plays") ) {
            gamePlays = mConfig.getGamesConfig().getInt("games." + gameKey + ".plays");
        }

        mConfig.getGamesConfig().set("games." + gameKey + ".plays", gamePlays + amount);
        mConfig.saveConfigs();
    }
}
