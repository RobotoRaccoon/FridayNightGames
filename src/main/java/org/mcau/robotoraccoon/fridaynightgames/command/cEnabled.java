package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.Commands;
import org.mcau.robotoraccoon.fridaynightgames.Main;
import org.mcau.robotoraccoon.fridaynightgames.utility.uBroadcast;
import org.mcau.robotoraccoon.fridaynightgames.utility.uPlayerList;
import org.mcau.robotoraccoon.fridaynightgames.utility.uVoting;

public class cEnabled {

    public static void enabled( CommandSender sender, String[] args ) {

        if( !sender.hasPermission( getPermission() ) ) {
            sender.sendMessage(Commands.getDenied());
            return;
        }

        if( args.length < 2 ) {

            if( Main.fngEnabled ) {
                uBroadcast.colour(sender, Commands.getPrefix() + "Status: &aEnabled");
            } else {
                uBroadcast.colour(sender, Commands.getPrefix() + "Status: &cDisabled");
            }

        }
        else if( args[1].matches("(?i)T.*") ) {

            Main.fngEnabled = true;
            uPlayerList.clearList();
            uVoting.generateList();

            uBroadcast.global(Commands.getPrefix() + "Has been &aEnabled &eby &5" + sender.getName());
            uBroadcast.global(Commands.getPrefix() + "Use &5/FNG Join&e to play in the games!");

        }
        else {

            Main.fngEnabled = false;
            uPlayerList.clearList();

            uBroadcast.global(Commands.getPrefix() + "Has been &cDisabled &eby &5" + sender.getName());

        }
    }

    public static String getPermission() { return "fng.operator"; }

}
