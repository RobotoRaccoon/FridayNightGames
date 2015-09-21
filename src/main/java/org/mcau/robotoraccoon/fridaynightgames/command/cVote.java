package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mcau.robotoraccoon.fridaynightgames.Commands;
import org.mcau.robotoraccoon.fridaynightgames.Main;
import org.mcau.robotoraccoon.fridaynightgames.utility.uBroadcast;
import org.mcau.robotoraccoon.fridaynightgames.utility.uVoting;

import java.util.List;

public class cVote extends SubCommand {

    public String getPermission() {
        return "fng.player";
    }

    public String getUsage() {
        return "vote [number]";
    }

    public void run(CommandSender sender, List<String> args) {

        if (!Main.getFngEnabled()) {
            uBroadcast.colour(sender, Commands.getDisabled());
            return;
        }

        // No number supplied, display the candidates.
        if (args.size() < 1) {
            uVoting.printList(sender);
            return;
        }

        // Only players from here onwards.
        if (!(sender instanceof Player)) {
            uBroadcast.colour(sender, Commands.getNoConsole());
            return;
        }

        Integer index = 0;
        try {
            index = Integer.valueOf(args.get(0)) - 1;
        } catch (Exception e) {
            uBroadcast.colour(sender, Commands.getError() + "You must specify the number, not the map name.");
            uVoting.printList(sender);
            return;
        }

        uVoting.vote(sender, index);

    }

}
