package com.sampreet.gateway.helpers;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class MessagesHelper {
    public static Component translateColors(String message) {
        if (message == null)
            return null;

        Component messageComponent = MiniMessage.miniMessage().deserialize(message);

        String legacySerialized = LegacyComponentSerializer.legacyAmpersand().serialize(messageComponent);

        return LegacyComponentSerializer.legacyAmpersand().deserialize(legacySerialized);
    }
}