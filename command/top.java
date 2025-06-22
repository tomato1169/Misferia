package azerot.azerot.command;

import azerot.azerot.Cutscene.DeadIsland;
import azerot.azerot.Cutscene.Pleyada;
import azerot.azerot.Cutscene.initial;
import azerot.azerot.Cutscene.startSecondPatch;
import azerot.azerot.GenerateInventory;
import azerot.azerot.WorldUtils;
import azerot.azerot.azerot;
import de.slikey.effectlib.effect.StarEffect;
import io.netty.util.internal.ThreadLocalRandom;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.awt.geom.GeneralPath;
import java.sql.SQLException;

public class top extends AbstractCommand {
    public top() {
        super("top");
    }

    public void execute(CommandSender sender, String label, String[] args) throws SQLException {
        Player p = (Player) sender;
        World world = Bukkit.getWorld("realm2");  // Получаем мир один раз
        double[][] coordinates = {
                {-1244.5, 67, 1372.5}, {-1236.5, 67, 1365.5}, {-1235.5, 67, 1349.5}, {-1242.5, 67, 1341.5}, {-1258.5, 67, 1340.5},
                {-1266.5, 67, 1347.5}, {-1267.5, 67, 1363.5}, {-1260.5, 67, 1371.5},
                {-1251.5, 55, 1365.5}, {-1260.5, 55, 1356.5}, {-1251.5, 55, 1347.5}, {-1242.5, 55, 1356.5}, {-1233.5, 55, 1356.5},
                {-1240.5, 55, 1369.5}, {-1251.5, 55, 1374.5}, {-1264.5, 55, 1367.5}, {-1269.5, 55, 1356.5},
                {-1262.5, 55, 1343.5}, {-1251.5, 55, 1338.5}, {-1238.5, 55, 1345.5}
        };

        for (double[] coord : coordinates) {
            WorldUtils.spawnMob("100", new Location(world, coord[0], coord[1], coord[2]), "0");
        }

    }

}