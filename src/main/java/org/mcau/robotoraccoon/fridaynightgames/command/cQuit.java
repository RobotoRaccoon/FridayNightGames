package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mcau.robotoraccoon.fridaynightgames.mCommands;
import org.mcau.robotoraccoon.fridaynightgames.mMain;
import org.mcau.robotoraccoon.fridaynightgames.utility.uBroadcast;

public class cQuit {

    public static void quit(CommandSender sender, String[] args) {

        if( !sender.hasPermission( getPermission() ) ) {
            uBroadcast.colour(sender, mCommands.getDenied());
            return;
        }

        if( !mMain.fngEnabled ) {
            uBroadcast.colour(sender, mCommands.getDisabled());
            return;
        }

        Player player = (Player) sender;

        if( mMain.playerList.containsKey( player.getUniqueId() ) ) {
            mMain.playerList.remove( player.getUniqueId() );
            uBroadcast.colour(sender, mCommands.getPrefix() + "You have quit the games! Thanks for playing!");
        } else {
            uBroadcast.colour(sender, mCommands.getError() + "You weren't in the games anyway.");
        }

    }

    public static String getPermission() { return "fng.player"; }

}
