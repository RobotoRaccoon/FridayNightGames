package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.mCommands;
import org.mcau.robotoraccoon.fridaynightgames.mMain;
import org.mcau.robotoraccoon.fridaynightgames.utility.uBroadcast;
import org.mcau.robotoraccoon.fridaynightgames.utility.uVoting;

public class cResults {

    public static void results( CommandSender sender, String[] args ) {

        if( !sender.hasPermission( getPermission() ) ) {
            uBroadcast.colour(sender, mCommands.getDenied());
            return;
        }

        if( !mMain.fngEnabled ) {
            uBroadcast.colour(sender, mCommands.getDisabled());
            return;
        }

        if( args.length < 2 ) {
            uBroadcast.colour(sender, mCommands.getPrefix() + "The most voted maps: &c" + uVoting.getMostVoted().toString());
            return;
        }
        else if( args[1].equalsIgnoreCase("reset") ) {

            if( !sender.hasPermission("fng.host") ) {
                uBroadcast.colour(sender, mCommands.getDenied() );
                return;
            }

            uVoting.generateList(5);
            uBroadcast.colour(sender, mCommands.getPrefix() + "The results have been reset and a new voting list has been generated.");
            return;

        }

    }

    public static String getPermission() { return "fng.player"; }

}

