package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.Commands;
import org.mcau.robotoraccoon.fridaynightgames.Main;
import org.mcau.robotoraccoon.fridaynightgames.utility.uBroadcast;
import org.mcau.robotoraccoon.fridaynightgames.utility.uVoting;

import java.util.List;

public class cResults extends SubCommand {

    public String getPermission() {
        return "fng.player";
    }

    public String getUsage() {
        return "results";
    }

    public void run(CommandSender sender, List<String> args) {

        if (!Main.getFngEnabled()) {
            uBroadcast.colour(sender, Commands.getDisabled());
            return;
        }

        if (args.size() < 1) {
            uBroadcast.colour(sender, Commands.getPrefix() + "The most voted maps: &c" + uVoting.getMostVoted().toString());
            return;
        }

        if (args.get(0).equalsIgnoreCase("reset")) {

            if (!sender.hasPermission("fng.host")) {
                uBroadcast.colour(sender, Commands.getDenied());
                return;
            }

            uVoting.generateList(5);
            uBroadcast.colour(sender, Commands.getPrefix() + "The results have been reset and a new voting list has been generated.");
            return;
        }

    }

}

