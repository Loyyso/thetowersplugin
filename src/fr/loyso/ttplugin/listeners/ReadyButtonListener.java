
package fr.loyso.ttplugin.listeners;

import fr.loyso.ttplugin.Plugin;
import fr.loyso.ttplugin.startinggame.Countdown;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class ReadyButtonListener implements Listener {
    private final Plugin plugin;
    public ReadyButtonListener(Plugin instance) {
        plugin = instance;
    }

    public static boolean redReady = false;
    public static boolean blueReady = false;
    public static boolean gameStarted = false;

    float volume = 100;
    float pitch = (float) 0.5;

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
                        Bukkit.getWorld("currentGame").playSound(new Location(Bukkit.getWorld("currentGame"), 0, 0, 0), Sound.BLOCK_NOTE_BASS, volume, pitch);
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            players.sendMessage("[-> Team §cRED§r is ready!");
                        }

                    } else if (redReadyBlockRedSide.getBlock().getType() == Material.EMERALD_BLOCK) {
                        redReadyBlockRedSide.getBlock().setType(Material.REDSTONE_BLOCK);
                        redReadyBlockBlueSide.getBlock().setType(Material.REDSTONE_BLOCK);
                        redReady = false;
                        Bukkit.getWorld("currentGame").playSound(new Location(Bukkit.getWorld("currentGame"), 0, 0, 0), Sound.BLOCK_NOTE_BASS, volume, pitch);
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            players.sendMessage("[-> Team §cRED§r isn't ready anymore!");
                        }
                    }
                }
                //Blue side
                else if ((blueTeam.getEntries().contains(player.getName())) && (blockX == -18) && (blockY == 67) && (blockZ == 986)) {
                    if (blueReadyBlockBlueSide.getBlock().getType() == Material.REDSTONE_BLOCK) {
                        blueReadyBlockBlueSide.getBlock().setType(Material.EMERALD_BLOCK);
                        blueReadyBlockRedSide.getBlock().setType(Material.EMERALD_BLOCK);
                        blueReady = true;
                        Bukkit.getWorld("currentGame").playSound(new Location(Bukkit.getWorld("currentGame"), 0, 0, 0), Sound.BLOCK_NOTE_BASS, volume, pitch);
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            players.sendMessage("[-> Team §9BLUE§r is ready!");
                        }
                    } else if (blueReadyBlockBlueSide.getBlock().getType() == Material.EMERALD_BLOCK) {
                        blueReadyBlockBlueSide.getBlock().setType(Material.REDSTONE_BLOCK);
                        blueReadyBlockRedSide.getBlock().setType(Material.REDSTONE_BLOCK);
                        blueReady = false;
                        Bukkit.getWorld("currentGame").playSound(new Location(Bukkit.getWorld("currentGame"), 0, 0, 0), Sound.BLOCK_NOTE_BASS, volume, pitch);
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            players.sendMessage("[-> Team §9BLUE§r isn't ready anymore!");
                        }
                    }
                }
                Countdown cntdwn = new Countdown(plugin);
                new Thread(cntdwn).start();
            }
        }
    }
}
