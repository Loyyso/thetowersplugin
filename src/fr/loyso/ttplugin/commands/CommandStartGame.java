package fr.loyso.ttplugin.commands;

import fr.loyso.ttplugin.Plugin;
import fr.loyso.ttplugin.startinggame.Launch;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandStartGame implements CommandExecutor {
    private final Plugin plugin;
    public CommandStartGame(Plugin instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Launch launch = new Launch(plugin);
            launch.startGame();
        }
        return false;
    }
}
