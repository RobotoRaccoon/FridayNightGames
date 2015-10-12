package org.mcau.robotoraccoon.fridaynightgames.utility;

import org.bukkit.ChatColor;
import org.mcau.robotoraccoon.fridaynightgames.Config;
import org.mcau.robotoraccoon.fridaynightgames.Main;
import org.mcau.robotoraccoon.fridaynightgames.games.MinigameMap;

import java.util.*;

public class GameUtil {

    //Start a specified game
    public static void start(MinigameMap map) {

        String command = TypeUtil.getJoinCommand(map.getType()) + " " + map.getKey();

        for (final UUID pUUID : PlayerListUtil.getKeys()) {
            RunAsUtil.asPlayer(command, pUUID);
        }

        Main.setAutoStartEnabled(false);
        Main.getPlayedGames().add(0, map);
        VotingUtil.generateList();
        map.incrementPlayCount();

        MessageUtil.global(MessageUtil.getPrefix() + "Starting Game: " + ChatColor.RED + map.getName());
    }

    // Starts the highest rated game.
    public static void startResults() {
        List<MinigameMap> mostVoted = VotingUtil.getMostVoted();
        MinigameMap map;
        Random random = new Random();

        if (mostVoted.size() < 1) {
            // No votes, pick a completely random map.
            List<MinigameMap> maps = new ArrayList<>(VotingUtil.mapList);
            map = maps.get(random.nextInt(maps.size()));
        } else {
            // Pick highest rated, or random of those if results are tied.
            map = mostVoted.get(random.nextInt(mostVoted.size()));
        }

        GameUtil.start(map);
    }

    //Forces all users to quit their game
    public static void end(MinigameMap map) {
        String command = TypeUtil.getQuitCommand(map.getKey());

        for (final UUID pUUID : PlayerListUtil.getKeys()) {
            RunAsUtil.asPlayer(command, pUUID);
        }

        MessageUtil.global(MessageUtil.getPrefix() + "Ending Game: " + ChatColor.RED + map.getName());
    }

    public static void loadMinigamesFromConfig() {
        Main.getMiniames().clear();
        Set<String> keys = Config.getGamesConfig().getConfigurationSection("games").getKeys(false);
        for (String key : keys) {
            MinigameMap map = new MinigameMap(key);
            Main.getMiniames().put(key, map);
        }
    }

    public static void saveMinigamesToConfig() {
        for (MinigameMap map : Main.getMiniames().values())
            map.saveToConfig();
    }
}
