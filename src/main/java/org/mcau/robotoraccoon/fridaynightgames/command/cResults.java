package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.Commands;
import org.mcau.robotoraccoon.fridaynightgames.Main;
import org.mcau.robotoraccoon.fridaynightgames.utility.uBroadcast;
import org.mcau.robotoraccoon.fridaynightgames.utility.uVoting;

public class cResults {

    public static void results(CommandSender sender, String[] args) {

        if (!sender.hasPermission(getPermission())) {
            uBroadcast.colour(sender, Commands.getDenied());
            return;
        }

        if (!Main.fngEnabled) {
            uBroadcast.colour(sender, Commands.getDisabled());
            return;
        }

        if (args.length < 2) {
            uBroadcast.colour(sender, Commands.getPrefix() + "The most voted maps: &c" + uVoting.getMostVoted().toString());
            return;
        } else if (args[1].equalsIgnoreCase("reset")) {

            if (!sender.hasPermission("fng.host")) {
                uBroadcast.colour(sender, Commands.getDenied());
                return;
            }

            uVoting.generateList(5);
            uBroadcast.colour(sender, Commands.getPrefix() + "The results have been reset and a new voting list has been generated.");
            return;

        }

    }

    public static String getPermission() {
        return "fng.player";
    }

}

