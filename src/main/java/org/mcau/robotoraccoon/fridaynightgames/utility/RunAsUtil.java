package org.mcau.robotoraccoon.fridaynightgames.utility;

import org.bukkit.Bukkit;

import java.util.UUID;

public class RunAsUtil {

    public static void asConsole(String command) {
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), command);
    }

    public static void asPlayer(String command, UUID player) {

        try {
            if (Bukkit.getServer().getPlayer(player) != null) {
                Bukkit.getServer().getPlayer(player).performCommand(command);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Bukkit.getLogger().warning("Failed to perform command on " +
                    Bukkit.getServer().getPlayer(player).getPlayerListName() + ": " + command);
        }

    }

}
