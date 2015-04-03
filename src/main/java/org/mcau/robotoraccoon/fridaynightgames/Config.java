package org.mcau.robotoraccoon.fridaynightgames;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {

    private static File configFile;
    private static File gamesFile;

    private static FileConfiguration config;
    private static FileConfiguration gamesConfig;

    public Config() {

        configFile = new File(Main.getPlugin().getDataFolder(), "config.yml");
        gamesFile = new File(Main.getPlugin().getDataFolder(), "games.yml");

        config = YamlConfiguration.loadConfiguration(configFile);
        gamesConfig = YamlConfiguration.loadConfiguration(gamesFile);

    }

    public static FileConfiguration getConfig() {
        return config;
    }

    public static FileConfiguration getGamesConfig() {
        return gamesConfig;
    }

    public static void createAllFiles() {

        if (!configFile.exists()) {
            Main.getPlugin().saveResource("config.yml", true);
        }
        if (!gamesFile.exists()) {
            Main.getPlugin().saveResource("games.yml", true);
        }

        loadConfigs();

    }

    public static boolean loadConfigs() {

        try {

            config = YamlConfiguration.loadConfiguration(new File(Main.getPlugin().getDataFolder(), "config.yml"));
            gamesConfig = YamlConfiguration.loadConfiguration(new File(Main.getPlugin().getDataFolder(), "games.yml"));

            Main.getPlugin().reloadConfig();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static void saveConfigs() {

        try {

            config.save(configFile);
            gamesConfig.save(gamesFile);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
