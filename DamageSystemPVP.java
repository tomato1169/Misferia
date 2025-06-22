package azerot.azerot;

import java.util.HashMap;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DamageSystemPVP implements Listener {
    static HashMap<String, Long> time = new HashMap<>();

    @EventHandler
    public void playerByPlayer(EntityDamageByEntityEvent e) {
        if (e.getEntity() == null)
            return;
        if (!e.getEntity().getType().equals(EntityType.PLAYER))
            return;
        if (!e.getDamager().getType().equals(EntityType.PLAYER) && !e.getDamager().getType().equals(EntityType.ARROW))
            return;
        if (e.getDamager().getType().equals(EntityType.ARROW) && !e.getDamager().hasMetadata("caster"))
            return;
        rpgplayer RPGDamager = null;
        rpgplayer player = rpgplayer.getRPGPlayer((Player)e.getEntity());
        double damagepower = 1.0D;
        ItemStack item = null;
        if (player == null)
            return;
        int plevel = player.getPLevel();
        int parmor = player.getArmor();
        int pmagicarmor = player.getMagicArmor();
        int presistance = player.getResistance();
        Player damager = null;
        if (!e.getDamager().getType().equals(EntityType.ARROW)) {
            RPGDamager = rpgplayer.getRPGPlayer((Player)e.getDamager());
            damager = (Player)e.getDamager();
            item = damager.getInventory().getItemInMainHand();
        } else {
            double length = ((MetadataValue)e.getDamager().getMetadata("length").get(0)).asDouble();
            damagepower = Math.max(1.0D, length / 1.5D);
            RPGDamager = rpgplayer.getRPGPlayer((Player)((MetadataValue)e.getDamager().getMetadata("caster").get(0)).value());
            damager = (Player)((MetadataValue)e.getDamager().getMetadata("caster").get(0)).value();
            item = (ItemStack)((MetadataValue)e.getDamager().getMetadata("item").get(0)).value();
        }
        Handler.unmount(damager);
        if (player.getSaveZone() == 1 || RPGDamager.getSaveZone() == 1)
            return;
        if (e.getEntity().equals(damager))
            return;
        if (RPGDamager.getPLevel() < 15) {
            damager.sendMessage(WorldUtils.worldName() + " Вы не можете атаковать игроков до 15 уровня!" );
            return;
        }
        if (player.getPLevel() < 15) {
            damager.sendMessage(WorldUtils.worldName() + " Вы не можете атаковать игроков, чей уровень ниже 15!");
            return;
        }
        if (Math.abs(RPGDamager.getPLevel() - player.getPLevel()) > 3) {
            damager.sendMessage(WorldUtils.worldName() + " Вы не можете атаковать игроков, чей уровень отличается от вашего более, чем на  3!");
            return;
        }
        if(RPGDamager.getPlayersForparty() != null){
            if(RPGDamager.getPlayersForparty().get(player.getName()) != null){
                Bukkit.broadcastMessage("Группа атакуемого: " + player.getPlayersForparty().toString());
                Bukkit.broadcastMessage("Группа атакующего: " + RPGDamager.getPlayersForparty().toString());
                return;
            }
        }
        int dlevel = RPGDamager.getPLevel();
        int id = ItemStat.getID(item);
        int mark = ItemStat.getMark(item);
        int spellid = ItemStat.getSpellID(id);
        int enchant = ItemStat.getEnchant(item);
        int markpower = ItemStat.getMarkpower(item);
        RPGItemContainer RPGI = RPGItemContainer.getRPGItemContainer(id);
        int mindamage = RPGI.getDamage(mark,markpower, enchant);
        int maxdamage = RPGI.getMaxDamage(mark,markpower, enchant) ;
        int minmagicdamage = RPGI.getMagicDamage(mark,markpower, enchant);
        int maxmagicdamage = RPGI.getMaxMagicDamage(mark,markpower, enchant);
        int critchance = RPGI.getCritChance(mark,markpower, enchant);
        critchance += RPGDamager.getCritChance();
        int critdamage = RPGI.getCritDamage(mark,markpower, enchant);
        critdamage += RPGDamager.getCritDamage();
        double vampiric = RPGI.getVampiric(mark,markpower, enchant);
        vampiric += RPGDamager.getVampiric();
        boolean cancelled = false;
        if (RPGI.getType() != 2 || mark == 100 || (e.getDamager().getType().equals(EntityType.PLAYER) && damager.getInventory().getItemInMainHand().getType().equals(Material.BOW)))
            cancelled = true;
        if (!ItemStat.canuseitem(RPGDamager, item)) {
            cancelled = true;
            damager.sendMessage(WorldUtils.worldName() + " §cВы не можете использовать этот предмет!");
        }
        if (cancelled) {
            mindamage = 0;
            maxdamage = 0;
            minmagicdamage = 0;
            maxmagicdamage = 0;
            critchance = 0;
            critdamage = 0;
            vampiric = 0;
        }
        if (RPGDamager.getActiveSpell() > 0 || spellid > 0) {
            critchance += 100;
            critdamage += 150;
            RPGDamager.setActiveSpell(0);
            if (RPGDamager.getActiveSpell() == 2 && spellid == 2 && Task.getTask("spellduration2-" + damager.getUniqueId()) != null)
                Task.getTask("spellduration2-" + damager.getUniqueId()).cancel();
            if (RPGDamager.getActiveSpell() == 3 && spellid == 3 &&
                    damagepower > 1.0D)
                damagepower *= 2.0D;
            critchance = 0;
            RPGDamager.setActiveSpell(0);
            RPGDamager.setPlusMagicDamage(0);
            if (RPGDamager.getActiveSpell() == 10 && Task.getTask("spellduration10-" + damager.getUniqueId()) != null)
                Task.getTask("spellduration10-" + damager.getUniqueId()).cancel();
            RPGDamager.setActiveSpell(0);
            RPGDamager.setPlusCritChance(0);
            RPGDamager.setPlusCritDamage(0);
            RPGDamager.setPlusVampiric(0);
            if (RPGDamager.getActiveSpell() == 11 && (spellid != 11 || RPGItemContainer.getRPGItemContainer(ItemStat.getID(RPGDamager.toPlayer().getInventory().getItemInOffHand())).getSpellID() != 11) && Task.getTask("spellduration11-" + damager.getUniqueId()) != null)
                Task.getTask("spellduration11-" + damager.getUniqueId()).cancel();
        }
        if (damagepower > 1.0D) {
            damagepower = Math.max(1.0D, damagepower / 2.0D);
            mindamage = (int)(mindamage * damagepower);
            maxdamage = (int)(maxdamage * damagepower);
            minmagicdamage = (int)(minmagicdamage * damagepower);
            maxmagicdamage = (int)(maxmagicdamage * damagepower);
        }
        long cd = 260L;
        if (RPGDamager.getActiveSpell() == 8 && spellid == 8)
            cd = 130L;
        if (time.containsKey(damager.getName())) {
            if (((Long)time.get(damager.getName())).longValue() + cd > System.currentTimeMillis()) {
                e.setCancelled(true);
                return;
            }
            time.put(damager.getName(), Long.valueOf(System.currentTimeMillis()));
        } else {
            time.put(damager.getName(), Long.valueOf(System.currentTimeMillis()));
        }
        int damage = ThreadLocalRandom.current().nextInt(mindamage, maxdamage + 1);
        int magicdamage = ThreadLocalRandom.current().nextInt(minmagicdamage, maxmagicdamage + 1);
        double pure = (damage + magicdamage );
        damage = (int) Math.ceil(DamageSystem.armorReducer(damage, parmor));
        if(damage < 0)
            damage = 0;

        magicdamage = (int) Math.ceil(DamageSystem.armorReducer(magicdamage, pmagicarmor));
        double total = (damage + magicdamage );
        damage = Math.max(1, damage);
        magicdamage = Math.max(0, magicdamage);
        double alldamage = damage + magicdamage;
        alldamage = (int)((alldamage - presistance) * total / pure);
        alldamage = Math.max(1, alldamage);
        double critmodifier = (critdamage > 0) ? (2.0D + critdamage / 100.0D) : 2.0D;
        alldamage = (int)(alldamage * ((Math.random() * 100.0D <= critchance) ? critmodifier : 1.0D));
        if (player.getDodge() > 0 &&
                Math.random() * 100.0D <= player.getDodge()) {
            int dodgepower = (int)(Math.random() * player.getDodgePower()) + 1;
            alldamage = (int)(alldamage * (1.0D - dodgepower / 100.0D));
            player.toPlayer().sendMessage(""+ dodgepower + "%");
            damager.sendMessage("");
        }
        if (!damager.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
            alldamage = (int)(alldamage * 1.5D);
        vampiric = (vampiric > 0) ? (alldamage * vampiric / 100) : 0;
        if (vampiric != 0)
            RPGDamageble.setHealth((Entity)damager, RPGDamager.getHealth() + vampiric, null, null);
        Player player1 = damager;
        player1.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 200, 100, true, false), true);
        player1.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK, 20, 100, true, false), true);
        LivingEntity lee = (LivingEntity)e.getEntity();
        lee.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 200, 100, true, false), true);
        player.toPlayer().setMaximumNoDamageTicks(0);
        player.toPlayer().setNoDamageTicks(0);
        player.toPlayer().damage(0.1D);
        RPGDamageble.damage((Entity)player.toPlayer(), alldamage, "player", damager.getName());
            ActionBar.actionbar(damager, 1, "§a(§l+" + vampiric + "§a) §c❤ §c§l" + RPGDamager.getHealth() + "§7§l/§cl"+ RPGDamager.getMaxHealth() + "§8| §c❤ §c§l" + player.getHealth() + "§7§l/§c§l" + player.getMaxHealth() + " §c(§l-" + alldamage + "§c)");

    }

}
