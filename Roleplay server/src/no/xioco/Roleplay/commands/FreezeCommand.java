package no.xioco.Roleplay.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Thomas on 29.10.2014.
 */
public class FreezeCommand implements CommandExecutor{

    public static HashMap<UUID, UUID> frozenPlayers = new HashMap<UUID, UUID>();

    public boolean onCommand(CommandSender sender, Command cmd, String cmdl, String[] args){
        if(cmd.getName().equalsIgnoreCase("frys")){
            if(!(sender instanceof Player)){
                sender.sendMessage("Du ma vere ingame for a bruke denne kommandoen!");
            }else{
                Player player = (Player) sender;
                if(player.hasPermission("xioco.frys")) {
                    if (args.length == 1) {
                        Player targetPlayer = Bukkit.getPlayer(args[0]);
                        if(targetPlayer == null){
                            player.sendMessage(ChatColor.RED + "Brukeren " + ChatColor.RESET + args[0] + ChatColor.RED + " er ikke pålogget!");
                            return true;
                        }
                        if(!frozenPlayers.containsValue(targetPlayer.getUniqueId())) {
                            frozenPlayers.put(player.getUniqueId(), targetPlayer.getUniqueId());
                        }else{
                            player.sendMessage(ChatColor.RED + "Denne brukeren er allerede fryst!");
                            return true;
                        }
                        player.sendMessage(ChatColor.RED + "Du frøs " + ChatColor.RESET + targetPlayer.getName() + "!");
                        targetPlayer.sendMessage(ChatColor.RED + "Du har blitt fryst av et stabmedlem.");
                    } else {
                        player.sendMessage(ChatColor.RED + "Feil argumenter! Bruk /frys <spiller>");
                    }
                }
            }
        }
        return true;
    }

}
