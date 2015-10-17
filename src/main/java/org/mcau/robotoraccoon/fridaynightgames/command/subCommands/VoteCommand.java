package org.mcau.robotoraccoon.fridaynightgames.command.subCommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mcau.robotoraccoon.fridaynightgames.Main;
import org.mcau.robotoraccoon.fridaynightgames.command.SubCommand;
import org.mcau.robotoraccoon.fridaynightgames.utility.LangUtil;
import org.mcau.robotoraccoon.fridaynightgames.utility.MessageUtil;
import org.mcau.robotoraccoon.fridaynightgames.utility.VotingUtil;

import java.util.List;

public class VoteCommand extends SubCommand {

    public String getPermission() {
        return "fng.player";
    }

    public String getUsage() {
        return "vote [number]";
    }

    public String getDescription() {
        return "&5Vote &f> &dVote for a map.";
    }

    public void run(CommandSender sender, List<String> args) {

        if (!Main.getFngEnabled()) {
            MessageUtil.colour(sender, LangUtil.formatErrorKey("error.fngDisabled"));
            return;
        }

        // No number supplied, display the candidates.
        if (args.size() < 1) {
            VotingUtil.printList(sender);
            return;
        }

        // Only players from here onwards.
        if (!(sender instanceof Player)) {
            MessageUtil.colour(sender, LangUtil.formatErrorKey("error.noConsole"));
            return;
        }

        Integer index = 0;
        try {
            index = Integer.valueOf(args.get(0)) - 1;
        } catch (Exception e) {
            MessageUtil.colour(sender, LangUtil.formatError("You must specify the number, not the map name."));
            VotingUtil.printList(sender);
            return;
        }

        VotingUtil.vote(sender, index);

    }

}
