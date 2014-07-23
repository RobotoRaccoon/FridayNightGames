package org.mcau.robotoraccoon.fridaynightgames.command;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mcau.robotoraccoon.fridaynightgames.mCommands;
import org.mcau.robotoraccoon.fridaynightgames.mMain;
import org.mcau.robotoraccoon.fridaynightgames.utility.uGameList;
import org.mcau.robotoraccoon.fridaynightgames.utility.uVoting;

public class cVote {

    public static void vote( CommandSender sender, String[] args ) {

        if( !sender.hasPermission( getPermission() ) ) {
            sender.sendMessage(mCommands.getDenied());
            return;
        }

        if( !mMain.fngEnabled ) {
            sender.sendMessage( mCommands.getDisabled() );
            return;
        }

        if( args.length < 2 ) {
            uVoting.printList(sender);
            return;
        }

        // Only players from here onwards.
        if( !(sender instanceof Player) ) {
            sender.sendMessage(mCommands.getNoConsole());
            return;
        }

        Integer index = null;
        try {
            index = Integer.valueOf( args[1] ) - 1;
        } catch(Exception e) {
            sender.sendMessage( mCommands.getError() + "You must specify a number, not the map name." );
            return;
        }

        uVoting.vote(sender, index);

    }

    public static String getPermission() { return "fng.player"; }
}
