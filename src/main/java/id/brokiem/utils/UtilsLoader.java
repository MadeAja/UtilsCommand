package id.brokiem.utils;

import dev.waterdog.plugin.Plugin;
import id.brokiem.utils.command.BroadcastCommand;
import id.brokiem.utils.command.KickPlayerCommand;
import id.brokiem.utils.command.PingCommand;
import id.brokiem.utils.command.SendToCommand;

public class UtilsLoader extends Plugin {

    @Override
    public void onEnable() {
        getProxy().getCommandMap().registerCommand("sendto", new SendToCommand("sendto"));
        getProxy().getCommandMap().registerCommand("kickplayer", new KickPlayerCommand("kickplayer"));
        getProxy().getCommandMap().registerCommand("ping", new PingCommand("ping"));
        getProxy().getCommandMap().registerCommand("bc", new BroadcastCommand("bc"));
    }
}
