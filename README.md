# Gateway [![Latest Release](https://img.shields.io/github/v/release/npmsam/Gateway?label=Latest%20Release&style=flat)](https://github.com/npmsam/Gateway/releases)

🎉 Gateway is a simple and modern Paper plugin that replaces the vanilla whitelist system and supports
both online and offline mode player connections.
Unlike the vanilla whitelist, it stores both online and offline user IDs when you whitelist a player.

## ✨ Features

The plugin replaces the vanilla whitelist command with the following subcommands, which are pretty similar to the vanilla command.

- `/whitelist list` -> view all whitelisted players
- `/whitelist on` / `/whitelist off` -> enable or disable the whitelist
- `/whitelist add <player>` -> add a player to the whitelist
- `/whitelist remove <player>` -> remove a player from the whitelist
- `/whitelist reload` -> reload the configuration file

Whitelisted players are stored in the `whitelist.json` file inside the plugin’s data folder.
You can manually add a player using the structure below and reload the plugin to apply the changes.

```yml
[
  {
    "username": "Notch",
    "onlineUuid": "069a79f4-44e9-4726-a5be-fca90e38aaf5",
    "offlineUuid": "b50ad385-829d-3141-a216-7e7d7539ba7f"
  },
  ...
]
```

You can change the message shown to non-whitelisted players in `config.yml`.
Both classic Minecraft [`&` color/formatting codes](https://minecraft.wiki/w/Formatting_codes) and PaperMC [MiniMessage formatting](https://docs.papermc.io/adventure/minimessage/format/) are supported.

## 📦 Download

You can download a ready to use JAR file from the [GitHub releases](https://github.com/npmsam/Gateway/releases) page or
the [Hangar](https://hangar.papermc.io/Samboii/Gateway) page.
This is the easiest option if you do not want to build the plugin yourself.

The default config file includes all supported events and built in placeholders.
Reading it is a simple way to learn how the plugin works and get started quickly.

## 🔧 Building

If you want to build the plugin JAR file yourself, you can use [Apache Maven](https://maven.apache.org/).

Open a terminal or command prompt, go to the plugin folder you cloned or extracted,
and run the following command

```bash
mvn clean package
```

## 🧾 Licensing

This plugin is licensed under the [MIT License](https://en.wikipedia.org/wiki/MIT_License).

You are free to use, copy, modify, merge, publish, distribute, sublicense,
and sell copies of the plugin, as long as the original copyright notice
and this permission notice are included.

See the [LICENSE](https://github.com/npmsam/Gateway/blob/main/LICENSE) file for full license details.