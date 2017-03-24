
package fr.loyso.ttplugin.listeners;

import fr.loyso.ttplugin.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class PlayerListener implements Listener {
    private final Plugin plugin;
    public List<String> wasConnected = new ArrayList<String>();

    public PlayerListener(Plugin instance) {
        plugin = instance;
    }

    @EventHandler
    public void onConnection(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        String playerString = player.toString();
        UUID playerUniqueId = player.getUniqueId();
        String playerUniqueIdString = playerUniqueId.toString();
        Location spawn = new Location(Bukkit.getWorld("currentGame"), 0, 64, 1024);
        System.out.println("List of players who were connected during this game : " + wasConnected);
        if (!wasConnected.isEmpty()) {
            if (!wasConnected.contains(playerString)) {
                System.out.println("[INFO] The player who just connected before, teleporting him to spawn.");
                wasConnected.add(playerString);
                event.getPlayer().teleport(spawn);
                System.out.println(wasConnected);
            }
        } else {
            if (!wasConnected.contains(playerString)) {
                System.out.println("[INFO] The player who just connected before, teleporting him to spawn.");
                wasConnected.add(playerString);
                event.getPlayer().teleport(spawn);
                System.out.println(wasConnected);
            } else if (wasConnected.contains(playerString)) {
                System.out.println("[INFO] The player who just connected has disconnected during the current game. He won't be teleported to spawn.");
                System.out.println(wasConnected);
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if(event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            event.getPlayer().sendMessage("Event!");
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        plugin.getLogger().info(event.getPlayer().getName() + " joined.");
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        plugin.getLogger().info(event.getPlayer().getName() + " left.");
    }
}
