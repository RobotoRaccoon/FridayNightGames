package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.Commands;
import org.mcau.robotoraccoon.fridaynightgames.Config;
import org.mcau.robotoraccoon.fridaynightgames.utility.uBroadcast;

import java.util.List;

public class cReload extends SubCommand {

    public String getPermission() {
        return "fng.operator";
    }

    public String getUsage() {
        return "reload";
    }

    public void run(CommandSender sender, List<String> args) {
        Config.loadConfigs();
        uBroadcast.colour(sender, Commands.getPrefix() + "&aSuccessfully reloaded the config file!");
    }

}
