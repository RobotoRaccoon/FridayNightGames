package org.mcau.robotoraccoon.fridaynightgames.thread;

import org.bukkit.scheduler.BukkitRunnable;
import org.mcau.robotoraccoon.fridaynightgames.mCommands;
import org.mcau.robotoraccoon.fridaynightgames.mMain;
import org.mcau.robotoraccoon.fridaynightgames.utility.uBroadcast;
import org.mcau.robotoraccoon.fridaynightgames.utility.uGame;
import org.mcau.robotoraccoon.fridaynightgames.utility.uVoting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class tAutoStartText extends Thread {

    public void run() {

        mMain.autoStartEnabled = true;

        try {

            Thread.sleep(1000);

            uBroadcast.global(mCommands.getPrefix() + "&dThe next Minigame will begin in: &560 seconds");
            uBroadcast.joined(mCommands.getPrefix() + "&dMake sure you use &5/FNG Vote&d and &5/FNG Vote <Number>");
            uBroadcast.notJoined(mCommands.getPrefix() + "&dMake sure you use &5/FNG Join&d to join the fun!");

            Thread.sleep(60000);

        }
        catch (Exception e) {
            //uBroadcast.host(mCommands.getPrefix() + "An error occurred when trying to AutoStart FNG - please start the game manually.");
        }

    }



}
