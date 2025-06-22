package azerot.azerot.Quest;

import azerot.azerot.azerot;
import de.slikey.effectlib.effect.StarEffect;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Objects;

public class QuestsPath
{
    public static void pathOn(Player p, String idQuests){
        Location finishLoc = null;
        switch (idQuests){
            case "3":
            finishLoc = new Location(Bukkit.getWorld("world"),-3227,70,1867,2.2F,3.0F);
            break;
            case "4":
                finishLoc = new Location(Bukkit.getWorld("world"),-4033,68,1976,2.2F,3.0F);
                break;
            case "5":
                finishLoc = new Location(Bukkit.getWorld("world"),-3998,64,1912,2.2F,3.0F);
                break;
            case "10":
                finishLoc = new Location(Bukkit.getWorld("world"),-4157,161,2892,2.2F,3.0F);
                break;
            case "11":
                finishLoc = new Location(Bukkit.getWorld("world"),-4235,130,3251,2.2F,3.0F);
                break;
        }


        assert finishLoc != null;
        Location finalFinishLoc = finishLoc.clone();
        (new BukkitRunnable() {
            public void run() {
                Location loc = p.getLocation();
                Vector direction = finalFinishLoc.toVector().subtract(loc.toVector()).normalize();
                double distance = 5;
                loc.add(direction.multiply(distance));
                if(loc.add(0,-1,0).getBlock().getType().equals(Material.AIR)){
                    loc.add(0,-1,0);
                }
                if(!loc.add(0,-1,0).getBlock().getType().equals(Material.AIR)){
                    loc.add(0,1,0);
                }

                StarEffect starEffect = new StarEffect(azerot.getEffectManager());
                starEffect.setLocation(loc);
                starEffect.particle = Particle.FLAME;
                starEffect.iterations = 1;
                starEffect.duration = 15;
                starEffect.spikeHeight = 0.5F;
                starEffect.spikesHalf = 2;
                starEffect.start();
            }
        }).runTaskTimer((Plugin)azerot.getInstance(), 0L, 20L);
    }
}
