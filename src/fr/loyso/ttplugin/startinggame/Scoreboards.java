package fr.loyso.ttplugin.startinggame;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class Scoreboards {
    public static void setTeams() {
        ScoreboardManager scbManager = Bukkit.getScoreboardManager();
        Scoreboard board = scbManager.getMainScoreboard();
        Team redTeam = board.registerNewTeam("Red");
        Team blueTeam = board.registerNewTeam("Blue");

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
