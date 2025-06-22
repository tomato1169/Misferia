package azerot.azerot.command;

import azerot.azerot.RpgTrade;
import azerot.azerot.WorldUtils;
import azerot.azerot.rpgplayer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class trade extends AbstractCommand{
    public trade() {
        super("trade");
    }

    public void execute(CommandSender sender, String label, String[] args) {
        if(sender.hasPermission("")) {
            rpgplayer rp = rpgplayer.getRPGPlayer((Player) sender);
            if (args.length == 0) {
                rp.toPlayer().sendMessage(WorldUtils.worldName() + " §c§b/trade [Ник игрока]");
            } else {
                Player op = Bukkit.getPlayer(args[0]);
                if (op.isOnline()) {
                    rpgplayer rp2 = rpgplayer.getRPGPlayer(Bukkit.getPlayer(args[0]));
                    if (rp2.isTrading()) {
                        rp.toPlayer().sendMessage(WorldUtils.worldName() + " §сЭтот игрок уже с кем-то торгует!");
                        return;
                    }
                    if (rp.toPlayer().getLocation().distance(rp2.toPlayer().getLocation()) > 5.0D) {
                        rp.toPlayer().sendMessage(WorldUtils.worldName() + " §cУказанный игрок слишком далеко");
                        return;
                    }
                    if (rp2.getName().equals(rp.getName())) {
                        rp.toPlayer().sendMessage(WorldUtils.worldName() + " §cВы не можете торговать с самим собой!");
                        return;
                    }
                    if (!rp2.getSettings().get("trade")) {
                        rp.toPlayer().sendMessage(WorldUtils.worldName() + " §cУказанный игрок выключил торговлю. Чтобы это стало возможным, он должен изменить это в настройках!");
                        return;
                    }
                    new RpgTrade(rp, rp2);

                } else {
                    rp.toPlayer().sendMessage(WorldUtils.worldName() + " §cИгрок не в сети!");
                    return;
                }


            }
        }
    }
}
