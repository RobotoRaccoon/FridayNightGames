package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.Main;
import org.mcau.robotoraccoon.fridaynightgames.utility.uMessage;
import org.mcau.robotoraccoon.fridaynightgames.utility.uVoting;

import java.util.List;

public class cResults extends SubCommand {

    public String getPermission() {
        return "fng.player";
    }

    public String getUsage() {
        return "results";
    }

    public String getDescription() {
        return "&5Results &f> &dView the vote results.";
    }

    public void run(CommandSender sender, List<String> args) {

        if (!Main.getFngEnabled()) {
            uMessage.colour(sender, uMessage.getDisabled());
            return;
        }

        if (args.size() < 1) {
            uMessage.colour(sender, uMessage.getPrefix() + "The most voted maps: &c" + uVoting.getMostVoted().toString());
            return;
        }

        if (args.get(0).equalsIgnoreCase("reset")) {

            if (!sender.hasPermission("fng.host")) {
                uMessage.colour(sender, uMessage.getDenied());
                return;
            }

            uVoting.generateList(5);
            uMessage.colour(sender, uMessage.getPrefix() + "The results have been reset and a new voting list has been generated.");
            return;
        }

    }

}

