
package fr.loyso.ttplugin.listeners;

import fr.loyso.ttplugin.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.io.File;


public class BlockListener implements Listener {
    private final Plugin plugin;
    public BlockListener(Plugin instance) {
        plugin = instance;
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        CommandSender commandSender = event.getPlayer();
        if (commandSender instanceof BlockCommandSender) {
            File file = new File("");
            File template = new File(file.getAbsolutePath() + "\\TowersTemplate");
            if (template.exists()) {
                plugin.getLogger().info("Template found!");
                Bukkit.getServer().broadcastMessage("[INFO] Command blocks disabled, restarting server in 5 seconds...");
                for (int seconds = 5; seconds >= 0; seconds = seconds - 1) {
                    try {
                        Thread.sleep(1000);
                        Bukkit.getServer().broadcastMessage("[INFO] " + seconds);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "stop");
            } else {
                Bukkit.getServer().broadcastMessage("[ERROR] The Towers world not found!");
                Bukkit.getServer().broadcastMessage("[ERROR] Please put The Towers map, named \"TowersTemplate\" in your server folder");
            }
        }

    }
    @EventHandler
    public void onBlockCanBuild(BlockCanBuildEvent event) {
        Material mat = event.getMaterial();
        Block block = event.getBlock();

        if (mat == Material.STONE) {
            Block under = block.getRelative(BlockFace.DOWN);
            if (under.getType() == Material.IRON_BLOCK) {
                event.setBuildable(false);
            }
        }
    }
}
