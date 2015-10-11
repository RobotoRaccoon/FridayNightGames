package org.mcau.robotoraccoon.fridaynightgames.utility;

import org.bukkit.entity.Player;
import org.mcau.robotoraccoon.fridaynightgames.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class ListUtil {

    // Array of keys
    public static List<UUID> getKeys() {
        return new ArrayList<UUID>(Main.getPlayerList().keySet());
    }

    // Array of players
    public static List<Player> getPlayers() {
        return new ArrayList<Player>(Main.getPlayerList().values());
    }

    // Array of player names
    public static List<String> getPlayerNames() {

        List<String> playerNames = new ArrayList<>();

        for (Player player : getPlayers()) {
            playerNames.add(player.getName());
        }

        return playerNames;
    }

    // Size of list
    public static Integer getSize() {
        return Main.getPlayerList().size();
    }

    // Clear the list
    public static void clearList() {
        Main.getPlayerList().clear();
    }

    // A single key at random
    public static UUID getRandomKey() {

        Random random = new Random();
        List<UUID> keys = ListUtil.getKeys();
        return keys.get(random.nextInt(keys.size()));
    }

    // A single players at random
    public static Player getRandomPlayer() {
        return Main.getPlayerList().get(ListUtil.getRandomKey());
    }

}
