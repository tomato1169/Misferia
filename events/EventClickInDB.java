package azerot.azerot.events;

import azerot.azerot.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;

public class EventClickInDB implements Listener {

    @EventHandler
    public void Click(InventoryClickEvent e) throws SQLException {
        Player wc = (Player)e.getWhoClicked();
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

        if(e.getClickedInventory().getName().equals("§6Список дополнений")){
            if(clicked.getItemMeta().getDisplayName().equals("§4§lНедоступно в данном дополнении!")){
                e.setCancelled(true);
                return;
            }
            if(clicked.getItemMeta().getDisplayName().equals("§7§lНачало")){
                wc.openInventory(azerot.getInventorys().get("InvMobsInFirstPatch"));

                e.setCancelled(true);
            }
            if(clicked.getItemMeta().getDisplayName().equals("§7§lПуть туда")){
                wc.openInventory(azerot.getInventorys().get("InvMobsInSecondPatch"));
                e.setCancelled(true);
            }
            if(clicked.getItemMeta().getDisplayName().equals("§6§lНовый мир")){
                wc.openInventory(azerot.getInventorys().get("InvMobsInThirdPatch"));
                e.setCancelled(true);
            }
        }
        if(e.getClickedInventory().getName().contains("§5Информация о предмете")){
            e.setCancelled(true);
            if(clicked.getItemMeta().getDisplayName().equals("§6Выйти")){
                int id = ItemStat.getID(e.getInventory().getItem(49));
                if(id <= 45){
                    wc.openInventory(azerot.getInventorys().get("InvItemsForFirstPatch: 1"));
                }
                if(id > 45 && id <= 90){
                    wc.openInventory(azerot.getInventorys().get("InvItemsForFirstPatch: 2"));
                }
                if(id > 90 && id <= 108){
                    wc.openInventory(azerot.getInventorys().get("InvItemsForFirstPatch: 3"));
                }
                if(id > 108 && id <= 153){
                    wc.openInventory(azerot.getInventorys().get("InvItemsForSecondPatch: 1"));
                }
                if(id > 153 && id <= 198){
                    wc.openInventory(azerot.getInventorys().get("InvItemsForSecondPatch: 2"));
                }
                if(id > 198 && id <= 239 || id == 1000 || id == 1001 || id == 1002 || id == 1003){
                    wc.openInventory(azerot.getInventorys().get("InvItemsForSecondPatch: 3"));
                }
                if(id > 1003 && id <= 1045){
                    wc.openInventory(azerot.getInventorys().get("InvItemsForSecondPatch: 4"));
                }
            }
        }

        if(e.getClickedInventory().equals(azerot.getInventorys().get("InvMobsInFirstPatch"))){
            if(e.getSlot() <= 44){
                if(!clicked.getItemMeta().getDisplayName().equals("§r")){
                    int id = e.getSlot() + 1;
                    wc.openInventory(azerot.getInventorys().get("InvForItemsInMob: " + id));
                }
            }
            if(clicked.getItemMeta().getDisplayName().equals("§5Информация о всех предметах данного дополнения")){
                wc.openInventory(azerot.getInventorys().get("InvItemsForFirstPatch: 1"));
            }
            if(clicked.getItemMeta().getDisplayName().equals("§6Выйти")){
                wc.openInventory(azerot.getInventorys().get("InvPatchSelection"));
            }
        }
        if(e.getClickedInventory().equals(azerot.getInventorys().get("InvItemsForFirstPatch: 1"))){
            if (e.getSlot() <= 44){
                int id = ItemStat.getID(e.getCurrentItem());
                wc.openInventory(azerot.getInventorys().get("InvForItemInMobs: " + id));
            }
            if(clicked.getItemMeta().getDisplayName().equals("§7§lФильтр предметов")){
                wc.openInventory(azerot.getInventorys().get("typeOneFirstPatch"));
            }
            if(clicked.getItemMeta().getDisplayName().equals("§6Выйти")){
                wc.openInventory(azerot.getInventorys().get("InvMobsInFirstPatch"));
            }
            if(clicked.getItemMeta().getDisplayName().equals("§6--->")){
                wc.openInventory(azerot.getInventorys().get("InvItemsForFirstPatch: 2"));
            }
        }
        if(e.getClickedInventory().equals(azerot.getInventorys().get("InvItemsForFirstPatch: 2"))){
            if (e.getSlot() <= 44){
                int id = ItemStat.getID(e.getCurrentItem());
                wc.openInventory(azerot.getInventorys().get("InvForItemInMobs: " + id));
            }
            if(clicked.getItemMeta().getDisplayName().equals("§6<---")){
                wc.openInventory(azerot.getInventorys().get("InvItemsForFirstPatch: 1"));
            }
            if(clicked.getItemMeta().getDisplayName().equals("§6--->")){
                wc.openInventory(azerot.getInventorys().get("InvItemsForFirstPatch: 3"));
            }
        }
        if(e.getClickedInventory().equals(azerot.getInventorys().get("InvItemsForFirstPatch: 3"))){
            if (e.getSlot() <= 44){
                int id = ItemStat.getID(e.getCurrentItem());
                wc.openInventory(azerot.getInventorys().get("InvForItemInMobs: " + id));
            }
            if(clicked.getItemMeta().getDisplayName().equals("§6<---")){
                wc.openInventory(azerot.getInventorys().get("InvItemsForFirstPatch: 2"));
            }

        }
        if(e.getClickedInventory().equals(azerot.getInventorys().get("typeOneFirstPatch"))){
            if(clicked.getItemMeta().getDisplayName().equals("§7§lФильтр предметов")){
                wc.openInventory(azerot.getInventorys().get("typeTwoFirstPatch"));
            }
            if(clicked.getItemMeta().getDisplayName().equals("§6Выйти")){
                wc.openInventory(azerot.getInventorys().get("InvMobsInFirstPatch"));
            }
        }
        if(e.getClickedInventory().equals(azerot.getInventorys().get("typeTwoFirstPatch"))){
            if(clicked.getItemMeta().getDisplayName().equals("§7§lФильтр предметов")){
                wc.openInventory(azerot.getInventorys().get("typeThreeFirstPatch"));
            }
            if(clicked.getItemMeta().getDisplayName().equals("§6Выйти")){
                wc.openInventory(azerot.getInventorys().get("InvMobsInFirstPatch"));
            }
        }
        if(e.getClickedInventory().equals(azerot.getInventorys().get("typeThreeFirstPatch"))){
            if(clicked.getItemMeta().getDisplayName().equals("§7§lФильтр предметов")){
                wc.openInventory(azerot.getInventorys().get("InvItemsForFirstPatch: 1"));
            }
            if(clicked.getItemMeta().getDisplayName().equals("§6Выйти")){
                wc.openInventory(azerot.getInventorys().get("InvMobsInFirstPatch"));
            }
        }
        if(e.getClickedInventory().getName().equals("§5Информация о монстре")){
            e.setCancelled(true);
            int id = Integer.parseInt(e.getInventory().getItem(49).getItemMeta().getLore().get(e.getInventory().getItem(49).getItemMeta().getLore().size() - 1));

            if(clicked.getItemMeta().getDisplayName().equals("§6Выйти")){
                if(id <= 27){
                    wc.openInventory(azerot.getInventorys().get("InvMobsInFirstPatch"));
                }else if(id > 27 && id <= 60) {
                    wc.openInventory(azerot.getInventorys().get("InvMobsInSecondPatch"));
                }
            }
            if(clicked.getItemMeta().getDisplayName().equals("§6--->")){
                wc.openInventory(azerot.getInventorys().get("InvForItemsInMob: " + id + "/2"));
            }
            if(clicked.getItemMeta().getDisplayName().equals("§6<---")){
                wc.openInventory(azerot.getInventorys().get("InvForItemsInMob: " + id));
            }
        }



        if(e.getClickedInventory().equals(azerot.getInventorys().get("InvMobsInSecondPatch"))){
            if(e.getSlot() <= 44){
                if(!clicked.getItemMeta().getDisplayName().equals("§r")){
                    int id = 27 + e.getSlot() + 1;
                    wc.openInventory(azerot.getInventorys().get("InvForItemsInMob: " + id));
                }
            }

            if(clicked.getItemMeta().getDisplayName().equals("§5Информация о всех предметах данного дополнения")){
                wc.openInventory(azerot.getInventorys().get("InvItemsForSecondPatch: 1"));
            }
            if(clicked.getItemMeta().getDisplayName().equals("§6Выйти")){
                wc.openInventory(azerot.getInventorys().get("InvPatchSelection"));
            }
        }
        if(e.getClickedInventory().equals(azerot.getInventorys().get("InvItemsForSecondPatch: 1"))){
            if (e.getSlot() <= 44){
                int id = ItemStat.getID(e.getCurrentItem());
                wc.openInventory(azerot.getInventorys().get("InvForItemInMobs: " + id));
            }
            if(clicked.getItemMeta().getDisplayName().equals("§7§lФильтр предметов")){
                wc.openInventory(azerot.getInventorys().get("typeOneSecondPatch: 1"));
            }
            if(clicked.getItemMeta().getDisplayName().equals("§6Выйти")){
                wc.openInventory(azerot.getInventorys().get("InvMobsInSecondPatch"));
            }
            if(clicked.getItemMeta().getDisplayName().equals("§6--->")){
                wc.openInventory(azerot.getInventorys().get("InvItemsForSecondPatch: 2"));
            }
        }


        if(e.getClickedInventory().equals(azerot.getInventorys().get("InvItemsForSecondPatch: 2"))){
            if (e.getSlot() <= 44){
                int id = ItemStat.getID(e.getCurrentItem());
                wc.openInventory(azerot.getInventorys().get("InvForItemInMobs: " + id));
            }
            if(clicked.getItemMeta().getDisplayName().equals("§6<---")){
                wc.openInventory(azerot.getInventorys().get("InvItemsForSecondPatch: 1"));
            }
            if(clicked.getItemMeta().getDisplayName().equals("§6--->")){
                wc.openInventory(azerot.getInventorys().get("InvItemsForSecondPatch: 3"));
            }
        }
        if(e.getClickedInventory().equals(azerot.getInventorys().get("InvItemsForSecondPatch: 3"))){
            if (e.getSlot() <= 44){
                int id = ItemStat.getID(e.getCurrentItem());
                wc.openInventory(azerot.getInventorys().get("InvForItemInMobs: " + id));
            }
            if(clicked.getItemMeta().getDisplayName().equals("§6<---")){
                wc.openInventory(azerot.getInventorys().get("InvItemsForSecondPatch: 2"));
            }
            if(clicked.getItemMeta().getDisplayName().equals("§6--->")){
                wc.openInventory(azerot.getInventorys().get("InvItemsForSecondPatch: 4"));
            }

        }
        if(e.getClickedInventory().equals(azerot.getInventorys().get("InvItemsForSecondPatch: 4"))){
            if (e.getSlot() <= 44){
                int id = ItemStat.getID(e.getCurrentItem());
                wc.openInventory(azerot.getInventorys().get("InvForItemInMobs: " + id));
            }
            if(clicked.getItemMeta().getDisplayName().equals("§6<---")){
                wc.openInventory(azerot.getInventorys().get("InvItemsForSecondPatch: 3"));
            }
            if(clicked.getItemMeta().getDisplayName().equals("§6--->")){
                wc.openInventory(azerot.getInventorys().get("InvItemsForSecondPatch: 5"));
            }
        }
        if(e.getClickedInventory().equals(azerot.getInventorys().get("InvItemsForSecondPatch: 5"))){
            if (e.getSlot() <= 44){
                int id = ItemStat.getID(e.getCurrentItem());
                wc.openInventory(azerot.getInventorys().get("InvForItemInMobs: " + id));
            }
            if(clicked.getItemMeta().getDisplayName().equals("§6<---")){
                wc.openInventory(azerot.getInventorys().get("InvItemsForSecondPatch: 4"));
            }
        }

        if(e.getClickedInventory().equals(azerot.getInventorys().get("typeOneSecondPatch: 1"))){
            if(clicked.getItemMeta().getDisplayName().equals("§7§lФильтр предметов")){
                wc.openInventory(azerot.getInventorys().get("typeTwoSecondPatch: 1"));
            }
            if(clicked.getItemMeta().getDisplayName().equals("§6Выйти")){
                wc.openInventory(azerot.getInventorys().get("InvMobsInSecondPatch"));
            }
            if(clicked.getItemMeta().getDisplayName().equals("§6--->")){
                wc.openInventory(azerot.getInventorys().get("typeOneSecondPatch: 2"));
            }
        }
        if(e.getClickedInventory().equals(azerot.getInventorys().get("typeTwoSecondPatch: 1"))){
            if(clicked.getItemMeta().getDisplayName().equals("§7§lФильтр предметов")){
                wc.openInventory(azerot.getInventorys().get("typeThreeSecondPatch: 1"));
            }
            if(clicked.getItemMeta().getDisplayName().equals("§6Выйти")){
                wc.openInventory(azerot.getInventorys().get("InvMobsInSecondPatch"));
            }
            if(clicked.getItemMeta().getDisplayName().equals("§6--->")){
                wc.openInventory(azerot.getInventorys().get("typeTwoSecondPatch: 2"));
            }
        }
        if(e.getClickedInventory().equals(azerot.getInventorys().get("typeThreeSecondPatch: 1"))){
            if(clicked.getItemMeta().getDisplayName().equals("§7§lФильтр предметов")){
                wc.openInventory(azerot.getInventorys().get("InvItemsForSecondPatch: 1"));
            }
            if(clicked.getItemMeta().getDisplayName().equals("§6Выйти")){
                wc.openInventory(azerot.getInventorys().get("InvMobsInFirstPatch"));
            }
            if(clicked.getItemMeta().getDisplayName().equals("§6--->")){
                wc.openInventory(azerot.getInventorys().get("typeThreeSecondPatch: 2"));
            }
        }

        if(e.getClickedInventory().equals(azerot.getInventorys().get("typeOneSecondPatch: 2"))){
            if(clicked.getItemMeta().getDisplayName().equals("§6<---")){
                wc.openInventory(azerot.getInventorys().get("typeOneSecondPatch: 1"));
            }
        }
        if(e.getClickedInventory().equals(azerot.getInventorys().get("typeTwoSecondPatch: 2"))){
            if(clicked.getItemMeta().getDisplayName().equals("§6<---")){
                wc.openInventory(azerot.getInventorys().get("typeTwoSecondPatch: 1"));
            }
        }
        if(e.getClickedInventory().equals(azerot.getInventorys().get("typeThreeSecondPatch: 2"))){
            if(clicked.getItemMeta().getDisplayName().equals("§6<---")){
                wc.openInventory(azerot.getInventorys().get("typeThreeSecondPatch: 1"));
            }
        }

        if(e.getClickedInventory().equals(azerot.getInventorys().get("InvMobsInThirdPatch"))){
            if(clicked.getItemMeta().getDisplayName().equals("§5Информация о всех предметах данного дополнения")){
                wc.openInventory(azerot.getInventorys().get("InvItemsForThirdPatch: 1"));
            }
            if(clicked.getItemMeta().getDisplayName().equals("§6Выйти")){
                wc.openInventory(azerot.getInventorys().get("InvPatchSelection"));
            }
        }

        if(e.getClickedInventory().equals(azerot.getInventorys().get("InvItemsForThirdPatch: 1"))){
            if(clicked.getItemMeta().getDisplayName().equals("§7§lФильтр предметов")){
                wc.openInventory(azerot.getInventorys().get("typeOneThirdPatch"));
            }
            if(clicked.getItemMeta().getDisplayName().equals("§6Выйти")){
                wc.openInventory(azerot.getInventorys().get("InvMobsInThirdPatch"));
            }
            if(clicked.getItemMeta().getDisplayName().equals("§6--->")){
                wc.openInventory(azerot.getInventorys().get("InvItemsForThirdPatch: 2"));
            }
        }

        if(e.getClickedInventory().equals(azerot.getInventorys().get("InvItemsForThirdPatch: 2"))){
            if(clicked.getItemMeta().getDisplayName().equals("§6<---")){
                wc.openInventory(azerot.getInventorys().get("InvItemsForThirdPatch: 1"));
            }
            if(clicked.getItemMeta().getDisplayName().equals("§6--->")){
                wc.openInventory(azerot.getInventorys().get("InvItemsForThirdPatch: 3"));
            }
        }
        if(e.getClickedInventory().equals(azerot.getInventorys().get("InvItemsForThirdPatch: 3"))){
            if(clicked.getItemMeta().getDisplayName().equals("§6<---")){
                wc.openInventory(azerot.getInventorys().get("InvItemsForThirdPatch: 2"));
            }

        }

    }
}
