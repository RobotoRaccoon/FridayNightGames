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

        try {

            Thread.sleep(1000);

            uBroadcast.global(mCommands.getPrefix() + "&dThe next Minigame will begin in: &560 seconds");
            uBroadcast.joined(mCommands.getPrefix() + "&dMake sure you use &5/FNG List&d and &5/FNG Vote <Map>");
            uBroadcast.notJoined(mCommands.getPrefix() + "&dMake sure you use &5/FNG Join&d to join the fun!");

            Thread.sleep(60000);

        }
        catch (Exception e) {
            uBroadcast.host(mCommands.getPrefix() + "An error occurred when trying to AutoStart FNG - please start the game manually.");
        }

        // Cancel if FNG is no longer enabled.
        if( !mMain.fngEnabled ) {
            return;
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
