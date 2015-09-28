package org.mcau.robotoraccoon.fridaynightgames.command;

import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class SubCommand {

    public boolean isConsoleAllowed() {
        return true;
    }

    public int getMinArgs() {
        return 0;
    }

    public abstract String getPermission();

    public abstract String getUsage();
    public abstract String getDescription();
    public abstract void run(CommandSender sender, List<String> args);

}
