package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.mCommands;
import org.mcau.robotoraccoon.fridaynightgames.mMain;
import org.mcau.robotoraccoon.fridaynightgames.utility.uBroadcast;
import org.mcau.robotoraccoon.fridaynightgames.utility.uPlayerList;

public class cPlayers {

    public static void players(CommandSender sender, String[] args) {

        if( !sender.hasPermission( getPermission() ) ) {
            uBroadcast.colour(sender, mCommands.getDenied());
            return;
        }

        if( !mMain.fngEnabled ) {
            uBroadcast.colour(sender, mCommands.getDisabled());
            return;
        }

        if( args.length < 2 ) {
            playersHelp(sender);
        }
        else try {
            switch( switchCommands.valueOf( args[1].toUpperCase() ) ) {

                case COUNT:
                    uBroadcast.colour(sender, mCommands.getPrefix() + "Current players: &c" + uPlayerList.getSize());
                    break;

                case LIST:

                    if( uPlayerList.getSize() == 0 ) {
                        uBroadcast.colour(sender, mCommands.getError() + "No one has joined FNG.");
                        break;
                    }

                    uBroadcast.colour(sender, mCommands.getPrefix() + "Current players: &c" + uPlayerList.getPlayerNames());
                    break;

                case RANDOM:

                    if( uPlayerList.getSize() == 0 ) {
                        uBroadcast.colour(sender, mCommands.getError() + "No one has joined FNG.");
                        break;
                    }

                    uBroadcast.colour(sender, mCommands.getPrefix() + "Random player: &c" + uPlayerList.getRandomPlayer().getName());
                    break;
            }

        } catch (IllegalArgumentException e) {
            playersHelp(sender);
        }

    }

    public static void playersHelp(CommandSender sender) {
        uBroadcast.colour(sender, "&5Players Count &f> &dAmount of joined players.");
        uBroadcast.colour(sender, "&5Players List &f> &dNames of joined players.");
        uBroadcast.colour(sender, "&5Players Random &f> &dA random players' name.");
    }

    private enum switchCommands {
        COUNT, LIST, RANDOM
    }

    public static String getPermission() { return "fng.player"; }

}
