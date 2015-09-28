package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.Config;
import org.mcau.robotoraccoon.fridaynightgames.utility.uMessage;
import org.mcau.robotoraccoon.fridaynightgames.utility.uTypeList;

import java.util.List;

public class cType extends SubCommand {

    public boolean isConsoleAllowed() {
        return true;
    }

    public String getPermission() {
        return "fng.operator";
    }

    public String getUsage() {
        return "type <list|add|remove>";
    }

    public void run(CommandSender sender, List<String> args) {

        if (args.size() < 1) {
            playersHelp(sender);
        } else try {
            switch (switchCommands.valueOf(args.get(0).toUpperCase())) {

                case ADD:
                    if (args.size() < 3) {
                        uMessage.colour(sender, uMessage.getError() + "/FNG Type Add <Type> <Plugin>");
                    } else if (uTypeList.getTypes().contains(args.get(1).toLowerCase())) {
                        uMessage.colour(sender, uMessage.getError() + "This type already exists.");
                    } else if (!uTypeList.getPluginKeys().contains(args.get(2).toLowerCase())) {
                        uMessage.colour(sender, uMessage.getError() + "Plugin not defined. Available plugins: " + uTypeList.getPluginKeys());
                    } else {
                        Config.getConfig().set("types." + args.get(1).toLowerCase(), args.get(2).toLowerCase());
                        Config.saveConfigs();
                        uMessage.colour(sender, uMessage.getPrefix() + "Successfully added: &c" + args.get(1).toLowerCase() + "|" + args.get(2).toLowerCase());
                    }
                    break;

                case LIST:
                    uMessage.colour(sender, uMessage.getPrefix() + "Available types: &c" + uTypeList.getTypes());
                    break;

                case REMOVE:
                    if (args.size() < 2) {
                        uMessage.colour(sender, uMessage.getError() + "/FNG Type Remove <Type>");
                    } else if (!uTypeList.getTypes().contains(args.get(1).toLowerCase())) {
                        uMessage.colour(sender, uMessage.getError() + "This type does not exist.");
                    } else {
                        Config.getConfig().set("types." + args.get(1).toLowerCase(), null);
                        Config.saveConfigs();
                        uMessage.colour(sender, uMessage.getPrefix() + "Successfully removed: &c" + args.get(1).toLowerCase());
                    }
                    break;

            }

        } catch (IllegalArgumentException e) {
            playersHelp(sender);
        }

    }

    public static void playersHelp(CommandSender sender) {
        uMessage.colour(sender, "&5Type List &f> &dList all available types.");
        uMessage.colour(sender, "&5Type Add &f> &dAdds a new type with join command.");
        uMessage.colour(sender, "&5Type Remove &f> &dRemoves an added type.");
    }

    private enum switchCommands {
        ADD, LIST, REMOVE
    }

}
