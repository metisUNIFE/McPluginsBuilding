package org.example;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {


    @Override
    public void onEnable() {
        getLogger().info("Plugin has been enabled");
    }

    @Override
    public boolean  onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("spada")) {

            if (sender instanceof Player) {
                Player giocatore = (Player) sender;


                ItemStack spada = new ItemStack(Material.DIAMOND_SWORD);

                ItemMeta spadaItemMeta = spada.getItemMeta();

                spadaItemMeta.setDisplayName(ChatColor.DARK_PURPLE + "Spada BoogieWoogie");

                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.LIGHT_PURPLE + "Boogie");
                lore.add(ChatColor.DARK_BLUE + "Woogie");

                spadaItemMeta.setLore(lore);
                spada.setItemMeta(spadaItemMeta);

                giocatore.getInventory().addItem(spada);

                giocatore.sendMessage(ChatColor.WHITE + "Balla");

                return true;

            }else {
                sender.sendMessage("Only players can do this.");
                return true;
            }
        }
        return false;
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin has been disabled");
    }


}
