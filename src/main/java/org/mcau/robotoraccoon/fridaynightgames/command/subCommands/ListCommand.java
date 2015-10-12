package org.mcau.robotoraccoon.fridaynightgames.command.subCommands;

import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.Main;
import org.mcau.robotoraccoon.fridaynightgames.command.SubCommand;
import org.mcau.robotoraccoon.fridaynightgames.games.MinigameMap;
import org.mcau.robotoraccoon.fridaynightgames.games.MinigameType;
import org.mcau.robotoraccoon.fridaynightgames.utility.MessageUtil;
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
            HashMap<MinigameType, List<String>> sortedGames = new HashMap<>();
            for (MinigameMap map : Main.getMinigames().values()) {
                MinigameType type = map.getType();

                List<String> nameList = new ArrayList<>();
                if (sortedGames.containsKey(type)) {
                    nameList = sortedGames.get(type);
                }

                nameList.add(map.getName());
                Collections.sort(nameList);
                sortedGames.put(type, nameList);
            }

            // Print the games under their respective heading
            for (MinigameType type : sortedGames.keySet()) {
                MessageUtil.colour(sender, "&5" + type + ": &e" + sortedGames.get(type).toString());
            }
        } else {
            String key = args.get(0).toLowerCase();
            MinigameMap map = Main.getMinigames().get(key);

            if (map == null) {
                MessageUtil.colour(sender, MessageUtil.getError() + "This minigame does not exist.");
                return;
            }

            MessageUtil.colour(sender, "&5Name: &e" + map.getName());
            MessageUtil.colour(sender, "&5Type: &e" + map.getType());
            MessageUtil.colour(sender, "&5Join Command: &e" + TypeUtil.getJoinCommand(map.getType()));
            MessageUtil.colour(sender, "&5Quit Command: &e" + TypeUtil.getQuitCommand(map.getType()));
            MessageUtil.colour(sender, "&5Total Plays: &e" + map.getPlayCount());
        }

    }

}
