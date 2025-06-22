package azerot.azerot.RPGMob;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Set;
import javax.annotation.Nullable;

import azerot.azerot.*;
import azerot.azerot.PathfinderGoalMeleeAttack;
import azerot.azerot.mobs.MobSystem;
import azerot.azerot.mobs.RPGMobsContainer;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Wolf;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RPGWolf extends EntityWolf {
    private int id;

    private String number;

    private int respawn;

    private String name;

    private int level;

    private int xp;

    private int health;

    private int maxhealth;

    private int damage;

    private float range;

    private long attackspeed;

    private String script;


    public RPGWolf(Location loc, int id, String number) throws SQLException {
        super((World)((CraftWorld)loc.getWorld()).getHandle());
        RPGMobsContainer rmc = RPGMobsContainer.getRPGMobsContainer(id);
        this.id = id;
        this.number = number;
        this.respawn = (int) rmc.getRespawn();
        this.name = rmc.getName();
        this.level = rmc.getLevel();
        this.xp = rmc.getXp();
        this.health = rmc.getHealth();
        this.maxhealth = rmc.getHealth();
        this.damage = rmc.getDamage();
        this.range = rmc.getRange();
        this.attackspeed = rmc.getAttackspeed();
        this.script = rmc.getScript();
        Set goalB = (Set)getPrivateField("b", PathfinderGoalSelector.class, this.goalSelector);
        goalB.clear();
        Set goalC = (Set)getPrivateField("c", PathfinderGoalSelector.class, this.goalSelector);
        goalC.clear();
        Set targetB = (Set)getPrivateField("b", PathfinderGoalSelector.class, this.targetSelector);
        targetB.clear();
        Set targetC = (Set)getPrivateField("c", PathfinderGoalSelector.class, this.targetSelector);
        targetC.clear();
        this.goalSelector.a(0, (PathfinderGoal)new PathfinderGoalFloat((EntityInsentient)this));
        this.goalSelector.a(2, (PathfinderGoal)new PathfinderGoalMeleeAttack((EntityCreature)this, 1.3D, false));
        this.goalSelector.a(5, (PathfinderGoal)new PathfinderGoalMoveTowardsRestriction((EntityCreature)this, 1.0D));
        this.goalSelector.a(7, (PathfinderGoal)new PathfinderGoalRandomStroll((EntityCreature)this, 1.0D));
        mobconfig(loc);
    }

    private void mobconfig(Location loc) {
        Wolf z = (Wolf)getBukkitEntity();
        z.setMaximumNoDamageTicks(0);
        z.setCustomNameVisible(true);
        z.setRemoveWhenFarAway(false);
        z.setSilent(true);
        z.setAgeLock(true);
        MobSystem.setData((Entity)z, this.id, Integer.parseInt(this.number), null);
        z.setCustomName(MobSystem.getFullName((Entity)z));
        RPGMobsContainer rmc = RPGMobsContainer.getRPGMobsContainer(id);
        MobSystem.mobToHome((LivingEntity)z);
    }

    public static Object getPrivateField(String fieldName, Class clazz, Object object) {
        Object o = null;
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            o = field.get(object);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return o;
    }
}
