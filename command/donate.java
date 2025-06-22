package azerot.azerot.command;

import azerot.azerot.GenerateInventory;
import azerot.azerot.WorldUtils;
import azerot.azerot.rpgplayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class donate extends AbstractCommand{
    public donate() {
        super("donate");
    }

    public void execute(CommandSender sender, String label, String[] args) {
        Player p = (Player) sender;
        p.openInventory(GenerateInventory.DonateShop(rpgplayer.getRPGPlayer(p)));
    }
}
