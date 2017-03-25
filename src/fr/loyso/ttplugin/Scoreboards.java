package fr.loyso.ttplugin;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.awt.*;

import static java.awt.SystemColor.text;

public class Scoreboards {
    public static void setTeams() {
        ScoreboardManager scbManager = Bukkit.getScoreboardManager();
        Scoreboard board = scbManager.getMainScoreboard();
        Team redTeam = board.registerNewTeam("Red");
        Team blueTeam = board.registerNewTeam("Blue");
        redTeam.setPrefix("§c");
        redTeam.setSuffix("§r");
        blueTeam.setPrefix("§9");
        blueTeam.setSuffix("§r");
        redTeam.setAllowFriendlyFire(false);
        blueTeam.setAllowFriendlyFire(false);
    }
}
