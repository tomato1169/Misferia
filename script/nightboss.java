package azerot.azerot.script;

import azerot.azerot.*;
import azerot.azerot.mobs.MobInfo;
import azerot.azerot.mobs.MobInfoManager;
import azerot.azerot.mobs.MobSystem;
import de.slikey.effectlib.effect.StarEffect;
import io.netty.util.internal.ThreadLocalRandom;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.sql.SQLException;
import java.util.*;

public class nightboss {
    static HashMap<String, Integer> attack1 = new HashMap<>();

    static HashMap<Integer, Location> locationBlocks = new HashMap<>();

    static HashMap<Integer, Integer> filledlocation = new HashMap<>();


    public static void onDamaged(Entity e, Entity t, int damage) throws SQLException {
        MobInfo mobInfo = MobInfoManager.getMobInfo(e);
        int id = mobInfo.getId();
        double percentage = (double) mobInfo.getHealth() / mobInfo.getMaxHealth();
        List<Player> p = MobSystem.getNearbyPlayers((LivingEntity)e, 8.0D);
        final int[] i = {0};

        if (mobInfo.getHealth() + damage == mobInfo.getMaxHealth()) {
            mobInfo.setCritChance(0);
            attack1.put(e.getUniqueId().toString(), 0);
        }
        if (percentage < 0.85D && (Integer) attack1.get(e.getUniqueId().toString()) == 0) {
            attack1.put(e.getUniqueId().toString(), 1);
            Location location = new Location(Bukkit.getWorld("world"), -4709,117,4032);
            for(Player nearby : p){
                long time = Bukkit.getWorld("world").getTime();
                if(time < 12300){
                    MobSpells.curseBlood(e, nearby, 5000, 1);
                }else{
                    MobSpells.curseBlood(e, nearby, 10000, 2);
                }

            }

        }
        if (percentage < 0.70D && (Integer) attack1.get(e.getUniqueId().toString()) == 1) {
            message(e, "§4");
            attack1.put(e.getUniqueId().toString(), 2);
            for(Player nearby : p){
                (new BukkitRunnable() {
                    public void run() {
                        try {
                            if(MobSystem.getTarget(e) == null){
                                cancel();
                                return;
                            }
                            if (e.isDead()) {
                                cancel();
                                return;
                            }else {
                                StarEffect starEffect = new StarEffect(azerot.getEffectManager());
                                int randomNum = ThreadLocalRandom.current().nextInt(0, locationBlocks.size());
                                filledlocation.put(i[0], randomNum);
                                i[0]++;
                                starEffect.setLocation(locationBlocks.get(randomNum));
                                starEffect.particle = Particle.FLAME;
                                starEffect.iterations = 1;
                                starEffect.duration = 15;
                                starEffect.spikeHeight = 0.5F;
                                starEffect.spikesHalf = 2;
                                starEffect.start();
                                for(int j = 0; j <= filledlocation.size(); j++){
                                    if(nearby.getLocation().getBlockX() == locationBlocks.get(filledlocation.get(j)).getBlockX() && nearby.getLocation().getBlockZ() == locationBlocks.get(filledlocation.get(j)).getBlockZ()){
                                        RPGDamageble.damage(nearby, (int)(rpgplayer.getRPGPlayer((Player)t).getMaxHealth() * 0.01D), null, null);
                                    }
                                }
                            }
                        } catch (IndexOutOfBoundsException exception) {
                            cancel();
                            return;
                        }
                    }
                }).runTaskTimer((Plugin)azerot.getPlugin(azerot.class), 0L, 20L);
            }

        }
        if (percentage < 0.40D && (Integer) attack1.get(e.getUniqueId().toString()) == 2) {
            message(e, "§4");
            attack1.put(e.getUniqueId().toString(), 3);
            long time = Bukkit.getWorld("world").getTime();
            if(time < 12300){
                WorldUtils.spawnMob(String.valueOf(47), e.getLocation(), String.valueOf(0));
            }else{
                WorldUtils.spawnMob(String.valueOf(48), e.getLocation(), String.valueOf(0));

            }
        }
        if (percentage < 0.25D && (Integer) attack1.get(e.getUniqueId().toString()) == 3) {
            message(e, "§4");
            attack1.put(e.getUniqueId().toString(), 4);
            for(Player nearby : p){
                long time = Bukkit.getWorld("world").getTime();
                if(time < 12300){
                    mobInfo.setAttackSpeed(13);
                }else{
                    mobInfo.setDodge(15);
                }

                (new BukkitRunnable() {
                    public void run() {
                        try {
                            if(MobSystem.getTarget(e) == null){
                                cancel();
                                return;
                            }
                            if (e.isDead()) {
                                cancel();
                                return;
                            }else {
                                for(int j = 0; j <= filledlocation.size(); j++){
                                    if(nearby.getLocation().getBlockX() == locationBlocks.get(filledlocation.get(j)).getBlockX() && nearby.getLocation().getBlockZ() == locationBlocks.get(filledlocation.get(j)).getBlockZ()){
                                        RPGDamageble.damage(nearby, (int)(rpgplayer.getRPGPlayer((Player)nearby).getMaxHealth() * 0.02D), null, null);
                                    }
                                }
                            }
                        } catch (IndexOutOfBoundsException exception) {
                            cancel();
                            return;
                        }
                    }
                }).runTaskTimer((Plugin)azerot.getPlugin(azerot.class), 0L, 20L);
            }

        }
        if (percentage < 0.15D && (Integer) attack1.get(e.getUniqueId().toString()) == 4) {
            message(e, "§4");
            attack1.put(e.getUniqueId().toString(), 5);
            for(Player nearby : p){
                MobSpells.curseVortex(e, nearby, 3000,10);
            }

        }


        }


    public static void setLocationBlocks(Location loc){
        Location location = new Location(Bukkit.getWorld("world"), -4709,117,4032);
        int i = 0;
        for(int ip = -9; ip <= 9 ; ip++){
            for(int j = -9 ; j <= 9; j++){
                locationBlocks.put(i, new Location(Bukkit.getWorld("world"), location.getBlockX() + ip,117, location.getBlockZ() + j));
                i++;
            }
        }

    }



    public static void onSpawn(Entity entity) {
        setLocationBlocks(entity.getLocation());
        Location location = new Location(Bukkit.getWorld("world"), -4709,117,4032);
        MobInfo mobInfo = MobInfoManager.getMobInfo(entity);
        (new BukkitRunnable() {
            public void run() {
                try {
                    if (entity.isDead()) {
                        cancel();
                        return;
                    }else {
                        if(entity.getLocation().distance(location) < 9){
                            long time = Bukkit.getWorld("world").getTime();
                            if(time < 12300){
                                mobInfo.setMagicArmor(0);
                                mobInfo.setMagicDamage(0);
                                mobInfo.setDamage(70);
                                mobInfo.setArmor(150);
                            }else{
                                mobInfo.setMagicArmor(150);
                                mobInfo.setMagicDamage(70);
                                mobInfo.setDamage(0);
                                mobInfo.setArmor(0);
                            }
                        }else {
                            long time = Bukkit.getWorld("world").getTime();
                            if(time < 12300){
                                mobInfo.setMagicArmor(0);
                                mobInfo.setMagicDamage(0);
                                mobInfo.setDamage(120);
                                mobInfo.setArmor(200);
                            }else{
                                mobInfo.setMagicArmor(200);
                                mobInfo.setMagicDamage(120);
                                mobInfo.setDamage(0);
                                mobInfo.setArmor(0);
                            }
                        }
                    }
                } catch (IndexOutOfBoundsException exception) {
                    cancel();
                    return;
                }
            }
        }).runTaskTimer((Plugin)azerot.getPlugin(azerot.class), 0L, 20L);
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


