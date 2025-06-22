package azerot.azerot.script;

import azerot.azerot.*;
import azerot.azerot.mobs.MobInfo;
import azerot.azerot.mobs.MobInfoManager;
import azerot.azerot.mobs.MobSystem;
import net.minecraft.server.v1_12_R1.AttributeInstance;
import net.minecraft.server.v1_12_R1.EntityInsentient;
import net.minecraft.server.v1_12_R1.GenericAttributes;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class alchemist {

    static HashMap<String, Integer> attack1 = new HashMap<>();
    static HashMap<String, Integer> stageBoss = new HashMap<>();

    public static void onDamaged(Entity e, Entity t, int damage) throws SQLException {
        Vector velocity;
        MobInfo mobInfo = MobInfoManager.getMobInfo(e);
        int id = mobInfo.getId();
        double percentage = (double) mobInfo.getHealth() / mobInfo.getMaxHealth();
        List<Player> p = MobSystem.getNearbyPlayers((LivingEntity)e, 8.0D);
        if (mobInfo.getHealth() + damage == mobInfo.getMaxHealth()) {
            mobInfo.setCritChance(0);
            attack1.put(e.getUniqueId().toString(), 0);

        }

        Double speed = 0.7D;
        if (percentage < 0.85D && (Integer) attack1.get(e.getUniqueId().toString()) == 0) {
            message(e, "§4Что же так слабо?");
            attack1.put(e.getUniqueId().toString(), 1);
            WorldUtils.spawnMob(String.valueOf(50), e.getLocation(), String.valueOf(0));
            WorldUtils.spawnMob(String.valueOf(50), e.getLocation(), String.valueOf(0));
            WorldUtils.spawnMob(String.valueOf(50), e.getLocation(), String.valueOf(0));
            (new BukkitRunnable() {
                public void run() {
                    if(MobSystem.getTarget(e) == null){
                        cancel();
                    }
                    if(stageBoss.get(e.getUniqueId().toString()) == 0){
                        mobInfo.setDamage((int) (mobInfo.getDamage() + Math.round(mobInfo.getDamage() * 0.1)));
                        mobInfo.setMagicDamage((int) (mobInfo.getMagicDamage() + Math.round(mobInfo.getMagicDamage() * 0.1)));
                    }
                    if(stageBoss.get(e.getUniqueId().toString()) == 1){
                        mobInfo.setCritChance(mobInfo.getCritChance() + 10);
                    }
                    if(e.isDead()){
                        cancel();
                    }
                }
            }).runTaskTimer((Plugin) azerot.getPlugin(azerot.class), 200L, 200L);
        }
        if (percentage < 0.5D && (Integer) attack1.get(e.getUniqueId().toString()) == 1) {
            message(e, "§4Что же так слабо?");
            attack1.put(e.getUniqueId().toString(), 2);
            stageBoss.put(e.getUniqueId().toString(), 1);

            azerot.getInstance().setClonesAlchemist(4);
            net.minecraft.server.v1_12_R1.Entity ent = WorldUtils.spawnMob(String.valueOf(1000), new Location(e.getWorld(),-4415, 84, 3937), String.valueOf(0));
            net.minecraft.server.v1_12_R1.Entity ent1 = WorldUtils.spawnMob(String.valueOf(1000), new Location(e.getWorld(),-4404, 84, 3958), String.valueOf(0));
            net.minecraft.server.v1_12_R1.Entity ent2 = WorldUtils.spawnMob(String.valueOf(1000), new Location(e.getWorld(),-4422, 84, 3965), String.valueOf(0));
            net.minecraft.server.v1_12_R1.Entity ent3 = WorldUtils.spawnMob(String.valueOf(1000), new Location(e.getWorld(),-4435, 84, 3944), String.valueOf(0));
            e.teleport(new Location(Bukkit.getWorld("world"), -4429, 166, 3944));
            (new BukkitRunnable() {
                public void run() {
                    if(!ent.getBukkitEntity().isDead()){
                        MobSystem.customRemove(ent.getBukkitEntity());
                    }
                    if(!ent1.getBukkitEntity().isDead()){
                        MobSystem.customRemove(ent1.getBukkitEntity());
                    }
                    if(!ent2.getBukkitEntity().isDead()){
                        MobSystem.customRemove(ent2.getBukkitEntity());
                    }
                    if(!ent3.getBukkitEntity().isDead()){
                        MobSystem.customRemove(ent3.getBukkitEntity());
                    }
                        e.teleport(new Location(Bukkit.getWorld("world"),-4423, 84, 3946));
                        mobInfo.setHealth((int) (Math.round((mobInfo.getMaxHealth()) * (double) (0.1 * azerot.getInstance().getClonesAlchemist())) + mobInfo.getHealth()));

                }
            }).runTaskLater((Plugin) azerot.getPlugin(azerot.class), 200L);
        }
        if (percentage < 0.25D && (Integer) attack1.get(e.getUniqueId().toString()) == 2) {
            message(e, "§4Что же так слабо?");
            attack1.put(e.getUniqueId().toString(), 3);
            mobInfo.setVampiric(50);
            final int[] i = {0};
            (new BukkitRunnable() {
                public void run() {
                    i[0]++;
                    if(e.isDead()){
                        cancel();
                    }
                    if(i[0] == 60){
                        cancel();
                    }
                    for(Player p : p){
                        if(p.getLocation().distance(e.getLocation()) >= 2){
                            p.setVelocity(e.getLocation().toVector().subtract(p.getLocation().toVector()).normalize().multiply(0.75));
                            RPGDamageble.damage(p, (((double) mobInfo.getDamage() / 2) + ((double) mobInfo.getMagicDamage() / 2)), null, null);
                        }
                    }

                }
            }).runTaskTimer((Plugin) azerot.getPlugin(azerot.class), 20L, 20L);
        }

    }


    public static void onSpawn(Entity entity) {
        stageBoss.put(entity.getUniqueId().toString(), 0);
    }

    public static void message(Entity e, String message) {
        MobInfo mobInfo = MobInfoManager.getMobInfo(e);
        int level = mobInfo.getLevel();
        String name = mobInfo.getName();
        List<Player> p = MobSystem.getNearbyPlayers((LivingEntity)e, 8.0D);
        for (Player nearby : p)
            nearby.sendMessage("§4["+ level + "§4] " + name + "§f: " + message);
    }
}

