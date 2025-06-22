package azerot.azerot.command;

import azerot.azerot.*;
import azerot.azerot.npcSpawnForRegion.spawnWolf;
import de.slikey.effectlib.effect.StarEffect;
import io.netty.util.internal.ThreadLocalRandom;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Lags extends AbstractCommand {
    public Lags() {
        super("lags");
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) throws SQLException {
        Player p = (Player) sender;
        Location loc = p.getLocation();
        HashMap<Integer, Location> locationBlocks = new HashMap<>();
        Location location = new Location(Bukkit.getWorld("realm2"), -1498,256,1808);

        int i = 0;
        for(int ip = -20; ip <= 20 ; ip++){
            for(int j = -24; j <= 24; j++){
                locationBlocks.put(i, new Location(Bukkit.getWorld("realm2"), location.getBlockX() + ip,256, location.getBlockZ() + j));
                i++;
            }
        }

        Vector launchVector = new Vector(0, 5, 0).normalize();
        p.setVelocity(launchVector);

       /* (new BukkitRunnable() {
            public void run() {

               /* for(int i1 = 0; i1 <= 7; i1++){
                    Location loc1 = locationBlocks.get(ThreadLocalRandom.current().nextInt(1, locationBlocks.size()));
                    loc1.add(0,30,0);
                    Fireball fireball = p.getWorld().spawn(loc1, Fireball.class);
                    fireball.setShooter(null);
                    fireball.setDirection(new org.bukkit.util.Vector(0, -1, 0));
                }


                EntityTippedArrow entitytippedarrow = new EntityTippedArrow((ent.getWorld()), (EntityLiving)ent);
                Projectile projectile = (Projectile) RPGPlayerShoot.spawnProjectileWithShooter(Arrow.class, ent.getBukkitEntity());
                double d0 = p.getLocation().getX() - ent.locX;
                double d1 = p.getLocation().getY() + (1.8F / 3.0F) - entitytippedarrow.getBukkitEntity().getLocation().getY();
                double d2 = p.getLocation().getZ() - ent.locZ;
                double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
                entitytippedarrow.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (14 - (ent).world.getDifficulty().a() * 4));
                projectile.setVelocity(entitytippedarrow.getBukkitEntity().getVelocity());


            }
        }).runTaskTimer((Plugin) azerot.getInstance(), 50L, 50L);
        */
    }



}
