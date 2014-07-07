package org.mcau.robotoraccoon.fridaynightgames.utility;

import org.bukkit.Bukkit;

import java.util.UUID;

public class uRunAs {

    public static void asConsole(String command) {
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), command);
    }

    public static void asPlayer(String command, UUID player) {
        if (Bukkit.getServer().getPlayer(player) != null) {
            Bukkit.getServer().getPlayer(player).performCommand(command);
        }

    }

}
