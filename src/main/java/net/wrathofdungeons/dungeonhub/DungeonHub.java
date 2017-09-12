package net.wrathofdungeons.dungeonhub;

import net.wrathofdungeons.dungeonapi.DungeonAPI;
import net.wrathofdungeons.dungeonhub.cmd.SelectServerCommand;
import net.wrathofdungeons.dungeonhub.cmd.SetSpawnCommand;
import net.wrathofdungeons.dungeonhub.listener.*;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class DungeonHub extends JavaPlugin {
    public static World mainWorld = null;
    public static Location spawnLocation = null;

    private static DungeonHub instance;

    public void onEnable(){
        instance = this;

        mainWorld = Bukkit.getWorlds().get(0);
        mainWorld.setGameRuleValue("doMobSpawning","false");
        mainWorld.setGameRuleValue("doFireTick","false");
        mainWorld.setGameRuleValue("doDaylightCycle","false");
        mainWorld.setTime(6000);
        mainWorld.setStorm(false);
        mainWorld.setThundering(false);
        mainWorld.setDifficulty(Difficulty.PEACEFUL);

        Bukkit.getPluginManager().registerEvents(new BlockListener(),this);
        Bukkit.getPluginManager().registerEvents(new DamageListener(),this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerChatListener(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerDropListener(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(),this);
        Bukkit.getPluginManager().registerEvents(new WeatherChangeListener(),this);

        new SetSpawnCommand();
        new SelectServerCommand();

        spawnLocation = new Location(mainWorld,getConfig().getDouble("locations.spawn.x"),getConfig().getDouble("locations.spawn.y"),getConfig().getDouble("locations.spawn.z"),getConfig().getInt("locations.spawn.yaw"),getConfig().getInt("locations.spawn.pitch"));
    }

    public static DungeonHub getInstance() {
        return instance;
    }
}