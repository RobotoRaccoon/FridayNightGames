package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.Main;
import org.mcau.robotoraccoon.fridaynightgames.utility.uMessage;
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
                uMessage.colour(sender, uMessage.getPrefix() + "Status: &aEnabled");
            else
                uMessage.colour(sender, uMessage.getPrefix() + "Status: &cDisabled");

        } else if (args.get(0).matches("(?i)T.*")) {

            Main.setFngEnabled(true);
            uPlayerList.clearList();
            uVoting.generateList();

            uMessage.global(uMessage.getPrefix() + "Has been &aEnabled &eby &5" + sender.getName());
            uMessage.global(uMessage.getPrefix() + "Use &5/FNG Join&e to play in the games!");

        } else {

            Main.setFngEnabled(false);
            uPlayerList.clearList();

            uMessage.global(uMessage.getPrefix() + "Has been &cDisabled &eby &5" + sender.getName());

        }
    }

}
