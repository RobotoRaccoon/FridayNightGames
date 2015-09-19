package org.mcau.robotoraccoon.fridaynightgames.utility;

import org.bukkit.ChatColor;
import org.mcau.robotoraccoon.fridaynightgames.Commands;
import org.mcau.robotoraccoon.fridaynightgames.Config;
import org.mcau.robotoraccoon.fridaynightgames.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class uGame {

    //Start a specified game
    public static void start(String gameKey) {

        gameKey = gameKey.toLowerCase();
        String command = uTypeList.getJoinCommand(uGameList.getGameType(gameKey)) + " " + gameKey;

        for (final UUID pUUID : uPlayerList.getKeys()) {
            uRunAs.asPlayer(command, pUUID);
        }

        Main.setAutoStartEnabled(false);
        Main.getPlayedGames().add(0, gameKey);
        uVoting.generateList();
        addPlayCount(gameKey);

        uBroadcast.global(Commands.getPrefix() + "Starting Game: " + ChatColor.RED + gameKey.toUpperCase());
    }

    // Starts the highest rated game.
    public static void startResults() {
        List<String> mostVoted = uVoting.getMostVoted();
        String gameName;
        Random random = new Random();

        if (mostVoted.size() < 1) {
            // No votes, pick a completely random map.
            List<String> keys = new ArrayList<>(uVoting.mapList);
            gameName = keys.get(random.nextInt(keys.size()));
        } else {
            // Pick highest rated, or random of those if results are tied.
            gameName = mostVoted.get(random.nextInt(mostVoted.size()));
        }

        uGame.start(gameName);
    }

    //Forces all users to quit their game
    public static void end(String gameKey) {

        gameKey = gameKey.toLowerCase();
        String command = uTypeList.getQuitCommand(uGameList.getGameType(gameKey));

        for (final UUID pUUID : uPlayerList.getKeys()) {
            uRunAs.asPlayer(command, pUUID);
        }

        uBroadcast.global(Commands.getPrefix() + "Ending Game: " + ChatColor.RED + gameKey.toUpperCase());
    }

    //Add plays to the games total play count for every FNG
    public static void addPlayCount(String gameKey) { addPlayCount(gameKey, 1); }
    public static void addPlayCount(String gameKey, Integer amount) {
        gameKey = gameKey.toLowerCase();
        Integer gamePlays = 0;

        if (Config.getGamesConfig().contains("games." + gameKey + ".plays")) {
            gamePlays = Config.getGamesConfig().getInt("games." + gameKey + ".plays");
        }

        Config.getGamesConfig().set("games." + gameKey + ".plays", gamePlays + amount);
        Config.saveConfigs();
    }
}
