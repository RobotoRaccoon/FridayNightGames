package org.mcau.robotoraccoon.fridaynightgames.utility;

import org.mcau.robotoraccoon.fridaynightgames.mMain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class uPlayerList {

    // Array of keys
    public static List<UUID> getKeys() { return new ArrayList<UUID>(mMain.playerList.keySet()); }

    // Array of values
    public static List<String> getValues() { return new ArrayList<String>(mMain.playerList.values()); }

    // Size of list
    public static Integer getSize() { return mMain.playerList.size(); }

    // Clear the list
    public static void clearList() { mMain.playerList.clear(); }

    // A single key at random
    public static UUID getRandomKey() {

        Random random = new Random();
        List<UUID> keys = uPlayerList.getKeys();
        return keys.get( random.nextInt( keys.size()) );
    }

    // A single value at random
    public static String getRandomValue() { return mMain.playerList.get(uPlayerList.getRandomKey()); }

}
