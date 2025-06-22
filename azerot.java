package azerot.azerot;

import azerot.azerot.DB.RPGDataBase;
import azerot.azerot.DB.RPGDataBaseGetter;
import azerot.azerot.Quest.NPC;
import azerot.azerot.Quest.Quest;
import azerot.azerot.RPGMob.*;
import azerot.azerot.command.*;
import azerot.azerot.events.*;
import azerot.azerot.mobs.MobSystem;
import azerot.azerot.mobs.RPGMobsContainer;
import azerot.azerot.regions.RegionEnter;
import azerot.azerot.utils.urlSkinsByID;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import de.slikey.effectlib.EffectManager;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;

import java.io.File;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.*;


public final class azerot extends JavaPlugin implements PluginMessageListener {
    private static azerot instance;

    public static RPGDataBase db;


    public static String quest = ";";

    static Gson gson = new Gson();
    @Getter
    private List<Integer> AllItemsIdSecondPatch = new ArrayList<>();

    @Getter
    public static HashMap<String, Inventory> inventorys = new HashMap<>();

    @Getter
    private List<Integer> items = new ArrayList<>();

    @Getter
    private List<Integer> mobsInPatchs = new ArrayList<>();

    @Getter
    private List<Integer> AllItemsIdFirstPatch = new ArrayList<>();

    @Getter
    private List<Integer> AllItemsIdThirdPatch = new ArrayList<>();


    @Getter
    private HashMap<String, List<Integer>> ItemsForType = new HashMap<>();


    @Getter
    private List<Integer> AllMobsIdFirstPatch = new ArrayList<>();

    @Getter
    private List<Integer> AllMobsIdSecondPatch = new ArrayList<>();

    @Getter
    private List<Integer> AllMobsIdThirdPatch = new ArrayList<>();

    public static String mobs;

    public static RPGDataBaseGetter data;

    public static int qsts;

    public static int patch = 0;

    private static EffectManager em;

    private static WorldGuardPlugin wg;

    public static HashMap<String, Object> donate = new HashMap<>();

    @Getter
    public static HashMap<Integer, String> EntitySkinsURL = new HashMap<>();

    public Boolean routerecording = false;

    @Getter
    private static List<Location> locationsBrakeFirst = new ArrayList<>();

    public static HashMap<String, List<Location>> pathRecord = new HashMap<>();
    public static HashMap<String, List<String>> recordSave = new HashMap<>();

    @Getter
    public static HashMap<Integer, List<Location>> locationsMobs = new HashMap<>();

    public Boolean getRouterecording() {
        return routerecording;
    }

    public void setRouterecording(Boolean routerecording) {
        this.routerecording = routerecording;
    }

    private static ProtocolManager protocolManager;


    private double globalXPbyDonate;
    @Getter
    @Setter
    private int clonesAlchemist = 0;

    @Getter
    private static List<Entity> entityForBossInThreePatch = new ArrayList<>();

    @Getter
    private BossBar barGlobalXP = Bukkit.getServer().createBossBar("", BarColor.GREEN, BarStyle.SOLID);





    public void onEnable() {
        instance = this;
        donate.put("additionalDrop", 0);
        donate.put("additionalXP", 0);
        donate.put("purchasedXP", false);
        donate.put("purchasedDROP", false);
        donate.put("timeXP", 0);
        donate.put("timeDROP", 0);
        donate.put("boughtBYPlayerXP", "");
        donate.put("boughtBYPlayerDROP", "");
        Scoreboard scoreboard = this.getServer().getScoreboardManager().getMainScoreboard();

        em = new EffectManager((Plugin)instance);
        wg = (WorldGuardPlugin)Bukkit.getPluginManager().getPlugin("WorldGuard");
        db = new RPGDataBase();
        data = new RPGDataBaseGetter(this);
        protocolManager = ProtocolLibrary.getProtocolManager();
        patch = 2;

        try {
            db.connect();
            Bukkit.getLogger().info("DataBase is connected!");
        } catch (ClassNotFoundException e) {
            Bukkit.getLogger().info("DataBase not connected!");
            e.printStackTrace();
        } catch (SQLException e) {
            Bukkit.getLogger().info("DataBase not connected!");
            e.printStackTrace();
        }
        try {
            if(db.isConnected()){
                data.loadItems();
                data.createTable();
                data.MobsTable();
                data.table();
                data.GraveTable();
                data.QuestTable();
                data.spawnMobs();
                Bukkit.getLogger().info("Database items connect");
            } else{
                Bukkit.getLogger().info("Database items not connect");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            if(db.isConnected()){
                data.loadMobs();
                Bukkit.getLogger().info("Database mobs connect");
            } else{
                Bukkit.getLogger().info("Mobs not connect");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if(db.isConnected()){
                data.loadRecipes();
                Bukkit.getLogger().info("Database rec connect");
            } else{
                Bukkit.getLogger().info("Rec not connect");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if(db.isConnected()){
                data.loadquests();
                data.loadBreak();
                Bukkit.getLogger().info("Database quests connect");
            } else{
                Bukkit.getLogger().info("quests not connect");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if(db.isConnected()){
                try {
                    data.loadGrave();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                Bukkit.getLogger().info("Database grave connect");
            } else{
                Bukkit.getLogger().info("grave not connect");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if(db.isConnected()){
                data.RecTable();
                data.loadRespawnMobs();
                Bukkit.getLogger().info("Database locations connect");
            } else{
                Bukkit.getLogger().info("Database locations not connect");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        new Grant();
        new Spawnadd();
        new Xp();
        new Lags();
        new top();
        new Kill();
        new QuestCommand();
        new auc();
        new checkdb();
        new trade();
        new BroadCast();
        new record();
        new donate();
        new calc();
        new skipCutscene();
        new spawn();
        new resetQuest();
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "message:main");
        Bukkit.getMessenger().registerIncomingPluginChannel((Plugin) this, "message:main", this);
        Bukkit.getPluginManager().registerEvents(new Handler(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new JoinPlayer(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new leavePlayer(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new Fishing(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new ArmorSwapEvent(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CustomTab(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new RegionEnter(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new RPGRegeneration(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new WorldUtils(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new ClickInGui(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new DamageSystem(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new DamageSystemPVP(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new MobSystem(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new SpellUse(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new EventClickInDB(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new CraftingMenu(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new NPC(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new PlayerCloseInventory(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new RPGBreak(), (Plugin) this);
        Bukkit.getPluginManager().registerEvents(new PlayerRightClickItem(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new RPGPlayerShoot(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickEmblems(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickShop(), (Plugin)this);
        NMSR.registerEntity("witherskeleton", 5, (Class)RPGWitherSkeleton.class);
        NMSR.registerEntity("bower", 54, (Class)RPGBower.class);
        NMSR.registerEntity("spider", 52, (Class)RPGSpider.class);
        NMSR.registerEntity("zombie", 54, (Class)RPGZombie.class);
        NMSR.registerEntity("cavespider", 59, (Class)RPGCaveSpider.class);
        NMSR.registerEntity("blaze", 61, (Class)RPGBlaze.class);
        NMSR.registerEntity("witch", 66, (Class)RPGWitch.class);
        NMSR.registerEntity("cow", 92, (Class)RPGCow.class);
        NMSR.registerEntity("wolf", 95, (Class)RPGWolf.class);
        NMSR.registerEntity("cat", 98, (Class)RPGCat.class);
        NMSR.registerEntity("golem", 99, (Class)RPGGolem.class);
        NMSR.registerEntity("human", 120, (Class)RPGHuman.class);


        ClickPacketNPC npc = new ClickPacketNPC(this);
        npc.onPacketClick();
        File config = new File(getDataFolder() + File.separator + "config.yml");
        if(Bukkit.getMessenger().getOutgoingChannels() != null){
            Bukkit.getLogger().info("" + Bukkit.getMessenger().getOutgoingChannels());
        }
        File file = new File(azerot.getInstance().getDataFolder() + File.separator + "config.yml");
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        List<String> records = yamlConfiguration.getStringList("records");
        try {
            WorldUtils.spawnAllMobs();
            MobSystem.setSpawn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Cycles.cycles();
        Cycles.clearItems();


        Bukkit.createWorld(new WorldCreator("realm2"));


        try (Connection connection = db.getHikari().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM quests WHERE id=? WHERE name=?")) {
            ResultSet result = ps.executeQuery("SELECT * FROM quests");
            while(result.next()) {
                int id = result.getInt("id");

                int i = 1;
                while (RPGQuestsContainer.getRPGQuestsContainer(i) != null) {
                    new Quest(String.valueOf(i));
                    i++;
                    if (id > qsts)
                        qsts = id;
                }
            }
            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                Cycles.actionbar(player);
                Cycles.nameforhead(player);
                Cycles.regeneration(player);
                savePlayer();
                try {
                    rpgplayer p = new rpgplayer(player);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                rpgplayer.resetPlayer(player.getName());
                (new RPGItem()).updateItem(player);

            }
            result.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            (new BukkitRunnable() {
                public void run() {
                    for(int i = 1; i < RPGItemContainer.getAllItems(); i++){
                        if(RPGItemContainer.getRPGItemContainer(items.get(i)) == null){
                            return;
                        }
                        if(RPGItemContainer.getRPGItemContainer(items.get(i)).getPatch() == 1){
                            AllItemsIdFirstPatch.add(items.get(i));
                        }
                        if(RPGItemContainer.getRPGItemContainer(items.get(i)).getPatch() == 2){
                            AllItemsIdSecondPatch.add(items.get(i));

                        }
                        if(RPGItemContainer.getRPGItemContainer(items.get(i)).getPatch() == 3){
                            AllItemsIdThirdPatch.add(items.get(i));
                        }
                    }
                    for(int i = 1; i < RPGMobsContainer.getRpgmobs().size(); i++){
                        if(RPGMobsContainer.getRPGMobsContainer(i) == null){
                            return;
                        }
                        if(RPGMobsContainer.getRPGMobsContainer(mobsInPatchs.get(i)).getPatch() == 1){
                            AllMobsIdFirstPatch.add(mobsInPatchs.get(i));
                        }
                        if(RPGMobsContainer.getRPGMobsContainer(mobsInPatchs.get(i)).getPatch() == 2){
                            AllMobsIdSecondPatch.add(mobsInPatchs.get(i));

                        }
                        if(RPGMobsContainer.getRPGMobsContainer(mobsInPatchs.get(i)).getPatch() == 3){
                            AllMobsIdThirdPatch.add(mobsInPatchs.get(i));
                        }
                    }
                }

            }).runTaskLaterAsynchronously((Plugin)azerot.getInstance(), 60L);

            (new BukkitRunnable() {
                public void run() {
                    List<Integer> typeOneFirstPatch = new ArrayList<>();
                    List<Integer> typeTwoFirstPatch = new ArrayList<>();
                    List<Integer> typeThreeFirstPatch = new ArrayList<>();

                    for(int i = 1; i < AllItemsIdFirstPatch.size(); i++){
                        if(RPGItemContainer.getRPGItemContainer(AllItemsIdFirstPatch.get(i)).getType() == 1){
                            typeOneFirstPatch.add(AllItemsIdFirstPatch.get(i));
                        }
                        if(RPGItemContainer.getRPGItemContainer(AllItemsIdFirstPatch.get(i)).getType() == 2){
                            typeTwoFirstPatch.add(AllItemsIdFirstPatch.get(i));
                        }
                        if(RPGItemContainer.getRPGItemContainer(AllItemsIdFirstPatch.get(i)).getType() == 3){
                            typeThreeFirstPatch.add(AllItemsIdFirstPatch.get(i));
                        }
                    }
                    Bukkit.broadcastMessage("айтемы 1 патча: " + typeOneFirstPatch.size());
                    Bukkit.broadcastMessage("айтемы 1 патча: " + typeTwoFirstPatch.size());
                    Bukkit.broadcastMessage("айтемы 1 патча: " + typeThreeFirstPatch.size());
                    ItemsForType.put("typeOneFirstPatch", typeOneFirstPatch);
                    ItemsForType.put("typeTwoFirstPatch", typeTwoFirstPatch);
                    ItemsForType.put("typeThreeFirstPatch", typeThreeFirstPatch);

                    List<Integer> typeOneSecondPatch = new ArrayList<>();
                    List<Integer> typeTwoSecondPatch = new ArrayList<>();
                    List<Integer> typeThreeSecondPatch = new ArrayList<>();
                    for(int i = 1; i < AllItemsIdSecondPatch.size(); i++){
                        if(RPGItemContainer.getRPGItemContainer(AllItemsIdSecondPatch.get(i)).getType() == 1){
                            typeOneSecondPatch.add(AllItemsIdSecondPatch.get(i));
                        }
                        if(RPGItemContainer.getRPGItemContainer(AllItemsIdSecondPatch.get(i)).getType() == 2){
                            typeTwoSecondPatch.add(AllItemsIdSecondPatch.get(i));
                        }
                        if(RPGItemContainer.getRPGItemContainer(AllItemsIdSecondPatch.get(i)).getType() == 3){
                            typeThreeSecondPatch.add(AllItemsIdSecondPatch.get(i));
                        }
                    }
                    Bukkit.broadcastMessage("айтемы 2 патча: " + typeOneSecondPatch.size());
                    Bukkit.broadcastMessage("айтемы 2 патча: " + typeTwoSecondPatch.size());
                    Bukkit.broadcastMessage("айтемы 2 патча: " + typeThreeSecondPatch.size());
                    ItemsForType.put("typeOneSecondPatch", typeOneSecondPatch);
                    ItemsForType.put("typeTwoSecondPatch", typeTwoSecondPatch);
                    ItemsForType.put("typeThreeSecondPatch", typeThreeSecondPatch);


                    List<Integer> typeOneThirdPatch = new ArrayList<>();
                    List<Integer> typeTwoThirdPatch = new ArrayList<>();
                    List<Integer> typeThreeThirdPatch = new ArrayList<>();
                    for(int i = 1; i < AllItemsIdThirdPatch.size(); i++){
                        if(RPGItemContainer.getRPGItemContainer(AllItemsIdThirdPatch.get(i)).getType() == 1){
                            typeOneThirdPatch.add(AllItemsIdThirdPatch.get(i));
                        }
                        if(RPGItemContainer.getRPGItemContainer(AllItemsIdThirdPatch.get(i)).getType() == 2){
                            typeTwoThirdPatch.add(AllItemsIdThirdPatch.get(i));
                        }
                        if(RPGItemContainer.getRPGItemContainer(AllItemsIdThirdPatch.get(i)).getType() == 3){
                            typeThreeThirdPatch.add(AllItemsIdThirdPatch.get(i));
                        }
                    }

                    ItemsForType.put("typeOneThirdPatch", typeOneThirdPatch);
                    ItemsForType.put("typeTwoThirdPatch", typeTwoThirdPatch);
                    ItemsForType.put("typeThreeThirdPatch", typeThreeThirdPatch);
                }

            }).runTaskLaterAsynchronously((Plugin)azerot.getInstance(), 80L);

        } catch (Exception e){
            e.printStackTrace();
        }
        (new BukkitRunnable() {
            public void run() {
                urlSkinsByID.onCreateURL();
                CreateInventory.createInvenotory();
            }
        }).runTaskLater((Plugin)azerot.getInstance(), 300L);
        }


    public void onDisable() {
        WorldUtils.clearEntity();
        WorldUtils.clearDrop();
        getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        getServer().getMessenger().unregisterIncomingPluginChannel(this);

        try {
            savePlayerOnRestart();
            saveMobs();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (db.isConnected())
                db.disconnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for(World world : Bukkit.getWorlds()){
            if(!world.getName().equals("world")){
                Bukkit.unloadWorld(world, true);
            }

        }

    }

    public static void saveMobs() throws SQLException {
            try (Connection connection = azerot.db.getHikari().getConnection();
                 PreparedStatement ps = connection.prepareStatement("SELECT * FROM spawnMobs")) {
                for(Integer id : azerot.getLocationsMobs().keySet()) {
                    Bukkit.broadcastMessage("" + id);
                    List<String> lore = new ArrayList<>();
                    for (int i = 0; i < azerot.locationsMobs.get(id).size(); i++) {
                        Location loc = azerot.locationsMobs.get(id).get(i);
                        lore.add(loc.getWorld().getName() + "/x:" + loc.getX() + "/y:" + loc.getY() + "/z:" + loc.getZ() + "/yaw:" + loc.getYaw() + "/pitch:" + loc.getPitch());
                    }
                    ResultSet res = ps.executeQuery(String.format("SELECT * FROM spawnMobs WHERE id='%s'", id));
                    if (res.next()) {
                        ps.executeUpdate(String.format("UPDATE spawnMobs SET locations='%s' WHERE id='%s'", lore.toString(), id));

                    } else {
                        ps.executeUpdate("INSERT INTO spawnMobs (id, locations) VALUES ('" + id + "', '" + lore.toString() + "')");
                    }
                    res.close();
                }
                ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

    }

    public static void savePlayer(rpgplayer p) throws SQLException {
        Player pl = p.getPlayer();
        if(Objects.equals(pl.getWorld().getName(), p.getName())){
            pl.teleport(new Location(Bukkit.getWorld("world"),-4640,106, 3336));
            if(p.getStatusQuest(String.valueOf(51)) == 1){
                p.setStatusQuest(String.valueOf(51), 3);

            }

        }
        quest = gson.toJson(p.getQuests());
        mobs = gson.toJson(p.getKilledMobs());
        String dialogs = gson.toJson(p.getDialogueBetweenNPC());
        String queststage = gson.toJson(p.getQuestsStage());
        String learnedSpell = gson.toJson(p.getLearnedSpells());
        HashMap<Integer, Integer> spellFrom = new HashMap<>();
        spellFrom.put(0, p.getSpellR());
        spellFrom.put(1, p.getSpellRR());
        spellFrom.put(2, p.getSpellRRR());
        spellFrom.put(3, p.getSpellRLL());
        spellFrom.put(4, p.getSpellRLR());
        spellFrom.put(5, p.getSpellRRL());
        spellFrom.put(6, p.getSpellRL());
        spellFrom.put(7, p.getSpellF());
        String spells = gson.toJson(spellFrom);
        int money = p.getMoney();
        String settings = gson.toJson(p.getSettings());
        String talents = gson.toJson(p.getTalents());
        String activeTalents = gson.toJson(p.getActivatedtalents());
        String points = gson.toJson(p.getTalentsPoints());
        String inv = InventoryStringDeSerializer.InventoryToString(p.toPlayer().getInventory());
        String inv1 = InventoryStringDeSerializer.armorToSting(p.toPlayer().getInventory().getArmorContents(), p.toPlayer());
        String chest = InventoryStringDeSerializer.ECToString(p.getEC());
        String recipeschest = null;
        int RCEnabled = p.getRCEnabled() ? 1 : 0;
        if(p.getRCEnabled()){
            recipeschest = InventoryStringDeSerializer.InventoryToString(p.getRC());
        }
        StringBuilder location = new StringBuilder();
        Location loc = p.toPlayer().getLocation();
        location.append(loc.getWorld().getName() + " ");
        location.append(Math.round(loc.getX()) + " ");
        location.append(Math.round(loc.getY()) + " ");
        location.append(Math.round(loc.getZ()) + " ");
        location.append(Math.round(loc.getYaw()) + " ");
        location.append(Math.round(loc.getPitch()));
        try (Connection connection = azerot.db.getHikari().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM players")) {
            ps.executeUpdate(String.format("UPDATE players SET level='%s', xp='%s', xpModifier ='%s', skillpoint='%s',  ecsize='%s', inv='%s', armor='%s', quests='%s', mobs='%s', EC='%s', Money='%s', location='%s', learnedSpells ='%s', spells='%s', settings='%s', RC='%s', rcEnabled='%s', talents='%s', ActivatedTalents='%s', talentsPoints='%s', questStage='%s', dialogue='%s' WHERE name='%s' ",
                    p.getLevel(),  p.getXP(),  p.getXpModifier(),  p.getSkillpoint(),  p.getEcSize(), inv, inv1, quest, mobs, chest, money, location,learnedSpell, spells, settings,recipeschest, RCEnabled, talents, activeTalents, points, queststage, dialogs, p.getName()));

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public static void savePlayer() {
        int i = 0;
        for (rpgplayer player : rpgplayer.playershm.values()) {
                try {
                    savePlayer(player);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
        }
    }

    public static void savePlayerOnRestart() throws SQLException {
        for (rpgplayer player : rpgplayer.playershm.values())
            savePlayer(player);
    }

    public static RPGDataBase getDataBase() {
        return db;
    }

    public static RPGDataBaseGetter getData() {
        return data;
    }

    public static azerot getInstance() {
        return instance;
    }

    public static EffectManager getEffectManager() {
        return em;
    }

    public static WorldGuardPlugin getWorldGuard() {
        return wg;
    }

    public static ProtocolManager getProtocolManager() {
        return protocolManager;
    }

    public void setProtocolManager(ProtocolManager protocolManager) {
        this.protocolManager = protocolManager;
    }




    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] bytes) {
        Type type = new TypeToken<HashMap<String, Integer>>() {
        }.getType();
        try {
            if (!channel.equals("message:main")) {
                return;
            }
            ByteArrayDataInput in = ByteStreams.newDataInput(bytes);
            String subchannel = in.readUTF();
            Bukkit.getLogger().info("Да: " + subchannel);
            rpgplayer pl = null;
            switch (subchannel){
                case "BoostXP":
                    String bool = in.readUTF();
                    if(bool.equals("true")){
                        donate.put("boostXP", true);
                        Bukkit.broadcastMessage("Буст опыта активирован!");
                    }else{
                        donate.put("boostXP", false);
                        Bukkit.broadcastMessage("Буст опыта закончился!");
                    }
                    break;
                case "PlayerMessage":
                    String name = in.readUTF();
                    int group = in.readInt();
                    int gold1 = in.readInt();
                    pl = rpgplayer.getRPGPlayer(Bukkit.getPlayer(name));
                    pl.setGold(gold1);
                    pl.setGroup(group);
                    break;
                case "forParty":
                    String whattoUse = in.readUTF();
                    Bukkit.getLogger().info("Дошли до сюда 1");
                    if(whattoUse.equals("addPlayers")){
                        Bukkit.getLogger().info("Дошли до сюда 2");
                        String playername = in.readUTF();
                        String players = in.readUTF();
                        pl = rpgplayer.getRPGPlayer(Bukkit.getPlayer(playername));
                        if(!pl.getPlayersForparty().isEmpty()){
                            pl.getPlayersForparty().clear();
                        }
                        Bukkit.getLogger().info("" + players);
                        HashMap<String, Integer> read = gson.fromJson(players, type);
                        pl.setPlayersForparty(read);
                        Bukkit.getLogger().info("Игрочишки: " + read);
                    } else if (whattoUse.equals("ClearPlayers")) {
                        String playername = in.readUTF();
                        pl = rpgplayer.getRPGPlayer(Bukkit.getPlayer(playername));
                        if(!pl.getPlayersForparty().isEmpty()){
                            pl.getPlayersForparty().clear();
                        }
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
