package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.mCommands;
import org.mcau.robotoraccoon.fridaynightgames.mConfig;

public class cReload {

    public static void reload(CommandSender sender, String[] args) {

        if( !sender.hasPermission( getPermission() ) ) {
            sender.sendMessage(mCommands.getDenied());
            return;
        }

        mConfig.loadConfigs();
        sender.sendMessage(mCommands.getPrefix() + ChatColor.GREEN + "Successfully reloaded the config file!");

    }

    public static String getPermission() { return "fng.operator"; }

}
