package azerot.azerot.RPGMob;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Set;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;
import net.minecraft.server.v1_12_R1.EntityZombie;
import net.minecraft.server.v1_12_R1.PathfinderGoalSelector;
import net.minecraft.server.v1_12_R1.World;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import azerot.azerot.azerot;
public class RPGHuman extends EntityZombie {
    File file = new File(azerot.getInstance().getDataFolder() + File.separator + "NPCs.yml");

    FileConfiguration config = (FileConfiguration)YamlConfiguration.loadConfiguration(this.file);

    private int id;

    private String type;

    private String name;
    private int health;

    private int maxhealth;

    private String skin;

    public RPGHuman(Location loc, int id) {
        super((World)((CraftWorld)loc.getWorld()).getHandle());
        this.id = id;
        this.type = this.config.getString(String.valueOf(this.id) + ".type");
        this.name = this.config.getString(String.valueOf(this.id) + ".name");
        this.skin = this.config.getString(String.valueOf(this.id) + ".skin");
        this.health = this.config.getInt(String.valueOf(this.id) + "health");
        this.maxhealth = this.config.getInt(String.valueOf(this.id) + "health");

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
        int invisible = (this.config.getString(String.valueOf(this.id) + ".invisible") != null) ? this.config.getInt(String.valueOf(this.id) + ".invisible") : 0;
        Zombie z = (Zombie)getBukkitEntity();
        Entity entity = z;
        z.setMaximumNoDamageTicks(0);
        z.setCustomNameVisible(true);
        z.setRemoveWhenFarAway(false);
        z.setInvulnerable(true);
        z.setSilent(true);
        z.setAI(false);
        if (invisible == 1)
            z.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 2147483647, Integer.valueOf(this.config.getInt(String.valueOf(this.id) + ".speed")).intValue(), false, false));
        z.setCustomName("§f" + this.name);
                z.setMetadata("npcs", (MetadataValue)new FixedMetadataValue((Plugin)azerot.getInstance(), String.valueOf(this.type)));
        z.setMetadata("id", (MetadataValue)new FixedMetadataValue((Plugin)azerot.getInstance(), Integer.valueOf(this.id)));
        z.setMetadata("name", (MetadataValue)new FixedMetadataValue((Plugin)azerot.getInstance(), String.valueOf(this.name)));
        String hand = this.config.getString(String.valueOf(this.id) + ".hand");
        String offhand = this.config.getString(String.valueOf(this.id) + ".offhand");
        String helmet = this.config.getString(String.valueOf(this.id) + ".helmet");
        String chestplate = this.config.getString(String.valueOf(this.id) + ".chestplate");
        String leggings = this.config.getString(String.valueOf(this.id) + ".leggings");
        String boots = this.config.getString(String.valueOf(this.id) + ".boots");
        if(skin != null) {
            DisguiseAPI.disguiseEntity((Entity) getBukkitEntity(), (Disguise) new PlayerDisguise(this.skin));
            Disguise disguise = DisguiseAPI.getDisguise((Entity) getBukkitEntity());
            disguise.getWatcher().setCustomName(this.name);
            disguise.getWatcher().setCustomNameVisible(true);
        }
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