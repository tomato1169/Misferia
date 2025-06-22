package azerot.azerot.events;

import azerot.azerot.GenerateInventory;
import azerot.azerot.WorldUtils;
import azerot.azerot.azerot;
import azerot.azerot.regions.regionManager;
import azerot.azerot.rpgplayer;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Score;

public class InventoryClickShop implements Listener {

    @EventHandler
    public void Click(InventoryClickEvent e) {
        Player wc = (Player) e.getWhoClicked();
        ItemStack clicked = e.getCurrentItem();
        rpgplayer pl = rpgplayer.getRPGPlayer(wc);
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


        if (e.getClickedInventory().getName().equals("§5Магазин: " + pl.getGold())){
            if(e.getClickedInventory().equals(wc.getInventory())){
                return;
            }
            if(clicked.getItemMeta().getDisplayName().equals("§2VIP")){
                if(pl.getGroup() >= 1){
                    return;
                }else {
                    if(pl.getGold() >= 200 - onPlesser(pl)){
                        pl.toPlayer().sendMessage(WorldUtils.worldName() + " §eВы приобрели группу: §2VIP");
                        pl.setGold(pl.getGold() - 200);
                        pl.setGroup(1);
                        wc.openInventory(GenerateInventory.DonateShop(pl));
                        pl.toPlayer().sendMessage("У вас осталось монет: " + pl.getGold());
                        WorldUtils.updatePlayerTab(pl.toPlayer());
                    }else {
                        pl.toPlayer().sendMessage(WorldUtils.worldName() + " §4Вам не хватает!");
                        return;
                    }
                }

            }
            if(clicked.getItemMeta().getDisplayName().equals("§bPREMIUM")){
                if(pl.getGroup() >= 2){
                    return;
                }else {
                    if(pl.getGold() >= 450 - onPlesser(pl)){
                        pl.toPlayer().sendMessage(WorldUtils.worldName() + " §eВы приобрели группу: §bPREMIUM");
                        pl.setGold(pl.getGold() - (450 - onPlesser(pl)));
                        pl.setGroup(2);
                        wc.openInventory(GenerateInventory.DonateShop(pl));
                        pl.toPlayer().sendMessage("У вас осталось монет: " + pl.getGold());
                        WorldUtils.updatePlayerTab(pl.toPlayer());
                    }else {
                        pl.toPlayer().sendMessage(WorldUtils.worldName() + " §4Вам не хватает!");
                        return;
                    }
                }

            }
            if(clicked.getItemMeta().getDisplayName().equals("§6HOLY")){
                if(pl.getGroup() >= 3){
                    return;
                }else {
                    if(pl.getGold() >= 750 - onPlesser(pl)){
                        pl.toPlayer().sendMessage(WorldUtils.worldName() + " §eВы приобрели группу: §6HOLY");
                        pl.setGold(pl.getGold() - (750 - onPlesser(pl)));
                        pl.setGroup(3);
                        wc.openInventory(GenerateInventory.DonateShop(pl));
                        pl.toPlayer().sendMessage("У вас осталось монет: " + pl.getGold());
                        WorldUtils.updatePlayerTab(pl.toPlayer());
                    }else {
                        pl.toPlayer().sendMessage(WorldUtils.worldName() + " §4Вам не хватает!");
                        return;
                    }
                }

            }
            if(clicked.getItemMeta().getDisplayName().equals("§1RENOWN")){
                if(pl.getGroup() >= 4){
                    return;
                }else {
                    if(pl.getGold() >= 1500 - onPlesser(pl)){
                        pl.toPlayer().sendMessage(WorldUtils.worldName() + " §eВы приобрели группу: §1RENOWN");
                        pl.setGold(pl.getGold() - (1500 - onPlesser(pl)));
                        pl.setGroup(4);
                        wc.openInventory(GenerateInventory.DonateShop(pl));
                        pl.toPlayer().sendMessage("У вас осталось монет: " + pl.getGold());
                        WorldUtils.updatePlayerTab(pl.toPlayer());
                    }else {
                        pl.toPlayer().sendMessage(WorldUtils.worldName() + " §4Вам не хватает!");
                        return;
                    }
                }

            }
            if(clicked.getItemMeta().getDisplayName().equals("§dPARADISE")){
                if(pl.getGroup() >= 5){
                    return;
                }else {
                    if(pl.getGold() >= 2500 - onPlesser(pl)){
                        pl.toPlayer().sendMessage(WorldUtils.worldName() + " §eВы приобрели группу: §dPARADISE");
                        pl.setGold(pl.getGold() - (2500 - onPlesser(pl)));
                        pl.setGroup(5);
                        wc.openInventory(GenerateInventory.DonateShop(pl));
                        pl.toPlayer().sendMessage("У вас осталось монет: " + pl.getGold());
                        WorldUtils.updatePlayerTab(pl.toPlayer());
                    }else {
                        pl.toPlayer().sendMessage(WorldUtils.worldName() + " §4Вам не хватает!");
                        return;
                    }
                }

            }
            if(clicked.getItemMeta().getDisplayName().equals("§5HEAVENLY")){
                if(pl.getGroup() >= 6){
                    return;
                }else {
                    if(pl.getGold() >= 3500 - onPlesser(pl)){
                        pl.toPlayer().sendMessage(WorldUtils.worldName() + " §eВы приобрели группу: §5HEAVENLY");
                        pl.setGold(pl.getGold() - (3500 - onPlesser(pl)));
                        pl.setGroup(6);
                        wc.openInventory(GenerateInventory.DonateShop(pl));
                        pl.toPlayer().sendMessage("У вас осталось монет: " + pl.getGold());
                        WorldUtils.updatePlayerTab(pl.toPlayer());
                    }else {
                        pl.toPlayer().sendMessage(WorldUtils.worldName() + " §4Вам не хватает!");
                        return;
                    }
                }

            }
            if(clicked.getItemMeta().getDisplayName().equals("§l§6LEGENDARY")){
                if(pl.getGroup() >= 7){
                    return;
                }else {
                    if(pl.getGold() >= 5000 - onPlesser(pl) ) {
                        pl.toPlayer().sendMessage(WorldUtils.worldName() + " §eВы приобрели группу: §l§6LEGENDARY");
                        pl.setGold(pl.getGold() - (5000 - onPlesser(pl)));
                        pl.setGroup(7);
                        wc.openInventory(GenerateInventory.DonateShop(pl));
                        pl.toPlayer().sendMessage("У вас осталось монет: " + pl.getGold());
                        WorldUtils.updatePlayerTab(pl.toPlayer());
                    }else {
                        pl.toPlayer().sendMessage(WorldUtils.worldName() + " §4Вам не хватает!");
                        return;
                    }
                }

            }
            if(clicked.getItemMeta().getDisplayName().equals("§5Хранилище для рецептов")){
                if(pl.getRCEnabled()){
                    return;
                }else{
                    if(pl.getGold() >= 150){
                        pl.setRCEnabled(true);
                        pl.toPlayer().sendMessage(WorldUtils.worldName() + " §eВы приобрели: §5Хранилище для рецептов!");
                        pl.setGold(pl.getGold() - 150);
                        wc.openInventory(GenerateInventory.DonateShop(pl));
                    }else{
                        pl.toPlayer().sendMessage(WorldUtils.worldName() + " §4Вам не хватает!");
                        return;
                    }
                }

            }
            if(clicked.getItemMeta().getDisplayName().equals("§5Кейс эмблем")){
                if(pl.getRCEnabled()){
                    return;
                }else{
                    if(pl.getGold() >= 100){
                        pl.toPlayer().sendMessage(WorldUtils.worldName() + " §eВы приобрели: §5Кейс эмблем!");
                        pl.setGold(pl.getGold() - 100);
                        wc.openInventory(GenerateInventory.DonateShop(pl));
                    }else{
                        pl.toPlayer().sendMessage(WorldUtils.worldName() + " §4Вам не хватает!");
                        return;
                    }
                }

            }
            if(clicked.getItemMeta().getDisplayName().equals("§5Допольнительный дроп")){
                if(azerot.donate.get("purchasedDROP").equals(false)){
                    if(pl.getGold() >= 300){
                        pl.toPlayer().sendMessage(WorldUtils.worldName() + " §eВы приобрели: §5Допольнительный дроп!");
                        pl.setGold(pl.getGold() - 300);
                        azerot.donate.put("additionalDrop", 10);
                        azerot.donate.put("purchasedDROP", true);
                        azerot.donate.put("boughtBYPlayerDROP", pl.getName());
                        azerot.donate.put("timeDROP", System.currentTimeMillis() + 600000L);
                        wc.openInventory(GenerateInventory.DonateShop(pl));
                    }else{
                        pl.toPlayer().sendMessage(WorldUtils.worldName() + " §4Вам не хватает!");
                        return;
                    }

                }else {
                    pl.toPlayer().sendMessage(WorldUtils.worldName() + " §4Вы не можете приобрести это, пока бонус активен!");
                    return;
                }
                }
            if(clicked.getItemMeta().getDisplayName().equals("§5Допольнительный опыт")){
                if(azerot.donate.get("purchasedXP").equals(false)){
                    if(pl.getGold() >= 300){
                        pl.toPlayer().sendMessage(WorldUtils.worldName() + " §eВы приобрели: §5Допольнительный опыт!");
                        pl.setGold(pl.getGold() - 300);
                        ByteArrayDataOutput out = ByteStreams.newDataOutput();
                        try{
                            out.writeUTF("GlobalBoost");
                            out.writeUTF("XP");
                            out.writeLong(System.currentTimeMillis() + 600000L);
                            azerot.getInstance().getServer().sendPluginMessage((Plugin)azerot.getInstance(), "message:main", out.toByteArray());
                        }catch (Exception exception){
                            exception.printStackTrace();
                        }
                    }else{
                        pl.toPlayer().sendMessage(WorldUtils.worldName() + " §4Вам не хватает!");
                        return;
                    }

                }else {
                    pl.toPlayer().sendMessage(WorldUtils.worldName() + " §4Вы не можете приобрести это, пока бонус активен!");
                    return;
                }
            }

            }

    }
    public static int onPlesser(rpgplayer p){
        int gold = 0;
        switch (p.getGroup()){
            case 0:
                gold = 0;
                break;
            case 1:
                gold = 200;
                break;
            case 2:
                gold = 450;
                break;
            case 3:
                gold = 750;
                break;
            case 4:
                gold = 1500;
                break;
            case 5:
                gold = 2500;
                break;
            case 6:
                gold = 3500;
                break;
        }
        return gold;
    }
}
