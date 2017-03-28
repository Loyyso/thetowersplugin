package fr.loyso.ttplugin.startinggame;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import static fr.loyso.ttplugin.listeners.BlockListener.blueReady;
import static fr.loyso.ttplugin.listeners.BlockListener.gameStarted;
import static fr.loyso.ttplugin.listeners.BlockListener.redReady;

public class Countdown implements Runnable{
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
                    players.sendMessage("[-> Ten!");
                }
                time = 9;
            }
            if ((System.currentTimeMillis() == startTime + 5000) && time == 9) {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage("[-> Nine!");
                }
                time = 8;
            }
            if ((System.currentTimeMillis() == startTime + 5000) && time == 8) {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage("[-> Eight!");
                }
                time = 7;
            }
            if ((System.currentTimeMillis() == startTime + 5000) && time == 7) {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage("[-> Seven!");
                }
                time = 6;
            }
            if ((System.currentTimeMillis() == startTime + 5000) && time == 6) {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage("[-> Six!");
                }
                time = 5;
            }
            if ((System.currentTimeMillis() == startTime + 5000) && time == 5) {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage("[-> Five!");
                }
                time = 4;
            }
            if ((System.currentTimeMillis() == startTime + 6000) && time == 4) {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage("[-> Four!");
                }
                time = 3;
            }
            if ((System.currentTimeMillis() == startTime + 7000) && time == 3) {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage("[-> Three!");
                }
                time = 2;
            }
            if ((System.currentTimeMillis() == startTime + 8000) && time == 2) {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage("[-> Two!");
                }
                time = 1;
            }
            if ((System.currentTimeMillis() == startTime + 9000) && time == 1) {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage("[-> One!");
                }
                time = 0;
            }
            if ((System.currentTimeMillis() == startTime + 10000) && time == 0) {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage("[-> Starting game!");
                }
                gameStarted = true;
                Launch launch = new Launch();
                launch.startGame();
            }
        }
        if ((!redReady && !blueReady) && timerStarted && !gameStarted || (redReady != blueReady) && timerStarted && !gameStarted) {
            for (Player players : Bukkit.getOnlinePlayers()) { players.sendMessage("[-> One of the teams isn't ready anymore. Cancelling countdown..."); }
        }
    }
}
