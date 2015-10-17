package org.mcau.robotoraccoon.fridaynightgames.utility;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageUtil {

    public static void global(String message) {
        Bukkit.broadcast(ChatColor.translateAlternateColorCodes('&', message), "fng.player");
    }

    public static void joined(String message) {

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (PlayerListUtil.getKeys().contains(player.getUniqueId())) {
                MessageUtil.colour(player, message);
            }
        }
    }

    public static void notJoined(String message) {

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!PlayerListUtil.getKeys().contains(player.getUniqueId())) {
                MessageUtil.colour(player, message);
            }
        }
    }

    public static void host(String message) {

        Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', message));
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission("fng.host")) {
                MessageUtil.colour(player, message);
            }
        }
    }

    public static void colour(CommandSender sender, String message) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public static void colour(Player player, String message) {
        colour((CommandSender) player, message);
    }

}
