package azerot.azerot;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;

public class SpellUse implements Listener {
    @EventHandler
    public void swap(PlayerSwapHandItemsEvent e) throws SQLException {
        int type = ItemStat.getType(e.getPlayer().getInventory().getItemInMainHand());
        e.setCancelled(true);
        rpgplayer p = rpgplayer.getRPGPlayer(e.getPlayer());
        if (rpgplayer.getRPGPlayer(e.getPlayer()).getCurseSilence() >= 1)
            return;
        RPGItemContainer RPGI = RPGItemContainer.getRPGItemContainer(ItemStat.getID(e.getPlayer().getInventory().getItemInMainHand()));
        if (ItemStat.canuseitem(rpgplayer.getRPGPlayer(e.getPlayer()), e.getPlayer().getInventory().getItemInMainHand()) && RPGI.getSpellID() != 0)
             SpellScript.SpellScript(e.getPlayer(), e.getPlayer().getInventory().getItemInMainHand(), RPGI.getSpellID(), 2);
        if(p.getSpellF() > 0  & type == 2){
                        SpellScript.SpellScript(e.getPlayer(), e.getPlayer().getInventory().getItemInMainHand(), p.getSpellF(), 1);
                        ActionBar.actionbar(p.toPlayer(), 1, "F");
        }
    }

    @EventHandler
    public void spellinteract(PlayerInteractEvent e) {
        if (rpgplayer.getRPGPlayer(e.getPlayer()).getCurseSilence() >= 1)
            return;
        if ((e.getItem() != null && e.getItem().equals(e.getPlayer().getInventory().getItemInOffHand())) || e.getAction() == Action.PHYSICAL || e.getItem() == null)
            return;
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock().getType().equals(Material.ENDER_CHEST))
            return;
        Player player = e.getPlayer();
        rpgplayer p = rpgplayer.getRPGPlayer(player);
        ItemStack hand = e.getPlayer().getInventory().getItemInMainHand();
        ItemStack offhand = e.getPlayer().getInventory().getItemInOffHand();
        if (hand == null || hand.getType() == Material.AIR || hand.getItemMeta().getDisplayName() == null || ItemStat.getType(hand) != 2 || hand.getType() == Material.BOW)
            return;
        if (p.getStreak()[2] == null) {
            if ((e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) &&
                    p.getStreak()[2] == null)
                spellstreak((Entity)player, "§6§lRight");
            if (p.getStreak()[0] != null && (
                    e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR) &&
                    p.getStreak()[2] == null)
                spellstreak((Entity)player, "§6§lLeft");
            if (p.getSpellstring() != "") {
                Player player1 = player;
                player1.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK, 30, 100, true, false), true);
                ActionBar.actionbar(player, 1, p.getSpellstring());
            }
        } else {
            spellstreak((Entity)player, "null");
        }
    }

    private static void spellstreak(final Entity caster, String type) {
        final rpgplayer p = rpgplayer.getRPGPlayer((Player)caster);
        if (type == "null")
            return;
        if (p.getStreak()[0] == null)
            (new BukkitRunnable() {
                public void run() {
                    try {
                        SpellScript.SpellScript((Player)caster, ((Player)caster).getInventory().getItemInMainHand(), p.getSpellBind(p.getSpellstring().replaceAll("§6§l", "").replaceAll("Left", "L").replaceAll("Right", "R").replaceAll(" - ", "").trim()), 1);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    p.setStreak(0, null);
                    p.setStreak(1, null);
                    p.setStreak(2, null);
                    p.setSpellstring("");
                    cancel();
                }
            }).runTaskTimer((Plugin)azerot.getPlugin(azerot.class), 15L, 0L);
        if (p.getStreak()[0] == null && p.getStreak()[2] == null) {
            p.setStreak(0, type);
            p.setSpellstring("§6§lRight");
            return;
        }
        if (p.getStreak()[1] == null) {
            p.setStreak(1, type);
            p.setSpellstring(p.getSpellstring() + " - " + p.getStreak()[1]);
            return;
        }
        if (p.getStreak()[1] != null && p.getStreak()[2] == null) {
            p.setStreak(2, type);
            p.setSpellstring(p.getSpellstring() + " - " + p.getStreak()[2]);
            return;
        }
    }
}
