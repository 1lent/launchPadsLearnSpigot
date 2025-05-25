package kotlin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class LaunchPadLeanSpigotKotlin extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("launchpad").setExecutor(new LaunchPadCommandKotlin(this));
        getServer().getPluginManager().registerEvents(new LaunchPadEventsKotlin(this), this);
    }
}

