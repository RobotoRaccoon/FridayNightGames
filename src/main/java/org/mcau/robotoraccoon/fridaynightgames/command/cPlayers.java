package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.Main;
import org.mcau.robotoraccoon.fridaynightgames.utility.uMessage;
import org.mcau.robotoraccoon.fridaynightgames.utility.uPlayerList;

import java.util.List;

public class cPlayers extends SubCommand {

    public String getPermission() {
        return "fng.player";
    }

    public String getUsage() {
        return "players <count|list|random>";
    }

    public void run(CommandSender sender, List<String> args) {

        if (!Main.getFngEnabled()) {
            uMessage.colour(sender, uMessage.getDisabled());
            return;
        }

        if (args.size() < 1) {
            playersHelp(sender);
        } else try {
            switch (switchCommands.valueOf(args.get(0).toUpperCase())) {

                case COUNT:
                    uMessage.colour(sender, uMessage.getPrefix() + "Current players: &c" + uPlayerList.getSize());
                    break;

                case LIST:

                    if (uPlayerList.getSize() == 0) {
                        uMessage.colour(sender, uMessage.getError() + "No one has joined FNG.");
                        break;
                    }

                    uMessage.colour(sender, uMessage.getPrefix() + "Current players: &c" + uPlayerList.getPlayerNames());
                    break;

                case RANDOM:

                    if (uPlayerList.getSize() == 0) {
                        uMessage.colour(sender, uMessage.getError() + "No one has joined FNG.");
                        break;
                    }

                    uMessage.colour(sender, uMessage.getPrefix() + "Random player: &c" + uPlayerList.getRandomPlayer().getName());
                    break;
            }

        } catch (IllegalArgumentException e) {
            playersHelp(sender);
        }

    }

    public static void playersHelp(CommandSender sender) {
        uMessage.colour(sender, "&5Players Count &f> &dAmount of joined players.");
        uMessage.colour(sender, "&5Players List &f> &dNames of joined players.");
        uMessage.colour(sender, "&5Players Random &f> &dA random players' name.");
    }

    private enum switchCommands {
        COUNT, LIST, RANDOM
    }

}
