package fr.loyso.ttplugin.startinggame;

import fr.loyso.ttplugin.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static fr.loyso.ttplugin.listeners.ReadyButtonListener.gameStarted;
import static fr.loyso.ttplugin.listeners.ReadyButtonListener.redReady;
import static fr.loyso.ttplugin.listeners.ReadyButtonListener.blueReady;

public class Countdown implements Runnable {
    private final Plugin plugin;
    public Countdown(Plugin instance) {
        plugin = instance;
    }
    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        boolean timerStarted = false;
        int time = 10;
        while (redReady && blueReady && !gameStarted) {
            if ((System.currentTimeMillis() == startTime) && time == 10) {
                timerStarted = true;
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage("[-> Both teams are ready. Starting game in ten seconds!");
                    players.sendMessage("\n[-> §aTen!§r");
                    Bukkit.getWorld("currentGame").playSound(new Location(Bukkit.getWorld("currentGame"), 0, 0, 0), Sound.BLOCK_NOTE_PLING, 100, 1);
                }
                time = 9;
            }
            if ((System.currentTimeMillis() == startTime + 1000) && time == 9) {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage("[-> §aNine!§r");
                    Bukkit.getWorld("currentGame").playSound(new Location(Bukkit.getWorld("currentGame"), 0, 0, 0), Sound.BLOCK_NOTE_PLING, 100, 1);
                }
                time = 8;
            }
            if ((System.currentTimeMillis() == startTime + 2000) && time == 8) {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage("[-> §aEight!§r");
                    Bukkit.getWorld("currentGame").playSound(new Location(Bukkit.getWorld("currentGame"), 0, 0, 0), Sound.BLOCK_NOTE_PLING, 100, 1);
                }
                time = 7;
            }
            if ((System.currentTimeMillis() == startTime + 3000) && time == 7) {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage("[-> §6Seven!§r");
                    Bukkit.getWorld("currentGame").playSound(new Location(Bukkit.getWorld("currentGame"), 0, 0, 0), Sound.BLOCK_NOTE_PLING, 100, 1);
                }
                time = 6;
            }
            if ((System.currentTimeMillis() == startTime + 4000) && time == 6) {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage("[-> §6Six!§r");
                    Bukkit.getWorld("currentGame").playSound(new Location(Bukkit.getWorld("currentGame"), 0, 0, 0), Sound.BLOCK_NOTE_PLING, 100, 1);
                }
                time = 5;
            }
            if ((System.currentTimeMillis() == startTime + 5000) && time == 5) {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage("[-> §6Five!§r");
                    Bukkit.getWorld("currentGame").playSound(new Location(Bukkit.getWorld("currentGame"), 0, 0, 0), Sound.BLOCK_NOTE_PLING, 100, 1);
                }
                time = 4;
            }
            if ((System.currentTimeMillis() == startTime + 6000) && time == 4) {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage("[-> §cFour!§r");
                    Bukkit.getWorld("currentGame").playSound(new Location(Bukkit.getWorld("currentGame"), 0, 0, 0), Sound.BLOCK_NOTE_PLING, 100, 1);
                }
                time = 3;
            }
            if ((System.currentTimeMillis() == startTime + 7000) && time == 3) {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage("[-> §cThree!§r");
                    Bukkit.getWorld("currentGame").playSound(new Location(Bukkit.getWorld("currentGame"), 0, 0, 0), Sound.BLOCK_NOTE_PLING, 100, 1);
                }
                time = 2;
            }
            if ((System.currentTimeMillis() == startTime + 8000) && time == 2) {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage("[-> §cTwo!§r");
                    Bukkit.getWorld("currentGame").playSound(new Location(Bukkit.getWorld("currentGame"), 0, 0, 0), Sound.BLOCK_NOTE_PLING, 100, 1);
                }
                time = 1;
            }
            if ((System.currentTimeMillis() == startTime + 9000) && time == 1) {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage("[-> §eOne!§r");
                    Bukkit.getWorld("currentGame").playSound(new Location(Bukkit.getWorld("currentGame"), 0, 0, 0), Sound.BLOCK_NOTE_PLING, 100, 1);
                }
                time = 0;
            }
            if ((System.currentTimeMillis() == startTime + 10000) && time == 0) {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage("\n[-> §6§lStarting game!§r");
                    Bukkit.getWorld("currentGame").playSound(new Location(Bukkit.getWorld("currentGame"), 0, 0, 0), Sound.ENTITY_PLAYER_LEVELUP, 100, 1);
                }
                gameStarted = true;
                new BukkitRunnable() {
                    public void run() {
                        Launch launch = new Launch();
                        launch.startGame();
                    }
                }.runTask(plugin);
            }
        }
        if ((!redReady && !blueReady) && timerStarted && !gameStarted || (redReady != blueReady) && timerStarted && !gameStarted) {
            for (Player players : Bukkit.getOnlinePlayers()) { players.sendMessage("\n§4[-> One of the teams isn't ready anymore. Cancelling countdown...§r"); }
        }
    }
}
