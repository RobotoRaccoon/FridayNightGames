package org.mcau.robotoraccoon.fridaynightgames.utility;

import org.mcau.robotoraccoon.fridaynightgames.Config;
import org.mcau.robotoraccoon.fridaynightgames.Main;
import org.mcau.robotoraccoon.fridaynightgames.games.MinigameType;

import java.util.Set;

public class TypeUtil {

    public static Set<String> getPluginKeys() {
        return Config.getConfig().getConfigurationSection("commands").getKeys(false);
    }

    // Get the join command from a type key
    public static String getJoinCommand(MinigameType type) {

        String plugin = type.getPlugin();

        if (getPluginKeys().contains(plugin)) {
            try {
                return Config.getConfig().getString("commands." + plugin + ".join");
            } catch (Exception e) {
            }
        }

        return Config.getConfig().getString("commands.unknown.join");
    }

    // Get the quit command from a type
    public static String getQuitCommand(MinigameType type) {

        String plugin = type.getPlugin();

        if (getPluginKeys().contains(plugin)) {
            try {
                return Config.getConfig().getString("commands." + plugin + ".quit");
            } catch (Exception e) {
            }
        }

        return Config.getConfig().getString("commands.unknown.quit");
    }

    public static void loadTypesFromConfig() {
        Main.getMinigames().clear();
        Set<String> keys = Config.getConfig().getConfigurationSection("types").getKeys(false);
        for (String key : keys) {
            MinigameType type = new MinigameType(key);
            Main.getGameTypes().put(key, type);
        }
    }

    public static void saveTypesToConfig() {
        for (MinigameType type : Main.getGameTypes().values())
            type.saveToConfig();
    }

}
