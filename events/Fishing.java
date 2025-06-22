package azerot.azerot.events;

import azerot.azerot.RPGItem;
import azerot.azerot.rpgplayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

public class Fishing implements Listener {
    @EventHandler
    public void onFishing(PlayerFishEvent e){
        e.setExpToDrop(0);

        Player p = e.getPlayer();
        rpgplayer rp = rpgplayer.getRPGPlayer(p);
        ItemStack item = new RPGItem().getRPGItem(23, 0, 1,  0, 0, 0,0, 0,null);
        if(e.getCaught() != null) {
            if (e.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
                e.getPlayer().getInventory().addItem(new ItemStack[]{(new RPGItem()).getRPGItem(23, 0, 1, 0, 0, 0,0, 0, rp)});
                e.getHook().remove();
                e.getCaught().remove();
                Bukkit.broadcastMessage("жд");
                return;
            }
        }
      if(e.getState() == PlayerFishEvent.State.CAUGHT_ENTITY){
          e.setCancelled(true);

      }
    }

}
