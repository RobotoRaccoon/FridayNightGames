package org.mcau.robotoraccoon.fridaynightgames;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {

    private static File configFile;
    private static File gamesFile;
    private static File langFile;

    private static FileConfiguration config;
    private static FileConfiguration gamesConfig;
    private static FileConfiguration lang;

    public Config() {
        configFile = new File(Main.getPlugin().getDataFolder(), "config.yml");
        gamesFile = new File(Main.getPlugin().getDataFolder(), "games.yml");
        langFile = new File(Main.getPlugin().getDataFolder(), "lang.yml");

        config = YamlConfiguration.loadConfiguration(configFile);
        gamesConfig = YamlConfiguration.loadConfiguration(gamesFile);
        lang = YamlConfiguration.loadConfiguration(langFile);
    }

    public static void createAllFiles() {
        if (!configFile.exists())
            Main.getPlugin().saveResource(configFile.getName(), true);

        if (!gamesFile.exists())
            Main.getPlugin().saveResource(gamesFile.getName(), true);

        if (!langFile.exists())
            Main.getPlugin().saveResource(langFile.getName(), true);

        loadConfigs();
    }

    public static boolean loadConfigs() {
        try {
            config      = YamlConfiguration.loadConfiguration(new File(Main.getPlugin().getDataFolder(), configFile.getName()));
            gamesConfig = YamlConfiguration.loadConfiguration(new File(Main.getPlugin().getDataFolder(), gamesFile.getName()));
            lang        = YamlConfiguration.loadConfiguration(new File(Main.getPlugin().getDataFolder(), langFile.getName()));

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
            lang.save(langFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static FileConfiguration getConfig() {
        return config;
    }

    public static FileConfiguration getGamesConfig() {
        return gamesConfig;
    }

    public static FileConfiguration getLang() {
        return lang;
    }
}
