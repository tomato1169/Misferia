package azerot.azerot.script;

import azerot.azerot.MobSpells;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import azerot.azerot.rpgplayer;

public class Poison {
    public static void onAttack(Entity e, Entity t, int chance, int duration) {
        if (Math.random() * 100.0D <= chance)
            MobSpells.tickdamage(e, (Player)t, (int)(rpgplayer.getRPGPlayer((Player)t).getMaxHealth() * 0.05D), 1000, duration, "§eВы отравлены!");
    }
}
