package org.mcau.robotoraccoon.fridaynightgames.games;

import org.mcau.robotoraccoon.fridaynightgames.Config;
import org.mcau.robotoraccoon.fridaynightgames.Main;

public class MinigameMap {

    private String name;
    private MinigameType type;
    private Integer playCount;

    public MinigameMap(String name, MinigameType type) {
        this(name, type, 0);
    }
    public MinigameMap(String name, MinigameType type, Integer playCount) {
        this.name = name;
        this.type = type;
        this.playCount = playCount;
    }

    public MinigameMap(String key) {
        loadFromConfig(key);
    }

    public void incrementPlayCount() {
        playCount++;
    }

    public String getKey() {
        return name.toLowerCase();
    }

    // Config file methods //
    public boolean loadFromConfig(String key) {
        if (!Config.getGamesConfig().contains("games." + key))
            return false;

        name = Config.getGamesConfig().getString("games." + key + ".name");
        type = Main.getGameTypes().get(Config.getGamesConfig().getString("games." + key + ".type"));
        playCount = Config.getGamesConfig().getInt("games." + key + ".plays");
        return true;
    }

    public void saveToConfig() {
        Config.getGamesConfig().set("games." + getKey() + ".name", name);
        Config.getGamesConfig().set("games." + getKey() + ".type", type.getKey());
        Config.getGamesConfig().set("games." + getKey() + ".plays", playCount);
        Config.saveConfigs();
    }

    // Variable accessors //
    public String getName() {
        return name;
    }

    public MinigameType getType() {
        return type;
    }

    public Integer getPlayCount() {
        return playCount;
    }

    // Overridden methods
    public String toString() {
        return name;
    }
}
