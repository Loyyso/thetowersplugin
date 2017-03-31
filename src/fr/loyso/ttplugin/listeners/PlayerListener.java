
package fr.loyso.ttplugin.listeners;

import fr.loyso.ttplugin.Plugin;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;

import static fr.loyso.ttplugin.listeners.ReadyButtonListener.gameStarted;
import static fr.loyso.ttplugin.startinggame.Launch.blueSpawn;
import static fr.loyso.ttplugin.startinggame.Launch.redSpawn;

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
                System.out.println("[INFO] The player who just connected wasn't connected before, teleporting him to spawn.");
                wasConnected.add(playerString);
                event.getPlayer().teleport(spawn);
                event.getPlayer().setGameMode(GameMode.ADVENTURE);
            }
        } else if (!wasConnected.contains(playerString)) {
            System.out.println("[INFO] The player who just connected wasn't connected before, teleporting him to spawn.");
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

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        Location spawn = new Location(Bukkit.getWorld("currentGame"), 0, 64, 1024);
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getMainScoreboard();
        Team redTeam = scoreboard.getTeam("Red");
        Team blueTeam = scoreboard.getTeam("Blue");
        if (event.getEntity().getType().equals(EntityType.PLAYER)) {
            Player player = Bukkit.getPlayer(event.getEntity().getName());
            if (redTeam.getEntries().contains(player.getName())) {
                player.getServer().getWorld("currentGame").setSpawnLocation(redSpawn.getBlockX(), redSpawn.getBlockY(), redSpawn.getBlockZ());
            }
            if (blueTeam.getEntries().contains(player.getName())) {
                player.getServer().getWorld("currentGame").setSpawnLocation(blueSpawn.getBlockX(), blueSpawn.getBlockY(), blueSpawn.getBlockZ());
            }
        }
    }
    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Location spawn = new Location(Bukkit.getWorld("currentGame"), 0, 64, 1024);
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getMainScoreboard();
        Team redTeam = scoreboard.getTeam("Red");
        Team blueTeam = scoreboard.getTeam("Blue");
        Player player = event.getPlayer();

        //Red team respawn
        if (redTeam.getEntries().contains(player.getName())) {
            ItemStack redLeatherHelmet = new ItemStack(Material.LEATHER_HELMET, 1);
            LeatherArmorMeta redLeatherHelmetMeta = (LeatherArmorMeta) redLeatherHelmet.getItemMeta();
            redLeatherHelmetMeta.setColor(Color.RED);
            redLeatherHelmet.setItemMeta(redLeatherHelmetMeta);

            ItemStack redLeatherChestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
            LeatherArmorMeta redLeatherChestplateMeta = (LeatherArmorMeta) redLeatherChestplate.getItemMeta();
            redLeatherChestplateMeta.setColor(Color.RED);
            redLeatherChestplate.setItemMeta(redLeatherChestplateMeta);

            ItemStack redLeatherLeggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
            LeatherArmorMeta redLeatherLeggingsMeta = (LeatherArmorMeta) redLeatherLeggings.getItemMeta();
            redLeatherLeggingsMeta.setColor(Color.RED);
            redLeatherLeggingsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
            redLeatherLeggings.setItemMeta(redLeatherLeggingsMeta);

            ItemStack redLeatherBoots = new ItemStack(Material.LEATHER_BOOTS, 1);
            LeatherArmorMeta redLeatherBootsMeta = (LeatherArmorMeta) redLeatherBoots.getItemMeta();
            redLeatherBootsMeta.setColor(Color.RED);
            redLeatherBoots.setItemMeta(redLeatherBootsMeta);

            player.getInventory().setArmorContents(new ItemStack[]{redLeatherBoots, redLeatherLeggings, redLeatherChestplate, redLeatherHelmet});
        }
        //Blue team respawn
        if (blueTeam.getEntries().contains(player.getName())) {
            ItemStack blueLeatherHelmet = new ItemStack(Material.LEATHER_HELMET, 1);
            LeatherArmorMeta blueLeatherHelmetMeta = (LeatherArmorMeta) blueLeatherHelmet.getItemMeta();
            blueLeatherHelmetMeta.setColor(Color.BLUE);
            blueLeatherHelmet.setItemMeta(blueLeatherHelmetMeta);

            ItemStack blueLeatherChestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
            LeatherArmorMeta blueLeatherChestplateMeta = (LeatherArmorMeta) blueLeatherChestplate.getItemMeta();
            blueLeatherChestplateMeta.setColor(Color.BLUE);
            blueLeatherChestplate.setItemMeta(blueLeatherChestplateMeta);

            ItemStack blueLeatherLeggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
            LeatherArmorMeta blueLeatherLeggingsMeta = (LeatherArmorMeta) blueLeatherLeggings.getItemMeta();
            blueLeatherLeggingsMeta.setColor(Color.BLUE);
            blueLeatherLeggingsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
            blueLeatherLeggings.setItemMeta(blueLeatherLeggingsMeta);

            ItemStack blueLeatherBoots = new ItemStack(Material.LEATHER_BOOTS, 1);
            LeatherArmorMeta blueLeatherBootsMeta = (LeatherArmorMeta) blueLeatherBoots.getItemMeta();
            blueLeatherBootsMeta.setColor(Color.BLUE);
            blueLeatherBoots.setItemMeta(blueLeatherBootsMeta);

            player.getInventory().setArmorContents(new ItemStack[]{blueLeatherBoots, blueLeatherLeggings, blueLeatherChestplate, blueLeatherHelmet});
        }
        //No team/observers respawn
        if ((!redTeam.getEntries().contains(player.getName())) && (!blueTeam.getEntries().contains(player.getName()))) {
            player.getServer().getWorld("currentGame").setSpawnLocation(spawn.getBlockX(), spawn.getBlockY(), spawn.getBlockZ());
        }
    }
}