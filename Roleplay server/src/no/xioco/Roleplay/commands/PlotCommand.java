package no.xioco.Roleplay.commands;

import no.xioco.Handlers.ChunkInfo;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.UUID;

/**
 * Created by Thomas on 26.10.2014.
 */
public class PlotCommand implements CommandExecutor{

    public static HashMap<ChunkInfo, UUID> claimedChunks = new HashMap<ChunkInfo, UUID>();
    public static HashSet<ChunkInfo> chunks = new HashSet<ChunkInfo>();

    public boolean onCommand(CommandSender sender, Command cmd, String cmdl, String[] args){
        if(cmd.getName().equalsIgnoreCase("tomt")){
            if(!(sender instanceof Player)){
                sender.sendMessage("Du ma vere ingame for a bruke denne kommandoen! :(");
            }else {
                Player player = (Player) sender;
                if(args.length >= 1) {
                    switch (args[0].toLowerCase()) {
                        case "info":
                            Iterator it = chunks.iterator();
                            while(it.hasNext()){
                                player.sendMessage(String.valueOf(it.next()));
                                System.out.println(it.next());
                            }
                            break;
                        case "ny":
                            Chunk chunk = player.getLocation().getChunk();
                                World world = chunk.getWorld();
                                createPlot(chunk, player);
                                player.sendMessage(ChatColor.GREEN + "Du lagde en ny tomt på følgende koordinater: X: " + chunk.getX() + " Z: " + chunk.getZ());
                                break;

                        case "":
                            break;
                        default:
                            player.sendMessage(ChatColor.RED + "Feil argumenter! Bruk /tomt <ny/info>");
                    }
                }else{
                    player.sendMessage("Feil argumenter :( Bruk /tomt ny");
                }
            }
        }
        return true;
    }

    public void createPlot(Chunk chunk, Player player){
        int x = chunk.getX();
        int z = chunk.getZ();
        World world = chunk.getWorld();
        claimedChunks.put(new ChunkInfo(x, z, world), player.getUniqueId());
        //chunks.add(new ChunkInfo(x, z, player, world));
    }
}
