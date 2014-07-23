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

        uBroadcast.colour(sender, mCommands.getPrefix() + "The most voted maps: &c" + uVoting.getMostVoted().toString());

    }

    public static String getPermission() { return "fng.player"; }

}

