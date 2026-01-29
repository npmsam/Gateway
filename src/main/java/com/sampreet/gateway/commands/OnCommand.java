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
            sendMessage(sender, "messages.commands.errors.already_on");
            return;
        }

        plugin.getConfig().set("enabled", true);
        plugin.saveConfig();

        sendMessage(sender, "messages.commands.on");
    }

    private void sendMessage(CommandSender sender, String path) {
        Component message = MessagesHelper.translateColors(plugin.getConfig().getString(path));
        if (message == null) return;

        sender.sendMessage(message);
    }
}