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
public class TempbanCommand implements CommandExecutor{

    public boolean onCommand(CommandSender sender, Command cmd, String cmdl, String[] args){

        if(cmd.getName().equalsIgnoreCase("tempban") || cmd.getName().equalsIgnoreCase("tb")){
            if(!(sender instanceof Player)){
                sender.sendMessage("Du ma vere ingame for a bruke denne kommandoen!");
            }else{
                Player player = (Player) sender;
                if(args.length < 3){
                    player.sendMessage(ChatColor.RED + "Feil argumenter! Bruk /tempban <brukernavn> <tid> <grunn>");
                }else{
                    Player targetPlayer = Bukkit.getPlayer(args[0]);
                    String time = args[1];
                    if(time.endsWith("m") || time.endsWith("t") || time.endsWith("d")){

                    }else{

                    }
                }
            }
        }

        return true;
    }

}
