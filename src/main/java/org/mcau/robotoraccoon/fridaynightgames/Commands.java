package org.mcau.robotoraccoon.fridaynightgames;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mcau.robotoraccoon.fridaynightgames.command.*;
import org.mcau.robotoraccoon.fridaynightgames.utility.uBroadcast;

public class Commands implements CommandExecutor {

    private static String commandPrefix = ChatColor.DARK_PURPLE + "[FNG] " + ChatColor.YELLOW;
    private static String commandError = commandPrefix + ChatColor.DARK_RED + "Error: " + ChatColor.RED;
    private static String commandDenied = getError() + "You do not have permission to run this command.";
    private static String commandDisabled = commandError + "FNG is not running at this time.";
    private static String commandNoConsole = commandError + "You may not run this command from console.";

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
        if (sender.hasPermission(cEnabled.getPermission()))
            uBroadcast.colour(sender, "&5Enabled [T|F] &f> &dLook at or change the status.");
        if (sender.hasPermission(cGame.getPermission()))
            uBroadcast.colour(sender, "&5Game &f> &dStart, End, Add, and Remove games.");
        if (sender.hasPermission(cJoin.getPermission()))
            uBroadcast.colour(sender, "&5Join &f> &dJoin in on the fun.");
        if (sender.hasPermission(cList.getPermission()))
            uBroadcast.colour(sender, "&5List &f> &dLook at the available maps.");
        if (sender.hasPermission(cPlayers.getPermission()))
            uBroadcast.colour(sender, "&5Players &f> &dSee who's joined.");
        if (sender.hasPermission(cQuit.getPermission()))
            uBroadcast.colour(sender, "&5Quit &f> &dQuit having fun.");
        if (sender.hasPermission(cReload.getPermission()))
            uBroadcast.colour(sender, "&5Reload &f> &dReload the config.");
        if (sender.hasPermission(cResults.getPermission()))
            uBroadcast.colour(sender, "&5Results &f> &dView the vote results.");
        //if( sender.hasPermission( cType.getPermission() ))
        //    uBroadcast.colour(sender, "&5Type" &f> &dList, Add, and Remove types.");
        if (sender.hasPermission(cVote.getPermission()))
            uBroadcast.colour(sender, "&5Vote &f> &dVote for a map.");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("fng") || cmd.getName().equalsIgnoreCase("fridaynightgames")) {

            if (args.length == 0) {

                playersHelp(sender);
            } else try {
                switch (switchCommands.valueOf(args[0].toUpperCase())) {

                    case ENABLED:
                        cEnabled.run(sender, args);
                        break;

                    case GAME:
                        cGame.run(sender, args);
                        break;

                    case JOIN:
                        if (!(sender instanceof Player)) {
                            sender.sendMessage(commandNoConsole);
                        } else {
                            cJoin.run(sender, args);
                        }
                        break;

                    case LIST:
                        cList.run(sender, args);
                        break;

                    case PLAYERS:
                        cPlayers.run(sender, args);
                        break;

                    case QUIT:
                        if (!(sender instanceof Player)) {
                            sender.sendMessage(commandNoConsole);
                        } else {
                            cQuit.run(sender, args);
                        }
                        break;

                    case RELOAD:
                        cReload.run(sender, args);
                        break;

                    case RESULTS:
                        cResults.run(sender, args);
                        break;

                    case TYPE:
                        cType.run(sender, args);
                        break;

                    case VOTE:
                        cVote.run(sender, args);
                        break;
                }

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
