package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mcau.robotoraccoon.fridaynightgames.mCommands;
import org.mcau.robotoraccoon.fridaynightgames.mMain;

public class cQuit {

    public static void quit(CommandSender sender, String[] args) {

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
            mMain.playerList.remove( player.getUniqueId() );
            player.sendMessage(mCommands.getPrefix() + "You have quit the games! Thanks for playing!");
        } else {
            player.sendMessage(mCommands.getError() + "You weren't in the games anyway.");
        }

    }

    public static String getPermission() { return "fng.player"; }

}
