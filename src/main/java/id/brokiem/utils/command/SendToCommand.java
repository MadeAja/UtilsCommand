package id.brokiem.utils.command;

import dev.waterdog.command.Command;
import dev.waterdog.command.CommandSender;
import dev.waterdog.command.CommandSettings;
import dev.waterdog.player.ProxiedPlayer;

public class SendToCommand extends Command {

    public SendToCommand(String name) {
        super(name, CommandSettings.builder()
                .setDescription("send to player server")
                .setPermission("utils.sendto.command")
                .build()
        );
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String s, String[] strings) {
        if (commandSender instanceof ProxiedPlayer) {
            if (strings.length > 0) {
                ProxiedPlayer player = commandSender.getProxy().getPlayer(strings[0]);

                if (player != null) {
                    commandSender.sendMessage("§aYou will connected to §2" + player.getName() + " §aserver (§2" + player.getServer().getInfo().getServerName() + "§a)");
                    ((ProxiedPlayer) commandSender).connect(player.getServer().getInfo());
                    return true;
                }

                commandSender.sendMessage("§cPlayer §4" + strings[0] + " §cnot found!");
                return true;
            }

            commandSender.sendMessage("Usage: /sendto <player>");
            return true;
        }

        commandSender.sendMessage("§cOnly player can run this command!");

        return false;
    }
}
