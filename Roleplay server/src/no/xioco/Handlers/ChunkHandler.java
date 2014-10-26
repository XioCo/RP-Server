package no.xioco.Handlers;

import no.xioco.Roleplay.commands.PlotCommand;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 * Created by Thomas on 26.10.2014.
 */
public class ChunkHandler {
    public Chunk getChunk(World world, Player player){
        int x = PlotCommand.claimedChunks.get(player).getX();
        int z = PlotCommand.claimedChunks.get(player).getZ();
        return Bukkit.getWorld(world.getName()).getChunkAt(x, z);
    }
}
