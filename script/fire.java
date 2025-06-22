package azerot.azerot.script;

import azerot.azerot.MobSpells;
import azerot.azerot.rpgplayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class fire {

    public static void onAttack(Entity e, Entity t, int chance) {
        if (Math.random() * 100.0D <= chance)
            MobSpells.brokenweapon((Player)t);
    }
}
