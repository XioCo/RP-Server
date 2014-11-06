package no.xioco.Handlers;

import org.bukkit.World;

/**
 * Created by Thomas on 26.10.2014.
 */
public class ChunkInfo {

    int x;
    int z;
    World world;

    public ChunkInfo(int x, int z, World world){
        this.x = x;
        this.z = z;
        this.world = world;
    }

    public int getX(){
        return this.x;
    }

    public int getZ(){
        return this.z;
    }

    public World getWorld() { return this.world; }
}
