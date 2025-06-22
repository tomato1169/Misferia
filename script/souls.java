package azerot.azerot.script;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import azerot.azerot.MobSpells;
import azerot.azerot.mobs.MobInfo;
import azerot.azerot.mobs.MobInfoManager;
import azerot.azerot.mobs.MobSystem;
import azerot.azerot.rpgplayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.util.Vector;

public class souls {
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
            message(e, "§4Не..ее меш...а..йте н...а...мм");
                    attack1.put(e.getUniqueId().toString(), 1);
            for (Player nearby : p){
                velocity = nearby.getLocation().getDirection().normalize().multiply(-2.0D).setY(1D);
                nearby.setVelocity(velocity);
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
