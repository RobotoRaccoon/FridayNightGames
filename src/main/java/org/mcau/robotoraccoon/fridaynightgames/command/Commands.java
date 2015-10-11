package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mcau.robotoraccoon.fridaynightgames.command.subCommands.*;
import org.mcau.robotoraccoon.fridaynightgames.utility.MessageUtil;

import java.util.*;

public class Commands implements CommandExecutor {

    private static HashMap<String, SubCommand> commands = new HashMap<>();
    public static void generateCommands() {
        commands.clear();

        commands.put("enabled", new EnabledCommand());
        commands.put("game", new GameCommand());
        commands.put("join", new JoinCommand());
        commands.put("list", new ListCommand());
        commands.put("players", new PlayersCommand());
        commands.put("quit", new QuitCommand());
        commands.put("reload", new ReloadCommand());
        commands.put("results", new ResultsCommand());
        commands.put("type", new TypeCommand());
        commands.put("vote", new VoteCommand());
    }

    public static void playersHelp(CommandSender sender) {
        MessageUtil.colour(sender, " &7===== &5Options &7=====");

        for(Map.Entry<String, SubCommand> entry : commands.entrySet()) {
            SubCommand command = entry.getValue();

            if (sender.hasPermission(command.getPermission()))
                MessageUtil.colour(sender, command.getDescription());
        }
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
                sender.sendMessage(MessageUtil.getNoConsole());
                return true;
            }

            // No permission
            if (!sender.hasPermission(command.getPermission())) {
                MessageUtil.colour(sender, MessageUtil.getDenied());
                return true;
            }

            // Remove first argument from the list before calling run.
            argsList.remove(0);
            command.run(sender, argsList);
        }

        return true;
    }

}
