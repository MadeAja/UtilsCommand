package id.brokiem.utils.command;

import dev.waterdog.ProxyServer;
import dev.waterdog.command.Command;
import dev.waterdog.command.CommandSender;
import dev.waterdog.command.CommandSettings;
import dev.waterdog.network.ServerInfo;

import java.net.InetSocketAddress;

public class AddServerCommand extends Command {

    public AddServerCommand(String name) {
        super(name, CommandSettings.builder()
                .setDescription("add server to the proxy")
                .setPermission("utils.addserver.command")
                .build()
        );
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String s, String[] strings) {
        if (strings.length < 3) {
            commandSender.sendMessage("§cUsage: /addserver <name> <ip> <port>");
            return true;
        }

        ServerInfo i = new ServerInfo(strings[0], new InetSocketAddress(strings[1], Integer.parseInt(strings[2])), null);
        ProxyServer.getInstance().registerServerInfo(i);
        commandSender.sendMessage("§aServer added successfully!");

        return true;
    }
}
