package azerot.azerot.RPGMob;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Set;

import azerot.azerot.*;
import azerot.azerot.PathfinderGoalMeleeAttack;
import azerot.azerot.mobs.MobInfo;
import azerot.azerot.mobs.MobSystem;
import azerot.azerot.mobs.RPGMobsContainer;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import net.minecraft.server.v1_12_R1.EntityZombie;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RPGZombie extends EntityZombie {


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
    private String skin;

    public RPGZombie(Location loc, int id, String number) throws SQLException {

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
        this.skin = rmc.getSkin();
        if(skin != null ) {
            DisguiseAPI.disguiseEntity((Entity) getBukkitEntity(), (Disguise) new PlayerDisguise(this.skin));
            getBukkitEntity().setMetadata("texture", (MetadataValue)new FixedMetadataValue((Plugin)azerot.getInstance(), skin));
        }
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
        this.goalSelector.a(7, (PathfinderGoal)new PathfinderGoalRandomStrollLand(this, 1.0D));
        this.goalSelector.a(8, (PathfinderGoal)new PathfinderGoalLookAtPlayer((EntityInsentient)this, EntityHuman.class, 2.0F));
        this.goalSelector.a(8, (PathfinderGoal)new PathfinderGoalRandomLookaround((EntityInsentient)this));
        this.goalSelector.a(7, (PathfinderGoal)new PathfinderGoalWalkToLoc((EntityCreature)this, loc.getX(), loc.getY(), loc.getZ(), 1.0D));
        mobconfig(loc);

    }

    private void mobconfig(Location loc) {
        Zombie z = (Zombie)getBukkitEntity();
        z.setMaximumNoDamageTicks(0);
        z.setCustomNameVisible(true);
        z.setRemoveWhenFarAway(false);
        z.setSilent(true);
        MobSystem.setData((Entity) z, this.id, Integer.parseInt(this.number), null);
        z.setCustomName(MobSystem.getFullName((Entity)z));
        RPGMobsContainer rmc = RPGMobsContainer.getRPGMobsContainer(id);
        if(DisguiseAPI.getDisguise((Entity) z) != null) {
            Disguise disguise = DisguiseAPI.getDisguise((Entity) z);
            disguise.getWatcher().setCustomName(MobSystem.getFullName((Entity) z));
            disguise.getWatcher().setCustomNameVisible(true);
        }

        int hand = rmc.getHand();
        int offhand = rmc.getOffhand();
        String helmet = rmc.getHelmet();
        String chestplate = rmc.getChestplate();
        String leggings = rmc.getLeggings();
        String boots = rmc.getLeggings();
        if(hand != 0){
            z.getEquipment().setItemInMainHand(new RPGItem().getRPGItem(hand,0,1,0,0,0,0,0,null));
        }
        if (offhand != 0)
            z.getEquipment().setItemInOffHand(new RPGItem().getRPGItem(offhand,0,1,0,0,0,0,0,null));
        if (helmet != null){
            z.getEquipment().setHelmet(ItemsForGui.setUnbreakable(new ItemStack(Material.getMaterial(helmet))));
        }else{
            z.getEquipment().setHelmet(null);
        }
        if (chestplate != null) {
            z.getEquipment().setChestplate(ItemsForGui.setUnbreakable(new ItemStack(Material.getMaterial(chestplate))));
        }else{
            z.getEquipment().setChestplate(null);
        }
        if (leggings != null){
            z.getEquipment().setLeggings(ItemsForGui.setUnbreakable(new ItemStack(Material.getMaterial(leggings))));
        }else{
            z.getEquipment().setLeggings(null);
        }

        if (boots != null)
            z.getEquipment().setBoots(ItemsForGui.setUnbreakable(new ItemStack(Material.getMaterial(boots))));

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

