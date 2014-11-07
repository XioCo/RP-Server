package no.xioco.Roleplay.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashSet;
import java.util.UUID;

/**
 * Created by Thomas on 07.11.2014.
 */
public class SocialSpyCommand implements CommandExecutor{

    public static HashSet<UUID> socialspy = new HashSet<UUID>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String cmdl, String[] args){
        if(cmd.getName().equalsIgnoreCase("spy")){
            if(!(sender instanceof Player)){
                sender.sendMessage("Du ma vere ingame for a bruke denne kommandoen!");
            } else {
                Player player = (Player) sender;
                if (player.hasPermission("xioco.socialspy")) {
                    UUID uuid = player.getUniqueId();
                    if (socialspy.contains(uuid)) {
                        socialspy.remove(uuid);
                        player.sendMessage(ChatColor.RED + "Du er ikke lenger i socialspy, og ser dermed ikke lenger andre sine privatmeldinger.");
                    } else {
                        socialspy.add(uuid);
                        player.sendMessage(ChatColor.GREEN + "Du er nå i socialspy, og ser alle sine privatmeldinger.");
                    }
                }
            }
        }
        return true;
    }

}
