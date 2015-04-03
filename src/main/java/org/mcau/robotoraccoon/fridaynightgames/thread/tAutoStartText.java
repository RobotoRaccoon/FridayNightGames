package org.mcau.robotoraccoon.fridaynightgames.thread;

import org.mcau.robotoraccoon.fridaynightgames.Commands;
import org.mcau.robotoraccoon.fridaynightgames.Main;
import org.mcau.robotoraccoon.fridaynightgames.utility.uBroadcast;

public class tAutoStartText extends Thread {

    public void run() {

        Main.autoStartEnabled = true;
        try {

            Thread.sleep(1000);

            uBroadcast.global(Commands.getPrefix() + "&dThe next Minigame will begin in: &560 seconds");
            uBroadcast.joined(Commands.getPrefix() + "&dMake sure you use &5/FNG Vote&d and &5/FNG Vote <Number>");
            uBroadcast.notJoined(Commands.getPrefix() + "&dMake sure you use &5/FNG Join&d to join the fun!");

            Thread.sleep(60000);

        }
        catch (Exception e) {
            //uBroadcast.host(mCommands.getPrefix() + "An error occurred when trying to AutoStart FNG - please start the game manually.");
        }

    }

}
