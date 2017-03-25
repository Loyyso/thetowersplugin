
package fr.loyso.ttplugin;

import fr.loyso.ttplugin.commands.CommandNewGame;
import fr.loyso.ttplugin.commands.CommandPing;
import fr.loyso.ttplugin.listeners.BlockListener;
import fr.loyso.ttplugin.listeners.PlayerListener;
import fr.loyso.ttplugin.listeners.TeamListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.List;

public class Plugin extends JavaPlugin {
    PlayerListener playerListener = new PlayerListener(this);
    BlockListener blockListener = new BlockListener(this);
    TeamListener teamListener = new TeamListener(this);

    @Override
    public void onEnable() {
        // Register our events
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(playerListener, this);
        pm.registerEvents(blockListener, this);
        pm.registerEvents(teamListener, this);

        // Register our commands
        getCommand("ping").setExecutor(new CommandPing());
        getCommand("newgame").setExecutor(new CommandNewGame());

        //Other things
        PluginDescriptionFile pdfFile = this.getDescription();
        getLogger().info( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
        Bukkit.getServer().broadcastMessage("[INFO] **********************************************************");
        Bukkit.getServer().broadcastMessage("[INFO] Make sure the commands blocks are disabled on your server!");
        Bukkit.getServer().broadcastMessage("[INFO] **********************************************************");
        Bukkit.getServer().broadcastMessage("[INFO] Execute /newgame to launch a new game!");

        //Create and configure teams
        Scoreboards.setTeams();

    }

    @Override
    public void onDisable() {
        // NOTE: All registered events are automatically unregistered when a plugin is disabled
        ScoreboardManager scbManager = Bukkit.getScoreboardManager();
        Scoreboard board = scbManager.getMainScoreboard();
        board.getTeam("Red").unregister();
        board.getTeam("Blue").unregister();
        getLogger().info("Goodbye!");
    }
}
