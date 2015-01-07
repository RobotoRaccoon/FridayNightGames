package org.mcau.robotoraccoon.fridaynightgames;

import au.com.mineauz.minigames.events.EndMinigameEvent;
import nl.Steffion.BlockHunt.Events.EndArenaEvent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitTask;
import org.mcau.robotoraccoon.fridaynightgames.thread.tAutoStartRunnable;
import org.mcau.robotoraccoon.fridaynightgames.thread.tAutoStartText;
import org.mcau.robotoraccoon.fridaynightgames.utility.uBroadcast;
import org.mcau.robotoraccoon.fridaynightgames.utility.uMoney;

public class mEvents implements Listener {

    @EventHandler
    public void onMinigameEnd(final EndMinigameEvent event) {

        try {
            if( event.getMinigame().getName(false).equalsIgnoreCase(mMain.fngPlayedGames.get(0)) && mMain.fngEnabled ) {

                new tAutoStartText().start();
                BukkitTask task = new tAutoStartRunnable().runTaskLater(mMain.getPlugin(), 1220); //61 seconds
                uMoney.awardPrizeMoney();
            }
        } catch (Exception e) {
        }
    }

    @EventHandler
    public void onBlockHuntArenaEnd(final EndArenaEvent event) {

        try {
            if( event.getArena().arenaName.equalsIgnoreCase(mMain.fngPlayedGames.get(0)) && mMain.fngEnabled ) {

                new tAutoStartText().start();
                BukkitTask task = new tAutoStartRunnable().runTaskLater(mMain.getPlugin(), 1220); //61 seconds
                uMoney.awardPrizeMoney();
            }
        } catch (Exception e) {
        }
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {

        if( mMain.fngEnabled ) {
            uBroadcast.colour(event.getPlayer(), mCommands.getPrefix() + "FNG is running! Use &5/FNG Join &eto be in the next game.");
        }
    }

    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent event) {

        if( mMain.fngEnabled && mMain.playerList.containsKey( event.getPlayer().getUniqueId() ) ) {
            mMain.playerList.remove(event.getPlayer().getUniqueId());
        }
    }

}
