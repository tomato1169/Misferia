package azerot.azerot.Quest;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import azerot.azerot.RPGQuestsContainer;
import azerot.azerot.WorldUtils;
import azerot.azerot.rpgplayer;
import lombok.Data;
import azerot.azerot.azerot;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.Nullable;

@Data
public class Quest {

    File filenpc = new File(azerot.getInstance().getDataFolder() + File.separator + "NPCs.yml");

    FileConfiguration confignpc = (FileConfiguration) YamlConfiguration.loadConfiguration(this.filenpc);


    private String id;

    private int type;


    private int rLvl;

    private String giver;

    private String giverxyz;

    private String taker;

    private String takerxyz;

    private String rQuest;

    private String rInPQuest;

    private String cQuest;

    private HashMap<Integer, Integer> enter_items = new HashMap<>();

    private HashMap<Integer, Integer> items = new HashMap<>();

    private HashMap<Integer, Integer> mobs = new HashMap<>();

    private int xp;

    private String[] teleportcoords;

    private Location teleport;

    private String lore;

    private String tp;

    private String name;

    private HashMap<Integer, Integer> reward = new HashMap<>();

    private HashMap<Integer, String> Dialogue = new HashMap<>();

    public static HashMap<String, Quest> quests = new HashMap<>();

    private String scripttocomplete;

    public Quest(String id) throws SQLException {
        RPGQuestsContainer rpgQuestsContainer = RPGQuestsContainer.getRPGQuestsContainer(Integer.parseInt(id));
        if(rpgQuestsContainer != null) {
            this.id = id;
            this.name = rpgQuestsContainer.getName();
            this.rLvl = rpgQuestsContainer.getRLevel();
            this.rQuest = rpgQuestsContainer.getRQuest();
            this.rInPQuest = rpgQuestsContainer.getRInPQuest();
            this.cQuest = rpgQuestsContainer.getCQuest();
            this.xp = rpgQuestsContainer.getXp();
            this.type = rpgQuestsContainer.getType();
            this.lore = rpgQuestsContainer.getLore();
            this.tp = rpgQuestsContainer.getTeleport();
           if(rpgQuestsContainer.getDialogue() != null){
                String[] dialogue = rpgQuestsContainer.getDialogue().split(";");
                int j = 0;
                for(String i : dialogue){
                    this.Dialogue.put(j, i);
                    j++;
                }

            }
            if(tp !=null){
                teleportcoords = tp.split(" ");
                double x = Double.parseDouble(teleportcoords[0]);
                double y = Double.parseDouble(teleportcoords[1]);
                double z = Double.parseDouble(teleportcoords[2]);
                this.teleport = new Location(Bukkit.getWorld("world"), Integer.parseInt(this.teleportcoords[0]) + 0.5D, Integer.parseInt(this.teleportcoords[1]), Integer.parseInt(this.teleportcoords[2]) + 0.5D);
            }
            if (rpgQuestsContainer.getMobs() != null) {
                        String[] mob = rpgQuestsContainer.getMobs().split(";");
                        for (String ip : mob) {
                            String[] it = ip.split(" ");
                            int MobId = Integer.parseInt(it[0]);
                            int mobAmount = Integer.parseInt(it[1]);
                            this.mobs.put(MobId, mobAmount);
                        }
                    }
            if(rpgQuestsContainer.getItems() != null){
                String[] items = rpgQuestsContainer.getItems().split(";");
                for(String i : items){
                    String[] it = i.split(" ");
                    int idItem = Integer.parseInt(it[0]);
                    int amountItem = Integer.parseInt(it[1]);
                    this.items.put(idItem, amountItem);

                }
            }
            if(rpgQuestsContainer.getEnter_items() != null){
                String[] items = rpgQuestsContainer.getEnter_items().split(";");
                for(String i : items){
                    String[] it = i.split(" ");
                    int idItem = Integer.parseInt(it[0]);
                    int amountItem = Integer.parseInt(it[1]);
                    this.enter_items.put(idItem, amountItem);

                }

            }
            if(rpgQuestsContainer.getReward() != null){
                String[] items = rpgQuestsContainer.getReward().split(";");
                for(String i : items){
                    String[] it = i.split(" ");
                    int idItem = Integer.parseInt(it[0]);
                    int amountItem = Integer.parseInt(it[1]);
                    this.reward.put(idItem, amountItem);

                }

            }
        }
        quests.put(id, this);
    }
    public static Quest getQuest(String id) {
        return quests.containsKey(id) ? quests.get(id) : null;
    }

    public String getName() {
        return this.name;
    }

    public String getGiver() {
        return this.giver;
    }

    public String getGiverXYZ() {
        return this.giverxyz;
    }

    public String getTaker() {
        return this.taker;
    }

    public String getTakerXYZ() {
        return this.takerxyz;
    }

    public int rLvl() {
        return this.rLvl;
    }

    public String getLore() {
        return this.lore;
    }

    public int getXp() {
        return this.xp;
    }

    public Location getTeleport() {
        return this.teleport;
    }

    public int getType() {
        return this.type;
    }

    public String getCQuest() {
        return this.cQuest;
    }

    public HashMap<Integer, Integer> getReward() {
        return this.reward;
    }
    public HashMap<Integer, Integer> getMobs() {
        if (!this.mobs.isEmpty())
            return this.mobs;
        return null;
    }

    public boolean haveMob(String id) {
        if (!this.mobs.isEmpty() &&
                this.mobs.get(Integer.valueOf(Integer.parseInt(id))) != null)
            return true;
        return false;
    }

    public int getMobAmount(String id) {
        if (!this.mobs.isEmpty())
            return ((Integer)this.mobs.get(Integer.valueOf(Integer.parseInt(id)))).intValue();
        return 0;
    }

    public HashMap<Integer, Integer> getItems() {
        return this.items;
    }

    public HashMap<Integer, Integer> getEnter() {
        return this.enter_items;
    }

    public boolean haveNeedMobs() {
        return !this.mobs.isEmpty();
    }
    public boolean isLock(rpgplayer p) {

        if (this.rLvl > p.getPLevel())
            return true;
        if (this.rQuest == null && this.rInPQuest == null)
            return false;
        if (!p.getCompleteQuest(this.rQuest))
            return true;
        if (this.rInPQuest != null &&
                !p.getInProcessQuest(this.rInPQuest) && !p.getCompleteQuest(this.rInPQuest))
            return true;
        return false;
    }
}
