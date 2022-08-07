package me.megamagnum.extrasounds;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class commandToggleSounds implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Plugin plugin = ExtraSounds.getPlugin(ExtraSounds.class);
        Configuration config = plugin.getConfig();
        Player p = (Player) sender;

        if (config.getBoolean("Users." + p.getUniqueId() + "sounds")) {
            config.set("Users." + p.getUniqueId() + "sounds", false);
            p.sendMessage(ChatColor.RED + "Sounds are now turned off!");
            p.playSound(p.getLocation(), Sound.BLOCK_PISTON_CONTRACT, 4, 8F);
            plugin.saveConfig();
        }
        else{
            config.set("Users." + p.getUniqueId() + "sounds", true);
            p.sendMessage(ChatColor.RED + "Sounds are now turned on!");
            p.playSound(p.getLocation(), Sound.BLOCK_PISTON_EXTEND, 4, 8F);
            plugin.saveConfig();
        }

        return true;

    }
}
