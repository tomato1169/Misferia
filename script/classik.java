package azerot.azerot.script;

import java.sql.BatchUpdateException;
import java.sql.SQLException;
import java.util.*;

import azerot.azerot.*;
import azerot.azerot.mobs.MobInfo;
import azerot.azerot.mobs.MobInfoManager;
import azerot.azerot.mobs.MobSystem;
import net.minecraft.server.v1_12_R1.EntityLiving;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class classik {
    static HashMap<String, Integer> attack1 = new HashMap<>();

    public static void onDamaged(Entity e, Entity t, int damage) {
        Vector velocity;
        MobInfo mobInfo = MobInfoManager.getMobInfo(e);
        int id = mobInfo.getId();
        double percentage = (double) mobInfo.getHealth() / mobInfo.getMaxHealth();
        List<Player> p = MobSystem.getNearbyPlayers((LivingEntity)e, 8.0D);
        if (mobInfo.getHealth() + damage == mobInfo.getMaxHealth()) {
            mobInfo.setCritChance(0);
            attack1.put(e.getUniqueId().toString(), Integer.valueOf(0));
        }


        if (percentage < 0.9D && (Integer) attack1.get(e.getUniqueId().toString()) == 0) {
            message(e, "§4ААГРХХХ");
            attack1.put(e.getUniqueId().toString(), 1);
            for (Player nearby : p){
                rpgplayer pl = rpgplayer.getRPGPlayer(nearby);
                MobSpells.curseFear(e, nearby,6000,20);
                return;
            }
        }




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
