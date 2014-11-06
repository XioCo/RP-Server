package no.xioco.Roleplay.Main;

import no.xioco.Listeners.PlayerListeners;
import no.xioco.Roleplay.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by jimbo8 on 26.10.2014.
 */
public class Main extends JavaPlugin{

    Main plugin;

    @Override
    public void onEnable(){
        this.saveDefaultConfig();

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerListeners(this), this);
        getCommand("tomt").setExecutor(new PlotCommand());
        getCommand("hide").setExecutor(new HideCommand());
        getCommand("ri").setExecutor(new MountCommand());
        getCommand("open").setExecutor(new OpenInvCommand());
        getCommand("AFK").setExecutor(new AFKCommand());
        getCommand("clearchat").setExecutor(new ClearChatCommand());
        getCommand("frys").setExecutor(new FreezeCommand());
        getCommand("tin").setExecutor(new UnfreezeCommand());
        getCommand("info").setExecutor(new BroadcastCommand());
        getCommand("viktig").setExecutor(new BroadcastCommand());
        getCommand("c").setExecutor(new AdminChatCommand());
    }

    @Override
    public void onDisable(){

    }

}
