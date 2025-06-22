package azerot.azerot.mobs;

import azerot.azerot.azerot;
import lombok.Getter;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
@Getter
@Setter
public class RPGMobsContainer {
    private int id;

    private String name;

    private int xp;

    private int damage;

    private int magicdamage;

    private int critchance;

    private int vampiric;
    private int mobrange;
    private int armor;
    private int magicarmor;
    private int resistance;
    private int range;
    private int attackspeed;
    private int dodge;
    private int aggressive;
    private int summoned;
    private int invisible;
    private int health;
    private String script;
    private int speed;
    private int hand;
    private String helmet;
    private String chestplate;

    private int BlackBlood;
    private String leggings;
    private String boots;
    private int offhand;

    private String type;

    private double respawn;
    private int frozen;
    private int stanchik;
    private int bleeding;
    private int curse;

    private String drop;
    private int ManaMob;

    private int level;
    private int typeMob;

    private int patch;

    private int slow;
    private int knockback;
    private String skin;

    private static final Map<Integer, RPGMobsContainer> rpgmobs = new HashMap<>();



    public RPGMobsContainer(ResultSet result) {
        try {
            if (result == null) {
                this.id = 0;
                this.name = null;
                this.level = 0;
                this.type = null;
                this.health = 0;
                this.respawn = 0;
                this.drop = null;
                this.xp = 0;
                this.BlackBlood = 0;
                this.damage = 0;
                this.magicdamage = 0;
                this.critchance = 0;
                this.vampiric = 0;
                this.slow = 0;
                this.frozen = 0;
                this.bleeding = 0;
                this.curse = 0;
                this.stanchik = 0;
                this.mobrange = 0;
                this.armor = 0;
                this.typeMob = 0;
                this.magicarmor = 0;
                this.resistance = 0;
                this.ManaMob = 0;
                this.range = 0;
                this.attackspeed = 0;
                this.knockback = 0;
                this.dodge = 0;
                this.aggressive = 0;
                this.summoned = 0;
                this.invisible = 0;
                this.speed = 0;
                this.script = null;
                this.hand = 0;
                this.helmet = null;
                this.chestplate = null;
                this.leggings = null;
                this.boots = null;
                this.offhand = 0;
                this.skin = null;
                this.patch = 0;
                azerot.getInstance().getMobsInPatchs().add(0, id);

            } else {
                this.id = result.getInt("id");
                this.name = result.getString("name");
                this.level = result.getInt("level");
                this.type = result.getString("type");
                this.typeMob = result.getInt("typeMob");
                this.respawn = result.getDouble("respawn");
                this.health = result.getInt("health");
                this.drop = result.getString("drop");
                this.xp = result.getInt("xp");
                this.damage = result.getInt("damage");
                this.magicdamage = result.getInt("magicdamage");
                this.critchance = result.getInt("critchance");
                this.vampiric = result.getInt("vampiric");
                this.mobrange = result.getInt("mobrange");
                this.knockback = result.getInt("knockback");
                this.armor = result.getInt("armor");
                this.magicarmor = result.getInt("magicarmor");
                this.frozen = result.getInt("frozen");
                this.bleeding = result.getInt("bleeding");
                this.curse = result.getInt("curse");
                this.BlackBlood = result.getInt("BlackBlood");
                this.ManaMob = result.getInt("ManaMob");
                this.stanchik = result.getInt("stanchik");
                this.resistance = result.getInt("resistance");
                this.range = result.getInt("range");
                this.attackspeed = result.getInt("attackspeed");
                this.dodge = result.getInt("dodge");
                this.slow = result.getInt("slow");
                this.aggressive = result.getInt("aggressive");
                this.summoned = result.getInt("summoned");
                this.invisible = result.getInt("invisible");
                this.speed = result.getInt("speed");
                this.script = result.getString("script");
                this.skin = result.getString("skin");
                this.hand = result.getInt("hand");
                this.helmet = result.getString("helmet");
                this.chestplate = result.getString("chestplate");
                this.leggings = result.getString("leggings");
                this.boots = result.getString("boots");
                this.offhand = result.getInt("offhand");
                this.patch = result.getInt("patch");
                azerot.getInstance().getMobsInPatchs().add(id);
                rpgmobs.put(this.id, this);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static Map<Integer, RPGMobsContainer> getRpgmobs() {
        return rpgmobs;
    }
    public static RPGMobsContainer getRPGMobsContainer(int id) {
        return rpgmobs.get(Integer.valueOf(id));
    }
}
