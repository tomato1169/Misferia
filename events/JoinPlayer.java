package azerot.azerot.events;


import azerot.azerot.*;
import azerot.azerot.Cutscene.*;
import azerot.azerot.Quest.Quest;
import azerot.azerot.Quest.questScript;
import azerot.azerot.RPGMob.RPGZombie;
import azerot.azerot.enchants.chance;
import com.comphenix.packetwrapper.*;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static org.bukkit.Bukkit.getName;

public class JoinPlayer implements Listener {


    private static final Map<String, Integer> gold = new HashMap<>();


    @EventHandler
    public void join(PlayerJoinEvent e) throws IOException, SQLException {
        e.getPlayer().teleport(new Location(Bukkit.getWorld("world"), -2499, 29, 602));
        e.setJoinMessage(null);
        rpgplayer p = rpgplayer.getRPGPlayer(e.getPlayer());
        e.getPlayer().setMetadata("lastloc", (MetadataValue) new FixedMetadataValue((Plugin) azerot.getInstance(), "region-1"));
        e.getPlayer().setMetadata("savezone", (MetadataValue) new FixedMetadataValue((Plugin) azerot.getInstance(), Integer.valueOf(0)));
        if (!rpgplayer.playershm.containsKey(e.getPlayer().getName())) {
            p = new rpgplayer(e.getPlayer());
        } else {
            p = rpgplayer.getRPGPlayer(e.getPlayer());
            rpgplayer.resetPlayer(e.getPlayer().getName());
        }
        (new RPGItem()).updateItem(e.getPlayer());
        p.resetArmor();
        p.toPlayer().setWalkSpeed(0.2f);
        e.getPlayer().setMetadata("health", (MetadataValue) new FixedMetadataValue((Plugin) azerot.getInstance(), (int) p.getMaxHealth()));
        e.getPlayer().setMetadata("mana", (MetadataValue) new FixedMetadataValue((Plugin) azerot.getInstance(), (int) p.getMaxMana()));
        Cycles.actionbar(e.getPlayer());
        Cycles.regeneration(e.getPlayer());
        p.toPlayer().setGameMode(GameMode.SURVIVAL);
        Cycles.updateScoreBoard(e.getPlayer(), null, null);
        p.toPlayer().sendMessage(WorldUtils.worldName() + " §2Задание \"§6Потаенные§2\" обновилось!");
        p.toPlayer().sendMessage(WorldUtils.worldName() + " §7§oЗадание \"§5Потаенные§7§o\" обновилось!");
        p.toPlayer().sendMessage(WorldUtils.worldName() + " §7Задание \"§5Потаенные§7\" обновилось!");
        p.toPlayer().sendMessage(WorldUtils.worldName() + " §b§oЗадание \"§6Потаенные§b§o\" обновилось!");
        p.toPlayer().sendMessage(WorldUtils.worldName() + " §bЗадание \"§5Потаенные§b\" обновилось!");
        p.toPlayer().sendMessage(WorldUtils.worldName() + " §fЗадание \"§5Потаенные§f\" обновилось!");
        p.toPlayer().sendMessage(WorldUtils.worldName() + " §f§oЗадание \"§5Потаенные§f§o\" обновилось!");


        p.toPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 100, true, false), true);
        if(azerot.donate.get("purchasedXP").equals(true)){
            azerot.getInstance().getBarGlobalXP().addPlayer(e.getPlayer());
        }
        for (Map.Entry<String, Integer> i : p.getQuests().entrySet()){
            String quest = i.getKey();
            int questInfo = i.getValue();
            if(questInfo == 2){
                p.setFinishQuest(p.getFinishQuest() + 1);
            }
        }

        rpgplayer finalP1 = p;
        rpgplayer finalP2 = p;
        (new BukkitRunnable() {
            public void run() {
                for (Map.Entry<String, Integer> i : finalP1.getQuests().entrySet()){
                    String quest = i.getKey();
                    int questInfo = i.getValue();
                    if(questInfo == 1){
                        finalP2.setQuestLook(quest);
                    }
                }
            }
        }).runTaskLater((Plugin)azerot.getInstance(), 20L);

        (new BukkitRunnable() {
            public void run() {
                WorldUtils.updatePlayerTab(e.getPlayer());
            }
        }).runTaskLater((Plugin)azerot.getInstance(), 20L);

        rpgplayer finalP = p;
    }

    }


