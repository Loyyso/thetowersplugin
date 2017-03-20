package fr.loyso.spigotplugin.commands.list;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandPing implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Location playerLocation = player.getLocation();
            player.getWorld().createExplosion(playerLocation.getX(), playerLocation.getY(), playerLocation.getZ(), 1);
        }
        return true;
    }
}
