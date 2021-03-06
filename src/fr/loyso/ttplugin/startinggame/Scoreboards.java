package fr.loyso.ttplugin.startinggame;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.*;

public class Scoreboards {
    public static void setTeams() {
        ScoreboardManager scbManager = Bukkit.getScoreboardManager();
        Scoreboard board = scbManager.getMainScoreboard();
        Team redTeam = board.registerNewTeam("Red");
        Team blueTeam = board.registerNewTeam("Blue");
        Team observerTeam = board.registerNewTeam("Observers");

        redTeam.setPrefix("§c");
        blueTeam.setPrefix("§9");

        redTeam.setSuffix("§r");
        blueTeam.setSuffix("§r");

        redTeam.setAllowFriendlyFire(false);
        blueTeam.setAllowFriendlyFire(false);

        redTeam.setCanSeeFriendlyInvisibles(true);
        blueTeam.setCanSeeFriendlyInvisibles(true);
    }
}