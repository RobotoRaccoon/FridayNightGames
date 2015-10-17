package org.mcau.robotoraccoon.fridaynightgames.command.subCommands;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;
import org.mcau.robotoraccoon.fridaynightgames.Main;
import org.mcau.robotoraccoon.fridaynightgames.command.SubCommand;
import org.mcau.robotoraccoon.fridaynightgames.games.MinigameMap;
import org.mcau.robotoraccoon.fridaynightgames.games.MinigameType;
import org.mcau.robotoraccoon.fridaynightgames.utility.LangUtil;
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

                // Display disabled games as red, if the sender is a host.
                if (map.getEnabled())
                    nameList.add(map.getName());
                else if (sender.hasPermission("fng.host"))
                    nameList.add("&c" + map.getName());

                Collections.sort(nameList);
                sortedGames.put(type, nameList);
            }

            // Print the games under their respective heading
            for (MinigameType type : sortedGames.keySet()) {
                MessageUtil.colour(sender, "&5" + type.getName() + ": &e" + StringUtils.join(sortedGames.get(type), "&e, "));
            }
        } else {
            String key = args.get(0).toLowerCase();
            MinigameMap map = Main.getMinigames().get(key);

            if (map == null) {
                MessageUtil.colour(sender, LangUtil.formatErrorKey("minigame.notFound"));
                return;
            }

            MessageUtil.colour(sender, "&7 ===== &5" + map.getName() + "&7 ======");
            MessageUtil.colour(sender, "&5Status: " + (map.getEnabled() ? "&aEnabled" : "&cDisabled") );
            MessageUtil.colour(sender, "&5Type: &e" + map.getType());
            MessageUtil.colour(sender, "&5Join Command: &e" + TypeUtil.getJoinCommand(map.getType()));
            MessageUtil.colour(sender, "&5Quit Command: &e" + TypeUtil.getQuitCommand(map.getType()));
            MessageUtil.colour(sender, "&5Total Plays: &e" + map.getPlayCount());
        }

    }

}
