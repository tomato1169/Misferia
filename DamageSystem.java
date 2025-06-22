package azerot.azerot;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import azerot.azerot.Quest.questScript;
import azerot.azerot.mobs.MobInfo;
import azerot.azerot.mobs.MobInfoManager;
import azerot.azerot.mobs.MobSystem;
import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class DamageSystem implements Listener {
    static HashMap<String, Long> time = new HashMap<>();

    @EventHandler
    public void mobByPlayer(EntityDamageByEntityEvent e) {
        try {
            if (e.getDamager().getType().equals(EntityType.PLAYER) || e.getDamager().getType().equals(EntityType.ARROW)) {
                if (e.getDamager().getType().equals(EntityType.PLAYER))
                    Handler.unmount((Player) e.getDamager());
                if (!(e.getEntity() instanceof org.bukkit.entity.Damageable))
                    return;
                if (e.getEntity().getType().equals(EntityType.ARMOR_STAND))
                    return;
                if (e.getEntity().getType().equals(EntityType.PLAYER)) {
                    e.setCancelled(true);
                    return;
                }
                if (e.getEntity().hasMetadata("mount")) {
                    e.setCancelled(true);
                    return;
                }
                double damagepower = 1.0D;
                ItemStack item = null;
                Player p = null;
                rpgplayer RPGPlayer = null;
                Entity entity = null;
                boolean isBow = false;
                int id = 0;
                if (e.getDamager().getType().equals(EntityType.PLAYER)) {
                    p = (Player) e.getDamager();
                    entity = e.getEntity();
                    RPGPlayer = RPGPlayer.getRPGPlayer(p);
                    item = p.getInventory().getItemInMainHand();
                    id = ItemStat.getID(item);
                }
                if(e.getDamager().getType().equals(EntityType.FIREBALL)){

                }
                if (e.getDamager().getType().equals(EntityType.ARROW)) {
                    if (!e.getDamager().hasMetadata("caster")) {
                        e.setCancelled(true);
                        return;
                    }
                    double length = ((MetadataValue) e.getDamager().getMetadata("length").get(0)).asDouble();
                    damagepower = Math.max(1.0D, length / 1.5D);
                    p = (Player) ((MetadataValue) e.getDamager().getMetadata("caster").get(0)).value();
                    entity = e.getEntity();
                    RPGPlayer = RPGPlayer.getRPGPlayer(p);
                    if (e.getDamager().hasMetadata("item"))
                        item = (ItemStack) ((MetadataValue) e.getDamager().getMetadata("item").get(0)).value();
                    id = ItemStat.getID(item);
                    isBow = true;
                }
                RPGItemContainer RPGI = RPGItemContainer.getRPGItemContainer(id);
                int mark = ItemStat.getMark(item);
                int markpower = ItemStat.getMarkpower(item);
                int spellid = ItemStat.getSpellID(id);
                int enchant = ItemStat.getEnchant(item);
                int mindamage = RPGI.getDamage(mark,markpower, enchant);
                int maxdamage = RPGI.getMaxDamage(mark,markpower, enchant);
                ItemStack hand = RPGPlayer.toPlayer().getInventory().getItemInOffHand();
                int idItem = ItemStat.getID(hand);
                int minmagicdamage = RPGI.getMagicDamage(mark,markpower, enchant);
                int maxmagicdamage = RPGI.getMaxMagicDamage(mark,markpower, enchant);

                int critchance = RPGI.getCritChance(mark,markpower, enchant);
                critchance += RPGPlayer.getCritChance();
                int critdamage = RPGI.getCritDamage(mark,markpower, enchant);
                critdamage += RPGPlayer.getCritDamage();
                int vampiric = RPGI.getVampiric(mark,markpower, enchant);
                vampiric += RPGPlayer.getVampiric();
                int frozen = RPGI.getFrozen(mark,markpower, enchant);
                int curse = RPGI.getCurse(mark,markpower, enchant);
                int bleeding = RPGI.getBleeding(mark,markpower, enchant);
                int IgnoringArmor = RPGI.getIgnoringArmor(mark,markpower, enchant);
                int stan = RPGI.getStan(mark,markpower, enchant);
                boolean cancelled = false;
                int slot = 0;
                if (RPGI.getType() != 2 || mark == 100 || (e.getDamager().getType().equals(EntityType.PLAYER) && p.getInventory().getItemInMainHand().getType().equals(Material.BOW)))
                    cancelled = true;
                if (!ItemStat.canuseitem(RPGPlayer, item)) {
                    cancelled = true;
                    p.sendMessage(WorldUtils.worldName() + " §cВы не можете использовать этот предмет!");
                }
                for (int i = 0; i < p.getInventory().getSize(); i++) {
                    slot = WorldUtils.checkItems(RPGPlayer, 87, 0);
                }
                int idMob = MobInfoManager.getMobInfo(entity).getId();
                if(idMob == 25){
                    if(RPGPlayer.getQuestsStage().get("4") != null && RPGPlayer.getQuestsStage().get("4") == 8){
                        e.setCancelled(true);
                        RPGPlayer.getQuestsStage().put("4", 9);
                        p.sendMessage("§7[1/1] §6" + p.getName() + "§7§o: Почему я не могу попасть? Похоже надо вернутся к Анри");
                        try {
                            questScript.ScriptsQuest(p, "4", "0", false, 0);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        return;
                    }
                }
                if (idMob == 25 || idMob == 26 || idMob == 27) {
                    if (p.getInventory().getItemInOffHand() == null || ItemStat.getID(RPGPlayer.toPlayer().getInventory().getItemInOffHand()) != 107) {
                        e.setCancelled(true);
                        p.sendMessage(WorldUtils.worldName() + " §cВы не можете атаковать призраков без амулета!");
                    }
                }


                if (cancelled) {
                    mindamage = 0;
                    maxdamage = 0;
                    minmagicdamage = 0;
                    maxmagicdamage = 0;
                    frozen = 0;
                    curse = 0;
                    stan = 0;
                    bleeding = 0;
                    IgnoringArmor = 0;
                    critchance = 0;
                    critdamage = 0;
                    vampiric = 0;
                }
                if (RPGPlayer.getActiveSpell() > 0 || spellid > 0) {
                    critchance += 100;
                    critdamage += 150;
                    RPGPlayer.setActiveSpell(0);
                    if (RPGPlayer.getActiveSpell() == 2 && spellid == 2 && Task.getTask("spellduration2-" + p.getUniqueId()) != null)
                        Task.getTask("spellduration2-" + p.getUniqueId()).cancel();
                    if (RPGPlayer.getActiveSpell() == 3 && spellid == 3 &&
                            damagepower > 1.0D)
                        damagepower *= 2.0D;
                    RPGPlayer.setActiveSpell(0);
                    RPGPlayer.setPlusMagicDamage(0);
                    if (RPGPlayer.getActiveSpell() == 10 && Task.getTask("spellduration10-" + p.getUniqueId()) != null)
                        Task.getTask("spellduration10-" + p.getUniqueId()).cancel();
                    RPGPlayer.setActiveSpell(0);
                    RPGPlayer.setPlusCritChance(0);
                    RPGPlayer.setPlusCritDamage(0);
                    RPGPlayer.setPlusVampiric(0);
                    if (RPGPlayer.getActiveSpell() == 11 && (spellid != 11 || RPGItemContainer.getRPGItemContainer(ItemStat.getID(RPGPlayer.toPlayer().getInventory().getItemInOffHand())).getSpellID() != 11) && Task.getTask("spellduration11-" + p.getUniqueId()) != null)
                        Task.getTask("spellduration11-" + p.getUniqueId()).cancel();
                }

                if (damagepower > 1.0D) {
                    mindamage = (int) (mindamage * damagepower);
                    maxdamage = (int) (maxdamage * damagepower);
                    minmagicdamage = (int) (minmagicdamage * damagepower);
                    maxmagicdamage = (int) (maxmagicdamage * damagepower);
                }
                long cd = 260L;
                if (RPGPlayer.getActiveSpell() == 8 && spellid == 8)
                    cd = 130L;
                if (time.containsKey(p.getName())) {
                    if (((Long) time.get(p.getName())).longValue() + cd > System.currentTimeMillis()) {
                        e.setCancelled(true);
                    } else {
                        time.put(p.getName(), Long.valueOf(System.currentTimeMillis()));
                    }
                } else {
                    time.put(p.getName(), Long.valueOf(System.currentTimeMillis()));
                }
                Random random = new Random();
                if (e.isCancelled())
                    return;
                e.setCancelled(true);
                final MobInfo mobInfo = MobInfoManager.getMobInfo(entity);
                damageParticle(entity.getLocation());
                int mresistance = mobInfo.getResistance();
                int mdodge = mobInfo.getDodge();
                int armor = mobInfo.getArmor() - (IgnoringArmor);
                int magicarmor = mobInfo.getMagicArmor() - (IgnoringArmor);
                int damage = ThreadLocalRandom.current().nextInt(mindamage, maxdamage + 1);
                int magicdamage = ThreadLocalRandom.current().nextInt(minmagicdamage, maxmagicdamage + 1);
                damage = (int) Math.ceil(DamageSystem.armorReducer(damage,armor));
                float pure = (damage + magicdamage);
                magicdamage = (int) Math.ceil(DamageSystem.armorReducer(magicdamage, magicarmor));
                float total = (damage + magicdamage);
                int alldamage = (damage + magicdamage);
                alldamage = (int) (alldamage - mresistance * total / pure);
                alldamage = Math.max(alldamage, 1);
                double critmodifier = (critdamage > 0) ? (2.0D + critdamage / 100.0D) : 2.0D;
                alldamage = (int) (alldamage * ((Math.random() * 100.0D <= critchance) ? critmodifier : 1.0D));
                if (mobInfo.getPercentArmor() < 1.0D)
                    alldamage = (int) (alldamage * mobInfo.getPercentArmor());
                if (mdodge > 0 &&
                        (int) (Math.random() * mdodge) + 1 == 1) {
                    alldamage = 0;
                    RPGPlayer.toPlayer().sendMessage("§7Цель уклонилась от атаки!");
                }
                double vampirich = (vampiric > 0) ? ((double) (alldamage * vampiric) / 100) : 0;
                if (RPGPlayer.getCurseFear() > 0)
                    vampirich = 0;

                if (RPGPlayer.getCurseBlood() > 0.0D) {
                    RPGDamageble.damage((Entity) p, (int) (alldamage * RPGPlayer.getCurseBlood()), null, null);
                    p.damage(0.1D);
                }
                if (RPGPlayer.getCurseRevenge() > 0.0D)
                    RPGPlayer.setCurseRevengeTime(1);
                MobSystem.damageMob((LivingEntity) entity, alldamage, (LivingEntity) p, isBow);
                if (((Creature) e.getEntity()).getTarget() == null && e.getEntity().hasMetadata("aggressive"))
                    ((Creature) e.getEntity()).setTarget((LivingEntity) p);
                if (vampirich != 0)
                    if (mobInfo.getBlackB() != 1) {
                        RPGDamageble.setHealth((Entity) p, RPGPlayer.getHealth() + vampirich, null , null);
                    }else {
                        vampirich = 0;
                    }

                ActionBar.actionbar(p, 1, "§a(§l+" + (vampirich > 0 ? Math.round(vampirich) : "") + "§a) §c❤ §c§l" + RPGPlayer.getHealth() + "§7§l/§c§l" + RPGPlayer.getMaxHealth() + " §8| §c❤ §c§l" + mobInfo.getHealth() + "§7§l/§c§l" + mobInfo.getMaxHealth() + " §c(§l-" + Math.round(alldamage) + "§c)");
                Player player1 = p;
                RPGPlayer.getBuff().put("CT", System.currentTimeMillis() + 9000L);
                player1.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK, 20, 100, true, false), true);
                long lastAttackTime = mobInfo.getLastAttackTime();
                if (lastAttackTime != 0L) {
                    if (lastAttackTime + 6000L < System.currentTimeMillis()) {
                        int thornsdamage = mobInfo.getDamage() + mobInfo.getMagicDamage() + mobInfo.getPureDamage();
                        int thornsmanadamage = mobInfo.getManaDamage() * 2;
                        mobInfo.setLastAttackTime(System.currentTimeMillis() - 5000L);
                        RPGDamageble.damage((Entity)p, thornsdamage, null, null);
                    }
                }
            }


        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }



    @EventHandler
    public void damageMob(EntityDamageEvent e) {
        if (e.getEntity().getType().equals(EntityType.PLAYER))
            return;
        if (e.getEventName().equals("EntityDamageByEntityEvent"))
            return;
        if (e.getCause().equals(EntityDamageEvent.DamageCause.CUSTOM))
            return;
        e.setCancelled(true);
    }

    @EventHandler
    public void playerByMob(EntityDamageByEntityEvent e) throws SQLException {
        if (!e.getEntity().getType().equals(EntityType.PLAYER))
            return;
        if (e.getDamager().getType().equals(EntityType.PLAYER))
            return;
        if (!((Player)e.getEntity()).isOnline())
            return;
        rpgplayer player = rpgplayer.getRPGPlayer((Player)e.getEntity());
        if (player == null)
            return;
        e.setCancelled(true);
        int plevel = player.getLevel();
        int parmor = player.getArmor();
        int pmagicarmor = player.getMagicArmor();
        int preturndamage = player.getReturnDamage();
        int pprotectbyeffect = player.getProtectbyeffect();
        int presistance = player.getResistance();
        Entity mob = null;
        if (!e.getDamager().getType().equals(EntityType.ARROW)) {
            mob = e.getDamager();
        } else {
            if (e.getDamager().hasMetadata("caster"))
                return;
            Arrow arrow = (Arrow)e.getDamager();
            mob = (Entity)arrow.getShooter();
        }
        MobInfo mobInfo = MobInfoManager.getMobInfo(mob);
        int mlevel = mobInfo.getLevel();
        int mdamage = mobInfo.getDamage();
        int mmagicdamage = mobInfo.getMagicDamage();
        int mcritchance = mobInfo.getCritChance();
        int mvampiric = mobInfo.getVampiric();
        int mmanadamage = 0;
        if (mob.hasMetadata("manadamage"))
            mmanadamage = mobInfo.getManaDamage();
        double mknockback = mobInfo.getKnockback();
        mobInfo.setLastAttackTime(System.currentTimeMillis());
        float pure = (mdamage + mmagicdamage);
        mdamage = (int) Math.ceil(DamageSystem.armorReducer(mdamage, parmor));
        mmagicdamage = (int) Math.ceil(DamageSystem.armorReducer(mmagicdamage, pmagicarmor));
        float total = (mdamage + mmagicdamage);
        mdamage = Math.max(0, mdamage);
        mmagicdamage = Math.max(0, mmagicdamage);
        int alldamage = mdamage + mmagicdamage;
        alldamage = (int)(alldamage - presistance * total / pure);
        alldamage = Math.max(alldamage, 0);
        if(mlevel - player.getLevel() >= 3){
            alldamage *= 2;
        }
        alldamage *= (Math.random() * 100.0D <= mcritchance) ? 2 : 1;
        if (player.getDodge() > 0 &&
                Math.random() * 100.0D <= player.getDodge()) {
            int dodgepower = (int)(Math.random() * player.getDodgePower()) + 1;
            alldamage = 0;
            player.toPlayer().sendMessage("Вы уклонились");
        }
        if (player.getCurseDamage() > 0.0D)
            alldamage = (int)(alldamage * player.getCurseDamage());
        alldamage = Math.max(1, alldamage);
            RPGDamageble.damage((Entity)player.toPlayer(), alldamage, null, null);
        player.toPlayer().damage(0.1D);
        if (mvampiric > 0)
            MobSystem.addHealth(mob, alldamage * mvampiric / 100);
        if (mknockback > 0.0D)
            knockback(mob, (Entity)player.toPlayer(), mknockback);

        player.getBuff().put("CT", System.currentTimeMillis() + 9000L);
        player.toPlayer().setMaximumNoDamageTicks(0);
        player.toPlayer().setNoDamageTicks(0);
        damageParticle(player.toPlayer().getLocation());
        ScriptsCheck.ScriptsOnAttack(mob, e.getEntity(), alldamage);
    }

    @EventHandler
    public void playerByAny(EntityDamageEvent e) {
        EntityDamageEvent.DamageCause cause = e.getCause();
        if (!e.getEntity().getType().equals(EntityType.PLAYER))
            return;
        if (cause == EntityDamageEvent.DamageCause.POISON) {
            e.setCancelled(true);
            return;
        }
        if (cause == EntityDamageEvent.DamageCause.ENTITY_ATTACK)
            return;
        if (cause == EntityDamageEvent.DamageCause.PROJECTILE)
            return;
        if (cause == EntityDamageEvent.DamageCause.CUSTOM)
            return;
        if (cause == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)
            return;
        if (cause == EntityDamageEvent.DamageCause.CONTACT)
            return;
        if (cause == EntityDamageEvent.DamageCause.STARVATION)
            return;
        if (cause == EntityDamageEvent.DamageCause.LIGHTNING)
            return;
        if (!((Player)e.getEntity()).isOnline())
            return;
        Player player = (Player)e.getEntity();
        player.setMaximumNoDamageTicks(0);
        player.setNoDamageTicks(0);
        rpgplayer p = rpgplayer.getRPGPlayer(player);
        if (cause == EntityDamageEvent.DamageCause.FALL || cause == EntityDamageEvent.DamageCause.FIRE || cause == EntityDamageEvent.DamageCause.FIRE_TICK || cause == EntityDamageEvent.DamageCause.HOT_FLOOR || cause == EntityDamageEvent.DamageCause.LAVA)
            RPGDamageble.damage(e.getEntity(), (int)(e.getDamage() / 20.0D * p.getMaxHealth()), null, null);
        if (cause == EntityDamageEvent.DamageCause.DROWNING)
            RPGDamageble.damage(e.getEntity(), (int)(p.getMaxHealth() * 0.3D), null, null);
        if (cause == EntityDamageEvent.DamageCause.SUFFOCATION)
            RPGDamageble.damage(e.getEntity(), p.getMaxHealth(), null, null);
        ActionBar.actionbar(player, 0, null);
        e.setDamage(0.1D);
        damageParticle(player.getLocation());
    }

    public static void damageParticle(Location loc) {
        loc.getWorld().playSound(loc, Sound.ENTITY_PLAYER_HURT, 1.0F, 1.0F);
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (rpgplayer.getRPGPlayer(player).getSettings().get("blood")) {
                Location location = loc.clone();
                rpgplayer.getRPGPlayer(player).toPlayer().playEffect(location, Effect.STEP_SOUND, 152);
                location.setY(location.getY() + 1.0D);
                rpgplayer.getRPGPlayer(player).toPlayer().playEffect(location, Effect.STEP_SOUND, 152);
            }
        }
    }

    public static void knockback(Entity damager, Entity target, double power) {
        Vector vec = target.getLocation().add(0.0D, 0.0D, 0.0D).toVector().subtract(damager.getLocation().toVector()).normalize().multiply(power);
        vec.setY(0.1D);
        target.setVelocity(vec);
    }

    public static float armorReducer(int damage, int armor) {
        return (float) (damage * 120) /((120 + armor));
    }
}
