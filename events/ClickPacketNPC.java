package azerot.azerot.events;

import azerot.azerot.GenerateInventory;
import azerot.azerot.Quest.Quest;
import azerot.azerot.Quest.questScript;
import azerot.azerot.WorldUtils;
import azerot.azerot.azerot;
import azerot.azerot.questsScripts.ThreePatch;
import azerot.azerot.rpgplayer;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.StructureModifier;
import com.comphenix.protocol.wrappers.EnumWrappers;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;
import java.util.Objects;

public class ClickPacketNPC {

    private final azerot plugin;

    public ClickPacketNPC(azerot plugin) {
        this.plugin = plugin;
    }

    public void onPacketClick(){
        azerot.getProtocolManager().addPacketListener(new PacketAdapter(plugin, ListenerPriority.NORMAL, PacketType.Play.Client.USE_ENTITY){
            public void onPacketReceiving(PacketEvent event){
                if(event.getPacketType() == PacketType.Play.Client.USE_ENTITY){
                    boolean boo = false;

                        PacketContainer packet = event.getPacket();
                        rpgplayer p = rpgplayer.getRPGPlayer(event.getPlayer());
                        StructureModifier<EnumWrappers.EntityUseAction> entityUseAction = packet.getEntityUseActions();
                        EnumWrappers.EntityUseAction action = entityUseAction.read(0);
                        if(action == EnumWrappers.EntityUseAction.ATTACK || action == EnumWrappers.EntityUseAction.INTERACT || action == EnumWrappers.EntityUseAction.INTERACT_AT) {
                            if(p.getSpawnFakeNPC().get(0) != null && p.getSpawnFakeNPC().get(0).getBukkitEntity().getEntityId() == packet.getIntegers().read(0)){
                                if(!p.isClicknpc()){
                                    p.toPlayer().openInventory(azerot.getInventorys().get("invTraining"));
                                    p.setClicknpc(true);
                                    setFalse(p);
                                    return;
                                }
                            }
                            if(p.getSpawnFakeNPC().get(2) != null && p.getSpawnFakeNPC().get(2).getBukkitEntity().getEntityId() == packet.getIntegers().read(0)){
                                if(!p.isClicknpc()){
                                    if(p.getSpawnFakeNPC().get(2).getBukkitEntity().getLocation().distance(new Location(p.getPlayer().getWorld(), -4009, 66, 1941)) <= 3){
                                        p.toPlayer().openInventory(azerot.getInventorys().get("invShop: 1"));
                                    }
                                    if(p.getSpawnFakeNPC().get(2).getBukkitEntity().getLocation().distance(new Location(p.getPlayer().getWorld(), -3866, 67, 2746)) <= 3){
                                        p.toPlayer().openInventory(azerot.getInventorys().get("invShop: 2"));
                                    }
                                    if(p.getSpawnFakeNPC().get(2).getBukkitEntity().getLocation().distance(new Location(p.getPlayer().getWorld(), -4160, 161, 2899)) <= 3){
                                        p.toPlayer().openInventory(azerot.getInventorys().get("invShop: 3"));
                                    }

                                    p.setClicknpc(true);
                                    setFalse(p);
                                    return;
                                }
                            }
                            if(p.getSpawnFakeNPC().get(4) != null && p.getSpawnFakeNPC().get(4).getBukkitEntity().getEntityId() == packet.getIntegers().read(0)){
                                if(!p.isClicknpc()){
                                    p.toPlayer().openInventory(GenerateInventory.sell());
                                    p.setClicknpc(true);
                                    setFalse(p);
                                    return;
                                }
                            }
                            if(p.getSpawnFakeNPC().get(1) != null && p.getSpawnFakeNPC().get(1).getBukkitEntity().getEntityId() == packet.getIntegers().read(0)){
                                if(!p.isClicknpc()){
                                    if (p.getQuests().get("1") == 1 && p.getQuestsStage().get("1") == 2){
                                        try {
                                            questScript.ScriptsQuest(p.toPlayer(), "1","0", true, 1);
                                        } catch (SQLException e) {
                                            throw new RuntimeException(e);
                                        }

                                    }
                                    p.setClicknpc(true);
                                    setFalse(p);
                                    return;
                                }
                            }
                            if(p.getSpawnFakeNPC().get(3) != null && p.getSpawnFakeNPC().get(3).getBukkitEntity().getEntityId() == packet.getIntegers().read(0)){
                                if(!p.isClicknpc()){
                                    if (p.getQuests().get("1") == 1 && p.getQuestsStage().get("1") == 5){
                                        try {
                                            questScript.ScriptsQuest(p.toPlayer(), "1","0", true, 3);
                                        } catch (SQLException e) {
                                            throw new RuntimeException(e);
                                        }

                                    }
                                    p.setClicknpc(true);
                                    setFalse(p);
                                    return;
                                }
                            }
                            if(p.getSpawnFakeNPC().get(7) != null && p.getSpawnFakeNPC().get(7).getBukkitEntity().getEntityId() == packet.getIntegers().read(0)){
                                if(!p.isClicknpc()){
                                    if (p.getQuests().get("1") == 1 || p.getQuestsStage().get("1") == 12 || p.getQuestsStage().get("1") == 20){
                                        try {
                                            questScript.ScriptsQuest(p.toPlayer(), "1","0", true, 7);
                                        } catch (SQLException e) {
                                            throw new RuntimeException(e);
                                        }

                                    }
                                    p.setClicknpc(true);
                                    setFalse(p);
                                    return;
                                }
                            }
                            if(p.getSpawnFakeNPC().get(5) != null && p.getSpawnFakeNPC().get(5).getBukkitEntity().getEntityId() == packet.getIntegers().read(0)){
                                if(!p.isClicknpc()){
                                    if(p.getLevel() >= 10){
                                        p.toPlayer().teleport(new Location(p.toPlayer().getWorld(), -3884,68,2700,2.2f,3.0f));

                                    }
                                    p.setClicknpc(true);
                                    setFalse(p);
                                    return;
                                }
                            }
                            if(p.getSpawnFakeNPC().get(10) != null && p.getSpawnFakeNPC().get(10).getBukkitEntity().getEntityId() == packet.getIntegers().read(0)){
                                if(!p.isClicknpc()){
                                    if(p.getLevel() >= 10){
                                        p.toPlayer().teleport(new Location(p.toPlayer().getWorld(), -4026,68,1885,1.4f,-1.6f));

                                    }
                                    p.setClicknpc(true);
                                    setFalse(p);
                                    return;
                                }
                            }

                            if(p.getSpawnFakeNPC().get(8) != null && p.getSpawnFakeNPC().get(8).getBukkitEntity().getEntityId() == packet.getIntegers().read(0)){
                                if(!p.isClicknpc()){
                                    if (p.getQuests().get("2") == 1 || p.getQuestsStage().get("2") == 2 || p.getQuestsStage().get("2") == 12){
                                        try {
                                            questScript.ScriptsQuest(p.toPlayer(), "2","0", true, 8);
                                        } catch (SQLException e) {
                                            throw new RuntimeException(e);
                                        }

                                    }

                                    p.setClicknpc(true);
                                    setFalse(p);
                                    return;
                                }
                            }

                            if(p.getSpawnFakeNPC().get(9) != null && p.getSpawnFakeNPC().get(9).getBukkitEntity().getEntityId() == packet.getIntegers().read(0)){
                                if(!p.isClicknpc()){
                                    String idlist = "3";
                                    if (p.getStatusQuest(idlist) == 3)
                                        p.updateQuest(idlist);
                                    if (p.getAvailableQuest(idlist) && !Quest.getQuest(idlist).isLock(p)) {
                                        p.getDialogueBetweenNPC().putIfAbsent(idlist, Integer.valueOf(0));
                                        p.toPlayer().sendMessage("§7[" + (((Integer)p.getDialogueBetweenNPC().get(idlist)).intValue() + 1) + "/" + ((Quest)Quest.quests.get(idlist)).getDialogue().size() + "] " + (String)((Quest)Quest.quests.get(idlist)).getDialogue().get(p.getDialogueBetweenNPC().get(idlist)));
                                        p.getDialogueBetweenNPC().put(idlist, Integer.valueOf(((Integer)p.getDialogueBetweenNPC().get(idlist)).intValue() + 1));
                                        if (((Integer)p.getDialogueBetweenNPC().get(idlist)).intValue() == ((Quest)Quest.quests.get(idlist)).getDialogue().size()) {
                                            try {
                                                p.takeQuest(idlist);
                                            } catch (SQLException e) {
                                                throw new RuntimeException(e);
                                            }
                                        }
                                    }
                                    p.setClicknpc(true);
                                    setFalse(p);
                                    return;
                                }
                            }

                            if(p.getSpawnFakeNPC().get(11) != null && p.getSpawnFakeNPC().get(11).getBukkitEntity().getEntityId() == packet.getIntegers().read(0)){
                                if(!p.isClicknpc()){

                                    if (p.getQuestsStage().get("4") != null || p.getQuestsStage().get("4") == 1 || p.getQuestsStage().get("4") == 6  || p.getQuestsStage().get("4") == 10){
                                        try {
                                            questScript.ScriptsQuest(p.toPlayer(), "4","0", true, 11);
                                        } catch (SQLException e) {
                                            throw new RuntimeException(e);
                                        }

                                    }
                                    p.setClicknpc(true);
                                    setFalse(p);
                                    return;
                                }
                            }

                            if(p.getSpawnFakeNPC().get(12) != null && p.getSpawnFakeNPC().get(12).getBukkitEntity().getEntityId() == packet.getIntegers().read(0)){
                                if(!p.isClicknpc()){
                                    if (p.getQuestsStage().get("4") == 15){
                                        try {
                                            questScript.ScriptsQuest(p.toPlayer(), "4","0", true, 12);
                                        } catch (SQLException e) {
                                            throw new RuntimeException(e);
                                        }

                                    }else{
                                        String idlist = "5";
                                        if (p.getStatusQuest(idlist) == 3){
                                            p.updateQuest(idlist);
                                        }
                                        if (p.getAvailableQuest(idlist) && !Objects.requireNonNull(Quest.getQuest(idlist)).isLock(p)) {
                                            p.getDialogueBetweenNPC().putIfAbsent(idlist, 0);
                                            p.toPlayer().sendMessage((String)((Quest)Quest.quests.get(idlist)).getDialogue().get(p.getDialogueBetweenNPC().get(idlist)));
                                            p.getDialogueBetweenNPC().put("5", p.getDialogueBetweenNPC().get(idlist) + 1);
                                            if ((Integer) p.getDialogueBetweenNPC().get(idlist) == ((Quest)Quest.quests.get(idlist)).getDialogue().size()) {
                                                try {
                                                    p.takeQuest(idlist);
                                                } catch (SQLException e) {
                                                    throw new RuntimeException(e);
                                                }
                                            }
                                        }
                                    }
                                    p.setClicknpc(true);
                                    setFalse(p);
                                    return;
                                }
                            }

                            if(p.getSpawnFakeNPC().get(13) != null && p.getSpawnFakeNPC().get(13).getBukkitEntity().getEntityId() == packet.getIntegers().read(0)){
                                if(!p.isClicknpc()){
                                    if (p.getQuests().get("5") == 1 && p.getQuestsStage().get("5") == 2){
                                        try {
                                            questScript.ScriptsQuest(p.toPlayer(), "5","0", true, 13);
                                        } catch (SQLException e) {
                                            throw new RuntimeException(e);
                                        }

                                    }
                                    if (p.getQuests().get("5") == 1 && p.getQuestsStage().get("5") == 4){
                                        try {
                                            questScript.ScriptsQuest(p.toPlayer(), "5","0", true, 13);
                                        } catch (SQLException e) {
                                            throw new RuntimeException(e);
                                        }

                                    }
                                    p.setClicknpc(true);
                                    setFalse(p);
                                    return;
                                }
                            }

                            if(p.getSpawnFakeNPC().get(14) != null && p.getSpawnFakeNPC().get(14).getBukkitEntity().getEntityId() == packet.getIntegers().read(0)){
                                if(!p.isClicknpc()){
                                    if (p.getQuestsStage().get("5") != null && p.getQuests().get("5") == 1 && p.getQuestsStage().get("5") == 5){
                                        try {
                                            questScript.ScriptsQuest(p.toPlayer(), "5","0", true, 14);
                                        } catch (SQLException e) {
                                            throw new RuntimeException(e);
                                        }

                                    }
                                    if (p.getQuestsStage().get("8") != null && p.getQuests().get("8") == 1 && p.getQuestsStage().get("8") == 9){
                                        try {
                                            questScript.ScriptsQuest(p.toPlayer(), "8","0", true, 14);
                                        } catch (SQLException e) {
                                            throw new RuntimeException(e);
                                        }

                                    }
                                    String idlist = "8";
                                    if (p.getStatusQuest(idlist) == 3){
                                        p.updateQuest(idlist);
                                    }
                                    if (p.getAvailableQuest(idlist) && !Objects.requireNonNull(Quest.getQuest(idlist)).isLock(p)) {
                                        p.getDialogueBetweenNPC().putIfAbsent(idlist, 0);
                                        p.toPlayer().sendMessage((String)((Quest)Quest.quests.get(idlist)).getDialogue().get(p.getDialogueBetweenNPC().get(idlist)));
                                        p.getDialogueBetweenNPC().put("8", p.getDialogueBetweenNPC().get(idlist) + 1);
                                        if ((Integer) p.getDialogueBetweenNPC().get(idlist) == ((Quest)Quest.quests.get(idlist)).getDialogue().size()) {
                                            try {
                                                p.takeQuest(idlist);
                                            } catch (SQLException e) {
                                                throw new RuntimeException(e);
                                            }
                                        }
                                    }

                                    p.setClicknpc(true);
                                    setFalse(p);
                                    return;
                                }
                            }

                            if(p.getSpawnFakeNPC().get(15) != null && p.getSpawnFakeNPC().get(15).getBukkitEntity().getEntityId() == packet.getIntegers().read(0)){
                                if(!p.isClicknpc()){
                                    if(p.getQuestsStage().get("5") != null){
                                        if (p.getQuestsStage().get("5") == 6 || p.getQuestsStage().get("5") == 13){
                                            try {
                                                questScript.ScriptsQuest(p.toPlayer(), "5","0", true, 15);
                                            } catch (SQLException e) {
                                                throw new RuntimeException(e);
                                            }
                                        }
                                    }
                                    p.setClicknpc(true);
                                    setFalse(p);
                                    return;
                                }
                            }

                            if(p.getSpawnFakeNPC().get(17) != null && p.getSpawnFakeNPC().get(17).getBukkitEntity().getEntityId() == packet.getIntegers().read(0)){
                                if(!p.isClicknpc()){
                                    if(p.getQuestsStage().get("5") != null){
                                        if (p.getQuestsStage().get("5") == 7 || p.getQuestsStage().get("5") == 14 || p.getQuestsStage().get("5") == 15){
                                            try {
                                                questScript.ScriptsQuest(p.toPlayer(), "5","0", true, 17);
                                            } catch (SQLException e) {
                                                throw new RuntimeException(e);
                                            }

                                        }
                                    }

                                    String idlist = "6";
                                    if (p.getStatusQuest(idlist) == 3){
                                        p.updateQuest(idlist);
                                    }
                                    if (p.getAvailableQuest(idlist) && !Objects.requireNonNull(Quest.getQuest(idlist)).isLock(p)) {
                                        p.getDialogueBetweenNPC().putIfAbsent(idlist, 0);
                                        p.toPlayer().sendMessage((String)((Quest)Quest.quests.get(idlist)).getDialogue().get(p.getDialogueBetweenNPC().get(idlist)));
                                        p.getDialogueBetweenNPC().put("6", p.getDialogueBetweenNPC().get(idlist) + 1);
                                        if ((Integer) p.getDialogueBetweenNPC().get(idlist) == ((Quest)Quest.quests.get(idlist)).getDialogue().size()) {
                                            try {
                                                p.takeQuest(idlist);
                                            } catch (SQLException e) {
                                                throw new RuntimeException(e);
                                            }
                                        }
                                    }
                                    p.setClicknpc(true);
                                    setFalse(p);
                                    return;
                                }
                            }

                            if(p.getSpawnFakeNPC().get(20) != null && p.getSpawnFakeNPC().get(20).getBukkitEntity().getEntityId() == packet.getIntegers().read(0)){
                                if(!p.isClicknpc()){
                                    String idlist = "9";
                                    if (p.getStatusQuest(idlist) == 3)
                                        p.updateQuest(idlist);
                                    if (p.getAvailableQuest(idlist) && !Quest.getQuest(idlist).isLock(p)) {
                                        p.getDialogueBetweenNPC().putIfAbsent(idlist, Integer.valueOf(0));
                                        p.toPlayer().sendMessage("§7[" + (((Integer)p.getDialogueBetweenNPC().get(idlist)).intValue() + 1) + "/" + ((Quest)Quest.quests.get(idlist)).getDialogue().size() + "] " + (String)((Quest)Quest.quests.get(idlist)).getDialogue().get(p.getDialogueBetweenNPC().get(idlist)));
                                        p.getDialogueBetweenNPC().put(idlist, Integer.valueOf(((Integer)p.getDialogueBetweenNPC().get(idlist)).intValue() + 1));
                                        if (((Integer)p.getDialogueBetweenNPC().get(idlist)).intValue() == ((Quest)Quest.quests.get(idlist)).getDialogue().size()) {
                                            try {
                                                p.takeQuest(idlist);
                                            } catch (SQLException e) {
                                                throw new RuntimeException(e);
                                            }
                                        }
                                    }
                                    p.setClicknpc(true);
                                    setFalse(p);
                                    return;
                                }
                            }

                            if(p.getSpawnFakeNPC().get(25) != null && p.getSpawnFakeNPC().get(25).getBukkitEntity().getEntityId() == packet.getIntegers().read(0)){
                                if(!p.isClicknpc()){
                                    if(p.getQuestsStage().get("9") != null){
                                        if (p.getQuestsStage().get("9") == 4 || p.getQuestsStage().get("9") == 5 || p.getQuestsStage().get("9") == 6){
                                            try {
                                                ThreePatch.ScriptsForThreePatch(p.toPlayer(), "9","0", true, 25);
                                            } catch (SQLException e) {
                                                throw new RuntimeException(e);
                                            }
                                        }
                                    }
                                    p.setClicknpc(true);
                                    setFalse(p);
                                    return;
                                }
                            }

                        }

                }
            }
        });
    }

    public void setFalse(rpgplayer p){
        (new BukkitRunnable() {
            public void run() {
                p.setClicknpc(false);
            }
        }).runTaskLater((Plugin)azerot.getInstance(), 5L);
    }

}
