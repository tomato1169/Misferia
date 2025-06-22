package azerot.azerot.events;

import azerot.azerot.rpgplayer;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class RegionEnterEvent implements Listener {
    private BossBar bar = Bukkit.getServer().createBossBar("", BarColor.GREEN, BarStyle.SOLID);
}
