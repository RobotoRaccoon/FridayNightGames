package org.mcau.robotoraccoon.fridaynightgames;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class mMain extends JavaPlugin {

    private static Plugin plugin;

    // FNG
    public static Boolean fngEnabled;
    public static final List<String> fngPlayedGames = new ArrayList<>();
    public static final HashMap<UUID, Player> playerList = new HashMap<>();
    public static final HashMap<UUID, String> voteList = new HashMap<>();

    // Vault
    public static Economy econ = null;

    @Override
    public void onEnable() {

        plugin = this;
        fngEnabled = false;

        // Vault
        setupEconomy();

        // Events
        PluginManager pluginManager = plugin.getServer().getPluginManager();
        pluginManager.registerEvents(new mEvents(), this);

        // Commands
        getCommand( "fridaynightgames" ).setExecutor( new mCommands() );
        getCommand( "fng"              ).setExecutor( new mCommands() );

        // Config
        new mConfig();
        mConfig.createAllFiles();

    }

    @Override
    public void onDisable() {

    }

    public static Plugin getPlugin() {
        return plugin;
    }

    private boolean setupEconomy() {
        if( getServer().getPluginManager().getPlugin("Vault") == null ) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if( rsp == null ) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

}
