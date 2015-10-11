package org.mcau.robotoraccoon.fridaynightgames;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mcau.robotoraccoon.fridaynightgames.command.*;
import org.mcau.robotoraccoon.fridaynightgames.utility.uMessage;

import java.util.*;

public class Commands implements CommandExecutor {

    private static HashMap<String, SubCommand> commands = new HashMap<>();
    public static void generateCommands() {
        commands.clear();

        commands.put("enabled", new cEnabled());
        commands.put("game", new cGame());
        commands.put("join", new cJoin());
        commands.put("list", new cList());
        commands.put("players", new cPlayers());
        commands.put("quit", new cQuit());
        commands.put("reload", new cReload());
        commands.put("results", new cResults());
        commands.put("type", new cType());
        commands.put("vote", new cVote());
    }

    public static void playersHelp(CommandSender sender) {
        uMessage.colour(sender, " &7===== &5Options &7=====");

        for(Map.Entry<String, SubCommand> entry : commands.entrySet()) {
            String key = entry.getKey();
            SubCommand command = entry.getValue();

            if (sender.hasPermission(command.getPermission()))
                uMessage.colour(sender, command.getDescription());
        }

        /*if (sender.hasPermission(commands.get("enabled").getPermission()))
            uMessage.colour(sender, "&5Enabled &f> &dLook at or change the status.");
        if (sender.hasPermission(commands.get("game").getPermission()))
            uMessage.colour(sender, "&5Game &f> &dStart, End, Add, and Remove games.");
        if (sender.hasPermission(commands.get("join").getPermission()))
            uMessage.colour(sender, "&5Join &f> &dJoin in on the fun.");
        if (sender.hasPermission(commands.get("list").getPermission()))
            uMessage.colour(sender, "&5List &f> &dLook at the available maps.");
        if (sender.hasPermission(commands.get("players").getPermission()))
            uMessage.colour(sender, "&5Players &f> &dSee who's joined.");
        if (sender.hasPermission(commands.get("quit").getPermission()))
            uMessage.colour(sender, "&5Quit &f> &dQuit having fun.");
        if (sender.hasPermission(commands.get("reload").getPermission()))
            uMessage.colour(sender, "&5Reload &f> &dReload the config.");
        if (sender.hasPermission(commands.get("results").getPermission()))
            uMessage.colour(sender, "&5Results &f> &dView the vote results.");
        //if( sender.hasPermission(commands.get("type").getPermission()))
        //    uBroadcast.colour(sender, "&5Type" &f> &dList, Add, and Remove types.");
        if (sender.hasPermission(commands.get("vote").getPermission()))
            uMessage.colour(sender, "&5Vote &f> &dVote for a map.");*/
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("fng") || cmd.getName().equalsIgnoreCase("fridaynightgames")) {

            SubCommand command;
            List<String> argsList = new ArrayList<>(Arrays.asList(args));

            // No sub-command supplied.
            if (argsList.size() == 0) {
                playersHelp(sender);
                return true;
            }

            // Check for valid sub-command.
            String key = argsList.get(0).toLowerCase();
            if (!commands.containsKey(key)) {
                playersHelp(sender);
                return true;
            }

            // Get command from the HashMap.
            command = commands.get(key);

            // If command does not support console usage
            if (!(sender instanceof Player) && !command.isConsoleAllowed()) {
                sender.sendMessage(uMessage.getNoConsole());
                return true;
            }

            // No permission
            if (!sender.hasPermission(command.getPermission())) {
                uMessage.colour(sender, uMessage.getDenied());
                return true;
            }

            // Remove first argument from the list before calling run.
            argsList.remove(0);
            command.run(sender, argsList);
        }

        return true;
    }

}
