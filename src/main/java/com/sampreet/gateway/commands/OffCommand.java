package com.sampreet.gateway.commands;

import com.sampreet.gateway.Gateway;
import com.sampreet.gateway.helpers.MessagesHelper;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class OffCommand extends SubCommand {
    public OffCommand(Gateway plugin) {
        super(plugin);
    }

    @Override
    public void execute(@NotNull CommandSender sender, String[] args) {
        if (!sender.hasPermission("whitelist.toggle")) {
            sendMessage(sender, "messages.commands.errors.no_permission");
            return;
        }

        if (!plugin.getConfig().getBoolean("enabled")) {
            sendMessage(sender, "messages.commands.errors.already_off");
            return;
        }

        plugin.getConfig().set("enabled", false);
        plugin.saveConfig();

        sendMessage(sender, "messages.commands.off");
    }

    private void sendMessage(CommandSender sender, String path) {
        Component message = MessagesHelper.translateColors(plugin.getConfig().getString(path));
        if (message == null) return;

        sender.sendMessage(message);
    }
}