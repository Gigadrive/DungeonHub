package net.wrathofdungeons.dungeonhub.listener;

import net.wrathofdungeons.dungeonapi.event.PlayerCoreDataLoadedEvent;
import net.wrathofdungeons.dungeonapi.user.User;
import net.wrathofdungeons.dungeonhub.DungeonHub;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        p.teleport(DungeonHub.spawnLocation);
    }

    @EventHandler
    public void onLoaded(PlayerCoreDataLoadedEvent e){
        Player p = e.getPlayer();
        User u = User.getUser(p);

        u.updateName();
    }
}
