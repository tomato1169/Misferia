package azerot.azerot;

import java.io.File;
import java.util.Iterator;

import azerot.azerot.RPG.RPGGrave;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class RPGLocations {

    public static Location nearestLoc(Location location, rpgplayer p) {
        if (location == null)
            return new Location(Bukkit.getWorld("world"), 0.0D, 0.0D, 0.0D);
        double nearestDistance = 9.99999999E8D;
        int m = 0;
        for (Integer integer : RPGGrave.getRPGGrave().keySet()) {
            int key = integer;
            RPGGrave rPGGrave = RPGGrave.getRPGGrave(key);

            if (rPGGrave.getRequiredQuest() != 0 && p.getQuestsStage().get(rPGGrave.getRequiredQuest() + "") != null && rPGGrave.getRequiredStage() != 0 && p.getQuestsStage().get(rPGGrave.getRequiredQuest() + "") < rPGGrave.getRequiredStage()){
                continue;
            }
            World world = Bukkit.getWorld(rPGGrave.getWorld());
            if (!location.getWorld().equals(world))
                continue;
            int d1 = rPGGrave.getX();
            int d2 = rPGGrave.getY();
            int d3 = rPGGrave.getZ();
            Location location1 = new Location(world, d1, d2, d3, 0.0F, 0.0F);
            if (checkDistance(location, location1) < nearestDistance) {
                m = key;
                nearestDistance = checkDistance(location, location1);
            }
        }
        RPGGrave grave = RPGGrave.getRPGGrave(m);
        if (grave.getTeleportTo() != 0)
            m = grave.getTeleportTo();
        RPGGrave finalGrave = RPGGrave.getRPGGrave(m);
        World w = Bukkit.getWorld(finalGrave.getWorld());
        double x = finalGrave.getX();
        double y = finalGrave.getY();
        double z = finalGrave.getZ();
        float yaw = finalGrave.getYaw();
        float pitch = finalGrave.getPitch();
        Location loc = new Location(w, x + 0.5D, y, z + 0.5D, yaw, pitch);
        Bukkit.broadcastMessage("тп было на " + m);
        return loc;
    }


    public static double checkDistance(Location a, Location b) {
        double ax = a.getX();
        double ay = a.getY();
        double az = a.getZ();
        double bx = b.getX();
        double by = b.getY();
        double bz = b.getZ();
        double x = 0.0D;
        double y = 0.0D;
        double z = 0.0D;
        if (ax > bx)
            x = ax - bx;
        if (bx > ax)
            x = bx - ax;
        if (ay > by)
            y = ay - by;
        if (by > ay)
            y = by - ay;
        if (az > bz)
            z = az - bz;
        if (bz > az)
            z = bz - az;
        return x + y + z;
    }
}
