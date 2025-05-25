package kotlin

import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.metadata.FixedMetadataValue
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.util.Vector

class LaunchPadEventsKotlin(private val plugin: JavaPlugin) : Listener {
    private val launchpadKey = NamespacedKey(plugin, "launchpad")

    @EventHandler
    fun onPlace(event: BlockPlaceEvent) {
        val meta = event.itemInHand.itemMeta ?: return
        if (event.blockPlaced.type != Material.SLIME_BLOCK) return
        if (meta.persistentDataContainer.has(launchpadKey, PersistentDataType.BYTE)) {
            event.blockPlaced.setMetadata("launchpad", FixedMetadataValue(plugin, true))
        }
    }

    @EventHandler
    fun onTouch(event: PlayerMoveEvent) {
        val player = event.player
        if (event.from.block == event.to.block) return

        val checkBlockUnderPlayer = player.location.subtract(0.0, 1.0, 0.0).block
        if (checkBlockUnderPlayer.type == Material.SLIME_BLOCK && checkBlockUnderPlayer.hasMetadata("launchpad")) {
            player.velocity = Vector(0.0, 1.2, 0.0)
            player.sendMessage("WOOOOOO!")
        }
    }
}