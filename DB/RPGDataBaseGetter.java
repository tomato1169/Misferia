package azerot.azerot.DB;

import azerot.azerot.*;
import azerot.azerot.RPG.LocationsMobs;
import azerot.azerot.RPG.RPGGrave;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import azerot.azerot.mobs.RPGMobsContainer;

import java.io.File;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RPGDataBaseGetter {
    private azerot plugin;

    public RPGDataBaseGetter(azerot plugin) {
        this.plugin = plugin;
    }


    public void createTable() {
        try {
            StringBuilder tableConstructor = new StringBuilder();
            tableConstructor.append("CREATE TABLE IF NOT EXISTS ");
            tableConstructor.append("items");
            tableConstructor.append(" (");
            tableConstructor.append("id int(255) UNSIGNED NOT NULL AUTO_INCREMENT,");
            tableConstructor.append("material varchar(64) NOT null,");
            tableConstructor.append("submaterial int(1) DEFAULT '0',");
            tableConstructor.append("enchant varchar(32) DEFAULT null,");
            tableConstructor.append("enchantpower int(3) DEFAULT null,");
            tableConstructor.append("color varchar(32) DEFAULT NULL,");
            tableConstructor.append("name varchar(64) NOT NULL,");
            tableConstructor.append("type int(3) DEFAULT '1',");
            tableConstructor.append("quality int(3) DEFAULT '1',");
            tableConstructor.append("damage int(9) DEFAULT '0',");
            tableConstructor.append("maxdamage int(9) DEFAULT '0',");
            tableConstructor.append("waterDamage int(9) DEFAULT '0',");
            tableConstructor.append("maxWaterDamage int(9) DEFAULT '0',");
            tableConstructor.append("firedamage int(9) DEFAULT '0',");
            tableConstructor.append("maxFiredamage int(9) DEFAULT '0',");
            tableConstructor.append("darkdamage int(9) DEFAULT '0',");
            tableConstructor.append("maxDarkdamage int(9) DEFAULT '0',");
            tableConstructor.append("naturedamage int(9) DEFAULT '0',");
            tableConstructor.append("maxdNaturedamage int(9) DEFAULT '0',");
            tableConstructor.append("lightdamage int(9) DEFAULT '0',");
            tableConstructor.append("maxdLightdamage int(9) DEFAULT '0',");
            tableConstructor.append("magicdamage int(9) DEFAULT '0',");
            tableConstructor.append("maxmagicdamage int(9) DEFAULT '0',");
            tableConstructor.append("maxpuredamage int(9) DEFAULT '0',");
            tableConstructor.append("critchance int(4) DEFAULT '0',");
            tableConstructor.append("critdamage int(4) DEFAULT '0',");
            tableConstructor.append("vampiric int(4) DEFAULT '0',");
            tableConstructor.append("health int(9) DEFAULT '0',");
            tableConstructor.append("armor int(9) DEFAULT '0',");
            tableConstructor.append("magicarmor int(9) DEFAULT '0',");
            tableConstructor.append("resistance int(9) DEFAULT '0',");
            tableConstructor.append("regeneration int(9) DEFAULT '0',");
            tableConstructor.append("dodge int(4) DEFAULT '0',");
            tableConstructor.append("trueregeneration int(9) DEFAULT '0',");
            tableConstructor.append("lore1 varchar(128) DEFAULT null,");
            tableConstructor.append("lore2 varchar(128) DEFAULT null,");
            tableConstructor.append("lore3 varchar(128) DEFAULT null,");
            tableConstructor.append("lore4 varchar(128) DEFAULT null,");
            tableConstructor.append("lore5 varchar(128) DEFAULT null,");
            tableConstructor.append("itemprice int(9) DEFAULT '0',");
            tableConstructor.append("level int(9) DEFAULT '0',");
            tableConstructor.append("itemlevel int(9) DEFAULT '0',");
            tableConstructor.append("strength int(9) DEFAULT '0',");
            tableConstructor.append("agility int(9) DEFAULT '0',");
            tableConstructor.append("intelligence int(9) DEFAULT '0',");
            tableConstructor.append("mininglevel int(9) DEFAULT '0',");
            tableConstructor.append("soulbound int(3) DEFAULT '0',");
            tableConstructor.append("spellid int(3) DEFAULT '0',");
            tableConstructor.append("hasMark int(3) DEFAULT '0',");
            tableConstructor.append("recipe int(3) DEFAULT '0',");
            tableConstructor.append("teleport varchar(32) DEFAULT null,");
            tableConstructor.append("PRIMARY KEY (id), UNIQUE(id), INDEX(id)");
            tableConstructor.append(") ENGINE=InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci");
            Statement rs = azerot.db.getHikari().getConnection().createStatement();
            rs.executeUpdate(tableConstructor.toString());
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void GraveTable() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS rpggrave (id int(255) UNSIGNED NOT NULL AUTO_INCREMENT,world varchar(16) ,x int(16) ,y int(16) ,z int(16) ,yaw int(16) ,pitch int(16) ,teleportTo int(16) ,requiredQuest int(16) ,PRIMARY KEY (id), UNIQUE(id), INDEX(id) ) ENGINE=InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci";
            Statement rs = azerot.db.getHikari().getConnection().createStatement();
            rs.executeUpdate(sql);
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void table() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS players (name varchar(16) NOT NULL, level int(255) NOT NULL DEFAULT '1'  , skillpoint INT(255) ,xp INT(255) ,xpModifier INT(255) ,strength INT(255) , faith INT(255) ,luck INT(255) ,intelligence INT(255) ,physical INT(255) ,scholarship INT(255) ,durability INT(255) ,agility INT(255) ,lifeforce INT(255) ,ecsize INT(255) ,learnedSpells INT(255) ,permession varchar(255),quests varchar(1000), mobs varchar(1000), inv varchar(1000), armor varchar(1000), PRIMARY KEY (name), UNIQUE(name), INDEX(name) ) ENGINE=InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci";
            Statement rs = azerot.db.getHikari().getConnection().createStatement();
            rs.executeUpdate(sql);
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void QuestTable() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS quests (id int(255),name varchar(16) DEFAULT 'null' , lore varchar(700) DEFAULT 'null', xp int(25) NOT NULL DEFAULT '0' ,rLevel int(9) DEFAULT '0', type int(9) ,mobs varchar(50) ,items varchar(50) ,rQuest int(9) ,teleport int(9) ,reward int(9) ,rInPQuest int(9) ,cQuest int(9) ,enter_items varchar(50) ,PRIMARY KEY (id), UNIQUE(id), INDEX(id) ) ENGINE=InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci";
            Statement rs = azerot.db.getHikari().getConnection().createStatement();
            rs.executeUpdate(sql);
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void MobsTable() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS mobs (id int(255),type varchar(16) DEFAULT 'null' , name varchar(16) DEFAULT 'null', health int(9) ,respawn int(9) NOT NULL DEFAULT '0'  , level int(9) ,xp int(9) ,damage int(9) ,magicdamage int(9) ,critchance int(9) ,vampiric int(9) ,mobrange int(9) ,armor int(9) ,magicarmor int(9) ,resistance int(9) ,rangeAgr int(9) ,attackspeed int(9) ,dodge int(9) ,aggressive int(9) ,summoned int(9) ,invisible int(9) ,script varchar(16) ,speed int(255) ,hand varchar(16) ,helmet varchar(16) ,chestplate varchar(16) ,leggings varchar(16) ,boots varchar(16) ,offhand varchar(16),PRIMARY KEY (id), UNIQUE(id), INDEX(id) ) ENGINE=InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci";
            Statement rs = azerot.db.getHikari().getConnection().createStatement();
            rs.executeUpdate(sql);
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void spawnMobs() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS spawnMobs (id int(255) UNSIGNED NOT NULL AUTO_INCREMENT," +
                    "locations text(100000) , PRIMARY KEY (id), UNIQUE(id), INDEX(id) ) ENGINE=InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci";
            Statement rs = azerot.db.getHikari().getConnection().createStatement();
            rs.executeUpdate(sql);
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void RecTable() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS recipes (id int(255) UNSIGNED NOT NULL AUTO_INCREMENT,items1 varchar(16) ,items2 varchar(16) ,craft varchar(16), PRIMARY KEY (id), UNIQUE(id), INDEX(id) ) ENGINE=InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci";
            Statement rs = azerot.db.getHikari().getConnection().createStatement();
            rs.executeUpdate(sql);
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    Gson gson = new Gson();


    public void loadRecipes() {
        new RPGRecipesContainer(null);
        try (Connection connection = azerot.db.getHikari().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM recipes WHERE id=? WHERE name=?")) {
            ResultSet result = ps.executeQuery("SELECT * FROM recipes");

            while (result.next()) {
                int id = result.getInt("id");
                Bukkit.getLogger().info("Recipes: " + id);
                new RPGRecipesContainer(result);
            }
            result.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void loadRespawnMobs() {
        new RPGRecipesContainer(null);
        try(Connection connection = azerot.db.getHikari().getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM spawnMobs WHERE id=?")) {
            ResultSet result = ps.executeQuery("SELECT * FROM spawnMobs");
            while (result.next()) {
                Integer name = result.getInt("id");
                String loc = result.getString("locations");
                List<Location> locationList = new ArrayList<>();
                String[] loca = loc.replace("[", "").replace("]", "").split(",");
                for (int i = 1; i < loca.length; i++) {
                    String locat = loca[i].replace("x:", "").replace("y:", "").replace("z:", "").replace("yaw:", "").replace("pitch:", "").replace(" ", "");
                    String[] locati = locat.split("/");
                    String world = locati[0];
                    Double x = Double.valueOf(locati[1]);
                    Double y = Double.valueOf(locati[2]);
                    Double z = Double.valueOf(locati[3]);
                    Float yaw = Float.valueOf(locati[4]);
                    Float pitch = Float.valueOf(locati[5]);
                    Location location = new Location(Bukkit.getWorld(world), x.doubleValue(), y.doubleValue(), z.doubleValue(), yaw.floatValue(), pitch.floatValue());
                    if(locationList.isEmpty()) {
                        locationList.add(0, location);
                    }
                    locationList.add(i, location);
                }
                azerot.locationsMobs.put(name, locationList);
            }
            result.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void loadBreak() {
        try (Connection connection = azerot.db.getHikari().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM locationBreak")) {
            ResultSet result = ps.executeQuery("SELECT * FROM locationBreak");
            while (result.next()) {
                int type = result.getInt("type");
                int x = result.getInt("x");
                int y = result.getInt("y");
                int z = result.getInt("z");

                if(type == 1){
                    Location location = new Location(Bukkit.getWorld("world"), x, y, z);
                    location.getBlock().setType(Material.IRON_ORE);
                    azerot.getLocationsBrakeFirst().add(location);
                }
            }
            result.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void loadGrave() throws SQLException {
        new RPGGrave(null);
        try (Connection connection = azerot.db.getHikari().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM rpggrave WHERE id=?")) {
            ResultSet result = ps.executeQuery("SELECT * FROM rpggrave");

            while (result.next()) {
                int id = result.getInt("id");
                Bukkit.getLogger().info("rpggrave: " + id);
                new RPGGrave(result);
            }
            result.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



    public void loadItems() {
        new RPGItemContainer(null);
        try (Connection connection = azerot.db.getHikari().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM items WHERE id=? WHERE name=?")) {
            ResultSet result = ps.executeQuery("SELECT * FROM items");

            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                Bukkit.getLogger().info("ITEM: " + id + "/" + name);
                new RPGItemContainer(result);
            }
            result.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void loadquests() {
        new RPGQuestsContainer(null);
        try (Connection connection = azerot.db.getHikari().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM quests WHERE id=?")) {
            ResultSet result = ps.executeQuery("SELECT * FROM quests");

            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                Bukkit.getLogger().info("quest: " + id + "/" + name);
                new RPGQuestsContainer(result);
            }
            result.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void loadMobs(){
        new RPGMobsContainer(null);
        try (Connection connection = azerot.db.getHikari().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM mobs WHERE id=? WHERE name=?")) {
            ResultSet res= ps.executeQuery("SELECT * FROM mobs");

            while(res.next()){
                int id = res.getInt("id");
                String name = res.getString("name");
                Bukkit.getLogger().info("§aMobs: " + id + "/" + name);
                new RPGMobsContainer(res);
            }
            res.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}

