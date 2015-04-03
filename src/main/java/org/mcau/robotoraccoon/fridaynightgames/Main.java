package org.mcau.robotoraccoon.fridaynightgames;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Main extends JavaPlugin {

    public static final List<String> fngPlayedGames = new ArrayList<>();
    public static final HashMap<UUID, Player> playerList = new HashMap<>();
    // FNG
    public static Boolean fngEnabled = false;
    public static Boolean autoStartEnabled = false;
    // Vault
    public static Economy econ = null;
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
        getCommand("fridaynightgames").setExecutor(new Commands());
        getCommand("fng").setExecutor(new Commands());

        // Config
        new Config();
        Config.createAllFiles();

    }

    @Override
    public void onDisable() {

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

}
