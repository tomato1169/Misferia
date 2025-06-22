package azerot.azerot.command;

import azerot.azerot.ItemStat;
import azerot.azerot.RPGItem;
import azerot.azerot.WorldUtils;
import azerot.azerot.rpgplayer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Grant extends AbstractCommand {
    public Grant() {
        super("grant");
    }

    public void execute(CommandSender sender, String label, String[] args) {
        try {
            if (!sender.hasPermission("")) {
                sender.sendMessage(WorldUtils.worldName() + "1" );
                return;
            }
            if (args.length == 0) {
                sender.sendMessage(WorldUtils.worldName() + "2" );
                return;
            }

            if (args.length > 0) {
                Player p = Bukkit.getPlayer(args[0]);
                int id = (args.length > 1) ? Integer.parseInt(args[1]) : 1;
                int count = (args.length > 2) ? Integer.parseInt(args[2]) : 1;
                int sharp = (args.length > 3) ? Integer.parseInt(args[3]) : 0;
                int mark = (args.length > 4) ? Integer.parseInt(args[4]) : 0;
                int markpower = (int)(Math.random() * 999.0D + 1.0D);
                int scaleS = (args.length > 5) ? Integer.parseInt(args[5]) : 0;
                int scaleA = (args.length > 6) ? Integer.parseInt(args[6]) : 0;
                int scaleI = (args.length > 7) ? Integer.parseInt(args[7]) : 0;
                ItemStack item = (new RPGItem()).getRPGItem(id, sharp, count,  mark,markpower, scaleS,scaleA, scaleI, rpgplayer.getRPGPlayer(p));
                p.getInventory().addItem(item);
                sender.sendMessage(WorldUtils.worldName() + " Предмет: " + item.getItemMeta().getDisplayName() + ", количество - " + item.getAmount() + ", игроку - " + p.getName() + " ");
                return;
            }
        } catch (Exception exception) {
            sender.sendMessage(WorldUtils.worldName() + "3" );
            exception.printStackTrace();
        }
    }
}
