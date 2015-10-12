package org.mcau.robotoraccoon.fridaynightgames.games;

import org.mcau.robotoraccoon.fridaynightgames.Config;

// -- Draft -- //
public class MinigameType {

    private String key;
    private String name;
    private String plugin;

    public MinigameType(String key, String name, String plugin) {
        this.key = key.toLowerCase();
        this.name = name;
        this.plugin = plugin.toLowerCase();
    }

    public MinigameType(String key) {
        loadFromConfig(key);
    }

    // Config file methods //
    public boolean loadFromConfig(String key) {
        if (!Config.getConfig().contains("types." + key))
            return false;

        this.key = key;

        // Old config set-up.
        if (!Config.getConfig().contains("types." + key + ".name")) {
            name = key;
            plugin = Config.getConfig().getString("types." + key);
            return true;
        }

        name = Config.getConfig().getString("types." + key + ".name");
        plugin = Config.getConfig().getString("types." + key + ".plugin");
        return true;
    }

    public void saveToConfig() {
        Config.getConfig().set("types." + getKey() + ".name", name);
        Config.getConfig().set("types." + getKey() + ".plugin", plugin);
        Config.saveConfigs();
    }

    // Variable accessors //
    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlugin() {
        return plugin;
    }

    // Overridden methods
    public String toString() {
        return key;
    }

}
