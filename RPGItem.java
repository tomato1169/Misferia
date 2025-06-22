package azerot.azerot;

import io.netty.util.internal.ThreadLocalRandom;
import lombok.Getter;
import lombok.Setter;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class RPGItem {
    static File items = new File(azerot.getInstance().getDataFolder() + File.separator + "items.yml");

    static FileConfiguration itemid = (FileConfiguration)YamlConfiguration.loadConfiguration(items);

    private int id;

    private int sharp;

    private String name;

    private int type;

    private int quality;

    private int damage;

    private int maxdamage;

    private int magicdamage;

    private int maxmagicdamage;

    private int critchance;

    private int critdamage;

    private int vampiric;

    private int manaregen;

    private int manaburn;

    private int durability;

    private int health;

    private int power;

    private int power1;

    private int armor;

    private int magicarmor;

    private int classes;

    private int resistance;

    private int rlevel;

    private int itemlevel;

    private  int alldamage;

    private int agility;
    private int intelligence;
    private int strength;


    public ItemStack getRPGItem(int id, int enchant, int count, int mark, int markpower, int scaleAtStrength, int scaleAtAgility, int scaleAtIntelligence, rpgplayer p) {
        RPGItemContainer RPGI = RPGItemContainer.getRPGItemContainer(id);
        if (!RPGI.getMaterial().equals("null")) {
            int type = RPGI.getType();
            String name = RPGI.getName();
            int quality = RPGI.getQuality();
            int damage = RPGI.getDamage(mark, markpower,enchant);
            int maxdamage = RPGI.getMaxDamage(mark,markpower,  enchant);
            int magicdamage = RPGI.getMagicDamage(mark,markpower,  enchant);
            int maxmagicdamage = RPGI.getMaxMagicDamage(mark,markpower,  enchant);
            int critchance = RPGI.getCritChance(mark,markpower,  enchant);
            int critdamage = RPGI.getCritDamage(mark,markpower,  enchant);
            int vampiric = RPGI.getVampiric(mark,markpower,  enchant);
            int health = RPGI.getHealth(mark,markpower, enchant);
            int armor = RPGI.getArmor(mark,markpower,  enchant);
            int magicarmor = RPGI.getMagicArmor(mark,markpower,  enchant);
            int resistance = RPGI.getResistance(mark,markpower,  enchant);
            int dodge = RPGI.getDodge(mark, markpower, enchant);
            int regeneration = RPGI.getRegeneration(mark,markpower,  enchant);
            int trueregeneration = RPGI.getTrueRegeneration(mark,markpower,  enchant);
            int returnArrow = RPGI.getReturnArrow();
            int itemprice = RPGI.getItemPrice();
            int level = RPGI.getLevel();
            int speed = RPGI.getSpeed(mark,markpower,  enchant);
            int itemlevel = RPGI.getItemlevel();
            int typeWeapon = RPGI.getTypeWeapon();
            int mininglevel = RPGI.getMiningLevel();
            int soulbound = RPGI.getSoulBound();
            int dopDamage = RPGI.getDopDamage(mark,markpower,  enchant);
            int returnDamage = RPGI.getReturnDamage(mark,markpower,  enchant);
            int resistEffect = RPGI.getProtectionByBleeding(mark,markpower,  enchant);
            int Bleeding = RPGI.getBleeding(mark,markpower,  enchant);
            int frozen = RPGI.getFrozen(mark,markpower,  enchant);
            int curse = RPGI.getCurse(mark,markpower,  enchant);
            int IgnoringArmor = RPGI.getIgnoringArmor(mark,markpower,  enchant);
            int returnarrow = RPGI.getReturnArrow();
            int stan = RPGI.getStan(mark,markpower,  enchant);
            int strength = RPGI.getStrength(mark, markpower, enchant);
            int agility = RPGI.getAgility(mark, markpower, enchant);
            int intelligence = RPGI.getIntelligence(mark, markpower, enchant);

            ItemStack item = null;
            Material material = Material.getMaterial(RPGI.getMaterial());
            short subid = (short)((RPGI.getSubMaterial() != 0) ? RPGI.getSubMaterial() : 0);
            item = new ItemStack(material, 1, subid);
            net.minecraft.server.v1_12_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
            NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();;
            if (compound == null) {
                compound = new NBTTagCompound();
                nmsStack.setTag(compound);
                compound = nmsStack.getTag();
            }
            NBTTagList modifiers = new NBTTagList();
            NBTTagCompound attackspeed = new NBTTagCompound();
            attackspeed.set("AttributeName", (NBTBase)new NBTTagString("generic.attackSpeed"));
            attackspeed.set("Name", (NBTBase)new NBTTagString("generic.attackSpeed"));
            attackspeed.set("Amount", (NBTBase)new NBTTagDouble(100));
            attackspeed.set("Operation", (NBTBase)new NBTTagInt(0));
            attackspeed.set("UUIDLeast", (NBTBase)new NBTTagInt(894654));
            attackspeed.set("UUIDMost", (NBTBase)new NBTTagInt(2872));
            attackspeed.set("Slot", (NBTBase)new NBTTagString("mainhand"));
            modifiers.add((NBTBase)attackspeed);
            compound.set("AttributeModifiers", (NBTBase)modifiers);
            nmsStack.setTag(compound);
            item = CraftItemStack.asBukkitCopy(nmsStack);
            List<String> lore = new ArrayList<>();
            ItemMeta meta = item.getItemMeta();
            if (!RPGI.getColor().equals("null")) {
                String[] cl = RPGI.getColor().trim().split(" ");
                item.setItemMeta(setColor(meta, Color.fromRGB(Integer.parseInt(cl[0]), Integer.parseInt(cl[1]), Integer.parseInt(cl[2]))));
            }
            if (!RPGI.getEnchant().equals("null"))
                meta.addEnchant(Enchantment.getByName(RPGI.getEnchant()), RPGI.getEnchantPower(), true);
            if (mark > 0)
                name = name + " с меткой" + getMark(type, mark);
            meta.setDisplayName(name);
            if (enchant != 0)
                meta.setDisplayName(name + " §7[§c+" + enchant + "§7]");
            boolean extrastring = false;
            alldamage = (maxdamage + damage) / 2;
            int alldamage2 = (maxmagicdamage + magicdamage) / 2;
            if(enchant >= 5){
                meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 1, false );
            }

            if (mark != 100) {
                if (damage != 0) {
                        lore.add("§4⚔ §7Урон: " + damage + "-" + maxdamage + "(" + alldamage + ")");
                        extrastring = true;
                }
                if (magicdamage != 0) {
                        lore.add("§9⚔ §7Магический урон: " + magicdamage + "-" + maxmagicdamage + "(" + alldamage2 + ")");
                    extrastring = true;
                }
                if (critchance != 0) {
                    lore.add("§6☤ §7Шанс крит.удара: " + critchance + "%");
                    extrastring = true;
                }
                if (critdamage != 0) {
                    lore.add("§4♆ §7Крит урон: " + critdamage + "%");
                    extrastring = true;
                }
                if (vampiric != 0) {
                    lore.add("§c☨ §7Вампиризм: " + vampiric + "%");
                    extrastring = true;
                }
                if(IgnoringArmor != 0){
                    lore.add("§5☨ §7Игнорирование брони: " + IgnoringArmor);
                    extrastring = true;
                }
                if(returnArrow != 0){
                    lore.add("§c➳ §7Возвращение стрел: " + returnArrow + "%" );
                    extrastring = true;
                }
                if (health != 0) {
                    lore.add("§4❤ §7Здоровье: " + health);
                    extrastring = true;
                }
                if (armor != 0) {
                    lore.add("§7☫ §7Броня: " + armor);
                    extrastring = true;
                }
                if (magicarmor != 0) {
                    lore.add("§b☬ §7Магическая броня: " + magicarmor);
                    extrastring = true;
                }
                if (resistance != 0) {
                    lore.add("§6❂ §7Сопротивление: " + resistance);
                    extrastring = true;
                }
                if(resistEffect != 0){
                    lore.add("§d❂ §7Сопротивление эффектам: " + resistEffect + "%");
                    extrastring = true;
                }
                if (dodge != 0) {
                    lore.add("§2⟿ §7Уклонение: " + dodge + "%");
                    extrastring = true;
                }
                if (regeneration != 0) {
                    lore.add("§a⚸ §7Регенерация: " + regeneration);
                    extrastring = true;
                }
                if (trueregeneration != 0) {
                    lore.add("§2⚸ §7Боевая регенерация: " + trueregeneration);
                    extrastring = true;
                }
                if(speed != 0){
                    lore.add("§2⚸ §7Скорость передвижения: " + speed + "%");
                    extrastring = true;

                }
                if(dopDamage != 0){
                    lore.add("§c⚔ §7Дополнительный урон: " + dopDamage +"%");
                    extrastring = true;
                }
                if(returnDamage != 0){
                    lore.add("§7❖ §7Возвращение урона: " + returnDamage + "%");
                    extrastring = true;
                }
                if(scaleAtStrength != 0 || scaleAtAgility != 0 || scaleAtIntelligence != 0){
                    lore.add("");
                    lore.add("§7Скалирование: ");
                    if(scaleAtStrength != 0){
                        lore.add("§7Сила: " + getScale(scaleAtStrength));
                    }
                    if(scaleAtAgility != 0){

                        lore.add("§7Ловкость: " + getScale(scaleAtAgility));
                    }
                    if(scaleAtIntelligence != 0){
                        lore.add("§7Интеллект: " + getScale(scaleAtIntelligence));
                    }
                }
                if(strength != 0 || agility!= 0 || intelligence != 0){
                    lore.add("");
                    lore.add("§7Бонусы: ");
                    if(strength != 0){
                        lore.add("§7Сила: " + strength);
                    }
                    if(agility != 0){
                        lore.add("§7Ловкость: " + agility);
                    }
                    if(intelligence != 0){
                        lore.add("§7Интеллект: " + intelligence);
                    }
                }

            } else {
                extrastring = true;
            }
            if(type != 1) {
                if (extrastring) {
                    lore.add("");
                    extrastring = false;
                }
            }
            if(level != 0){
                lore.add("§7Требования: ");
            }
            if (p == null) {
                if (level > 0) {
                    lore.add("§7Уровень: " + level);
                    lore.add("");
                    extrastring = true;
                }
                if(classes > 0){
                    lore.add("§7Требуемый класс: Палач");
                    lore.add("");
                }
                if(type == 5){
                        lore.add("§4Уровень прокачки: 0/1000");
                }

                if (mininglevel > 0) {
                    lore.add("" + mininglevel);
                    extrastring = true;
                }
            } else {
                if (level > 0) {
                    if (p.getPLevel() >= level) {
                        lore.add("§aУровень: " + level + " §a✔" );
                        lore.add("");
                    } else {
                        lore.add("§cУровень: " + level + " §c✖" );
                        lore.add("");
                    }
                    extrastring = true;
                }
            }
            if (itemprice > 0) {
                lore.add("§7Цена предмета: " + getPrice(ItemStat.getEnchantItemPrice(id, enchant)));
                extrastring = true;
            }

            if (RPGI.getRecipe() != 0) {
                      lore = loreItemsRecipes(RPGI.getRecipe(), lore);

            }

            if(!RPGI.getLore(1).equals("null")){
                lore.add("§6---------------");
            }
            for (int i = 1; i <= 5; i++) {
                if (!RPGI.getLore(i).equals("null")) {
                    lore.add("§7§o" + RPGI.getLore(i));
                    extrastring = true;
                }
            }
            if(!RPGI.getLore(1).equals("null")){
                lore.add("§6---------------");
            }
            if (soulbound == 1){
                lore.add("§4Привязано к персонажу");
            }
            if (soulbound == 2){
                lore.add("§4Квестовый предмет");
            }
            lore.add(getQuality(quality));
                        lore.add("§8" + id + "-" + type + "-" + mark + "-" + markpower + "-"+ enchant);
                                meta.setLore(lore);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
            meta.setUnbreakable(true);
            item.setItemMeta(meta);
            item.setAmount(count);
            if (RPGI.getTexture() != null)
                item = setTag(item, "texture", (NBTBase)new NBTTagString(RPGI.getTexture()));
            item = setTag(item, "itemInfo", (NBTBase)new NBTTagString(id + "-" + type + "-" + mark  + "-" + markpower + "-" + enchant + "-" + scaleAtStrength + "-" + scaleAtAgility + "-" + scaleAtIntelligence));
            return item;
        }
        return null;
    }
    public static <T extends NBTBase> T getTag(ItemStack item, String tag) {
        net.minecraft.server.v1_12_R1.ItemStack nms = CraftItemStack.asNMSCopy(item);
        NBTTagCompound nmsTag = nms.getTag();
        if (nmsTag == null)
            nmsTag = new NBTTagCompound();
        return (nmsTag.get(tag) == null) ? null : (T)nmsTag.get(tag);
    }

    public static String getInfo(ItemStack item, String tag, int value) {
        NBTTagString nbtItem = getTag(item, tag);
        if (nbtItem == null)
            return null;
        return nbtItem.c_().split("\\-")[value];
    }

    public ItemMeta setColor(ItemMeta meta, Color color) {
        LeatherArmorMeta lmeta = (LeatherArmorMeta)meta;
        lmeta.setColor(color);
        return (ItemMeta)lmeta;
    }

    private String getScale(int value) {
        String[] grades = {"E", "D", "C", "B", "A", "S"};
        return (value <= grades.length && value > 0) ? grades[value - 1] : "";
    }

    public List<String> loreItemsRecipes(Integer id, List<String> lore){
        lore.add("§7Позволяет создать следующий предмет:");
        lore.add("");

        RPGRecipesContainer rpgRecipesContainer = RPGRecipesContainer.RPGRecipesContainer(id);
        String items1 = rpgRecipesContainer.getItems1();
        String[] items11 = items1.split(";");
        String items2 = rpgRecipesContainer.getItems2();
        String[] items22 = new String[0];
        if(items2 != null){
            items22 = items2.split(";");
        }
        String[] craft = rpgRecipesContainer.getCraft().split(" ");
        List<String> loreCraft = new ArrayList<>();
        loreCraft = getRPGItem(Integer.parseInt(craft[0]),0,1,0,0,0,0,0,null).getItemMeta().getLore();
        loreCraft.remove(loreCraft.size() - 1);
        lore.add(getRPGItem(Integer.parseInt(craft[0]),0,1,0,0,0,0,0,null).getItemMeta().getDisplayName());
        lore.addAll(loreCraft);
        lore.add("");
        lore.add("§5---------------");
        lore.add("§7Необходимо для создания:");
        for (String j : items11) {
            String[] ip = j.trim().split(" ");
            lore.add("  §7-" + RPGItemContainer.getRPGItemContainer(Integer.parseInt(ip[0])).getName() + " x" + Integer.parseInt(ip[1]));
        }
        if(items2 != null) {
            for (String j : items22) {
                String[] ip = j.trim().split(" ");
                lore.add("  §7-" + RPGItemContainer.getRPGItemContainer(Integer.parseInt(ip[0])).getName() + " x" + Integer.parseInt(ip[1]));
            }
        }
        lore.add("§5---------------");

        return lore;
    }


    public void updateItem(Player p) {
        int size = p.getInventory().getSize();
        for (int slot = 0; slot < size; slot++) {
            ItemStack item = p.getInventory().getItem(slot);
            if (item != null)
                if (ItemStat.getID(item) != 0) {
                    p.getInventory().setItem(slot, getRPGItem(ItemStat.getID(item), ItemStat.getEnchant(item), item.getAmount(), ItemStat.getMark(item), ItemStat.getMarkpower(item), ItemStat.getScaleAtStrength(item), ItemStat.getScaleAtAgility(item), ItemStat.getScaleAtIntelligence(item), rpgplayer.getRPGPlayer(p)));
                } else {
                    p.getInventory().setItem(slot, null);
                }
        }
    }
    public static ItemStack setTag(ItemStack item, String tag, NBTBase value) {
        net.minecraft.server.v1_12_R1.ItemStack nms = CraftItemStack.asNMSCopy(item);
        NBTTagCompound nmsTag = nms.getTag();
        if (nmsTag == null)
            nmsTag = new NBTTagCompound();
        nmsTag.set(tag, value);
        nms.setTag(nmsTag);
        return CraftItemStack.asBukkitCopy(nms);
    }

    public String getQuality(int quality) {
        if (quality == 1)
            return "§8§nПосредственный предмет";
        if (quality == 2)
            return "§2§nКачественный предмет";
        if (quality == 3)
            return "§9§nОтменный предмет";
        if (quality == 4)
            return "§5§nДревний предмет";
        if (quality == 5)
            return "§6§nМифический предмет";
        if (quality == 6)
            return "§e§nАртефакт";
        if (quality == 7)
            return "§4§nБожественный предмет";
        if (quality == 11)
            return "§4§l§nСлужебный предмет";
        return "";
    }

    public String getPrice(int itemprice) {
        String price = "";
        int emerald5 = 0;
        int emerald4 = 0;
        int emerald3 = 0;
        int emerald2 = 0;
        int emerald1 = 0;
        while (itemprice >= 16777216) {
            itemprice -= 16777216;
            emerald5++;
        }
        while (itemprice >= 262144) {
            itemprice -= 262144;
            emerald4++;
        }
        while (itemprice >= 4096) {
            itemprice -= 4096;
            emerald3++;
        }
        while (itemprice >= 64) {
            itemprice -= 64;
            emerald2++;
        }
        while (itemprice > 0 && itemprice < 64) {
            itemprice--;
            emerald1++;
        }
        if (emerald5 > 0)
            price = price + "§7" + emerald5 + " §6⛃ ";
        if (emerald4 > 0)
            price = price + "§7" + emerald4 + " §5⛃ ";
        if (emerald3 > 0)
            price = price + "§7" + emerald3 + " §9⛃ ";
        if (emerald2 > 0)
            price = price + "§7" + emerald2 + " §2⛃ ";
        if (emerald1 > 0)
            price = price + "§7" + emerald1 + " §f⛃ ";
        return price;
    }

    public String getMark(int type, int mark) {
        switch (type) {
            case 2:
                if (mark == 1)
                    return " убийцы";
                if (mark == 2)
                    return " ярости";
                if (mark == 3)
                    return " солнца";
                if (mark == 4)
                    return " разрушителя";
                if (mark == 5)
                    return " луны";
                if (mark == 6)
                    return " зверя";
                if (mark == 7)
                    return " вампира";
                if (mark == 8)
                    return " волшебника";

                break;
            case 3:
            case 4:
                if (mark == 1)
                    return " целителя";
                if (mark == 2)
                    return " чародея";
                if (mark == 3)
                    return " стойкости";
                if (mark == 4)
                    return " полководца";
                if (mark == 5)
                    return " духа";
                if (mark == 6)
                    return " баланса";
                if (mark == 7)
                    return " духа";
                if (mark == 8)
                    return " духа";
                break;
        }
        return "?";
    }

    public int getIQuality(int id) {
        return (itemid.getString(String.valueOf(id) + ".quality") != null) ? itemid.getInt(String.valueOf(id) + ".quality") : 0;
    }

    public int getRLevel(int id) {
        return (itemid.getString(String.valueOf(id) + ".level") != null) ? itemid.getInt(String.valueOf(id) + ".level") : 0;
    }

    public int getRStrength(int id) {
        return (itemid.getString(String.valueOf(id) + ".strength") != null) ? itemid.getInt(String.valueOf(id) + ".strength") : 0;
    }

    public int getRAgility(int id) {
        return (itemid.getString(String.valueOf(id) + ".agility") != null) ? itemid.getInt(String.valueOf(id) + ".agility") : 0;
    }

    public int getRintelligence(int id) {
        return (itemid.getString(String.valueOf(id) + ".intelligence") != null) ? itemid.getInt(String.valueOf(id) + ".intelligence") : 0;
    }
}
