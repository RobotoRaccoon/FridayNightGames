package org.mcau.robotoraccoon.fridaynightgames.command.subCommands;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.Config;
import org.mcau.robotoraccoon.fridaynightgames.Main;
import org.mcau.robotoraccoon.fridaynightgames.command.SubCommand;
import org.mcau.robotoraccoon.fridaynightgames.games.MinigameType;
import org.mcau.robotoraccoon.fridaynightgames.utility.MessageUtil;
import org.mcau.robotoraccoon.fridaynightgames.utility.TypeUtil;

import java.util.List;

public class TypeCommand extends SubCommand {

    public String getPermission() {
        return "fng.operator";
    }

    public String getUsage() {
        return "type <list|add|remove|setname>";
    }

    public String getDescription() {
        return "&5Type &f> &dList, Add, and Remove types.";
    }

    public void run(CommandSender sender, List<String> args) {

        if (args.size() < 1) {
            playersHelp(sender);
        } else try {
            String typeKey;
            MinigameType type;

            switch (switchCommands.valueOf(args.get(0).toUpperCase())) {

                case ADD:
                    if (args.size() < 3) {
                        MessageUtil.colour(sender, MessageUtil.getError() + "/FNG Type Add <Type> <Plugin>");
                        return;
                    }

                    typeKey = args.get(1).toLowerCase();
                    type = Main.getGameTypes().get(typeKey);
                    String plugin = args.get(2).toLowerCase();

                    if (type != null) {
                        MessageUtil.colour(sender, MessageUtil.getError() + "This type already exists.");
                    } else if (!TypeUtil.getPluginKeys().contains(plugin)) {
                        MessageUtil.colour(sender, MessageUtil.getError() + "Plugin not defined. Available plugins: " + TypeUtil.getPluginKeys());
                    } else {
                        type = new MinigameType(typeKey, plugin, args.get(1));
                        Main.getGameTypes().put(typeKey, type);
                        MessageUtil.colour(sender, MessageUtil.getPrefix() +
                                "Successfully added game type: &c" + args.get(1).toLowerCase() + "|" + args.get(2).toLowerCase());
                    }
                    break;

                case LIST:
                    if (args.size() < 2) {
                        MessageUtil.colour(sender, MessageUtil.getPrefix() + "Available types: &c" +
                                StringUtils.join(Main.getGameTypes().values(), ", "));
                    } else {
                        typeKey = args.get(1).toLowerCase();
                        type = Main.getGameTypes().get(typeKey);

                        if (type == null) {
                            MessageUtil.colour(sender, MessageUtil.getError() + "This type does not exist.");
                            return;
                        }

                        MessageUtil.colour(sender, "&5Name: &e" + type.getName());
                        MessageUtil.colour(sender, "&5Short-hand: &e" + type.getKey());
                        MessageUtil.colour(sender, "&5Plugin: &e" + type.getPlugin());
                    }
                    break;

                case REMOVE:
                    if (args.size() < 2) {
                        MessageUtil.colour(sender, MessageUtil.getError() + "/FNG Type Remove <Type>");
                        return;
                    }

                    typeKey = args.get(1).toLowerCase();
                    type = Main.getGameTypes().get(typeKey);

                    if (type == null || !Main.getGameTypes().containsValue(type)) {
                        MessageUtil.colour(sender, MessageUtil.getError() + "This type does not exist.");
                    } else {
                        Config.getConfig().set("types." + type.getKey(), null);
                        Main.getGameTypes().remove(typeKey);
                        MessageUtil.colour(sender, MessageUtil.getPrefix() +
                                "Successfully removed game type: &c" + args.get(1).toLowerCase());
                    }
                    break;

                case SETNAME:
                    if (args.size() < 2) {
                        MessageUtil.colour(sender, MessageUtil.getError() + "/FNG Type SetName <Type> <Name...>");
                        return;
                    }

                    typeKey = args.get(1).toLowerCase();
                    type = Main.getGameTypes().get(typeKey);

                    if (type == null || !Main.getGameTypes().containsValue(type)) {
                        MessageUtil.colour(sender, MessageUtil.getError() + "This type does not exist.");
                    } else {
                        args.remove(0);
                        args.remove(0);
                        String newName = StringUtils.join(args, " ");
                        type.setName(newName);
                        MessageUtil.colour(sender, MessageUtil.getPrefix() +
                                "Successfully set the display name to: &c" + newName);
                    }
                    break;

            }

        } catch (IllegalArgumentException e) {
            playersHelp(sender);
        }

    }

    public static void playersHelp(CommandSender sender) {
        MessageUtil.colour(sender, "&5Type List &f> &dList all available types.");
        MessageUtil.colour(sender, "&5Type Add &f> &dAdds a new type with join command.");
        MessageUtil.colour(sender, "&5Type Remove &f> &dRemoves an added type.");
        MessageUtil.colour(sender, "&5Type SetName &f> &dSets the display name for a type.");
    }

    private enum switchCommands {
        ADD, LIST, REMOVE, SETNAME
    }

}
