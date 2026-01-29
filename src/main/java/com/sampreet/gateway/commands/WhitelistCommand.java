package com.sampreet.gateway.commands;

import com.sampreet.gateway.Gateway;
import com.sampreet.gateway.helpers.MessagesHelper;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WhitelistCommand implements CommandExecutor {
    private final Gateway plugin;

    private final Map<String, SubCommand> subCommands = new HashMap<>();

    public WhitelistCommand(Gateway plugin) {
        this.plugin = plugin;

        subCommands.put("on", new OnCommand(plugin));
        subCommands.put("off", new OffCommand(plugin));
        subCommands.put("add", new AddCommand(plugin));
        subCommands.put("list", new ListCommand(plugin));
        subCommands.put("remove", new RemoveCommand(plugin));
        subCommands.put("reload", new ReloadCommand(plugin));
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (args.length == 0) {
            sendMessage(sender, "messages.commands.errors.no_command");
            return true;
        }

        SubCommand subCommand = subCommands.get(args[0].toLowerCase());
        if (subCommand == null) {
            sendMessage(sender, "messages.commands.errors.invalid_command");
            return true;
        }

        String[] subArgs = Arrays.copyOfRange(args, 1, args.length);
        subCommand.execute(sender, subArgs);
        return true;
    }

    private void sendMessage(CommandSender sender, String path) {
        Component message = MessagesHelper.translateColors(plugin.getConfig().getString(path));
        if (message == null) return;

        sender.sendMessage(message);
    }
}