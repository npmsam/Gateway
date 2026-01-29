package com.sampreet.gateway.helpers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class PlayerUuid {
    public static @Nullable UUID onlineUuid(String username) throws IOException {
        URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + username);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        if (connection.getResponseCode() != 200) return null;

        try (Reader reader = new InputStreamReader(connection.getInputStream())) {
            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
            String rawUuid = json.get("id").getAsString();
            return parseMojangUuid(rawUuid);
        }
    }

    public static @NotNull UUID offlineUuid(String username) {
        return UUID.nameUUIDFromBytes(
                ("OfflinePlayer:" + username).getBytes(StandardCharsets.UTF_8)
        );
    }

    public static @NotNull PlayerEntry getPlayerEntry(String username) {
        UUID onlineUuid;
        try {
            onlineUuid = PlayerUuid.onlineUuid(username);
        } catch (IOException exception) {
            onlineUuid = null;
        }

        UUID offlineUuid = PlayerUuid.offlineUuid(username);

        PlayerEntry playerEntry = new PlayerEntry();
        playerEntry.username = username;
        playerEntry.onlineUuid = onlineUuid;
        playerEntry.offlineUuid = offlineUuid;

        return playerEntry;
    }

    public static @NotNull UUID parseMojangUuid(@NotNull String raw) {
        return UUID.fromString(
                raw.replaceFirst(
                        "(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})",
                        "$1-$2-$3-$4-$5"
                )
        );
    }
}