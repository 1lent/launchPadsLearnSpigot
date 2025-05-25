package com.lent.launchpadLearnSpigot;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class LaunchpadLearnSpigot extends JavaPlugin {

    @Override
    public void onEnable() {
            getCommand("launchpad").setExecutor(new LaunchPadCmd(this));
            getServer().getPluginManager().registerEvents(new LaunchPadEvents(this), this);
        }
    }

