package org.mcau.robotoraccoon.fridaynightgames.utility;

import com.pauldavdesign.mineauz.minigames.minigame.Minigame;
import org.bukkit.Bukkit;
import org.mcau.robotoraccoon.fridaynightgames.mConfig;

import java.util.HashSet;
import java.util.List;
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

        if( getKeys().contains( key )) {
            return mConfig.getGamesConfig().getString("games." + key + ".name");
        }
        else {
            return key; //Unknown
        }
    }

    // Get the game type
    public static String getGameType(String key) {

        key = key.toLowerCase();

        if( getKeys().contains( key )) {
            return mConfig.getGamesConfig().getString("games." + key + ".type");
        }
        else {
            return "unknown"; //Unknown
        }
    }

}
