package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.Commands;
import org.mcau.robotoraccoon.fridaynightgames.Config;
import org.mcau.robotoraccoon.fridaynightgames.utility.uBroadcast;

public class cReload {

    public static void run(CommandSender sender, String[] args) {

        if (!sender.hasPermission(getPermission())) {
            uBroadcast.colour(sender, Commands.getDenied());
            return;
        }

        Config.loadConfigs();
        uBroadcast.colour(sender, Commands.getPrefix() + "&aSuccessfully reloaded the config file!");

    }

    public static String getPermission() {
        return "fng.operator";
    }

}
