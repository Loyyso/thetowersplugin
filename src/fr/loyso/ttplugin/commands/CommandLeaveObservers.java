package fr.loyso.ttplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class CommandLeaveObservers implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        ScoreboardManager scbManager = Bukkit.getScoreboardManager();
        Scoreboard board = scbManager.getMainScoreboard();
        Team observerTeam = board.getTeam("Observers");
        if (commandSender instanceof Player) {
            String playerString = commandSender.getName();
            if (observerTeam.hasEntry(playerString)) {
                observerTeam.removeEntry(playerString);
                ((Player) commandSender).playSound(new Location(Bukkit.getWorld("currentGame"), 0, 0, 0), Sound.BLOCK_NOTE_HARP, 100, 1);
                ((Player) commandSender).setGameMode(GameMode.ADVENTURE);
                ((Player) commandSender).teleport(new Location(Bukkit.getWorld("currentGame"), 0, 64, 1024));
                commandSender.sendMessage("[-> You left §aOBSERVERS§r team!");
            } else if (!observerTeam.hasEntry(playerString)) {
                commandSender.sendMessage("[-> You're not in the §aOBSERVERS§r team!");
            }
        }
        return true;
    }
}