package org.mcau.robotoraccoon.fridaynightgames.thread;

import org.mcau.robotoraccoon.fridaynightgames.Main;
import org.mcau.robotoraccoon.fridaynightgames.utility.uMessage;

public class tAutoStartText extends Thread {

    public void run() {

        Main.setAutoStartEnabled(true);
        try {

            Thread.sleep(1000);

            uMessage.global(uMessage.getPrefix() + "&dThe next Minigame will begin in: &560 seconds");
            uMessage.joined(uMessage.getPrefix() + "&dMake sure you use &5/FNG Vote&d and &5/FNG Vote <Number>");
            uMessage.notJoined(uMessage.getPrefix() + "&dMake sure you use &5/FNG Join&d to join the fun!");

            Thread.sleep(60000);

        } catch (Exception e) {
            //uBroadcast.host(mCommands.getPrefix() + "An error occurred when trying to AutoStart FNG - please start the game manually.");
        }

    }

}
