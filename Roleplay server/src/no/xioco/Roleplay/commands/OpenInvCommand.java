package no.xioco.Roleplay.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Thomas on 27.10.2014.
 */
public class OpenInvCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String cmdl, String[] args){
        if(cmd.getName().equalsIgnoreCase("open")){
            if(!(sender instanceof Player)){
                sender.sendMessage("Du ma vere ingame for a bruke denne kommandoen.");
            }else{
                Player player = (Player) sender;
                if(player.hasPermission("xioco.openinv")) {
                    if(args.length == 1) {
                        Player targetPlayer;
                        targetPlayer = Bukkit.getPlayer(args[0]);
                        if(targetPlayer == null){
                            player.sendMessage(ChatColor.RED + "Brukeren " + ChatColor.WHITE + args[0] + ChatColor.RED + " er ikke pålogget!");
                            return true;
                        }
                        player.openInventory(targetPlayer.getInventory());
                    }else if(args.length == 2) {
                        if(args[1].equalsIgnoreCase("enderchest") || args[1].equalsIgnoreCase("ender")){
                            Player targetPlayer = Bukkit.getPlayer(args[0]);
                            player.openInventory(targetPlayer.getEnderChest());
                        }else{
                            player.sendMessage(ChatColor.RED + "Feil argumenter! Bruk /open [brukernavn] <enderchest>");
                        }
                    }else{
                        sender.sendMessage(ChatColor.RED + "Feil argumenter! Bruk /open <brukernavn|enderchest> <brukernavn>");
                    }
                }else{
                    player.sendMessage(ChatColor.RED + "Du har ikke tilgang til denne kommandoen.");
                }
            }
        }

        return true;
    }

}
