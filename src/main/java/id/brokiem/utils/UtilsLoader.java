package id.brokiem.utils;

import dev.waterdog.event.Event;
import dev.waterdog.event.defaults.PlayerDisconnectEvent;
import dev.waterdog.network.ServerInfo;
import dev.waterdog.plugin.Plugin;
import id.brokiem.utils.command.*;

public class UtilsLoader extends Plugin {

    @Override
    public void onEnable() {
        getProxy().getCommandMap().registerCommand("sendto", new SendToCommand("sendto"));
        getProxy().getCommandMap().registerCommand("kickplayer", new KickPlayerCommand("kickplayer"));
        getProxy().getCommandMap().registerCommand("bc", new BroadcastCommand("bc"));
        getProxy().getCommandMap().registerCommand("find", new FindCommand("find"));
        getProxy().getCommandMap().registerCommand("ip", new IpCommand("ip"));
        getProxy().getCommandMap().registerCommand("send", new SendCommand("send"));
        getProxy().getCommandMap().registerCommand("tell", new TellCommand("tell"));
        getProxy().getCommandMap().registerCommand("removeserver", new RemoveServerCommand("removeserver"));
        getProxy().getCommandMap().registerCommand("addserver", new AddServerCommand("addserver"));

        getProxy().getEventManager().subscribe(PlayerDisconnectEvent.class, this::onDisconnect);
    }

    private void onDisconnect(PlayerDisconnectEvent event) {
        if (event.getReason().contains("kicked")) {
            return;
        }

        event.setCancelled();
        event.getPlayer().sendMessage("§cServer is down. Connecting to the Lobby ...");
        event.getPlayer().connect(getProxy().getServerInfo("lobby-2"));
    }
}
