package org.mcau.robotoraccoon.fridaynightgames.utility;

import org.bukkit.entity.Player;
import org.mcau.robotoraccoon.fridaynightgames.mCommands;
import org.mcau.robotoraccoon.fridaynightgames.mMain;

public class uMoney {

    public static void awardPrizeMoney() {

        if( mMain.econ == null ) {
            return;
        }

        // Maybe add config option to select one of three modes.

        Double amount = mMain.fngPlayedGames.size() * 1.0;

        for( Player player : uPlayerList.getPlayers() ) {
            mMain.econ.depositPlayer( player, amount );
            player.sendMessage( mCommands.getPrefix() + "An extra $" + amount + " has been added to your account for playing in FNG!" );
        }

    }

}
