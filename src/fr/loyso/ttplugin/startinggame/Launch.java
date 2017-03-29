package fr.loyso.ttplugin.startinggame;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.*;

import java.util.HashSet;
import static fr.loyso.ttplugin.listeners.ReadyButtonListener.gameStarted;

public class Launch {
    public static Location redSpawn = new Location(Bukkit.getServer().getWorld("currentGame"), 84, 192, 1152);
    public static Location blueSpawn = new Location(Bukkit.getServer().getWorld("currentGame"), -84, 192, 1152);

    public void startGame() {
        ScoreboardManager scbManager = Bukkit.getScoreboardManager();
        Scoreboard board = scbManager.getMainScoreboard();
        Team redTeam = board.getTeam("Red");
        Team blueTeam = board.getTeam("Blue");

        if (gameStarted) {
            for (Player player : Bukkit.getOnlinePlayers()){
                player.sendMessage("[-> The game has started!");
            }

            //Change potion effects
            for (Player player : Bukkit.getOnlinePlayers()) {
                for (PotionEffect effect : player.getActivePotionEffects()) {
                    player.removePotionEffect(effect.getType());
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20, 0, false, true), true);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 0, false, true), true);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 0, false, true), true);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 200, 0, false, true), true);
                }
            }

            //Set scoreboard
            Objective scoreObj = board.registerNewObjective("Score", "dummy");
            scoreObj.setDisplayName("§6§lSCORE§r");
            scoreObj.setDisplaySlot(DisplaySlot.SIDEBAR);
            Score redScore = scoreObj.getScore("§c§oRED§r");
            Score blueScore = scoreObj.getScore("§9§oBLUE§r");
            redScore.setScore(0);
            blueScore.setScore(0);

            //Red team
            HashSet<Player> redPlayersList = new HashSet<>(redTeam.getEntries().size());
            redTeam.getEntries().forEach(i -> redPlayersList.add(Bukkit.getPlayer(i)));

            for (Player player : redPlayersList) {
                player.teleport(redSpawn);
                player.setSaturation(10);
                player.setGameMode(GameMode.SURVIVAL);
            }
            //Blue team
            HashSet<Player> bluePlayersList = new HashSet<>(blueTeam.getEntries().size());
            blueTeam.getEntries().forEach(i -> bluePlayersList.add(Bukkit.getPlayer(i)));

            for (Player player : bluePlayersList) {
                player.teleport(blueSpawn);
                player.setSaturation(10);
                player.setGameMode(GameMode.SURVIVAL);
            }
        }
    }
}