package org.mcau.robotoraccoon.fridaynightgames.utility;

import org.bukkit.entity.Player;
import org.mcau.robotoraccoon.fridaynightgames.Commands;
import org.mcau.robotoraccoon.fridaynightgames.Main;

public class uMoney {

    public static void awardPrizeMoney() {

        if (Main.getEcon() == null) {
            return;
        }

        // Maybe add config option to select one of three modes.

        Double amount = Main.getPlayedGames().size() * 1.0;

        for (Player player : uPlayerList.getPlayers()) {
            Main.getEcon().depositPlayer(player, amount);
            uBroadcast.colour(player, Commands.getPrefix() + "An extra $" + amount + " has been added to your account for playing in FNG!");
        }

    }

}
