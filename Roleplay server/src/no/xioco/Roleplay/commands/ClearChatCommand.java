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
public class ClearChatCommand implements CommandExecutor{

    public boolean onCommand(CommandSender sender, Command cmd, String cmdl, String[] args){

        if(cmd.getName().equalsIgnoreCase("clearchat")){
            if(!(sender instanceof Player)){
                sender.sendMessage("Du ma vere ingame for a bruke denne kommandoen!");
            }else {
                Player player = (Player) sender;
                if (player.hasPermission("xioco.clearchat")) {
                    for (int i = 0; i < 100; i++) {
                        Bukkit.broadcastMessage("");
                    }
                    Bukkit.broadcastMessage(ChatColor.GREEN + "Chatten ble tømt av " + ChatColor.RESET + player.getName());
                }else{
                    player.sendMessage(ChatColor.RED + "Du har ikke tilgang til denne kommandoen!");
                }
            }
        }

        return true;
    }

}
