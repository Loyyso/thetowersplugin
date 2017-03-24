package fr.loyso.ttplugin.commands;

import fr.loyso.ttplugin.listeners.PlayerListener;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CommandNewGame implements CommandExecutor {
    public void copyWorld(File source, File target) {
        try {
            ArrayList<String> ignore = new ArrayList<String>(Arrays.asList("uid.dat", "session.dat"));
            if (!ignore.contains(source.getName())) {
                if (source.isDirectory()) {
                    if (!target.exists())
                        target.mkdirs();
                    String files[] = source.list();
                    for (String file : files) {
                        File srcFile = new File(source, file);
                        File destFile = new File(target, file);
                        copyWorld(srcFile, destFile);
                    }
                } else {
                    InputStream in = new FileInputStream(source);
                    OutputStream out = new FileOutputStream(target);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = in.read(buffer)) > 0)
                        out.write(buffer, 0, length);
                    in.close();
                    out.close();
                }
            }
        } catch (IOException e) {

        }
    }

    public boolean deleteWorld(File path) {
        if (path.exists()) {
            File files[] = path.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteWorld(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
        return (path.delete());
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (commandSender instanceof Player) {
            File file = new File("");
            File template = new File(file.getAbsolutePath() + "\\TowersTemplate");
            File runningGame = new File(file.getAbsolutePath() + "\\currentGame");
            World templateWorld = Bukkit.getWorld("TowersTemplate");
            if (template.exists()) {
                Bukkit.getServer().getWorlds().add(templateWorld);

                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                    PlayerInventory inventory = player.getInventory();
                    inventory.clear();
                    player.setGameMode(GameMode.ADVENTURE);
                    Location spawn = new Location(Bukkit.getWorld("currentGame"), 0, 64, 1024);
                    player.teleport(spawn);
                    player.kickPlayer("The server is restarting, come back in a few seconds!");
                }

                for (Chunk c : Bukkit.getWorld("currentGame").getLoadedChunks()) {
                    c.unload(false);
                }
                Bukkit.getServer().unloadWorld(Bukkit.getWorld("currentGame"), true);
                deleteWorld(runningGame);
                copyWorld(template, runningGame);

                Bukkit.getWorlds().add(Bukkit.getWorld("currentGame"));
                Bukkit.getServer().unloadWorld(templateWorld, true);
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "stop");
            }
            return true;
        }
        return true;
    }
}
