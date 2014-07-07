package org.mcau.robotoraccoon.fridaynightgames;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mcau.robotoraccoon.fridaynightgames.command.*;

public class mCommands implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String commandNoConsole = commandError + "You may not run this command from console.";

        if( cmd.getName().equalsIgnoreCase("fng") || cmd.getName().equalsIgnoreCase("fridaynightgames") ) {

            if( args.length == 0 ) {

                playersHelp(sender);
            }
            else try {
                switch( switchCommands.valueOf( args[0].toUpperCase() ) ) {

                    case ENABLED:
                        cEnabled.enabled(sender, args);
                        break;

                    case GAME:
                        cGame.game(sender, args);
                        break;

                    case JOIN:
                        if( !(sender instanceof Player) ) {
                            sender.sendMessage(commandNoConsole);
                        } else {
                            cJoin.join(sender, args);
                        }
                        break;

                    case LIST:
                        cList.list(sender, args);
                        break;

                    case PLAYERS:
                        cPlayers.players(sender, args);
                        break;

                    case QUIT:
                        if( !(sender instanceof Player) ) {
                            sender.sendMessage(commandNoConsole);
                        } else {
                            cQuit.quit(sender, args);
                        }
                        break;

                    case RELOAD:
                        cReload.reload(sender, args);
                        break;

                    case RESULTS:
                        cResults.results(sender, args);
                        break;

                    case TYPE:
                        cType.type(sender, args);
                        break;

                    case VOTE:
                        if( !(sender instanceof Player) ) {
                            sender.sendMessage(commandNoConsole);
                        } else {
                            cVote.vote(sender, args);
                        }
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

    private static String commandPrefix = ChatColor.DARK_PURPLE + "[FNG] " + ChatColor.YELLOW;
    private static String commandError = commandPrefix + ChatColor.DARK_RED + "Error: " + ChatColor.RED;
    private static String commandDenied = getError() + "You do not have permission to run this command.";
    private static String commandDisabled = commandError + "FNG is not running at this time.";

    public static String getPrefix()   { return commandPrefix;   }
    public static String getError()    { return commandError;    }
    public static String getDenied()   { return commandDenied;   }
    public static String getDisabled() { return commandDisabled; }

    public static void playersHelp(CommandSender sender) {
        if( sender.hasPermission( cEnabled.getPermission() ))
            sender.sendMessage(ChatColor.DARK_PURPLE + "Enabled [T|F]" + ChatColor.WHITE + " > " + ChatColor.LIGHT_PURPLE + "Look at or change the status.");
        if( sender.hasPermission( cGame.getPermission() ))
            sender.sendMessage(ChatColor.DARK_PURPLE + "Game" + ChatColor.WHITE + " > " + ChatColor.LIGHT_PURPLE + "Start, End, Add, and Remove games.");
        if( sender.hasPermission( cJoin.getPermission() ))
            sender.sendMessage(ChatColor.DARK_PURPLE + "Join" + ChatColor.WHITE + " > " + ChatColor.LIGHT_PURPLE + "Join in on the fun.");
        if( sender.hasPermission( cList.getPermission() ))
            sender.sendMessage(ChatColor.DARK_PURPLE + "List" + ChatColor.WHITE + " > " + ChatColor.LIGHT_PURPLE + "Look at the available maps.");
        if( sender.hasPermission( cPlayers.getPermission() ))
            sender.sendMessage(ChatColor.DARK_PURPLE + "Players" + ChatColor.WHITE + " > " + ChatColor.LIGHT_PURPLE + "See who's joined.");
        if( sender.hasPermission( cQuit.getPermission() ))
            sender.sendMessage(ChatColor.DARK_PURPLE + "Quit" + ChatColor.WHITE + " > " + ChatColor.LIGHT_PURPLE + "Quit having fun.");
        if( sender.hasPermission( cReload.getPermission() ))
            sender.sendMessage(ChatColor.DARK_PURPLE + "Reload" + ChatColor.WHITE + " > " + ChatColor.LIGHT_PURPLE + "Reload the config.");
        if( sender.hasPermission( cResults.getPermission() ))
            sender.sendMessage(ChatColor.DARK_PURPLE + "Results" + ChatColor.WHITE + " > " + ChatColor.LIGHT_PURPLE + "View the vote results.");
        //if( sender.hasPermission( cType.getPermission() ))
        //    sender.sendMessage(ChatColor.DARK_PURPLE + "Type" + ChatColor.WHITE + " > " + ChatColor.LIGHT_PURPLE + "List, Add, and Remove types.");
        if( sender.hasPermission( cVote.getPermission() ))
            sender.sendMessage(ChatColor.DARK_PURPLE + "Vote" + ChatColor.WHITE + " > " + ChatColor.LIGHT_PURPLE + "Vote for a map.");
    }

}
