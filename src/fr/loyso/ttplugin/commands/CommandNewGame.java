package fr.loyso.ttplugin.commands;

import fr.loyso.ttplugin.Plugin;
import fr.loyso.ttplugin.TTProperties;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (commandSender instanceof Player) {
            File file = new File("");
            File template = new File(file.getAbsolutePath() + "\\TowersTemplate");
            File serverProperties = new File(file.getAbsolutePath() + "\\server.properties");
            if (template.exists()) {
                FileInputStream in = null;
                Properties properties = new Properties();

                //Player sender = (Player) commandSender;
                //sender.sendMessage(file.getAbsolutePath() + "\\server.properties");
                //Bukkit.getServer().getConsoleSender().sendMessage(file.getAbsolutePath() + "\\server.properties");

                try{
                    in = new FileInputStream(serverProperties);
                    properties.load(in);
                    properties.getProperty("enable-command-block");
                    if (properties.getProperty("enable-command-block").contentEquals("true")) {
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
                }
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
