package org.mcau.robotoraccoon.fridaynightgames.utility;

import org.mcau.robotoraccoon.fridaynightgames.Config;

import java.util.HashSet;
import java.util.Set;

public class uGameList {

    // All game names in lowercase
    public static Set<String> getKeys() {

        Set<String> keys = new HashSet<>();
        try {
            keys = Config.getGamesConfig().getConfigurationSection("games").getKeys(false);
        } catch (Exception e) {
        }

        return keys;
    }

    // If the game exists in the config
    public static Boolean gameExists(String key) {
        return (Config.getGamesConfig().contains("games." + key));
    }

    // Correct capitalisation of the name
    public static String getGameName(String key) {

        key = key.toLowerCase();

        if (Config.getGamesConfig().contains("games." + key + ".name")) {
            return Config.getGamesConfig().getString("games." + key + ".name");
        }

        return key; //Unknown
    }

    // Get the game type
    public static String getGameType(String key) {

        key = key.toLowerCase();

        if (Config.getGamesConfig().contains("games." + key + ".type")) {
            return Config.getGamesConfig().getString("games." + key + ".type");
        }

        return "unknown"; //Unknown
    }

}
