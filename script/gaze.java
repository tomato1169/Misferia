package azerot.azerot.script;

import azerot.azerot.MobSpells;
import azerot.azerot.RPGDamageble;
import azerot.azerot.azerot;
import azerot.azerot.mobs.MobSystem;
import azerot.azerot.rpgplayer;
import net.minecraft.server.v1_12_R1.AttributeInstance;
import net.minecraft.server.v1_12_R1.EntityInsentient;
import net.minecraft.server.v1_12_R1.GenericAttributes;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftLivingEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Locale;

public class gaze {

    public static void onAttack(Entity e, Entity t) {
        int radius = 5;
        Location location = e.getLocation();

    }


    public static void onSpawn(Entity entity) {

        AttributeInstance ai = ((EntityInsentient)((CraftLivingEntity)entity).getHandle()).getAttributeInstance(GenericAttributes.MOVEMENT_SPEED);
        ai.setValue(0.0D);
        MobSystem.monsters.add(entity);

    }

}
