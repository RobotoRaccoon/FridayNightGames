package org.mcau.robotoraccoon.fridaynightgames;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mcau.robotoraccoon.fridaynightgames.command.*;
import org.mcau.robotoraccoon.fridaynightgames.utility.uBroadcast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Commands implements CommandExecutor {

    private static String commandPrefix = ChatColor.DARK_PURPLE + "[FNG] " + ChatColor.YELLOW;
    private static String commandError = getPrefix() + ChatColor.DARK_RED + "Error: " + ChatColor.RED;
    private static String commandDenied = getError() + "You do not have permission to run this command.";
    private static String commandDisabled = getPrefix() + "FNG is not running at this time.";
    private static String commandNoConsole = getPrefix() + "You may not run this command from console.";

    public static String getPrefix() {
        return commandPrefix;
    }

    public static String getError() {
        return commandError;
    }

    public static String getDenied() {
        return commandDenied;
    }

    public static String getDisabled() {
        return commandDisabled;
    }

    public static String getNoConsole() {
        return commandNoConsole;
    }

    public static void playersHelp(CommandSender sender) {
        uBroadcast.colour(sender, " &7===== &5Options &7=====");
        //if (sender.hasPermission(cEnabled.getPermission()))
            uBroadcast.colour(sender, "&5Enabled [T|F] &f> &dLook at or change the status.");
        //if (sender.hasPermission(cGame.getPermission()))
            uBroadcast.colour(sender, "&5Game &f> &dStart, End, Add, and Remove games.");
        //if (sender.hasPermission(cJoin.getPermission()))
            uBroadcast.colour(sender, "&5Join &f> &dJoin in on the fun.");
        //if (sender.hasPermission(cList.getPermission()))
            uBroadcast.colour(sender, "&5List &f> &dLook at the available maps.");
        //if (sender.hasPermission(cPlayers.getPermission()))
            uBroadcast.colour(sender, "&5Players &f> &dSee who's joined.");
        //if (sender.hasPermission(cQuit.getPermission()))
            uBroadcast.colour(sender, "&5Quit &f> &dQuit having fun.");
        //if (sender.hasPermission(cReload.getPermission()))
            uBroadcast.colour(sender, "&5Reload &f> &dReload the config.");
        //if (sender.hasPermission(cResults.getPermission()))
            uBroadcast.colour(sender, "&5Results &f> &dView the vote results.");
        //if( sender.hasPermission( cType.getPermission() ))
        //    uBroadcast.colour(sender, "&5Type" &f> &dList, Add, and Remove types.");
        //if (sender.hasPermission(cVote.getPermission()))
            uBroadcast.colour(sender, "&5Vote &f> &dVote for a map.");
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
                    sender.sendMessage(commandNoConsole);
                    return true;
                }

                // No permission
                if (!sender.hasPermission(command.getPermission())) {
                    uBroadcast.colour(sender, Commands.getDenied());
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
