package com.lent.launchpadLearnSpigot;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;


public class LaunchPadEvents implements Listener {
    private final JavaPlugin plugin;
    private final NamespacedKey launchpadKey;

    public LaunchPadEvents(JavaPlugin plugin) {
        this.plugin = plugin;
        this.launchpadKey = new NamespacedKey(plugin, "launchpad");
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        ItemMeta meta = event.getItemInHand().getItemMeta();
        if (meta == null) return;
        if (event.getBlockPlaced().getType() != Material.SLIME_BLOCK) return;
        if (meta.getPersistentDataContainer().has(launchpadKey, PersistentDataType.BYTE)) {
            event.getBlockPlaced().setMetadata("launchpad", new FixedMetadataValue(plugin, true));
        }
    }


    @EventHandler
    public void onTouch(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (event.getFrom().getBlock().equals(event.getTo().getBlock())) return;

        Block checkBlockUnderPlayer = player.getLocation().subtract(0, 1, 0).getBlock();
        if (checkBlockUnderPlayer.getType() == Material.SLIME_BLOCK && checkBlockUnderPlayer.hasMetadata("launchpad")) {
            player.setVelocity(new Vector(0, 1.2, 0));
            player.sendMessage("WOOOOOO!");
        }
    }

}

