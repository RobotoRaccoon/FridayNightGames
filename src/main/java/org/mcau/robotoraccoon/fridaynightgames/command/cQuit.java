package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mcau.robotoraccoon.fridaynightgames.Main;
import org.mcau.robotoraccoon.fridaynightgames.utility.uMessage;

import java.util.List;

public class cQuit extends SubCommand {

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
            uMessage.colour(sender, uMessage.getDisabled());
            return;
        }

        Player player = (Player) sender;
        if (Main.getPlayerList().containsKey(player.getUniqueId())) {
            Main.getPlayerList().remove(player.getUniqueId());
            uMessage.colour(sender, uMessage.getPrefix() + "You have quit the games! Thanks for playing!");
        } else {
            uMessage.colour(sender, uMessage.getError() + "You weren't in the games anyway.");
        }

    }

}
