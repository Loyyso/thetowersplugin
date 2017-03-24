package fr.loyso.ttplugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.List;

public class TeamListener implements Listener {
    //Team lists
    public List<String> teamRed = new ArrayList<String>();
    public List<String> teamBlue = new ArrayList<String>();

    @EventHandler
    public void joinTeam(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        String playerString = player.toString();

        Location playerLocation = event.getPlayer().getLocation();
        //----------Join team red----------
        Location redJoinLocation = new Location(Bukkit.getWorld("currentGame"),8, 65, 1024);
        if (playerLocation == redJoinLocation) {
            if (!teamRed.isEmpty()) {
                if (teamRed.contains(playerString)) {
                    //blablabla
                }
            } else {
                //blablabla
            }
        }
        //----------Join team blue----------
        Location blueJoinLocation = new Location(Bukkit.getWorld("currentGame"), -8, 65, 1024);
        if (playerLocation == blueJoinLocation) {
            if (!teamBlue.isEmpty()) {
                if (teamBlue.contains(playerString)) {
                    //blablabla
                }
            } else {
                //blablabla
            }
        }
    }
}
