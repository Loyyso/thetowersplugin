package fr.loyso.ttplugin.startinggame;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static fr.loyso.ttplugin.listeners.BlockListener.gameStarted;

public class Launch {
    public void startGame() {
        ScoreboardManager scbManager = Bukkit.getScoreboardManager();
        Scoreboard board = scbManager.getMainScoreboard();
        Team redTeam = board.getTeam("Red");
        Team blueTeam = board.getTeam("Blue");

        if (gameStarted) {
            //Red team
            Location redSpawn = new Location(Bukkit.getServer().getWorld("currentGame"), 84, 192, 1152);
            int redSpawnX = redSpawn.getBlockX();
            int redSpawnY = redSpawn.getBlockY();
            int redSpawnZ = redSpawn.getBlockZ();

            HashSet<Player> redPlayersList = new HashSet<>(redTeam.getEntries().size());
            redTeam.getEntries().forEach(i -> redPlayersList.add(Bukkit.getPlayer(i)));

            for (Player player : redPlayersList) {
                player.sendMessage("TEFDSLFSIGHJDSFIOUGDSHJFGLKJSDJKL"); //DEBUG
                player.teleport(redSpawn);
            }
            //Blue team
            Location blueSpawn = new Location(Bukkit.getServer().getWorld("currentGame"), -84, 192, 1152);
            int blueSpawnX = redSpawn.getBlockX();
            int blueSpawnY = redSpawn.getBlockY();
            int blueSpawnZ = redSpawn.getBlockZ();

            HashSet<Player> bluePlayersList = new HashSet<>(blueTeam.getEntries().size());
            blueTeam.getEntries().forEach(i -> bluePlayersList.add(Bukkit.getPlayer(i)));

            for (Player player : bluePlayersList) {
                player.sendMessage("TEFDSLFSIGHJDSFIOUGDSHJFGLKJSDJKL"); //DEBUG
                player.teleport(blueSpawn);
            }
        }
    }
}