package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.Config;
import org.mcau.robotoraccoon.fridaynightgames.utility.uMessage;
import org.mcau.robotoraccoon.fridaynightgames.utility.uGameList;
import org.mcau.robotoraccoon.fridaynightgames.utility.uTypeList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class cList extends SubCommand {

    public String getPermission() {
        return "fng.player";
    }

    public String getUsage() {
        return "list [game]";
    }

    public void run(CommandSender sender, List<String> args) {

        if (args.size() < 1) {

            // Get games into an associative array.
            HashMap<String, List<String>> sortedGames = new HashMap<>();
            for (String game : uGameList.getKeys()) {
                String type = uGameList.getGameType(game);

                List<String> nameList = new ArrayList<>();
                if (sortedGames.containsKey(type)) {
                    nameList = sortedGames.get(type);
                }

                nameList.add(uGameList.getGameName(game));
                Collections.sort(nameList);
                sortedGames.put(type, nameList);
            }

            // Print the games under their respective heading
            for (String type : sortedGames.keySet()) {
                uMessage.colour(sender, "&5" + type + ": &e" + sortedGames.get(type).toString());
            }
        } else {
            String gameKey = args.get(0).toLowerCase();

            uMessage.colour(sender, "&5Name: &e" + uGameList.getGameName(gameKey));
            uMessage.colour(sender, "&5Type: &e" + uGameList.getGameType(gameKey));
            uMessage.colour(sender, "&5Join Command: &e" + uTypeList.getJoinCommand(uGameList.getGameType(gameKey)));
            uMessage.colour(sender, "&5Quit Command: &e" + uTypeList.getQuitCommand(uGameList.getGameType(gameKey)));
            uMessage.colour(sender, "&5Total Plays: &e" + Config.getGamesConfig().getInt("games." + gameKey + ".plays"));
        }

    }

}
