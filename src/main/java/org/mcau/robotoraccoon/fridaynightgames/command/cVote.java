package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mcau.robotoraccoon.fridaynightgames.mCommands;
import org.mcau.robotoraccoon.fridaynightgames.mMain;
import org.mcau.robotoraccoon.fridaynightgames.utility.uGameList;

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
            sender.sendMessage( mCommands.getError() + "You need to specify a map. Look at maps using /FNG List" );
        }
        else {

            String gameKey = args[1].toLowerCase();

            if( !uGameList.getKeys().contains(gameKey) ) {
                sender.sendMessage( mCommands.getError() + "This map does not exist. Look at maps using /FNG List" );
                return;
            }

            Player player = (Player) sender;
            mMain.voteList.put(player.getUniqueId(), gameKey);
            player.sendMessage( mCommands.getPrefix() + "You have successfully voted for " + ChatColor.DARK_PURPLE + uGameList.getGameName(gameKey) );

        }

    }

    public static String getPermission() { return "fng.player"; }
}
