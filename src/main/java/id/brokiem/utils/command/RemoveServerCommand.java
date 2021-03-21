package id.brokiem.utils.command;

import dev.waterdog.ProxyServer;
import dev.waterdog.command.Command;
import dev.waterdog.command.CommandSender;
import dev.waterdog.command.CommandSettings;

public class RemoveServerCommand extends Command {

    public RemoveServerCommand(String name) {
        super(name, CommandSettings.builder()
                .setDescription("remove server to the proxy")
                .setPermission("utils.removeserver.command")
                .build()
        );
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String s, String[] strings) {
        if (strings.length == 0) {
            commandSender.sendMessage("§cUsage: /removeserver <server>");
            return true;
        }

        if (ProxyServer.getInstance().removeServerInfo(strings[0]) != null) {
            commandSender.sendMessage("§aServer removed successfully!");
            return true;
        }

        commandSender.sendMessage("§cA server with that name could not be found");
        return false;
    }
}
