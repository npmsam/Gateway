package com.sampreet.gateway.commands;

import com.sampreet.gateway.Gateway;
import com.sampreet.gateway.helpers.MessagesHelper;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;

public class OnCommand extends SubCommand {
    public OnCommand(Gateway plugin) {
        super(plugin);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (plugin.getConfig().getBoolean("enabled")) {
            Component message = MessagesHelper.translateColors(plugin.getConfig().getString("system.commands.errors.already_on"));
            if (message == null) return;
            sender.sendMessage(message);
            return;
        }

        plugin.getConfig().set("enabled", true);
        plugin.saveConfig();

        Component message = MessagesHelper.translateColors(plugin.getConfig().getString("system.commands.on"));
        if (message == null) return;
        sender.sendMessage(message);
    }
}