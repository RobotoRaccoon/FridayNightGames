package org.mcau.robotoraccoon.fridaynightgames.utility;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class uBroadcast {

    public static void global( String message ) {
        Bukkit.broadcast( ChatColor.translateAlternateColorCodes('&', message), "fng.player" );
    }

    public static void joined( String message ) {

        for( Player player : Bukkit.getOnlinePlayers() ) {
            if( uPlayerList.getKeys().contains( player.getUniqueId() ) ) {
                player.sendMessage( ChatColor.translateAlternateColorCodes('&', message) );
            }
        }
    }

    public static void notJoined( String message ) {

        for( Player player : Bukkit.getOnlinePlayers() ) {
            if( !uPlayerList.getKeys().contains( player.getUniqueId() ) ) {
                player.sendMessage( ChatColor.translateAlternateColorCodes('&', message) );
            }
        }
    }

    public static void host( String message ) {

        for( Player player : Bukkit.getOnlinePlayers() ) {
            if( player.hasPermission("fng.host") ) {
                player.sendMessage( ChatColor.translateAlternateColorCodes('&', message) );
            }
        }
    }

}
