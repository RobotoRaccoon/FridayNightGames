package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.mCommands;
import org.mcau.robotoraccoon.fridaynightgames.mMain;
import org.mcau.robotoraccoon.fridaynightgames.utility.uPlayerList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class cPlayers {

    public static void players(CommandSender sender, String[] args) {

        if( !sender.hasPermission( getPermission() ) ) {
            sender.sendMessage(mCommands.getDenied());
            return;
        }

        if( !mMain.fngEnabled ) {
            sender.sendMessage( mCommands.getDisabled() );
            return;
        }

        if( args.length < 2 ) {
            playersHelp(sender);
        }
        else try {
            switch( switchCommands.valueOf( args[1].toUpperCase() ) ) {

                case COUNT:
                    String countMessage = String.format("%SCurrent players: %s%s",
                            mCommands.getPrefix(),
                            ChatColor.RED,
                            uPlayerList.getSize()
                    );
                    sender.sendMessage(countMessage);
                    break;

                case LIST:

                    if( uPlayerList.getSize() == 0 ) {
                        sender.sendMessage(mCommands.getError() + "No one has joined FNG.");
                        break;
                    }

                    String listMessage = String.format("%SCurrent players: %s%s",
                            mCommands.getPrefix(),
                            ChatColor.RED,
                            uPlayerList.getValues()
                    );
                    sender.sendMessage(listMessage);
                    break;

                case RANDOM:

                    if( uPlayerList.getSize() == 0 ) {
                        sender.sendMessage(mCommands.getError() + "No one has joined FNG.");
                        break;
                    }

                    String randomMessage = String.format("%SRandom player: %s%s",
                            mCommands.getPrefix(),
                            ChatColor.RED,
                            uPlayerList.getRandomValue()
                    );
                    sender.sendMessage(randomMessage);
                    break;
            }

        } catch (IllegalArgumentException e) {
            playersHelp(sender);
        }

    }

    public static void playersHelp(CommandSender sender) {
        sender.sendMessage(ChatColor.DARK_PURPLE + "Players Count"  + ChatColor.WHITE + " > " + ChatColor.LIGHT_PURPLE + "Amount of joined players.");
        sender.sendMessage(ChatColor.DARK_PURPLE + "Players List"   + ChatColor.WHITE + " > " + ChatColor.LIGHT_PURPLE + "Names of joined players.");
        sender.sendMessage(ChatColor.DARK_PURPLE + "Players Random" + ChatColor.WHITE + " > " + ChatColor.LIGHT_PURPLE + "A random players' name.");
    }

    private enum switchCommands {
        COUNT, LIST, RANDOM
    }

    public static String getPermission() { return "fng.player"; }

}
