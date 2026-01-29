package com.sampreet.gateway.helpers;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sampreet.gateway.Gateway;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

public class JsonHelper {
    private final Gateway plugin;
    private final File file;
    private final Gson gson;

    private List<PlayerEntry> entries = new ArrayList<>();

    public JsonHelper(File dataFolder, Gateway plugin) {
        this.plugin = plugin;
        this.file = new File(dataFolder, "whitelist.json");
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void load() {
        if (!file.exists()) {
            save();
            return;
        }

        Type type = new TypeToken<List<PlayerEntry>>() {
        }.getType();

        try (Reader reader = new FileReader(file)) {
            List<PlayerEntry> loaded = gson.fromJson(reader, type);
            if (loaded != null) entries = loaded;
        } catch (IOException exception) {
            String errorMessage = plugin.getConfig().getString("messages.errors.loading-failed");
            if (errorMessage == null || errorMessage.trim().isEmpty())
                return;

            plugin.getLogger().log(Level.SEVERE, errorMessage, exception);
        }
    }

    public void save() {
        try (Writer writer = new FileWriter(file)) {
            gson.toJson(entries, writer);
        } catch (IOException exception) {
            String errorMessage = plugin.getConfig().getString("messages.errors.saving-failed");
            if (errorMessage == null || errorMessage.trim().isEmpty())
                return;

            plugin.getLogger().log(Level.SEVERE, errorMessage, exception);
        }
    }

    public PlayerEntry get(UUID uuid) {
        if (uuid == null) return null;

        for (PlayerEntry entry : entries) {
            if (uuid.equals(entry.onlineUuid) || uuid.equals(entry.offlineUuid)) {
                return entry;
            }
        }

        return null;
    }

    public boolean check(UUID uuid) {
        return uuid != null && get(uuid) != null;
    }

    public boolean check(PlayerEntry playerEntry) {
        if (playerEntry == null) return false;

        UUID online = playerEntry.onlineUuid;
        UUID offline = playerEntry.offlineUuid;

        return (online != null && get(online) != null) ||
                (offline != null && get(offline) != null);
    }

    public boolean add(@NotNull PlayerEntry entry) {
        if (entry.onlineUuid != null && get(entry.onlineUuid) != null) return false;
        if (entry.offlineUuid != null && get(entry.offlineUuid) != null) return false;

        entries.add(entry);
        return true;
    }

    public boolean remove(UUID uuid) {
        PlayerEntry entry = get(uuid);
        if (entry == null) return false;

        entries.remove(entry);
        return true;
    }

    public List<PlayerEntry> list() {
        return List.copyOf(entries);
    }
}