package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mcau.robotoraccoon.fridaynightgames.Main;
import org.mcau.robotoraccoon.fridaynightgames.utility.uMessage;

import java.util.List;

public class cJoin extends SubCommand {

    public boolean isConsoleAllowed() {
        return false;
    }

    public String getPermission() {
        return "fng.player";
    }

    public String getUsage() {
        return "join";
    }

    public String getDescription() {
        return "&5Join &f> &dJoin in on the fun.";
    }

    public void run(CommandSender sender, List<String> args) {

        if (!Main.getFngEnabled()) {
            uMessage.colour(sender, uMessage.getDisabled());
            return;
        }

        Player player = (Player) sender;
        if (Main.getPlayerList().containsKey(player.getUniqueId())) {
            uMessage.colour(sender, uMessage.getError() + "You have already joined the games.");
        } else {
            Main.getPlayerList().put(player.getUniqueId(), player);
            uMessage.colour(sender, uMessage.getPrefix() + "You have joined the games! You'll join with the next available lobby!");
        }

    }

}
