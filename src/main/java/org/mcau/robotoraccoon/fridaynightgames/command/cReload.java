package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.Config;
import org.mcau.robotoraccoon.fridaynightgames.utility.uMessage;

import java.util.List;

public class cReload extends SubCommand {

    public String getPermission() {
        return "fng.operator";
    }

    public String getUsage() {
        return "reload";
    }

    public String getDescription() {
        return "&5Reload &f> &dReload the config.";
    }

    public void run(CommandSender sender, List<String> args) {
        Config.loadConfigs();
        uMessage.colour(sender, uMessage.getPrefix() + "&aSuccessfully reloaded the config file!");
    }

}
