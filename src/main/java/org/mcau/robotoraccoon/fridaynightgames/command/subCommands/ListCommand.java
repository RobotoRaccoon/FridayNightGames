package org.mcau.robotoraccoon.fridaynightgames.command.subCommands;

import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.Config;
import org.mcau.robotoraccoon.fridaynightgames.command.SubCommand;
import org.mcau.robotoraccoon.fridaynightgames.utility.MessageUtil;
import org.mcau.robotoraccoon.fridaynightgames.utility.GameListUtil;
import org.mcau.robotoraccoon.fridaynightgames.utility.TypeUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ListCommand extends SubCommand {

    public String getPermission() {
        return "fng.player";
    }

    public String getUsage() {
        return "list [game]";
    }

    public String getDescription() {
        return "&5List &f> &dLook at the available maps.";
    }

    public void run(CommandSender sender, List<String> args) {

        if (args.size() < 1) {

            // Get games into an associative array.
            HashMap<String, List<String>> sortedGames = new HashMap<>();
            for (String game : GameListUtil.getKeys()) {
                String type = GameListUtil.getGameType(game);

                List<String> nameList = new ArrayList<>();
                if (sortedGames.containsKey(type)) {
                    nameList = sortedGames.get(type);
                }

                nameList.add(GameListUtil.getGameName(game));
                Collections.sort(nameList);
                sortedGames.put(type, nameList);
            }

            // Print the games under their respective heading
            for (String type : sortedGames.keySet()) {
                MessageUtil.colour(sender, "&5" + type + ": &e" + sortedGames.get(type).toString());
            }
        } else {
            String gameKey = args.get(0).toLowerCase();

            MessageUtil.colour(sender, "&5Name: &e" + GameListUtil.getGameName(gameKey));
            MessageUtil.colour(sender, "&5Type: &e" + GameListUtil.getGameType(gameKey));
            MessageUtil.colour(sender, "&5Join Command: &e" + TypeUtil.getJoinCommand(GameListUtil.getGameType(gameKey)));
            MessageUtil.colour(sender, "&5Quit Command: &e" + TypeUtil.getQuitCommand(GameListUtil.getGameType(gameKey)));
            MessageUtil.colour(sender, "&5Total Plays: &e" + Config.getGamesConfig().getInt("games." + gameKey + ".plays"));
        }

    }

}
