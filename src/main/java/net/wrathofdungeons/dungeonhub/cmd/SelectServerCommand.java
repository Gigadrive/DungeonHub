package net.wrathofdungeons.dungeonhub.cmd;

import net.wrathofdungeons.dungeonapi.cmd.manager.Command;
import net.wrathofdungeons.dungeonhub.inv.GameServerMenu;
import org.bukkit.entity.Player;

public class SelectServerCommand extends Command {
    public SelectServerCommand(){
        super(new String[]{"selectserver","serverlist"});
    }

    @Override
    public void execute(Player p, String label, String[] args) {
        GameServerMenu.openFor(p);
    }
}
