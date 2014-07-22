package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.mCommands;
import org.mcau.robotoraccoon.fridaynightgames.mConfig;
import org.mcau.robotoraccoon.fridaynightgames.mMain;
import org.mcau.robotoraccoon.fridaynightgames.utility.*;

public class cGame {

    public static void game( CommandSender sender, String[] args ) {

        if( !sender.hasPermission( getPermission() ) ) {
            sender.sendMessage(mCommands.getDenied());
            return;
        }

        if( args.length < 2 ) {
            playersHelp(sender);
        }
        else try {
            switch( switchCommands.valueOf( args[1].toUpperCase() ) ) {

                case ADD:
                    if( args.length < 4 ) {
                        sender.sendMessage( mCommands.getError() + "/FNG Game Add <Name> <Type>" );
                    }
                    else if( uGameList.getKeys().contains( args[2].toLowerCase() ) ) {
                        sender.sendMessage(mCommands.getError() + "This minigame already exists.");
                    }
                    else if( !uTypeList.getTypes().contains( args[3].toLowerCase() ) ) {
                        sender.sendMessage( mCommands.getError() + "Type not defined. Available types: " + uTypeList.getTypes() );
                    }
                    else {
                        mConfig.getGamesConfig().set("games." + args[2].toLowerCase() + ".name", args[2]);
                        mConfig.getGamesConfig().set("games." + args[2].toLowerCase() + ".type", args[3].toLowerCase());
                        mConfig.saveConfigs();
                        sender.sendMessage( mCommands.getPrefix() + "Added the minigame: " + ChatColor.RED + args[2] + "|" + args[3].toLowerCase());
                    }
                    break;

                case END:
                    if( !mMain.fngEnabled ) {
                        sender.sendMessage( mCommands.getDisabled() );
                        return;
                    }

                    if( args.length < 3 ) {
                        sender.sendMessage( mCommands.getError() + "/FNG Game End <Name>" );
                    }
                    else {
                        uGame.end(args[2]);
                    }
                    break;

                case REMOVE:
                    if( args.length < 3 ) {
                        sender.sendMessage( mCommands.getError() + "/FNG Game Remove <Name>" );
                    }
                    else if( !uGameList.getKeys().contains( args[2].toLowerCase() ) ) {
                        sender.sendMessage( mCommands.getError() + "This minigame does not exist." );
                    }
                    else {
                        mConfig.getGamesConfig().set("games." + args[2].toLowerCase(), null);
                        mConfig.saveConfigs();
                        sender.sendMessage( mCommands.getPrefix() + "Game successfully removed.");
                    }
                    break;

                case START:
                    if( !mMain.fngEnabled ) {
                        sender.sendMessage( mCommands.getDisabled() );
                        return;
                    }

                    if( args.length < 3 ) {
                        sender.sendMessage( mCommands.getError() + "/FNG Game Start <Name>" );
                    }
                    else {
                        uGame.start(args[2]);
                    }
                    break;

            }

        } catch (IllegalArgumentException e) {
            playersHelp(sender);
        }

    }

    public static void playersHelp(CommandSender sender) {
        sender.sendMessage(ChatColor.DARK_PURPLE + "Game Start" + ChatColor.WHITE + " > " + ChatColor.LIGHT_PURPLE + "Starts the specified game.");
        sender.sendMessage(ChatColor.DARK_PURPLE + "Game End"   + ChatColor.WHITE + " > " + ChatColor.LIGHT_PURPLE + "Forces everyone to quit.");
        sender.sendMessage(ChatColor.DARK_PURPLE + "Game Add"   + ChatColor.WHITE + " > " + ChatColor.LIGHT_PURPLE + "Adds a new game.");
        sender.sendMessage(ChatColor.DARK_PURPLE + "Game Remove"+ ChatColor.WHITE + " > " + ChatColor.LIGHT_PURPLE + "Removes an added game.");
    }

    private enum switchCommands {
        ADD, END, REMOVE, START
    }

    public static String getPermission() { return "fng.host"; }

}
