package com.sampreet.gateway.commands;

import com.sampreet.gateway.Gateway;
import com.sampreet.gateway.helpers.MessagesHelper;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;

public class OffCommand extends SubCommand {
    public OffCommand(Gateway plugin) {
        super(plugin);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!plugin.getConfig().getBoolean("enabled")) {
            Component message = MessagesHelper.translateColors(plugin.getConfig().getString("messages.commands.errors.already_off"));
            if (message == null) return;
            sender.sendMessage(message);
            return;
        }

        plugin.getConfig().set("enabled", false);
        plugin.saveConfig();

        Component message = MessagesHelper.translateColors(plugin.getConfig().getString("messages.commands.off"));
        if (message == null) return;
        sender.sendMessage(message);
    }
}