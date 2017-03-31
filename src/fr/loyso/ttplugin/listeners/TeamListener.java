package fr.loyso.ttplugin.listeners;

import fr.loyso.ttplugin.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import static fr.loyso.ttplugin.listeners.ReadyButtonListener.blueReady;
import static fr.loyso.ttplugin.listeners.ReadyButtonListener.gameStarted;
import static fr.loyso.ttplugin.listeners.ReadyButtonListener.redReady;

public class TeamListener implements Listener {
    private final Plugin plugin;
    public TeamListener(Plugin instance) {
        plugin = instance;
    }

    float volume = 100;
    float pitch = (float) 0.5;

    @EventHandler
    public void joinTeam(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        String playerString = player.getName();

        ScoreboardManager scbManager = Bukkit.getScoreboardManager();
        Scoreboard board = scbManager.getMainScoreboard();
        Team redTeam = board.getTeam("Red");
        Team blueTeam = board.getTeam("Blue");
        Team observerTeam = board.getTeam("Observers");

        Location spawn = new Location(Bukkit.getWorld("currentGame"), 0, 64, 1024);
        Location playerLocation = event.getPlayer().getLocation();
        int playerX = ((int) playerLocation.getBlockX());
        int playerY = ((int) playerLocation.getBlockY());
        int playerZ = ((int) playerLocation.getBlockZ());
        //----------Join team red----------
        Location redJoinLocation = new Location(Bukkit.getWorld("currentGame"),8, 65, 1024);
        Location redHub = new Location(Bukkit.getWorld("currentGame"), 29, 64, 992);
        if ((playerX == 8) && (playerY == 65) && (playerZ == 1024) && !gameStarted && !redReady) {
            if (!redTeam.hasEntry(playerString)) {
                redTeam.addEntry(playerString);
                player.teleport(redHub);
                player.playSound(new Location(Bukkit.getWorld("currentGame"), 0, 0, 0), Sound.BLOCK_NOTE_HARP, 100, 2);
                player.sendMessage("[-> You joined §cRED§r team!");
            } else if (redTeam.getEntries().contains(playerString)) {
                player.playSound(new Location(Bukkit.getWorld("currentGame"), 0, 0, 0), Sound.BLOCK_NOTE_HARP, 100, 2);
                player.sendMessage("[-> You are already in §cRED§r team!");
                player.teleport(redHub);
            }
        } else if ((playerX == 8) && (playerY == 65) && (playerZ == 1024) && redReady && !gameStarted) {
            player.playSound(new Location(Bukkit.getWorld("currentGame"), 0, 0, 0), Sound.BLOCK_NOTE_BASS, volume, pitch);
            player.sendMessage("[-> §cRED§r team is ready, you can't join them!");
        } else if ((playerX == 8) && (playerY == 65) && (playerZ == 1024) && gameStarted) {
            player.playSound(new Location(Bukkit.getWorld("currentGame"), 0, 0, 0), Sound.BLOCK_NOTE_BASS, volume, pitch);
            player.sendMessage("[-> The game has started, you can't join a team!");
        }
        //----------Join team blue----------
        Location blueJoinLocation = new Location(Bukkit.getWorld("currentGame"), -8, 65, 1024);
        Location blueHub = new Location(Bukkit.getWorld("currentGame"), -28, 64, 992);
        if ((playerX == -8) && (playerY == 65) && (playerZ == 1024) && !gameStarted && !blueReady) {
            if (!blueTeam.hasEntry(playerString)) {
                blueTeam.addEntry(playerString);
                player.teleport(blueHub);
                player.playSound(new Location(Bukkit.getWorld("currentGame"), 0, 0, 0), Sound.BLOCK_NOTE_HARP, 100, 2);
                player.sendMessage("[-> You joined §9BLUE§r team!");
            } else if (blueTeam.getEntries().contains(playerString)){
                player.playSound(new Location(Bukkit.getWorld("currentGame"), 0, 0, 0), Sound.BLOCK_NOTE_HARP, 100, 2);
                player.sendMessage("[-> You are already in §9BLUE§r team!");
                player.teleport(blueHub);
            }
        } else if ((playerX == -8) && (playerY == 65) && (playerZ == 1024) && blueReady && !gameStarted) {
            player.playSound(new Location(Bukkit.getWorld("currentGame"), 0, 0, 0), Sound.BLOCK_NOTE_BASS, volume, pitch);
            player.sendMessage("[-> §9BLUE§r team is ready, you can't join them!");
        } else if ((playerX == -8) && (playerY == 65) && (playerZ == 1024) && gameStarted) {
            player.playSound(new Location(Bukkit.getWorld("currentGame"), 0, 0, 0), Sound.BLOCK_NOTE_BASS, volume, pitch);
            player.sendMessage("[-> The game has started, you can't join a team!");
        }

        //----------Leave red team----------
        Location redLeaveLocation = new Location(Bukkit.getWorld("currentGame"),16, 65, 992);
        if ((playerX == 16) && (playerY == 65) && (playerZ == 992) && !gameStarted && !redReady) {
            if (!redTeam.hasEntry(playerString)) {
                player.teleport(spawn);
            } else if (redTeam.getEntries().contains(playerString)) {
                redTeam.removeEntry(playerString);
                player.playSound(new Location(Bukkit.getWorld("currentGame"), 0, 0, 0), Sound.BLOCK_NOTE_HARP, 100, 1);
                player.sendMessage("[-> You left §cRED§r team!");
                player.teleport(spawn);
            }
        } else if ((playerX == 16) && (playerY == 65) && (playerZ == 992) && redReady && !gameStarted) {
            player.playSound(new Location(Bukkit.getWorld("currentGame"), 0, 0, 0), Sound.BLOCK_NOTE_BASS, volume, pitch);
            player.sendMessage("[-> §cRED§r team is ready, you can't leave them!");
        }
        //----------Leave blue team----------
        Location blueLeaveLocation = new Location(Bukkit.getWorld("currentGame"), -16, 65, 992);
        if ((playerX == -16) && (playerY == 65) && (playerZ == 992) && !gameStarted && !blueReady) {
            if (!blueTeam.hasEntry(playerString)) {
                player.teleport(spawn);
            } else if (blueTeam.getEntries().contains(playerString)){
                blueTeam.removeEntry(playerString);
                player.playSound(new Location(Bukkit.getWorld("currentGame"), 0, 0, 0), Sound.BLOCK_NOTE_HARP, 100, 1);
                player.sendMessage("[-> You left §9BLUE§r team!");
                player.teleport(spawn);
            }
        } else if ((playerX == -16) && (playerY == 65) && (playerZ == 992) && blueReady && !gameStarted) {
            player.playSound(new Location(Bukkit.getWorld("currentGame"), 0, 0, 0), Sound.BLOCK_NOTE_BASS, volume, pitch);
            player.sendMessage("[-> §9BLUE§r team is ready, you can't leave them!");
        }

        //----------Join observer----------
        if ((playerX == 0) && (playerY == 65) && (playerZ == 1032)) {
            if (!observerTeam.hasEntry(playerString)) {
                observerTeam.addEntry(playerString);
                player.setGameMode(GameMode.SPECTATOR);
                player.teleport(new Location(Bukkit.getWorld("currentGame"), 0, 210, 1152));
                player.playSound(new Location(Bukkit.getWorld("currentGame"), 0, 0, 0), Sound.BLOCK_NOTE_HARP, 100, 2);
                player.sendMessage("[-> You joined §aOBSERVERS§r team!");
                player.sendMessage("[-> Use §6§o/leaveobservers§r to leave the observer team.");
            } else if (observerTeam.getEntries().contains(playerString)){
                player.playSound(new Location(Bukkit.getWorld("currentGame"), 0, 0, 0), Sound.BLOCK_NOTE_HARP, 100, 2);
                player.sendMessage("[-> You are already in §aOBSERVERS§r team!");
                player.sendMessage("[-> Use §6§o/leaveobservers§r to leave the observer team.");
                player.teleport(new Location(Bukkit.getWorld("currentGame"), 0, 210, 1152));
            }
        }
    }
}
