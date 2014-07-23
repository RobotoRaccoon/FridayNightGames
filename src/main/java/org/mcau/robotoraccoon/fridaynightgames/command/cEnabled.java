package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.mCommands;
import org.mcau.robotoraccoon.fridaynightgames.mMain;
import org.mcau.robotoraccoon.fridaynightgames.utility.uBroadcast;
import org.mcau.robotoraccoon.fridaynightgames.utility.uPlayerList;
import org.mcau.robotoraccoon.fridaynightgames.utility.uVoting;

public class cEnabled {

    public static void enabled( CommandSender sender, String[] args ) {

        if( !sender.hasPermission( getPermission() ) ) {
            sender.sendMessage(mCommands.getDenied());
            return;
        }

        if( args.length < 2 ) {

            if( mMain.fngEnabled ) {
                uBroadcast.colour(sender, mCommands.getPrefix() + "Status: &aEnabled");
            } else {
                uBroadcast.colour(sender, mCommands.getPrefix() + "Status: &cDisabled");
            }

        }
        else if( args[1].matches("(?i)T.*") ) {

            mMain.fngEnabled = true;
            uPlayerList.clearList();
            uVoting.generateList(5);

            uBroadcast.global(mCommands.getPrefix() + "Has been &aEnabled &eby &5" + sender.getName());
            uBroadcast.global(mCommands.getPrefix() + "Use &5/FNG Join&e to play in the games!");

        }
        else {

            mMain.fngEnabled = false;
            uPlayerList.clearList();

            uBroadcast.global(mCommands.getPrefix() + "Has been &cDisabled &eby &5" + sender.getName());

        }
    }

    public static String getPermission() { return "fng.operator"; }

}
