package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mcau.robotoraccoon.fridaynightgames.mCommands;
import org.mcau.robotoraccoon.fridaynightgames.mMain;
import org.mcau.robotoraccoon.fridaynightgames.utility.uBroadcast;

public class cJoin {

    public static void join(CommandSender sender, String[] args) {

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
            uBroadcast.colour(sender, mCommands.getError() + "You have already joined the games.");
        } else {
            mMain.playerList.put( player.getUniqueId(), player );
            uBroadcast.colour(sender, mCommands.getPrefix() + "You have joined the games! You'll join with the next available lobby!");
        }

    }

    public static String getPermission() { return "fng.player"; }

}
