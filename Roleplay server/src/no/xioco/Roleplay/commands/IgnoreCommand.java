package no.xioco.Roleplay.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Thomas on 07.11.2014.
 */
public class IgnoreCommand implements CommandExecutor {

    public static HashMap<UUID, UUID> ignores = new HashMap<UUID, UUID>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String cmdl, String[] args){

        if(cmd.getName().equalsIgnoreCase("ignorer")){
            if(!(sender instanceof Player)){
                sender.sendMessage("Du ma vere ingame for a bruke denne kommandoen!");
            }else{
                Player player = (Player) sender;
                if(args.length != 2){
                    player.sendMessage(ChatColor.RED + "Feil argumenter! Bruk /ignorer <brukernavn> <av/på/sjekk>");
                }else{
                    Player targetPlayer = Bukkit.getPlayer(args[0]);
                    if(targetPlayer == null){
                        player.sendMessage(ChatColor.RED + "Brukeren " + ChatColor.RESET + targetPlayer.getName() + ChatColor.RED + " er ikke pålogget!");
                    }else{
                        switch(args[1].toLowerCase()){
                            case "på":
                                        ignores.put(player.getUniqueId(), targetPlayer.getUniqueId());
                                        player.sendMessage(ChatColor.GREEN + "Du har nå ignorert " + args[0]);

                                break;
                            case "av":
                                if(ignores.get(player.getUniqueId()).equals(targetPlayer.getUniqueId())) {
                                    for (Map.Entry<UUID, UUID> entry : ignores.entrySet()) {
                                        UUID key = entry.getKey();
                                        UUID value = entry.getValue();
                                        if(key.equals(player.getUniqueId())){
                                            if(value.equals(targetPlayer.getUniqueId())){
                                                ignores.remove(key);
                                            }
                                        }
                                    }
                                    player.sendMessage(ChatColor.GREEN + "Du har nå ignorert " + args[0]);
                                }else{
                                    player.sendMessage(ChatColor.RED + "Du ignorerer allerede " + args[0] + ". For å fjerne ignorering, skriv /ignorer <brukernavn> av.");
                                }                                break;
                            case "sjekk":
                                StringBuilder sb = new StringBuilder();
                                for (Map.Entry<UUID, UUID> entry : ignores.entrySet()) {
                                    if(entry.getKey().equals(player.getUniqueId())) {
                                        if (sb.length() > 0) {
                                            sb.append(ChatColor.GREEN + ", ");
                                        }
                                        sb.append(entry.getValue());
                                    }
                                }
                                player.sendMessage(ChatColor.GREEN + "Du har ignorert følgende spillere: " + ChatColor.RESET + sb.toString());
                                break;
                            default:
                                player.sendMessage(ChatColor.RED + "Feil subkommando! Bruk /ignorer <brukernavn> <av/på/sjekk>");
                                break;
                        }
                    }
                }
            }
        }

        return true;
    }

}
