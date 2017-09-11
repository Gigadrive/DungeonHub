package net.wrathofdungeons.dungeonhub.listener;

import net.wrathofdungeons.dungeonapi.user.User;
import net.wrathofdungeons.dungeonhub.inv.GameServerMenu;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(User.isLoaded(p)){
            User u = User.getUser(p);

            if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR){
                if(p.getItemInHand() != null && p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().hasDisplayName()){
                    String displayName = p.getItemInHand().getItemMeta().getDisplayName();

                    if(displayName.equals(ChatColor.GREEN + "Server Selector")){
                        e.setCancelled(true);
                        e.setUseItemInHand(Event.Result.DENY);
                        e.setUseInteractedBlock(Event.Result.DENY);

                        GameServerMenu.openFor(p);
                    }
                }
            }
        }
    }
}
