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
            final String playerName = strings[0];
            final ProxiedPlayer p = ProxyServer.getInstance().getPlayer(playerName);
            if (p != null) {
                p.disconnect(strings[1], false);
                commandSender.sendMessage("§aPlayer kicked successfully.");
                return true;
            }

            commandSender.sendMessage("§cPlayer with the name §e" + strings[0] + " §ccould not be found");
            return true;
        }

        commandSender.sendMessage("Usage: /kickplayer <player>");

        return false;
    }
}
