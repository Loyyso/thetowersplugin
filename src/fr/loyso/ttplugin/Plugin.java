
package fr.loyso.ttplugin;

import fr.loyso.ttplugin.commands.CommandLeaveObservers;
import fr.loyso.ttplugin.commands.CommandNewGame;
import fr.loyso.ttplugin.commands.CommandPing;
import fr.loyso.ttplugin.commands.CommandStartGame;
import fr.loyso.ttplugin.listeners.ReadyButtonListener;
import fr.loyso.ttplugin.listeners.PlayerListener;
import fr.loyso.ttplugin.listeners.ScoreListener;
import fr.loyso.ttplugin.listeners.TeamListener;
import fr.loyso.ttplugin.startinggame.Scoreboards;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class Plugin extends JavaPlugin {
    PlayerListener playerListener = new PlayerListener(this);
    ReadyButtonListener readyButtonListener = new ReadyButtonListener(this);
    TeamListener teamListener = new TeamListener(this);
    ScoreListener scoreListener = new ScoreListener(this);

    @Override
    public void onEnable() {
        // Register our commands
        getCommand("ping").setExecutor(new CommandPing());
        getCommand("newgame").setExecutor(new CommandNewGame());
        getCommand("startgame").setExecutor(new CommandStartGame(this));
        getCommand("leaveobservers").setExecutor(new CommandLeaveObservers());

        //Create and configure teams
        Scoreboards.setTeams();

        // Register our events
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(playerListener, this);
        pm.registerEvents(readyButtonListener, this);
        pm.registerEvents(teamListener, this);
        pm.registerEvents(scoreListener, this);

        //Other things
        PluginDescriptionFile pdfFile = this.getDescription();
        getLogger().info( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
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
