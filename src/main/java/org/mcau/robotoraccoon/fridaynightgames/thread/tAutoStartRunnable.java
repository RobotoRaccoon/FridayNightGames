package org.mcau.robotoraccoon.fridaynightgames.thread;

import org.bukkit.scheduler.BukkitRunnable;
import org.mcau.robotoraccoon.fridaynightgames.Main;
import org.mcau.robotoraccoon.fridaynightgames.utility.uGame;

public class tAutoStartRunnable extends BukkitRunnable {

    public void run() {

        // Cancel if FNG is no longer enabled, or the timer was stopped.
        if (!Main.getFngEnabled() || !Main.getAutoStartEnabled()) {
            this.cancel();
            return;
        }

        uGame.startResults();
    }

}
