package azerot.azerot;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RPGItemMarks {
    private static final Map<String, RPGItemMarks> rpgitemmarks = new HashMap<>();

    private static final Map<String, Integer> tosave = new HashMap<>();

    private int id;

    private int mark;

    private int markpower;

    private double typeStats;

    private int givemark = 0;

    private int givemarkpower = 0;

    private int enchant;

    private int damage;

    private int maxdamage;

    private int magicdamage;

    private int maxmagicdamage;

    private int critchance;

    private int critdamage;

    private int vampiric;

    private int manaregen;

    private int manaburn;

    private int health;

    private int armor;

    private int ingnoringArmor;
    private int DopDamage;

    private int ReturnDamage;

    private int magicarmor;

    private int resistance;

    private int dodge;

    private int dodgepower;

    private int regeneration;

    private int trueregeneration;

    private int speed;

    public RPGItemMarks(String data) {
        String[] datas = data.split("-");
        this.id = Integer.parseInt(datas[0]);
        this.mark = Integer.parseInt(datas[1]);
        this.markpower = Integer.parseInt(datas[2]);
        this.enchant = Integer.parseInt(datas[3]);
        Random r = new Random();
        r.setSeed(this.markpower);
        RPGItemContainer RPGI = RPGItemContainer.getRPGItemContainer(id);
        switch (this.mark) {
            case 1:
                this.damage = randomStat(1, RPGI.getDamage(this.givemark, this.givemarkpower, 0), 0.95D, 1.20D);
                this.maxdamage = randomStat(2, RPGI.getMaxDamage(this.givemark, this.givemarkpower, 0), 0.95D, 1.20D);
                this.magicdamage = randomStat(3, RPGI.getMagicDamage(this.givemark, this.givemarkpower, 0), 0.8D, 0.95D);
                this.maxmagicdamage = randomStat(4, RPGI.getMaxMagicDamage(this.givemark, this.givemarkpower, 0), 0.8D, 0.95D);
                this.critchance = randomStat(5, RPGI.getCritChance(this.givemark, this.givemarkpower, 0), 1.1D, 1.2D);
                this.critdamage = randomStat(6, RPGI.getCritDamage(this.givemark, this.givemarkpower, 0), 1.35D, 1.55D);
                this.vampiric = randomStat(7, RPGI.getVampiric(this.givemark, this.givemarkpower, 0), 0.5D, 0.75D);
                this.manaburn = randomStat(8, RPGI.getVampiric(this.givemark, this.givemarkpower, 0), 0.85D, 1D);
                this.health = randomStat(9, RPGI.getHealth(this.givemark, this.givemarkpower, 0), 2.0D, 2.1D);
                this.armor = randomStat(10, RPGI.getArmor(this.givemark, this.givemarkpower, 0), 0.75D, 1.0D);
                this.magicarmor = randomStat(11, RPGI.getMagicArmor(this.givemark, this.givemarkpower, 0), 0.75D, 1.0D);
                this.resistance = randomStat(12, RPGI.getResistance(this.givemark, this.givemarkpower, 0), 0.5D, 0.75D);
                this.dodge = randomStat(13, RPGI.getDodge(this.givemark, this.givemark, this.givemarkpower), 0.6D, 0.7D);
                this.regeneration = randomStat(14, RPGI.getRegeneration(this.givemark, this.givemarkpower, 0), 0.9D, 1.1D);
                this.trueregeneration = randomStat(15, RPGI.getTrueRegeneration(this.givemark, this.givemarkpower, 0), 0.9D, 1.1D);
                this.speed = randomStat(16, RPGI.getSpeed(this.givemark, this.givemark, 0), 1.0D, 1.0D);
                this.ReturnDamage = randomStat(17, RPGI.getReturnDamage(this.givemark, this.givemarkpower, 0), 1.0D, 1.10D);
                this.DopDamage = randomStat(18, RPGI.getDopDamage(this.givemark, this.givemarkpower, 0), 1.0D, 1.10D);
                this.manaregen = randomStat(19, RPGI.getManaregen(this.givemark, this.givemarkpower, 0), 1.00D, 1.15D);
                this.ingnoringArmor = randomStat(20, RPGI.getManaregen(this.givemark, this.givemarkpower, 0), 1.50D, 2.25D);
                break;
            case 2:
                this.damage = randomStat(1, RPGI.getDamage(this.givemark, this.givemarkpower, 0), 1.05D, 1.2D);
                this.maxdamage = randomStat(2, RPGI.getMaxDamage(this.givemark, this.givemarkpower, 0), 1D, 1.2D);
                this.magicdamage = randomStat(3, RPGI.getMagicDamage(this.givemark, this.givemarkpower, 0), 1.05D, 1.2D);
                this.maxmagicdamage = randomStat(4, RPGI.getMaxMagicDamage(this.givemark, this.givemarkpower, 0), 1.0D, 1.2D);
                this.critchance = randomStat(5, RPGI.getCritChance(this.givemark, this.givemarkpower, 0), 1.35D, 1.75D);
                this.critdamage = randomStat(6, RPGI.getCritDamage(this.givemark, this.givemarkpower, 0), 1D, 1.1D);
                this.vampiric = randomStat(7, RPGI.getVampiric(this.givemark, this.givemarkpower, 0), 0.85D, 1D);
                this.manaburn = randomStat(8, RPGI.getVampiric(this.givemark, this.givemarkpower, 0), 1.25D, 1.55D);
                this.health = randomStat(9, RPGI.getHealth(this.givemark, this.givemarkpower, 0), 1.05D, 1.2D);
                this.armor = randomStat(10, RPGI.getArmor(this.givemark, this.givemarkpower, 0), 0.9D, 1.0D);
                this.magicarmor = randomStat(11, RPGI.getMagicArmor(this.givemark, this.givemarkpower, 0), 1.15D, 1.35D);
                this.resistance = randomStat(12, RPGI.getResistance(this.givemark, this.givemarkpower, 0), 0.9D, 1.0D);
                this.dodge = randomStat(13, RPGI.getDodge(this.givemark, this.givemarkpower, 0), 1.15D, 1.35D);
                this.regeneration = randomStat(14, RPGI.getRegeneration(this.givemark, this.givemarkpower, 0), 1.15D, 1.25D);
                this.trueregeneration = randomStat(15, RPGI.getTrueRegeneration(this.givemark, this.givemarkpower, 0), 1.15D, 1.25D);
                this.speed = randomStat(16, RPGI.getSpeed(this.givemark, this.givemark, 0), 1.0D, 1.0D);
                this.ReturnDamage = randomStat(17, RPGI.getReturnDamage(this.givemark, this.givemarkpower, 0), 1.0D, 1.45D);
                this.DopDamage = randomStat(18, RPGI.getDopDamage(this.givemark, this.givemarkpower, 0), 1.15D, 1.45D);
                this.manaregen = randomStat(19, RPGI.getManaregen(this.givemark, this.givemarkpower, 0), 1.50D, 2.25D);
                this.ingnoringArmor = randomStat(20, RPGI.getManaregen(this.givemark, this.givemarkpower, 0), 1.20D, 1.35D);
                break;
            case 3:
                this.damage = randomStat(1, RPGI.getDamage(this.givemark, this.givemarkpower, 0), 1.20D, 1.30D);
                this.maxdamage = randomStat(2, RPGI.getMaxDamage(this.givemark, this.givemarkpower, 0), 1.20D, 1.30D);
                this.magicdamage = randomStat(3, RPGI.getMagicDamage(this.givemark, this.givemarkpower, 0), 0.6D, 0.85D);
                this.maxmagicdamage = randomStat(4, RPGI.getMaxMagicDamage(this.givemark, this.givemarkpower, 0), 0.6D, 0.85D);
                this.critchance = randomStat(5, RPGI.getCritChance(this.givemark, this.givemarkpower, 0), 0.89D, 1.0D);
                this.critdamage = randomStat(6, RPGI.getCritDamage(this.givemark, this.givemarkpower, 0), 1.1D, 1.30D);
                this.vampiric = randomStat(7, RPGI.getVampiric(this.givemark, this.givemarkpower, 0), 0.85D, 1.0D);
                this.manaburn = randomStat(8, RPGI.getVampiric(this.givemark, this.givemarkpower, 0), 0.85D, 0.95D);
                this.health = randomStat(9, RPGI.getHealth(this.givemark, this.givemarkpower, 0), 1.0D, 1.2D);
                this.armor = randomStat(10, RPGI.getArmor(this.givemark, this.givemarkpower, 0), 1.15D, 1.35D);
                this.magicarmor = randomStat(11, RPGI.getMagicArmor(this.givemark, this.givemarkpower, 0), 1.2D, 1.35D);
                this.resistance = randomStat(12, RPGI.getResistance(this.givemark, this.givemarkpower, 0), 1.15D, 1.5D);
                this.dodge = randomStat(13, RPGI.getDodge(this.givemark, this.givemarkpower, 0), 0.4D, 0.5D);
                this.regeneration = randomStat(14, RPGI.getRegeneration(this.givemark, this.givemarkpower, 0), 0.6D, 0.85D);
                this.trueregeneration = randomStat(15, RPGI.getTrueRegeneration(this.givemark, this.givemarkpower, 0), 0.7D, 0.89D);
                this.speed = randomStat(16, RPGI.getSpeed(this.givemark, this.givemarkpower, 0), 1.0D, 1.0D);
                this.ReturnDamage = randomStat(17, RPGI.getReturnDamage(this.givemark, this.givemarkpower, 0), 1.0D, 1.45D);
                this.DopDamage = randomStat(18, RPGI.getDopDamage(this.givemark, this.givemarkpower, 0), 1.0D, 1.15D);
                this.manaregen = randomStat(19, RPGI.getManaregen(this.givemark, this.givemarkpower, 0), 1.0D, 1.15D);
                this.ingnoringArmor = randomStat(20, RPGI.getManaregen(this.givemark, this.givemarkpower, 0), 1.10D, 1.15D);
                break;
            case 4:
                this.damage = randomStat(1, RPGI.getDamage(this.givemark, this.givemarkpower, 0), 1.12D, 1.17D);
                this.maxdamage = randomStat(2, RPGI.getMaxDamage(this.givemark, this.givemarkpower, 0), 1.12D, 1.17D);
                this.magicdamage = randomStat(3, RPGI.getMagicDamage(this.givemark, this.givemarkpower, 0), 1D, 1.2D);
                this.maxmagicdamage = randomStat(4, RPGI.getMaxMagicDamage(this.givemark, this.givemarkpower, 0), 1D, 1.2D);
                this.critchance = randomStat(5, RPGI.getCritChance(this.givemark, this.givemarkpower, 0), 1.1D, 1.2D);
                this.critdamage = randomStat(6, RPGI.getCritDamage(this.givemark, this.givemarkpower, 0), 1.05D, 1.5D);
                this.ingnoringArmor = randomStat(20, RPGI.getCritDamage(this.givemark, this.givemarkpower, 0), 1.15D, 1.5D);
                this.vampiric = randomStat(7, RPGI.getVampiric(this.givemark, this.givemarkpower, 0), 0.5D, 0.75D);
                this.manaburn = randomStat(8, RPGI.getVampiric(this.givemark, this.givemarkpower, 0), 1.05D, 1.25D);
                this.health = randomStat(9, RPGI.getHealth(this.givemark, this.givemarkpower, 0), 1.0D, 1.45D);
                this.armor = randomStat(10, RPGI.getArmor(this.givemark, this.givemarkpower, 0), 0.95D, 1.25D);
                this.magicarmor = randomStat(11, RPGI.getMagicArmor(this.givemark, this.givemarkpower, 0), 0.95D, 1.25D);
                this.resistance = randomStat(12, RPGI.getResistance(this.givemark, this.givemarkpower, 0), 0.75D, 1.15D);
                this.dodge = randomStat(13, RPGI.getDodge(this.givemark, this.givemarkpower, 0), 1.1D, 1.55D);
                this.regeneration = randomStat(14, RPGI.getRegeneration(this.givemark, this.givemarkpower, 0), 1.0D, 1.55D);
                this.trueregeneration = randomStat(15, RPGI.getTrueRegeneration(this.givemark, this.givemarkpower, 0), 1.0D, 1.25D);
                this.speed = randomStat(16, RPGI.getSpeed(this.givemark, this.givemarkpower, 0), 1.0D, 1.0D);
                this.ReturnDamage = randomStat(17, RPGI.getReturnDamage(this.givemark, this.givemarkpower, 0), 0.90D, 1.20D);
                this.DopDamage = randomStat(18, RPGI.getDopDamage(this.givemark, this.givemarkpower, 0), 0.95D, 1.15D);
                this.manaregen = randomStat(19, RPGI.getManaregen(this.givemark, this.givemarkpower, 0), 1.50D, 1.75D);
                break;
            case 5:
                this.damage = randomStat(1, RPGI.getDamage(this.givemark, this.givemarkpower, 0), 0.6D, 0.85D);
                this.maxdamage = randomStat(2, RPGI.getMaxDamage(this.givemark, this.givemarkpower, 0), 0.6D, 0.85D);
                this.magicdamage = randomStat(3, RPGI.getMagicDamage(this.givemark, this.givemarkpower, 0), 1.20D, 1.30D);
                this.maxmagicdamage = randomStat(4, RPGI.getMaxMagicDamage(this.givemark, this.givemarkpower, 0), 1.20D, 1.30);
                this.critchance = randomStat(5, RPGI.getCritChance(this.givemark, this.givemarkpower, 0), 1D, 1.2D);
                this.critdamage = randomStat(6, RPGI.getCritDamage(this.givemark, this.givemarkpower, 0), 1D, 1.2D);
                this.vampiric = randomStat(7, RPGI.getVampiric(this.givemark, this.givemarkpower, 0), 0.9D, 1.1D);
                this.manaburn = randomStat(8, RPGI.getVampiric(this.givemark, this.givemarkpower, 0), 1.25D, 1.55D);
                this.health = randomStat(9, RPGI.getHealth(this.givemark, this.givemarkpower, 0), 1.0D, 1.3D);
                this.armor = randomStat(10, RPGI.getArmor(this.givemark, this.givemarkpower, 0), 1.05D, 1.3D);
                this.magicarmor = randomStat(11, RPGI.getMagicArmor(this.givemark, this.givemarkpower, 0), 1.05D, 1.3D);
                this.resistance = randomStat(12, RPGI.getResistance(0, this.givemarkpower, 0), 1.0D, 1.3D);
                this.dodge = randomStat(13, RPGI.getDodge(this.givemark, this.givemarkpower, 0), 1.0D, 1.55D);
                this.regeneration = randomStat(14, RPGI.getRegeneration(this.givemark, this.givemarkpower, 0), 0.5D, 1.25D);
                this.trueregeneration = randomStat(15, RPGI.getTrueRegeneration(this.givemark, this.givemarkpower, 0), 0.5D, 0.75D);
                this.speed = randomStat(16, RPGI.getSpeed(this.givemark, this.givemarkpower, 0), 1.0D, 1.0D);
                this.ReturnDamage = randomStat(17, RPGI.getReturnDamage(this.givemark, this.givemarkpower, 0), 1.0D, 1.25D);
                this.DopDamage = randomStat(18, RPGI.getDopDamage(this.givemark, this.givemarkpower, 0), 1.15D, 1.25D);
                this.manaregen = randomStat(19, RPGI.getManaregen(this.givemark, this.givemarkpower, 0), 1.50D, 1.75D);
                this.ingnoringArmor = randomStat(20, RPGI.getManaregen(this.givemark, this.givemarkpower, 0), 1.0D, 1.15D);
                break;
            case 6:
                this.damage = randomStat(1, RPGI.getDamage(this.givemark, this.givemarkpower, 0), 1.22D, 1.35D);
                this.maxdamage = randomStat(2, RPGI.getMaxDamage(this.givemark, this.givemarkpower, 0), 1.22D, 1.35D);
                this.magicdamage = randomStat(3, RPGI.getMagicDamage(this.givemark, this.givemarkpower, 0), 0.65D, 0.75D);
                this.maxmagicdamage = randomStat(4, RPGI.getMaxMagicDamage(this.givemark, this.givemarkpower, 0), 0.65D, 0.75D);
                this.critchance = randomStat(5, RPGI.getCritChance(this.givemark, this.givemarkpower, 0), 0.9D, 1.3D);
                this.critdamage = randomStat(6, RPGI.getCritDamage(this.givemark, this.givemarkpower, 0), 0.9D, 1.3D);
                this.vampiric = randomStat(7, RPGI.getVampiric(this.givemark, this.givemarkpower, 0), 0.5D, 0.9D);
                this.manaburn = randomStat(8, RPGI.getVampiric(this.givemark, this.givemarkpower, 0), 0.85D, 1D);
                this.health = randomStat(9, RPGI.getHealth(this.givemark, this.givemarkpower, 0), 1.05D, 1.35D);
                this.armor = randomStat(10, RPGI.getArmor(this.givemark, this.givemarkpower, 0), 1.05D, 1.35D);
                this.magicarmor = randomStat(11, RPGI.getMagicArmor(this.givemark, this.givemarkpower, 0), 1.05D, 1.35D);
                this.resistance = randomStat(12, RPGI.getResistance(this.givemark, this.givemarkpower, 0), 1.05D, 1.35D);
                this.dodge = randomStat(13, RPGI.getDodge(this.givemark, this.givemarkpower, 0), 1.05D, 1.35D);
                this.regeneration = randomStat(14, RPGI.getRegeneration(this.givemark, this.givemarkpower, 0), 1.05D, 1.35D);
                this.trueregeneration = randomStat(15, RPGI.getTrueRegeneration(this.givemark, this.givemarkpower, 0), 1.05D, 1.35D);
                this.speed = randomStat(16, RPGI.getSpeed(this.givemark, this.givemarkpower, 0), 1.0D, 1.0D);
                this.ReturnDamage = randomStat(17, RPGI.getReturnDamage(this.givemark, this.givemarkpower, 0), 1.05D, 1.35D);
                this.DopDamage = randomStat(18, RPGI.getDopDamage(this.givemark, this.givemarkpower, 0), 1.05D, 1.35D);
                this.manaregen = randomStat(19, RPGI.getManaregen(this.givemark, this.givemarkpower, 0), 1.05D, 1.35D);
                this.ingnoringArmor = randomStat(20, RPGI.getManaregen(this.givemark, this.givemarkpower, 0), 1.05D, 1.35D);
                break;
            case 7:
                this.damage = randomStat(1, RPGI.getDamage(this.givemark, this.givemarkpower, 0), 1D, 1.10D);
                this.maxdamage = randomStat(2, RPGI.getMaxDamage(this.givemark, this.givemarkpower, 0), 1D, 1.15D);
                this.magicdamage = randomStat(3, RPGI.getMagicDamage(this.givemark, this.givemarkpower, 0), 1.05D, 1.1D);
                this.maxmagicdamage = randomStat(4, RPGI.getMaxMagicDamage(this.givemark, this.givemarkpower, 0), 1.0D, 1.05D);
                this.critchance = randomStat(5, RPGI.getCritChance(this.givemark, this.givemarkpower, 0), 0.75D, 0.9D);
                this.critdamage = randomStat(6, RPGI.getCritDamage(this.givemark, this.givemarkpower, 0), 0.75D, 0.9D);
                this.vampiric = randomStat(7, RPGI.getVampiric(this.givemark, this.givemarkpower, 0), 1.3D, 1.55D);
                this.manaburn = randomStat(8, RPGI.getVampiric(this.givemark, this.givemarkpower, 0), 0.85D, 1D);
                this.health = randomStat(9, RPGI.getHealth(this.givemark, this.givemarkpower, 0), 1.05D, 1.2D);
                this.armor = randomStat(10, RPGI.getArmor(this.givemark, this.givemarkpower, 0), 1.1D, 1.25D);
                this.magicarmor = randomStat(11, RPGI.getMagicArmor(this.givemark, this.givemarkpower, 0), 0.7D, 0.8D);
                this.resistance = randomStat(12, RPGI.getResistance(this.givemark, this.givemarkpower, 0), 0.8D, 0.9D);
                this.dodge = randomStat(13, RPGI.getDodge(this.givemark, this.givemarkpower, 0), 0.6D, 0.8D);
                this.regeneration = randomStat(14, RPGI.getRegeneration(this.givemark, this.givemarkpower, 0), 1.0D, 1.1D);
                this.trueregeneration = randomStat(15, RPGI.getTrueRegeneration(this.givemark, this.givemarkpower, 0), 1.0D, 1.1D);
                this.speed = randomStat(16, RPGI.getSpeed(this.givemark, this.givemarkpower, 0), 1.0D, 1.0D);
                this.ReturnDamage = randomStat(17, RPGI.getReturnDamage(this.givemark, this.givemarkpower, 0), 1.0D, 1.45D);
                this.DopDamage = randomStat(18, RPGI.getDopDamage(this.givemark, this.givemarkpower, 0), 1.15D, 1.45D);
                this.manaregen = randomStat(19, RPGI.getManaregen(this.givemark, this.givemarkpower, 0), 1.50D, 2.25D);
                this.ingnoringArmor = randomStat(20, RPGI.getManaregen(this.givemark, this.givemarkpower, 0), 1.50D, 2.25D);
                break;
            case 8:
                this.damage = randomStat(1, RPGI.getDamage(this.givemark, this.givemarkpower, 0), 0.65D, 0.75D);
                this.maxdamage = randomStat(2, RPGI.getMaxDamage(this.givemark, this.givemarkpower, 0), 0.85D, 0.9D);
                this.magicdamage = randomStat(3, RPGI.getMagicDamage(this.givemark, this.givemarkpower, 0), 1.25D, 1.35D);
                this.maxmagicdamage = randomStat(4, RPGI.getMaxMagicDamage(this.givemark, this.givemarkpower, 0), 1.25D, 1.35D);
                this.critchance = randomStat(5, RPGI.getCritChance(this.givemark, this.givemarkpower, 0), 0.89D, 1.0D);
                this.critdamage = randomStat(6, RPGI.getCritDamage(this.givemark, this.givemarkpower, 0), 0.89D, 1.0D);
                this.vampiric = randomStat(7, RPGI.getVampiric(this.givemark, this.givemarkpower, 0), 0.85D, 1.0D);
                this.manaburn = randomStat(8, RPGI.getVampiric(this.givemark, this.givemarkpower, 0), 1.15D, 1.6D);
                this.health = randomStat(9, RPGI.getHealth(this.givemark, this.givemarkpower, 0), 1.0D, 1.1D);
                this.armor = randomStat(10, RPGI.getArmor(this.givemark, this.givemarkpower, 0), 0.75D, 1.05D);
                this.magicarmor = randomStat(11, RPGI.getMagicArmor(this.givemark, this.givemarkpower, 0), 0.8D, 1.0D);
                this.resistance = randomStat(12, RPGI.getResistance(this.givemark, this.givemarkpower, 0), 1.0D, 1.1D);
                this.dodge = randomStat(13, RPGI.getDodge(this.givemark, this.givemarkpower, 0), 0.6D, 0.8D);
                this.regeneration = randomStat(14, RPGI.getRegeneration(this.givemark, this.givemarkpower, 0), 1.0D, 1.1D);
                this.trueregeneration = randomStat(15, RPGI.getTrueRegeneration(this.givemark, this.givemarkpower, 0), 1.0D, 1.1D);
                this.speed = randomStat(16, RPGI.getSpeed(this.givemark, this.givemarkpower, 0), 1.0D, 1.0D);
                this.ReturnDamage = randomStat(17, RPGI.getReturnDamage(this.givemark, this.givemarkpower, 0), 1.0D, 1.45D);
                this.DopDamage = randomStat(18, RPGI.getDopDamage(this.givemark, this.givemarkpower, 0), 1.15D, 1.45D);
                this.manaregen = randomStat(19, RPGI.getManaregen(this.givemark, this.givemarkpower, 0), 1.50D, 2.25D);
                this.ingnoringArmor = randomStat(20, RPGI.getManaregen(this.givemark, this.givemarkpower, 0), 1.50D, 2.25D);
                break;
        }
        if (this.damage > this.maxdamage)
            this.maxdamage = this.damage;
        if (this.magicdamage > this.maxmagicdamage)
            this.maxmagicdamage = this.magicdamage;
        rpgitemmarks.put(data, this);
    }

    public static RPGItemMarks getRPGItemMarks(String data) {
        if (data.split("-")[1].equals("1000"))
            return new RPGItemMarks(data);
        if (rpgitemmarks.get(data) == null)
            new RPGItemMarks(data);
        return rpgitemmarks.get(data);
    }

    public int getDamage() {
        return this.damage;
    }

    public int getMaxDamage() {
        return this.maxdamage;
    }

    public int getMagicDamage() {
        return this.magicdamage;
    }

    public int getMaxMagicDamage() {
        return this.maxmagicdamage;
    }

    public int getCritChance() {
        return this.critchance;
    }

    public int getCritDamage() {
        return this.critdamage;
    }

    public int getVampiric() {
        return this.vampiric;
    }

    public int getManaRegen() {
        return this.manaregen;
    }

    public int getManaBurn() {
        return this.manaburn;
    }

    public int getHealth() {
        return this.health;
    }

    public int getArmor() {
        return this.armor;
    }

    public int getMagicArmor() {
        return this.magicarmor;
    }

    public int getResistance() {
        return this.resistance;
    }

    public int getDodge() {
        return this.dodge;
    }

    public int getDodgePower() {
        return this.dodgepower;
    }

    public int getRegeneration() {
        return this.regeneration;
    }

    public int getTrueRegeneration() {
        return this.trueregeneration;
    }

    public int getSpeed() {
        return this.speed;
    }

    public int randomStat(int type, int value, double min, double max) {
        if (value == 0)
            return 0;
        Random r = new Random();
        r.setSeed((this.mark + this.markpower + this.enchant + type));
        int mini = (int)(value * min), maxi = (int)(value * max);
        value = mini;
        if (maxi > mini)
            value += r.nextInt(maxi - mini + 1);
        return value;
    }
}