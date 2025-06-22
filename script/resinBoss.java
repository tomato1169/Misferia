package azerot.azerot.script;

import azerot.azerot.MobSpells;
import azerot.azerot.RPGDamageble;
import azerot.azerot.azerot;
import azerot.azerot.mobs.MobInfo;
import azerot.azerot.mobs.MobInfoManager;
import azerot.azerot.mobs.MobSystem;
import azerot.azerot.rpgplayer;
import com.comphenix.packetwrapper.WrapperPlayServerEntityDestroy;
import com.comphenix.packetwrapper.WrapperPlayServerEntityMetadata;
import com.comphenix.packetwrapper.WrapperPlayServerMount;
import com.comphenix.packetwrapper.WrapperPlayServerSpawnEntityLiving;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import net.minecraft.server.v1_12_R1.AttributeInstance;
import net.minecraft.server.v1_12_R1.EntityInsentient;
import net.minecraft.server.v1_12_R1.GenericAttributes;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftLivingEntity;
import org.bukkit.entity.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class resinBoss {
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
            message(e, "§4");
            attack1.put(e.getUniqueId().toString(), 1);
            for(Player nearby : p){
                MobSpells.curseVortex(e, nearby, 3000,10);
            }

        }
        if (percentage < 0.50D && (Integer) attack1.get(e.getUniqueId().toString()) == 1) {
            message(e, "§4");
            attack1.put(e.getUniqueId().toString(), 2);
            for(Player nearby : p){
                MobSpells.curseVortex(e, nearby, 3000,10);
            }
            mobInfo.setMagicArmor(150);
            mobInfo.setArmor(0);

        }
        if (percentage < 0.35D && (Integer) attack1.get(e.getUniqueId().toString()) == 2) {
            message(e, "§4");
            attack1.put(e.getUniqueId().toString(), 3);
            for(Player nearby : p){
                MobSpells.curseBlood(e, nearby, 5000, 3);
            }
            mobInfo.setMagicArmor(150);
           Player pl = (Player) ((Creature)e).getTarget();
           pl.setWalkSpeed(0);

        }
        if (percentage < 0.15D && (Integer) attack1.get(e.getUniqueId().toString()) == 3) {
            message(e, "§4");
            attack1.put(e.getUniqueId().toString(), 4);
            for(Player nearby : p){
                MobSpells.tickdamage(e, (Player)t, (int)(rpgplayer.getRPGPlayer((Player)t).getMaxHealth() * 0.05D), 1000, 100, "§eВы отравлены!");
                (new BukkitRunnable() {
                    public void run() {
                        try {
                            if (!nearby.isOnline()) {
                                cancel();
                                return;
                            }else {
                                if(e.isDead()){
                                    nearby.setWalkSpeed(0.2F);
                                    MobSpells.cancellSpells(nearby, "all");
                                    cancel();
                                }
                            }



                        } catch (IndexOutOfBoundsException exception) {
                            cancel();
                            return;
                        }
                    }
                }).runTaskTimer((Plugin)azerot.getPlugin(azerot.class), 0L, 1L);
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

