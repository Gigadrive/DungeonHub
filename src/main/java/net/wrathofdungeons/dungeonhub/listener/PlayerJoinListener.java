package net.wrathofdungeons.dungeonhub.listener;

import net.wrathofdungeons.dungeonapi.event.PlayerCoreDataLoadedEvent;
import net.wrathofdungeons.dungeonapi.user.User;
import net.wrathofdungeons.dungeonapi.util.ItemUtil;
import net.wrathofdungeons.dungeonhub.DungeonHub;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        p.teleport(DungeonHub.spawnLocation);
        p.setGameMode(GameMode.ADVENTURE);
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);

        p.getInventory().addItem(ItemUtil.namedItem(Material.COMPASS, ChatColor.GREEN + "Server Selector",null));
    }

    @EventHandler
    public void onLoaded(PlayerCoreDataLoadedEvent e){
        Player p = e.getPlayer();
        User u = User.getUser(p);

        u.updateName();
    }
}
