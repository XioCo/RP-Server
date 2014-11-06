package no.xioco.Roleplay.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.UUID;

/**
 * Created by Thomas on 27.10.2014.
 */
public class HideCommand implements CommandExecutor{

    public static HashSet<UUID> hiddenPlayers = new HashSet<UUID>();

    public boolean onCommand(CommandSender sender, Command cmd, String cmdl, String[] args){

        if(cmd.getName().equalsIgnoreCase("hide")){
            if(!(sender instanceof Player)){
                sender.sendMessage("Du ma vere ingame for a bruke denne kommandoen!");
            }else{
                Player player = (Player) sender;
                if(player.hasPermission("xioco.ghost")) {
                    if (hiddenPlayers.contains(player.getUniqueId())){
                        for(Player oP : Bukkit.getOnlinePlayers()){
                            if(!oP.hasPermission("xioco.ghost")){
                                oP.showPlayer(player);
                            }
                        }
                        hiddenPlayers.remove(player.getUniqueId());
                        player.sendMessage(ChatColor.RED + "Du er ikke lenger usynlig!");
                    }else{
                        for(Player oP : Bukkit.getServer().getOnlinePlayers()){
                            if(!oP.hasPermission("xioco.ghost")){
                                oP.hidePlayer(player);
                            }
                        }
                        hiddenPlayers.add(player.getUniqueId());
                        player.sendMessage(ChatColor.GREEN + "Du er nå usynlig!");
                    }
                }else{
                    player.sendMessage(ChatColor.RED + "Du har ikke tilgang til denne kommandoen.");
                }
            }
        }

        return true;
    }

}
