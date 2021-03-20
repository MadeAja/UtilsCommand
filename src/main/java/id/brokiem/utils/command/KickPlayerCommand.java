package id.brokiem.utils.command;

import dev.waterdog.ProxyServer;
import dev.waterdog.command.Command;
import dev.waterdog.command.CommandSender;
import dev.waterdog.command.CommandSettings;
import dev.waterdog.player.ProxiedPlayer;

public class KickPlayerCommand extends Command {

    public KickPlayerCommand(String name) {
        super(name, CommandSettings.builder()
                .setDescription("kick specified player in the proxy")
                .setPermission("utils.kickplayer.command")
                .build()
        );
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String s, String[] strings) {
        if (strings.length > 0) {
            final ProxiedPlayer player = ProxyServer.getInstance().getPlayer(strings[0]);
            if (player != null) {
                player.disconnect(strings[1], false);
                commandSender.sendMessage("§aPlayer kicked successfully.");
                return true;
            }

            commandSender.sendMessage("§cPlayer §4" + strings[0] + " §cnot found!");
            return true;
        }

        commandSender.sendMessage("Usage: /kickplayer <player>");

        return true;
    }
}
