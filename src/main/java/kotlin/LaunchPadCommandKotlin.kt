package kotlin

import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.java.JavaPlugin


class LaunchPadCommandKotlin(private val plugin: JavaPlugin) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, s: String, strings: Array<String>): Boolean {
        if (sender !is Player) return true

        val launchPad = ItemStack(Material.SLIME_BLOCK)
        val meta = launchPad.itemMeta
        val launchkey = NamespacedKey(plugin, "launchpad")
        meta.persistentDataContainer.set(launchkey, PersistentDataType.BYTE, 69.toByte())
        launchPad.setItemMeta(meta)

        sender.inventory.addItem(launchPad)
        return true
    }
}