package org.mcau.robotoraccoon.fridaynightgames.utility;

import org.bukkit.ChatColor;
import org.mcau.robotoraccoon.fridaynightgames.mCommands;
import org.mcau.robotoraccoon.fridaynightgames.mConfig;
import org.mcau.robotoraccoon.fridaynightgames.mMain;

import java.util.UUID;

public class uGame {

    //Start a specified game
    public static void start(String gameKey) {

        gameKey = gameKey.toLowerCase();
        String command = uTypeList.getJoinCommand(uGameList.getGameType(gameKey)) + " " + gameKey;

        for( final UUID pUUID : uPlayerList.getKeys() ) {
            uRunAs.asPlayer(command, pUUID);
        }

        mMain.autoStartEnabled = false;
        mMain.fngPlayedGames.add(0, gameKey);
        uVoting.generateList();
        addPlayCount(gameKey);

        uBroadcast.global(mCommands.getPrefix() + "Starting Game: " + ChatColor.RED + gameKey.toUpperCase());
    }

    //Forces all users to quit their game
    public static void end(String gameKey) {

        gameKey = gameKey.toLowerCase();
        String command = uTypeList.getQuitCommand(uGameList.getGameType(gameKey));

        for( final UUID pUUID : uPlayerList.getKeys() ) {
            uRunAs.asPlayer(command, pUUID);
        }

        uBroadcast.global(mCommands.getPrefix() + "Ending Game: " + ChatColor.RED + gameKey.toUpperCase());
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
