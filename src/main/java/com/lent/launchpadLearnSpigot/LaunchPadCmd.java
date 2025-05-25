package com.lent.launchpadLearnSpigot;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;


public class LaunchPadCmd implements CommandExecutor {

    private final JavaPlugin plugin;
    public LaunchPadCmd(JavaPlugin plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(sender instanceof Player player)) return true;

        ItemStack launchPad = new ItemStack(Material.SLIME_BLOCK);
        ItemMeta meta = launchPad.getItemMeta();
        NamespacedKey launchkey = new NamespacedKey(plugin, "launchpad");
        meta.getPersistentDataContainer().set(launchkey, PersistentDataType.BYTE, (byte) 69);
        launchPad.setItemMeta(meta);

        player.getInventory().addItem(launchPad);
        return true;
    }
}
