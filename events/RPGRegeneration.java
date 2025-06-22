package azerot.azerot.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public class RPGRegeneration implements Listener {
    @EventHandler
    public void onRegeneration(EntityRegainHealthEvent e) {
        e.setCancelled(true);
    }
}

