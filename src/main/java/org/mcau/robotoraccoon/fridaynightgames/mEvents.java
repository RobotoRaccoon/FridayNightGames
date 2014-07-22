package org.mcau.robotoraccoon.fridaynightgames;

import au.com.mineauz.minigames.events.EndMinigameEvent;
import nl.Steffion.BlockHunt.Events.eArenaEnd;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.mcau.robotoraccoon.fridaynightgames.thread.tAutoStart;
import org.mcau.robotoraccoon.fridaynightgames.utility.uMoney;

public class mEvents implements Listener {

    @EventHandler
    public void onMinigameEnd(final EndMinigameEvent event) {

        try {
            if (event.getMinigame().getName(false).equalsIgnoreCase(mMain.fngPlayedGames.get(0)) && mMain.fngEnabled) {

                new tAutoStart().start();
                uMoney.awardPrizeMoney();
            }
        } catch (Exception e) {
        }
    }

    @EventHandler
    public void onBlockHuntArenaEnd(final eArenaEnd event) {

        try {
            if (event.getArenaName().equalsIgnoreCase(mMain.fngPlayedGames.get(0)) && mMain.fngEnabled) {

                new tAutoStart().start();
                uMoney.awardPrizeMoney();
            }
        } catch (Exception e) {
        }
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {

        if (mMain.fngEnabled) {
            event.getPlayer().sendMessage(mCommands.getPrefix() + "FNG is running! Use " + ChatColor.DARK_PURPLE +
                    "/FNG Join" + ChatColor.YELLOW + " to be in the next game.");
        }
    }

    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent event) {

        if (mMain.fngEnabled && mMain.playerList.containsKey(event.getPlayer().getUniqueId())) {
            mMain.playerList.remove(event.getPlayer().getUniqueId());
        }
    }

}
