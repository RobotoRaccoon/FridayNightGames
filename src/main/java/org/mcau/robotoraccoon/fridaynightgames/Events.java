package org.mcau.robotoraccoon.fridaynightgames;

import au.com.mineauz.minigames.events.EndMinigameEvent;
import nl.Steffion.BlockHunt.Events.EndArenaEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.mcau.robotoraccoon.fridaynightgames.thread.AutoStartRunnable;
import org.mcau.robotoraccoon.fridaynightgames.thread.AutoStartTextThread;
import org.mcau.robotoraccoon.fridaynightgames.utility.LangUtil;
import org.mcau.robotoraccoon.fridaynightgames.utility.MessageUtil;
import org.mcau.robotoraccoon.fridaynightgames.utility.MoneyUtil;

public class Events implements Listener {

    @EventHandler
    public void onMinigameEnd(final EndMinigameEvent event) {

        try {
            if (event.getMinigame().getName(false).equalsIgnoreCase(Main.getPlayedGames().get(0).getKey()) && Main.getFngEnabled()) {

                new AutoStartTextThread().start();
                new AutoStartRunnable().runTaskLater(Main.getPlugin(), 1220); //61 seconds
                MoneyUtil.awardPrizeMoney();
            }
        } catch (Exception e) {
        }
    }

    @EventHandler
    public void onBlockHuntArenaEnd(final EndArenaEvent event) {

        try {
            if (event.getArena().arenaName.equalsIgnoreCase(Main.getPlayedGames().get(0).getKey()) && Main.getFngEnabled()) {

                new AutoStartTextThread().start();
                new AutoStartRunnable().runTaskLater(Main.getPlugin(), 1220); //61 seconds
                MoneyUtil.awardPrizeMoney();
            }
        } catch (Exception e) {
        }
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {

        if (Main.getFngEnabled()) {
            MessageUtil.colour(event.getPlayer(), LangUtil.formatPrefix("FNG is running! Use &5/FNG Join &eto be in the next game."));
        }
    }

    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent event) {

        if (Main.getFngEnabled() && Main.getPlayerList().containsKey(event.getPlayer().getUniqueId())) {
            Main.getPlayerList().remove(event.getPlayer().getUniqueId());
        }
    }

}
