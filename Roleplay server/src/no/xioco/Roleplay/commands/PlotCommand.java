package no.xioco.Roleplay.commands;

import no.xioco.Handlers.ChunkInfo;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Thomas on 26.10.2014.
 */
public class PlotCommand implements CommandExecutor{

    public static HashMap<UUID, ChunkInfo> claimedChunks = new HashMap<UUID, ChunkInfo>();

    public boolean onCommand(CommandSender sender, Command cmd, String cmdl, String[] args){
        if(cmd.getName().equalsIgnoreCase("tomt")){
            if(!(sender instanceof Player)){
                sender.sendMessage("Du ma vere ingame for a bruke denne kommandoen! :(");
            }else {
                Player player = (Player) sender;
                if (args.length >= 1 && args.length <= 3) {
                    switch (args[0].toLowerCase()) {
                        case "info":
                            break;
                        case "ny":
                            Chunk chunk = player.getLocation().getChunk();
                            createPlot(chunk, player);
                            break;
                        case "":
                            break;
                        default:
                            player.sendMessage(ChatColor.RED + "Feil argumenter! Bruk /tomt <ny/info>");
                    }
                }
            }
        }
        return true;
    }

    public void createPlot(Chunk chunk, Player player){
        int x = chunk.getX();
        int z = chunk.getZ();
        claimedChunks.put(player.getUniqueId(), new ChunkInfo(x, z, player));
    }
}
