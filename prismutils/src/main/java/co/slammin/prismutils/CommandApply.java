package co.slammin.prismutils;

import java.util.List;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class CommandApply implements CommandExecutor {
    App plugin;

    public CommandApply(App app) {
        plugin = app;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        String uuid;
        uuid = plugin.getPlayerData().getString(player.getName() + ".applicationID");

        if (uuid == null) {
            uuid = UUID.randomUUID().toString().substring(0, 8);
            plugin.getPlayerData().set(player.getName() + ".applicationID", uuid);
            plugin.savePlayerData();
        }

        List<String> strings = plugin.getConfig().getStringList("applyMessages");

        for (String string : strings) {
            string = string.replaceAll("%APPLICATION-ID%", uuid);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', string));
        }

        
        return true;
    }
    
}
