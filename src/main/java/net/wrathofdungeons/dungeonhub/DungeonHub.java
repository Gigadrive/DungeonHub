package net.wrathofdungeons.dungeonhub;

import net.wrathofdungeons.dungeonapi.DungeonAPI;
import net.wrathofdungeons.dungeonhub.listener.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class DungeonHub extends JavaPlugin {
    public void onEnable(){
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(),this);
    }
}