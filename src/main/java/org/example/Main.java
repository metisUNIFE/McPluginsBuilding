package org.example;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Fireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin implements Listener {


    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("Plugin has been enabled");
    }

    @Override
    public boolean  onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("bacchetta")) {

            if (sender instanceof Player) {
                Player giocatore = (Player) sender;


                ItemStack bacchetta = new ItemStack(Material.STICK);

                ItemMeta meta = bacchetta.getItemMeta();

                Component itemName = Component.text("EXPLOSION").color(NamedTextColor.DARK_PURPLE);
                meta.displayName(itemName);

                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.LIGHT_PURPLE + "EXPLOOOOOSION");
                meta.setLore(lore);
                bacchetta.setItemMeta(meta);

                giocatore.getInventory().addItem(bacchetta);

                giocatore.sendMessage(ChatColor.WHITE + "Right click to fire");

                return true;

            }else {
                sender.sendMessage("Only players can do this.");
                return true;
            }
        }
        return false;
    }

    @EventHandler
    public void usaBacchetta(PlayerInteractEvent e) {
        Player giocatore = e.getPlayer();

        if (e.getAction() == Action.RIGHT_CLICK_AIR  || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            ItemStack oggettoInMano = e.getItem();
            giocatore.sendMessage(ChatColor.BLUE + "Test");

            if (oggettoInMano != null && oggettoInMano.getType() == Material.STICK) {
                giocatore.sendMessage(ChatColor.DARK_AQUA + "Test");

                if (oggettoInMano.hasItemMeta() && oggettoInMano.getItemMeta().displayName().equals(Component.text("EXPLOSION").color(NamedTextColor.DARK_PURPLE)) ) {
                    giocatore.sendMessage(ChatColor.WHITE + "Test");
                    giocatore.launchProjectile(Fireball.class);
                    giocatore.sendMessage(ChatColor.RED + "EXPLOOOOOSIOOOOON");
                }
            }
        }
    }


    @Override
    public void onDisable() {
        getLogger().info("Plugin has been disabled");
    }


}
