package com.sampreet.gateway.listeners;

import com.sampreet.gateway.Gateway;
import com.sampreet.gateway.helpers.MessagesHelper;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class AsyncPlayerPreLoginListener implements Listener {
    private final Gateway plugin;


    public AsyncPlayerPreLoginListener(Gateway plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onAsyncPlayerPre(AsyncPlayerPreLoginEvent asyncPlayerPreLoginEvent) {
        if (!plugin.getConfig().getBoolean("enabled", false)) return;

        if (plugin.getJsonHelper().check(asyncPlayerPreLoginEvent.getUniqueId())) return;

        asyncPlayerPreLoginEvent.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_WHITELIST);

        String kickMessage = plugin.getConfig().getString("messages.login_denied");
        if (kickMessage == null || kickMessage.trim().isEmpty()) return;
        kickMessage = kickMessage.replace("<player>", asyncPlayerPreLoginEvent.getName());

        Component kickMessageComponent = MessagesHelper.translateColors(kickMessage);

        asyncPlayerPreLoginEvent.kickMessage(kickMessageComponent);
    }
}