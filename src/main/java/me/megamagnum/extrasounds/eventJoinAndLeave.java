package me.megamagnum.extrasounds;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class eventJoinAndLeave implements Listener {
    Plugin plugin = ExtraSounds.getPlugin(ExtraSounds.class);
    Configuration config = this.plugin.getConfig();
    @EventHandler
    public void onJoin(PlayerJoinEvent event){

        config.set("Users." + event.getPlayer().getUniqueId() + ".sounds", true);
        plugin.saveConfig();

        if(config.getBoolean("JoinSound")) {
        Player p = event.getPlayer();
            for(Player player : Bukkit.getOnlinePlayers()) {
                if (config.getBoolean("Users." + p.getUniqueId() + "sounds")) {
                    player.playSound(player.getLocation(), Sound.BLOCK_PISTON_EXTEND, config.getInt("JoinVolume"), 3);
                }
            }
        }

    }
    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
      if(config.getBoolean("LeaveSound")) {

        Player p = event.getPlayer();
            for(Player player : Bukkit.getOnlinePlayers()) {
                if (config.getBoolean("Users." + p.getUniqueId() + "sounds")) {
                    player.playSound(player.getLocation(), Sound.BLOCK_PISTON_CONTRACT, config.getInt("LeaveVolume"), 3F);
                }
            }

        }
    }
}
