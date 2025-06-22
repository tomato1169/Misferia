package azerot.azerot.script;

import azerot.azerot.MobSpells;
import azerot.azerot.WorldUtils;
import azerot.azerot.mobs.MobInfo;
import azerot.azerot.mobs.MobInfoManager;
import azerot.azerot.mobs.MobSystem;
import azerot.azerot.rpgplayer;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class sunOfBoss {
    static HashMap<String, Integer> attack1 = new HashMap<>();

    public static void onDamaged(Entity e, Entity t, int damage) throws SQLException {
        Vector velocity;
        MobInfo mobInfo = MobInfoManager.getMobInfo(e);
        int id = mobInfo.getId();
        double percentage = (double) mobInfo.getHealth() / mobInfo.getMaxHealth();
        List<Player> p = MobSystem.getNearbyPlayers((LivingEntity)e, 8.0D);
        if (mobInfo.getHealth() + damage == mobInfo.getMaxHealth()) {
            mobInfo.setCritChance(0);
            attack1.put(e.getUniqueId().toString(), Integer.valueOf(0));
        }
        if (percentage < 0.85D && (Integer) attack1.get(e.getUniqueId().toString()) == 0) {
            if(p.size() == 1){
                message(e, "§4Да как ты смеешь??");
            }else {
                message(e, "§4Да как вы смеете???");
            }
            WorldUtils.spawnMob(String.valueOf(32), e.getLocation(), String.valueOf(0));
            WorldUtils.spawnMob(String.valueOf(33), e.getLocation(), String.valueOf(0));
            attack1.put(e.getUniqueId().toString(), 1);
            for (Player nearby : p){

            }
        }
        if (percentage < 0.60D && (Integer) attack1.get(e.getUniqueId().toString()) == 1) {
            message(e, "§4Помогите же мне, мои солдаты");
            WorldUtils.spawnMob(String.valueOf(35), e.getLocation(), String.valueOf(0));
            WorldUtils.spawnMob(String.valueOf(35), e.getLocation(), String.valueOf(0));
            attack1.put(e.getUniqueId().toString(), 2);

        }
        if (percentage < 0.40D && (Integer) attack1.get(e.getUniqueId().toString()) == 2) {
            if(p.size() == 1){
                message(e, "§4Ты оказался сильнее, чем я думал");
            }else {
                message(e, "§4Вы оказались сильнее, чем я думал");
            }
            WorldUtils.spawnMob(String.valueOf(34), e.getLocation(), String.valueOf(0));
            attack1.put(e.getUniqueId().toString(), 3);
        }
        if (percentage < 0.20D && (Integer) attack1.get(e.getUniqueId().toString()) == 3) {
                message(e, "§4Ты оказался сильнее, чем я думал");
            for(Player nearby : p){
                MobSpells.curseVortex(e, nearby, 3000,10);
            }
            attack1.put(e.getUniqueId().toString(), 4);
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