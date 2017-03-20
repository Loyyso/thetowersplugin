
package fr.loyso.spigotplugin.listeners;

import fr.loyso.spigotplugin.Plugin;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockCanBuildEvent;


public class BlockListener implements Listener {
    private final Plugin plugin;
    public BlockListener(Plugin instance) {
        plugin = instance;
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
