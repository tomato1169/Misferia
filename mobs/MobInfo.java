package azerot.azerot.mobs;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

@Getter
@Setter

public class MobInfo {
    public static HashMap<UUID, MobInfo> mobs = new HashMap<>();
    private int id;

    private int number;

    private int level;

    private String name;

    private int respawn;

    private int xp;

    private int damage;

    private int magicDamage;

    private int pureDamage;

    private int manaDamage;

    private int critChance;

    private int vampiric;

    private double knockback;

    private double range;

    private int attackSpeed;

    private int health;

    private int maxHealth;

    private int mana;

    private int maxMana;

    private int armor;

    private int magicArmor;

    private double percentArmor;

    private int resistance;

    private int dodge;

    private int dodgePower;

    private double mobRange;

    private int blackB;

    private int yellowB;

    private int manaMob;

    private int redB;

    private int aggressive;

    private String script;


    private int leveling;

    private int champion;

    private String visibleTo;

    private int targetCount;

    private long lastAttackTime;

    private Location spawnLocation;

    MobInfo(int id, int number, int level, String name, int respawn, int xp, int damage, int magicDamage, int manaDamage, int critChance, int vampiric, double knockback, double range, int attackSpeed, int health, int maxHealth, int mana, int maxMana, int armor, int magicArmor, double percentArmor, int resistance, int dodge, double mobRange, int blackB, int manaMob, int redB, int aggressive, String script, int leveling, int champion, String visibleTo, int targetCount, long lastAttackTime, Location spawnLocation) {
        this.id = id;
        this.number = number;
        this.level = level;
        this.name = name;
        this.respawn = respawn;
        this.xp = xp;
        this.damage = damage;
        this.magicDamage = magicDamage;
        this.pureDamage = pureDamage;
        this.manaDamage = manaDamage;
        this.critChance = critChance;
        this.vampiric = vampiric;
        this.knockback = knockback;
        this.range = range;
        this.attackSpeed = attackSpeed;
        this.health = health;
        this.maxHealth = maxHealth;
        this.mana = mana;
        this.maxMana = maxMana;
        this.armor = armor;
        this.magicArmor = magicArmor;
        this.percentArmor = percentArmor;
        this.resistance = resistance;
        this.dodge = dodge;
        this.dodgePower = dodgePower;
        this.mobRange = mobRange;
        this.blackB = blackB;

        this.yellowB = yellowB;
        this.manaMob = manaMob;
        this.redB = redB;
        this.aggressive = aggressive;
        this.script = script;
        this.leveling = leveling;
        this.champion = champion;
        this.visibleTo = visibleTo;
        this.targetCount = targetCount;
        this.lastAttackTime = lastAttackTime;
        this.spawnLocation = spawnLocation;
    }

    public static MobInfoBuilder builder() {
        return new MobInfoBuilder();
    }

    public static class MobInfoBuilder {
        private int id;

        private int number;

        private int level;

        private String name;

        private int respawn;

        private int xp;

        private int damage;

        private int magicDamage;

        private int pureDamage;

        private int manaDamage;

        private int critChance;

        private int vampiric;

        private double knockback;

        private double range;

        private int attackSpeed;

        private int health;

        private int maxHealth;

        private int mana;

        private int maxMana;

        private int armor;

        private int magicArmor;

        private double percentArmor;

        private int resistance;

        private int dodge;

        private int dodgePower;

        private double mobRange;

        private int blackB;

        private int yellowB;

        private int manaMob;

        private int redB;

        private int aggressive;

        private String script;


        private int leveling;

        private int champion;

        private String visibleTo;

        private int targetCount;

        private long lastAttackTime;

        private Location spawnLocation;

        public MobInfoBuilder id(int id) {
            this.id = id;
            return this;
        }

        public MobInfoBuilder number(int number) {
            this.number = number;
            return this;
        }

        public MobInfoBuilder level(int level) {
            this.level = level;
            return this;
        }

        public MobInfoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public MobInfoBuilder respawn(int respawn) {
            this.respawn = respawn;
            return this;
        }

        public MobInfoBuilder xp(int xp) {
            this.xp = xp;
            return this;
        }

        public MobInfoBuilder damage(int damage) {
            this.damage = damage;
            return this;
        }

        public MobInfoBuilder magicDamage(int magicDamage) {
            this.magicDamage = magicDamage;
            return this;
        }

        public MobInfoBuilder pureDamage(int pureDamage) {
            this.pureDamage = pureDamage;
            return this;
        }

        public MobInfoBuilder manaDamage(int manaDamage) {
            this.manaDamage = manaDamage;
            return this;
        }

        public MobInfoBuilder critChance(int critChance) {
            this.critChance = critChance;
            return this;
        }

        public MobInfoBuilder vampiric(int vampiric) {
            this.vampiric = vampiric;
            return this;
        }

        public MobInfoBuilder knockback(double knockback) {
            this.knockback = knockback;
            return this;
        }

        public MobInfoBuilder range(double range) {
            this.range = range;
            return this;
        }

        public MobInfoBuilder attackSpeed(int attackSpeed) {
            this.attackSpeed = attackSpeed;
            return this;
        }

        public MobInfoBuilder health(int health) {
            this.health = health;
            return this;
        }

        public MobInfoBuilder maxHealth(int maxHealth) {
            this.maxHealth = maxHealth;
            return this;
        }

        public MobInfoBuilder mana(int mana) {
            this.mana = mana;
            return this;
        }

        public MobInfoBuilder maxMana(int maxMana) {
            this.maxMana = maxMana;
            return this;
        }

        public MobInfoBuilder armor(int armor) {
            this.armor = armor;
            return this;
        }

        public MobInfoBuilder magicArmor(int magicArmor) {
            this.magicArmor = magicArmor;
            return this;
        }

        public MobInfoBuilder percentArmor(double percentArmor) {
            this.percentArmor = percentArmor;
            return this;
        }

        public MobInfoBuilder resistance(int resistance) {
            this.resistance = resistance;
            return this;
        }

        public MobInfoBuilder dodge(int dodge) {
            this.dodge = dodge;
            return this;
        }

        public MobInfoBuilder dodgePower(int dodgePower) {
            this.dodgePower = dodgePower;
            return this;
        }

        public MobInfoBuilder mobRange(double mobRange) {
            this.mobRange = mobRange;
            return this;
        }

        public MobInfoBuilder blackB(int blackB) {
            this.blackB = blackB;
            return this;
        }

        public MobInfoBuilder yellowB(int yellowB) {
            this.yellowB = yellowB;
            return this;
        }

        public MobInfoBuilder manaMob(int manaMob) {
            this.manaMob = manaMob;
            return this;
        }

        public MobInfoBuilder redB(int redB) {
            this.redB = redB;
            return this;
        }

        public MobInfoBuilder aggressive(int aggressive) {
            this.aggressive = aggressive;
            return this;
        }

        public MobInfoBuilder script(String script) {
            this.script = script;
            return this;
        }


        public MobInfoBuilder leveling(int leveling) {
            this.leveling = leveling;
            return this;
        }

        public MobInfoBuilder champion(int champion) {
            this.champion = champion;
            return this;
        }

        public MobInfoBuilder visibleTo(String visibleTo) {
            this.visibleTo = visibleTo;
            return this;
        }

        public MobInfoBuilder targetCount(int targetCount) {
            this.targetCount = targetCount;
            return this;
        }

        public MobInfoBuilder lastAttackTime(long lastAttackTime) {
            this.lastAttackTime = lastAttackTime;
            return this;
        }

        public MobInfoBuilder spawnLocation(Location spawnLocation) {
            this.spawnLocation = spawnLocation;
            return this;
        }

        public MobInfo build() {
            return new MobInfo(this.id, this.number, this.level, this.name, this.respawn, this.xp, this.damage, this.magicDamage, this.manaDamage, this.critChance, this.vampiric, this.knockback, this.range, this.attackSpeed, this.health, this.maxHealth, this.mana, this.maxMana, this.armor, this.magicArmor, this.percentArmor, this.resistance, this.dodge,this.mobRange, this.blackB,  this.manaMob, this.redB, this.aggressive, this.script, this.leveling, this.champion, this.visibleTo, this.targetCount, this.lastAttackTime, this.spawnLocation);
        }

        public String toString() {
            return "MobInfo.MobInfoBuilder(id=" + this.id + ", number=" + this.number + ", level=" + this.level + ", name=" + this.name + ", respawn=" + this.respawn + ", xp=" + this.xp + ", damage=" + this.damage + ", magicDamage=" + this.magicDamage + ", manaDamage=" + this.manaDamage + ", critChance=" + this.critChance + ", vampiric=" + this.vampiric + ", knockback=" + this.knockback + ", range=" + this.range + ", attackSpeed=" + this.attackSpeed + ", health=" + this.health + ", maxHealth=" + this.maxHealth + ", mana=" + this.mana + ", maxMana=" + this.maxMana + ", armor=" + this.armor + ", magicArmor=" + this.magicArmor + ", percentArmor=" + this.percentArmor + ", resistance=" + this.resistance + ", dodge=" + this.dodge + ", mobRange=" + this.mobRange + ", blackB=" + this.blackB  + ", manaMob=" + this.manaMob + ", redB=" + this.redB + ", aggressive=" + this.aggressive + ", script=" + this.script + ", leveling=" + this.leveling + ", champion=" + this.champion + ", visibleTo=" + this.visibleTo + ", targetCount=" + this.targetCount + ", lastAttackTime=" + this.lastAttackTime + ", spawnLocation=" + this.spawnLocation + ")";
        }
    }

    public int getId() {
        return this.id;
    }

    public int getNumber() {
        return this.number;
    }

    public int getLevel() {
        return this.level;
    }

    public String getName() {
        return this.name;
    }

    public int getRespawn() {
        return this.respawn;
    }

    public int getXp() {
        return this.xp;
    }

    public int getDamage() {
        return this.damage;
    }

    public int getMagicDamage() {
        return this.magicDamage;
    }

    public int getPureDamage() {
        return this.pureDamage;
    }

    public int getManaDamage() {
        return this.manaDamage;
    }

    public int getCritChance() {
        return this.critChance;
    }

    public int getVampiric() {
        return this.vampiric;
    }

    public double getKnockback() {
        return this.knockback;
    }

    public double getRange() {
        return this.range;
    }

    public int getAttackSpeed() {
        return this.attackSpeed;
    }

    public int getHealth() {
        return this.health;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public int getMana() {
        return this.mana;
    }

    public int getMaxMana() {
        return this.maxMana;
    }

    public int getArmor() {
        return this.armor;
    }

    public int getMagicArmor() {
        return this.magicArmor;
    }

    public double getPercentArmor() {
        return this.percentArmor;
    }

    public int getResistance() {
        return this.resistance;
    }

    public int getDodge() {
        return this.dodge;
    }
    public Player getVisibleTo() {
        if (this.visibleTo == null)
            return null;
        return Bukkit.getPlayer(this.visibleTo);
    }

    public int getDodgePower() {
        return this.dodgePower;
    }

    public double getMobRange() {
        return this.mobRange;
    }

    public int getBlackB() {
        return this.blackB;
    }

    public int getYellowB() {
        return this.yellowB;
    }

    public int getManaMob() {
        return this.manaMob;
    }

    public int getRedB() {
        return this.redB;
    }

    public int getAggressive() {
        return this.aggressive;
    }

    public String getScript() {
        return this.script;
    }


    public int getLeveling() {
        return this.leveling;
    }

    public int getChampion() {
        return this.champion;
    }

    public int getTargetCount() {
        return this.targetCount;
    }

    public long getLastAttackTime() {
        return this.lastAttackTime;
    }

    public Location getSpawnLocation() {
        return this.spawnLocation;
    }


    public RPGMobsContainer getMonster() {
        return RPGMobsContainer.getRPGMobsContainer(this.id);
    }
}