package azerot.azerot.RPGMob;

import azerot.azerot.azerot;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.EntityZombie;
import net.minecraft.server.v1_12_R1.PathfinderGoalSelector;
import net.minecraft.server.v1_12_R1.World;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Zombie;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.lang.reflect.Field;
import java.util.Set;

public class NPCMob extends EntityZombie {
    private String name;

    public NPCMob(Location loc, int id) {
        super((World)((CraftWorld)loc.getWorld()).getHandle());


        Set goalB = (Set)getPrivateField("b", PathfinderGoalSelector.class, this.goalSelector);
        goalB.clear();
        Set goalC = (Set)getPrivateField("c", PathfinderGoalSelector.class, this.goalSelector);
        goalC.clear();
        Set targetB = (Set)getPrivateField("b", PathfinderGoalSelector.class, this.targetSelector);
        targetB.clear();
        Set targetC = (Set)getPrivateField("c", PathfinderGoalSelector.class, this.targetSelector);
        targetC.clear();
        mobconfig(loc);
    }

    private void mobconfig(Location loc) {
        Zombie z = (Zombie)getBukkitEntity();
        Entity entity = z;
        z.setMaximumNoDamageTicks(0);
        z.setCustomNameVisible(true);
        z.setRemoveWhenFarAway(false);
        z.setInvulnerable(true);
        z.setSilent(true);
        z.setAI(false);
        z.setCustomName("§f" + this.name);

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
