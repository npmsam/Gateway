package com.sampreet.gateway.commands;

import com.sampreet.gateway.Gateway;
import com.sampreet.gateway.helpers.MessagesHelper;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;

public class ReloadCommand extends SubCommand {
    public ReloadCommand(Gateway plugin) {
        super(plugin);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        plugin.reloadConfig();

        String message = plugin.getConfig().getString("messages.commands.reload");
        if (message == null || message.trim().isEmpty()) return;

        message = message.replace("<plugin_version>", plugin.getDescription().getVersion());
        Component messageComponent = MessagesHelper.translateColors(message);
        if (messageComponent == null) return;

        sender.sendMessage(messageComponent);
    }
}