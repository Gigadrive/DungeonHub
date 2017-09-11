package net.wrathofdungeons.dungeonhub.listener;

import net.wrathofdungeons.dungeonapi.user.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        if(!e.isCancelled()){
            Player p = e.getPlayer();
            String msg = e.getMessage();
            e.setCancelled(true);

            if(User.isLoaded(p)){
                User u = User.getUser(p);

                for(Player all : Bukkit.getOnlinePlayers()){
                    all.sendMessage(p.getDisplayName() + ChatColor.WHITE + ": " + msg);
                }
            }
        }
    }
}
