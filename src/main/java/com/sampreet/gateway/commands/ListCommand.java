package com.sampreet.gateway.commands;

import com.sampreet.gateway.Gateway;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ListCommand extends SubCommand {
    public ListCommand(Gateway plugin) {
        super(plugin);
    }

    @Override
    public void execute(CommandSender sender, String @NotNull [] args) {
    }
}