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
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    private static String commandPrefix = ChatColor.DARK_PURPLE + "[FNG] " + ChatColor.YELLOW;
    private static String commandError = getPrefix() + ChatColor.DARK_RED + "Error: " + ChatColor.RED;
    private static String commandDenied = getError() + "You do not have permission to run this command.";
    private static String commandDisabled = getError() + "FNG is not running at this time.";
    private static String commandNoConsole = getError() + "You may not run this command from console.";

    public static String getPrefix() {
        return commandPrefix;
    }

    public static String getError() {
        return commandError;
    }

    public static String getDenied() {
        return commandDenied;
    }

    public static String getDisabled() {
        return commandDisabled;
    }

    public static String getNoConsole() {
        return commandNoConsole;
    }
}
