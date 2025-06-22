package azerot.azerot.script;

import azerot.azerot.RPGDamageble;
import azerot.azerot.rpgplayer;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.sql.SQLException;

public class Slowness {



    public static void onAttack(Entity e, Entity t) throws SQLException {
        rpgplayer p = new rpgplayer((Player) t);
        RPGDamageble.damage(t, (p.getMaxHealth()/20), null, null);
    }
}
