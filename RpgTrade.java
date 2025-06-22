package azerot.azerot;

import java.util.*;

import azerot.azerot.events.CraftingMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RpgTrade implements Listener {
    private static final HashMap<String, RpgTrade> trades = new HashMap<>();

    private static final HashSet<String> trade_enabled = new HashSet<>();

    private static ItemStack accepted = null;

    private static ItemStack accept = null;

    private HashMap<String, Integer> itemsFirstPlayer = new HashMap<>();

    private static Material maccepted = null;

    private static Material maccept = null;

    private final rpgplayer t1;

    private final rpgplayer t2;

    private int lk1 = 0;
    private int lk2 = 0;

    private static int moneyT1 = 0;

    private int moneyT2 = 0;

    private int i = 9;
    private int j = 14;
    private final Inventory inv = Bukkit.createInventory(null, 54, "Обмен");

    public RpgTrade(rpgplayer trader1, rpgplayer trader2) {
        this.t1 = trader1;
        this.t2 = trader2;
        this.inv.setItem(1, lkForTrade(lk1));
        this.inv.setItem(2, ItemsForGui.moneyForTrade(moneyT1));
        this.inv.setItem(6, ItemsForGui.moneyForTrade(moneyT2));
        this.inv.setItem(7, lkForTrade(lk2));
        this.inv.setItem(45, ItemsForGui.lkForTrade());
        this.inv.setItem(46, ItemsForGui.lk2ForTrade());
        this.inv.setItem(47, ItemsForGui.lkMinusTrade());
        this.inv.setItem(48, ItemsForGui.lk2MinusTrade());
        this.inv.setItem(50, ItemsForGui.lkForTrade());
        this.inv.setItem(51, ItemsForGui.lk2ForTrade());
        this.inv.setItem(52, ItemsForGui.lkMinusTrade());
        this.inv.setItem(53, ItemsForGui.lk2MinusTrade());
        this.inv.setItem(36, ItemsForGui.money1());
        this.inv.setItem(37, ItemsForGui.money2());
        this.inv.setItem(38, ItemsForGui.money3());
        this.inv.setItem(39, ItemsForGui.money4());
        this.inv.setItem(41, ItemsForGui.money1());
        this.inv.setItem(42, ItemsForGui.money2());
        this.inv.setItem(43, ItemsForGui.money3());
        this.inv.setItem(44, ItemsForGui.money4());
        if (accept == null) {
            accept = new ItemStack(Material.REDSTONE_BLOCK);
            ItemMeta itemMeta = accept.getItemMeta();
            itemMeta.setDisplayName("§2Согласиться на обмен");
                    accept.setItemMeta(itemMeta);
            accepted = new ItemStack(Material.EMERALD_BLOCK);
            itemMeta = accepted.getItemMeta();
            itemMeta.setDisplayName(ChatColor.DARK_GREEN + "Согласен!");
                    accepted.setItemMeta(itemMeta);
            maccepted = accepted.getType();
            maccept = accept.getType();
        }
        ItemStack corner = new ItemStack(Material.IRON_FENCE);
        ItemMeta im = corner.getItemMeta();
        im.setDisplayName(this.t1.getName() + " - " + this.t2.getName());
        corner.setItemMeta(im);
        for (int i = 4; i < 54; i += 9)
            this.inv.setItem(i, corner);
        this.inv.setItem(0, accept);
        this.inv.setItem(8, accept);
        trades.put(this.t1.getName(), this);
        trades.put(this.t2.getName(), this);
        this.t1.getPlayer().openInventory(this.inv);
        this.t2.getPlayer().openInventory(this.inv);
    }

    public static  ItemStack lkForTrade(int lk){
        ItemStack item = new ItemStack(Material.GOLD_BLOCK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6LK");
        List<String> lore = new ArrayList<>();
        lore.add("§6" + lk);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }




    public Inventory getInventory() {
        return this.inv;
    }

    public rpgplayer getFirstTrader() {
        return this.t1;
    }

    public rpgplayer getSecondTrader() {
        return this.t2;
    }

    public List<ItemStack> getFirstContents() {
        List<ItemStack> list = new ArrayList<>();
        for (int i = 9; i < this.inv.getSize() -18; i++) {
            if (i % 9 < 4) {
                ItemStack is = this.inv.getItem(i);
                if (is != null)
                    list.add(is);
            }
        }
        return list;
    }

    public List<ItemStack> getSecondContents() {
        List<ItemStack> list = new ArrayList<>();
        for (int i = 14; i < this.inv.getSize()-18; i++) {
            if (i % 9 > 4) {
                ItemStack is = this.inv.getItem(i);
                if (is != null)
                    list.add(is);
            }
        }
        return list;
    }

    public void handleQuit() {
        trades.remove(this.t1.getName());
        trades.remove(this.t2.getName());
        this.t1.getPlayer().closeInventory();
        this.t2.getPlayer().closeInventory();
        String msg = WorldUtils.worldName() + " §cТорговля была прервана!";
        this.t1.toPlayer().sendMessage(msg);
        this.t2.toPlayer().sendMessage(msg);
        t1.setMoney(t1.getMoney() + moneyT1);
        t2.setMoney(t2.getMoney() + moneyT2);
        for (ItemStack is : getFirstContents()){
            int id = ItemStat.getID(is);
            int mark = ItemStat.getMark(is);
            int enchant = ItemStat.getEnchant(is);
            int amount = is.getAmount();
            int strength = ItemStat.getScaleAtStrength(is);
            int agility = ItemStat.getScaleAtAgility(is);
            int intelligence = ItemStat.getScaleAtIntelligence(is);
            int markpower = ItemStat.getMarkpower(is);
            this.t1.toPlayer().getInventory().addItem(new RPGItem().getRPGItem(id,enchant,amount,mark,markpower,strength, agility, intelligence,t1));
        }
        for (ItemStack is : getSecondContents()){
            int id = ItemStat.getID(is);
            int mark = ItemStat.getMark(is);
            int enchant = ItemStat.getEnchant(is);
            int amount = is.getAmount();
            int strength = ItemStat.getScaleAtStrength(is);
            int agility = ItemStat.getScaleAtAgility(is);
            int intelligence = ItemStat.getScaleAtIntelligence(is);
            int markpower = ItemStat.getMarkpower(is);
            this.t2.toPlayer().getInventory().addItem(new RPGItem().getRPGItem(id,enchant,amount,mark,markpower,strength, agility, intelligence,t1));
        }
        this.inv.clear();
    }

    public void updateInventory() {
        this.t1.getPlayer().updateInventory();
        this.t2.getPlayer().updateInventory();
    }

    private void handleFirstPlayerClick(InventoryClickEvent e) {
        e.setCancelled(true);
        int slot = e.getRawSlot();
        Material m1 = this.inv.getItem(0).getType();
        Material m2 = this.inv.getItem(8).getType();
        switch (slot){
            case 45:
                if (lk1 + 5 >= t1.getGold()) {
                    lk1 = t1.getGold();
                    t1.toPlayer().sendMessage(WorldUtils.worldName() + " §4Вы не можете предложить больше, чем у вас есть!");
                }else {
                    lk1 +=5;
                }
                this.inv.setItem(1, lkForTrade(lk1));
                break;
            case 46:
               if (lk1 + 10 >= t1.getGold()) {
                    lk1 = t1.getGold();
                    t1.toPlayer().sendMessage(WorldUtils.worldName() + " §4Вы не можете предложить больше, чем у вас есть!");
                }else {
                    lk1 +=10;
                }
                this.inv.setItem(1, lkForTrade(lk1));
                break;
            case 47:
                if (lk1 - 5 < 0) {
                    lk1 = 0;
                }else {
                    lk1 -=5;
                }
                this.inv.setItem(1, lkForTrade(lk1));
                break;
            case 48:
                if (lk1 - 10 < 0) {
                    lk1 = 0;
                }else {
                    lk1 -=10;
                }
                this.inv.setItem(1, lkForTrade(lk1));
                break;
            case 36:
                if(e.getClick().isLeftClick()){
                    if (t1.getMoney() > 0){
                        moneyT1++;
                        t1.setMoney(t1.getMoney() - 1);
                    }else{
                        t1.toPlayer().sendMessage(WorldUtils.worldName() + " §4Вы не можете предложить больше, чем у вас есть!");
                    }

                }
                if(e.getClick().isRightClick()){
                    if(moneyT1 > 0){
                        moneyT1--;
                        t1.setMoney(t1.getMoney() + 1);
                    }
                }
                this.inv.setItem(2, ItemsForGui.moneyForTrade(moneyT1));
                break;
            case 37:
                if(e.getClick().isLeftClick()){
                    if (t1.getMoney() > 64){
                        moneyT1 += 64;
                        t1.setMoney(t1.getMoney() - 64);
                    }else{
                        t1.toPlayer().sendMessage(WorldUtils.worldName() + " §4Вы не можете предложить больше, чем у вас есть!");
                    }
                }
                if(e.getClick().isRightClick()){
                    if(moneyT1 >= 64){
                        moneyT1 -= 64;
                        t1.setMoney(t1.getMoney() + 64);
                    }
                }
                this.inv.setItem(2, ItemsForGui.moneyForTrade(moneyT1));
                break;
            case 38:
                if(e.getClick().isLeftClick()){
                    if (t1.getMoney() > 4096){
                        moneyT1 += 4096;
                        t1.setMoney(t1.getMoney() - 4096);
                    }else{
                        t1.toPlayer().sendMessage(WorldUtils.worldName() + " §4Вы не можете предложить больше, чем у вас есть!");
                    }
                }
                if(e.getClick().isRightClick()){
                    if(moneyT1 >= 4096){
                        moneyT1 -= 4096;
                        t1.setMoney(t1.getMoney() + 4096);
                    }
                }
                this.inv.setItem(2, ItemsForGui.moneyForTrade(moneyT1));
                break;
            case 39:
                if(e.getClick().isLeftClick()){
                    if (t1.getMoney() > 262144){
                        moneyT1 += 262144;
                        t1.setMoney(t1.getMoney() - 262144);
                    }else{
                        t1.toPlayer().sendMessage(WorldUtils.worldName() + " §4Вы не можете предложить больше, чем у вас есть!");
                    }
                }
                if(e.getClick().isRightClick()){
                    if(moneyT1 >= 262144){
                        moneyT1 -= 262144;
                        t1.setMoney(t1.getMoney() + 262144);
                    }
                }
                this.inv.setItem(2, ItemsForGui.moneyForTrade(moneyT1));
                break;
        }
        if (slot % 9 < 4) {
            if (slot == 0) {
                e.setCancelled(true);
                if (m1 == maccept) {
                    this.inv.setItem(0, accepted);
                    if (m2 == maccepted)
                        makeTradeDone();
                }
            } else {
                if (m1 == maccepted)
                    this.inv.setItem(0, accept);
                if (m2 == maccepted)
                    this.inv.setItem(8, accept);
            }
        } else {
            e.setCancelled(true);
            return;
        }
        updateInventory();
    }

    private void handleSecondPlayerClick(InventoryClickEvent e) {
        int slot = e.getRawSlot();
        Material m1 = this.inv.getItem(0).getType();
        Material m2 = this.inv.getItem(8).getType();
        switch (slot){
            case 50:
                if (lk2 + 5 >= t2.getGold()) {
                    lk2 = t2.getGold();
                    t2.toPlayer().sendMessage(WorldUtils.worldName() + " §4Вы не можете предложить больше, чем у вас есть!");
                }else {
                    lk2 +=5;
                }
                this.inv.setItem(7, lkForTrade(lk2));
                break;
            case 51:
                if (lk2 + 10 >= t2.getGold()) {
                    lk2 = t2.getGold();
                    t2.toPlayer().sendMessage(WorldUtils.worldName() + " §4Вы не можете предложить больше, чем у вас есть!");
                }else {
                    lk2 +=10;
                }
                this.inv.setItem(7, lkForTrade(lk2));
                break;
            case 52:
                if (lk2 - 5 < 0) {
                    lk2 = 0;
                }else {
                    lk2 -=5;
                }
                this.inv.setItem(7, lkForTrade(lk2));
                break;
            case 53:
                if (lk2 - 10 < 0) {
                    lk2 = 0;
                }else {
                    lk2 -=10;
                }
                this.inv.setItem(7, lkForTrade(lk2));
                break;
            case 41:
                if(e.getClick().isLeftClick()){
                    if (t2.getMoney() > 0){
                        moneyT2++;
                        t2.setMoney(t2.getMoney() - 1);
                    }else{
                        t2.toPlayer().sendMessage(WorldUtils.worldName() + " §4Вы не можете предложить больше, чем у вас есть!");
                    }

                }
                if(e.getClick().isRightClick()){
                    if(moneyT2 > 0){
                        moneyT2--;
                        t2.setMoney(t2.getMoney() + 1);
                    }
                }
                this.inv.setItem(6, ItemsForGui.moneyForTrade(moneyT2));
                break;
            case 42:
                if(e.getClick().isLeftClick()){
                    if (t2.getMoney() > 64){
                        moneyT2 += 64;
                        t2.setMoney(t2.getMoney() - 64);
                    }else{
                        t2.toPlayer().sendMessage(WorldUtils.worldName() + " §4Вы не можете предложить больше, чем у вас есть!");
                    }
                }
                if(e.getClick().isRightClick()){
                    if(moneyT2 >= 64){
                        moneyT2 -= 64;
                        t2.setMoney(t2.getMoney() + 64);
                    }
                }
                this.inv.setItem(6, ItemsForGui.moneyForTrade(moneyT2));
                break;
            case 43:
                if(e.getClick().isLeftClick()){
                    if (t2.getMoney() > 4096){
                        moneyT2 += 4096;
                        t2.setMoney(t2.getMoney() - 4096);
                    }else{
                        t2.toPlayer().sendMessage(WorldUtils.worldName() + " §4Вы не можете предложить больше, чем у вас есть!");
                    }
                }
                if(e.getClick().isRightClick()){
                    if(moneyT2 >= 4096){
                        moneyT2 -= 4096;
                        t1.setMoney(t2.getMoney() + 4096);
                    }
                }
                this.inv.setItem(6, ItemsForGui.moneyForTrade(moneyT2));
                break;
            case 44:
                if(e.getClick().isLeftClick()){
                    if (t2.getMoney() > 262144){
                        moneyT2 += 262144;
                        t2.setMoney(t2.getMoney() - 262144);
                    }else{
                        t2.toPlayer().sendMessage(WorldUtils.worldName() + " §4Вы не можете предложить больше, чем у вас есть!");
                    }
                }
                if(e.getClick().isRightClick()){
                    if(moneyT2 >= 262144){
                        moneyT2 -= 262144;
                        t2.setMoney(t2.getMoney() + 262144);
                    }
                }
                this.inv.setItem(6, ItemsForGui.moneyForTrade(moneyT2));
                break;
        }
        if (slot % 9 > 4) {
            if (slot == 8) {
                e.setCancelled(true);
                if (m2 == maccept) {
                    this.inv.setItem(8, accepted);
                    if (m1 == maccepted)
                        makeTradeDone();
                }
            } else {
                if (m1 == maccepted)
                    this.inv.setItem(0, accept);
                if (m2 == maccepted)
                    this.inv.setItem(8, accept);
            }
        } else {
            e.setCancelled(true);
            return;
        }
        updateInventory();
    }

    public void makeTradeDone() {
        for (ItemStack is : getFirstContents()){
            int id = ItemStat.getID(is);
            int mark = ItemStat.getMark(is);
            int enchant = ItemStat.getEnchant(is);
            int amount = is.getAmount();
            int strength = ItemStat.getScaleAtStrength(is);
            int agility = ItemStat.getScaleAtAgility(is);
            int intelligence = ItemStat.getScaleAtIntelligence(is);
            int markpower = ItemStat.getMarkpower(is);
            this.t2.toPlayer().getInventory().addItem(new RPGItem().getRPGItem(id,enchant,amount,mark,markpower,strength,agility,intelligence,t2));
        }
        for (ItemStack is : getSecondContents()){
            int id = ItemStat.getID(is);
            int mark = ItemStat.getMark(is);
            int enchant = ItemStat.getEnchant(is);
            int amount = is.getAmount();
            int strength = ItemStat.getScaleAtStrength(is);
            int agility = ItemStat.getScaleAtAgility(is);
            int intelligence = ItemStat.getScaleAtIntelligence(is);
            int markpower = ItemStat.getMarkpower(is);
            this.t1.toPlayer().getInventory().addItem(new RPGItem().getRPGItem(id,enchant,amount,mark,markpower,strength,agility,intelligence,t1));
        }
        if(lk1 > 0){
            this.t1.setGold(t1.getGold() - lk1);
            this.t2.setGold(t2.getGold() + lk1);
            this.t2.toPlayer().sendMessage(WorldUtils.worldName() + " §2На ваш счет поступило " + lk1 + " золота!");
        }
        if(lk2 > 0){
             this.t2.setGold(t2.getGold() - lk2);
            this.t1.setGold(t1.getGold() + lk2);
            this.t1.toPlayer().sendMessage(WorldUtils.worldName() + " §2На ваш счет поступило " + lk2 + " золота!");
        }
        if(moneyT1 > 0){
            t2.setMoney(t2.getMoney() + moneyT1);
            moneyT1 = 0;
            this.t2.toPlayer().sendMessage(WorldUtils.worldName() + " §2На ваш счет поступило " + moneyT1 + " монет!");
        }
        if(moneyT2 > 0){
            t1.setMoney(t1.getMoney() + moneyT2);
            moneyT2 = 0;
            this.t1.toPlayer().sendMessage(WorldUtils.worldName() + " §2На ваш счет поступило " + moneyT2 + " монет!");
        }
        trades.remove(this.t1.getName());
        trades.remove(this.t2.getName());
        this.t1.getPlayer().closeInventory();
        this.t2.getPlayer().closeInventory();
        this.t1.toPlayer().sendMessage(WorldUtils.worldName() + " §aТорговля завершена");
        this.t2.toPlayer().sendMessage(WorldUtils.worldName() + " §aТорговля завершена");

        this.inv.clear();
    }

    public void handleClick(InventoryClickEvent e) {
        int slot = e.getRawSlot();
        Player p = (Player)e.getWhoClicked();
        Inventory top = e.getView().getTopInventory();
        ItemStack clicked = e.getCurrentItem();
        ClickType click = e.getClick();
        if (click == ClickType.DOUBLE_CLICK || click == ClickType.SHIFT_LEFT || click == ClickType.SHIFT_RIGHT || e.getClick().isShiftClick()) {
            e.setCancelled(true);
            return;
        }
        if(Objects.equals(e.getWhoClicked().getName(), t1.getName())) {
            if (e.getClickedInventory().getName().equals("container.inventory")) {
                e.setCancelled(true);
                if(RPGItemContainer.getRPGItemContainer(ItemStat.getID(clicked)).getSoulBound() == 1 ||  RPGItemContainer.getRPGItemContainer(ItemStat.getID(clicked)).getSoulBound() == 2){
                    e.setCancelled(true);
                    t1.toPlayer().sendMessage(WorldUtils.worldName() + " §4Вы не можете передать квестовые или привязаные предметы!");
                    return;
                }
                if(i <= 30){
                    this.inv.setItem(i, clicked);
                    e.getCurrentItem().setAmount(0);
                    updateInventory();
                }else{
                    t1.toPlayer().sendMessage(WorldUtils.worldName() + " §cНет места!");
                    return;
                }
                if ((i+1) % 9 >= 4) {
                    i += 6;
                } else {
                    if (i + 1 >= 31) {
                        i = 31;
                    } else {
                        i++;
                    }
                }
            }
        }
        if(Objects.equals(e.getWhoClicked().getName(), t2.getName())) {
            if (e.getClickedInventory().getName().equals("container.inventory")) {
                if(RPGItemContainer.getRPGItemContainer(ItemStat.getID(clicked)).getSoulBound() == 1 ||  RPGItemContainer.getRPGItemContainer(ItemStat.getID(clicked)).getSoulBound() == 2){
                    e.setCancelled(true);
                    t2.toPlayer().sendMessage(WorldUtils.worldName() + " §4Вы не можете передать квестовые или привязаные предметы!");
                    return;
                }
                e.setCancelled(true);
                if(j <= 35){
                    this.inv.setItem(j, clicked);
                    e.getCurrentItem().setAmount(0);
                    updateInventory();
                }else{
                    t2.toPlayer().sendMessage(WorldUtils.worldName() + " §cНет места!");
                    return;
                }
                if ((j+1) % 9 <= 4) {
                    j += 6;
                } else {
                    if (j + 1 >= 36) {
                        j = 36;
                    } else {
                        j++;
                    }
                }
            }
        }
        String name = top.getItem(4).getItemMeta().getDisplayName();
        String[] args = name.split(" - ");
        if (args[0].equalsIgnoreCase(p.getName())) {
            handleFirstPlayerClick(e);
        } else if (args[1].equalsIgnoreCase(p.getName())) {
            handleSecondPlayerClick(e);
        } else {
            e.setCancelled(true);
        }
    }

    public static boolean isTrading(rpgplayer rp) {
        return trades.containsKey(rp.getName());
    }

    public static RpgTrade getTrading(rpgplayer rp) {
        return trades.get(rp.getName());
    }

    public static boolean isTradeEnabled(rpgplayer rp) {
        return trade_enabled.contains(rp.getName());
    }
}
