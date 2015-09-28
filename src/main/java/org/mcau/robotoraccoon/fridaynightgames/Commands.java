package org.mcau.robotoraccoon.fridaynightgames;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mcau.robotoraccoon.fridaynightgames.command.*;
import org.mcau.robotoraccoon.fridaynightgames.utility.uMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Commands implements CommandExecutor {

    public static void playersHelp(CommandSender sender) {
        uMessage.colour(sender, " &7===== &5Options &7=====");
        //if (sender.hasPermission(cEnabled.getPermission()))
            uMessage.colour(sender, "&5Enabled [T|F] &f> &dLook at or change the status.");
        //if (sender.hasPermission(cGame.getPermission()))
            uMessage.colour(sender, "&5Game &f> &dStart, End, Add, and Remove games.");
        //if (sender.hasPermission(cJoin.getPermission()))
            uMessage.colour(sender, "&5Join &f> &dJoin in on the fun.");
        //if (sender.hasPermission(cList.getPermission()))
            uMessage.colour(sender, "&5List &f> &dLook at the available maps.");
        //if (sender.hasPermission(cPlayers.getPermission()))
            uMessage.colour(sender, "&5Players &f> &dSee who's joined.");
        //if (sender.hasPermission(cQuit.getPermission()))
            uMessage.colour(sender, "&5Quit &f> &dQuit having fun.");
        //if (sender.hasPermission(cReload.getPermission()))
            uMessage.colour(sender, "&5Reload &f> &dReload the config.");
        //if (sender.hasPermission(cResults.getPermission()))
            uMessage.colour(sender, "&5Results &f> &dView the vote results.");
        //if( sender.hasPermission( cType.getPermission() ))
        //    uBroadcast.colour(sender, "&5Type" &f> &dList, Add, and Remove types.");
        //if (sender.hasPermission(cVote.getPermission()))
            uMessage.colour(sender, "&5Vote &f> &dVote for a map.");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("fng") || cmd.getName().equalsIgnoreCase("fridaynightgames")) {

            if (args.length == 0) {

                playersHelp(sender);
            } else try {
                SubCommand command;

                switch (switchCommands.valueOf(args[0].toUpperCase())) {

                    case ENABLED:
                        command = new cEnabled();
                        break;

                    case GAME:
                        command = new cGame();
                        break;

                    case JOIN:
                        command = new cJoin();
                        break;

                    case LIST:
                        command = new cList();
                        break;

                    case PLAYERS:
                        command = new cPlayers();
                        break;

                    case QUIT:
                        command = new cQuit();
                        break;

                    case RELOAD:
                        command = new cReload();
                        break;

                    case RESULTS:
                        command = new cResults();
                        break;

                    case TYPE:
                        command = new cType();
                        break;

                    case VOTE:
                        command = new cVote();
                        break;

                    default:
                        throw new IllegalArgumentException();
                }

                // If command does not support console usage
                if (!(sender instanceof Player) && !command.isConsoleAllowed()) {
                    sender.sendMessage(uMessage.getNoConsole());
                    return true;
                }

                // No permission
                if (!sender.hasPermission(command.getPermission())) {
                    uMessage.colour(sender, uMessage.getDenied());
                    return true;
                }

                // Convert String[] to List<String>, removing first argument.
                List<String> argsList = new ArrayList<>(Arrays.asList(args));
                argsList.remove(0);
                command.run(sender, argsList);

            } catch (IllegalArgumentException e) {

                playersHelp(sender);
            }
        }

        return true;
    }

    private enum switchCommands {
        ENABLED, GAME, JOIN, LIST, PLAYERS, QUIT, RELOAD, RESULTS, TYPE, VOTE
    }

}
