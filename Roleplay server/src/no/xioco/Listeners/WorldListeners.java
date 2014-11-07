package no.xioco.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

/**
 * Created by Thomas on 07.11.2014.
 */
public class WorldListeners implements Listener {

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event){
        if(event.getWorld().hasStorm()){
            event.getWorld().setStorm(false);
        }else{
            event.setCancelled(true);
        }
    }

}
