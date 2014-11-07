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
 * Created by Thomas on 07.11.2014.
 */
public class PMCommands implements CommandExecutor{

    public static HashMap<UUID, UUID> playersenders = new HashMap<UUID, UUID>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String cmdl, String[] args){
        if(cmd.getName().equalsIgnoreCase("m")){
            if(!(sender instanceof Player)) {
                sender.sendMessage("Du ma vere ingame for a bruke denne kommandoen!");
            } else {
                Player player = (Player) sender;
                if(args.length >= 2) {
                    Player targetPlayer = Bukkit.getPlayer(args[0]);
                    if(targetPlayer == null){
                        player.sendMessage(ChatColor.RED + "Brukeren: " + ChatColor.RESET + args[0] + ChatColor.RED + " er ikke pålogget!");
                    }else {
                        StringBuilder sb = new StringBuilder();
                        for(int i = 1; i < args.length; i++){
                            if(sb.length() > 0) {
                                sb.append(" ");
                            }
                            sb.append(args[i]);
                        }

                        String msg = sb.toString();

                        player.sendMessage(player.getName() + ChatColor.AQUA + "" + ChatColor.BOLD + " > " + ChatColor.RESET + targetPlayer.getName() + ": " + msg);
                        targetPlayer.sendMessage(targetPlayer.getName() + ChatColor.AQUA + "" + ChatColor.BOLD + " > " + ChatColor.RESET + player.getName() + ": " + msg);
                        playersenders.put(player.getUniqueId(), targetPlayer.getUniqueId());
                        for(Player oP : Bukkit.getOnlinePlayers()){
                            if(SocialSpyCommand.socialspy.contains(oP.getUniqueId())){
                                oP.sendMessage(ChatColor.GRAY + player.getName() + ChatColor.BOLD + " > " + ChatColor.RESET + ChatColor.GRAY + targetPlayer.getName() + ": " + msg);
                            }
                        }
                    }
                }
            }
        }

        if(cmd.getName().equalsIgnoreCase("r")){
            if(!(sender instanceof Player)) {
                sender.sendMessage("Du ma vere ingame for a bruke denne kommandoen!");
            } else {
                Player player = (Player) sender;
                if(args.length >= 1) {
                    Player targetPlayer = Bukkit.getPlayer(playersenders.get(player.getUniqueId()));
                    if(targetPlayer == null){
                        player.sendMessage(ChatColor.RED + "Du har ingen å svare til.");
                    } else {
                        StringBuilder sb = new StringBuilder();
                        for(int i = 0; i < args.length; i++){
                            if(sb.length() > 0) {
                                sb.append(" ");
                            }
                            sb.append(args[i]);
                        }

                        String msg = sb.toString();

                        player.sendMessage(player.getName() + ChatColor.AQUA + "" + ChatColor.BOLD + " > " + ChatColor.RESET + targetPlayer.getName() + ": " + msg);
                        targetPlayer.sendMessage(targetPlayer.getName() + ChatColor.AQUA + "" + ChatColor.BOLD + " > " + ChatColor.RESET + player.getName() + ": " + msg);
                        for(Player oP : Bukkit.getOnlinePlayers()){
                            if(SocialSpyCommand.socialspy.contains(oP.getUniqueId())){
                                oP.sendMessage(ChatColor.GRAY + targetPlayer.getName() + ChatColor.BOLD + " > " + ChatColor.RESET + ChatColor.GRAY + player.getName() + ": " + msg);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

}
