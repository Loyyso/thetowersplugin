package fr.loyso.ttplugin.startinggame;

import fr.loyso.ttplugin.Plugin;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.*;

import java.util.HashSet;
import static fr.loyso.ttplugin.listeners.ReadyButtonListener.gameStarted;

public class Launch {
    private final Plugin plugin;
    public Launch(Plugin instance) {
        plugin = instance;
    }

    public static Location redSpawn = new Location(Bukkit.getServer().getWorld("currentGame"), 84, 192, 1152);
    public static Location blueSpawn = new Location(Bukkit.getServer().getWorld("currentGame"), -84, 192, 1152);

    public void startGame() {
        ScoreboardManager scbManager = Bukkit.getScoreboardManager();
        Scoreboard board = scbManager.getMainScoreboard();
        Team redTeam = board.getTeam("Red");
        Team blueTeam = board.getTeam("Blue");

        if (gameStarted) {
            for (Player player : Bukkit.getOnlinePlayers()){
                player.sendMessage("[-> The game has started!");
            }

            //Change potion effects
            for (Player player : Bukkit.getOnlinePlayers()) {
                for (PotionEffect effect : player.getActivePotionEffects()) {
                    player.removePotionEffect(effect.getType());
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20, 0, false, true), true);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 0, false, true), true);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 0, false, true), true);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 200, 0, false, true), true);
                }
            }

            //Set scoreboard
            Objective scoreObj = board.registerNewObjective("Score", "dummy");
            scoreObj.setDisplayName("§6§lSCORE§r");
            scoreObj.setDisplaySlot(DisplaySlot.SIDEBAR);
            Score redScore = scoreObj.getScore("§c§oRED§r");
            Score blueScore = scoreObj.getScore("§9§oBLUE§r");
            redScore.setScore(0);
            blueScore.setScore(0);

            //Red team
            HashSet<Player> redPlayersList = new HashSet<>(redTeam.getEntries().size());
            redTeam.getEntries().forEach(i -> redPlayersList.add(Bukkit.getPlayer(i)));

            for (Player player : redPlayersList) {
                ItemStack redLeatherHelmet = new ItemStack(Material.LEATHER_HELMET, 1);
                LeatherArmorMeta redLeatherHelmetMeta = (LeatherArmorMeta) redLeatherHelmet.getItemMeta();
                redLeatherHelmetMeta.setColor(Color.RED);
                redLeatherHelmet.setItemMeta(redLeatherHelmetMeta);

                ItemStack redLeatherChestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
                LeatherArmorMeta redLeatherChestplateMeta = (LeatherArmorMeta) redLeatherChestplate.getItemMeta();
                redLeatherChestplateMeta.setColor(Color.RED);
                redLeatherChestplate.setItemMeta(redLeatherChestplateMeta);

                ItemStack redLeatherLeggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
                LeatherArmorMeta redLeatherLeggingsMeta = (LeatherArmorMeta) redLeatherLeggings.getItemMeta();
                redLeatherLeggingsMeta.setColor(Color.RED);
                redLeatherLeggingsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
                redLeatherLeggings.setItemMeta(redLeatherLeggingsMeta);

                ItemStack redLeatherBoots = new ItemStack(Material.LEATHER_BOOTS, 1);
                LeatherArmorMeta redLeatherBootsMeta = (LeatherArmorMeta) redLeatherBoots.getItemMeta();
                redLeatherBootsMeta.setColor(Color.RED);
                redLeatherBoots.setItemMeta(redLeatherBootsMeta);

                player.teleport(redSpawn);
                player.setSaturation(10);
                player.setGameMode(GameMode.SURVIVAL);
                player.getInventory().setArmorContents(new ItemStack[]{redLeatherBoots, redLeatherLeggings, redLeatherChestplate, redLeatherHelmet});
            }
            //Blue team
            HashSet<Player> bluePlayersList = new HashSet<>(blueTeam.getEntries().size());
            blueTeam.getEntries().forEach(i -> bluePlayersList.add(Bukkit.getPlayer(i)));

            for (Player player : bluePlayersList) {
                ItemStack blueLeatherHelmet = new ItemStack(Material.LEATHER_HELMET, 1);
                LeatherArmorMeta blueLeatherHelmetMeta = (LeatherArmorMeta) blueLeatherHelmet.getItemMeta();
                blueLeatherHelmetMeta.setColor(Color.BLUE);
                blueLeatherHelmet.setItemMeta(blueLeatherHelmetMeta);

                ItemStack blueLeatherChestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
                LeatherArmorMeta blueLeatherChestplateMeta = (LeatherArmorMeta) blueLeatherChestplate.getItemMeta();
                blueLeatherChestplateMeta.setColor(Color.BLUE);
                blueLeatherChestplate.setItemMeta(blueLeatherChestplateMeta);

                ItemStack blueLeatherLeggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
                LeatherArmorMeta blueLeatherLeggingsMeta = (LeatherArmorMeta) blueLeatherLeggings.getItemMeta();
                blueLeatherLeggingsMeta.setColor(Color.BLUE);
                blueLeatherLeggingsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
                blueLeatherLeggings.setItemMeta(blueLeatherLeggingsMeta);

                ItemStack blueLeatherBoots = new ItemStack(Material.LEATHER_BOOTS, 1);
                LeatherArmorMeta blueLeatherBootsMeta = (LeatherArmorMeta) blueLeatherBoots.getItemMeta();
                blueLeatherBootsMeta.setColor(Color.BLUE);
                blueLeatherBoots.setItemMeta(blueLeatherBootsMeta);

                player.teleport(blueSpawn);
                player.setSaturation(10);
                player.setGameMode(GameMode.SURVIVAL);
                player.getInventory().setArmorContents(new ItemStack[]{blueLeatherBoots, blueLeatherLeggings, blueLeatherChestplate, blueLeatherHelmet});
            }
            MiddleItems middleItems = new MiddleItems(plugin);
            new Thread(middleItems).start();
        }
    }
}