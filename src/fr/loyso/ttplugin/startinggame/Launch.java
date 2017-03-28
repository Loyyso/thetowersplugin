package fr.loyso.ttplugin.startinggame;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;
import java.util.HashSet;
import static fr.loyso.ttplugin.listeners.BlockListener.gameStarted;

public class Launch {
    public void startGame() {
        ScoreboardManager scbManager = Bukkit.getScoreboardManager();
        Scoreboard board = scbManager.getMainScoreboard();
        Team redTeam = board.getTeam("Red");
        Team blueTeam = board.getTeam("Blue");

        if (gameStarted) {
            for (Player player : Bukkit.getOnlinePlayers()){
                player.sendMessage("[-> The game has started!");
            }

            //Clear potion effects
            for (Player player : Bukkit.getOnlinePlayers()) {
                for (PotionEffect effect : player.getActivePotionEffects()) {
                    player.removePotionEffect(effect.getType());
                }
            }

            //Red team
            Location redSpawn = new Location(Bukkit.getServer().getWorld("currentGame"), 84, 192, 1152);
            int redSpawnX = redSpawn.getBlockX();
            int redSpawnY = redSpawn.getBlockY();
            int redSpawnZ = redSpawn.getBlockZ();

            HashSet<Player> redPlayersList = new HashSet<>(redTeam.getEntries().size());
            redTeam.getEntries().forEach(i -> redPlayersList.add(Bukkit.getPlayer(i)));

            for (Player player : redPlayersList) {
                player.teleport(redSpawn);
                player.setSaturation(0);
                player.setGameMode(GameMode.SURVIVAL);
            }

            //Blue team
            Location blueSpawn = new Location(Bukkit.getServer().getWorld("currentGame"), -84, 192, 1152);
            int blueSpawnX = redSpawn.getBlockX();
            int blueSpawnY = redSpawn.getBlockY();
            int blueSpawnZ = redSpawn.getBlockZ();

            HashSet<Player> bluePlayersList = new HashSet<>(blueTeam.getEntries().size());
            blueTeam.getEntries().forEach(i -> bluePlayersList.add(Bukkit.getPlayer(i)));

            for (Player player : bluePlayersList) {
                player.teleport(blueSpawn);
                player.setSaturation(0);
                player.setGameMode(GameMode.SURVIVAL);
            }
        }
    }
}