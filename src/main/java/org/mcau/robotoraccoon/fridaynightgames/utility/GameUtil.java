package org.mcau.robotoraccoon.fridaynightgames.utility;

import org.bukkit.ChatColor;
import org.mcau.robotoraccoon.fridaynightgames.Config;
import org.mcau.robotoraccoon.fridaynightgames.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class GameUtil {

    //Start a specified game
    public static void start(String gameKey) {

        gameKey = gameKey.toLowerCase();
        String command = TypeUtil.getJoinCommand(GameListUtil.getGameType(gameKey)) + " " + gameKey;

        for (final UUID pUUID : ListUtil.getKeys()) {
            RunAsUtil.asPlayer(command, pUUID);
        }

        Main.setAutoStartEnabled(false);
        Main.getPlayedGames().add(0, gameKey);
        VotingUtil.generateList();
        addPlayCount(gameKey);

        MessageUtil.global(MessageUtil.getPrefix() + "Starting Game: " + ChatColor.RED + gameKey.toUpperCase());
    }

    // Starts the highest rated game.
    public static void startResults() {
        List<String> mostVoted = VotingUtil.getMostVoted();
        String gameName;
        Random random = new Random();

        if (mostVoted.size() < 1) {
            // No votes, pick a completely random map.
            List<String> keys = new ArrayList<>(VotingUtil.mapList);
            gameName = keys.get(random.nextInt(keys.size()));
        } else {
            // Pick highest rated, or random of those if results are tied.
            gameName = mostVoted.get(random.nextInt(mostVoted.size()));
        }

        GameUtil.start(gameName);
    }

    //Forces all users to quit their game
    public static void end(String gameKey) {

        gameKey = gameKey.toLowerCase();
        String command = TypeUtil.getQuitCommand(GameListUtil.getGameType(gameKey));

        for (final UUID pUUID : ListUtil.getKeys()) {
            RunAsUtil.asPlayer(command, pUUID);
        }

        MessageUtil.global(MessageUtil.getPrefix() + "Ending Game: " + ChatColor.RED + gameKey.toUpperCase());
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
