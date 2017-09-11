package net.wrathofdungeons.dungeonhub.cmd;

import net.wrathofdungeons.dungeonapi.cmd.manager.Command;
import net.wrathofdungeons.dungeonapi.user.Rank;
import net.wrathofdungeons.dungeonhub.DungeonHub;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SetSpawnCommand extends Command {
    public SetSpawnCommand(){
        super("setspawn", Rank.ADMIN);
    }

    @Override
    public void execute(Player p, String label, String[] args) {
        p.sendMessage(ChatColor.GREEN + "Success!");

        DungeonHub.spawnLocation = p.getLocation();
        DungeonHub.getInstance().getConfig().set("locations.spawn.x",p.getLocation().getX());
        DungeonHub.getInstance().getConfig().set("locations.spawn.y",p.getLocation().getY());
        DungeonHub.getInstance().getConfig().set("locations.spawn.z",p.getLocation().getZ());
        DungeonHub.getInstance().getConfig().set("locations.spawn.yaw",p.getLocation().getYaw());
        DungeonHub.getInstance().getConfig().set("locations.spawn.pitch",p.getLocation().getPitch());
        DungeonHub.getInstance().saveConfig();
    }
}
