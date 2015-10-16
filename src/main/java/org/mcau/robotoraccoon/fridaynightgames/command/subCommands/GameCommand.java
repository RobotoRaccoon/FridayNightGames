package org.mcau.robotoraccoon.fridaynightgames.command.subCommands;

import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.Config;
import org.mcau.robotoraccoon.fridaynightgames.Main;
import org.mcau.robotoraccoon.fridaynightgames.command.SubCommand;
import org.mcau.robotoraccoon.fridaynightgames.games.MinigameMap;
import org.mcau.robotoraccoon.fridaynightgames.games.MinigameType;
import org.mcau.robotoraccoon.fridaynightgames.utility.MessageUtil;
import org.mcau.robotoraccoon.fridaynightgames.utility.GameUtil;

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
            String key;
            MinigameMap map;

            switch (switchCommands.valueOf(args.get(0).toUpperCase())) {

                case ADD:
                    if (args.size() < 3) {
                        MessageUtil.colour(sender, MessageUtil.getError() + "/FNG Game Add <Name> <Type>");
                        return;
                    }

                    key = args.get(1).toLowerCase();
                    map = Main.getMinigames().get(key);
                    MinigameType type = Main.getGameTypes().get(args.get(2).toLowerCase());

                    if (map != null) {
                        MessageUtil.colour(sender, MessageUtil.getError() + "This minigame already exists.");
                    } else if (type == null) {
                        MessageUtil.colour(sender, MessageUtil.getError() + "Type not defined. Available types: " + Main.getGameTypes().values());
                    } else {
                        map = new MinigameMap(args.get(1), type);
                        Main.getMinigames().put(key, map);
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

                case ENABLED:
                    if (args.size() < 2) {
                        MessageUtil.colour(sender, MessageUtil.getError() + "/FNG Game Enabled <Name> [T|F]");
                        return;
                    }

                    key = args.get(1).toLowerCase();
                    map = Main.getMinigames().get(key);
                    if (map == null) {
                        MessageUtil.colour(sender, MessageUtil.getError() + "This minigame does not exist.");
                        return;
                    }

                    if (args.size() < 3) {

                        if (map.getEnabled())
                            MessageUtil.colour(sender, MessageUtil.getPrefix() + map.getName() + " is &aEnabled");
                        else
                            MessageUtil.colour(sender, MessageUtil.getPrefix() + map.getName() + " is &cDisabled");

                    } else if (args.get(2).startsWith("T") || args.get(2).startsWith("t")) {
                        map.setEnabled(true);
                        MessageUtil.colour(sender, MessageUtil.getPrefix() + map.getName() + " has been &aEnabled");
                    } else {
                        map.setEnabled(false);
                        MessageUtil.colour(sender, MessageUtil.getPrefix() + map.getName() + " has been &cDisabled");
                    }
                    break;

                case END:
                    if (!Main.getFngEnabled()) {
                        MessageUtil.colour(sender, MessageUtil.getDisabled());
                        return;
                    }

                    if (args.size() < 2) {
                        MessageUtil.colour(sender, MessageUtil.getError() + "/FNG Game End <Name>");
                        return;
                    }

                    key = args.get(1).toLowerCase();
                    map = Main.getMinigames().get(key);
                    if (map == null) {
                        MessageUtil.colour(sender, MessageUtil.getError() + "This minigame does not exist.");
                    } else {
                        GameUtil.end(map);
                    }
                    break;

                case REMOVE:
                    if (args.size() < 2) {
                        MessageUtil.colour(sender, MessageUtil.getError() + "/FNG Game Remove <Name>");
                        return;
                    }

                    key = args.get(1).toLowerCase();
                    map = Main.getMinigames().get(key);
                    if (map == null) {
                        MessageUtil.colour(sender, MessageUtil.getError() + "This minigame does not exist.");
                    } else {
                        Config.getGamesConfig().set("games." + key, null);
                        Main.getMinigames().remove(key);
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
                        return;
                    }

                    key = args.get(1).toLowerCase();
                    map = Main.getMinigames().get(key);
                    if (args.get(1).toLowerCase().equals("results")) {
                        GameUtil.startResults();
                    } else if (map == null) {
                        MessageUtil.colour(sender, MessageUtil.getError() + "This minigame does not exist.");
                    } else if (!map.getEnabled()) {
                        MessageUtil.colour(sender, MessageUtil.getError() + "This minigame is currently disabled.");
                    } else {
                        GameUtil.start(map);
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
        MessageUtil.colour(sender, "&5Game Enabled &f> &dToggle if a game can be started.");
        MessageUtil.colour(sender, "&5Game End &f> &dForces everyone to quit.");
        MessageUtil.colour(sender, "&5Game Remove &f> &dRemoves an added game.");
        MessageUtil.colour(sender, "&5Game Start &f> &dStarts the specified game.");
    }

    private enum switchCommands {
        ADD, COUNT, ENABLED, END, REMOVE, START
    }

}
