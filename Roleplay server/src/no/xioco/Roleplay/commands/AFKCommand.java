package no.xioco.Roleplay.commands;

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
public class AFKCommand implements CommandExecutor {
    public static HashMap<UUID, Integer> AFK = new HashMap<UUID, Integer>();

    long seconds = 1 * 1000;

    public boolean onCommand(CommandSender sender, Command cmd, String cmdl, String[] args){
        if(!(sender instanceof Player)){
            sender.sendMessage("Du ma vere ingame for a bruke denne kommandoen!");
        }else{
            Player player = (Player) sender;
                AFK.remove(player.getUniqueId());
                player.sendMessage(ChatColor.RED + "Du er ikke lenger AFK!");
                player.sendMessage(ChatColor.GREEN + "Du er nå AFK!");

        }
        return true;
    }

}
