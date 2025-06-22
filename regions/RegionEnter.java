package azerot.azerot.regions;

import azerot.azerot.Cutscene.DeadIsland;
import azerot.azerot.Cutscene.initial;
import azerot.azerot.Quest.Quest;
import azerot.azerot.Quest.questScript;
import azerot.azerot.WorldUtils;
import azerot.azerot.azerot;
import azerot.azerot.npcSpawnForRegion.npcForJapan;
import azerot.azerot.npcSpawnForRegion.npcInitialVillage;
import azerot.azerot.questsScripts.ThreePatch;
import azerot.azerot.rpgplayer;
import com.comphenix.packetwrapper.WrapperPlayServerEntityHeadRotation;
import com.comphenix.packetwrapper.WrapperPlayServerEntityMetadata;
import com.comphenix.packetwrapper.WrapperPlayServerNamedEntitySpawn;
import com.comphenix.packetwrapper.WrapperPlayServerSpawnEntityLiving;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import com.mewin.WGRegionEvents.events.RegionEnterEvent;
import com.mewin.WGRegionEvents.events.RegionEnteredEvent;
import com.mewin.WGRegionEvents.events.RegionLeaveEvent;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;
import java.util.Random;
import java.util.UUID;

public class RegionEnter implements Listener {
    @EventHandler
    public void onRegionEnter(RegionEnterEvent e) throws SQLException {
        (new BukkitRunnable() {
            public void run() {
                rpgplayer p = rpgplayer.getRPGPlayer(e.getPlayer());
                if(p == null) return;

                if (e.getRegion().getId().contains("rpgzone"))
                    return;
                if (e.getRegion().getId().equals("triggerone")) {
                    if(p.getQuestsStage().get("1") == null && p.getQuests().get("1") == 3){
                            try {
                                p.takeQuest("1");
                            } catch (SQLException ep) {
                                throw new RuntimeException(ep);
                            }
                    }
                }
                if (e.getRegion().getId().equals("startf")) {
                    npcInitialVillage.spawnSkillF(e.getPlayer());
                }
                if (e.getRegion().getId().equals("triggertwo")) {
                    if(p.getQuestsStage().get("2") == null && !Quest.getQuest("2").isLock(p) && p.getQuests().get("2") == 3){
                            try {
                                p.takeQuest("2");
                            } catch (SQLException ep) {
                                throw new RuntimeException(ep);
                        }
                    }
                }
                if(e.getRegion().getId().equals("triggerthree")){
                    if(p.getQuests().get("5") == 1){
                        if(p.getQuestsStage().get("5") == 1){
                            p.getDialogueBetweenNPC().put("5-1", 1);
                            p.getQuestsStage().put("5", 2);
                            npcInitialVillage.spawnElai(e.getPlayer());
                        }
                    }
                }
                if(e.getRegion().getId().equals("housealchemist")){
                    if(p.getQuestsStage().get("6") != null && p.getQuestsStage().get("6") < 29){
                        npcInitialVillage.spawnAlchemist(e.getPlayer());
                    } else if (p.getQuestsStage().get("5") != null) {
                        npcInitialVillage.spawnAlchemist(e.getPlayer());
                    }
                    if(p.getQuestsStage().get("6") != null && p.getQuestsStage().get("6") == 29){
                        p.getQuestsStage().put("6", 30);
                        try {
                            questScript.ScriptsQuest(p.toPlayer(), "6", "0", false, 0);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
                if(e.getRegion().getId().equals("region1")){
                    p.setSaveZone(1);
                    npcInitialVillage.spawn(p.toPlayer());
                    p.toPlayer().sendTitle("§6Деревня Гинс!", "§6Безопасная зона", 10, 70, 20);
                }
                if(e.getRegion().getId().equals("quest6-3")){
                    if(p.getQuestsStage().get("5") != null && p.getQuestsStage().get("5") == 4){
                        WorldServer s = ((CraftWorld)p.toPlayer().getWorld()).getHandle();
                        EntityZombie elijah = new EntityZombie(s);
                        elijah.setLocation(-4455, 105, 3282, -42F,0F);
                        elijah.setCustomNameVisible(true);
                        elijah.setCustomName("§6Элайя");
                        elijah.setInvisible(false);
                        elijah.getBukkitEntity().setGlowing(true);
                        PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving((EntityLiving) elijah);
                        ((CraftPlayer)p.toPlayer()).getHandle().playerConnection.sendPacket(packet);
                        p.getSpawnFakeNPC().put(13, elijah);
                    }
                }

                if (e.getRegion().getId().equals("region2")) {
                    p.setSaveZone(1);
                    if(p.getQuestsStage().get("1") != null && p.getQuestsStage().get("1") == 3){
                        p.getQuestsStage().put("1", 4);
                        npcInitialVillage.spawnGenry(e.getPlayer());
                        try {
                            questScript.ScriptsQuest(p.toPlayer(), "1","0", false, 0);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
                if (e.getRegion().getId().equals("region3")) {
                    p.setSaveZone(1);

                    npcInitialVillage.spawnRegion3(p.toPlayer());

                    if(p.getQuestsStage().get("1") != null && p.getQuestsStage().get("1") == 9){
                        p.getQuestsStage().put("1", 10);
                        try {
                            questScript.ScriptsQuest(p.toPlayer(), "1","0", false, 0);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }

                if (e.getRegion().getId().equals("region4")) {
                    p.setSaveZone(1);
                    npcInitialVillage.spawnDeadIsland(p.toPlayer());
                }
                if (e.getRegion().getId().equals("region5")) {
                    p.setSaveZone(1);
                    npcInitialVillage.spawnAnri(p.toPlayer());
                    if(p.getQuestsStage().get("4") != null && p.getQuestsStage().get("4") == 1 && p.getDialogueBetweenNPC().get("4.1") == 1){
                        try {
                            questScript.ScriptsQuest(e.getPlayer(), "4", "0", true, 11);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }

                    }
                }

                if(e.getRegion().getId().equals("saveofanri")){
                    if(p.getQuestsStage().get("4") != null &&  p.getQuestsStage().get("4") >= 15){
                        p.setSaveZone(1);
                        npcInitialVillage.spawnSaveAnri(e.getPlayer());
                    }
                }
                if(e.getRegion().getId().equals("region6")){
                    p.setSaveZone(1);
                    npcInitialVillage.spawnGrotus(e.getPlayer());
                }

                if(e.getRegion().getId().equals("startvillage")){
                    p.setSaveZone(1);
                    npcForJapan.spawnVillager(p.toPlayer());
                    if(p.getQuestsStage().get("9") == 7){
                        p.getQuestsStage().put("9",8);
                        try {
                            ThreePatch.ScriptsForThreePatch(p.toPlayer(),"9","0",false,0);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }


            }
        }).runTaskLater((Plugin)azerot.getInstance(), 5);
    }
    @EventHandler
    public void onRegionLeave(RegionLeaveEvent e) throws SQLException {
        rpgplayer pl = rpgplayer.getRPGPlayer(e.getPlayer());
        if (rpgplayer.getRPGPlayer(e.getPlayer()) == null)
            return;
        if(rpgplayer.getRPGPlayer(e.getPlayer()).getSaveZone() == 1){
            rpgplayer.getRPGPlayer(e.getPlayer()).setSaveZone(0);
        }
        if(e.getRegion().getId().equals("startf")){
            if(!pl.getSpawnFakeNameNPC().isEmpty()){
                PacketPlayOutEntityDestroy packDeleteSlime = new PacketPlayOutEntityDestroy(pl.getSpawnFakeEntity().get(1).getBukkitEntity().getEntityId());
                ((CraftPlayer)e.getPlayer()).getHandle().playerConnection.sendPacket(packDeleteSlime);
                for(Integer value : pl.getSpawnFakeNameNPC().keySet()){
                    PacketPlayOutEntityDestroy packDeleteSlime4 = new PacketPlayOutEntityDestroy(pl.getSpawnFakeNameNPC().get(value).getBukkitEntity().getEntityId());
                    ((CraftPlayer)e.getPlayer()).getHandle().playerConnection.sendPacket(packDeleteSlime4);
                }
            }
        }
        if(e.getRegion().getId().equals("region1")){
            npcInitialVillage.deSpawn(e.getPlayer());
        }
        if(e.getRegion().getId().equals("startvillage")){
            pl.setSaveZone(0);
            npcInitialVillage.deSpawn(e.getPlayer());
            pl.getQuestsStage().put("9", 2);
            ThreePatch.ScriptsForThreePatch(pl.toPlayer(),"9","0",false, 0);
        }
        if(e.getRegion().getId().equals("region2")){
            npcInitialVillage.deSpawn(e.getPlayer());
        }
        if(e.getRegion().getId().equals("region3")){
            npcInitialVillage.deSpawn(e.getPlayer());
        }
        if(e.getRegion().getId().equals("region4")){
            npcInitialVillage.deSpawn(e.getPlayer());
        }
        if(e.getRegion().getId().equals("region5")){
            npcInitialVillage.deSpawn(e.getPlayer());
        }
        if(e.getRegion().getId().equals("region6")){
            npcInitialVillage.deSpawn(e.getPlayer());
        }
        if(e.getRegion().getId().equals("triggerthree")){
            if(pl.getQuestsStage().get("5") != null && pl.getQuestsStage().get("5") == 2){
                pl.getQuestsStage().put("5", 1);
            }
            npcInitialVillage.deSpawn(e.getPlayer());
        }
        if(e.getRegion().getId().equals("housealchemist")){
            if(pl.getQuestsStage().get("5") == 8){
                questScript.ScriptsQuest(e.getPlayer(), "5", "0", false, 100000);
            }
            npcInitialVillage.deSpawn(e.getPlayer());
        }
        if(e.getRegion().getId().equals("quest6-3")){
            if(pl.getSpawnFakeNPC().get(13) != null){
                    PacketPlayOutEntityDestroy packDelete1 = new PacketPlayOutEntityDestroy(pl.getSpawnFakeNPC().get(13).getBukkitEntity().getEntityId());
                    ((CraftPlayer) pl.toPlayer()).getHandle().playerConnection.sendPacket(packDelete1);
                    pl.getSpawnFakeNPC().remove(13);
            }
        }
        if(e.getRegion().getId().equals("saveOfAnri")){
            npcInitialVillage.deSpawn(e.getPlayer());
        }
    }
}
