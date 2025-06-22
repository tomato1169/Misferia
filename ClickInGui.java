package azerot.azerot;

import azerot.azerot.Quest.Quest;
import azerot.azerot.Quest.questScript;
import azerot.azerot.enchants.chance;
import azerot.azerot.events.EventClickInDB;
import azerot.azerot.events.InventoryClickEmblems;
import azerot.azerot.events.PVP_JAPAN_Event;
import azerot.azerot.mobs.MobSystem;
import com.comphenix.packetwrapper.WrapperPlayServerCustomPayload;

import io.netty.buffer.Unpooled;
import io.netty.util.internal.ThreadLocalRandom;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.EnumHand;
import net.minecraft.server.v1_12_R1.PacketDataSerializer;
import net.minecraft.server.v1_12_R1.PacketPlayOutCustomPayload;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.comphenix.protocol.utility.MinecraftReflection.getCraftBukkitClass;

public class ClickInGui implements Listener {
    HashMap<Player, Integer> lastitem = new HashMap<>();
    HashMap<Integer, Integer> auc = new HashMap<>();
    Inventory aucion = Bukkit.createInventory(null, 27, "§5Аукцион");
    int i = 1;


    public void addStoneToInventory(ItemStack clicked, int slotIndex, int itemId11, int itemId22, Player wc) {
        if (wc.getOpenInventory().getTopInventory().getItem(slotIndex) == null) {
            if (clicked.getAmount() > 1) {
                wc.getOpenInventory().getTopInventory().setItem(slotIndex, clicked);
                wc.getOpenInventory().getTopInventory().getItem(slotIndex).setAmount(1);
                clicked.setAmount(clicked.getAmount() - 1);
            } else {
                wc.getOpenInventory().getTopInventory().setItem(slotIndex, clicked);
                clicked.setAmount(0);
            }

            List<Integer> stoneTypes = new ArrayList<>();
            stoneTypes.add(ItemStat.getID(clicked));
            if (itemId11 >= 6 && itemId11 <= 9) {
                stoneTypes.add(itemId11);
            }
            if (itemId22 >= 6 && itemId22 <= 9) {
                stoneTypes.add(itemId22);
            }

            int[] usedStones = new int[stoneTypes.size()];
            for (int i = 0; i < stoneTypes.size(); i++) {
                switch (stoneTypes.get(i)) {
                    case 6:
                        usedStones[i] = 1;
                        break;
                    case 7:
                        usedStones[i] = 2;
                        break;
                    case 8:
                        usedStones[i] = 3;
                        break;
                    case 9:
                        usedStones[i] = 4;
                        break;
                }
            }

            wc.getOpenInventory().getTopInventory().setItem(9, ItemsForGui.chance(ItemStat.getEnchant(wc.getOpenInventory().getTopInventory().getItem(4)), usedStones));
            return;
        }
    }

    @EventHandler
    public void Click(InventoryClickEvent e) throws SQLException {
        Player wc = (Player)e.getWhoClicked();
        ItemStack clicked = e.getCurrentItem();
        if(!wc.getOpenInventory().getTopInventory().getName().equals("§6Мусорка") && !wc.getOpenInventory().getTopInventory().getName().equals("§6Генератор") && !wc.getOpenInventory().getTopInventory().getName().equals("§6Хранилище") && !wc.getOpenInventory().getTopInventory().getName().equals("§6Хранилище рецептов") && !wc.getOpenInventory().getTopInventory().getName().equals("Обмен") && !wc.getOpenInventory().getTopInventory().getName().equals("§6Улучшение")) {
            if (wc.getOpenInventory().getType().equals(InventoryType.CHEST)) {
                e.setCancelled(true);
             }
        }
        if(wc.getOpenInventory().getTopInventory().getName().equals("§6Улучшение")){
            e.setCancelled(true);
            if(e.getClickedInventory() == wc.getOpenInventory().getBottomInventory()){
                if(RPGItemContainer.getRPGItemContainer(ItemStat.getID(clicked)).getType() == 2 || RPGItemContainer.getRPGItemContainer(ItemStat.getID(clicked)).getType() == 3){
                    wc.getOpenInventory().getTopInventory().setItem(4, clicked);
                }

                if (ItemStat.getID(clicked) >= 6 && ItemStat.getID(clicked) <= 9) {
                    if (wc.getOpenInventory().getTopInventory().getItem(11) == null) {
                        addStoneToInventory(clicked, 11, 0, 0, wc);
                        return;
                    }

                    if (wc.getOpenInventory().getTopInventory().getItem(22) == null) {
                        addStoneToInventory(clicked, 22, ItemStat.getID(wc.getOpenInventory().getTopInventory().getItem(11)), 0, wc);
                        return;
                    }

                    if (wc.getOpenInventory().getTopInventory().getItem(15) == null) {
                        addStoneToInventory(clicked, 15, ItemStat.getID(wc.getOpenInventory().getTopInventory().getItem(11)), ItemStat.getID(wc.getOpenInventory().getTopInventory().getItem(22)), wc);
                        return;
                    }
                }
            }else if(e.getClickedInventory() == wc.getOpenInventory().getTopInventory()){
                if(e.getSlot() == 4){
                    if(WorldUtils.hasInventory(wc, 1)){
                        wc.getInventory().addItem(clicked);
                        clicked.setAmount(0);
                    }else{
                        e.setCancelled(true);
                        wc.sendMessage(WorldUtils.worldName() + " §4Недостаточно места!");
                        return;
                    }
                }
                if(e.getSlot() == 11){
                    if(WorldUtils.hasInventory(wc, 1)){
                        wc.getInventory().addItem(clicked);
                        clicked.setAmount(0);
                    }else{
                        e.setCancelled(true);
                        wc.sendMessage(WorldUtils.worldName() + " §4Недостаточно места!");
                        return;
                    }
                }
                if(e.getSlot() == 15){
                    if(WorldUtils.hasInventory(wc, 1)){
                        wc.getInventory().addItem(clicked);
                        clicked.setAmount(0);
                    }else{
                        e.setCancelled(true);
                        wc.sendMessage(WorldUtils.worldName() + " §4Недостаточно места!");
                        return;
                    }
                }
                if(e.getSlot() == 22){
                    if(WorldUtils.hasInventory(wc, 1)){
                        wc.getInventory().addItem(clicked);
                        clicked.setAmount(0);
                    }else{
                        e.setCancelled(true);
                        wc.sendMessage(WorldUtils.worldName() + " §4Недостаточно места!");
                        return;
                    }
                }
                if(e.getSlot() == 17){
                    e.setCancelled(true);
                    Random r = new Random();
                    int random = r.nextInt(100);
                    List<Integer> values = new ArrayList<>();
                    if(ItemStat.getID(wc.getOpenInventory().getTopInventory().getItem(11)) == 6){
                        values.add(1);
                    }
                    if(ItemStat.getID(wc.getOpenInventory().getTopInventory().getItem(11)) == 7){
                        values.add(2);
                    }
                    if(ItemStat.getID(wc.getOpenInventory().getTopInventory().getItem(11)) == 8){
                        values.add(3);
                    }
                    if(ItemStat.getID(wc.getOpenInventory().getTopInventory().getItem(11)) == 9){
                        values.add(4);
                    }

                    if(ItemStat.getID(wc.getOpenInventory().getTopInventory().getItem(22)) == 6){
                        values.add(1);
                    }
                    if(ItemStat.getID(wc.getOpenInventory().getTopInventory().getItem(22)) == 7){
                        values.add(2);
                    }
                    if(ItemStat.getID(wc.getOpenInventory().getTopInventory().getItem(22)) == 8){
                        values.add(3);
                    }
                    if(ItemStat.getID(wc.getOpenInventory().getTopInventory().getItem(22)) == 9){
                        values.add(4);
                    }
                    if(ItemStat.getID(wc.getOpenInventory().getTopInventory().getItem(15)) == 6){
                        values.add(1);
                    }
                    if(ItemStat.getID(wc.getOpenInventory().getTopInventory().getItem(15)) == 7){
                        values.add(2);
                    }
                    if(ItemStat.getID(wc.getOpenInventory().getTopInventory().getItem(15)) == 8){
                        values.add(3);
                    }
                    if(ItemStat.getID(wc.getOpenInventory().getTopInventory().getItem(15)) == 9){
                        values.add(4);
                    }

                    int[] usedStones = new int[values.size()];
                    for(int j = 0; j < values.size(); j++) usedStones[j] = values.get(j);
                    if(random <= chance.calculateSuccessChance(ItemStat.getEnchant(wc.getOpenInventory().getTopInventory().getItem(4)), usedStones)){
                        int clickid = ItemStat.getID(wc.getOpenInventory().getTopInventory().getItem(4));
                        int clickenchant = ItemStat.getEnchant(wc.getOpenInventory().getTopInventory().getItem(4));
                        int strength = ItemStat.getScaleAtStrength(wc.getOpenInventory().getTopInventory().getItem(4));
                        int agility = ItemStat.getScaleAtAgility(wc.getOpenInventory().getTopInventory().getItem(4));
                        int intelligence = ItemStat.getScaleAtIntelligence(wc.getOpenInventory().getTopInventory().getItem(4));
                        RPGItemContainer RPGI = RPGItemContainer.getRPGItemContainer(clickid);
                        int clickmark = ItemStat.getMark(clicked);
                        int clickmarkpower = ItemStat.getMarkpower(clicked);
                        wc.getOpenInventory().getTopInventory().setItem(4, new RPGItem().getRPGItem(clickid,clickenchant + 1,1,clickmark,clickmarkpower,strength,agility,intelligence,rpgplayer.getRPGPlayer(wc)));
                        wc.getOpenInventory().getTopInventory().getItem(11).setAmount(0);
                        if(wc.getOpenInventory().getTopInventory().getItem(15) != null){
                            wc.getOpenInventory().getTopInventory().getItem(15).setAmount(0);
                        }
                        if(wc.getOpenInventory().getTopInventory().getItem(22) != null){
                            wc.getOpenInventory().getTopInventory().getItem(22).setAmount(0);
                        }
                        wc.getOpenInventory().getTopInventory().getItem(22).setAmount(0);
                        wc.getOpenInventory().getTopInventory().getItem(9).setAmount(0);
                        wc.sendMessage(WorldUtils.worldName() + " §2Удача");
                    }else{
                        wc.getOpenInventory().getTopInventory().getItem(4).setAmount(0);
                        wc.getOpenInventory().getTopInventory().getItem(11).setAmount(0);
                        wc.getOpenInventory().getTopInventory().getItem(15).setAmount(0);
                        wc.getOpenInventory().getTopInventory().getItem(22).setAmount(0);
                        wc.getOpenInventory().getTopInventory().getItem(9).setAmount(0);
                        wc.sendMessage(WorldUtils.worldName() + " §4Неудача");
                    }

                }
            }
        }
        if(e.getClickedInventory() != null && e.getClickedInventory().equals(wc.getInventory())){
            if(e.getClickedInventory().getItem(e.getSlot()) != null)
                    Bukkit.getLogger().info(wc.getName() + " §2Кликнул на предмет " + e.getCurrentItem().getItemMeta().getDisplayName() + " x" + e.getCurrentItem().getAmount());
        }
        if(wc.getOpenInventory().getTopInventory().getName().equals("Обмен")){
            if(e.getClick().isShiftClick()){
                if (e.getCurrentItem().getType() != Material.IRON_FENCE && e.getCurrentItem().getType() != Material.REDSTONE_BLOCK && e.getCurrentItem().getType() != Material.EMERALD_BLOCK) {
                    e.setCancelled(true);
                    wc.sendMessage(WorldUtils.worldName() + " §4Вы не можете перенести предметы с зажатой Shift");
                }
                return;
            }
        }
        ItemStack cursor = e.getCursor();
        aucion.setItem(1, ItemsForGui.sellAuc());
        rpgplayer p = rpgplayer.getRPGPlayer((Player)e.getWhoClicked());
        if (wc.getVehicle() != null) {
            e.setCancelled(true);
            return;
        }
        if (e.getClickedInventory() == null)
            return;
        if (e.getView().getTopInventory().getName().equals("") || e.getView().getTopInventory().getName().equals("@")) {
            if (e.getAction().equals(InventoryAction.COLLECT_TO_CURSOR)) {
                e.setCancelled(true);
                return;
            }
            if (e.getAction().equals(InventoryAction.DROP_ALL_CURSOR)) {
                e.setCancelled(true);
                return;
            }
            if (e.getAction().equals(InventoryAction.DROP_ALL_SLOT)) {
                e.setCancelled(true);
                return;
            }
            if (e.getAction().equals(InventoryAction.DROP_ONE_CURSOR)) {
                e.setCancelled(true);
                return;
            }
            if (e.getAction().equals(InventoryAction.DROP_ONE_SLOT)) {
                e.setCancelled(true);
                return;
            }
            if (e.getClick() == ClickType.NUMBER_KEY) {
                e.setCancelled(true);
                return;
            }
        }
        if (clicked == null || clicked.getType() == Material.AIR)
            return;
        if (e.getView().getTopInventory().getName().equals("§6Хранилище рецептов") &&
                RPGItemContainer.getRPGItemContainer(ItemStat.getID(clicked)).getRecipe() == 0) {
            wc.sendMessage(WorldUtils.worldName() + " §cВы можете положить сюда только рецепт!" );
                    e.setCancelled(true);
        }
        if(e.getClickedInventory().equals(azerot.getInventorys().get("ECorRC"))){
            if (e.getSlot() == 12){
                wc.openInventory(p.getEC());
            }
            if (e.getSlot() == 14){
                wc.openInventory(p.getRC());
            }
        }

        if (e.getClickedInventory().getName().equals("§5Информация о персонаже")) {
        if (e.getClickedInventory().equals(wc.getInventory()))
            e.setCancelled(true);
        if (clicked.getItemMeta().getDisplayName().equals("§5Эмблемы"))
                wc.openInventory(GenerateInventory.Emblebs(p));
        if(clicked.getItemMeta().getDisplayName().equals("§5Магазин"))
            wc.openInventory(GenerateInventory.DonateShop(p));
        if (clicked.getItemMeta().getDisplayName().equals("§5Способности"))
                wc.openInventory(GenerateInventory.spellBind(p));
        if (clicked.getItemMeta().getDisplayName().equals("§5Настройки"))
                wc.openInventory(GenerateInventory.settings(p));
        if (clicked.getItemMeta().getDisplayName().equals("§5Открыть информацию о существах"))
                wc.openInventory(azerot.getInventorys().get("InvPatchSelection"));
        e.setCancelled(true);
    }
    if (clicked.getItemMeta().getDisplayName().equals("§fБестиарий")){
          wc.openInventory(azerot.getInventorys().get("InvBestiary"));
    }
    if(e.getClickedInventory().getName().equals("§5Бестиарий")) {
        e.setCancelled(true);
        if (clicked.getItemMeta().getDisplayName().equals("§fМетки"))
            wc.openInventory(GenerateInventory.tags(p));
        if (clicked.getItemMeta().getDisplayName().equals("§fБоссы"))
            wc.openInventory(azerot.getInventorys().get("InvBosses"));
        if (clicked.getItemMeta().getDisplayName().equals("§fМеханики"))
            wc.openInventory(azerot.getInventorys().get("InvMechanics"));
    }
    if(e.getClickedInventory().getName().equals("Обмен")){
        e.setCancelled(true);
    }

          if(e.getClickedInventory().getName().equals("§5Метки")){
              List<String> lore = e.getCurrentItem().getItemMeta().getLore();
              if (clicked.getItemMeta().getDisplayName().equals("§eЕжедневное задание")){
                  if(p.getDailies().get("beginningOfTheTask") != null){
                      if(p.getDailies().get("completeDailies") != null && p.getDailies().get("completeDailies").equals(true)){
                          if(Long.parseLong(p.getDailies().get("beginningOfTheTask").toString()) >= System.currentTimeMillis()){
                              p.getPlayer().sendMessage(WorldUtils.worldName() + " §4Следующее задание вы сможете взять " + LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()),
                                      ZoneId.of("Europe/Moscow")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                          }else {
                              p.getDailies().put("beginningOfTheTask", System.currentTimeMillis() + 86400000L);
                              p.getPlayer().sendMessage(WorldUtils.worldName() + " §2Вы взяли ежедневное задание!");
                              p.getDailies().put("boolean", true);
                              p.getDailies().put("dailiestype", ThreadLocalRandom.current().nextInt(1, 3));
                              p.getDailies().put("mobs", 0);
                              p.getDailies().put("redmobs", 0);
                              p.getDailies().put("boss", 0);
                              p.getDailies().put("completeDailies", false);
                              int type = (int) p.getDailies().get("dailiestype");
                              switch (type){
                                  case 1:
                                      wc.sendMessage(WorldUtils.worldName() + " §2Вы взяли задание на убийство 25 монстров");
                                      break;
                                  case 2:
                                      wc.sendMessage(WorldUtils.worldName() + " §2Вы взяли задание на убийство 10 Элитних монстров");
                                      break;
                                  case 3:
                                      wc.sendMessage(WorldUtils.worldName() + " §2Вы взяли задание на убийство 3 боссов");
                                      break;
                              }
                          }
                      }else {
                          if(Long.parseLong(p.getDailies().get("beginningOfTheTask").toString()) >= System.currentTimeMillis()){
                              p.getPlayer().sendMessage(WorldUtils.worldName() + " §4Вы уже взяли задание за сегодня!");
                          }else {
                              p.getDailies().put("beginningOfTheTask", System.currentTimeMillis() + 86400000L);
                              p.getPlayer().sendMessage(WorldUtils.worldName() + " §2Вы взяли ежедневное задание!");
                              p.getDailies().put("boolean", true);
                              p.getDailies().put("dailiestype", ThreadLocalRandom.current().nextInt(1, 3));
                              p.getDailies().put("mobs", 0);
                              p.getDailies().put("redmobs", 0);
                              p.getDailies().put("boss", 0);
                              p.getDailies().put("completeDailies", false);
                              int type = (int) p.getDailies().get("dailiestype");
                              switch (type){
                                  case 1:
                                      wc.sendMessage(WorldUtils.worldName() + " §2Вы взяли задание на убийство 25 монстров");
                                      break;
                                  case 2:
                                      wc.sendMessage(WorldUtils.worldName() + " §2Вы взяли задание на убийство 10 Элитних монстров");
                                      break;
                                  case 3:
                                      wc.sendMessage(WorldUtils.worldName() + " §2Вы взяли задание на убийство 3 боссов");
                                      break;
                              }
                          }
                      }

                  }

              }
          }
          if (e.getClickedInventory().getName().equals("§5Способности")) {
            e.setCancelled(true);
            if (clicked.getType().equals(Material.BOOK))
            this.lastitem.put(wc, Integer.valueOf(ItemStat.getGUIIDFromLore(clicked)));
            if (clicked.getType().equals(Material.BARRIER))
            this.lastitem.put(wc, Integer.valueOf(0));
            if (clicked.getType().equals(Material.BOOK_AND_QUILL)) {
            if (this.lastitem.get(wc) == null)
            return;
            if (((Integer)this.lastitem.get(wc)).intValue() == 0) {
            p.setSpellBind(clicked.getItemMeta().getDisplayName().replaceAll("§6", "").replaceAll("Left", "L").replaceAll("Right", "R").replaceAll("-", "").trim(), 0);
            wc.openInventory(GenerateInventory.spellBind(p));
            return;
            }
            p.setSpellBind(clicked.getItemMeta().getDisplayName().replaceAll("§6", "").replaceAll("Left", "L").replaceAll("Right", "R").replaceAll("-", "").trim(), ((Integer)this.lastitem.get(wc)).intValue());
            if(clicked.getItemMeta().getDisplayName().equals("§6F")){
                p.setSpellBind("§F", ((Integer)this.lastitem.get(wc)).intValue());

            }
            wc.openInventory(GenerateInventory.spellBind(p));
            }
            }
            if (e.getClickedInventory().getName().equals("§6Информация о зачаровании"))
            e.setCancelled(true);
            if (e.getClickedInventory().getName().equals("§5Настройки")) {
            e.setCancelled(true);
            if (clicked.getItemMeta().getDisplayName().equals("§6Эффекты крови")) {
            if (p.getSettings().get("blood")){
            p.getSettings().put("blood", false);
            } else {
                p.getSettings().put("blood", true);
            }
            wc.openInventory(GenerateInventory.settings(p));
            }

            if (clicked.getItemMeta().getDisplayName().equals("§6Обмен")) {
            if (p.getSettings().get("trade")) {
                p.getSettings().put("trade", false);
            } else {
                p.getSettings().put("trade", true);
            }
            wc.openInventory(GenerateInventory.settings(p));
            }

                if (clicked.getItemMeta().getDisplayName().equals("§6Анимация открытия сундука")) {
                    if (p.getSettings().get("openCases")) {
                        p.getSettings().put("openCases", false);
                    } else {
                        p.getSettings().put("openCases", true);
                    }
                    wc.openInventory(GenerateInventory.settings(p));
                }

                if (clicked.getItemMeta().getDisplayName().equals("§6Выпадение предметов")) {
                    if(e.getClick().isLeftClick()){
                        wc.openInventory(GenerateInventory.drop(p));
                    } else if (e.getClick().isRightClick()) {
                        wc.openInventory(GenerateInventory.filter(p));

                    }
                }
            }
            if(e.getClickedInventory().getName().equals("§5Фильтр сообщений о предметах")){
                if (clicked.getItemMeta().getDisplayName().equals("§7Посредственное качество")) {
                        if (p.getSettings().get("dropWhite")) {
                            p.getSettings().put("dropWhite", false);
                        } else {
                            p.getSettings().put("dropWhite", true);
                        }
                        wc.openInventory(GenerateInventory.filter(p));
                }
                if (clicked.getItemMeta().getDisplayName().equals("§2Качественное качество")) {
                    if (p.getSettings().get("dropGreen")) {
                        p.getSettings().put("dropGreen", false);
                    } else {
                        p.getSettings().put("dropGreen", true);
                    }
                    wc.openInventory(GenerateInventory.filter(p));
                }
                if (clicked.getItemMeta().getDisplayName().equals("§9Отменное качество")) {
                    if (p.getSettings().get("dropBlue")) {
                        p.getSettings().put("dropBlue", false);
                    } else {
                        p.getSettings().put("dropBlue", true);
                    }
                    wc.openInventory(GenerateInventory.filter(p));
                }
                if (clicked.getItemMeta().getDisplayName().equals("§5Древнее качество")) {
                    if (p.getSettings().get("dropPurple")) {
                        p.getSettings().put("dropPurple", false);
                    } else {
                        p.getSettings().put("dropPurple", true);
                    }
                    wc.openInventory(GenerateInventory.filter(p));
                }
            }

        if(e.getClickedInventory().getName().equals("§5Фильтр предметов")){
            if (clicked.getItemMeta().getDisplayName().equals("§7Посредственное качество")) {
                if (p.getSettings().get("liftingWhite")) {
                    p.getSettings().put("liftingWhite", false);
                } else {
                    p.getSettings().put("liftingWhite", true);
                }
                wc.openInventory(GenerateInventory.drop(p));
            }
            if (clicked.getItemMeta().getDisplayName().equals("§2Качественное качество")) {
                if (p.getSettings().get("liftingGreen")) {
                    p.getSettings().put("liftingGreen", false);
                } else {
                    p.getSettings().put("liftingGreen", true);
                }
                wc.openInventory(GenerateInventory.drop(p));
            }
            if (clicked.getItemMeta().getDisplayName().equals("§9Отменное качество")) {
                if (p.getSettings().get("liftingBlue")) {
                    p.getSettings().put("liftingBlue", false);
                } else {
                    p.getSettings().put("liftingBlue", true);
                }
                wc.openInventory(GenerateInventory.drop(p));
            }
            if (clicked.getItemMeta().getDisplayName().equals("§5Древнее качество")) {
                if (p.getSettings().get("liftingPurple")) {
                    p.getSettings().put("liftingPurple", false);
                } else {
                    p.getSettings().put("liftingPurple", true);
                }
                wc.openInventory(GenerateInventory.drop(p));
            }
        }


        if(e.getClickedInventory().getName().equals("§6Доступные события")){
            if(clicked.getItemMeta().getDisplayName().equals("§5Священная земля")){
                if(PVP_JAPAN_Event.getGames("1-1") == null){
                    PVP_JAPAN_Event event = new PVP_JAPAN_Event();
                    event.startGame();
                }
                wc.openInventory(GenerateInventory.Japan_PVPEvent(p));
            }

        }
        if(e.getClickedInventory().getName().equals("§6Священная земля")){
            if (clicked.getItemMeta().getDisplayName().equals("§9Синяя команда")){
                if(PVP_JAPAN_Event.getGames("1-1").getBlueTeam().get(p.getName()) == null){
                    if(PVP_JAPAN_Event.getGames("1-1").getRedTeam().get(p.getName()) != null){
                        p.toPlayer().sendMessage(WorldUtils.worldName() + " §4Вы уже состоите в красной команде!");
                    }else {
                        PVP_JAPAN_Event.getGames("1-1").getBlueTeam().put(p.getName(), 0);
                    }
                }
            }
            if (clicked.getItemMeta().getDisplayName().equals("§4Красная команда")){
                if(PVP_JAPAN_Event.getGames("1-1").getRedTeam().get(p.getName()) == null){
                    if(PVP_JAPAN_Event.getGames("1-1").getBlueTeam().get(p.getName()) != null){
                        p.toPlayer().sendMessage(WorldUtils.worldName() + " §4Вы уже состоите в красной команде!");
                    }else {
                        PVP_JAPAN_Event.getGames("1-1").getRedTeam().put(p.getName(), 0);
                    }
                }
                if(PVP_JAPAN_Event.getGames("1-1").getRedTeam().size() >= 2){
                    (new BukkitRunnable() {
                        public void run() {
                           for(String st : PVP_JAPAN_Event.getGames("1-1").getBlueTeam().keySet()){
                               Player pL = Bukkit.getPlayer(st);
                               pL.teleport(new Location(Bukkit.getWorld("pvp_event"),-114, 60, 74));
                               rpgplayer rpgplayer1 = rpgplayer.getRPGPlayer(pL);
                               rpgplayer1.setPVP_Japan_active(true);
                               rpgplayer1.setPVP_JAPAN_Event_Team("blue");
                           }
                            for(String st : PVP_JAPAN_Event.getGames("1-1").getRedTeam().keySet()){
                                Player pl = Bukkit.getPlayer(st);
                                pl.teleport(new Location(Bukkit.getWorld("pvp_event"),224, 60, 74));
                                rpgplayer rpgplayer1 = rpgplayer.getRPGPlayer(pl);
                                rpgplayer1.setPVP_Japan_active(true);
                                rpgplayer1.setPVP_JAPAN_Event_Team("red");
                            }
                        }
                    }).runTaskLater((Plugin)azerot.getInstance(), 20L);
                    final int[] i = {0};
                    final int[] j = {0};
                    BossBar bar1 = Bukkit.getServer().createBossBar("", BarColor.RED, BarStyle.SOLID);

                    (new BukkitRunnable() {
                        public void run() {
                            for(String st : PVP_JAPAN_Event.getGames("1-1").getRedTeam().keySet()){
                                Player p = Bukkit.getPlayer(st);
                                bar1.addPlayer(p);
                                (new BukkitRunnable() {
                                    public void run() {
                                        for(String st : PVP_JAPAN_Event.getGames("1-1").getRedTeam().keySet()){
                                            int m = (i[0] / 20) / 60;
                                            int s = (i[0] / 20) % 60;
                                            i[0]++;
                                            bar1.setTitle("" + (14 - m) + ":" + (s >= 50 ? "0" + (59 - s) : (59 - s)));
                                            bar1.setProgress(i[0] / 18000);
                                        }
                                    }
                                }).runTaskTimer((Plugin)azerot.getInstance(), 0L, 1L);
                            }
                        }
                    }).runTaskLater((Plugin)azerot.getInstance(), 100L);

                }
            }
        }

            if (e.getClickedInventory().getName().contains("§6Торгаш")) {
                if(e.getClickedInventory().equals(wc.getInventory())){
                    e.setCancelled(true);
                }
            e.setCancelled(true);
            if (ItemStat.getGUIID(clicked) != 0)
            wc.openInventory(GenerateInventory.shopbuy(Integer.parseInt(e.getInventory().getName().substring(e.getInventory().getName().indexOf("#") + 1)), ItemStat.getGUIID(clicked), clicked.getAmount(), p));
            }
            if (e.getClickedInventory().getName().contains("§6Меню торговли")) {
            e.setCancelled(true);
            if (clicked.getItemMeta().getDisplayName().equals("§aПодтвердить покупку")) {
                int money = 0;
                for(int i = 0; i< e.getClickedInventory().getSize(); i++){
                    if (e.getClickedInventory().getItem(i).getType() != ItemsForGui.corner().getType() && i != 16 && i != 43){
                        if(ItemStat.getID(e.getClickedInventory().getItem(i)) == 1){
                            money += e.getClickedInventory().getItem(i).getAmount();
                        } else if (ItemStat.getID(e.getClickedInventory().getItem(i)) == 2) {
                            money += e.getClickedInventory().getItem(i).getAmount() * 64;
                        }else if (ItemStat.getID(e.getClickedInventory().getItem(i)) == 3) {
                            money += e.getClickedInventory().getItem(i).getAmount() * 4096;
                        }
                        else if (ItemStat.getID(e.getClickedInventory().getItem(i)) == 4) {
                            money += e.getClickedInventory().getItem(i).getAmount() * 262144;
                        }
                        else if (ItemStat.getID(e.getClickedInventory().getItem(i)) == 5) {
                            money += e.getClickedInventory().getItem(i).getAmount() * 16777216;
                        }else if(ItemStat.getID(e.getClickedInventory().getItem(i)) > 5){
                            if (e.getClickedInventory().getItem(10).getType() != ItemsForGui.corner().getType() && e.getClickedInventory().getItem(10).getType() != Material.EMERALD &&
                                    WorldUtils.checkItems(p, ItemStat.getGUIID(e.getClickedInventory().getItem(10)), e.getClickedInventory().getItem(10).getAmount()) < e.getClickedInventory().getItem(10).getAmount())
                                return;
                            if (e.getClickedInventory().getItem(12).getType() != ItemsForGui.corner().getType() && e.getClickedInventory().getItem(12).getType() != Material.EMERALD &&
                                    WorldUtils.checkItems(p, ItemStat.getGUIID(e.getClickedInventory().getItem(12)),e.getClickedInventory().getItem(12).getAmount()) < e.getClickedInventory().getItem(12).getAmount())
                                return;
                            if (e.getClickedInventory().getItem(14).getType() != ItemsForGui.corner().getType() && e.getClickedInventory().getItem(14).getType() != Material.EMERALD &&
                                    WorldUtils.checkItems(p, ItemStat.getGUIID(e.getClickedInventory().getItem(14)),e.getClickedInventory().getItem(14).getAmount()) < e.getClickedInventory().getItem(14).getAmount())
                                return;
                            if (e.getClickedInventory().getItem(37).getType() != ItemsForGui.corner().getType() && e.getClickedInventory().getItem(37).getType() != Material.EMERALD &&
                                    WorldUtils.checkItems(p, ItemStat.getGUIID(e.getClickedInventory().getItem(37)), 37) < e.getClickedInventory().getItem(37).getAmount())
                                return;
                            if (e.getClickedInventory().getItem(39).getType() != ItemsForGui.corner().getType() && e.getClickedInventory().getItem(38).getType() != Material.EMERALD &&
                                    WorldUtils.checkItems(p, ItemStat.getGUIID(e.getClickedInventory().getItem(39)),e.getClickedInventory().getItem(39).getAmount()) < e.getClickedInventory().getItem(39).getAmount())
                                return;
                            if (e.getClickedInventory().getItem(41).getType() != ItemsForGui.corner().getType() && e.getClickedInventory().getItem(41).getType() != Material.EMERALD &&
                                    WorldUtils.checkItems(p, ItemStat.getGUIID(e.getClickedInventory().getItem(41)), e.getClickedInventory().getItem(41).getAmount()) < e.getClickedInventory().getItem(41).getAmount())
                                return;
                        }
                    }
                }
                    if (!WorldUtils.hasInventory(wc, 1)) {
                        wc.sendMessage(WorldUtils.worldName() + " §cНет места!");
                        return;
                    }
                if (WorldUtils.checkMoney(p, money) < money) {
                    p.toPlayer().sendMessage(WorldUtils.worldName() + " §cНедостаточно монет!");
                    return;
                } else {
                    WorldUtils.removeMoney(p, money);
                }
                    for (int i = 0; i < e.getClickedInventory().getSize(); i++) {
                        if (e.getClickedInventory().getItem(i).getType() != ItemsForGui.corner().getType() && i != 16 && i != 43)
                            WorldUtils.removeItems(p, ItemStat.getGUIID(e.getClickedInventory().getItem(i)), e.getClickedInventory().getItem(i).getAmount());
                    }
                    wc.getInventory().addItem((new RPGItem()).getRPGItem(ItemStat.getGUIID(e.getClickedInventory().getItem(16)), 0, e.getClickedInventory().getItem(16).getAmount(), 0, 0, 0, 0,0, rpgplayer.getRPGPlayer(wc)));
                    wc.playSound(wc.getLocation(), Sound.AMBIENT_CAVE, 1.0F, 1.0F);
                    wc.sendMessage(WorldUtils.worldName() + " §aВы успешно купили предмет!");
                }
        }
            try {
                if (e.getClickedInventory().getName().contains("§6Рецепт")) {
                    if(e.getClickedInventory().equals(wc.getInventory())){
                        e.setCancelled(true);
                    }
                    e.setCancelled(true);
                    int recipeid = Integer.parseInt(e.getClickedInventory().getName().substring(e.getClickedInventory().getName().indexOf("#") + 1));

                    if (clicked.getItemMeta().getDisplayName().equals("§aПодтвердить создание")) {
                        if (e.getClickedInventory().getItem(10).getType() != ItemsForGui.corner().getType() &&
                                WorldUtils.checkItems(p, ItemStat.getGUIID(e.getClickedInventory().getItem(10)), e.getClickedInventory().getItem(10).getAmount()) < e.getClickedInventory().getItem(10).getAmount())
                            return;
                        if (e.getClickedInventory().getItem(12).getType() != ItemsForGui.corner().getType() &&
                                WorldUtils.checkItems(p, ItemStat.getGUIID(e.getClickedInventory().getItem(12)),e.getClickedInventory().getItem(12).getAmount()) < e.getClickedInventory().getItem(12).getAmount())
                            return;
                        if (e.getClickedInventory().getItem(14).getType() != ItemsForGui.corner().getType() &&
                                WorldUtils.checkItems(p, ItemStat.getGUIID(e.getClickedInventory().getItem(14)),e.getClickedInventory().getItem(14).getAmount()) < e.getClickedInventory().getItem(14).getAmount())
                            return;
                        if (e.getClickedInventory().getItem(37).getType() != ItemsForGui.corner().getType() &&
                                WorldUtils.checkItems(p, ItemStat.getGUIID(e.getClickedInventory().getItem(37)), 37) < e.getClickedInventory().getItem(37).getAmount())
                            return;
                        if (e.getClickedInventory().getItem(39).getType() != ItemsForGui.corner().getType() &&
                                WorldUtils.checkItems(p, ItemStat.getGUIID(e.getClickedInventory().getItem(39)),e.getClickedInventory().getItem(39).getAmount()) < e.getClickedInventory().getItem(39).getAmount())
                            return;
                        if (e.getClickedInventory().getItem(41).getType() != ItemsForGui.corner().getType() &&
                                WorldUtils.checkItems(p, ItemStat.getGUIID(e.getClickedInventory().getItem(41)), e.getClickedInventory().getItem(41).getAmount()) < e.getClickedInventory().getItem(41).getAmount())
                            return;
                        wc.closeInventory();
                        if (!WorldUtils.hasInventory(wc, 1)) {
                            wc.sendMessage(WorldUtils.worldName() + " §cНет места");
                            return;
                        }
                        for (int i = 0; i < e.getClickedInventory().getSize(); i++) {
                            if (e.getClickedInventory().getItem(i).getType() != ItemsForGui.corner().getType() && i != 16 && i != 43)
                                WorldUtils.removeItems(p, ItemStat.getGUIID(e.getClickedInventory().getItem(i)), e.getClickedInventory().getItem(i).getAmount());
                        }
                        if(wc.getInventory().getItemInMainHand().getAmount() >= 1){
                            wc.getInventory().getItemInMainHand().setAmount(wc.getInventory().getItemInMainHand().getAmount() -1);

                        }
                        int markpower = (int)(Math.random() * 999.0D + 1.0D);
                        RPGItemContainer rpgItemContainer = RPGItemContainer.getRPGItemContainer(ItemStat.getGUIID(e.getClickedInventory().getItem(16)));
                        if(rpgItemContainer.getType() == 2 || rpgItemContainer.getType() == 3){
                            if(rpgItemContainer.getHasMark() == 1){
                                wc.getInventory().addItem((new RPGItem()).getRPGItem(ItemStat.getGUIID(e.getClickedInventory().getItem(16)), 0, e.getClickedInventory().getItem(16).getAmount(),  ThreadLocalRandom.current().nextInt(1, 7), markpower,0,0, 0, rpgplayer.getRPGPlayer(wc)));
                            }else{
                                wc.getInventory().addItem((new RPGItem()).getRPGItem(ItemStat.getGUIID(e.getClickedInventory().getItem(16)), 0, e.getClickedInventory().getItem(16).getAmount(),  0, markpower,0,0, 0, rpgplayer.getRPGPlayer(wc)));
                            }
                        }else{
                            wc.getInventory().addItem((new RPGItem()).getRPGItem(ItemStat.getGUIID(e.getClickedInventory().getItem(16)), 0, e.getClickedInventory().getItem(16).getAmount(),  0, 0,0,0, 0, rpgplayer.getRPGPlayer(wc)));
                        }
                        if(ItemStat.getGUIID(e.getClickedInventory().getItem(16)) == 107){
                            questScript.ScriptsQuest(wc, "9", "0", false, 0);
                        }
                        wc.playSound(wc.getLocation(), Sound.BLOCK_ANVIL_USE, 1.0F, 1.0F);
                        wc.sendMessage(WorldUtils.worldName() + " §aВы успешно создали предмет!");
                        wc.closeInventory();
                    }
                }
            }catch (Exception exception){
                exception.printStackTrace();

        }
            if(e.getClickedInventory().getName().equals("§5Аукцион")) {
                if (clicked.getItemMeta().getDisplayName().equals("§aПродать предмет")) {
                    Inventory inv = Bukkit.createInventory(null, 5 * 9, "§5Ваши предметы");
                    inv.setContents(wc.getInventory().getContents().clone());
                    wc.openInventory(inv);

                }
            }

                if (e.getClickedInventory().getName().equals("§5Ваши предметы")) {
                    e.setCancelled(true);
                    Inventory inv = Bukkit.createInventory(null, 18, "§5Продажа");
                    ItemStack item = new ItemStack(clicked);
                    int id = ItemStat.getID(clicked);
                    auc.put(0, id);
                    wc.openInventory(inv);
                    ItemStack item1 = new ItemStack(Material.NETHER_STAR);
                    ItemStack item2 = new ItemStack(Material.NETHER_STAR);
                    ItemStack item3 = new ItemStack(Material.NETHER_STAR);
                    ItemStack item4 = new ItemStack(Material.NETHER_STAR);
                    ItemMeta meta1 = item1.getItemMeta();
                    meta1.setDisplayName("§aМонетка");
                    item1.setItemMeta(meta1);
                    ItemMeta meta2 = item2.getItemMeta();
                    meta2.setDisplayName("§aМонета");
                    item2.setItemMeta(meta2);
                    ItemMeta meta3 = item3.getItemMeta();
                    meta3.setDisplayName("§aЦенная монета");
                    item3.setItemMeta(meta3);
                    ItemMeta meta4 = item4.getItemMeta();
                    meta4.setDisplayName("§aОчень ценная монета");
                    item4.setItemMeta(meta4);

                    inv.setItem(0, item1);
                    inv.setItem(1, item2);
                    inv.setItem(2, item3);
                    inv.setItem(3, item4);
                    inv.setItem(17, clicked);
                    e.setCancelled(true);


                }
                if (e.getClickedInventory().getName().equals("§5Продажа")) {
                    e.setCancelled(true);
                    List<String> lore = new ArrayList<>();
                    Inventory inv = Bukkit.createInventory(null, 9, "§5Выбор цены");
                    ItemStack item1 = new ItemStack(Material.NETHER_STAR);
                    if (e.getSlot() == 17) {
                        return;
                    }
                    if (clicked.getItemMeta().getDisplayName().equals("§aМонетка")) {
                        auc.put(1, 1);

                    }
                    if (clicked.getItemMeta().getDisplayName().equals("§aМонета")) {
                        auc.put(1, 2);

                    }
                    if (clicked.getItemMeta().getDisplayName().equals("§aЦенная монета")) {
                        auc.put(1, 3);

                    }
                    if (clicked.getItemMeta().getDisplayName().equals("§aОчень ценная монета")) {
                        auc.put(1, 4);

                    }
                    ItemMeta meta1 = item1.getItemMeta();
                    meta1.setDisplayName("§aДобавить валюты");
                    lore.add("§aСделать +1, нажмите правым кликом");
                    lore.add("§aСделать +4, нажмите левым кликом");
                    lore.add("§aСделать +16, нажмите шифтом и правым кликом кликом");
                    ItemStack item2 = new ItemStack(Material.PAPER);
                    ItemMeta meta2 = item2.getItemMeta();
                    meta2.setDisplayName("§2Выставить на аукцион");
                    item2.setItemMeta(meta2);

                    meta1.setLore(lore);
                    item1.setItemMeta(meta1);

                    inv.setItem(0, item1);
                    inv.setItem(8, item2);

                    wc.openInventory(inv);


                }
                if (e.getClickedInventory().getName().equals("§5Выбор цены")) {
                    e.setCancelled(true);
                    int id = 0;
                    if (clicked.getItemMeta().getDisplayName().equals("§aДобавить валюты")) {
                        if (e.getClick().isLeftClick()) {
                            wc.sendMessage("§a+4");
                            id += 4;
                            wc.sendMessage(id + "");


                        }
                        if (e.getClick().isRightClick()) {
                            wc.sendMessage("§a+1");
                            wc.sendMessage(id + "");
                            id += 1;
                        }
                        if (e.getClick().isKeyboardClick()) {
                            wc.sendMessage("§a+16");
                            id += 16;
                            auc.put(2, 4);


                        }
                    }
                    if (clicked.getItemMeta().getDisplayName().equals("§2Выставить на аукцион")) {
                        wc.sendMessage(auc + "");
                        ItemStack item = (new RPGItem()).getRPGItem(auc.get(0), 0, 1,  0, 0, 0,0,0,null);
                        aucion.setItem(12, item );
                        wc.openInventory(aucion);

                    }


                }
        if(e.getClickedInventory().getName().equals("§6Генератор")){
            if (clicked == null || clicked.getItemMeta() == null || clicked.getType() == Material.AIR)
                return;

                    if (clicked.getItemMeta().getDisplayName().equals("§aПодтвердить продажу")) {
                        ItemStack item1 = e.getClickedInventory().getItem(1);
                        ItemStack item2 = e.getClickedInventory().getItem(2);
                        ItemStack item3 = e.getClickedInventory().getItem(3);
                        ItemStack item = e.getClickedInventory().getItem(0);
                        int id = ItemStat.getID(item);
                        if (e.getClickedInventory().getItem(0) == null || e.getClickedInventory().getItem(1) == null || e.getClickedInventory().getItem(2) == null || e.getClickedInventory().getItem(3) == null) {
                            wc.sendMessage(WorldUtils.worldName() + " §4Недостаточно предметов!");
                            e.setCancelled(true);
                            return;
                        } else {
                            if(ItemStat.getID(item1) == id && ItemStat.getID(item2) == id && ItemStat.getID(item3) == id){
                                int cl = ItemStat.getMarkpower(item);
                                cl += ItemStat.getMarkpower(item1);
                                cl += ItemStat.getMarkpower(item2);
                                cl += ItemStat.getMarkpower(item3);
                                int cl1 = cl / 4;
                                Random r = new Random();
                                int result = r.nextInt(cl1);
                                int clickmark = ItemStat.getMark(item1);
                                Bukkit.broadcastMessage("" + cl1 + " /" + result);
                                wc.getInventory().addItem((new RPGItem()).getRPGItem(id, 0, 1, clickmark, result, 0, 0,0, rpgplayer.getRPGPlayer(wc)));

                                e.getClickedInventory().clear();
                                wc.closeInventory();

                            }else{
                                wc.sendMessage(WorldUtils.worldName()+ " Предметы должны быть одинаковые!");
                            }

                        }
                    }


        }

     if (e.getClickedInventory().getName().equals("§6Мусорка")) {
                if (clicked == null || clicked.getItemMeta() == null || clicked.getType() == Material.AIR)
                    return;
                if (clicked.getItemMeta().getDisplayName().equals("§aПодтвердить продажу")) {
                    e.setCancelled(true);
                    int reward = 0;
                    boolean hasItem = false;
                    int i = 0;


                    for (i = 0; i < e.getClickedInventory().getSize(); i++) {


                        if(e.getClickedInventory().getItem(i) != null && !e.getClickedInventory().getItem(i).equals(ItemsForGui.sellcomplete())){
                            int id = ItemStat.getID(e.getClickedInventory().getItem(i));
                            RPGItemContainer RPGI = RPGItemContainer.getRPGItemContainer(id);
                            if(RPGI.getItemPrice() * e.getClickedInventory().getItem(i).getAmount() == 0 ) {
                                wc.sendMessage(WorldUtils.worldName() + " §cВы не можете продать эти предметы!");
                                return;
                            }
                            if(RPGI.getItemPrice() * e.getClickedInventory().getItem(i).getAmount() > 0){
                                reward += RPGI.getItemPrice() * e.getClickedInventory().getItem(i).getAmount();
                                reward += (int) Math.max(0.0D, RPGI.getItemPrice() * 0.5D * ItemStat.getEnchant(e.getClickedInventory().getItem(i)));
                                hasItem = true;
                            }

                        }
                    }
                    if (!hasItem || reward < 1) {

                        wc.sendMessage(WorldUtils.worldName() + " §cНедостаточно предметов для продажи!");
                        return;
                    } else {
                        p.setMoney(p.getMoney() + reward);
                        wc.playSound(wc.getLocation(), Sound.AMBIENT_CAVE, 1.0F, 1.0F);
                        wc.sendMessage(WorldUtils.worldName() + " §aВы продали предметы за §6" + reward + " §aмонет!");
                        e.getClickedInventory().clear();
                        wc.closeInventory();
                    }
                }
            }

        if (e.getClickedInventory().getName().equals("§6Доступные поручения"))
        e.setCancelled(true);
        }

    public HashMap<Integer, Integer> getAuc() {
        return auc;
    }

    public void setAucion(Inventory aucion) {
        this.aucion = aucion;
    }

    public Inventory getAucion() {
        return aucion;
    }
}
