package azerot.azerot.command;

import azerot.azerot.GenerateInventory;
import azerot.azerot.RPGDamageble;
import azerot.azerot.WorldUtils;
import azerot.azerot.rpgplayer;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class skipCutscene extends AbstractCommand {
    public skipCutscene() {
        super("skip");
    }

    public void execute(CommandSender sender, String label, String[] args) {
        Player p = Bukkit.getPlayer(sender.getName());
        rpgplayer pl = rpgplayer.getRPGPlayer(p);
        if(pl.isWatchingCutscene()){
            pl.setSkipCutscene(true);
            p.sendMessage(WorldUtils.worldName() + " §2Вы пропустили катсцену!");
        }
    }
}
