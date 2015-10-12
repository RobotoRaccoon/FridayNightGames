package org.mcau.robotoraccoon.fridaynightgames.command.subCommands;

import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.Config;
import org.mcau.robotoraccoon.fridaynightgames.command.SubCommand;
import org.mcau.robotoraccoon.fridaynightgames.utility.GameUtil;
import org.mcau.robotoraccoon.fridaynightgames.utility.MessageUtil;

import java.util.List;

public class ReloadCommand extends SubCommand {

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
        GameUtil.saveMinigamesToConfig();
        Config.loadConfigs();
        GameUtil.loadMinigamesFromConfig();
        MessageUtil.colour(sender, MessageUtil.getPrefix() + "&aSuccessfully reloaded the config file!");
    }

}
