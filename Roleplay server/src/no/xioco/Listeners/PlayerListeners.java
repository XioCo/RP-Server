package no.xioco.Listeners;

import no.xioco.Handlers.ChunkHandler;
import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

/**
 * Created by Thomas on 26.10.2014.
 */
public class PlayerListeners implements Listener{

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        Block b = event.getBlock();
        Chunk chunk = b.getChunk();
        ChunkHandler ch = new ChunkHandler();
        if(chunk == ch.getChunk(b.getWorld(), player)){
            player.sendMessage("Funker! =D =D");
        }else{
            player.sendMessage("Funker ikke! =( =(");
        }
    }
}
