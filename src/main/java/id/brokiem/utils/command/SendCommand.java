package id.brokiem.utils.command;

import dev.waterdog.command.Command;
import dev.waterdog.command.CommandSender;
import dev.waterdog.command.CommandSettings;
import dev.waterdog.network.ServerInfo;
import dev.waterdog.player.ProxiedPlayer;

public class SendCommand extends Command {

    public SendCommand(String name) {
        super(name, CommandSettings.builder()
                .setDescription("sends the specified player(s) to the specified server")
                .setPermission("utils.send.command")
                .build()
        );
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String s, String[] strings) {
        if (commandSender instanceof ProxiedPlayer) {
            if (strings.length > 0) {
                ServerInfo server = commandSender.getProxy().getServerInfo(strings[1]);

                if (server == null) {
                    commandSender.sendMessage("§cServer " + strings[1] + " not found!");
                    return true;
                }

                if (strings[0].equals("all")) {
                    for (ProxiedPlayer player : commandSender.getProxy().getPlayers().values()) {
                        player.connect(server);
                    }

                    return true;
                }

                if (strings[0].equals("current")) {
                    for (ProxiedPlayer player : ((ProxiedPlayer) commandSender).getServerInfo().getPlayers()) {
                        player.connect(server);
                    }

                    return true;
                }

                ProxiedPlayer player = commandSender.getProxy().getPlayer(strings[0]);
                player.connect(server);
                return true;
            }

            commandSender.sendMessage("Usage: /send <player/current/all> <target server>");
            return true;
        }

        commandSender.sendMessage("§cOnly player can run this command!");

        return true;
    }
}
