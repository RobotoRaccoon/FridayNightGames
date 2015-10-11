package org.mcau.robotoraccoon.fridaynightgames.command.subCommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mcau.robotoraccoon.fridaynightgames.Main;
import org.mcau.robotoraccoon.fridaynightgames.command.SubCommand;
import org.mcau.robotoraccoon.fridaynightgames.utility.MessageUtil;

import java.util.List;

public class QuitCommand extends SubCommand {

    public boolean isConsoleAllowed() {
        return false;
    }

    public String getPermission() {
        return "fng.player";
    }

    public String getUsage() {
        return "quit";
    }

    public String getDescription() {
        return "&5Quit &f> &dQuit having fun.";
    }

    public void run(CommandSender sender, List<String> args) {

        if (!Main.getFngEnabled()) {
            MessageUtil.colour(sender, MessageUtil.getDisabled());
            return;
        }

        Player player = (Player) sender;
        if (Main.getPlayerList().containsKey(player.getUniqueId())) {
            Main.getPlayerList().remove(player.getUniqueId());
            MessageUtil.colour(sender, MessageUtil.getPrefix() + "You have quit the games! Thanks for playing!");
        } else {
            MessageUtil.colour(sender, MessageUtil.getError() + "You weren't in the games anyway.");
        }

    }

}
