package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.Commands;
import org.mcau.robotoraccoon.fridaynightgames.Main;
import org.mcau.robotoraccoon.fridaynightgames.utility.uBroadcast;
import org.mcau.robotoraccoon.fridaynightgames.utility.uPlayerList;
import org.mcau.robotoraccoon.fridaynightgames.utility.uVoting;

import java.util.List;

public class cEnabled extends SubCommand {

    public String getPermission() {
        return "fng.operator";
    }

    public String getUsage() {
        return "enabled [T|F]";
    }

    public void run(CommandSender sender, List<String> args) {

        if (args.size() < 1) {

            if (Main.getFngEnabled())
                uBroadcast.colour(sender, Commands.getPrefix() + "Status: &aEnabled");
            else
                uBroadcast.colour(sender, Commands.getPrefix() + "Status: &cDisabled");

        } else if (args.get(0).matches("(?i)T.*")) {

            Main.setFngEnabled(true);
            uPlayerList.clearList();
            uVoting.generateList();

            uBroadcast.global(Commands.getPrefix() + "Has been &aEnabled &eby &5" + sender.getName());
            uBroadcast.global(Commands.getPrefix() + "Use &5/FNG Join&e to play in the games!");

        } else {

            Main.setFngEnabled(false);
            uPlayerList.clearList();

            uBroadcast.global(Commands.getPrefix() + "Has been &cDisabled &eby &5" + sender.getName());

        }
    }

}
