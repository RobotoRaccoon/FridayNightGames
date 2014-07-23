package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.mCommands;
import org.mcau.robotoraccoon.fridaynightgames.mConfig;
import org.mcau.robotoraccoon.fridaynightgames.utility.uBroadcast;
import org.mcau.robotoraccoon.fridaynightgames.utility.uTypeList;

public class cType {

    public static void type( CommandSender sender, String[] args ) {

        if( !sender.hasPermission( getPermission() ) ) {
            uBroadcast.colour(sender, mCommands.getDenied());
            return;
        }

        if( args.length < 2 ) {
            playersHelp(sender);
        }
        else try {
            switch( switchCommands.valueOf( args[1].toUpperCase() ) ) {

                case ADD:
                    if( args.length < 4 ) {
                        uBroadcast.colour(sender, mCommands.getError() + "/FNG Type Add <Type> <Plugin>");
                    }
                    else if( uTypeList.getTypes().contains( args[2].toLowerCase() ) ){
                        uBroadcast.colour(sender, mCommands.getError() + "This type already exists.");
                    }
                    else if( !uTypeList.getPluginKeys().contains( args[3].toLowerCase() ) ) {
                        uBroadcast.colour(sender, mCommands.getError() + "Plugin not defined. Available plugins: " + uTypeList.getPluginKeys());
                    }
                    else {
                        mConfig.getConfig().set("types." + args[2].toLowerCase(), args[3].toLowerCase());
                        mConfig.saveConfigs();
                        uBroadcast.colour(sender, mCommands.getPrefix() + "Successfully added: &c" + args[2].toLowerCase() + "|" + args[3].toLowerCase());
                    }
                    break;

                case LIST:
                    uBroadcast.colour(sender, mCommands.getPrefix() + "Available types: &c" + uTypeList.getTypes());
                    break;

                case REMOVE:
                    if( args.length < 3 ) {
                        uBroadcast.colour(sender, mCommands.getError() + "/FNG Type Remove <Type>");
                    }
                    else if( !uTypeList.getTypes().contains( args[2].toLowerCase() ) ){
                        uBroadcast.colour(sender, mCommands.getError() + "This type does not exist.");
                    }
                    else {
                        mConfig.getConfig().set("types." + args[2].toLowerCase(), null);
                        mConfig.saveConfigs();
                        uBroadcast.colour(sender, mCommands.getPrefix() + "Successfully removed: &c" + args[2].toLowerCase());
                    }
                    break;

            }

        } catch (IllegalArgumentException e) {
            playersHelp(sender);
        }

    }

    public static void playersHelp(CommandSender sender) {
        uBroadcast.colour(sender, "&5Type List &f> &dList all available types.");
        uBroadcast.colour(sender, "&5Type Add &f> &dAdds a new type with join command.");
        uBroadcast.colour(sender, "&5Type Remove &f> &dRemoves an added type.");
    }

    private enum switchCommands {
        ADD, LIST, REMOVE
    }

    public static String getPermission() { return "fng.operator"; }

}
