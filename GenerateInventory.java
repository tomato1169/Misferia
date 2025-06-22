package azerot.azerot;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import azerot.azerot.mobs.MobSystem;
import azerot.azerot.mobs.RPGMobsContainer;

import azerot.azerot.Quest.Quest;
import azerot.azerot.questsStageInfo.do30lvl;
import io.netty.util.internal.ThreadLocalRandom;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.ShulkerBox;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class GenerateInventory {
    static HashMap<rpgplayer, Inventory> lastInv = new HashMap<>();

    public static Inventory playerInfo(rpgplayer p) {
        Inventory inv = Bukkit.createInventory(null, 27, "§5Информация о персонаже");
        inv.setItem(0, ItemsForGui.emblem(p));
        inv.setItem(13, ItemsForGui.BlockCharacterInfo(p));
        inv.setItem(22, ItemsForGui.OpenSpellBinding(p));
        inv.setItem(17, ItemsForGui.settings());
        inv.setItem(18, ItemsForGui.Donat());
        inv.setItem(inv.getSize() - 1, ItemsForGui.OpenDataBase());
        for (int j = 0; j < inv.getSize(); j++) {
            if (inv.getItem(j) == null)
                inv.setItem(j, ItemsForGui.corner());
        }
        return inv;
    }

    public static Inventory EventsInv(rpgplayer p) {
        Inventory inv = Bukkit.createInventory(null, 9, "§6Доступные события");
        inv.setItem(5, ItemsForGui.Japan_PVPEvent());
        for (int j = 0; j < inv.getSize(); j++) {
            if (inv.getItem(j) == null)
                inv.setItem(j, ItemsForGui.corner());
        }
        return inv;
    }

    public static Inventory Japan_PVPEvent(rpgplayer p) {
        Inventory inv = Bukkit.createInventory(null, 9, "§6Священная земля");
        inv.setItem(4, ItemsForGui.Japan_BLUETeam());
        inv.setItem(6, ItemsForGui.Japan_REDTeam());
        for (int j = 0; j < inv.getSize(); j++) {
            if (inv.getItem(j) == null)
                inv.setItem(j, ItemsForGui.corner());
        }
        return inv;
    }
    

    public static Inventory Emblebs(rpgplayer p ){
        Inventory inv = Bukkit.createInventory(null, 54, "§5Эмблемы: " + p.getSkillpoint() + " очков!");
        ItemMeta im = null;
        for (int j = 9; j < 18; j++)
            inv.setItem(j, ItemsForGui.corner());
        if(p.getTalents().get("SecondChance") != null){
            inv.setItem(0, ItemsForGui.SecondChance(p));
        }
        if(p.getTalents().get("SunAndMoon") != null) {
            inv.setItem(1, ItemsForGui.SunAndMoon(p));
        }
        if(p.getTalents().get("Survivalinstinct") != null){
            inv.setItem(2, ItemsForGui.survivalinstinct(p));
        }
        if(p.getTalents().get("Magicalfoundation") != null){
            inv.setItem(3, ItemsForGui.magicalfoundation(p));
        }
        if(p.getTalents().get("Precisestrikes") != null){
            inv.setItem(4, ItemsForGui.precisestrikes(p));
        }

        int slot = 0;
        for (Iterator<Integer> iterator = p.getLearnedSpells().iterator(); iterator.hasNext(); ) {
            int i = ((Integer)iterator.next()).intValue();
            slot++;
        }
        return inv;
    }

    public static Inventory settings(rpgplayer p) {
        Inventory inv = Bukkit.createInventory(null, 9, "§5Настройки");
        inv.setItem(0, ItemsForGui.settingsblood(p));
        inv.setItem(1, ItemsForGui.settingstrade(p));
        inv.setItem(2, ItemsForGui.settingdrop(p));
        inv.setItem(4, ItemsForGui.openEmblems(p));
        for (int j = 0; j < inv.getSize(); j++) {
            if (inv.getItem(j) == null)
                inv.setItem(j, ItemsForGui.corner());
        }
        return inv;
    }

    public static Inventory DonateShop(rpgplayer p) {
        Inventory inv = Bukkit.createInventory(null, 54, "§5Магазин: " + p.getGold());
        inv.setItem(8, ItemsForGui.permessionOne());
        inv.setItem(17, ItemsForGui.permessionTwo());
        inv.setItem(26, ItemsForGui.permessionThree());
        inv.setItem(35, ItemsForGui.permessionFour());
        inv.setItem(44, ItemsForGui.permessionFive());
        inv.setItem(52, ItemsForGui.permessionSix());
        inv.setItem(53, ItemsForGui.permessionSeven());
        inv.setItem(1, ItemsForGui.RC());
        inv.setItem(3, ItemsForGui.caseOFEmblems());
        inv.setItem(10, ItemsForGui.additionalXP());
        inv.setItem(12, ItemsForGui.additionalDrop());
        for (int j = 0; j < inv.getSize(); j++) {
            if (inv.getItem(j) == null)
                inv.setItem(j, ItemsForGui.corner());
        }
        return inv;
    }
    public static Inventory filter(rpgplayer p) {
        Inventory inv = Bukkit.createInventory(null, 9, "§5Фильтр сообщений о предметах");
        inv.setItem(0, ItemsForGui.filterwhite(p));
        inv.setItem(1, ItemsForGui.filterGreen(p));
        inv.setItem(2, ItemsForGui.filterBlue(p));
        inv.setItem(3, ItemsForGui.filterPurple(p));
        for (int j = 0; j < inv.getSize(); j++) {
            if (inv.getItem(j) == null)
                inv.setItem(j, ItemsForGui.corner());
        }
        return inv;
    }

    public static Inventory drop(rpgplayer p) {
        Inventory inv = Bukkit.createInventory(null, 9, "§5Фильтр предметов");
        inv.setItem(0, ItemsForGui.whiteitem(p));
        inv.setItem(1, ItemsForGui.GreenItem(p));
        inv.setItem(2, ItemsForGui.BlueItem(p));
        inv.setItem(3, ItemsForGui.PurpleItem(p));
        for (int j = 0; j < inv.getSize(); j++) {
            if (inv.getItem(j) == null)
                inv.setItem(j, ItemsForGui.corner());
        }
        return inv;
    }


    public static Inventory tags(rpgplayer p) {
        Inventory inv = Bukkit.createInventory(null, 9, "§5Метки");
        inv.setItem(0, ItemsForGui.tags1());
            inv.setItem(1, ItemsForGui.Dailies(p));
        for (int j = 0; j < inv.getSize(); j++) {
            if (inv.getItem(j) == null)
                inv.setItem(j, ItemsForGui.corner());
        }
        return inv;
    }


    public static Inventory spellBind(rpgplayer p) {
        Inventory inv = Bukkit.createInventory(null, 45, "§5Способности");
                ItemMeta im = null;
        for (int j = 9; j < 18; j++)
            inv.setItem(j, ItemsForGui.corner());
        inv.setItem(0, ItemsForGui.BindBook("R", p));
        inv.setItem(1, ItemsForGui.BindBook("RR", p));
        inv.setItem(2, ItemsForGui.BindBook("RL", p));
        inv.setItem(3, ItemsForGui.BindBook("RRR", p));
        inv.setItem(4, ItemsForGui.BindBook("RRL", p));
        inv.setItem(5, ItemsForGui.BindBook("RLR", p));
        inv.setItem(6, ItemsForGui.BindBook("RLL", p));
        inv.setItem(7, ItemsForGui.BindBook("F", p));
        inv.setItem(18, ItemsForGui.SpellClear());
        int slot = 0;
        for (Iterator<Integer> iterator = p.getLearnedSpells().iterator(); iterator.hasNext(); ) {
            int i = ((Integer)iterator.next()).intValue();
            inv.setItem(19 + slot, ItemsForGui.SpellBook(i));
            slot++;
        }
        return inv;
    }

    public static Inventory viewEnchant(int id) {
        Inventory inv = Bukkit.createInventory(null, 27, "§5Зачар");
        RPGItemContainer RPGI = RPGItemContainer.getRPGItemContainer(id);
        int quality = RPGI.getQuality();
        int chance = 0;
        if(quality == 1 || quality == 2 ||quality == 3 ){
            for (int i = 0; i <= 7; i++) {
                if (i == 0)
                    chance = 7 + quality / 2;
                if (i == 1)
                    chance = 10 + quality;
                if (i == 2)
                    chance = 15 + quality;
                if (i == 3)
                    chance = 15 + quality * 2;
                if (i == 4)
                    chance = 23 + (int)Math.round(quality * 2.5D);
                if (i == 5)
                    chance = 27 + quality * 3;
                if (i == 6)
                    chance = 45 + quality * 4;
                ItemStack veitem = ItemsForGui.GUIItem((new RPGItem()).getRPGItem(id, i, 1,  0, 0, 0,0, 0,null));
                ItemMeta vemeta = veitem.getItemMeta();
                List<String> lore = new ArrayList<>();
                if(i == 7)
                lore.add("§cМаксимальный уровень зачарования");
                lore.addAll(vemeta.getLore());
                vemeta.setLore(lore);
                veitem.setItemMeta(vemeta);
                inv.setItem(i, veitem);
            }
        }else {
            for (int i = 0; i <= 10; i++) {
                if (i == 0)
                    chance = 7 + quality / 2;
                if (i == 1)
                    chance = 10 + quality;
                if (i == 2)
                    chance = 15 + quality;
                if (i == 3)
                    chance = 15 + quality * 2;
                if (i == 4)
                    chance = 23 + (int) Math.round(quality * 2.5D);
                if (i == 5)
                    chance = 27 + quality * 3;
                if (i == 6)
                    chance = 45 + quality * 4;
                if (i >= 7)
                    chance = 60 + quality * 4;
                ItemStack veitem = ItemsForGui.GUIItem((new RPGItem()).getRPGItem(id, i, 1, 0, 0, 0, 0, 0,null));
                ItemMeta vemeta = veitem.getItemMeta();
                List<String> lore = new ArrayList<>();
                if (i < 10) {
                    lore.add("§6Шанс зачарования на §c+" + (i + 1) + "§6: §b" + (100 - chance) + "%");
                } else {
                    lore.add("§cМаксимальный уровень зачарования");
                }
                lore.addAll(vemeta.getLore());
                vemeta.setLore(lore);
                veitem.setItemMeta(vemeta);
                inv.setItem(i, veitem);
            }
        }
        for (int j = 0; j < inv.getSize(); j++) {
            if (inv.getItem(j) == null)
                inv.setItem(j, ItemsForGui.corner());
        }
        return inv;
    }

    public static Inventory allPlayerQuest(rpgplayer player) {
        Inventory inv = Bukkit.getServer().createInventory(null, 54, "§6Доступные поручения");
        for (int i = 1; i < azerot.qsts + 1; i++) {
            if (player.getStatusQuest("" + i) != 1 && player.getStatusQuest("" + i) != 2 && !Quest.getQuest("" + i).isLock(player) && Quest.getQuest("" + i).getType() != 2)
                inv.addItem(new ItemStack[] { ItemsForGui.canAccept("" + i) });
            if (player.getPLevel() >= Quest.getQuest("" + i).rLvl() && Quest.getQuest("" + i).isLock(player) && Quest.getQuest("" + i).getType() != 2)
                inv.addItem(new ItemStack[] { ItemsForGui.cantAccept("" + i) });
        }
        for (int j = 0; j < inv.getSize(); j++) {
            if (inv.getItem(j) == null)
                inv.setItem(j, ItemsForGui.corner());
        }
        return inv;
    }

    public static Inventory sell() {
        Inventory inv = Bukkit.getServer().createInventory(null, 9, "§6Мусорка");
                inv.setItem(inv.getSize() - 1, ItemsForGui.sellcomplete());

        return inv;
    }

    public static Inventory zatochka() {
        Inventory inv = Bukkit.getServer().createInventory(null, 27, "§6Улучшение");
       // 11,22,15 камни 4 вещь, 9-шанс, 17- потдверждение
        inv.setItem(17, ItemsForGui.confirm());
        for (int j = 0; j < inv.getSize(); j++) {
            if(j != 11 && j != 22 && j != 15 && j != 4 && j != 9 && j != 17){
                inv.setItem(j, ItemsForGui.corner());
            }
        }

        return inv;
    }



    public static Inventory quest(Player p, Integer quest) {
        Inventory inv = Bukkit.getServer().createInventory(null, 45, "§5Задание: Начало #1");
        switch (quest){
            case 1:
                for(int i = 1; i < 21; i++){
                    inv.setItem(i - 1, do30lvl.quest1(p, i));
                }
                break;
            case 2:
                for(int i = 1; i < 13; i++){
                    inv.setItem(i - 1, do30lvl.quest2(p, i));
                }
                break;
            case 4:
                for(int i = 1; i < 17; i++){
                    inv.setItem(i - 1, do30lvl.quest4(p, i));
                }
                break;
            case 5:
                for(int i = 1; i < 16; i++){
                    inv.setItem(i - 1, do30lvl.quest5(p, i));
                }
                break;
            case 6:
                for(int i = 1; i < 33; i++){
                    inv.setItem(i - 1, do30lvl.quest6(p, i));
                }
                break;

        }
        return inv;
    }

    public void test(){
        for(int i = 1; i <= 2; i++){
            Inventory invThirdPacthItems = Bukkit.createInventory(null, 54, "§5Информация о предметах. Страница: " + i);
            List<Integer> items = new ArrayList<>();
            if(i == 1){
                items = azerot.getInstance().getAllItemsIdThirdPatch().subList(0,45);
            }
            if(i == 2){
                items = azerot.getInstance().getAllItemsIdThirdPatch().subList(45,90);
            }
            int slotSecondPacthItems = 0;
            for (Integer j : items) {
                ItemStack rpgitem = ItemsForGui.GUIItem((new RPGItem()).getRPGItem(j, 0, 1,  0, 1, 0,0,0,null));
                invThirdPacthItems.setItem(slotSecondPacthItems, rpgitem);
                slotSecondPacthItems++;
            }

            if(i == 1){
                invThirdPacthItems.setItem(invThirdPacthItems.getSize() - 1, ItemsForGui.BlockDataBaseNextPage(53));
            }
            if(i == 1){
                invThirdPacthItems.setItem(invThirdPacthItems.getSize() - 9, ItemsForGui.BlockDataBaseLeave());
                invThirdPacthItems.setItem(49, ItemsForGui.FilterItems(0));
            }else{
                invThirdPacthItems.setItem(invThirdPacthItems.getSize() - 9, ItemsForGui.BlockDataBaseLastPage(45));
            }
            for (int j = 0; j < invThirdPacthItems.getSize(); j++) {
                if (invThirdPacthItems.getItem(j) == null)
                    invThirdPacthItems.setItem(j, ItemsForGui.corner());
            }
            azerot.getInventorys().put("InvItemsForThirdPatch: " + i, invThirdPacthItems);
        }
    }

    public static Inventory shopbuy(int shopid, int itemid, int amount, rpgplayer player) {
        Inventory inv = Bukkit.getServer().createInventory(null, 54, "§6Меню торговли §c#" + shopid);
                File file = new File(azerot.getInstance().getDataFolder() + File.separator + "shops.yml");
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        for (int j = 0; j < inv.getSize(); j++)
            inv.setItem(j, ItemsForGui.corner());
        List<String> itemsprice = yamlConfiguration.getStringList(String.valueOf(shopid) + "." + itemid);
        int slot = 10;
        for (String i : itemsprice) {
            if (slot > 14 && slot < 37)
                slot = 37;
            String[] it = i.trim().split(" ");
            inv.setItem(slot, ItemsForGui.GUIItem((new RPGItem()).getRPGItem(Integer.valueOf(Integer.valueOf(it[0]).intValue()).intValue(), 0, Integer.valueOf(Integer.valueOf(it[1]).intValue()).intValue(),  0, 0, 0,0, 0, player)));
            slot += 2;
        }
        inv.setItem(16, ItemsForGui.GUIItem((new RPGItem()).getRPGItem(itemid, 0, amount, 0, 0,0,0, 0,player)));
        inv.setItem(43, ItemsForGui.tradecomplete());
        return inv;
    }
    public static Inventory destroyerItems(ItemStack item, rpgplayer p) {
        Inventory inv = Bukkit.getServer().createInventory(null, 9, "§6Перековка предмета");
        inv.setItem(inv.getSize() - 1, ItemsForGui.destroyercomplete());
        for (int j = 0; j < inv.getSize(); j++)
            inv.setItem(j, ItemsForGui.corner());
        inv.setItem(4, ItemsForGui.GUIItem(item));
        return inv;
    }





    public static int getEmerald(int price, int type) {
        int emerald1 = 0;
        int emerald2 = 0;
        int emerald3 = 0;
        int emerald4 = 0;
        int emerald5 = 0;
        while (price >= 16777216) {
            price -= 16777216;
            emerald5++;
        }
        while (price >= 262144) {
            price -= 262144;
            emerald4++;
        }
        while (price >= 4096) {
            price -= 4096;
            emerald3++;
        }
        while (price >= 64) {
            price -= 64;
            emerald2++;
        }
        while (price > 0 && price < 64) {
            price--;
            emerald1++;
        }
        if (type == 5)
            return emerald5;
        if (type == 4)
            return emerald4;
        if (type == 3)
            return emerald3;
        if (type == 2)
            return emerald2;
        if (type == 1)
            return emerald1;
        return 0;
    }
}
