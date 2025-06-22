package azerot.azerot;

import java.sql.SQLException;
import java.util.Random;

import io.netty.util.internal.ThreadLocalRandom;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class Enchant implements Listener {
    private static final Random r = new Random();

    @EventHandler
    public void enchant(InventoryClickEvent e) throws SQLException {
        Player p = (Player)e.getWhoClicked();
        ItemStack clicked = e.getCurrentItem();
        ItemStack cursor = e.getCursor();
        boolean allowenchant = false;
        boolean isBlessed = false;
        boolean cantBreak = false;
        int[] chance = new int[10];
        int itemchance = 0;
        if (clicked == null || clicked.getType() == Material.AIR)
            return;
        if (!e.getClickedInventory().getName().equals("container.inventory"))
            return;
        RPGItem RPGItem = new RPGItem();
        int clickid = ItemStat.getID(clicked);
        int clickenchant = ItemStat.getEnchant(clicked);
        RPGItemContainer RPGI = RPGItemContainer.getRPGItemContainer(clickid);
        int clickquality = RPGI.getQuality();
        int clicklevel = RPGI.getLevel();
        int clickmark = ItemStat.getMark(clicked);
        int cursorid = ItemStat.getID(cursor);
        rpgplayer rpgplayer = new rpgplayer(p);
        if (ItemStat.getType(clicked) != 2 && ItemStat.getType(clicked) != 3) {
            allowenchant = false;
            return;
        }
        if (ItemStat.getMark(clicked) == 100) {
            allowenchant = false;
            return;
        }
        if (cursorid == 6 &&
                clicklevel >= 0 && clicklevel < 20) {
            allowenchant = true;
            isBlessed = false;
        }
        if (cursorid == 7 &&
                clicklevel >= 0 && clicklevel < 20) {
            allowenchant = true;
            isBlessed = true;
        }
        if (cursorid == 8 &&
                clicklevel >= 20 && clicklevel < 30) {
            allowenchant = true;
            isBlessed = false;
        }
        if (cursorid == 9 &&
                clicklevel >= 20 && clicklevel < 50) {
            allowenchant = true;
            isBlessed = true;
        }
        if (cursorid == 10 &&
                clicklevel >= 30 && clicklevel < 40) {
            allowenchant = true;
            isBlessed = false;
        }
        if (cursorid == 11 &&
                clicklevel >= 30 && clicklevel < 50) {
            allowenchant = true;
            isBlessed = true;
        }
        if (cursorid == 157 &&
                clicklevel >= 40 && clicklevel < 60) {
            allowenchant = true;
            isBlessed = false;
        }
        if (cursorid == 158 &&
                clicklevel >= 40 && clicklevel < 60) {
            allowenchant = true;
            isBlessed = true;
        }
        if (cursorid == 159 &&
                clicklevel >= 60 && clicklevel < 70) {
            allowenchant = true;
            isBlessed = false;
        }
        if (cursorid == 160 &&
                clicklevel >= 60 && clicklevel < 70) {
            allowenchant = true;
            isBlessed = true;
        }
        if (cursorid == 165) {
            allowenchant = true;
            cantBreak = true;
            if (clickenchant >= 4)
                allowenchant = false;
        }
        if (clickenchant >= 8)
            isBlessed = false;
        if (clickquality == 1)
            itemchance = 1;
        if (clickquality == 2)
            itemchance = 2;
        if (clickquality == 3)
            itemchance = 3;
        if (clickquality == 4)
            itemchance = 4;
        if (clickquality == 5)
            itemchance = 5;
        if(clickquality == 6)
            itemchance = 5;
        if(clickquality == 7)
            itemchance = 5;
        chance[0] = 7 + itemchance / 2;
        chance[1] = 10 + itemchance;
        chance[2] = 15 + itemchance;
        chance[3] = 15 + itemchance * 2;
        chance[4] = 23 + (int)Math.round(itemchance * 2.5D);
        chance[5] = 40 + itemchance * 3;
        chance[6] = 55 + itemchance * 4;
        chance[7] = 70 + itemchance * 4;
        chance[8] = 72 + itemchance * 4;
        chance[9] = 75 + itemchance * 4;
        if (clickenchant == 0 && isBlessed == true)
            chance[0] = 0;
        if (cursor.getType() == Material.NETHER_STAR)
            if (allowenchant == true) {
                if (clickenchant < 10) {
                    int random = r.nextInt(100);
                    if (cantBreak)
                        random = 100;

                        if (isBlessed) {
                            p.sendMessage(WorldUtils.worldName() + " §cУровень зачарования понижен!" );
                        }
                        Bukkit.getLogger().info(p.getName() + ": сточил " + clickid + "-" + clickenchant + "-" + clickmark);
                    }
                    e.setCancelled(true);
                    cursor.setAmount(cursor.getAmount() - 1);
                } else {
                    p.sendMessage(WorldUtils.worldName() + " §cМаксимальный уровень зачарования!" );
                            e.setCancelled(true);
                }

    }


}
