package fr.loyso.ttplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CommandNewGame implements CommandExecutor {
    public void copyWorld(File source, File target){
        try {
            ArrayList<String> ignore = new ArrayList<String>(Arrays.asList("uid.dat", "session.dat"));
            if(!ignore.contains(source.getName())) {
                if(source.isDirectory()) {
                    if(!target.exists())
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
        if(path.exists()) {
            File files[] = path.listFiles();
            for(int i=0; i<files.length; i++) {
                if(files[i].isDirectory()) {
                    deleteWorld(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
        return(path.delete());
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
                    Location spawn = new Location(Bukkit.getWorld("currentGame"), 0,64,1024);
                    player.teleport(spawn);
                    player.kickPlayer("The server is restarting, come back in a few seconds!");
                }

                for(Chunk c : Bukkit.getWorld("currentGame").getLoadedChunks()) {
                    c.unload(false);
                }
                Bukkit.getServer().unloadWorld(Bukkit.getWorld("currentGame"), true);
                deleteWorld(runningGame);
                copyWorld(template, runningGame);

                Bukkit.getWorlds().add(Bukkit.getWorld("currentGame"));
                Bukkit.getServer().unloadWorld(templateWorld, true);
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "stop");

                //Player sender = (Player) commandSender;
                //sender.sendMessage(file.getAbsolutePath() + "\\server.properties");
                //Bukkit.getServer().getConsoleSender().sendMessage(file.getAbsolutePath() + "\\server.properties");

                /*try{
                    in = new FileInputStream(serverProperties);
                    properties.load(in);
                    if (TTProperties.ServerProperty.COMMAND_BLOCKS) {
                        Player sender = (Player) commandSender;
                        sender.sendMessage("test");
                        Bukkit.getServer().broadcastMessage("testfdgskjo");

                        properties.setProperty(("enable-command-block"), "false");

                        //TTProperties.setServerProperty(TTProperties.ServerProperty.COMMAND_BLOCKS, false); //Disable command blocks
                        //TTProperties.savePropertiesFile();
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
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (in !=null) {
                            in.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }*/
            }

            /*String currentDirectory;
            File file = new File("");
            currentDirectory = file.getAbsolutePath();
            String worldDirectory = currentDirectory + "\\Plugins\\plugin.jar\\src\\fr.loyso.plugin\\samplethetowers";
            File test = Bukkit.getWorld("sampleTheTowers").getWorldFolder();
            Player sender = (Player) commandSender;
            sender.sendMessage(test);
            /*FileUtils.copyDirectory(File );
            // The world to copy
            //World source = Bukkit.getWorld("sampleTheTowers");
            World test = World
            World source = Bukkit.get;
            File sourceFolder = source.getWorldFolder();

            // The world to overwrite when copying
            World target = Bukkit.getWorld("runningGame");
            File targetFolder = target.getWorldFolder();
            sender.sendMessage("[Restarting] Copying a new The Towers world to start a new game.");
            copyWorld(sourceFolder, targetFolder);
            sender.sendMessage("[Restarting] Done!");*/
        }
        return true;
    }
}
