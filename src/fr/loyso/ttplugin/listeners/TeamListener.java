package fr.loyso.ttplugin.listeners;

import fr.loyso.ttplugin.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class TeamListener implements Listener {
    private final Plugin plugin;
    public TeamListener(Plugin instance) {
        plugin = instance;
    }

    @EventHandler
    public void joinTeam(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        String playerString = player.getName();

        ScoreboardManager scbManager = Bukkit.getScoreboardManager();
        Scoreboard board = scbManager.getMainScoreboard();
        Team redTeam = board.getTeam("Red");
        Team blueTeam = board.getTeam("Blue");

        Location spawn = new Location(Bukkit.getWorld("currentGame"), 0, 64, 1024);
        Location playerLocation = event.getPlayer().getLocation();
        int playerX = ((int) playerLocation.getBlockX());
        int playerY = ((int) playerLocation.getBlockY());
        int playerZ = ((int) playerLocation.getBlockZ());
        //----------Join team red----------
        Location redJoinLocation = new Location(Bukkit.getWorld("currentGame"),8, 65, 1024);
        Location redHub = new Location(Bukkit.getWorld("currentGame"), 29, 64, 992);

        if ((playerX == 8) && (playerY == 65) && (playerZ == 1024)) {
            if (!redTeam.hasEntry(playerString)) {
                redTeam.addEntry(playerString);
                player.teleport(redHub);
                player.sendMessage("You joined §cRED§r team!");
            } else if (redTeam.getEntries().contains(playerString)) {
                player.sendMessage("You are already in §cRED§r team!");
                player.teleport(redHub);
            }
        }
        //----------Join team blue----------
        Location blueJoinLocation = new Location(Bukkit.getWorld("currentGame"), -8, 65, 1024);
        Location blueHub = new Location(Bukkit.getWorld("currentGame"), -28, 64, 992);
        if ((playerX == -8) && (playerY == 65) && (playerZ == 1024)) {
            if (!blueTeam.hasEntry(playerString)) {
                blueTeam.addEntry(playerString);
                player.teleport(blueHub);
                player.sendMessage("You joined §9BLUE§r team!");
            } else if (blueTeam.getEntries().contains(playerString)){
                player.sendMessage("You are already in §9BLUE§r team!");
                player.teleport(blueHub);
            }
        }

        //----------Leave red team----------
        Location redLeaveLocation = new Location(Bukkit.getWorld("currentGame"),16, 65, 992);

        if ((playerX == 16) && (playerY == 65) && (playerZ == 992)) {
            if (!redTeam.hasEntry(playerString)) {
                player.teleport(spawn);
            } else if (redTeam.getEntries().contains(playerString)) {
                redTeam.removeEntry(playerString);
                player.sendMessage("You left §cRED§r team!");
                player.teleport(spawn);
            }
        }
        //----------Leave blue team----------
        Location blueLeaveLocation = new Location(Bukkit.getWorld("currentGame"), -16, 65, 992);
        if ((playerX == -16) && (playerY == 65) && (playerZ == 992)) {
            if (!blueTeam.hasEntry(playerString)) {
                player.teleport(spawn);
            } else if (blueTeam.getEntries().contains(playerString)){
                blueTeam.removeEntry(playerString);
                player.sendMessage("You left §9BLUE§r team!");
                player.teleport(spawn);
            }
        }
    }
}
