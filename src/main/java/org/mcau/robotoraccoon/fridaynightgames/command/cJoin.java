package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mcau.robotoraccoon.fridaynightgames.mCommands;
import org.mcau.robotoraccoon.fridaynightgames.mMain;

public class cJoin {

    public static void join(CommandSender sender, String[] args) {

        Player player = (Player) sender;

        if( !sender.hasPermission( getPermission() ) ) {
            sender.sendMessage(mCommands.getDenied());
            return;
        }

        if( !mMain.fngEnabled ) {
            sender.sendMessage( mCommands.getDisabled() );
            return;
        }

        if( mMain.playerList.containsKey( player.getUniqueId() ) ) {
            player.sendMessage(mCommands.getError() + "You have already joined the games.");
        } else {
            mMain.playerList.put( player.getUniqueId(), player.getName() );
            player.sendMessage(mCommands.getPrefix() + "You have joined the games! You'll join with the next available lobby!");
        }

    }

    public static String getPermission() { return "fng.player"; }

}
