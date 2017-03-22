package fr.loyso.ttplugin.commands;

import fr.loyso.ttplugin.Properties;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class CommandStopTest implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (commandSender instanceof Player) {
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "stop");
        }
        return true;
    }
}
