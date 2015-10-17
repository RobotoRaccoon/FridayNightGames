package org.mcau.robotoraccoon.fridaynightgames.thread;

import org.mcau.robotoraccoon.fridaynightgames.Main;
import org.mcau.robotoraccoon.fridaynightgames.utility.LangUtil;
import org.mcau.robotoraccoon.fridaynightgames.utility.MessageUtil;

public class AutoStartTextThread extends Thread {

    public void run() {

        Main.setAutoStartEnabled(true);
        try {
            Thread.sleep(1000);

            MessageUtil.global(LangUtil.formatPrefix("&dThe next Minigame will begin in: &560 seconds"));
            MessageUtil.joined(LangUtil.formatPrefix("&dMake sure you use &5/FNG Vote&d and &5/FNG Vote <Number>"));
            MessageUtil.notJoined(LangUtil.formatPrefix("&dMake sure you use &5/FNG Join&d to join the fun!"));
        } catch (Exception e) {
        }

    }

}
