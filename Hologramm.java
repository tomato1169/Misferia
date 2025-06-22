package azerot.azerot;


import azerot.azerot.RPGMob.RPGZombie;
import azerot.azerot.mobs.MobSystem;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Hologramm {
    public static void tempHolo(String holo, String subholo, double time, Location loc) {
        final ArmorStand armorStand = (ArmorStand)loc.getWorld().spawn(loc.add(0.0D, 0.5D, 0.0D), ArmorStand.class);
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setSmall(true);
        armorStand.setBasePlate(false);
        armorStand.setMarker(true);
        armorStand.setCanPickupItems(false);
        armorStand.setCustomNameVisible(true);
        armorStand.setCustomName(holo);

        final ArmorStand armorStand1 = (ArmorStand)loc.getWorld().spawn(loc.add(0.0D, -0.25D, 0.0D), ArmorStand.class);
        armorStand1.setVisible(false);
        armorStand1.setGravity(false);
        armorStand1.setSmall(true);
        armorStand1.setBasePlate(false);
        armorStand1.setMarker(true);
        armorStand1.setCanPickupItems(false);
        armorStand1.setCustomNameVisible(true);
        armorStand1.setCustomName(subholo);
        azerot.getInstance();
        (new BukkitRunnable() {
            public void run() {
                armorStand.remove();
                armorStand1.remove();
            }
        }).runTaskLater((Plugin)azerot.getPlugin(azerot.class), (long) (time * 20L));
    }

    public static void HoloHp(Entity e) {
        final ArmorStand armorStand = (ArmorStand)e.getLocation().getWorld().spawn(((LivingEntity)e).getEyeLocation().add(0,1,0), ArmorStand.class);
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setSmall(true);
        armorStand.setBasePlate(false);
        armorStand.setMarker(true);
        armorStand.setCanPickupItems(false);
        e.setPassenger(armorStand);
        (new BukkitRunnable() {
            public void run() {
                if(e.isDead()){
                    armorStand.remove();
                    cancel();
                }
                if(MobSystem.getPercentHealth(e).isEmpty()){
                    armorStand.setCustomNameVisible(false);
                    return;
                }else{
                    armorStand.setCustomNameVisible(true);
                    armorStand.setCustomName(MobSystem.getPercentHealth(e));
                }
            }
        }).runTaskTimer((Plugin)azerot.getInstance(), 20L, 1L);
    }

}
