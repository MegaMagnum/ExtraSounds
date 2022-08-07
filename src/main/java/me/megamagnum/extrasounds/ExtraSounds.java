package me.megamagnum.extrasounds;

import org.bukkit.plugin.java.JavaPlugin;

public final class ExtraSounds extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new eventMessages(), this);
        getServer().getPluginManager().registerEvents(new eventJoinAndLeave(), this);
        this.getCommand("togglesound").setExecutor(new commandToggleSounds());
        loadConfig();
        // Plugin startup logic

    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
