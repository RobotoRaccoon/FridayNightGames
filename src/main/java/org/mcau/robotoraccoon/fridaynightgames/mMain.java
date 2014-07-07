package org.mcau.robotoraccoon.fridaynightgames;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class mMain extends JavaPlugin {

    private static Plugin plugin;

    // FNG
    public static Boolean fngEnabled;
    public static final List<String> fngPlayedGames = new ArrayList<>();
    public static final HashMap<UUID, String> playerList = new HashMap<>();
    public static final HashMap<UUID, String> voteList = new HashMap<>();

    @Override
    public void onEnable() {

        plugin = this;
        fngEnabled = false;

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

}
