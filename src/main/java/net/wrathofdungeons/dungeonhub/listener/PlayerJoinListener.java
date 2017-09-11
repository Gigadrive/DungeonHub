package net.wrathofdungeons.dungeonhub.listener;

import net.wrathofdungeons.dungeonapi.event.PlayerCoreDataLoadedEvent;
import net.wrathofdungeons.dungeonapi.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onLoaded(PlayerCoreDataLoadedEvent e){
        Player p = e.getPlayer();
        User u = User.getUser(p);

        u.updateName();
    }
}
