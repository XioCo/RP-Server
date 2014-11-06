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
public class AdminChatCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String cmdl, String[] args) {
        if (cmd.getName().equalsIgnoreCase("c")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Du ma vere ingame for a bruke denne kommandoen!");
            } else {
                Player player = (Player) sender;
                if (player.hasPermission("xioco.adminchat")) {
                    if (args.length == 0) {
                        player.sendMessage(ChatColor.RED + "For få argumenter! Bruk /c <melding>");
                    } else {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < args.length; i++) {
                            if (sb.length() > 0) {
                                sb.append(" ");
                            }
                            sb.append(args[i]);
                        }
                        Bukkit.broadcast("(" + ChatColor.BLUE + "Vakt" + ChatColor.GRAY + "/" + ChatColor.GOLD + "Stab" + ChatColor.RESET + ") " + player.getName() + ": " + sb.toString(), "xioco.adminchat");

                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Du har ikke tilgang til denne kommandoen!");
                }
            }
        }
    return true;
    }

}
