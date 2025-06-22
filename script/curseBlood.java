package azerot.azerot.script;

import azerot.azerot.MobSpells;
import azerot.azerot.mobs.MobInfo;
import azerot.azerot.mobs.MobInfoManager;
import azerot.azerot.mobs.MobSystem;
import azerot.azerot.rpgplayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.List;

public class curseBlood {
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
        if (percentage < 0.6D && (Integer) attack1.get(e.getUniqueId().toString()) == 0) {
            attack1.put(e.getUniqueId().toString(), 1);
            for (Player nearby : p){
                rpgplayer pl = rpgplayer.getRPGPlayer(nearby);
                MobSpells.curseBlood(e, nearby, 5000, 1);
                return;
            }
        }




    }
}