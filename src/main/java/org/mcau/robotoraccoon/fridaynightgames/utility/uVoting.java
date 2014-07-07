package org.mcau.robotoraccoon.fridaynightgames.utility;

import org.mcau.robotoraccoon.fridaynightgames.mMain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class uVoting {

    // Get the votes from <pUUID,gameKey> into <gameKey,Score>
    public static HashMap<String, Integer> getVoteTally() {

        HashMap<String, Integer> voteTally = new HashMap<>();

        for( UUID pUUID : mMain.voteList.keySet() ) {

            String gameKey = mMain.voteList.get(pUUID);
            Integer gameScore;

            if( voteTally.containsKey(gameKey) ) {
                gameScore = voteTally.get(gameKey) + 1;
            }
            else {
                gameScore = 1;
            }

            voteTally.put(gameKey, gameScore);

        }

        return voteTally;
    }

    public static List<String> getMostVoted() {

        List<String> mostVoted = new ArrayList<>();
        HashMap<String, Integer> voteTally = getVoteTally();
        Integer highestVote = 0;

        for( String gameKey : voteTally.keySet() ) {

            if( voteTally.get(gameKey) > highestVote ) {
                highestVote = voteTally.get(gameKey);
                mostVoted.clear();
                mostVoted.add(gameKey);
            }
            else if( voteTally.get(gameKey).equals(highestVote) ) {
                mostVoted.add(gameKey);
            }

        }

        return mostVoted;

    }

}
