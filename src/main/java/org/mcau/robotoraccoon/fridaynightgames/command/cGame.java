package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.mCommands;
import org.mcau.robotoraccoon.fridaynightgames.mConfig;
import org.mcau.robotoraccoon.fridaynightgames.mMain;
import org.mcau.robotoraccoon.fridaynightgames.utility.*;

public class cGame {

    public static void game( CommandSender sender, String[] args ) {

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
                        uBroadcast.colour(sender, mCommands.getError() + "/FNG Game Add <Name> <Type>");
                    }
                    else if( uGameList.getKeys().contains( args[2].toLowerCase() ) ) {
                        uBroadcast.colour(sender, mCommands.getError() + "This minigame already exists.");
                    }
                    else if( !uTypeList.getTypes().contains( args[3].toLowerCase() ) ) {
                        uBroadcast.colour(sender, mCommands.getError() + "Type not defined. Available types: " + uTypeList.getTypes());
                    }
                    else {
                        mConfig.getGamesConfig().set("games." + args[2].toLowerCase() + ".name", args[2]);
                        mConfig.getGamesConfig().set("games." + args[2].toLowerCase() + ".type", args[3].toLowerCase());
                        mConfig.saveConfigs();
                        uBroadcast.colour(sender, mCommands.getPrefix() + "Added the minigame: &c" + args[2] + "|" + args[3].toLowerCase());
                    }
                    break;

                case COUNT:
                    if( !mMain.fngEnabled ) {
                        uBroadcast.colour(sender, mCommands.getDisabled());
                        return;
                    }

                    uBroadcast.colour(sender, mCommands.getPrefix() + "Amount of games started in this session: &c" + mMain.fngPlayedGames.size());
                    break;

                case END:
                    if( !mMain.fngEnabled ) {
                        uBroadcast.colour(sender, mCommands.getDisabled());
                        return;
                    }

                    if( args.length < 3 ) {
                        uBroadcast.colour(sender, mCommands.getError() + "/FNG Game End <Name>");
                    }
                    else {
                        uGame.end(args[2]);
                    }
                    break;

                case REMOVE:
                    if( args.length < 3 ) {
                        uBroadcast.colour(sender, mCommands.getError() + "/FNG Game Remove <Name>");
                    }
                    else if( !uGameList.getKeys().contains( args[2].toLowerCase() ) ) {
                        uBroadcast.colour(sender, mCommands.getError() + "This minigame does not exist.");
                    }
                    else {
                        mConfig.getGamesConfig().set("games." + args[2].toLowerCase(), null);
                        mConfig.saveConfigs();
                        uBroadcast.colour(sender, mCommands.getPrefix() + "Game successfully removed.");
                    }
                    break;

                case START:
                    if( !mMain.fngEnabled ) {
                        uBroadcast.colour(sender, mCommands.getDisabled());
                        return;
                    }

                    if( args.length < 3 ) {
                        uBroadcast.colour(sender, mCommands.getError() + "/FNG Game Start <Name>");
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
        uBroadcast.colour(sender, " &7===== &5Options &7=====");
        uBroadcast.colour(sender, "&5Game Add &f> &dAdds a new game.");
        uBroadcast.colour(sender, "&5Game Count &f> &dView how many games have been played.");
        uBroadcast.colour(sender, "&5Game End &f> &dForces everyone to quit.");
        uBroadcast.colour(sender, "&5Game Remove &f> &dRemoves an added game.");
        uBroadcast.colour(sender, "&5Game Start &f> &dStarts the specified game.");
    }

    private enum switchCommands {
        ADD, COUNT, END, REMOVE, START
    }

    public static String getPermission() { return "fng.host"; }

}
