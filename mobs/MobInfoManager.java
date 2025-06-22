package azerot.azerot.mobs;

import java.util.HashMap;
import org.bukkit.entity.Entity;

public class MobInfoManager {
    public static HashMap<Entity, MobInfo> getMobInfo() {
        return mobInfo;
    }

    public static HashMap<Entity, MobInfo> mobInfo = new HashMap<>();

    public void MobInfoManager(Entity e) {
        MobInfo mobInfo = MobInfo.builder().id(0).number(0).level(0).name("").respawn(0).xp(0).damage(0).magicDamage(0).pureDamage(0).manaDamage(0).critChance(0).vampiric(0).knockback(0.0D).range(0.0D).attackSpeed(0).health(0).maxHealth(0).mana(0).maxMana(0).armor(0).magicArmor(0).percentArmor(1.0D).resistance(0).dodge(0).dodgePower(0).mobRange(0.0D).blackB(0).yellowB(0).manaMob(0).aggressive(1).script(null).leveling(0).champion(1).visibleTo(null).targetCount(17).lastAttackTime(0L).spawnLocation(null).build();
        final MobInfoManager mobInfoManager = this;
        MobInfoManager.mobInfo.put(e, mobInfo);
    }

    public static MobInfo getMobInfo(Entity e) {
        if (!mobInfo.containsKey(e))
            (new MobInfoManager()).MobInfoManager(e);
        return mobInfo.get(e);
    }

    public static void removeMobInfo(Entity e) {
        mobInfo.remove(e);
    }
}
