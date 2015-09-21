package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.Commands;
import org.mcau.robotoraccoon.fridaynightgames.Config;
import org.mcau.robotoraccoon.fridaynightgames.Main;
import org.mcau.robotoraccoon.fridaynightgames.utility.uBroadcast;
import org.mcau.robotoraccoon.fridaynightgames.utility.uGame;
import org.mcau.robotoraccoon.fridaynightgames.utility.uGameList;
import org.mcau.robotoraccoon.fridaynightgames.utility.uTypeList;

import java.util.List;

public class cGame extends SubCommand {

    public String getPermission() {
        return "fng.host";
    }

    public String getUsage() {
        return "game <add|count|end|remove|start>";
    }

    public void run(CommandSender sender, List<String> args) {

        if (args.size() < 1) {
            playersHelp(sender);
        } else try {
            switch (switchCommands.valueOf(args.get(0).toUpperCase())) {

                case ADD:
                    if (args.size() < 3) {
                        uBroadcast.colour(sender, Commands.getError() + "/FNG Game Add <Name> <Type>");
                    } else if (uGameList.gameExists(args.get(1).toLowerCase())) {
                        uBroadcast.colour(sender, Commands.getError() + "This minigame already exists.");
                    } else if (!uTypeList.getTypes().contains(args.get(2).toLowerCase())) {
                        uBroadcast.colour(sender, Commands.getError() + "Type not defined. Available types: " + uTypeList.getTypes());
                    } else {
                        Config.getGamesConfig().set("games." + args.get(1).toLowerCase() + ".name", args.get(1));
                        Config.getGamesConfig().set("games." + args.get(1).toLowerCase() + ".type", args.get(2).toLowerCase());
                        Config.getGamesConfig().set("games." + args.get(1).toLowerCase() + ".plays", 0);
                        Config.saveConfigs();
                        uBroadcast.colour(sender, Commands.getPrefix() + "Added the minigame: &c" + args.get(1) + "|" + args.get(2).toLowerCase());
                    }
                    break;

                case COUNT:
                    if (!Main.getFngEnabled()) {
                        uBroadcast.colour(sender, Commands.getDisabled());
                        return;
                    }

                    uBroadcast.colour(sender, Commands.getPrefix() + "Amount of games started in this session: &c" + Main.getPlayedGames().size());
                    break;

                case END:
                    if (!Main.getFngEnabled()) {
                        uBroadcast.colour(sender, Commands.getDisabled());
                        return;
                    }

                    if (args.size() < 2) {
                        uBroadcast.colour(sender, Commands.getError() + "/FNG Game End <Name>");
                    } else if (!uGameList.gameExists(args.get(1).toLowerCase())) {
                        uBroadcast.colour(sender, Commands.getError() + "This minigame does not exist.");
                    } else {
                        uGame.end(args.get(1));
                    }
                    break;

                case REMOVE:
                    if (args.size() < 2) {
                        uBroadcast.colour(sender, Commands.getError() + "/FNG Game Remove <Name>");
                    } else if (!uGameList.gameExists(args.get(1).toLowerCase())) {
                        uBroadcast.colour(sender, Commands.getError() + "This minigame does not exist.");
                    } else {
                        Config.getGamesConfig().set("games." + args.get(1).toLowerCase(), null);
                        Config.saveConfigs();
                        uBroadcast.colour(sender, Commands.getPrefix() + "Game successfully removed.");
                    }
                    break;

                case START:
                    if (!Main.getFngEnabled()) {
                        uBroadcast.colour(sender, Commands.getDisabled());
                        return;
                    }

                    if (args.size() < 2) {
                        uBroadcast.colour(sender, Commands.getError() + "/FNG Game Start <Name|Results>");
                    } else if (args.get(1).toLowerCase().equals("results")) {
                        uGame.startResults();
                    } else if (!uGameList.gameExists(args.get(1).toLowerCase())) {
                        uBroadcast.colour(sender, Commands.getError() + "This minigame does not exist.");
                    } else {
                        uGame.start(args.get(1));
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

}
