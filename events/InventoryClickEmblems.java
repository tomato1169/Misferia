package azerot.azerot.events;

import azerot.azerot.GenerateInventory;
import azerot.azerot.rpgplayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickEmblems implements Listener {
    @EventHandler
    public void Click(InventoryClickEvent e){
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


        if(e.getClickedInventory().getName().equals("§5Эмблемы: " + pl.getSkillpoint() + " очков!")){
            if(clicked.getItemMeta().getDisplayName().equals("§4Эмблема: Второй шанс")){
                if(e.getClick().isRightClick()){
                    pl.pIncreaseTalents("SecondChance");
                }
                if(e.getClick().isLeftClick()){
                    pl.pActivateTalents("SecondChance");
                }
            }
            if(clicked.getItemMeta().getDisplayName().equals("§7Эмблема: Солнце и луна")){
                if(e.getClick().isRightClick()){
                    pl.pIncreaseTalents("SunAndMoon");
                }
                if(e.getClick().isLeftClick()){
                    pl.pActivateTalents("SunAndMoon");
                }
            }
            if(clicked.getItemMeta().getDisplayName().equals("§2Эмблема: Магическая основа")){
                if(e.getClick().isRightClick()){
                    pl.pIncreaseTalents("Magicalfoundation");
                }
                if(e.getClick().isLeftClick()){
                    pl.pActivateTalents("Magicalfoundation");
                }
            }
            if(clicked.getItemMeta().getDisplayName().equals("§2Эмблема: Инстинкт выживания")){
                if(e.getClick().isRightClick()){
                    pl.pIncreaseTalents("Survivalinstinct");
                }
                if(e.getClick().isLeftClick()){
                    pl.pActivateTalents("Survivalinstinct");
                }
            }
            wc.openInventory(GenerateInventory.Emblebs(pl));
        }
    }
}
