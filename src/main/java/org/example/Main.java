package org.example;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

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

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can run this command");
            return true;
        }

        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("bacchetta")) {

                ItemStack bacchetta = new ItemStack(Material.STICK);

                ItemMeta meta = bacchetta.getItemMeta();

                Component itemName = Component.text("EXPLOSION").color(NamedTextColor.DARK_PURPLE);
                meta.displayName(itemName);

                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.LIGHT_PURPLE + "EXPLOOOOOSION");
                meta.setLore(lore);
                bacchetta.setItemMeta(meta);

                p.getInventory().addItem(bacchetta);

                p.sendMessage(ChatColor.WHITE + "Right click to fire");

                return true;

        }else if (cmd.getName().equalsIgnoreCase("reassembly")) {

            ItemStack reassembly = new ItemStack(Material.CLOCK);

            ItemMeta meta = reassembly.getItemMeta();
            Component itemName = Component.text("REASSEMBLING").color(NamedTextColor.DARK_AQUA);
            meta.displayName(itemName);
            reassembly.setItemMeta(meta);

            p.getInventory().addItem(reassembly);
            p.sendMessage(ChatColor.WHITE + "Graft and tamper till you graft no mo");
            return true;
        }

        return false;
    }

    @EventHandler
    public void useReassembly(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction() == Action.PHYSICAL || e.getAction() == Action.RIGHT_CLICK_AIR) {
            ItemStack heldItem = e.getItem();

            if (heldItem != null && heldItem.getType() == Material.CLOCK) {

                if (heldItem.hasItemMeta() && heldItem.getItemMeta().displayName().equals(Component.text("REASSEMBLING").color(NamedTextColor.DARK_AQUA))) {

                    Component invTitle = Component.text("Reassembling").color(NamedTextColor.DARK_GRAY);
                    Inventory inv = Bukkit.createInventory(null, 9, invTitle);

                    ItemStack option1 = new ItemStack(Material.STRING);
                    ItemMeta meta1 = option1.getItemMeta();
                    meta1.displayName(Component.text("Graft space"));
                    option1.setItemMeta(meta1);

                    ItemStack option2 = new ItemStack(Material.SPIDER_EYE);
                    ItemMeta meta2 = option2.getItemMeta();
                    meta2.displayName(Component.text("Graft disease"));
                    option2.setItemMeta(meta2);

                    inv.setItem(3, option1);
                    inv.setItem(6, option2);

                    p.openInventory(inv);
                }
            }
        }
    }

    @EventHandler
    public void lockInventory(InventoryClickEvent e) {
        Component title = Component.text("Reassembling").color(NamedTextColor.DARK_GRAY);

        if (e.getView().title().equals(title)) {
            e.setCancelled(true);

            if (e.getCurrentItem() != null && e.getCurrentItem().getType() == Material.SPIDER_EYE) {

                PotionEffect immobility = new PotionEffect(PotionEffectType.SLOWNESS, 100, 5);

                e.getWhoClicked().addPotionEffect(immobility);
                e.getWhoClicked().closeInventory();
            }
        }
    }

    @EventHandler
    public void usaBacchetta(PlayerInteractEvent e) {
        Player giocatore = e.getPlayer();

        if (e.getAction() == Action.RIGHT_CLICK_AIR  || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            ItemStack oggettoInMano = e.getItem();

            if (oggettoInMano != null && oggettoInMano.getType() == Material.STICK) {

                if (oggettoInMano.hasItemMeta() && oggettoInMano.getItemMeta().displayName().equals(Component.text("EXPLOSION").color(NamedTextColor.DARK_PURPLE)) ) {

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
