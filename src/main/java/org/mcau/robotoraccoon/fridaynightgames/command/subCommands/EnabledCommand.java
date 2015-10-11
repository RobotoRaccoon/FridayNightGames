package org.mcau.robotoraccoon.fridaynightgames.command.subCommands;

import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.Main;
import org.mcau.robotoraccoon.fridaynightgames.command.SubCommand;
import org.mcau.robotoraccoon.fridaynightgames.utility.MessageUtil;
import org.mcau.robotoraccoon.fridaynightgames.utility.ListUtil;
import org.mcau.robotoraccoon.fridaynightgames.utility.VotingUtil;

import java.util.List;

public class EnabledCommand extends SubCommand {

    public String getPermission() {
        return "fng.operator";
    }

    public String getUsage() {
        return "enabled [T|F]";
    }

    public String getDescription() {
        return "&5Enabled &f> &dLook at or change the status.";
    }

    public void run(CommandSender sender, List<String> args) {

        if (args.size() < 1) {

            if (Main.getFngEnabled())
                MessageUtil.colour(sender, MessageUtil.getPrefix() + "Status: &aEnabled");
            else
                MessageUtil.colour(sender, MessageUtil.getPrefix() + "Status: &cDisabled");

            MessageUtil.colour(sender, MessageUtil.getPrefix() + "To change the status, run &6/fng " + getUsage());

        } else if (args.get(0).startsWith("T") || args.get(0).startsWith("t")) {

            Main.setFngEnabled(true);
            //uPlayerList.clearList(); // Disabled clearing of the list, forces disabling FNG first.
            VotingUtil.generateList();

            MessageUtil.global(MessageUtil.getPrefix() + "Has been &aEnabled &eby &5" + sender.getName());
            MessageUtil.global(MessageUtil.getPrefix() + "Use &5/FNG Join&e to play in the games!");

        } else {

            Main.setFngEnabled(false);
            ListUtil.clearList();

            MessageUtil.global(MessageUtil.getPrefix() + "Has been &cDisabled &eby &5" + sender.getName());
            MessageUtil.global(MessageUtil.getPrefix() + "We hope you all had fun!");

        }
    }

}
