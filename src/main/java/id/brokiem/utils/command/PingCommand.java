package id.brokiem.utils.command;

import com.nukkitx.network.raknet.RakNetPong;
import dev.waterdog.command.Command;
import dev.waterdog.command.CommandSender;
import dev.waterdog.command.CommandSettings;
import dev.waterdog.network.ServerInfo;
import dev.waterdog.player.ProxiedPlayer;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class PingCommand extends Command {

    public PingCommand(String name) {
        super(name, CommandSettings.builder()
                .setDescription("get your latency from downstream server")
                .build()
        );
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String s, String[] strings) {
        if (commandSender instanceof ProxiedPlayer) {
            final ServerInfo targetServer = ((ProxiedPlayer) commandSender).getServer().getInfo();

            if (targetServer != null) {
                CompletableFuture<RakNetPong> request = targetServer.ping(3, TimeUnit.SECONDS);
                request.whenComplete((response, t) -> {
                    if (t == null && response != null) {
                        commandSender.sendMessage("&7Your ping is &a" + (response.getPongTime() - response.getPingTime()) + "ms");
                    } else if (t != null) {
                        commandSender.sendMessage("§cError while pinging. §c: " + t.getMessage());
                    } else {
                        commandSender.sendMessage("§cAn error occured while pinging :(");
                    }
                });

                return true;
            }
        }

        commandSender.sendMessage("Usage: /ping");

        return true;
    }
}
