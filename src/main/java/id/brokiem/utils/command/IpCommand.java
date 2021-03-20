package id.brokiem.utils.command;

import dev.waterdog.command.Command;
import dev.waterdog.command.CommandSender;
import dev.waterdog.command.CommandSettings;
import dev.waterdog.player.ProxiedPlayer;

public class IpCommand extends Command {

    public IpCommand(String name) {
        super(name, CommandSettings.builder()
                .setDescription("get the player ip in the proxy")
                .setPermission("utils.ip.command")
                .build()
        );
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String s, String[] strings) {
        if (strings.length > 0) {
            ProxiedPlayer player = commandSender.getProxy().getPlayer(strings[0]);

            if (player != null) {
                commandSender.sendMessage("§a" + player.getName() + " ip is §2" + player.getAddress());
                return true;
            }

            commandSender.sendMessage("§cPlayer §4" + strings[0] + " §cnot found!");
            return true;
        }

        commandSender.sendMessage("Usage: /ip <player>");

        return true;
    }
}
