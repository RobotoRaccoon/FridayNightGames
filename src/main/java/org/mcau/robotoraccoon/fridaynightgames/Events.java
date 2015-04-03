package org.mcau.robotoraccoon.fridaynightgames;

import au.com.mineauz.minigames.events.EndMinigameEvent;
import nl.Steffion.BlockHunt.Events.EndArenaEvent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.mcau.robotoraccoon.fridaynightgames.thread.tAutoStartRunnable;
import org.mcau.robotoraccoon.fridaynightgames.thread.tAutoStartText;
import org.mcau.robotoraccoon.fridaynightgames.utility.uBroadcast;
import org.mcau.robotoraccoon.fridaynightgames.utility.uMoney;

public class Events implements Listener {

    @EventHandler
    public void onMinigameEnd(final EndMinigameEvent event) {

        try {
            if( event.getMinigame().getName(false).equalsIgnoreCase(Main.fngPlayedGames.get(0)) && Main.fngEnabled ) {

                new tAutoStartText().start();
                new tAutoStartRunnable().runTaskLater(Main.getPlugin(), 1220); //61 seconds
                uMoney.awardPrizeMoney();
            }
        } catch (Exception e) {
        }
    }

    @EventHandler
    public void onBlockHuntArenaEnd(final EndArenaEvent event) {

        try {
            if( event.getArena().arenaName.equalsIgnoreCase(Main.fngPlayedGames.get(0)) && Main.fngEnabled ) {

                new tAutoStartText().start();
                new tAutoStartRunnable().runTaskLater(Main.getPlugin(), 1220); //61 seconds
                uMoney.awardPrizeMoney();
            }
        } catch (Exception e) {
        }
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {

        if( Main.fngEnabled ) {
            uBroadcast.colour(event.getPlayer(), Commands.getPrefix() + "FNG is running! Use &5/FNG Join &eto be in the next game.");
        }
    }

    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent event) {

        if( Main.fngEnabled && Main.playerList.containsKey( event.getPlayer().getUniqueId() ) ) {
            Main.playerList.remove(event.getPlayer().getUniqueId());
        }
    }

}
