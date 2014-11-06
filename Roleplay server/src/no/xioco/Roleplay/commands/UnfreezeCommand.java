package no.xioco.Roleplay.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Thomas on 29.10.2014.
 */
public class UnfreezeCommand implements CommandExecutor{

    public boolean onCommand(CommandSender sender, Command cmd, String cmdl, String[] args){
        if(cmd.getName().equalsIgnoreCase("tin")){
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
                        if(FreezeCommand.frozenPlayers.containsValue(targetPlayer.getUniqueId())) {
                            FreezeCommand.frozenPlayers.values().remove(targetPlayer.getUniqueId());
                        }else{
                            player.sendMessage(ChatColor.RED + "Denne brukeren er ikke fryst fra før!");
                            return true;
                        }
                        player.sendMessage(ChatColor.GREEN + "Du tinet opp " + ChatColor.RESET + targetPlayer.getName() + ChatColor.GREEN + "!");
                        targetPlayer.sendMessage(ChatColor.GREEN + "Du har blitt tinet opp av et stabmedlem.");
                    } else {
                        player.sendMessage(ChatColor.RED + "Feil argumenter! Bruk /tin <spiller>");
                    }
                }
            }
        }
        return true;
    }


}
