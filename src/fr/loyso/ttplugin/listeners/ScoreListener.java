package fr.loyso.ttplugin.listeners;

import fr.loyso.ttplugin.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scoreboard.*;

import static fr.loyso.ttplugin.listeners.ReadyButtonListener.gameStarted;
import static fr.loyso.ttplugin.startinggame.Launch.redSpawn;
import static fr.loyso.ttplugin.startinggame.Launch.blueSpawn;

public class ScoreListener implements Listener {
    private final Plugin plugin;
    public ScoreListener(Plugin instance) {
        plugin = instance;
    }

    @EventHandler
    public void onPlayerScoring(PlayerMoveEvent event) {
        if (gameStarted) {
            //bhbdfgfhfyigihko
            ScoreboardManager scbManager = Bukkit.getScoreboardManager();
            Scoreboard board = scbManager.getMainScoreboard();
            Team redTeam = board.getTeam("Red");
            Team blueTeam = board.getTeam("Blue");
            Objective scoreObj = board.getObjective("Score");
            Score redScore = scoreObj.getScore("§c§oRED§r");
            Score blueScore = scoreObj.getScore("§9§oBLUE§r");

            Player player = event.getPlayer();
            Location playerLocation = player.getLocation();
            int playerX = playerLocation.getBlockX();
            int playerY = playerLocation.getBlockY();
            int playerZ = playerLocation.getBlockZ();

            //For red team scoring in blue home
            if (redTeam.getEntries().contains(player.getName())) {
                if ((playerX <= -83 && playerX >= -85) && (playerY < 201 && playerY >= 200) && (playerZ <= 1153 && playerZ >= 1151)) {
                    player.teleport(redSpawn);
                    redScore.setScore(redScore.getScore() + 1);
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        players.sendMessage("[-> §c§oRED§r §6scored a point!§r");
                    }
                    Bukkit.getWorld("currentGame").playSound(new Location(Bukkit.getWorld("currentGame"), 0, 0, 0), Sound.ENTITY_PLAYER_LEVELUP, 100, 1);
                }
            }
            //For blue team scoring in red home
            if (blueTeam.getEntries().contains(player.getName())) {
                if ((playerX <= 85 && playerX >= 83) && (playerY < 201 && playerY >= 200) && (playerZ <= 1153 && playerZ >= 1151)) {
                    player.teleport(blueSpawn);
                    redScore.setScore(blueScore.getScore() + 1);
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        players.sendMessage("[-> §9BLUE§r §6scored a point!§r");
                    }
                    Bukkit.getWorld("currentGame").playSound(new Location(Bukkit.getWorld("currentGame"), 0, 0, 0), Sound.ENTITY_PLAYER_LEVELUP, 100, 1);
                }
            }
        }
    }
}
