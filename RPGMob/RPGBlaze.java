package azerot.azerot.RPGMob;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Set;

import azerot.azerot.PathfinderGoalMeleeAttack;
import azerot.azerot.mobs.MobSystem;
import azerot.azerot.mobs.RPGMobsContainer;
import net.minecraft.server.v1_12_R1.EntityBlaze;
import net.minecraft.server.v1_12_R1.EntityCreature;
import net.minecraft.server.v1_12_R1.EntityInsentient;
import net.minecraft.server.v1_12_R1.EnumParticle;
import net.minecraft.server.v1_12_R1.PathfinderGoal;
import net.minecraft.server.v1_12_R1.PathfinderGoalFloat;
import net.minecraft.server.v1_12_R1.PathfinderGoalMoveTowardsRestriction;
import net.minecraft.server.v1_12_R1.PathfinderGoalSelector;
import net.minecraft.server.v1_12_R1.SoundEffects;
import net.minecraft.server.v1_12_R1.World;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import azerot.azerot.azerot;

public class RPGBlaze extends EntityBlaze {
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

    protected void r() {}

    public void n() {
        if (this.world.isClientSide) {
            if (this.random.nextInt(24) == 0 && !isSilent())
                this.world.a(this.locX + 0.5D, this.locY + 0.5D, this.locZ + 0.5D, SoundEffects.D, bK(), 1.0F + this.random.nextFloat(), this.random.nextFloat() * 0.7F + 0.3F, false);
            for (int var1 = 0; var1 < 2; var1++)
                this.world.addParticle(EnumParticle.SMOKE_LARGE, this.locX + (this.random.nextDouble() - 0.5D) * this.width, this.locY + this.random.nextDouble() * this.length, this.locZ + (this.random.nextDouble() - 0.5D) * this.width, 0.0D, 0.0D, 0.0D, new int[0]);
        }
        super.n();
    }

    protected void M() {}

    public RPGBlaze(Location loc, int id, String number) throws SQLException {
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
        mobconfig(loc);
    }

    private void mobconfig(Location loc) {
        Blaze z = (Blaze)getBukkitEntity();
        z.setMaximumNoDamageTicks(0);
        z.setCustomNameVisible(true);
        z.setRemoveWhenFarAway(false);
        z.setSilent(true);
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
