package org.mcau.robotoraccoon.fridaynightgames.utility;

import org.mcau.robotoraccoon.fridaynightgames.mConfig;

import java.util.HashSet;
import java.util.Set;

public class uGameList {

    // All game names in lowercase
    public static Set<String> getKeys() {

        Set<String> keys = new HashSet<>();
        try {
            keys = mConfig.getGamesConfig().getConfigurationSection("games").getKeys(false);
        } catch (Exception e) {}

        return keys;
    }

    // Correct capitalisation of the name
    public static String getGameName(String key) {

        key = key.toLowerCase();

        if( mConfig.getGamesConfig().contains("games." + key + ".name") ) {
            return mConfig.getGamesConfig().getString("games." + key + ".name");
        }

        return key; //Unknown
    }

    // Get the game type
    public static String getGameType(String key) {

        key = key.toLowerCase();

        if( mConfig.getGamesConfig().contains("games." + key + ".type") ) {
            return mConfig.getGamesConfig().getString("games." + key + ".type");
        }

        return "unknown"; //Unknown
    }

}
