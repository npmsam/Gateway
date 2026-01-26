package com.sampreet.gateway;

import org.bukkit.plugin.java.JavaPlugin;

public final class Gateway extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        if (!getConfig().getBoolean("enabled", true)) {
            logMessage("system.status.disabled");

            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        logMessage("system.lifecycle.enable");
    }

    @Override
    public void onDisable() {
        logMessage("system.lifecycle.disable");
    }

    private void logMessage(String path) {
        String message = getConfig().getString(path);
        if (message == null || message.trim().isEmpty()) return;
        message = message.replace("<plugin_version>", getDescription().getVersion());
        getLogger().info(message);
    }
}