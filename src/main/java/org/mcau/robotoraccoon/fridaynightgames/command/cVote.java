package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mcau.robotoraccoon.fridaynightgames.Commands;
import org.mcau.robotoraccoon.fridaynightgames.Main;
import org.mcau.robotoraccoon.fridaynightgames.utility.uBroadcast;
import org.mcau.robotoraccoon.fridaynightgames.utility.uVoting;

public class cVote {

    public static void run(CommandSender sender, String[] args) {

        if (!sender.hasPermission(getPermission())) {
            uBroadcast.colour(sender, Commands.getDenied());
            return;
        }

        if (!Main.getFngEnabled()) {
            uBroadcast.colour(sender, Commands.getDisabled());
            return;
        }

        if (args.length < 2) {
            uVoting.printList(sender);
            return;
        }

        // Only players from here onwards.
        if (!(sender instanceof Player)) {
            uBroadcast.colour(sender, Commands.getNoConsole());
            return;
        }

        Integer index;
        try {
            index = Integer.valueOf(args[1]) - 1;
        } catch (Exception e) {
            uBroadcast.colour(sender, Commands.getError() + "You must specify a number, not the map name.");
            uVoting.printList(sender);
            return;
        }

        uVoting.vote(sender, index);

    }

    public static String getPermission() {
        return "fng.player";
    }
}
