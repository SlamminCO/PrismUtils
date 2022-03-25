package co.slammin.prismutils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class CommandVerifyApplication implements CommandExecutor {
    App plugin;

    public CommandVerifyApplication(App app) {
        plugin = app;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        
        if (args.length < 2) {
            return false;
        }
        
        String uuid = plugin.getPlayerData().getString(args[0] + ".applicationID");

        if (uuid == null) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c" + args[0] + " has no application ID"));
        } else if (uuid.equals(args[1])) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aApplication ID matches"));
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cApplication ID does not match"));
        }

        return true;
    }
    
}
