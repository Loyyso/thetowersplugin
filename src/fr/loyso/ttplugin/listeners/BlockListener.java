
package fr.loyso.ttplugin.listeners;

import fr.loyso.ttplugin.Plugin;
import fr.loyso.ttplugin.startinggame.Countdown;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;
import java.io.File;

public class BlockListener implements Listener {
    private final Plugin plugin;
    public BlockListener(Plugin instance) {
        plugin = instance;
    }

    public static boolean redReady = false;
    public static boolean blueReady = false;

    public static boolean gameStarted = false;

    @EventHandler
    public void onButtonInteract(PlayerInteractEvent event) throws InterruptedException {
        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            ScoreboardManager scbManager = Bukkit.getScoreboardManager();
            Scoreboard board = scbManager.getMainScoreboard();
            Team redTeam = board.getTeam("Red");
            Team blueTeam = board.getTeam("Blue");

            CommandSender player = event.getPlayer();

            int blockX = event.getClickedBlock().getLocation().getBlockX();
            int blockY = event.getClickedBlock().getLocation().getBlockY();
            int blockZ = event.getClickedBlock().getLocation().getBlockZ();
            int playerX = event.getPlayer().getLocation().getBlockX();
            int playerY = event.getPlayer().getLocation().getBlockY();
            int playerZ = event.getPlayer().getLocation().getBlockZ();

            Material clickedMaterial = event.getClickedBlock().getType();
            Location redReadyBlockRedSide = new Location(Bukkit.getWorld("currentGame"),17 , 66, 998);
            Location blueReadyBlockBlueSide = new Location(Bukkit.getWorld("currentGame"),-17 , 66, 986);
            Location redReadyBlockBlueSide = new Location(Bukkit.getWorld("currentGame"),-17 , 66, 998);
            Location blueReadyBlockRedSide = new Location(Bukkit.getWorld("currentGame"),17 , 66, 986);
            if (clickedMaterial == Material.STONE_BUTTON) {
                //Red side
                if ((redTeam.getEntries().contains(player.getName())) && (blockX == 18) && (blockY == 67) && (blockZ == 998)) {
                    if (redReadyBlockRedSide.getBlock().getType() == Material.REDSTONE_BLOCK) {
                        redReadyBlockRedSide.getBlock().setType(Material.EMERALD_BLOCK);
                        redReadyBlockBlueSide.getBlock().setType(Material.EMERALD_BLOCK);
                        redReady = true;
                    } else if (redReadyBlockRedSide.getBlock().getType() == Material.EMERALD_BLOCK) {
                        redReadyBlockRedSide.getBlock().setType(Material.REDSTONE_BLOCK);
                        redReadyBlockBlueSide.getBlock().setType(Material.REDSTONE_BLOCK);
                        redReady = false;
                    }
                }
                //Blue side
                else if ((blueTeam.getEntries().contains(player.getName())) && (blockX == -18) && (blockY == 67) && (blockZ == 986)) {
                    if (blueReadyBlockBlueSide.getBlock().getType() == Material.REDSTONE_BLOCK) {
                        blueReadyBlockBlueSide.getBlock().setType(Material.EMERALD_BLOCK);
                        blueReadyBlockRedSide.getBlock().setType(Material.EMERALD_BLOCK);
                        blueReady = true;
                    } else if (blueReadyBlockBlueSide.getBlock().getType() == Material.EMERALD_BLOCK) {
                        blueReadyBlockBlueSide.getBlock().setType(Material.REDSTONE_BLOCK);
                        blueReadyBlockRedSide.getBlock().setType(Material.REDSTONE_BLOCK);
                        blueReady = false;
                    }
                }
            }

            Countdown cntdwn = new Countdown();
            new Thread(cntdwn).start();
        }
    }
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        CommandSender commandSender = event.getPlayer();
        if (commandSender instanceof BlockCommandSender) {
            File file = new File("");
            File template = new File(file.getAbsolutePath() + "\\TowersTemplate");
            if (template.exists()) {
                plugin.getLogger().info("Template found!");
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
            } else {
                Bukkit.getServer().broadcastMessage("[ERROR] The Towers world not found!");
                Bukkit.getServer().broadcastMessage("[ERROR] Please put The Towers map, named \"TowersTemplate\" in your server folder");
            }
        }

    }
    @EventHandler
    public void onBlockCanBuild(BlockCanBuildEvent event) {
        Material mat = event.getMaterial();
        Block block = event.getBlock();

        if (mat == Material.STONE) {
            Block under = block.getRelative(BlockFace.DOWN);
            if (under.getType() == Material.IRON_BLOCK) {
                event.setBuildable(false);
            }
        }
    }
}
