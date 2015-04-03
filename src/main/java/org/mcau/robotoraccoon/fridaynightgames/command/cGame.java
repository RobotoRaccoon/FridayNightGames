package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.Commands;
import org.mcau.robotoraccoon.fridaynightgames.Config;
import org.mcau.robotoraccoon.fridaynightgames.Main;
import org.mcau.robotoraccoon.fridaynightgames.utility.uBroadcast;
import org.mcau.robotoraccoon.fridaynightgames.utility.uGame;
import org.mcau.robotoraccoon.fridaynightgames.utility.uGameList;
import org.mcau.robotoraccoon.fridaynightgames.utility.uTypeList;

public class cGame {

    public static void run(CommandSender sender, String[] args) {

        if (!sender.hasPermission(getPermission())) {
            uBroadcast.colour(sender, Commands.getDenied());
            return;
        }

        if (args.length < 2) {
            playersHelp(sender);
        } else try {
            switch (switchCommands.valueOf(args[1].toUpperCase())) {

                case ADD:
                    if (args.length < 4) {
                        uBroadcast.colour(sender, Commands.getError() + "/FNG Game Add <Name> <Type>");
                    } else if (uGameList.gameExists(args[2].toLowerCase())) {
                        uBroadcast.colour(sender, Commands.getError() + "This minigame already exists.");
                    } else if (!uTypeList.getTypes().contains(args[3].toLowerCase())) {
                        uBroadcast.colour(sender, Commands.getError() + "Type not defined. Available types: " + uTypeList.getTypes());
                    } else {
                        Config.getGamesConfig().set("games." + args[2].toLowerCase() + ".name", args[2]);
                        Config.getGamesConfig().set("games." + args[2].toLowerCase() + ".type", args[3].toLowerCase());
                        Config.getGamesConfig().set("games." + args[2].toLowerCase() + ".plays", 0);
                        Config.saveConfigs();
                        uBroadcast.colour(sender, Commands.getPrefix() + "Added the minigame: &c" + args[2] + "|" + args[3].toLowerCase());
                    }
                    break;

                case COUNT:
                    if (!Main.fngEnabled) {
                        uBroadcast.colour(sender, Commands.getDisabled());
                        return;
                    }

                    uBroadcast.colour(sender, Commands.getPrefix() + "Amount of games started in this session: &c" + Main.fngPlayedGames.size());
                    break;

                case END:
                    if (!Main.fngEnabled) {
                        uBroadcast.colour(sender, Commands.getDisabled());
                        return;
                    }

                    if (args.length < 3) {
                        uBroadcast.colour(sender, Commands.getError() + "/FNG Game End <Name>");
                    } else if (!uGameList.gameExists(args[2].toLowerCase())) {
                        uBroadcast.colour(sender, Commands.getError() + "This minigame does not exist.");
                    } else {
                        uGame.end(args[2]);
                    }
                    break;

                case REMOVE:
                    if (args.length < 3) {
                        uBroadcast.colour(sender, Commands.getError() + "/FNG Game Remove <Name>");
                    } else if (!uGameList.gameExists(args[2].toLowerCase())) {
                        uBroadcast.colour(sender, Commands.getError() + "This minigame does not exist.");
                    } else {
                        Config.getGamesConfig().set("games." + args[2].toLowerCase(), null);
                        Config.saveConfigs();
                        uBroadcast.colour(sender, Commands.getPrefix() + "Game successfully removed.");
                    }
                    break;

                case START:
                    if (!Main.fngEnabled) {
                        uBroadcast.colour(sender, Commands.getDisabled());
                        return;
                    }

                    if (args.length < 3) {
                        uBroadcast.colour(sender, Commands.getError() + "/FNG Game Start <Name>");
                    } else if (!uGameList.gameExists(args[2].toLowerCase())) {
                        uBroadcast.colour(sender, Commands.getError() + "This minigame does not exist.");
                    } else {
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

    public static String getPermission() {
        return "fng.host";
    }

    private enum switchCommands {
        ADD, COUNT, END, REMOVE, START
    }

}
