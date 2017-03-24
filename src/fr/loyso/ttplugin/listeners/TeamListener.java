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
    //Team lists
    //public List<String> teamRed = new ArrayList<String>();
    //public List<String> teamBlue = new ArrayList<String>();

    @EventHandler
    public void joinTeam(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        String playerString = player.toString();

        ScoreboardManager scbManager = Bukkit.getScoreboardManager();
        Scoreboard board = scbManager.getMainScoreboard();
        Team redTeam = board.getTeam("Red");
        Team blueTeam = board.getTeam("Blue");

        Location playerLocation = event.getPlayer().getLocation();
        //----------Join team red----------
        Location redJoinLocation = new Location(Bukkit.getWorld("currentGame"),8, 65, 1024);
        Location redHub = new Location(Bukkit.getWorld("currentGame"), 29, 64, 992);
        if (playerLocation.equals(redJoinLocation)) {
            player.sendMessage("tedkfsolglods");
            if (!redTeam.getEntries().equals("")) {
                if (!redTeam.hasEntry(playerString)) {
                    //teamRed.add(playerString);
                    redTeam.addEntry(playerString);
                    player.teleport(redHub);
                }
            } else if (!redTeam.hasEntry(playerString)) {
                //teamRed.add(playerString);
                redTeam.addEntry(playerString);
                player.teleport(redHub);
            } else if (redTeam.hasEntry(playerString)) {
                player.sendMessage("You are already in Red team!");
            }
        }
        //----------Join team blue----------
        /*Location blueJoinLocation = new Location(Bukkit.getWorld("currentGame"), -8, 65, 1024);
        Location blueHub = new Location(Bukkit.getWorld("currentGame"), -39, 64, 62);
        if (playerLocation.equals(blueJoinLocation)) {
            if (!teamBlue.isEmpty()) {
                if (!teamBlue.contains(playerString)) {
                    //teamBlue.add(playerString);
                    player.teleport(blueHub);
                }
            } else if (!teamBlue.contains(playerString)) {
                //teamBlue.add(playerString);
                player.teleport(blueHub);
            } else if (teamBlue.contains(playerString)) {
                player.sendMessage("You are already in Blue team!");
            }
        }*/
    }
}
