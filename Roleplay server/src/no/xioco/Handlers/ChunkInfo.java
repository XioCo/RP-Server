package no.xioco.Handlers;

import org.bukkit.entity.Player;

/**
 * Created by Thomas on 26.10.2014.
 */
public class ChunkInfo {

    int x;
    int z;
    Player player;

    public ChunkInfo(int x, int z, Player player){
        this.x = x;
        this.z = z;
        this.player = player;
    }

    public int getX(){
        return this.x;
    }

    public int getZ(){
        return this.z;
    }

    public Player getPlayer(){
        return this.player;
    }
}
