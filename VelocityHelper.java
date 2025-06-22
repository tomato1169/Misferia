package azerot.azerot;

import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class VelocityHelper {
    public static void setVelocity(Entity e, Vector velocity) {
        double x = velocity.getX(), y = velocity.getY(), z = velocity.getZ();
        if (x != x || y != y || z != z)
            return;
        if (velocity.length() > 3.0D)
            velocity = velocity.normalize().multiply(3.0D);
        e.setVelocity(velocity);
    }
}
