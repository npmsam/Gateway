package com.sampreet.gateway.commands;

import com.sampreet.gateway.Gateway;
import com.sampreet.gateway.helpers.MessagesHelper;
import com.sampreet.gateway.helpers.PlayerEntry;
import com.sampreet.gateway.helpers.PlayerUuid;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class AddCommand extends SubCommand {

    public AddCommand(Gateway plugin) {
        super(plugin);
    }

    @Override
    public void execute(CommandSender sender, String @NotNull [] args) {
        if (!sender.hasPermission("whitelist.modify")) {
            sendMessage(sender, "messages.commands.errors.no_permission", null);
            return;
        }

        if (args.length == 0) {
            sendMessage(sender, "messages.commands.errors.no_player", null);
            return;
        }

        String username = args[0];

        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
            PlayerEntry playerEntry = PlayerUuid.getPlayerEntry(username);

            plugin.getServer().getScheduler().runTask(plugin, () -> handleAdd(sender, username, playerEntry));
        });
    }

    private void handleAdd(CommandSender sender, String username, PlayerEntry playerEntry) {
        if (plugin.getJsonHelper().check(playerEntry)) {
            sendMessage(sender, "messages.commands.errors.already_whitelisted", username);
            return;
        }

        plugin.getJsonHelper().add(playerEntry);
        plugin.getJsonHelper().save();

        sendMessage(sender, "messages.commands.add", username);
    }

    private void sendMessage(CommandSender sender, String path, String player) {
        String message = plugin.getConfig().getString(path);
        if (message == null || message.trim().isEmpty()) return;

        if (player != null) {
            message = message.replace("<player>", player);
        }

        Component component = MessagesHelper.translateColors(message);
        if (component != null) {
            sender.sendMessage(component);
        }
    }
}