package org.mcau.robotoraccoon.fridaynightgames.command.subCommands;

import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.Main;
import org.mcau.robotoraccoon.fridaynightgames.command.SubCommand;
import org.mcau.robotoraccoon.fridaynightgames.utility.MessageUtil;
import org.mcau.robotoraccoon.fridaynightgames.utility.ListUtil;

import java.util.List;

public class PlayersCommand extends SubCommand {

    public String getPermission() {
        return "fng.player";
    }

    public String getUsage() {
        return "players <count|list|random>";
    }

    public String getDescription() {
        return "&5Players &f> &dSee who's joined.";
    }

    public void run(CommandSender sender, List<String> args) {

        if (!Main.getFngEnabled()) {
            MessageUtil.colour(sender, MessageUtil.getDisabled());
            return;
        }

        if (args.size() < 1) {
            playersHelp(sender);
        } else try {
            switch (switchCommands.valueOf(args.get(0).toUpperCase())) {

                case COUNT:
                    MessageUtil.colour(sender, MessageUtil.getPrefix() + "Current players: &c" + ListUtil.getSize());
                    break;

                case LIST:

                    if (ListUtil.getSize() == 0) {
                        MessageUtil.colour(sender, MessageUtil.getError() + "No one has joined FNG.");
                        break;
                    }

                    MessageUtil.colour(sender, MessageUtil.getPrefix() + "Current players: &c" + ListUtil.getPlayerNames());
                    break;

                case RANDOM:

                    if (ListUtil.getSize() == 0) {
                        MessageUtil.colour(sender, MessageUtil.getError() + "No one has joined FNG.");
                        break;
                    }

                    MessageUtil.colour(sender, MessageUtil.getPrefix() + "Random player: &c" + ListUtil.getRandomPlayer().getName());
                    break;
            }

        } catch (IllegalArgumentException e) {
            playersHelp(sender);
        }

    }

    public static void playersHelp(CommandSender sender) {
        MessageUtil.colour(sender, "&5Players Count &f> &dAmount of joined players.");
        MessageUtil.colour(sender, "&5Players List &f> &dNames of joined players.");
        MessageUtil.colour(sender, "&5Players Random &f> &dA random players' name.");
    }

    private enum switchCommands {
        COUNT, LIST, RANDOM
    }

}