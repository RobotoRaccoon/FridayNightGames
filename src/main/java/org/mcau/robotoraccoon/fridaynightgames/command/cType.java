package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.mCommands;
import org.mcau.robotoraccoon.fridaynightgames.mConfig;
import org.mcau.robotoraccoon.fridaynightgames.utility.uTypeList;

public class cType {

    public static void type( CommandSender sender, String[] args ) {

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
                        sender.sendMessage( mCommands.getError() + "/FNG Type Add <Type> <Plugin>" );
                    }
                    else if( uTypeList.getTypes().contains( args[2].toLowerCase() ) ){
                        sender.sendMessage( mCommands.getError() + "This type already exists." );
                    }
                    else if( !uTypeList.getPluginKeys().contains( args[3].toLowerCase() ) ) {
                        sender.sendMessage( mCommands.getError() + "Plugin not defined. Available plugins: " + uTypeList.getPluginKeys() );
                    }
                    else {
                        mConfig.getConfig().set("types." + args[2].toLowerCase(), args[3].toLowerCase());
                        mConfig.saveConfigs();
                        sender.sendMessage( mCommands.getPrefix() + "Successfully added: " + ChatColor.RED + args[2].toLowerCase() + "|" + args[3].toLowerCase());
                    }
                    break;

                case LIST:
                        sender.sendMessage( mCommands.getPrefix() +  "Available types: " + ChatColor.RED + uTypeList.getTypes() );
                    break;

                case REMOVE:
                    if( args.length < 3 ) {
                        sender.sendMessage( mCommands.getError() + "/FNG Type Remove <Type>" );
                    }
                    else if( !uTypeList.getTypes().contains( args[2].toLowerCase() ) ){
                        sender.sendMessage( mCommands.getError() + "This type does not exist." );
                    }
                    else {
                        mConfig.getConfig().set("types." + args[2].toLowerCase(), null);
                        mConfig.saveConfigs();
                        sender.sendMessage( mCommands.getPrefix() + "Successfully removed: " + ChatColor.RED + args[2].toLowerCase());
                    }
                    break;

            }

        } catch (IllegalArgumentException e) {
            playersHelp(sender);
        }

    }

    public static void playersHelp(CommandSender sender) {
        sender.sendMessage(ChatColor.DARK_PURPLE + "Type List"  + ChatColor.WHITE + " > " + ChatColor.LIGHT_PURPLE + "List all available types.");
        sender.sendMessage(ChatColor.DARK_PURPLE + "Type Add"   + ChatColor.WHITE + " > " + ChatColor.LIGHT_PURPLE + "Adds a new type with join command.");
        sender.sendMessage(ChatColor.DARK_PURPLE + "Type Remove"+ ChatColor.WHITE + " > " + ChatColor.LIGHT_PURPLE + "Removes an added type.");
    }

    private enum switchCommands {
        ADD, LIST, REMOVE
    }

    public static String getPermission() { return "fng.operator"; }

}
