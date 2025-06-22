package azerot.azerot.command;

import azerot.azerot.RPGDamageble;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Kill extends AbstractCommand {
    public Kill() {
        super("kill");
    }

    public void execute(CommandSender sender, String label, String[] args) {
        Player p = Bukkit.getPlayer(sender.getName());
        RPGDamageble.death((Entity)p, null, null);
    }
}
