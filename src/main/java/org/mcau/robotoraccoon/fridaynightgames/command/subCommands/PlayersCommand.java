package org.mcau.robotoraccoon.fridaynightgames.command.subCommands;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.Main;
import org.mcau.robotoraccoon.fridaynightgames.command.SubCommand;
import org.mcau.robotoraccoon.fridaynightgames.utility.LangUtil;
import org.mcau.robotoraccoon.fridaynightgames.utility.MessageUtil;
import org.mcau.robotoraccoon.fridaynightgames.utility.PlayerListUtil;

import java.util.Collections;
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
            MessageUtil.colour(sender, LangUtil.formatErrorKey("error.fngDisabled"));
            return;
        }

        if (args.size() < 1) {
            playersHelp(sender);
        } else try {
            switch (switchCommands.valueOf(args.get(0).toUpperCase())) {

                case COUNT:
                    MessageUtil.colour(sender, LangUtil.formatPrefix("Current players: &c" + PlayerListUtil.getSize()));
                    break;

                case LIST:

                    if (PlayerListUtil.getSize() == 0) {
                        MessageUtil.colour(sender, LangUtil.formatError("No one has joined FNG yet"));
                        break;
                    }

                    List<String> playerNames = PlayerListUtil.getPlayerNames();
                    Collections.sort(playerNames);
                    MessageUtil.colour(sender, LangUtil.formatPrefix("Current players: &c" + StringUtils.join(playerNames, ", ")));
                    break;

                case RANDOM:

                    if (PlayerListUtil.getSize() == 0) {
                        MessageUtil.colour(sender, LangUtil.formatErrorKey("No one has joined FNG yet"));
                        break;
                    }

                    MessageUtil.colour(sender, LangUtil.formatPrefix("Random player: &c" + PlayerListUtil.getRandomPlayer().getName()));
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
