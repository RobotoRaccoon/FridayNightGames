package org.mcau.robotoraccoon.fridaynightgames;

import com.pauldavdesign.mineauz.minigames.events.EndMinigameEvent;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.mcau.robotoraccoon.fridaynightgames.thread.tAutoStart;

public class mEvents implements Listener {

    @EventHandler
    public void onMinigameEnd( final EndMinigameEvent event ) {

        try {
            if( event.getMinigame().getName(false).equalsIgnoreCase(mMain.fngPlayedGames.get(0)) && mMain.fngEnabled ) {
                Bukkit.getLogger().info("The last FNG game ended -> AutoStarting now.");
                new tAutoStart().start();
            }
        } catch (Exception e) {}
    }

    @EventHandler
    public void onPlayerJoin( final PlayerJoinEvent event ) {

        if( mMain.fngEnabled ) {
            event.getPlayer().sendMessage( mCommands.getPrefix() + "FNG is running! Use `/FNG Join` to be in the next game." );
        }
    }

    @EventHandler
    public void onPlayerQuit( final PlayerQuitEvent event ) {

        if( mMain.playerList.containsKey( event.getPlayer().getUniqueId()) ) {
            mMain.playerList.remove( event.getPlayer().getUniqueId() );
        }
    }

}
