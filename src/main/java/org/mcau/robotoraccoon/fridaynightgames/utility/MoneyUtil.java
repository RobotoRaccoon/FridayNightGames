package org.mcau.robotoraccoon.fridaynightgames.utility;

import org.bukkit.entity.Player;
import org.mcau.robotoraccoon.fridaynightgames.Main;

public class MoneyUtil {

    public static void awardPrizeMoney() {

        if (Main.getEcon() == null) {
            return;
        }

        // Maybe add config option to select one of three modes.

        Double amount = Main.getPlayedGames().size() * 1.0;

        for (Player player : ListUtil.getPlayers()) {
            Main.getEcon().depositPlayer(player, amount);
            MessageUtil.colour(player, MessageUtil.getPrefix() + "An extra $" + amount + " has been added to your account for playing in FNG!");
        }

    }

}
