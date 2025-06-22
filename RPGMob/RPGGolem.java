package azerot.azerot.RPGMob;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Set;

import azerot.azerot.RPGItem;
import azerot.azerot.mobs.MobSystem;
import azerot.azerot.mobs.RPGMobsContainer;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import azerot.azerot.azerot;

public class RPGGolem extends EntityIronGolem {
    File file = new File(azerot.getInstance().getDataFolder() + File.separator + "mobs.yml");

    FileConfiguration config = (FileConfiguration)YamlConfiguration.loadConfiguration(this.file);

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

    public RPGGolem(Location loc, int id, String number) throws SQLException {
        super((World)((CraftWorld)loc.getWorld()).getHandle());
        this.id = id;
        this.number = number;
        this.respawn = this.config.getInt(String.valueOf(this.id) + ".respawn");
        this.name = this.config.getString(String.valueOf(this.id) + ".name");
        this.level = this.config.getInt(String.valueOf(this.id) + ".level");
        this.xp = this.config.getInt(String.valueOf(this.id) + ".xp");
        this.health = this.config.getInt(String.valueOf(this.id) + ".health");
        this.maxhealth = this.config.getInt(String.valueOf(this.id) + ".health");
        this.damage = this.config.getInt(String.valueOf(this.id) + ".damage");
        this.range = this.config.getInt(String.valueOf(this.id) + ".range");
        this.attackspeed = this.config.getLong(String.valueOf(this.id) + ".attackspeed");
        this.script = this.config.getString(String.valueOf(this.id) + ".script");
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
        this.goalSelector.a(8, (PathfinderGoal)new PathfinderGoalLookAtPlayer((EntityInsentient)this, EntityHuman.class, 2.0F));
        this.goalSelector.a(8, (PathfinderGoal)new PathfinderGoalRandomLookaround((EntityInsentient)this));
        mobconfig(loc);
    }

    private void mobconfig(Location loc) {
        IronGolem z = (IronGolem)getBukkitEntity();
        z.setMaximumNoDamageTicks(0);
        z.setCustomNameVisible(true);
        z.setRemoveWhenFarAway(false);
        z.setSilent(false);
        RPGMobsContainer rmc = RPGMobsContainer.getRPGMobsContainer(id);
        int hand = rmc.getHand();
        int offhand = rmc.getOffhand();
        String helmet = rmc.getHelmet();
        String chestplate = rmc.getChestplate();
        String leggings = rmc.getLeggings();
        String boots = rmc.getLeggings();
        if(hand != 0){
            z.getEquipment().setItemInMainHand(new RPGItem().getRPGItem(hand,0,1,0,0,0,0,0,null));
        }
        MobSystem.setData((Entity)z, this.id, Integer.parseInt(this.number), null);
        z.setCustomName(MobSystem.getFullName((Entity)z));
        RPGMobsContainer monster = RPGMobsContainer.getRPGMobsContainer(id);
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
