
package fr.loyso.spigotplugin;

import java.util.HashMap;

import fr.loyso.spigotplugin.commands.list.CommandPing;
import fr.loyso.spigotplugin.listeners.BlockListener;
import fr.loyso.spigotplugin.listeners.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {
    PlayerListener playerListener = new PlayerListener(this);
    BlockListener blockListener = new BlockListener(this);

    @Override
    public void onEnable() {
        // Register our events
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(playerListener, this);
        pm.registerEvents(blockListener, this);

        // Register our commands
        getCommand("ping").setExecutor(new CommandPing());

        PluginDescriptionFile pdfFile = this.getDescription();
        getLogger().info( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
    }

    @Override
    public void onDisable() {
        // NOTE: All registered events are automatically unregistered when a plugin is disabled
        getLogger().info("Goodbye!");
    }
}
