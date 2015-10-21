package org.mcau.robotoraccoon.fridaynightgames.command.subCommands;

import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.Main;
import org.mcau.robotoraccoon.fridaynightgames.command.SubCommand;
import org.mcau.robotoraccoon.fridaynightgames.utility.*;

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
                MessageUtil.colour(sender, LangUtil.formatPrefix("Status: &aEnabled"));
            else
                MessageUtil.colour(sender, LangUtil.formatPrefix("Status: &cDisabled"));

            MessageUtil.colour(sender, LangUtil.formatPrefix("To change the status, run &6/fng " + getUsage()));

        } else if (args.get(0).startsWith("T") || args.get(0).startsWith("t")) {

            Main.setFngEnabled(true);
            //uPlayerList.clearList(); // Disabled clearing of the list, forces disabling FNG first.
            VotingUtil.generateList();

            MessageUtil.global(LangUtil.formatPrefix("FNG has been &aEnabled &eby &5" + sender.getName() +
                    "\n&eUse &5/FNG Join&e to play in the games!"));

        } else {

            Main.setFngEnabled(false);
            GameUtil.saveMinigamesToConfig();
            PlayerListUtil.clearList();
            Main.getPlayedGames().clear();

            MessageUtil.global(LangUtil.formatPrefix("FNG has been &cDisabled &eby &5" + sender.getName() +
                    "\n&eWe hope you all had fun!"));

        }
    }

}
