package org.mcau.robotoraccoon.fridaynightgames.command.subCommands;

import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.Config;
import org.mcau.robotoraccoon.fridaynightgames.Main;
import org.mcau.robotoraccoon.fridaynightgames.command.SubCommand;
import org.mcau.robotoraccoon.fridaynightgames.utility.MessageUtil;
import org.mcau.robotoraccoon.fridaynightgames.utility.GameUtil;
import org.mcau.robotoraccoon.fridaynightgames.utility.GameListUtil;
import org.mcau.robotoraccoon.fridaynightgames.utility.TypeUtil;

import java.util.List;

public class GameCommand extends SubCommand {

    public String getPermission() {
        return "fng.host";
    }

    public String getUsage() {
        return "game <add|count|end|remove|start>";
    }

    public String getDescription() {
        return "&5Game &f> &dStart, End, Add, and Remove games.";
    }

    public void run(CommandSender sender, List<String> args) {

        if (args.size() < 1) {
            playersHelp(sender);
        } else try {
            switch (switchCommands.valueOf(args.get(0).toUpperCase())) {

                case ADD:
                    if (args.size() < 3) {
                        MessageUtil.colour(sender, MessageUtil.getError() + "/FNG Game Add <Name> <Type>");
                    } else if (GameListUtil.gameExists(args.get(1).toLowerCase())) {
                        MessageUtil.colour(sender, MessageUtil.getError() + "This minigame already exists.");
                    } else if (!TypeUtil.getTypes().contains(args.get(2).toLowerCase())) {
                        MessageUtil.colour(sender, MessageUtil.getError() + "Type not defined. Available types: " + TypeUtil.getTypes());
                    } else {
                        Config.getGamesConfig().set("games." + args.get(1).toLowerCase() + ".name", args.get(1));
                        Config.getGamesConfig().set("games." + args.get(1).toLowerCase() + ".type", args.get(2).toLowerCase());
                        Config.getGamesConfig().set("games." + args.get(1).toLowerCase() + ".plays", 0);
                        Config.saveConfigs();
                        MessageUtil.colour(sender, MessageUtil.getPrefix() + "Added the minigame: &c" + args.get(1) + "|" + args.get(2).toLowerCase());
                    }
                    break;

                case COUNT:
                    if (!Main.getFngEnabled()) {
                        MessageUtil.colour(sender, MessageUtil.getDisabled());
                        return;
                    }

                    MessageUtil.colour(sender, MessageUtil.getPrefix() + "Amount of games started in this session: &c" + Main.getPlayedGames().size());
                    break;

                case END:
                    if (!Main.getFngEnabled()) {
                        MessageUtil.colour(sender, MessageUtil.getDisabled());
                        return;
                    }

                    if (args.size() < 2) {
                        MessageUtil.colour(sender, MessageUtil.getError() + "/FNG Game End <Name>");
                    } else if (!GameListUtil.gameExists(args.get(1).toLowerCase())) {
                        MessageUtil.colour(sender, MessageUtil.getError() + "This minigame does not exist.");
                    } else {
                        GameUtil.end(args.get(1));
                    }
                    break;

                case REMOVE:
                    if (args.size() < 2) {
                        MessageUtil.colour(sender, MessageUtil.getError() + "/FNG Game Remove <Name>");
                    } else if (!GameListUtil.gameExists(args.get(1).toLowerCase())) {
                        MessageUtil.colour(sender, MessageUtil.getError() + "This minigame does not exist.");
                    } else {
                        Config.getGamesConfig().set("games." + args.get(1).toLowerCase(), null);
                        Config.saveConfigs();
                        MessageUtil.colour(sender, MessageUtil.getPrefix() + "Game successfully removed.");
                    }
                    break;

                case START:
                    if (!Main.getFngEnabled()) {
                        MessageUtil.colour(sender, MessageUtil.getDisabled());
                        return;
                    }

                    if (args.size() < 2) {
                        MessageUtil.colour(sender, MessageUtil.getError() + "/FNG Game Start <Name|Results>");
                    } else if (args.get(1).toLowerCase().equals("results")) {
                        GameUtil.startResults();
                    } else if (!GameListUtil.gameExists(args.get(1).toLowerCase())) {
                        MessageUtil.colour(sender, MessageUtil.getError() + "This minigame does not exist.");
                    } else {
                        GameUtil.start(args.get(1));
                    }
                    break;

            }

        } catch (IllegalArgumentException e) {
            playersHelp(sender);
        }

    }

    public static void playersHelp(CommandSender sender) {
        MessageUtil.colour(sender, " &7===== &5Options &7=====");
        MessageUtil.colour(sender, "&5Game Add &f> &dAdds a new game.");
        MessageUtil.colour(sender, "&5Game Count &f> &dView how many games have been played.");
        MessageUtil.colour(sender, "&5Game End &f> &dForces everyone to quit.");
        MessageUtil.colour(sender, "&5Game Remove &f> &dRemoves an added game.");
        MessageUtil.colour(sender, "&5Game Start &f> &dStarts the specified game.");
    }

    private enum switchCommands {
        ADD, COUNT, END, REMOVE, START
    }

}
