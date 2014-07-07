package org.mcau.robotoraccoon.fridaynightgames.thread;


import org.bukkit.ChatColor;
import org.mcau.robotoraccoon.fridaynightgames.mCommands;
import org.mcau.robotoraccoon.fridaynightgames.mMain;
import org.mcau.robotoraccoon.fridaynightgames.utility.uBroadcast;
import org.mcau.robotoraccoon.fridaynightgames.utility.uGame;
import org.mcau.robotoraccoon.fridaynightgames.utility.uGameList;
import org.mcau.robotoraccoon.fridaynightgames.utility.uVoting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class tAutoStart extends Thread {

    public void run() {

        uBroadcast.global(mCommands.getPrefix() + ChatColor.LIGHT_PURPLE + "The next Minigame will begin in: 60 seconds");
        uBroadcast.joined(mCommands.getPrefix() + ChatColor.LIGHT_PURPLE + "Make sure you use `/FNG List` and `/FNG Vote <Map>`");
        uBroadcast.notJoined(mCommands.getPrefix() + ChatColor.LIGHT_PURPLE + "Make sure you use `/FNG Join` to join the fun!");

        try {
            Thread.sleep(60000);
        }
        catch (Exception e) {
            uBroadcast.host(mCommands.getPrefix() + "An error occurred when trying to AutoStart FNG - please start the game manually.");
        }

        List<String> mostVoted = uVoting.getMostVoted();
        String gameName;
        Random random = new Random();

        if( mostVoted.size() < 1 ) {
            List<String> keys = new ArrayList<>(uGameList.getKeys());
            gameName = keys.get( random.nextInt( keys.size()) );
        }
        else {
            gameName = mostVoted.get( random.nextInt( mostVoted.size() ));
        }

        uGame.start(gameName);

    }

}
