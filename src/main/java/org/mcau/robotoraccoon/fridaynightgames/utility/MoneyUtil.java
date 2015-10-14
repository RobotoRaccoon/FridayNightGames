package org.mcau.robotoraccoon.fridaynightgames.utility;

import org.bukkit.entity.Player;
import org.mcau.robotoraccoon.fridaynightgames.Config;
import org.mcau.robotoraccoon.fridaynightgames.Main;

public class MoneyUtil {

    public static void awardPrizeMoney() {

        if (Main.getEcon() == null || !Config.getConfig().getBoolean("economy.enabled")) {
            return;
        }

        Double amount = getConstantPay();
        amount += getLinearMultiplier() * Main.getPlayedGames().size();

        // Round payment to a whole cent.
        amount = Math.round(100.0*amount) / 100.0;

        for (Player player : PlayerListUtil.getPlayers()) {
            Main.getEcon().depositPlayer(player, amount);
            MessageUtil.colour(player, MessageUtil.getPrefix() + "You have been given an extra &5$" + amount + "&e for playing in FNG!");
        }

    }

    private static Double getConstantPay() {
        return Config.getConfig().getDouble("economy.constantPay");
    }

    private static Double getLinearMultiplier() {
        return Config.getConfig().getDouble("economy.linearMultiplier");
    }

}
