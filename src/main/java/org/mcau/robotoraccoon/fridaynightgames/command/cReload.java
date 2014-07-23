package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.mCommands;
import org.mcau.robotoraccoon.fridaynightgames.mConfig;
import org.mcau.robotoraccoon.fridaynightgames.utility.uBroadcast;

public class cReload {

    public static void reload(CommandSender sender, String[] args) {

        if( !sender.hasPermission( getPermission() ) ) {
            uBroadcast.colour(sender, mCommands.getDenied());
            return;
        }

        mConfig.loadConfigs();
        uBroadcast.colour(sender, mCommands.getPrefix() + "&aSuccessfully reloaded the config file!");

    }

    public static String getPermission() { return "fng.operator"; }

}
