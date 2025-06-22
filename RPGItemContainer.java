package azerot.azerot;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RPGItemContainer {
    private static final Map<Integer, RPGItemContainer> rpgitems = new HashMap<>();

    private static double highenchant01 = 0.075D;

    private static double highenchant005 = 0.037D;

    private int id;

    private String material;

    private int submaterial;

    private String enchant;

    private int enchantpower;

    private String color;

    private String name;

    private int type;

    private int quality;

    private int damage;

    private int maxdamage;



    private int magicdamage;
    private int pureDamage;

    private int maxmagicdamage;

    private int critchance;

    private int critdamage;

    private int vampiric;

    private int Bleeding;

    private int frozen;

    private int curse;
    private int returnArrow;

    private int manaregen;

    private int manaburn;

    private int health;

    private int armor;
    private int magicarmor;

    private int resistance;


    private int dodge;

    private int speed;

    private int dodgepower;

    private int regeneration;

    private int trueregeneration;



    private String lore1;

    private String lore2;

    private String lore3;

    private String lore4;

    private String lore5;

    private int itemprice;

    private int level;


    private int typeWeapon;

    private int mininglevel;

    private int soulbound;

    private int itemlevel;

    private int spellid;

    private int hasMark;

    private int recipe;

    private int dopDamage;

    private String teleport;

    private int stan;
    private int returnDamage;
    private String texture;

    private Integer patch;

    private int agility;

    private int strength;

    private int intelligence;


    private int protectionByBleeding;
    private int IgnoringArmor;

    public RPGItemContainer(ResultSet result) {
        try {
            if (result == null) {
                this.id = 0;
                this.material = "AIR";
                this.submaterial = 0;
                this.enchant = "";
                this.enchantpower = 0;
                this.color = "";
                this.name = "§fРука";
                this.type = 1;
                this.quality = 1;
                this.damage = 1;
                this.maxdamage = 1;
                this.Bleeding = 0;
                this.stan = 0;
                this.frozen = 0;
                this.curse = 0;
                this.magicdamage = 0;
                this.maxmagicdamage = 0;
                this.manaburn = 0;
                this.manaregen = 0;
                this.returnArrow = 0;
                this.critchance = 0;
                this.critdamage = 0;
                this.vampiric = 0;
                this.speed = 0;
                this.health = 0;
                this.dopDamage = 0;
                this.armor = 0;
                this.protectionByBleeding = 0;
                this.magicarmor = 0;
                this.IgnoringArmor = 0;
                this.resistance = 0;
                this.dodge = 0;
                this.regeneration = 0;
                this.trueregeneration = 0;
                this.returnDamage = 0;
                this.typeWeapon = 0;
                this.agility = 0;
                this.strength = 0;
                this.intelligence = 0;
                this.lore1 = null;
                this.lore2 = null;
                this.lore3 = null;
                this.lore4 = null;
                this.lore5 = null;
                this.itemprice = 0;
                this.texture = null;
                this.level = 0;
                this.itemlevel = 0;
                this.soulbound = 0;
                this.spellid = 0;
                this.hasMark = 0;
                this.recipe = 0;
                this.teleport = null;
                this.patch = 0;
                rpgitems.put(Integer.valueOf(this.id), this);
                azerot.getInstance().getItems().add(0, Integer.valueOf(this.id));
            } else {
                this.id = result.getInt("id");
                this.material = result.getString("material");
                this.submaterial = result.getInt("submaterial");
                this.enchant = result.getString("enchant");
                this.enchantpower = result.getInt("enchantpower");
                this.color = result.getString("color");
                this.name = result.getString("name");
                this.type = result.getInt("type");
                this.quality = result.getInt("quality");
                this.damage = result.getInt("damage");
                this.maxdamage = result.getInt("maxdamage");
                this.magicdamage = result.getInt("magicdamage");
                this.maxmagicdamage = result.getInt("maxmagicdamage");
                this.critchance = result.getInt("critchance");
                this.critdamage = result.getInt("critdamage");
                this.vampiric = result.getInt("vampiric");
                this.IgnoringArmor = result.getInt("IgnoringArmor");
                this.manaburn = result.getInt("manaburn");
                this.Bleeding = result.getInt("Bleeding");
                this.stan = result.getInt("stan");
                this.frozen = result.getInt("frozen");
                this.curse = result.getInt("curse");
                this.health = result.getInt("health");
                this.speed = result.getInt("speed");
                this.armor = result.getInt("armor");
                this.magicarmor = result.getInt("magicarmor");
                this.resistance = result.getInt("resistance");
                this.dodge = result.getInt("dodge");
                this.dopDamage = result.getInt("dopDamage");
                this.returnDamage = result.getInt("returnDamage");
                this.protectionByBleeding = result.getInt("protectionByBleeding");
                this.regeneration = result.getInt("regeneration");
                this.trueregeneration = result.getInt("trueregeneration");
                this.typeWeapon = result.getInt("typeWeapon");
                this.returnArrow = result.getInt("returnArrow");
                this.agility = result.getInt("agility");
                this.strength = result.getInt("strength");
                this.intelligence = result.getInt("intelligence");
                this.lore1 = result.getString("lore1");
                this.lore2 = result.getString("lore2");
                this.lore3 = result.getString("lore3");
                this.lore4 = result.getString("lore4");
                this.lore5 = result.getString("lore5");
                this.itemprice = result.getInt("itemprice");
                this.level = result.getInt("level");
                this.itemlevel = result.getInt("itemlevel");
                this.soulbound = result.getInt("soulbound");
                this.spellid = result.getInt("spellid");
                this.hasMark = result.getInt("hasMark");
                this.recipe = result.getInt("recipe");
                this.teleport = result.getString("teleport");
                this.patch = result.getInt("patch");
                this.texture = result.getString("texture");
                rpgitems.put(Integer.valueOf(this.id), this);
                azerot.getInstance().getItems().add(Integer.valueOf(this.id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getMarkId(int id, int mark, int markpower, int enchant) {
        return id + "-" + mark + "-" + markpower + "-" + enchant;
    }
    public static RPGItemContainer getRPGItemContainer(int id) {
        return rpgitems.get(Integer.valueOf(id));
    }

    public static int getAllItems(){
        return rpgitems.size();
    }

    public String getMaterial() {
        return this.material;
    }

    public int getSubMaterial() {
        return this.submaterial;
    }

    public String getEnchant() {
        return this.enchant;
    }

    public int getEnchantPower() {
        return this.enchantpower;
    }

    public String getColor() {
        return this.color;
    }

    public String getName() {
        return this.name;
    }

    public int getType() {
        return this.type;
    }

    public int getQuality() {
        return this.quality;
    }
    public Integer getPatch() {
        return this.patch;
    }

    public int getStan(int mark, int markpower, int enchant) {
        int highenchant = Math.max(0, enchant - 8);
        int stat = 0;

        return stat;
    }
    public String getTexture() {
        return this.texture;
    }


    public int getFrozen(int mark, int markpower, int enchant) {
        int highenchant = Math.max(0, enchant - 8);
        int stat = this.frozen;

        return stat;
    }
    public int getAgility(int mark, int markpower, int enchant) {
        int highenchant = Math.max(0, enchant - 8);

        return this.agility;
    }

    public int getStrength(int mark, int markpower, int enchant) {
        int highenchant = Math.max(0, enchant - 8);

        return this.strength;
    }

    public int getIntelligence(int mark, int markpower, int enchant) {
        int highenchant = Math.max(0, enchant - 8);

        return this.intelligence;
    }


    public int getCurse(int mark, int markpower, int enchant) {
        int highenchant = Math.max(0, enchant - 8);
        int stat = this.curse;

        return stat;
    }

    public int getBleeding(int mark, int markpower, int enchant) {
        int highenchant = Math.max(0, enchant - 8);
        int stat = this.Bleeding;

        return stat;
    }
    public int getIgnoringArmor(int mark, int markPower, int enchant) {
        int highenchant = Math.max(0, enchant - 8);
        int stat = this.IgnoringArmor;

        return stat;
    }




    public int getItemlevel(){
        return this.itemlevel;
    }

    public int getDopDamage(int mark, int markPower, int enchant) {
        int highenchant = Math.max(0, enchant - 8);
        int stat = this.dopDamage;
        return stat;
    }

    public int getReturnDamage(int mark, int markPower, int enchant) {
        int highenchant = Math.max(0, enchant - 8);
        int stat = this.returnDamage;
        return stat;
    }

    public int getProtectionByBleeding(int mark, int markpower, int enchant) {
        int stat = this.protectionByBleeding;
        return stat;
    }
    public int getDamage(int mark, int markpower, int enchant) {
        int highenchant = Math.max(0, enchant - 8);
        int stat = this.damage;
        int typeStats = 1;
        if(typeWeapon == 1){ //это означает что у айтемы и маг и физ может быть(или хуй его знает короче)
            if(markpower % 2 > 0){
                stat = 0;
                typeStats = 0;
            }
        }
        if(mark > 0) {
            stat = RPGItemMarks.getRPGItemMarks(getMarkId(this.id, mark, markpower, enchant)).getDamage();
        }
        if(mark == 0){
            stat = randomStat(1, stat,markpower,enchant,0.95D, 1.1D);
        }
        stat *=  (1.0D + 0.1D * enchant);
        stat += (int)Math.round(stat * highenchant * highenchant01);
        if(stat > getMaxDamage(mark, markpower, enchant)){
            stat = Math.min(getMaxDamage(mark, markpower, enchant), stat);
        }
        return stat;
    }
    public int randomStat(int type, int value, int markpower, int enchant, double min, double max) {
        if (value == 0)
            return 0;
        Random r = new Random();
        r.setSeed((id + markpower + enchant + type));
        int mini = (int)(value * min), maxi = (int)(value * max);
        value = mini;
        if (maxi > mini)
            value += r.nextInt(maxi - mini + 1);
        return value;
    }

    public int getMaxDamage(int mark, int markpower, int enchant) {
        int highenchant = Math.max(0, enchant - 8);
        int stat = this.maxdamage;
        int typeStats = 1;
        if(typeWeapon == 1){
            if(markpower % 2 > 0){
                stat = 0;
                typeStats = 0;
            }
        }
        if(mark > 0){
            stat = RPGItemMarks.getRPGItemMarks(getMarkId(this.id, mark, markpower, enchant)).getMaxDamage();
        }
        if(mark == 0){
            stat = randomStat(2, stat,markpower,enchant,0.95D, 1.1D);
        }
        stat *= (1.0D + 0.1D * enchant);
        stat += (int)Math.round(stat * highenchant * highenchant01);
        return stat;
    }
    public int getMagicDamage(int mark, int markpower, int enchant) {
        int highenchant = Math.max(0, enchant - 8);
        int stat = this.magicdamage;
        if(typeWeapon == 1){
            if(markpower % 2 == 0){
                stat = 0;
            }
        }
        if(mark > 0){
            stat = RPGItemMarks.getRPGItemMarks(getMarkId(this.id, mark, markpower, enchant)).getMagicDamage();
        }
        if(mark == 0){
            stat = randomStat(3, stat,markpower,enchant,0.95D, 1.1D);
        }
        stat *= (1.0D + 0.1D * enchant);
        stat += (int)Math.round(stat * highenchant * highenchant01);
        if(stat > getMaxMagicDamage(mark, markpower, enchant)){
            stat = Math.min(getMaxMagicDamage(mark, markpower, enchant), stat);
        }
        return stat;
    }

    public int getMaxMagicDamage(int mark, int markpower, int enchant) {
        int highenchant = Math.max(0, enchant - 8);
        int stat = this.maxmagicdamage;
        if(typeWeapon == 1){
            if(markpower % 2 == 0){
                stat = 0;
            }
        }
        if(mark > 0){
            stat = RPGItemMarks.getRPGItemMarks(getMarkId(this.id, mark, markpower, enchant)).getMaxMagicDamage();
        }
        if(mark == 0){
            stat = randomStat(4, stat,markpower,enchant,0.95D, 1.1D);
        }
        stat *= (1.0D + 0.1D * enchant);
        stat += (int)Math.round(stat * highenchant * highenchant01);
        return stat;
        }
    public  int calculateprice(float percent) {
        int value = 1;
        double levelModifier = 1 + itemlevel * 0.01;//1.72
        value *= 1 + itemlevel * 0.25;
        value *= levelModifier;
        value *= percent;
        return value;

    }

    public int getCritChance(int mark, int markpower, int enchant) {
        int highenchant = Math.max(0, enchant - 8);
        int stat = this.critchance;
        if(mark > 0){
            stat = RPGItemMarks.getRPGItemMarks(getMarkId(this.id, mark, markpower, enchant)).getCritChance();
        }
        if(mark == 0){
            stat = randomStat(5, stat,markpower,enchant,0.95D, 1.1D);
        }

        stat = (int)Math.round(stat * (1.0D + 0.05D * enchant));
        stat += (int)Math.round(stat * highenchant * highenchant005);
        return stat;
    }
    public int getManaburn(int mark, int markpower, int enchant) {
        int highenchant = Math.max(0, enchant - 8);
        int stat = this.manaburn;
        if(mark > 0){
            stat = RPGItemMarks.getRPGItemMarks(getMarkId(this.id, mark, markpower, enchant)).getManaBurn();
        }
        if(mark == 0){
            stat = randomStat(16, stat,markpower,enchant,0.95D, 1.1D);
        }

        stat = (int)Math.round(stat * (1.0D + 0.05D * enchant));
        stat += (int)Math.round(stat * highenchant * highenchant005);
        return stat;
    }
    public int getManaregen(int mark, int markpower, int enchant) {
        int highenchant = Math.max(0, enchant - 8);
        int stat = this.manaregen;
        if(mark > 0){
            stat = RPGItemMarks.getRPGItemMarks(getMarkId(this.id, mark, markpower, enchant)).getManaRegen();
        }
        if(mark == 0){
            stat = randomStat(17, stat,markpower,enchant,0.95D, 1.1D);
        }

        stat = (int)Math.round(stat * (1.0D + 0.05D * enchant));
        stat += (int)Math.round(stat * highenchant * highenchant005);
        return stat;
    }

    public int getCritDamage(int mark, int markpower, int enchant) {
        int highenchant = Math.max(0, enchant - 8);
        int stat = this.critdamage;
        if(mark > 0){
            stat = RPGItemMarks.getRPGItemMarks(getMarkId(this.id, mark, markpower, enchant)).getCritDamage();
        }
        if(mark == 0){
            stat = randomStat(6, stat,markpower,enchant,0.95D, 1.1D);
        }
        stat = (int)Math.round(stat * (1.0D + 0.05D * enchant));
        stat += (int)Math.round(stat * highenchant * highenchant005);
        return stat;
    }

    public int getVampiric(int mark, int markpower, int enchant) {
        int highenchant = Math.max(0, enchant - 8);
        int stat = this.vampiric;
        if(mark > 0){
            stat = RPGItemMarks.getRPGItemMarks(getMarkId(this.id, mark, markpower, enchant)).getVampiric();
        }
        if(mark == 0){
            stat = randomStat(7, stat,markpower,enchant,0.95D, 1.1D);
        }

        stat = (int)Math.round(stat * (1.0D + 0.05D * enchant));
        stat += (int)Math.round(stat * highenchant * highenchant005);
        return stat;
    }


    public int getHealth(int mark, int markpower, int enchant) {
        int highenchant = Math.max(0, enchant - 8);
        int stat = this.health;
        if(mark > 0){
            stat = RPGItemMarks.getRPGItemMarks(getMarkId(this.id, mark, markpower, enchant)).getHealth();
        }
        if(mark == 0){
            stat = randomStat(8, stat,markpower,enchant,0.95D, 1.1D);
        }

        stat *= (1.0D + 0.1D * enchant);
        stat += (int)Math.round(stat * highenchant * highenchant01);
        return stat;
    }

    public int getArmor(int mark, int markpower, int enchant) {
        int highenchant = Math.max(0, enchant - 8);
        int stat = this.armor;
        if(typeWeapon == 2){
            if(markpower % 2 > 0){
                stat = 0;
            }
        }
        if(mark > 0){
            stat = RPGItemMarks.getRPGItemMarks(getMarkId(this.id, mark, markpower, enchant)).getArmor();
        }
        if(mark == 0){
            stat = randomStat(9, stat,markpower,enchant,0.95D, 1.1D);
        }
        stat *=(1.0D + 0.1D * enchant);
        stat += (int)Math.round(stat * highenchant * highenchant01);
        return stat;
    }




    public int getMagicArmor(int mark, int markpower, int enchant) {
        int highenchant = Math.max(0, enchant - 8);
        int stat = this.magicarmor;
        if(typeWeapon == 2){
            if(markpower % 2 == 0){
                stat = 0;
            }
        }
        if(mark > 0){
            stat = RPGItemMarks.getRPGItemMarks(getMarkId(this.id, mark, markpower, enchant)).getMagicArmor();
        }
        if(mark == 0){
            stat = randomStat(10, stat,markpower,enchant,0.95D, 1.1D);
        }

        stat = (int) (stat * (1.0D + 0.1D * enchant));
        stat += (int)Math.round(stat * highenchant * highenchant01);
        return stat;
    }

    public int getResistance(int mark, int markpower, int enchant) {
        int highenchant = Math.max(0, enchant - 8);
        int stat = this.resistance;
        if(mark > 0){
            stat = RPGItemMarks.getRPGItemMarks(getMarkId(this.id, mark, markpower, enchant)).getResistance();
        }
        if(mark == 0){
            stat = randomStat(11, stat,markpower,enchant,0.95D, 1.1D);
        }


        stat *= (1.0D + 0.1D * enchant);
        stat += (int)Math.round(stat * highenchant * highenchant005);
        return stat;
    }

    public int getDodge(int mark, int markpower, int enchant) {
        int highenchant = Math.max(0, enchant - 8);
        int stat = this.dodge;
        if(mark > 0){
            stat = RPGItemMarks.getRPGItemMarks(getMarkId(this.id, mark, markpower, enchant)).getDodge();
        }
        if(mark == 0){
            stat = randomStat(12, stat,markpower,enchant,0.95D, 1.1D);
        }

        stat = (int)Math.round(stat * (1.0D + 0.05D * enchant));
        stat += (int)Math.round(stat * highenchant * highenchant005);
        return stat;
    }
    public int getSpeed(int mark, int markpower, int enchant) {
        int highenchant = Math.max(0, enchant - 8);
        int stat = this.speed;
        if(mark > 0){
            stat = RPGItemMarks.getRPGItemMarks(getMarkId(this.id, mark, markpower, enchant)).getSpeed();
        }
        if(mark == 0){
            stat = randomStat(13, stat,markpower,enchant,0.95D, 1.1D);
        }
        stat = (int) Math.round(stat * (1.0D + 0.05D * enchant));
        stat += (int) Math.round(stat * highenchant * highenchant005);
        return stat;
    }

    public int getRegeneration(int mark, int markpower, int enchant) {
        int highenchant = Math.max(0, enchant - 8);
        int stat = this.regeneration;
        if(mark > 0){
            stat = RPGItemMarks.getRPGItemMarks(getMarkId(this.id, mark, markpower, enchant)).getRegeneration();
        }
        if(mark == 0){
            stat = randomStat(14, stat,markpower,enchant,0.95D, 1.1D);
        }

        stat *= (1.0D + 0.1D * enchant);
        stat += (int)Math.round(stat * highenchant * highenchant01);
        return stat;
    }

    public int getTrueRegeneration(int mark, int markpower, int enchant) {
        int highenchant = Math.max(0, enchant - 8);
        int stat = this.trueregeneration;
        if(mark > 0){
            stat = RPGItemMarks.getRPGItemMarks(getMarkId(this.id, mark, markpower, enchant)).getTrueRegeneration();
        }
        if(mark == 0){
            stat = randomStat(15, stat,markpower,enchant,0.95D, 1.1D);
        }
        stat += (int)Math.round(stat * highenchant * highenchant01);
        return stat;
    }
    public String getLore(int number) {
        String stat = "";
        if (number == 1)
            stat = this.lore1;
        if (number == 2)
            stat = this.lore2;
        if (number == 3)
            stat = this.lore3;
        if (number == 4)
            stat = this.lore4;
        if (number == 5)
            stat = this.lore5;
        return stat;
    }

    public int getItemPrice() {
        if(type == 2)
             this.itemprice = calculateprice(2.25F);
        if(type == 3)
            this.itemprice = calculateprice(2.25F);
        return this.itemprice;
    }

    public int getLevel() {
        return this.level;
    }


    public int getTypeWeapon(){
        return this.typeWeapon;
    }


    public int getReturnArrow(){
        return this.returnArrow;
    }

    public int getMiningLevel() {
        return this.mininglevel;
    }

    public int getSoulBound() {
        return this.soulbound;
    }

    public int getSpellID() {
        return this.spellid;
    }

    public int getHasMark() {
        return this.hasMark;
    }

    public int getRecipe() {
        return this.recipe;
    }

    public String[] getTeleport() {
        if (this.teleport.equals("null"))
            return null;
        return this.teleport.trim().split(" ");
    }
}
