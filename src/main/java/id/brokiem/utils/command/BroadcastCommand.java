package id.brokiem.utils.command;

import dev.waterdog.command.Command;
import dev.waterdog.command.CommandSender;
import dev.waterdog.command.CommandSettings;
import dev.waterdog.player.ProxiedPlayer;

public class BroadcastCommand extends Command {

    public BroadcastCommand(String name) {
        super(name, CommandSettings.builder()
                .setDescription("broadcast message to all players in the proxy")
                .setPermission("utils.broadcast.command")
                .build()
        );
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String s, String[] strings) {
        if (strings.length > 0) {
            for (ProxiedPlayer p : commandSender.getProxy().getPlayers().values()) {
                p.sendMessage("§l §a» " + String.join(" ", strings));
                return true;
            }
        }

        commandSender.sendMessage("Usage: /bc <message>");

        return false;
    }
}
