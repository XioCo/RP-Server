package no.xioco.Listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;

/**
 * Created by Thomas on 07.11.2014.
 */
public class EntityListeners implements Listener {

    @EventHandler
    public void onEntityBlockChange(EntityChangeBlockEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onTNTExplode(ExplosionPrimeEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event){
        event.setCancelled(true);
        event.blockList().clear();
    }

}
