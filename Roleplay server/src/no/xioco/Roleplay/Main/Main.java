package no.xioco.Roleplay.Main;

import no.xioco.Listeners.PlayerListeners;
import no.xioco.Roleplay.commands.PlotCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by jimbo8 on 26.10.2014.
 */
public class Main extends JavaPlugin{

    @Override
    public void onEnable(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerListeners(), this);
        getCommand("tomt").setExecutor(new PlotCommand());
    }

    @Override
    public void onDisable(){

    }

}
