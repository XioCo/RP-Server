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
public class MountCommand implements CommandExecutor{

    public boolean onCommand(CommandSender sender, Command cmd, String cmdl, String[] args){
        if(cmd.getName().equalsIgnoreCase("ri")){
            if(!(sender instanceof Player)){
                sender.sendMessage("Du ma vere ingame for a bruke denne kommandoen!");
            }else {
                Player player = (Player) sender;
                if (player.hasPermission("xioco.skuldre")) {
                    if (args.length != 1) {
                        player.sendMessage(ChatColor.RED + "Feil argumenter! Bruk /ri <brukernavn>");
                    } else {
                        Player targetPlayer = Bukkit.getPlayer(args[0]);
                        if (targetPlayer == null) {
                            player.sendMessage(ChatColor.RED + "Brukeren " + ChatColor.RESET + args[0] + ChatColor.RED + " er offline!");
                            return true;
                        }

                        for (Player oP : Bukkit.getOnlinePlayers()) {
                            if (!oP.hasPermission("xioco.skuldre")) {
                                oP.hidePlayer(player);
                            }
                        }
                        targetPlayer.setPassenger(player);
                        HideCommand.hiddenPlayers.add(player.getUniqueId());
                        player.sendMessage(ChatColor.GREEN + "Du rir nå på " + ChatColor.RESET + targetPlayer.getName() + ChatColor.GREEN + " og er usynlig!");
                        player.sendMessage(ChatColor.GREEN + "For å bli synlig igjen, skriv /hide.");
                    }
                }else{
                    player.sendMessage(ChatColor.RED + "Du har ikke tilgang til denne kommandoen.");
                }
            }
        }
        return true;
    }

}
