package me.megamagnum.extrasounds;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

public class eventMessages implements Listener {
    Plugin plugin = ExtraSounds.getPlugin(ExtraSounds.class);
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player sender = e.getPlayer();
        Configuration config = this.plugin.getConfig();

        if(config.getBoolean("ChatPing")){
        for (Player pinged : Bukkit.getOnlinePlayers()) {
            e.setCancelled(true);
            if(e.getMessage().toLowerCase().contains(pinged.getName().toLowerCase())) {

                if(config.getBoolean("Users." + pinged.getUniqueId() + "sounds")) {
                    e.setCancelled(true);

                    sender.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED + "You have pinged " + pinged.getName() + "!"));
                    pinged.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED + "You have been pinged by " + sender.getName() + "!"));
                    pinged.playSound(pinged.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 3F, 0.8F);
                    sender.playSound(sender.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 3F, 0.7F);

                    for(Player everyone : Bukkit.getOnlinePlayers()){
                        if(everyone.getUniqueId().equals(pinged.getUniqueId())){
                            pinged.sendMessage("<"+ sender.getDisplayName()+"> " + ChatColor.RED+e.getMessage());

                        } else if (everyone.getUniqueId().equals(sender.getUniqueId())) {
                          sender.sendMessage( "<"+ sender.getDisplayName()+"> " + ChatColor.AQUA+e.getMessage());
                        }else
                            everyone.sendMessage("<"+ sender.getDisplayName()+"> " +e.getMessage());
                    }
                    }else{
                        sender.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED + "The player you are trying to ping has his pings toggled off"));
                }

                }else{
                e.setMessage(" "+ e.getMessage());
            }


            }
            }
        }
    }