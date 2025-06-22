package azerot.azerot.script;

import azerot.azerot.MobSpells;
import azerot.azerot.azerot;
import azerot.azerot.mobs.MobSystem;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;

public class scriptArсherFor85Mob
{
    static HashMap<String, Integer> attack1 = new HashMap<>();

    public static void onAttack(Entity e) {
        Double speed = 0.2D;
        if(e.getLocation().getY() == 55){
            (new BukkitRunnable() {
                public void run() {

                    if(e.isDead()){
                        cancel();
                    }
                    if(MobSystem.getTarget(e) == null){
                        cancel();
                    }
                    double x = (Math.random() - 0.5) * speed;
                    double z = (Math.random() - 0.5) * speed;

                    Vector velocity = new Vector(x, 0, z);
                    e.setVelocity(e.getVelocity().add(velocity));
                }
            }).runTaskTimer((Plugin) azerot.getInstance(), 2L, 2L);
        }
    }
}
