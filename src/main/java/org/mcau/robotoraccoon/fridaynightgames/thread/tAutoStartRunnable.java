package org.mcau.robotoraccoon.fridaynightgames.thread;

import org.bukkit.scheduler.BukkitRunnable;
import org.mcau.robotoraccoon.fridaynightgames.Main;
import org.mcau.robotoraccoon.fridaynightgames.utility.uGame;
import org.mcau.robotoraccoon.fridaynightgames.utility.uVoting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class tAutoStartRunnable extends BukkitRunnable {

    public void run() {

        // Cancel if FNG is no longer enabled, or the timer was stopped.
        if (!Main.fngEnabled || !Main.autoStartEnabled) {
            this.cancel();
            return;
        }

        List<String> mostVoted = uVoting.getMostVoted();
        String gameName;
        Random random = new Random();

        if (mostVoted.size() < 1) {
            List<String> keys = new ArrayList<>(uVoting.mapList);
            gameName = keys.get(random.nextInt(keys.size()));
        } else {
            gameName = mostVoted.get(random.nextInt(mostVoted.size()));
        }

        uGame.start(gameName);
    }

}
