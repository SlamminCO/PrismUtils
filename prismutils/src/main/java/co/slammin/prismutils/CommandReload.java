package co.slammin.prismutils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class CommandReload implements CommandExecutor {
    App plugin;

    public CommandReload(App app) {
        plugin = app;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        
        plugin.reloadConfig();
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aReloaded!"));
        return true;
    }
    
}
