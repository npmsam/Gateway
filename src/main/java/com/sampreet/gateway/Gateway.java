package com.sampreet.gateway;

import com.sampreet.gateway.commands.WhitelistCommand;
import com.sampreet.gateway.helpers.JsonHelper;
import com.sampreet.gateway.listeners.AsyncPlayerPreLoginListener;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Gateway extends JavaPlugin {

    private JsonHelper jsonHelper;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        jsonHelper = new JsonHelper(getDataFolder(), this);
        jsonHelper.load();

        PluginCommand whitelistPluginCommand = getCommand("whitelist");
        if (whitelistPluginCommand != null) {
            whitelistPluginCommand.setExecutor(new WhitelistCommand(this));
        }

        getServer().getPluginManager().registerEvents(new AsyncPlayerPreLoginListener(this), this);

        logMessage("system.lifecycle.enable");
    }

    @Override
    public void onDisable() {
        logMessage("system.lifecycle.disable");
    }

    private void logMessage(String path) {
        String message = getConfig().getString(path);
        if (message == null || message.trim().isEmpty()) return;
        message = message.replace("<version>", getDescription().getVersion());
        getLogger().info(message);
    }

    public JsonHelper getJsonHelper() {
        return jsonHelper;
    }
}