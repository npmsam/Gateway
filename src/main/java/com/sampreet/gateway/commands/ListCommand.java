package com.sampreet.gateway.commands;

import com.sampreet.gateway.Gateway;
import com.sampreet.gateway.helpers.MessagesHelper;
import com.sampreet.gateway.helpers.PlayerEntry;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class ListCommand extends SubCommand {
    public ListCommand(Gateway plugin) {
        super(plugin);
    }

    @Override
    public void execute(CommandSender sender, String @NotNull [] args) {
        String message = plugin.getConfig().getString("messages.commands.list");
        if (message == null || message.trim().isEmpty()) return;

        List<PlayerEntry> entries = plugin.getJsonHelper().list();

        if (entries.isEmpty()) {
            Component errorMessage = MessagesHelper.translateColors(plugin.getConfig().getString("messages.commands.errors.no_players_whitelisted"));
            if (errorMessage == null) return;

            sender.sendMessage(message);
            return;
        }

        String list = entries.stream()
                .map(entry -> entry.username)
                .filter(name -> name != null && !name.isBlank())
                .collect(Collectors.joining(", "));

        message = message.replace("<list>", list);

        Component component = MessagesHelper.translateColors(message);
        if (component != null) {
            sender.sendMessage(component);
        }
    }
}