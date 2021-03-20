package id.brokiem.utils;

import dev.waterdog.plugin.Plugin;
import id.brokiem.utils.command.*;

public class UtilsLoader extends Plugin {

    @Override
    public void onEnable() {
        getProxy().getCommandMap().registerCommand("sendto", new SendToCommand("sendto"));
        getProxy().getCommandMap().registerCommand("kickplayer", new KickPlayerCommand("kickplayer"));
        getProxy().getCommandMap().registerCommand("ping", new PingCommand("ping"));
        getProxy().getCommandMap().registerCommand("bc", new BroadcastCommand("bc"));
        getProxy().getCommandMap().registerCommand("find", new FindCommand("find"));
        getProxy().getCommandMap().registerCommand("ip", new IpCommand("ip"));
    }
}
