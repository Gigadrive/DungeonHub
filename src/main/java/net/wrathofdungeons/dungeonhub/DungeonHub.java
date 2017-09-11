package net.wrathofdungeons.dungeonhub;

import net.wrathofdungeons.dungeonapi.DungeonAPI;
import net.wrathofdungeons.dungeonhub.listener.PlayerChatListener;
import net.wrathofdungeons.dungeonhub.listener.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class DungeonHub extends JavaPlugin {
    public static World mainWorld = null;

    public void onEnable(){
        mainWorld = Bukkit.getWorlds().get(0);

        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerChatListener(),this);
    }
}