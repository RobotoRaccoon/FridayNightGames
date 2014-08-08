package org.mcau.robotoraccoon.fridaynightgames.utility;

import org.mcau.robotoraccoon.fridaynightgames.mConfig;

import java.util.Set;

public class uTypeList {

    public static Set<String> getTypes() { return mConfig.getConfig().getConfigurationSection("types").getKeys(false); }

    public static String getPlugin(String type) {

        type = type.toLowerCase();

        if( getTypes().contains( type ) ) {
            return mConfig.getConfig().getString("types." + type);
        }
        else {
            return mConfig.getConfig().getString("types.unknown");
        }
    }

    public static Set<String> getPluginKeys() { return mConfig.getConfig().getConfigurationSection("commands").getKeys(false); }

    // Get the join command from a type key
    public static String getJoinCommand(String type) {

        type = type.toLowerCase();
        String plugin = getPlugin(type);

        if( getPluginKeys().contains(plugin) ) {
            return mConfig.getConfig().getString("commands." + plugin + ".join");
        }
        else {
            return mConfig.getConfig().getString("commands.unknown.join");
        }
    }

    // Get the quit command from a type
    public static String getQuitCommand(String type) {

        type = type.toLowerCase();
        String plugin = getPlugin(type);

        if( getPluginKeys().contains(plugin) ) {
            return mConfig.getConfig().getString("commands." + plugin + ".quit");
        }
        else {
            return mConfig.getConfig().getString("commands.unknown.quit");
        }
    }

}