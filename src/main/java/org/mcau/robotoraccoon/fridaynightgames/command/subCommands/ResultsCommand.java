package org.mcau.robotoraccoon.fridaynightgames.command.subCommands;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.Main;
import org.mcau.robotoraccoon.fridaynightgames.command.SubCommand;
import org.mcau.robotoraccoon.fridaynightgames.utility.LangUtil;
import org.mcau.robotoraccoon.fridaynightgames.utility.MessageUtil;
import org.mcau.robotoraccoon.fridaynightgames.utility.VotingUtil;

import java.util.List;

public class ResultsCommand extends SubCommand {

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
            MessageUtil.colour(sender, LangUtil.formatErrorKey("error.fngDisabled"));
            return;
        }

        if (args.size() < 1) {
            String maps = "" + StringUtils.join(VotingUtil.getMostVoted(), ", ");
            MessageUtil.colour(sender, LangUtil.formatPrefix("The most voted maps: &c" + (maps.isEmpty() ? "N/A" : maps)));
            return;
        }

        if (args.get(0).equalsIgnoreCase("reset")) {

            if (!sender.hasPermission("fng.host")) {
                MessageUtil.colour(sender, LangUtil.formatErrorKey("error.noPermission"));
                return;
            }

            VotingUtil.generateList(5);
            MessageUtil.colour(sender, LangUtil.formatPrefix("The results have been reset and a new voting list has been generated."));
            return;
        }

    }

}

