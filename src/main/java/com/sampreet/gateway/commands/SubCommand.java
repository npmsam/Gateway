package com.sampreet.gateway.commands;

import com.sampreet.gateway.Gateway;
import org.bukkit.command.CommandSender;

public abstract class SubCommand {
    protected final Gateway plugin;

    protected SubCommand(Gateway plugin) {
        this.plugin = plugin;
    }

    public abstract void execute(CommandSender sender, String[] args);
}