package net.wrathofdungeons.dungeonhub.listener;

import net.wrathofdungeons.dungeonhub.DungeonHub;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player p = e.getPlayer();

        if(p.getLocation().getY() <= 0) p.teleport(DungeonHub.spawnLocation);
    }
}
