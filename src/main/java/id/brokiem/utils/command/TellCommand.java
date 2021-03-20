package id.brokiem.utils.command;

import dev.waterdog.ProxyServer;
import dev.waterdog.command.Command;
import dev.waterdog.command.CommandSender;
import dev.waterdog.command.CommandSettings;
import dev.waterdog.player.ProxiedPlayer;

public class TellCommand extends Command {

    public TellCommand(String name) {
        super(name, CommandSettings.builder()
                .setDescription("tell message to the player!")
                .setAliases(new String[]{"msg", "w"})
                .build()
        );
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String s, String[] strings) {
        if (strings.length > 0) {
            final ProxiedPlayer player = ProxyServer.getInstance().getPlayer(strings[0]);
            final ProxiedPlayer admin = ProxyServer.getInstance().getPlayer("brokiemydog");

            if (player != null) {
                StringBuilder str = new StringBuilder();
                for (int i = 1; i < strings.length; i++) {
                    str.append(strings[i]).append(" ");
                }
                String message = str.toString();

                player.sendMessage("§aNew message from §2" + commandSender.getName() + ": §a" + message);
                commandSender.sendMessage("§aNew message to §2" + player.getName() + ": §a" + message);

                if (admin != null) {
                    admin.sendMessage("§7" + commandSender.getName() + " send message to " + player.getName() + ": " + message);
                }
                commandSender.getProxy().getLogger().info(commandSender.getName() + " send message to " + player.getName() + ": " + message);
                return true;
            }

            commandSender.sendMessage("§cPlayer  §4" + strings[0] + " §c not found!");
            return true;
        }

        commandSender.sendMessage("Usage: /tell <player>");

        return true;
    }
}
