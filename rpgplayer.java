package azerot.azerot;

import azerot.azerot.DB.RPGDataBase;
import azerot.azerot.Quest.Quest;
import azerot.azerot.Quest.questScript;
import azerot.azerot.mobs.MobSystem;
import azerot.azerot.questsScripts.ThreePatch;
import com.comphenix.protocol.ProtocolLib;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import io.netty.util.internal.ThreadLocalRandom;
import lombok.Setter;
import net.minecraft.server.v1_12_R1.Entity;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import lombok.Getter;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;
import azerot.azerot.mobs.RPGMobsContainer;

import java.io.File;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public class rpgplayer {

    private Player player;

    RPGDataBase db;

    private String name;

    private Integer lvlkirka;

    public Integer getLvlkirka() {
        return this.lvlkirka;
    }

    public void setLvlkirka(int lvlkirka) {
        this.lvlkirka = lvlkirka;
    }

    private ProtocolLib lib;

    private String prefix;


    private int level;

    private int skillpoint;
    private long xp;

    private int speed;

    private long lxp;

    @Getter
    private double xpModifier;

    private double mininglevel;


    private int ecsize;

    private Inventory ec;

    private Inventory rc;

    private Inventory arts;

    private boolean rcEnabled;

    private boolean settingsblood;

    private boolean settingsdamage;

    private boolean settingspvp;

    private boolean settingsmusic;

    private boolean nowDie;

    private double health;

    private int armor;

    private boolean playAnimation = false;

    private int magicarmor;
    private int protectbyeffect;
    private int returndamage;
    private int dopdamage;

    private int resistance;

    private int dodge;

    private int dodgepower;

    private int regeneration;

    private int trueregeneration;
    private int armorcritchance;

    private int armorcritdamage;

    private int armorvampiric;

    private int plusdamage;

    private int plusmagicdamage;

    private double plusfiredamage;

    private int pluscritchance;

    private int manaregeneration;

    private int maxmana;



    private int pluscritdamage;

    private int plusvampiric;

    private int activespell;

    private String[] streak;

    private String spellstring;

    private int spellR = 0;

    private int spellRR = 0;

    private int spellRL = 0;

    private int spellRRR = 0;

    private double setPlusHealth;

    private int spellRRL = 0;

    private int spellRLR = 0;

    private int spellF = 0;

    private int spellRLL = 0;

    private ItemStack sbitem;

    private String lastloc = "";

    private int savezone = 0;

    private double curseBlood = 0.0D;

    private int curseFear = 0;

    private double curseDamage = 0.0D;

    private double curseMovement = 0.0D;

    private Location curseMovementLoc;

    private double curseRevenge = 0.0D;

    private int curseRevengeTime = 0;

    private int curseSilence = 0;


    private int curseMana = 0;

    private int curseCorrosion = 0;

    private int vortex = 0;

    private int vortexVec = 0;

    private String quest;

    private  int blacksmith = 0;

    private long unwantedPlayer = 0L;

    private int finishQuest = 0;

    private int region = 0;

    private boolean watchingCutscene = false;

    private List<Integer> learnedSpells = new ArrayList<>();

    private HashMap<String, Integer> killedMobs = new HashMap<>();

    public static HashMap<String, rpgplayer> playershm = new HashMap<>();

    private HashMap<String, Integer> DialogueBetweenNPC = new HashMap<>();

    private HashMap<String, Integer> quests = new HashMap<>();

    private HashMap<String, Integer> questsStage = new HashMap<>();

    private HashMap<String, Integer> playersForparty =  new HashMap<>();

    private int money;

    private HashMap<String, Long> spellcooldown = new HashMap<>();

    private HashMap<Integer, Integer> buffbyitems = new HashMap<>();

    private HashMap<String, Object> dailies = new HashMap<>();
    private List<String> canCompleteQuest = new ArrayList<>();

    private HashMap<String, Boolean> settings = new HashMap<>();

    private HashMap<String, Integer> talents = new HashMap<>();
    private HashMap<String, Boolean> activatedtalents = new HashMap<>();

    private HashMap<String, Integer> talentsPoints = new HashMap<>();

    private HashMap<Player, Boolean> packetUse = new HashMap<>();

    private HashMap<Integer, Integer> flowerGrowth = new HashMap<>();

    private HashMap<Integer, Integer> flowers = new HashMap<>();

    private HashMap<Integer, Entity> spawnFakeEntity = new HashMap<>();

    private HashMap<Integer, Entity> spawnFakeNameNPC = new HashMap<>();

    private HashMap<Integer, Entity> spawnFakeNPC = new HashMap<>();

    private HashMap<String, Long> buff = new HashMap<>();

    private HashMap<String, Integer> cooldown = new HashMap<>();

    private boolean skipCutscene = false;

    private boolean clicknpc = false;

    private boolean PVP_Japan_active = false;

    private Scoreboard scoreboard;

    private Location locationWolf = null;

    private String questLook;

    private String PVP_JAPAN_Event_Team = "";

    private String ScoreBoardInfo;

    private int Gold = 0;

    private  String clas;

    private int group;

    Gson gson = new Gson();

    private int entityId = 0;


    public rpgplayer(Player p) throws SQLException {
        this.level = 1;
                this.ecsize = 1;
                this.lvlkirka = 1;
                this.getSpellcooldown().put("SecondChance", 1000L);
                this.getDailies().put("beginningOfTheTask", 0);
        this.getDailies().put("completeDailies", true);

        try (Connection connection = azerot.db.getHikari().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM players")) {
            ResultSet res= ps.executeQuery(String.format("SELECT * FROM players WHERE name='%s'", p.getName()));

            if(res.next()) {
                Type type = new TypeToken<HashMap<Integer, Integer>>() {
                }.getType();


                int level1 = res.getInt("level");
                this.level = level1;
                int skill = res.getInt("skillpoint");
                this.skillpoint = skill;
                int xp = res.getInt("xp");
                this.xp = xp;
                this.xpModifier = 0;
                int ecsize = res.getInt("ecsize" );
                this.ecsize = ecsize;
                this.money = res.getInt("money");
                int rcEnabled1 = res.getInt("rcEnabled");
                this.rcEnabled = rcEnabled1 == 1;
                String spells = res.getString("spells");
                String learnedSpell = res.getString("learnedSpells");
                Type learnedSpellsToken = (new TypeToken<List<Integer>>() {
                }).getType();
                if(learnedSpell == null){
                    this.learnedSpells = null;
                }else{
                    this.learnedSpells = gson.fromJson(learnedSpell, learnedSpellsToken);
                }
                HashMap<String, Integer> spell = gson.fromJson(spells, type);
                if(spell != null){
                    this.spellR = spell.get(0) != null ? spell.get(0) : 0;
                    this.spellRR =spell.get(1) != null ? spell.get(1) : 0;
                    this.spellRRR = spell.get(2) != null ? spell.get(2) : 0;
                    this.spellRLL = spell.get(3) != null ? spell.get(3) : 0;
                    this.spellRLR = spell.get(4) != null ? spell.get(4) : 0;
                    this.spellRRL = spell.get(5) != null ? spell.get(5) : 0;
                    this.spellRL= spell.get(6) != null ? spell.get(6) : 0;
                    this.spellF = spell.get(7) != null ? spell.get(7) : 0;
                }


            } else {
                ps.executeUpdate("INSERT INTO players(name)" +
                        " VALUES ('" + p.getName()  + "')");
                this.learnedSpells.add(1);
                this.spellF = 1;
            }
            res.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

               this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.player = p;
        this.name = p.getName();
        this.player.setMetadata("xp", (MetadataValue)new FixedMetadataValue((Plugin)azerot.getInstance(), Long.valueOf(this.xp)));
        this.ec = generateEC();
        this.rc = generateRC();
        setSettings();
        setTalents();
        setActivatedTalents();
        setQuestsStage();
        createScoreBoard();
        this.streak = new String[3];
        this.group = 0;
        Location();
        this.ScoreBoardInfo = "quests";
        this.questsStage.put("0", 0);
        loadArmor();
        this.questLook = "0";
        loadItems();
        this.spellstring = "";
        setQuests();
        playershm.put(this.name, this);
        this.lxp = RPGLevels.RPGLevels(getRPGPlayer(this.player));
        resetArmor();
    }

    public static rpgplayer getRPGPlayer(Player player) {
        return playershm.get(player.getName());
    }
    public String getColorFromGroup() {
        switch (this.group){
            case 0:
                return "§7";
            case 1:
                return "§2";
            case 2:
                return "§b";
            case 3:
                return "§6";
            case 4:
                return "§1";
            case 5:
                return "§d";
            case 6:
                return "§5";
            case 7:
                return "§l§6";
            case 8:
                return "§e";
            case 9:
                return "§9";
            case 10:
                return "§4";
        }
        return "";
    }

    public String getPrefixFromGroup() {
        switch (this.group){
            case 0:
                return getColorFromGroup() + ""; //15
            case 1:
                return getColorFromGroup() + "VIP"; //14
            case 2:
                return getColorFromGroup() + "PREMIUM";//13
            case 3:
                return getColorFromGroup() + "HOLY"; //12
            case 4:
                return getColorFromGroup() + "RENOWN"; //11
            case 5:
                return getColorFromGroup() + "PARADISE";//10
            case 6:
                return getColorFromGroup() + "HEAVENLY";//9
            case 7:
                return getColorFromGroup() + "LEGENDARY";//8
            case 8:
                return getColorFromGroup() + "TESTER";
            case 9:
                return getColorFromGroup() + "MOD";
            case 10:
                return getColorFromGroup() + "DEV";
        }
        return "";
    }

    public void setGold(int Gold) {
        this.Gold = Gold;
        try{
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("PlayerGold");
            out.writeUTF(getName());
            out.writeInt(getGold());
            Bukkit.getServer().sendPluginMessage((Plugin)azerot.getInstance(), "message:main", out.toByteArray());
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public void setGroup(int Group) {
        this.group = Group;
        try{
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("PlayerGroup");
            out.writeUTF(getName());
            out.writeInt(getGroup());
            Bukkit.getServer().sendPluginMessage((Plugin)azerot.getInstance(), "message:main", out.toByteArray());
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public void createScoreBoard(){
        Team white = this.scoreboard.registerNewTeam("white");
        Team green = this.scoreboard.registerNewTeam("green");
        Team blue = this.scoreboard.registerNewTeam("blue");
        Team yellow = this.scoreboard.registerNewTeam("yellow");
        Team gold = this.scoreboard.registerNewTeam("gold");
        Team purple = this.scoreboard.registerNewTeam("purple");
        Team red = this.scoreboard.registerNewTeam("red");

        white.setColor(ChatColor.DARK_GRAY);
        white.setPrefix("§8");

        white.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);

        green.setColor(ChatColor.DARK_GREEN);
        green.setPrefix("§2");
        green.setSuffix("");
        green.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);

        blue.setColor(ChatColor.BLUE);
        blue.setPrefix("§9");
        blue.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);


        purple.setColor(ChatColor.DARK_PURPLE);
        purple.setPrefix("§5");
        purple.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);

        red.setColor(ChatColor.DARK_RED);
        red.setPrefix("§4");
        red.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);

        yellow.setColor(ChatColor.YELLOW);
        yellow.setPrefix("§e");


    }

    public RpgTrade getTrade() {
        return RpgTrade.getTrading(this);
    }
    public void setSettings() throws SQLException {
        try (Connection connection = azerot.db.getHikari().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM players")) {
            ResultSet res= ps.executeQuery(String.format("SELECT * FROM players WHERE name='%s'", player.getName()));

            while (res.next()) {
                Type type = new TypeToken<HashMap<String, Boolean>>() {
                }.getType();
                String setting = res.getString("settings");
                if(setting == null){
                    this.settings.put("trade", true);
                    this.settings.put("blood", false);
                    this.settings.put("vimPlayer", false);
                    this.settings.put("dropWhite", false);
                    this.settings.put("dropGreen", false);
                    this.settings.put("dropBlue", false);
                    this.settings.put("dropPurple", false);
                    this.settings.put("autoRC", false);
                    this.settings.put("autoSkip", false);
                    this.settings.put("openCases", true);
                    this.settings.put("liftingWhite", false);
                    this.settings.put("liftingGreen", false);
                    this.settings.put("liftingBlue", false);
                    this.settings.put("liftingPurple", false);


                }else{
                    HashMap<String, Boolean> read = gson.fromJson(setting, type);
                    this.settings = (HashMap<String, Boolean>) read.clone() ;
                }
            }
            res.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    public void setTalents() throws SQLException {
        try (Connection connection = azerot.db.getHikari().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM players")) {
            ResultSet res= ps.executeQuery(String.format("SELECT * FROM players WHERE name='%s'", player.getName()));

            while (res.next()) {
                Type type = new TypeToken<HashMap<String, Integer>>() {
                }.getType();
                String talentString = res.getString("talents");
                if(talentString != null){
                    HashMap<String, Integer> read = gson.fromJson(talentString, type);
                    this.talents =(HashMap<String, Integer>) read.clone() ;
                }
            }
            res.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void setActivatedTalents() throws SQLException {
        try (Connection connection = azerot.db.getHikari().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM players")) {
            ResultSet res= ps.executeQuery(String.format("SELECT * FROM players WHERE name='%s'", player.getName()));

            while (res.next()) {
                String talentString = res.getString("ActivatedTalents");
                String talent = res.getString("talents");
                String points = res.getString("talentsPoints");
                if(talentString != null){
                    Type type = new TypeToken<HashMap<String, Boolean>>() {
                    }.getType();
                    HashMap<String, Boolean> read = gson.fromJson(talentString, type);
                    this.activatedtalents =(HashMap<String, Boolean>) read.clone() ;
                }
                if(talent != null){
                    Type type = new TypeToken<HashMap<String, Integer>>() {
                    }.getType();
                    HashMap<String, Integer> read = gson.fromJson(talent, type);
                    this.talents = (HashMap<String, Integer>) read.clone();
                }
                if(points != null){
                    Type type = new TypeToken<HashMap<String, Integer>>() {
                    }.getType();
                    HashMap<String, Integer> read = gson.fromJson(points, type);
                    this.talentsPoints = (HashMap<String, Integer>) read.clone();

                }
            }
            res.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void setQuestsStage() throws SQLException {
        try (Connection connection = azerot.db.getHikari().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM players")) {
            ResultSet res= ps.executeQuery(String.format("SELECT * FROM players WHERE name='%s'", player.getName()));

            while (res.next()) {
                String questsStages = res.getString("questStage");
                if(questsStages != null){
                    Type type = new TypeToken<HashMap<String, Integer>>() {
                    }.getType();
                    HashMap<String, Boolean> read = gson.fromJson(questsStages, type);
                    this.questsStage =(HashMap<String, Integer>) read.clone() ;
                }
            }
            res.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void setQuests() throws SQLException {
        try (Connection connection = azerot.db.getHikari().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM players")) {
            ResultSet res= ps.executeQuery(String.format("SELECT * FROM players WHERE name='%s'", player.getName()));

            while (res.next()) {
                String quest = res.getString("quests");
                String mobs = res.getString("mobs");
                String dialogue = res.getString("dialogue");
                Type type = new TypeToken<HashMap<String, Integer>>() {
                }.getType();
                if (quest == null) {
                    for (int i = 1; i <= azerot.qsts; i++)
                        this.quests.put(String.valueOf(i), 3);
                    this.quests.put("1", 3);
                } else {
                    HashMap<String, Integer> read = gson.fromJson(quest, type);
                    if(mobs != null){
                        HashMap<String, Integer> mob = gson.fromJson(mobs, type);
                        this.killedMobs = (HashMap<String, Integer>) mob.clone();
                    }else{
                        for(int i = 1; i <= RPGMobsContainer.getRpgmobs().size(); i++){
                            this.killedMobs.put(String.valueOf(i), 0);
                        }
                    }
                    this.quests = (HashMap<String, Integer>) read.clone();
                    for(int i = read.size(); i <= Quest.quests.size(); i++){
                        this.quests.put(String.valueOf(i), 3);
                    }
                    if(dialogue != null){
                        HashMap<String, Integer> dialog = gson.fromJson(dialogue, type);
                        this.DialogueBetweenNPC = (HashMap<String, Integer>) dialog.clone();
                    }
                }
            }
            res.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }





    public void updateQuest(String id) {
        if (!Objects.requireNonNull(Quest.getQuest((new StringBuilder(String.valueOf(id))).toString())).isLock(this))
            setStatusQuest((new StringBuilder(String.valueOf(id))).toString(), 0);
    }

    public void updatePlayerQuest() throws SQLException {

        for (int i = 1; i <= azerot.qsts; i++) {
            if (quests.get(i) != null)
                switch (quests.get(i)) {
                    case -1:
                        if (!Objects.requireNonNull(Quest.getQuest((new StringBuilder(String.valueOf(i))).toString())).isLock(this))
                            setStatusQuest((new StringBuilder(String.valueOf(i))).toString(), 0);
                        break;
                }
        }
    }


    public void setPlayerData(String datatype, String dataname, int dataint, String datastring) {
        if (datatype.equals("string"))
            this.player.setMetadata(dataname, (MetadataValue)new FixedMetadataValue((Plugin)azerot.getInstance(), datastring));
        if (datatype.equals("int"))
            this.player.setMetadata(dataname, (MetadataValue)new FixedMetadataValue((Plugin)azerot.getInstance(), Integer.valueOf(dataint)));
    }

    public Player toPlayer() {
        return this.player;
    }

    public int getPLevel() {
        return this.level;
    }

    public int getSkillpoint() {
        return this.skillpoint;
    }

    public long getXP() {
        return this.xp;
    }

    public long getLXp() {
        return this.lxp;
    }

    public boolean getRCEnabled(){
        return rcEnabled;
    }

    public void setRCEnabled(boolean permission){
        this.rcEnabled = permission;
    }

    public Inventory getEC() {
        return this.ec;
    }

    public void setEC(Inventory inv) {
        this.ec = inv;
    }

    public Location Location() throws SQLException {
        Location loc = null;
        try (Connection connection = azerot.db.getHikari().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM players")) {
            ResultSet res= ps.executeQuery(String.format("SELECT * FROM players WHERE name='%s'", player.getName()));

            while(res.next()) {
                String location = res.getString("location");

                if (location == null || location.equals("null")) {
                    loc = new Location(Bukkit.getWorld("world"), -6, 70, 300, -145.7f, 1.8f);
                    player.teleport(loc);
                    player.sendTitle("§6Акт I", "§6Забытый", 7, 50, 15);
                }else {
                    String[] locs = location.split(" ");
                    loc = new Location(Bukkit.getWorld(locs[0]), Double.parseDouble(locs[1]), Double.parseDouble(locs[2]), Double.parseDouble(locs[3]), Float.parseFloat(locs[4]), Float.parseFloat(locs[5]));
                    player.teleport(loc);
                }
            }
            res.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

            return loc;

    }

    public void loadArmor() throws SQLException {
        try (Connection connection = azerot.db.getHikari().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM players")) {
            ResultSet res= ps.executeQuery(String.format("SELECT * FROM players WHERE name='%s'", player.getName()));

            while(res.next()) {
                String inv1 = res.getString("armor");
                if (inv1 == null) {
                    return;
                } else {
                    Inventory u1 = InventoryStringDeSerializer.ArmorToInventory(inv1, getPlayer());
                    toPlayer().getInventory().setHelmet(u1.getItem(0));
                    toPlayer().getInventory().setChestplate(u1.getItem(1));
                    toPlayer().getInventory().setLeggings(u1.getItem(2));
                    toPlayer().getInventory().setBoots(u1.getItem(3));
                    toPlayer().getInventory().setItemInOffHand(u1.getItem(4));
                }
            }
            res.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void loadItems() throws SQLException {
        try (Connection connection = azerot.db.getHikari().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM players")) {
            ResultSet res= ps.executeQuery(String.format("SELECT * FROM players WHERE name='%s'", player.getName()));

            while(res.next()) {
                String inv = res.getString("inv");
                String art = res.getString("arts");
                if (inv != null){
                    InventoryStringDeSerializer.StringToInventory(inv, getPlayer());
                }else{
                    toPlayer().getInventory().clear();
                    player.getInventory().addItem(new RPGItem().getRPGItem(29,0,1,0,0,0,0,0, getRPGPlayer(player)));
                    player.getInventory().addItem(new RPGItem().getRPGItem(30,0,1,0,0,0,0,0, getRPGPlayer(player)));
                }
                if(art != null){
                    InventoryStringDeSerializer.StringToArts(art, getPlayer());

                }
            }
            res.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }

    public Inventory generateRC() throws SQLException {
        Inventory inv = null;
        try (Connection connection = azerot.db.getHikari().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM players")) {
            ResultSet res= ps.executeQuery(String.format("SELECT * FROM players WHERE name='%s'", player.getName()));

            inv = Bukkit.getServer().createInventory(null, 27, "§6Хранилище рецептов");
            while(res.next()) {
                String chest = res.getString("RC");
                if (chest != null) {
                    String[] serializedBlocks = chest.split(";");
                    String invInfo = serializedBlocks[0];
                    for (int i = 1; i < serializedBlocks.length; i++) {
                        String item = serializedBlocks[i];
                        String item1 = item.replace("-", ";");
                        String[] item2 = item1.split(":");
                        String itemAttribute = item2[1];
                        String[] itemAttribute1 = itemAttribute.split(";");


                        ItemStack is = null;
                        Boolean createdItemStack = false;
                        int id = Integer.parseInt(itemAttribute1[0]);
                        int amount = Integer.parseInt(itemAttribute1[1]);

                        if (id > 0) {
                            inv.setItem(Integer.parseInt(item2[0]), (new RPGItem()).getRPGItem(id, 0, amount, 0, 0, 0, 0,0, getRPGPlayer(this.player)));
                        }
                    }
                }
            }
            res.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return inv;
    }


    public Inventory generateEC() throws SQLException {
        Inventory inv = null;
        try (Connection connection = azerot.db.getHikari().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM players")) {
            ResultSet res= ps.executeQuery(String.format("SELECT * FROM players WHERE name='%s'", player.getName()));

            inv = Bukkit.getServer().createInventory(null, 9 * ecsize, "§6Хранилище");
            while(res.next()) {
                String chest = res.getString("EC");
                if (chest != null) {
                    String[] serializedBlocks = chest.split(";");

                    for (String item : serializedBlocks) {
                        String item1 = item.replace("-", ";");
                        String[] item2 = item1.split(":");
                        String itemAttribute = item2[1];
                        String[] itemAttribute1 = itemAttribute.split(";");

                        ItemStack is = null;
                        Boolean createdItemStack = false;
                        int id = Integer.parseInt(itemAttribute1[0]);
                        int amount = Integer.parseInt(itemAttribute1[1]);
                        int enchant = Integer.parseInt(itemAttribute1[2]);
                        int mark = Integer.parseInt(itemAttribute1[3]);
                        int markpower = Integer.parseInt(itemAttribute1[4]);
                        int scaleAtStrenght = Integer.parseInt(itemAttribute1[5]);
                        int scaleAtAgility = Integer.parseInt(itemAttribute1[6]);
                        int scaleAtIntelligence = Integer.parseInt(itemAttribute1[7]);

                        if (id > 0) {
                            inv.setItem(Integer.parseInt(item2[0]), (new RPGItem()).getRPGItem(id, enchant, amount, mark, markpower, scaleAtStrenght, scaleAtAgility, scaleAtIntelligence, getRPGPlayer(toPlayer())));
                        }
                    }
                }
            }
            res.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return inv;
    }

    public Inventory generateEC(Inventory inventory) {
        Inventory inv = Bukkit.getServer().createInventory(null, 9 * this.ecsize, "§6Хранилище");
        for (int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i) != null && inventory
                    .getItem(i).getAmount() != 0)
                inv.setItem(i, inventory.getItem(i));
        }
        return inv;
    }

    public Inventory generateRC(Inventory inventory) {
        Inventory inv = Bukkit.getServer().createInventory(null, 27, "§6Хранилище рецептов");
        for (int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i) != null && inventory
                    .getItem(i).getAmount() != 0)
                inv.setItem(i, inventory.getItem(i));
        }
        return inv;
    }

    public Inventory getRC() {
        return this.rc;
    }

    public void setRC(Inventory inv) {
        this.rc = inv;
    }



    public boolean getSettingsBlood() {
        return this.settingsblood;
    }

    public void setSettingsBlood(boolean setting) {
        this.settingsblood = setting;
    }

    public boolean getSettingsDamage() {
        return this.settingsdamage;
    }

    public void setSettingsDamage(boolean setting) {
        this.settingsdamage = setting;
    }

    public boolean getSettingsPVP() {
        return this.settingspvp;
    }

    public void setSettingsPVP(boolean setting) {
        this.settingspvp = setting;
    }

    public boolean getSettingsMusic() {
        return this.settingsmusic;
    }

    public void setSettingsMusic(boolean setting) {
        this.settingsmusic = setting;
    }

    public boolean getAvailableQuest(String id) {
        if (((Integer)this.quests.get(id)).intValue() != 0)
            return false;
        return true;
    }

    public boolean getInProcessQuest(String id) {
        if (((Integer)this.quests.get(id)).intValue() != 1)
            return false;
        return true;
    }
    public boolean getCompleteQuest(String id) {
        if ((Integer) this.quests.get(id) != 2)
            return false;
        return true;
    }

    public boolean haveMob(String id) {
        return this.killedMobs.containsKey(id);
    }

    public int getMob(String id) {
        return this.killedMobs.get(id);
    }

    public void addMob(String id, int count) throws SQLException {
        int mobs = this.killedMobs.containsKey(id) ? (Integer) this.killedMobs.get(id) : 0;
        mobs = mobs + count;
        this.killedMobs.put(id, mobs);
        for (int i = 1; i <= azerot.qsts; i++) {
            if (getStatusQuest(String.valueOf(i)) == 1)
                questScript.ScriptsQuest(toPlayer(), String.valueOf(i), id, false, 0);
        }
    }

    public void checkQuestCompleted(String id) throws SQLException {
        List<Integer> qip = new ArrayList<>();
        for (int i = 1; i <= azerot.qsts; i++) {
            if (getStatusQuest(String.valueOf(i)) == 1)
                qip.add(Integer.valueOf(i));
        }
        for (Iterator<Integer> iterator = qip.iterator(); iterator.hasNext(); ) {
            int j = ((Integer)iterator.next()).intValue();
            Quest q = Quest.getQuest("" + j);
            if (getMob(id) <= q.getMobAmount(id))
                toPlayer().sendMessage(WorldUtils.worldName() + " §fУбит " + MobSystem.getName(Integer.parseInt(id)) + " §6" + getMob(id) + "§f/§6" + q.getMobAmount(id) + " §fпо заданию §e" + q.getName() + " #" + j);
            questScript.ScriptsQuest(toPlayer(), id, id, false, 0);
        }
    }


    public void updateCompletedQuest(int idItem, int amount) throws SQLException {
        for (String id : this.quests.keySet().stream().filter(id -> (((Integer)this.quests.get(id)).intValue() == 1)).collect(Collectors.toList())) {
            this.canCompleteQuest.add(id);

        }
    }

    public int getStatusQuest(String i) {
        return (Integer) this.quests.get(i);
    }


    public void setStatusQuest(String i, int status) {
        this.quests.put(i, Integer.valueOf(status));
    }

    public void takeQuest(String id) throws SQLException {
        Quest quest = Quest.getQuest(id);
        HashMap<Integer, Integer> r = quest.getEnter();
        if (!r.isEmpty()) {
            int amount = 0;
            for (int i = 0; i < 36; i++) {
                if (toPlayer().getInventory().getItem(i) == null || toPlayer().getInventory().getItem(i).getType().equals(Material.AIR) || toPlayer().getInventory().getItem(i).getAmount() == 0)
                    amount++;
            }
            if (amount < r.size()) {
                toPlayer().sendMessage("§4Нет места");
                return;
            }
            for (Iterator<Integer> iterator = r.keySet().iterator(); iterator.hasNext(); ) {
                int reward = ((Integer)iterator.next()).intValue();
                ItemStack item = (new RPGItem()).getRPGItem(reward, 0, 1,  0, 0,0, 0,0, getRPGPlayer(this.player));
                item.setAmount(((Integer)r.get(Integer.valueOf(reward))).intValue());
                this.player.getInventory().addItem(new ItemStack[] { item });
            }
        }
        setStatusQuest(id, 1);
        setQuestLook(id);
        getQuestsStage().put(id, 0);
        toPlayer().sendTitle("§6Вы взяли задание!", "", 10, 70, 20);
        toPlayer().sendMessage(WorldUtils.worldName()+ " §6Вы взяли задание!");
        if(Integer.valueOf(id) <= 8) {
            questScript.ScriptsQuest(toPlayer(), id, String.valueOf(0), false, 0);
        }
        if(Integer.valueOf(id) >= 9) {
            ThreePatch.ScriptsForThreePatch(toPlayer(), id, String.valueOf(0), false, 0);
        }
    }

    public void completeQuest(String id) throws SQLException {
        Quest quest = Quest.getQuest(id);
        for (Iterator<Integer> iterator = quest.getItems().keySet().iterator(); iterator.hasNext(); ) {
            int i = ((Integer)iterator.next()).intValue();
            int amount = WorldUtils.checkItems(this, i, 0);
            if (amount < ((Integer)quest.getItems().get(Integer.valueOf(i))).intValue()) {
                toPlayer().sendMessage("§4Вы не можете выполнить это задание!");
                return;
            }
        }
        HashMap<Integer, Integer> r = quest.getReward();
        if (!r.isEmpty()) {
            int amount = 0;
            for (int i = 0; i < 36; i++) {
                if (toPlayer().getInventory().getItem(i) == null || toPlayer().getInventory().getItem(i).getType().equals(Material.AIR) || toPlayer().getInventory().getItem(i).getAmount() == 0)
                    amount++;
            }
            if (amount < r.size()) {
                toPlayer().sendMessage("§4Нет места!");
                return;
            }
        }
        if (quest.getTeleport() != null)
            toPlayer().teleport(quest.getTeleport());
        if (quest.getType() == 2) {
            setStatusQuest(id, 0);
        } else {
            setStatusQuest(id, 2);
            this.finishQuest = this.finishQuest + 1;
            if (quest.getCQuest() != null)
                setStatusQuest(quest.getCQuest(), 2);
        }
        toPlayer().playSound(toPlayer().getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1.0F, 1.0F);
        updatePlayerQuest();
        toPlayer().sendTitle("§6Задание выполнено!", "", 10, 70, 20);
    }

    public String getName() {
        return this.name;
    }

    public void PLevelUp() throws SQLException {
        this.level++;
        this.player.sendMessage("§a§a§lВы достигли §b§l" + this.level + " §a§lуровня! Поздравляем" );
        this.player.sendTitle("§b§oПоздравляем с §6§o"+ this.level + " §b§oуровнем!", "", 7, 50, 15);
        this.player.getWorld().playSound(this.player.getLocation(), Sound.ENTITY_FIREWORK_LAUNCH, 1.0F, 1.0F);
        this.player.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, this.player.getLocation(), 50, 0.0D, 0.5D, 0.0D, 0.15D);
        this.player.setLevel(this.level);
        WorldUtils.updatePlayerTab(this.player);
        (new BukkitRunnable() {
            public void run() {
                for (Map.Entry<String, Integer> i : getQuests().entrySet()){
                    String quest = i.getKey();
                    int questInfo = i.getValue();
                    if(questInfo == 1){
                        try {
                            questScript.ScriptsQuest(toPlayer(), quest, "0", false, 0);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        }).runTaskLater((Plugin)azerot.getInstance(), 20L);
        resetArmor();
        (new RPGItem()).updateItem(this.player);
            }


    public void setLevel(int amount) {
        this.level = amount;
        this.lxp = RPGLevels.RPGLevels(getRPGPlayer(this.player));
        this.player.setLevel(this.level);
        WorldUtils.updatePlayerTab(this.player);
        resetArmor();
        (new RPGItem()).updateItem(this.player);
    }
    public  void addLvlKirka(int amount){
        setLvlkirka(getLvlkirka() + amount);
    }


    public void pIncreaseTalents(String talent){
        switch (talent){
            case "SecondChance":
                if(this.getTalents().get("SecondChance") == 6){
                    player.sendMessage(WorldUtils.worldName() + " §4Максимальный уровень!");
                    return;
                }else {
                    if(this.talentsPoints.get("SecondChance") >= Math.pow(2, this.getTalents().get("SecondChance"))){
                        this.talentsPoints.put("SecondChance", this.talentsPoints.get("SecondChance") - (int) Math.pow(2, this.getTalents().get("SecondChance")));
                        this.getTalents().put("SecondChance", this.getTalents().get("SecondChance") + 1);
                        player.sendMessage(WorldUtils.worldName() + " §2Вы прокачали эмблему!");
                    }else{
                        toPlayer().sendMessage(WorldUtils.worldName() + " §4Недостаточно эмблем!");
                    }
                }
                break;
            case "SunAndMoon":
                if(this.getTalents().get("SunAndMoon") == 10){
                    player.sendMessage(WorldUtils.worldName() + " §4Максимальный уровень!");
                    return;
                }else {
                    if(this.talentsPoints.get("SunAndMoon") >= Math.pow(2, this.getTalents().get("SunAndMoon"))){
                        this.talentsPoints.put("SunAndMoon", this.talentsPoints.get("SunAndMoon") - (int) Math.pow(2, this.getTalents().get("SunAndMoon")));
                        this.getTalents().put("SunAndMoon", this.getTalents().get("SunAndMoon") + 1);
                        player.sendMessage(WorldUtils.worldName() + " §2Вы прокачали эмблему!");
                    }else{
                        toPlayer().sendMessage(WorldUtils.worldName() + " §4Недостаточно эмблем!");
                    }
                }
                break;
            case "Survivalinstinct":
                if(this.getTalents().get("Survivalinstinct") == 10){
                    player.sendMessage(WorldUtils.worldName() + " §4Максимальный уровень!");
                    return;
                }else {
                    if(this.talentsPoints.get("Survivalinstinct") >= Math.pow(2, this.getTalents().get("Survivalinstinct"))){
                        this.talentsPoints.put("Survivalinstinct", this.talentsPoints.get("Survivalinstinct") - (int) Math.pow(2, this.getTalents().get("Survivalinstinct")));
                        this.getTalents().put("Survivalinstinct", this.getTalents().get("Survivalinstinct") + 1);
                        player.sendMessage(WorldUtils.worldName() + " §2Вы прокачали эмблему!");
                    }else{
                        toPlayer().sendMessage(WorldUtils.worldName() + " §4Недостаточно эмблем!");
                    }
                }
                break;
            case "Magicalfoundation":
                if(this.getTalents().get("Magicalfoundation") == 10){
                    player.sendMessage(WorldUtils.worldName() + " §4Максимальный уровень!");
                    return;
                }else {
                    if(this.talentsPoints.get("Magicalfoundation") >= Math.pow(2, this.getTalents().get("Magicalfoundation"))){
                        this.talentsPoints.put("Magicalfoundation", this.talentsPoints.get("Magicalfoundation") - (int) Math.pow(2, this.getTalents().get("Magicalfoundation")));
                        this.getTalents().put("Magicalfoundation", this.getTalents().get("Magicalfoundation") + 1);
                        player.sendMessage(WorldUtils.worldName() + " §2Вы прокачали эмблему!");
                    }else{
                        toPlayer().sendMessage(WorldUtils.worldName() + " §4Недостаточно эмблем!");
                    }
                }
                break;
        }
    }
    public void pActivateTalents(String talent){
        if(this.getActivatedtalents().get(talent) == null){
            this.getActivatedtalents().put(talent, true);
        }else{
            this.getActivatedtalents().put(talent, !this.getActivatedtalents().get(talent));
        }
    }

    public void addXP(int amount) throws SQLException {
        if (amount == 0)
            return;
        this.xpModifier = xpModifier;

        switch(getGroup()){
            case 0:
                this.xpModifier = 0;
                break;
            case 1:
                this.xpModifier = 0.05D;
                break;
            case 2:
                this.xpModifier = 0.1D;
                break;
            case 3:
                this.xpModifier = 0.15D;
                break;
            case 4:
                this.xpModifier = 0.2D;
                break;
            case 5:
                this.xpModifier = 0.25D;
                break;
            case 6:
                this.xpModifier = 0.3D;
                break;
            case 7:
                this.xpModifier = 0.35D;
                break;


        }

        int bonusXp = (int)(amount * getXpModifier());
        this.player.sendMessage(WorldUtils.worldName() + " §2+" + amount + ((bonusXp > 0) ? (" §3(+"  + bonusXp + ")") : "") + " §2опыта"  );
        amount += bonusXp;
        if (getLXp() == -1L) {
            this.xp = 0L;
            this.player.setExp(0.0F);
            return;
        }


        this.xp += amount;
        if (this.xp >= this.lxp)
            while (this.xp >= this.lxp) {
                if (getLXp() == -1L) {
                    this.xp = 0L;
                    this.player.setExp(0.0F);
                    return;
                }
                PLevelUp();
                this.xp -= this.lxp;
                this.lxp = RPGLevels.RPGLevels(getRPGPlayer(this.player));
            }
        double percentxp = this.xp / this.lxp * 100.0D;
        double mathxp = Math.min(0.01D * percentxp, 0.99D);
        this.player.setExp((float)mathxp);
    }

    public void setXP(int amount) {
        if (amount == 0)
            return;
        amount = Math.max(0, amount);
        this.xp = amount;
        double percentxp = this.xp / this.lxp * 100.0D;
        double mathxp = Math.min(0.01D * percentxp, 0.99D);
        this.player.setExp((float)mathxp);
    }

    public void setXpModifier(double amount) {
        this.xpModifier = amount;
    }

    public double getMiningLevel() {
        return this.mininglevel;
    }

    public void setMiningLevel(double amount) {
        this.mininglevel = amount;
    }

    public void addMiningLevel(double amount) {
        if (amount <= 0.0D)
            return;
        setMiningLevel(getMiningLevel() + amount);
        this.player.sendMessage(WorldUtils.worldName() + "" + getMiningLevel() + "");
    }
    public void clearStats() {
        int point = 0;
        Iterator iterator = this.getTalents().entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) iterator.next();
            point += entry.getValue();
        }
        this.getTalents().replaceAll( (k,v)->v=0 );
        this.skillpoint = point;
        Bukkit.broadcastMessage("Очков: " + point);
    }

    public int getEcSize() {
        return this.ecsize;
    }

    public void EcIncrease(int amount) {
        this.ecsize = amount;
        setEC(generateEC(this.ec));
        this.player.sendMessage(WorldUtils.worldName() + " §aМаксимальный размер хранилища изменен!" );
    }

    public static void resetPlayer(String name) {
        if (playershm.containsKey(name))
            ((rpgplayer)playershm.get(name)).player = Bukkit.getPlayer(name);
    }

    public void resetArmor() {
        if (getRPGPlayer(this.player) == null)
            return;
        int health = 0;
        int armor = 0;
        int magicarmor = 0;
        int resistance = 0;
        int dodge = 0;
        int dodgepower = 0;
        int regeneration = 0;
        int trueregeneration = 0;
        int speed = 0;
        int protectbyeefect = 0;
        int returndamage = 0;
        int dopdamage = 0;
        int armorcritchance = 0;
        int armorcritdamage = 0;
        int armorvampiric = 0;
        int maxmana = 0;
        int manaregeneration = 0;
        ItemStack armoron = this.player.getInventory().getHelmet();
        RPGItemContainer RPGI = RPGItemContainer.getRPGItemContainer(ItemStat.getID(armoron));
        if (armoron != null &&
                ItemStat.canuseitem(getRPGPlayer(this.player), armoron) == true && ItemStat.getType(armoron) == 3 && ItemStat.getMark(armoron) != 100) {
            int mark = ItemStat.getMark(armoron);
            int enchant = ItemStat.getEnchant(armoron);
            int markpower = ItemStat.getMarkpower(armoron);
            health += RPGI.getHealth(mark,markpower,  enchant);
            armor += RPGI.getArmor(mark,markpower,  enchant);
            magicarmor += RPGI.getMagicArmor(mark,markpower,  enchant);
            protectbyeefect += RPGI.getProtectionByBleeding(mark,markpower,  enchant);
            returndamage += RPGI.getReturnDamage(mark,markpower,  enchant);
            dopdamage += RPGI.getDopDamage(mark,markpower,  enchant);
            resistance += RPGI.getResistance(mark,markpower,  enchant);
            dodge += RPGI.getDodge(mark,markpower,  enchant);
            regeneration += RPGI.getRegeneration(mark,markpower,  enchant);
            trueregeneration += RPGI.getTrueRegeneration(mark,markpower,  enchant);
            speed +=  RPGI.getSpeed(mark,markpower,  enchant);
            armorcritchance += RPGI.getCritChance(mark,markpower,  enchant);
            armorcritdamage += RPGI.getCritDamage(mark,markpower,  enchant);
            armorvampiric += RPGI.getVampiric(mark,markpower,  enchant);
        }
        armoron = this.player.getInventory().getChestplate();
        RPGI = RPGItemContainer.getRPGItemContainer(ItemStat.getID(armoron));
        if (armoron != null &&
                ItemStat.canuseitem(getRPGPlayer(this.player), armoron) == true && ItemStat.getType(armoron) == 3 && ItemStat.getMark(armoron) != 100) {
            int mark = ItemStat.getMark(armoron);
            int enchant = ItemStat.getEnchant(armoron);
            int markpower = ItemStat.getMarkpower(armoron);
            health += RPGI.getHealth(mark,markpower,  enchant);
            armor += RPGI.getArmor(mark,markpower,  enchant);
            magicarmor += RPGI.getMagicArmor(mark,markpower,  enchant);
            protectbyeefect += RPGI.getProtectionByBleeding(mark,markpower,  enchant);
            returndamage += RPGI.getReturnDamage(mark,markpower,  enchant);
            dopdamage += RPGI.getDopDamage(mark,markpower,  enchant);
            resistance += RPGI.getResistance(mark,markpower,  enchant);
            dodge += RPGI.getDodge(mark,markpower,  enchant);
            regeneration += RPGI.getRegeneration(mark,markpower,  enchant);
            trueregeneration += RPGI.getTrueRegeneration(mark,markpower,  enchant);
            speed +=  RPGI.getSpeed(mark,markpower,  enchant);
            armorcritchance += RPGI.getCritChance(mark,markpower,  enchant);
            armorcritdamage += RPGI.getCritDamage(mark,markpower,  enchant);
            armorvampiric += RPGI.getVampiric(mark,markpower,  enchant);
        }
        armoron = this.player.getInventory().getLeggings();
        RPGI = RPGItemContainer.getRPGItemContainer(ItemStat.getID(armoron));
        if (armoron != null &&
                ItemStat.canuseitem(getRPGPlayer(this.player), armoron) == true && ItemStat.getType(armoron) == 3 && ItemStat.getMark(armoron) != 100) {
            int mark = ItemStat.getMark(armoron);
            int enchant = ItemStat.getEnchant(armoron);
            int markpower = ItemStat.getMarkpower(armoron);
            health += RPGI.getHealth(mark,markpower,  enchant);
            armor += RPGI.getArmor(mark,markpower,  enchant);
            magicarmor += RPGI.getMagicArmor(mark,markpower,  enchant);
            protectbyeefect += RPGI.getProtectionByBleeding(mark,markpower,  enchant);
            returndamage += RPGI.getReturnDamage(mark,markpower,  enchant);
            dopdamage += RPGI.getDopDamage(mark,markpower,  enchant);
            resistance += RPGI.getResistance(mark,markpower,  enchant);
            dodge += RPGI.getDodge(mark,markpower,  enchant);
            regeneration += RPGI.getRegeneration(mark,markpower,  enchant);
            trueregeneration += RPGI.getTrueRegeneration(mark,markpower,  enchant);
            speed +=  RPGI.getSpeed(mark,markpower,  enchant);
            armorcritchance += RPGI.getCritChance(mark,markpower,  enchant);
            armorcritdamage += RPGI.getCritDamage(mark,markpower,  enchant);
            armorvampiric += RPGI.getVampiric(mark,markpower,  enchant);
        }
        armoron = this.player.getInventory().getBoots();
        RPGI = RPGItemContainer.getRPGItemContainer(ItemStat.getID(armoron));
        if (armoron != null &&
                ItemStat.canuseitem(getRPGPlayer(this.player), armoron) == true && ItemStat.getType(armoron) == 3 && ItemStat.getMark(armoron) != 100) {
            int mark = ItemStat.getMark(armoron);
            int enchant = ItemStat.getEnchant(armoron);
            int markpower = ItemStat.getMarkpower(armoron);
            health += RPGI.getHealth(mark,markpower,  enchant);
            armor += RPGI.getArmor(mark,markpower,  enchant);
            magicarmor += RPGI.getMagicArmor(mark,markpower,  enchant);
            protectbyeefect += RPGI.getProtectionByBleeding(mark,markpower,  enchant);
            returndamage += RPGI.getReturnDamage(mark,markpower,  enchant);
            dopdamage += RPGI.getDopDamage(mark,markpower,  enchant);
            resistance += RPGI.getResistance(mark,markpower,  enchant);
            dodge += RPGI.getDodge(mark,markpower,  enchant);
            regeneration += RPGI.getRegeneration(mark,markpower,  enchant);
            trueregeneration += RPGI.getTrueRegeneration(mark,markpower,  enchant);
            speed +=  RPGI.getSpeed(mark,markpower,  enchant);
            armorcritchance += RPGI.getCritChance(mark,markpower,  enchant);
            armorcritdamage += RPGI.getCritDamage(mark,markpower,  enchant);
            armorvampiric += RPGI.getVampiric(mark,markpower,  enchant);
        }
        if(this.arts != null){
            armoron = this.arts.getItem(0);
        }
        RPGI = RPGItemContainer.getRPGItemContainer(ItemStat.getID(armoron));
        if (armoron != null &&
                ItemStat.canuseitem(getRPGPlayer(this.player), armoron) == true && ItemStat.getType(armoron) == 4 && ItemStat.getMark(armoron) != 100) {
            int mark = ItemStat.getMark(armoron);
            int enchant = ItemStat.getEnchant(armoron);
            int markpower = ItemStat.getMarkpower(armoron);
            health += RPGI.getHealth(mark,markpower,  enchant);
            armor += RPGI.getArmor(mark,markpower,  enchant);
            magicarmor += RPGI.getMagicArmor(mark,markpower,  enchant);
            protectbyeefect += RPGI.getProtectionByBleeding(mark,markpower,  enchant);
            returndamage += RPGI.getReturnDamage(mark,markpower,  enchant);
            dopdamage += RPGI.getDopDamage(mark,markpower,  enchant);
            resistance += RPGI.getResistance(mark,markpower,  enchant);
            dodge += RPGI.getDodge(mark,markpower,  enchant);
            regeneration += RPGI.getRegeneration(mark,markpower,  enchant);
            trueregeneration += RPGI.getTrueRegeneration(mark,markpower,  enchant);
            speed +=  RPGI.getSpeed(mark,markpower,  enchant);
            armorcritchance += RPGI.getCritChance(mark,markpower,  enchant);
            armorcritdamage += RPGI.getCritDamage(mark,markpower,  enchant);
            armorvampiric += RPGI.getVampiric(mark,markpower,  enchant);
        }
        if(this.arts != null){
            armoron = this.arts.getItem(1);
        }
        RPGI = RPGItemContainer.getRPGItemContainer(ItemStat.getID(armoron));
        if (armoron != null &&
                ItemStat.canuseitem(getRPGPlayer(this.player), armoron) && ItemStat.getType(armoron) == 4 && ItemStat.getMark(armoron) != 100) {
            int mark = ItemStat.getMark(armoron);
            int enchant = ItemStat.getEnchant(armoron);
            int markpower = ItemStat.getMarkpower(armoron);
            health += RPGI.getHealth(mark,markpower,  enchant);
            armor += RPGI.getArmor(mark,markpower,  enchant);
            magicarmor += RPGI.getMagicArmor(mark,markpower,  enchant);
            protectbyeefect += RPGI.getProtectionByBleeding(mark,markpower,  enchant);
            returndamage += RPGI.getReturnDamage(mark,markpower,  enchant);
            dopdamage += RPGI.getDopDamage(mark,markpower,  enchant);
            resistance += RPGI.getResistance(mark,markpower,  enchant);
            dodge += RPGI.getDodge(mark,markpower,  enchant);
            regeneration += RPGI.getRegeneration(mark,markpower,  enchant);
            trueregeneration += RPGI.getTrueRegeneration(mark,markpower,  enchant);
            speed +=  RPGI.getSpeed(mark,markpower,  enchant);
            armorcritchance += RPGI.getCritChance(mark,markpower,  enchant);
            armorcritdamage += RPGI.getCritDamage(mark,markpower,  enchant);
            armorvampiric += RPGI.getVampiric(mark,markpower,  enchant);
        }
        if(this.arts != null){
            armoron = this.arts.getItem(2);
        }
        RPGI = RPGItemContainer.getRPGItemContainer(ItemStat.getID(armoron));
        if (armoron != null &&
                ItemStat.canuseitem(getRPGPlayer(this.player), armoron) && ItemStat.getType(armoron) == 4 && ItemStat.getMark(armoron) != 100) {
            int mark = ItemStat.getMark(armoron);
            int enchant = ItemStat.getEnchant(armoron);
            int markpower = ItemStat.getMarkpower(armoron);
            health += RPGI.getHealth(mark,markpower,  enchant);
            armor += RPGI.getArmor(mark,markpower,  enchant);
            magicarmor += RPGI.getMagicArmor(mark,markpower,  enchant);
            protectbyeefect += RPGI.getProtectionByBleeding(mark,markpower,  enchant);
            returndamage += RPGI.getReturnDamage(mark,markpower,  enchant);
            dopdamage += RPGI.getDopDamage(mark,markpower,  enchant);
            resistance += RPGI.getResistance(mark,markpower,  enchant);
            dodge += RPGI.getDodge(mark,markpower,  enchant);
            regeneration += RPGI.getRegeneration(mark,markpower,  enchant);
            trueregeneration += RPGI.getTrueRegeneration(mark,markpower,  enchant);
            speed +=  RPGI.getSpeed(mark,markpower,  enchant);
            armorcritchance += RPGI.getCritChance(mark,markpower,  enchant);
            armorcritdamage += RPGI.getCritDamage(mark,markpower,  enchant);
            armorvampiric += RPGI.getVampiric(mark,markpower,  enchant);
        }

        this.health = 20 + RPGLevels.getHealthForLevel(this.level) + health ;
        if(this.getTalents().get("Survivalinstinct") != null && this.getTalents().get("Survivalinstinct") > 0){
            if(this.getActivatedtalents().get("Survivalinstinct")){
                this.health = (int) getMaxHealth() +  (getMaxHealth()  * (0.05D * this.getTalents().get("Survivalinstinct")));
            }

        }
        this.armor = armor;
        this.magicarmor = magicarmor;
        this.protectbyeffect = protectbyeefect;
        this.maxmana = 100 + this.level + maxmana;
        if(this.getTalents().get("Magicalfoundation") != null && this.getTalents().get("Magicalfoundation") > 0){
            if(this.getActivatedtalents().get("Magicalfoundation")){
                this.maxmana = (int) (this.maxmana + Math.round((float) this.maxmana  * (0.05D * this.getTalents().get("Magicalfoundation"))));
            }

        }
        this.manaregeneration = 2 + manaregeneration;
        this.returndamage = returndamage;
        this.dopdamage = dopdamage;
        this.resistance = (int) (resistance);
        this.dodge = dodge ;
        this.dodgepower = 25 + dodgepower;
        this.regeneration = regeneration + (int)(2.0D + getRPGPlayer(this.player).getMaxHealth() * 0.05D);
        this.trueregeneration = trueregeneration;
        if(speed == 0){
            toPlayer().setWalkSpeed(0.2f);
        }
        if(speed > 0){
            toPlayer().setWalkSpeed((float) (toPlayer().getWalkSpeed() + (0.2 * ((double) speed / 100))));
        }
        this.armorcritchance = armorcritchance;
        this.armorcritdamage = armorcritdamage;
        this.armorvampiric = armorvampiric;
    }



    public int getCritChance() {
        return this.armorcritchance + this.pluscritchance;
    }

    public int getCritDamage() {
        return this.armorcritdamage + this.pluscritdamage;
    }

    public int getVampiric() {
        return this.armorvampiric + this.plusvampiric;
    }

    public int getHealth() {
        return ((MetadataValue)toPlayer().getMetadata("health").get(0)).asInt();
    }

    public double getMaxHealth() {
        if (this.health != 0)
            return this.health;
        return 1;
    }

    public int getMana() {
        return ((MetadataValue)toPlayer().getMetadata("mana").get(0)).asInt();
    }

    public int getMaxMana() {
        if (this.maxmana != 0)
            return this.maxmana;
        return 1;
    }


    public int getArmor() {
        return Math.max(this.armor, 0);
    }
    public int getReturnDamage() {
        return Math.max(this.returndamage, 0);
    }
    public int getDopdamage() {
        return Math.max(this.dopdamage, 0);
    }
    public int getProtectbyeffect() {
        return Math.max(this.protectbyeffect, 0);
    }


    public int getMagicArmor() {
        return Math.max(this.magicarmor, 0);
    }

    public int getResistance() {
        return Math.max(this.resistance, 0);
    }

    public int getRegeneration() {
        return Math.max(this.regeneration, 0);
    }

    public int getDodge() {
        return Math.max(this.dodge, 0);
    }

    public int getDodgePower() {
        return Math.max(this.dodgepower, 0);
    }

    public int getTrueRegeneration() {
        return Math.max(this.trueregeneration, 0);
    }


    public void setPlusDamage(int amount) {
        this.plusdamage = amount;
    }

    public void setPlusMagicDamage(int amount) {
        this.plusmagicdamage = amount;
    }

    public void setPlusFireDamage(double amount) {
        this.plusfiredamage = amount;
    }

    public void setPlusCritChance(int amount) {
        this.pluscritchance = amount;
    }

    public void setPlusCritDamage(int amount) {
        this.pluscritdamage = amount;
    }

    public void setPlusVampiric(int amount) {
        this.plusvampiric = amount;
    }

    public List<Integer> getLearnedSpells() {
        return this.learnedSpells;
    }

    public void addLearnedSpells(int id) {
        this.learnedSpells.add(Integer.valueOf(id));
    }

    public void clearLearnedSpells() {
        this.learnedSpells.clear();
    }

    public int getSpellBind(@NotNull String bind) {
        switch (bind) {
            case "R":
                return this.spellR;
            case "RR":
                return this.spellRR;
            case "RL":
                return this.spellRL;
            case "RRR":
                return this.spellRRR;
            case "RRL":
                return this.spellRRL;
            case "RLR":
                return this.spellRLR;
            case "RLL":
                return this.spellRLL;
            case "F":
                return this.spellF;
        }
        return 0;
    }

    public void setSpellBind(String bind, int spellid) {
        switch (bind) {
            case "R":
                this.spellR = spellid;
                break;
            case "RR":
                this.spellRR = spellid;
                break;
            case "RL":
                this.spellRL = spellid;
                break;
            case "RRR":
                this.spellRRR = spellid;
                break;
            case "RRL":
                this.spellRRL = spellid;
                break;
            case "RLR":
                this.spellRLR = spellid;
                break;
            case "RLL":
                this.spellRLL = spellid;
                break;
            case "F":
                this.spellF = spellid;
                break;
        }
    }

    public void clearSpellBinds() {
        this.spellR = 0;
        this.spellRR = 0;
        this.spellRL = 0;
        this.spellRRR = 0;
        this.spellRRL = 0;
        this.spellRLR = 0;
        this.spellRLL = 0;
        this.spellF = 0;
    }

    public boolean isTrading() {
        return RpgTrade.isTrading(this);
    }



    public long getUnwantedPlayer() {
        return this.unwantedPlayer;
    }

    public void setUnwantedPlayer(long amount) {
        this.unwantedPlayer = System.currentTimeMillis() + amount;
    }


    public int getActiveSpell() {
        return this.activespell;
    }

    public void setActiveSpell(int id) {
        this.activespell = id;
    }

    public String[] getStreak() {
        return this.streak;
    }

    public void setStreak(int array, String type) {
        this.streak[array] = type;
    }

    public String getSpellstring() {
        return this.spellstring;
    }

    public void setSpellstring(String text) {
        this.spellstring = text;
    }

    public boolean getNowDie() {
        return this.nowDie;
    }

    public void setNowDie(boolean died) {
        this.nowDie = died;
    }

    public ItemStack getSBItem() {
        return this.sbitem;
    }

    public void setSBItem(ItemStack item) {
        this.sbitem = item;
    }

    public String getLastLoc() {
        return this.lastloc;
    }

    public void setLastLoc(String loc) {
        this.lastloc = loc;
    }

    public int getSaveZone() {
        return this.savezone;
    }

    public void setSaveZone(int amount) {
        if(amount == 1){
            toPlayer().sendMessage(WorldUtils.worldName() + " §2Вы вошли безопасную зону!");
        }else{
            toPlayer().sendMessage(WorldUtils.worldName() + " §cВы покинули безопасную зону!");
        }
        this.savezone = amount;
    }

    public double getCurseBlood() {
        return this.curseBlood;
    }

    public void setCurseBlood(double amount) {
        this.curseBlood = amount;
    }

    public int getCurseFear() {
        return this.curseFear;
    }
    public int getManaRegeneration() {
        return Math.max(this.manaregeneration, 0);
    }

    public void setCurseFear(int amount) {
        this.curseFear = amount;
    }

    public double getCurseMovement() {
        return this.curseMovement;
    }

    public void setCurseMovement(double amount) {
        this.curseMovement = amount;
    }

    public Location getCurseMovementLoc() {
        return this.curseMovementLoc;
    }

    public void setCurseMovementLoc(Location loc) {
        this.curseMovementLoc = loc;
    }

    public double getCurseRevenge() {
        return this.curseRevenge;
    }

    public void setCurseRevenge(double amount) {
        this.curseRevenge = amount;
    }

    public double getCurseRevengeTime() {
        return this.curseRevengeTime;
    }

    public void setCurseRevengeTime(int amount) {
        this.curseRevengeTime = amount;
    }

    public double getCurseDamage() {
        return this.curseDamage;
    }

    public void setCurseDamage(double amount) {
        this.curseDamage = amount;
    }

    public int getCurseSilence() {
        return this.curseSilence;
    }

    public void setCurseSilence(int amount) {
        this.curseSilence = amount;
    }

    public int getCurseMana() {
        return this.curseMana;
    }

    public void setCurseMana(int amount) {
        this.curseMana = amount;
    }

    public int getCurseCorrosion() {
        return this.curseCorrosion;
    }

    public void setCurseCorrosion(int amount) {
        this.curseCorrosion = amount;
    }

    public int getVortex() {
        return this.vortex;
    }

    public void setVortex(int amount) {
        this.vortex = amount;
    }

    public void setPlusHealth(double amount) {
        this.setPlusHealth = amount;
    }

    public int getVortexVec() {
        return this.vortexVec;
    }

    public void setVortexVec(int amount) {
        this.vortexVec = amount;
    }
    public String getPrefix() {
        return this.prefix;
    }


}
