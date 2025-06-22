package azerot.azerot.command;

import azerot.azerot.WorldUtils;
import azerot.azerot.azerot;
import net.minecraft.server.v1_12_R1.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_12_R1.PacketPlayOutEntityTeleport;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import azerot.azerot.rpgplayer;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class Xp extends AbstractCommand {
    public Xp() {
        super("xp");
    }

    public void execute(CommandSender sender, String label, String[] args) {
        try {
            if (!sender.hasPermission("op")) {
                sender.sendMessage(WorldUtils.worldName() + "проблема ");
                return;
            }
            if (args.length == 0) {
                sender.sendMessage(WorldUtils.worldName() + " проблема 2" );
                return;
            }
            if (args.length > 0) {
                rpgplayer p = rpgplayer.getRPGPlayer(Bukkit.getPlayer(args[0]));
                int xp = (args.length > 1) ? Integer.parseInt(args[1]) : 1;
                p.addXP(xp);
                return;
            }
        } catch (Exception exception) {
            sender.sendMessage(WorldUtils.worldName() + " проблема 3" );
            exception.printStackTrace();
        }
    }
}
