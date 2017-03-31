package fr.loyso.ttplugin.startinggame;

import fr.loyso.ttplugin.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dye;
import org.bukkit.scheduler.BukkitRunnable;

import static fr.loyso.ttplugin.listeners.ReadyButtonListener.gameStarted;

public class MiddleItems implements Runnable {
    private final Plugin plugin;
    public MiddleItems(Plugin instance) {
        plugin = instance;
    }

    @Override
    public void run() {
        boolean playerInItemSpawnZone = false;
        Location ironSpawn = new Location(Bukkit.getWorld("currentGame"), 0, 206, 1138);
        Location lapisSpawn = new Location(Bukkit.getWorld("currentWorld"), 0, 206, 1166);
        Dye lapisDye = new Dye();
        lapisDye.setColor(DyeColor.BLUE);
        ItemStack lapis = lapisDye.toItemStack();
        lapis.setAmount(1);
        while(gameStarted) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if ((player.getLocation().getBlockX() <= 12) && (player.getLocation().getBlockX() > -12) && (player.getLocation().getBlockZ() >= 1126) && (player.getLocation().getBlockZ() <= 1178)) {
                    playerInItemSpawnZone = true;
                } else {
                    playerInItemSpawnZone = false;
                }
            }
            if (playerInItemSpawnZone == true) {
                new BukkitRunnable() {
                    public void run() {
                        Bukkit.getWorld("currentGame").dropItemNaturally(ironSpawn, new ItemStack(Material.IRON_INGOT, 1));
                        Bukkit.getWorld("currentGame").dropItemNaturally(lapisSpawn, lapis);
                    }
                }.runTask(plugin);
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
