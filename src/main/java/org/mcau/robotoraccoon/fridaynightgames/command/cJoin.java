package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mcau.robotoraccoon.fridaynightgames.Commands;
import org.mcau.robotoraccoon.fridaynightgames.Main;
import org.mcau.robotoraccoon.fridaynightgames.utility.uBroadcast;

public class cJoin {

    public static void run(CommandSender sender, String[] args) {

        if (!sender.hasPermission(getPermission())) {
            uBroadcast.colour(sender, Commands.getDenied());
            return;
        }

        if (!Main.fngEnabled) {
            uBroadcast.colour(sender, Commands.getDisabled());
            return;
        }

        Player player = (Player) sender;

        if (Main.playerList.containsKey(player.getUniqueId())) {
            uBroadcast.colour(sender, Commands.getError() + "You have already joined the games.");
        } else {
            Main.playerList.put(player.getUniqueId(), player);
            uBroadcast.colour(sender, Commands.getPrefix() + "You have joined the games! You'll join with the next available lobby!");
        }

    }

    public static String getPermission() {
        return "fng.player";
    }

}
