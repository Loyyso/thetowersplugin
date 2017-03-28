
package fr.loyso.ttplugin.listeners;

import fr.loyso.ttplugin.Plugin;
import fr.loyso.ttplugin.startinggame.Launch;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockListener implements Listener {
    private final Plugin plugin;
    public BlockListener(Plugin instance) {
        plugin = instance;
    }

    public static boolean redReady = false;
    public static boolean blueReady = false;
    public static boolean gameStarted = false;

    @EventHandler
    public void onButtonInteract(PlayerInteractEvent event) throws InterruptedException {
        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            ScoreboardManager scbManager = Bukkit.getScoreboardManager();
            Scoreboard board = scbManager.getMainScoreboard();
            Team redTeam = board.getTeam("Red");
            Team blueTeam = board.getTeam("Blue");

            CommandSender player = event.getPlayer();

            int blockX = event.getClickedBlock().getLocation().getBlockX();
            int blockY = event.getClickedBlock().getLocation().getBlockY();
            int blockZ = event.getClickedBlock().getLocation().getBlockZ();
            int playerX = event.getPlayer().getLocation().getBlockX();
            int playerY = event.getPlayer().getLocation().getBlockY();
            int playerZ = event.getPlayer().getLocation().getBlockZ();

            Material clickedMaterial = event.getClickedBlock().getType();
            Location redReadyBlockRedSide = new Location(Bukkit.getWorld("currentGame"),17 , 66, 998);
            Location blueReadyBlockBlueSide = new Location(Bukkit.getWorld("currentGame"),-17 , 66, 986);
            Location redReadyBlockBlueSide = new Location(Bukkit.getWorld("currentGame"),-17 , 66, 998);
            Location blueReadyBlockRedSide = new Location(Bukkit.getWorld("currentGame"),17 , 66, 986);
            if (clickedMaterial == Material.STONE_BUTTON) {
                //Red side
                if ((redTeam.getEntries().contains(player.getName())) && (blockX == 18) && (blockY == 67) && (blockZ == 998)) {
                    if (redReadyBlockRedSide.getBlock().getType() == Material.REDSTONE_BLOCK) {
                        redReadyBlockRedSide.getBlock().setType(Material.EMERALD_BLOCK);
                        redReadyBlockBlueSide.getBlock().setType(Material.EMERALD_BLOCK);
                        redReady = true;
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            players.sendMessage("[-> Team §cRED§r is ready!");
                        }

                    } else if (redReadyBlockRedSide.getBlock().getType() == Material.EMERALD_BLOCK) {
                        redReadyBlockRedSide.getBlock().setType(Material.REDSTONE_BLOCK);
                        redReadyBlockBlueSide.getBlock().setType(Material.REDSTONE_BLOCK);
                        redReady = false;
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            players.sendMessage("[-> Team §cRED§r isn't ready anymore!");
                        }
                    }
                }
                //Blue side
                else if ((blueTeam.getEntries().contains(player.getName())) && (blockX == -18) && (blockY == 67) && (blockZ == 986)) {
                    if (blueReadyBlockBlueSide.getBlock().getType() == Material.REDSTONE_BLOCK) {
                        blueReadyBlockBlueSide.getBlock().setType(Material.EMERALD_BLOCK);
                        blueReadyBlockRedSide.getBlock().setType(Material.EMERALD_BLOCK);
                        blueReady = true;
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            players.sendMessage("[-> Team §9BLUE§r is ready!");
                        }
                    } else if (blueReadyBlockBlueSide.getBlock().getType() == Material.EMERALD_BLOCK) {
                        blueReadyBlockBlueSide.getBlock().setType(Material.REDSTONE_BLOCK);
                        blueReadyBlockRedSide.getBlock().setType(Material.REDSTONE_BLOCK);
                        blueReady = false;
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            players.sendMessage("[-> Team §9BLUE§r isn't ready anymore!");
                        }
                    }
                }

                final BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
                new Thread(() -> {
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
                        if ((System.currentTimeMillis() == startTime + 1000) && time == 9) {
                            for (Player players : Bukkit.getOnlinePlayers()) {
                                players.sendMessage("[-> Nine!");
                            }
                            time = 8;
                        }
                        if ((System.currentTimeMillis() == startTime + 2000) && time == 8) {
                            for (Player players : Bukkit.getOnlinePlayers()) {
                                players.sendMessage("[-> Eight!");
                            }
                            time = 7;
                        }
                        if ((System.currentTimeMillis() == startTime + 3000) && time == 7) {
                            for (Player players : Bukkit.getOnlinePlayers()) {
                                players.sendMessage("[-> Seven!");
                            }
                            time = 6;
                        }
                        if ((System.currentTimeMillis() == startTime + 4000) && time == 6) {
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
                }).start();
            }
        }
    }
}
