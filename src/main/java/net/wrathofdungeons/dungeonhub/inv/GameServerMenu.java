package net.wrathofdungeons.dungeonhub.inv;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.server.info.ServerInfo;
import net.wrathofdungeons.dungeonapi.user.User;
import net.wrathofdungeons.dungeonapi.util.ItemUtil;
import net.wrathofdungeons.dungeonapi.util.Util;
import net.wrathofdungeons.dungeonhub.DungeonHub;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.inventivetalent.menubuilder.inventory.InventoryMenuBuilder;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class GameServerMenu {
    private static ArrayList<Server> SERVER;

    public static ArrayList<Server> getServers(){
        if(SERVER == null){
            SERVER = new ArrayList<Server>();

            for(ServerInfo info : CloudAPI.getInstance().getServers("Game")){
                SERVER.add(new Server(info.getServiceId().getServerId(), info.getOnlineCount(), info.getMaxPlayers()));
            }

            new BukkitRunnable(){
                @Override
                public void run() {
                    SERVER = null;
                }
            }.runTaskLater(DungeonHub.getInstance(),10*20);
        }

        return SERVER;
    }

    public static void openFor(Player p){
        openFor(p,1);
    }

    public static void openFor(Player p, int page){
        User u = User.getUser(p);
        InventoryMenuBuilder inv = new InventoryMenuBuilder(Util.MAX_INVENTORY_SIZE);

        ArrayList<Server> servers = getServers();
        int sizePerPage = 36;
        int total = servers.size();

        ItemStack pl = ItemUtil.namedItem(Material.STAINED_GLASS_PANE, " ", null, 15);

        double d = (((double)total)/((double)sizePerPage));
        int maxPages = ((Double)d).intValue();
        if(maxPages < d) maxPages++;

        inv.withTitle("Select a server (" + page + "/" + maxPages + ")");

        int slot = 0;

        for(Server a : servers.stream().skip((page-1) * sizePerPage).limit(sizePerPage).collect(Collectors.toCollection(ArrayList::new))){
            ItemStack item = new ItemStack(Material.EMERALD_BLOCK);
            ChatColor color = ChatColor.GREEN;

            if(a.onlinePercentage() >= 50){
                item.setType(Material.GOLD_BLOCK);
                color = ChatColor.YELLOW;
            }

            if(a.onlinePercentage() >= 80){
                item.setType(Material.REDSTONE_BLOCK);
                color = ChatColor.RED;
            }

            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setDisplayName(color.toString() + ChatColor.BOLD + a.name);
            ArrayList<String> itemLore = new ArrayList<String>();
            itemLore.add(ChatColor.WHITE + "Players: " + color + String.valueOf(a.online) + ChatColor.WHITE + "/" + color + String.valueOf(a.max));
            itemLore.add(" ");
            if(a.online < a.max){
                itemLore.add(ChatColor.LIGHT_PURPLE + "Click to connect!");
            } else {
                itemLore.add(ChatColor.DARK_RED + "This server is full!");
            }
            itemMeta.setLore(itemLore);

            item.setItemMeta(itemMeta);

            inv.withItem(slot,item,((player, action, item1) -> {
                p.closeInventory();

                if(a.online < a.max){
                    u.connect(a.name);
                } else {
                    p.sendMessage(ChatColor.DARK_RED + "This server is full!");
                }
            }),ClickType.LEFT);

            slot++;
        }

        inv.withItem(36, pl);
        inv.withItem(37, pl);
        inv.withItem(38, pl);
        inv.withItem(39, pl);
        inv.withItem(40, pl);
        inv.withItem(41, pl);
        inv.withItem(42, pl);
        inv.withItem(43, pl);
        inv.withItem(44, pl);

        if(page != 1) inv.withItem(47,ItemUtil.namedItem(Material.ARROW, ChatColor.GOLD + "<< " + ChatColor.AQUA + "Previous page", null), ((player, clickType, itemStack) -> openFor(player, page-1)), ClickType.LEFT);
        inv.withItem(49, ItemUtil.namedItem(Material.BARRIER, ChatColor.DARK_RED + "Close", null), (player, clickType, itemStack) -> p.closeInventory(), ClickType.LEFT);
        if(maxPages > page) inv.withItem(51,ItemUtil.namedItem(Material.ARROW, ChatColor.AQUA + "Next page" + ChatColor.GOLD + " >>", null), (player, clickType, itemStack) -> openFor(player, page+1), ClickType.LEFT);

        inv.show(p);
    }

    public static class Server {
        public String name;
        public int online;
        public int max;

        public Server(String name, int online, int max){
            this.name = name;
            this.online = online;
            this.max = max;
        }

        public double onlinePercentage(){
            return ((online/max)*100);
        }
    }
}
