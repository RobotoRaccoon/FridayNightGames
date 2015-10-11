package org.mcau.robotoraccoon.fridaynightgames.command.subCommands;

import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.Config;
import org.mcau.robotoraccoon.fridaynightgames.command.SubCommand;
import org.mcau.robotoraccoon.fridaynightgames.utility.MessageUtil;
import org.mcau.robotoraccoon.fridaynightgames.utility.TypeUtil;

import java.util.List;

public class TypeCommand extends SubCommand {

    public String getPermission() {
        return "fng.operator";
    }

    public String getUsage() {
        return "type <list|add|remove>";
    }

    public String getDescription() {
        return "&5Type &f> &dList, Add, and Remove types.";
    }

    public void run(CommandSender sender, List<String> args) {

        if (args.size() < 1) {
            playersHelp(sender);
        } else try {
            switch (switchCommands.valueOf(args.get(0).toUpperCase())) {

                case ADD:
                    if (args.size() < 3) {
                        MessageUtil.colour(sender, MessageUtil.getError() + "/FNG Type Add <Type> <Plugin>");
                    } else if (TypeUtil.getTypes().contains(args.get(1).toLowerCase())) {
                        MessageUtil.colour(sender, MessageUtil.getError() + "This type already exists.");
                    } else if (!TypeUtil.getPluginKeys().contains(args.get(2).toLowerCase())) {
                        MessageUtil.colour(sender, MessageUtil.getError() + "Plugin not defined. Available plugins: " + TypeUtil.getPluginKeys());
                    } else {
                        Config.getConfig().set("types." + args.get(1).toLowerCase(), args.get(2).toLowerCase());
                        Config.saveConfigs();
                        MessageUtil.colour(sender, MessageUtil.getPrefix() + "Successfully added: &c" + args.get(1).toLowerCase() + "|" + args.get(2).toLowerCase());
                    }
                    break;

                case LIST:
                    MessageUtil.colour(sender, MessageUtil.getPrefix() + "Available types: &c" + TypeUtil.getTypes());
                    break;

                case REMOVE:
                    if (args.size() < 2) {
                        MessageUtil.colour(sender, MessageUtil.getError() + "/FNG Type Remove <Type>");
                    } else if (!TypeUtil.getTypes().contains(args.get(1).toLowerCase())) {
                        MessageUtil.colour(sender, MessageUtil.getError() + "This type does not exist.");
                    } else {
                        Config.getConfig().set("types." + args.get(1).toLowerCase(), null);
                        Config.saveConfigs();
                        MessageUtil.colour(sender, MessageUtil.getPrefix() + "Successfully removed: &c" + args.get(1).toLowerCase());
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
    }

    private enum switchCommands {
        ADD, LIST, REMOVE
    }

}
