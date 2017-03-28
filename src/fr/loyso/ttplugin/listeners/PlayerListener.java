
package fr.loyso.ttplugin.listeners;

import fr.loyso.ttplugin.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

import static fr.loyso.ttplugin.listeners.BlockListener.gameStarted;

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
        Location spawn = new Location(Bukkit.getWorld("currentGame"), 0, 64, 1024);
        if (!wasConnected.isEmpty()) {
            if (!wasConnected.contains(playerString)) {
                System.out.println("[INFO] The player who just connected was already connected before, teleporting him to spawn.");
                wasConnected.add(playerString);
                event.getPlayer().teleport(spawn);
            }
        } else if (!wasConnected.contains(playerString)) {
            System.out.println("[INFO] The player who just connected was already connected before, teleporting him to spawn.");
            wasConnected.add(playerString);
            event.getPlayer().teleport(spawn);
            event.getPlayer().setGameMode(GameMode.ADVENTURE);
        } else if (wasConnected.contains(playerString)) {
            System.out.println("[INFO] The player who just connected has disconnected during the current game. He won't be teleported to spawn.");
            event.getPlayer().setGameMode(GameMode.SURVIVAL);
        }
        if (!gameStarted) {
            for (Player players : Bukkit.getOnlinePlayers()) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 72000, 1, true, false), true);
                player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 72000, 1, true, false), true);
            }
        }
    }
}