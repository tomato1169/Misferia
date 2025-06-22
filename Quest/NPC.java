package azerot.azerot.Quest;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import azerot.azerot.*;
import azerot.azerot.mobs.MobInfo;
import azerot.azerot.mobs.MobInfoManager;
import azerot.azerot.mobs.MobSystem;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class NPC implements Listener {
    File file = new File(azerot.getInstance().getDataFolder() + File.separator + "NPCs.yml");

    FileConfiguration config = (FileConfiguration)YamlConfiguration.loadConfiguration(this.file);





    @EventHandler
    public void clickNPC(PlayerInteractEntityEvent e) throws SQLException {
        int i = 0;
        MobInfo mobInfo = MobInfoManager.getMobInfo(e.getRightClicked());
        ItemStack hand2 = e.getPlayer().getInventory().getItemInMainHand();
        String name = e.getRightClicked().getName();
        rpgplayer pl = rpgplayer.getRPGPlayer(e.getPlayer());
        int id2 = ItemStat.getID(hand2);


        if(e.getRightClicked().getType().equals(EntityType.PLAYER)){
            if(hand2.getType() == Material.AIR) {
                Inventory inv = Bukkit.createInventory(null, 9 * 6, "§7Инвентарь игрока: " + e.getRightClicked().getName());
                Player pp = (Player) e.getRightClicked();
                ItemStack InfoBoss = new ItemStack(Material.NETHER_STAR);
                rpgplayer rpgplayer1 = rpgplayer.getRPGPlayer(pp);
                ItemMeta meta = InfoBoss.getItemMeta();
                List<String> lore = new ArrayList<>();
                if (rpgplayer1.haveMob(String.valueOf(10)) && rpgplayer1.getMob(String.valueOf(10)) > 0) {
                    lore.add("§7" + MobSystem.getName(10) + ": " + rpgplayer1.getMob(String.valueOf(10)));

                }
                meta.setLore(lore);
                InfoBoss.setItemMeta(meta);
                ItemStack hand1 = pp.getInventory().getItemInMainHand();
                ItemStack helmet = pp.getInventory().getHelmet();
                ItemStack armor = pp.getInventory().getChestplate();
                ItemStack leggings = pp.getInventory().getLeggings();
                ItemStack boots = pp.getInventory().getBoots();
                int id1 = ItemStat.getID(hand1);
                inv.setItem(10, hand1);
                inv.setItem(11, helmet);
                inv.setItem(12, armor);
                inv.setItem(13, leggings);
                inv.setItem(14, boots);
                inv.setItem(15, InfoBoss);

                e.getPlayer().openInventory(inv);
            }

        }
        e.setCancelled(true);
    }
    }

