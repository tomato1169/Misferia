package azerot.azerot;

import azerot.azerot.mobs.MobInfoManager;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import net.minecraft.server.v1_12_R1.Packet;
import net.minecraft.server.v1_12_R1.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_12_R1.SoundEffects;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.UUID;

public class RPGPlayerShoot implements Listener {

    private static final Cache<UUID, Byte> shoot = CacheBuilder.newBuilder().expireAfterWrite(250L, TimeUnit.MILLISECONDS).build();
    @EventHandler
    public void onEntityShootBow(EntityShootBowEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player)e.getEntity();
            if (shoot.asMap().containsKey(p.getUniqueId())) {
                e.setCancelled(true);
                return;
            }

            shoot.put(p.getUniqueId(), Byte.valueOf((byte)1));
            ItemStack hand = e.getBow();
            e.setCancelled(true);
            int id = ItemStat.getID(hand);
            RPGItemContainer RPGI = RPGItemContainer.getRPGItemContainer(id);
            int level = RPGI.getLevel();
            int canShoot = getArrowId(p, level);
            if (canShoot > 0) {
                e.setCancelled(true);
                WorldUtils.removeItems(rpgplayer.getRPGPlayer(p), canShoot, 1);
                if (getArrowId(p, level) == 0 && getQuiverId(p, level) != 0) {
                    p.getInventory().addItem(new RPGItem().getRPGItem(canShoot,0,64,0,0,0,0,0,rpgplayer.getRPGPlayer(p)));
                    WorldUtils.removeItems(rpgplayer.getRPGPlayer(p), getQuiverId(p, level), 1);
                }
                spawnProjectile(Arrow.class, p, hand, e.getProjectile().getVelocity());
                return;
            }
            p.sendMessage(WorldUtils.worldName() + " §4У вас нет стрел для этого лука" );
        }
    }


    @EventHandler
    public void onProjectileHit(ProjectileHitEvent e) {
        e.getEntity().remove();
    }

    public static <T extends Projectile> T spawnProjectile(Class<T> clazz, Player player, ItemStack bow) {
        return spawnProjectile(clazz, player, bow, null);
    }

    public int getArrowId(Player p, int level) {
        if (level >= 0 && level < 20 &&
                WorldUtils.checkItems(rpgplayer.getRPGPlayer(p), 21, 0) > 0)
            return 21;
        if (level >= 20 && level < 30 &&
                WorldUtils.checkItems(rpgplayer.getRPGPlayer(p), 22, 0) > 0)
            return 22;
        if (level >= 30 && level < 40 &&
                WorldUtils.checkItems(rpgplayer.getRPGPlayer(p), 23, 0) > 0)
            return 23;
        if (level >= 40 && level < 50 &&
                WorldUtils.checkItems(rpgplayer.getRPGPlayer(p), 24, 0) > 0)
            return 24;
        if (level >= 50 && level < 60 &&
                WorldUtils.checkItems(rpgplayer.getRPGPlayer(p), 25, 0) > 0)
            return 25;
        return 0;
    }

    public int getQuiverId(Player p, int level) {
        if (level >= 0 && level < 20 &&
                WorldUtils.checkItems(rpgplayer.getRPGPlayer(p), 25, 0) > 0)
            return 25;
        if (level >= 20 && level < 30 &&
                WorldUtils.checkItems(rpgplayer.getRPGPlayer(p), 26, 0) > 0)
            return 26;
        if (level >= 30 && level < 40 &&
                WorldUtils.checkItems(rpgplayer.getRPGPlayer(p), 27, 0) > 0)
            return 27;
        if (level >= 40 && level < 50 &&
                WorldUtils.checkItems(rpgplayer.getRPGPlayer(p), 28, 0) > 0)
            return 28;
        if (level >= 50 && level < 60 &&
                WorldUtils.checkItems(rpgplayer.getRPGPlayer(p), 273, 0) > 0)
            return 273;
        return 0;
    }

    public static <T extends Projectile> T spawnProjectile(Class<T> clazz, Player player, ItemStack bow, Vector velocity) {
        double length;
        Projectile projectile = player.launchProjectile(clazz);
        if (velocity != null) {
            length = velocity.length();
            projectile.setVelocity(velocity);
        } else {
            length = 3.0D;
            VelocityHelper.setVelocity((Entity)projectile, projectile.getVelocity().multiply(1.5F));
        }
        projectile.setMetadata("caster", (MetadataValue)new FixedMetadataValue((Plugin)azerot.getInstance(), player));
        projectile.setMetadata("item", (MetadataValue)new FixedMetadataValue((Plugin)azerot.getInstance(), bow));
        projectile.setMetadata("length", (MetadataValue)new FixedMetadataValue((Plugin)azerot.getInstance(), Double.valueOf(length)));
        return (T)projectile;
    }
    public static <T extends Projectile> T spawnProjectileWithShooter(Class<T> clazz, Entity shooter) {
        Projectile projectile = ((LivingEntity)shooter).launchProjectile(clazz);
        VelocityHelper.setVelocity((Entity)projectile, projectile.getVelocity().multiply(1.5F));
        if (MobInfoManager.getMobInfo(shooter).getVisibleTo() != null)
            hideProjectile(MobInfoManager.getMobInfo(shooter).getVisibleTo(), projectile);
        Location loc = shooter.getLocation();
        ((CraftEntity)shooter).getHandle().a(SoundEffects.gW, 1.0F, 1.0F / ((new Random()).nextFloat() * 0.4F + 0.8F));
        loc.getWorld().playSound(loc, Sound.ENTITY_ARROW_SHOOT, 1.0F, 1.0F);
        return (T)projectile;
    }
    public static void hideProjectile(Player visibleTo, Projectile projectile) {
        for (Player on : Bukkit.getServer().getOnlinePlayers()) {
            PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(new int[]{projectile.getEntityId()});
            if (!on.equals(visibleTo))
                (((CraftPlayer) on).getHandle()).playerConnection.sendPacket((Packet) packet);
        }
    }
}
