package org.mcau.robotoraccoon.fridaynightgames;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcau.robotoraccoon.fridaynightgames.command.Commands;
import org.mcau.robotoraccoon.fridaynightgames.command.games.MinigameMap;
import org.mcau.robotoraccoon.fridaynightgames.utility.GameUtil;

import java.util.*;

public class Main extends JavaPlugin {

    // FNG
    private static final List<MinigameMap> playedGames = new ArrayList<>();
    private static final List<MinigameMap> minigames = new ArrayList<>();
    private static final HashMap<UUID, Player> playerList = new HashMap<>();
    private static Boolean fngEnabled = false;
    private static Boolean autoStartEnabled = false;

    // Vault
    private static Economy econ = null;
    private static Plugin plugin;

    public static Plugin getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;

        // Vault
        setupEconomy();

        // Events
        PluginManager pluginManager = plugin.getServer().getPluginManager();
        pluginManager.registerEvents(new Events(), this);

        // Commands
        Commands.generateCommands();
        getCommand("fridaynightgames").setExecutor(new Commands());
        getCommand("fng").setExecutor(new Commands());

        // Config
        new Config();
        Config.createAllFiles();

        // Load minigames
        GameUtil.loadMinigamesFromConfig();
    }

    @Override
    public void onDisable() {
        GameUtil.saveMinigamesToConfig();
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    // FNG
    public static HashMap<UUID, Player> getPlayerList() {
        return playerList;
    }

    public static List<MinigameMap> getPlayedGames() {
        return playedGames;
    }

    public static List<MinigameMap> getMiniames() {
        return minigames;
    }

    public static Boolean getFngEnabled() {
        return fngEnabled;
    }

    public static void setFngEnabled(Boolean fngEnabled) {
        Main.fngEnabled = fngEnabled;
    }

    public static Boolean getAutoStartEnabled() {
        return autoStartEnabled;
    }

    public static void setAutoStartEnabled(Boolean autoStartEnabled) {
        Main.autoStartEnabled = autoStartEnabled;
    }

    // Vault
    public static Economy getEcon() {
        return econ;
    }

}
