package net.wrathofdungeons.dungeonhub;

import net.wrathofdungeons.dungeonapi.DungeonAPI;
import net.wrathofdungeons.dungeonhub.listener.PlayerChatListener;
import net.wrathofdungeons.dungeonhub.listener.PlayerJoinListener;
import net.wrathofdungeons.dungeonhub.listener.WeatherChangeListener;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class DungeonHub extends JavaPlugin {
    public static World mainWorld = null;

    public void onEnable(){
        mainWorld = Bukkit.getWorlds().get(0);
        mainWorld.setGameRuleValue("doMobSpawning","false");
        mainWorld.setGameRuleValue("doFireTick","false");
        mainWorld.setGameRuleValue("doDaylightCycle","false");
        mainWorld.setTime(6000);
        mainWorld.setStorm(false);
        mainWorld.setThundering(false);
        mainWorld.setDifficulty(Difficulty.PEACEFUL);

        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerChatListener(),this);
        Bukkit.getPluginManager().registerEvents(new WeatherChangeListener(),this);
    }
}