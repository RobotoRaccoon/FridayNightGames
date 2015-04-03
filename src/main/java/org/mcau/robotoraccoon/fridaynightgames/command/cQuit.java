package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mcau.robotoraccoon.fridaynightgames.Commands;
import org.mcau.robotoraccoon.fridaynightgames.Main;
import org.mcau.robotoraccoon.fridaynightgames.utility.uBroadcast;

public class cQuit {

    public static void run(CommandSender sender, String[] args) {

        if (!sender.hasPermission(getPermission())) {
            uBroadcast.colour(sender, Commands.getDenied());
            return;
        }

        if (!Main.getFngEnabled()) {
            uBroadcast.colour(sender, Commands.getDisabled());
            return;
        }

        Player player = (Player) sender;
        if (Main.getPlayerList().containsKey(player.getUniqueId())) {
            Main.getPlayerList().remove(player.getUniqueId());
            uBroadcast.colour(sender, Commands.getPrefix() + "You have quit the games! Thanks for playing!");
        } else {
            uBroadcast.colour(sender, Commands.getError() + "You weren't in the games anyway.");
        }

    }

    public static String getPermission() {
        return "fng.player";
    }

}
