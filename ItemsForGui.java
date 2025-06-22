package azerot.azerot;

import azerot.azerot.Quest.Quest;
import azerot.azerot.enchants.chance;
import azerot.azerot.mobs.MobSystem;
import azerot.azerot.utils.Reflections;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.mojang.authlib.GameProfile;
import org.apache.commons.codec.binary.Base64;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import io.netty.util.internal.ThreadLocalRandom;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.NBTTagList;
import net.minecraft.server.v1_12_R1.NBTTagString;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import azerot.azerot.mobs.RPGMobsContainer;

import java.io.File;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.*;

public class ItemsForGui {


    public static ItemStack GUIItem(ItemStack item) {
        ItemMeta im = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.addAll(im.getLore());
        lore.add("§8Предмет в меню");
                im.setLore(lore);
        item.setItemMeta(im);
        return item;
    }

    public static ItemStack setUnbreakable(ItemStack item) {
        ItemMeta im = item.getItemMeta();
        im.setUnbreakable(true);
        item.setItemMeta(im);
        return item;
    }
    public static ItemStack emblem(rpgplayer p){
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 2);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§5Эмблемы");
        List<String> lore = new ArrayList<>();
        lore.add("§7Здесь вы можете прокачать добытые эмблемы!");

        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack EC(){
        ItemStack item = new ItemStack(Material.ENDER_CHEST, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Хранилище");
        List<String> lore = new ArrayList<>();
        lore.add("§7Здесь вы можете хранить предметы");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack RCIn(){
        ItemStack item = new ItemStack(Material.ENDER_CHEST, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Хранилище рецептов");
        List<String> lore = new ArrayList<>();
        lore.add("§7Здесь вы можете хранить рецепты");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack rejim(){
        ItemStack item = new ItemStack(Material.BOOK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Misferia - как режим");
        List<String> lore = new ArrayList<>();
        lore.add("§7Режим был разработан в стиле MMORPG.");
        lore.add("§7Выполняйте задания. Все таки в них представлен история мира.");
        lore.add("§7Сражайтесь с монстрами, либо же игроками. Прокачивайте свое снаряжение при помощи системы заточки");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack mobs(){
        ItemStack item = new ItemStack(Material.BOOK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Монстры");
        List<String> lore = new ArrayList<>();
        lore.add("§7Монстры представлены в нескольких экземплярах");
        lore.add("§7С белым названием - это обычные монстры, как правило, у них меньше всего урона и здоровья и их легче всего убить");
        lore.add("§7С красным название - элитные монстры, имеют больше здоровья и урона, нежели обычные монстры");
        lore.add("§7так же у них могут быть свои способности");
        lore.add("§7С жирным красным - боссы, самые опасные монстры, имеют ряд способстей и довольно сложно убить в одиночку");
        lore.add("§7Требуется прокачаная экипировка");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack mechanika(){
        ItemStack item = new ItemStack(Material.BOOK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Урон-");
        List<String> lore = new ArrayList<>();
        lore.add("§7Тип урона может быть физическим или же магическим");
        lore.add("§7Физический урон блокируется броней");
        lore.add("§7Следовательно, магический урон блокируется магической броней");
        lore.add("§7Сопротивление - блокирует любой тип урон, 1 очко сопротивления = 1 урон");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack zatochka(){
        ItemStack item = new ItemStack(Material.BOOK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Заточка-");
        List<String> lore = new ArrayList<>();
        lore.add("§7До 30 уровня, чтобы улучшить свою экипировку");
        lore.add("§7Вам следует для начала выбить звезду заточки и в инвентаре взять заточку в мышку и кликнуть на предмет");
        lore.add("§7который вы хотите зачаровать");
        lore.add("§7Звезды заточки бывают 2 типов");
        lore.add("§7Обычная заточка: при неудачу сбрасывает уровень заточки до нуля");
        lore.add("§7Благославленная заточка: при неудачу сбрасывает уровень заточки на одну единицу");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack firstPatch(){
        ItemStack item = new ItemStack(Material.BOOK_AND_QUILL, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§7§lНачало");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack FilterItems(int type){
        ItemStack item = new ItemStack(Material.COMPASS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§7§lФильтр предметов");
        List<String> lore = new ArrayList<>();
        switch (type){
            case 0:
                lore.add("Нажмите, чтобы фильтровать");
                break;
            case 1:
                lore.add("§6Обычные предметы");
                break;
            case 2:
                lore.add("§4Оружие");
                break;
            case 3:
                lore.add("§2Броня");
                break;
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack secondPatch(){
        ItemStack item = new ItemStack(Material.BOOK_AND_QUILL, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§7§lПуть туда");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack thirdPatch(){
        ItemStack item = new ItemStack(Material.BOOK_AND_QUILL, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6§lНовый мир");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack unavailablePatch(){
        ItemStack item = new ItemStack(Material.BOOK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§4§lНедоступно в данном дополнении!");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }



    public static ItemStack CharacterInfo() {
        ItemStack item = new ItemStack(Material.GOLD_NUGGET, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§5Информация о персонаже");
                List<String> lore = new ArrayList<>();
        lore.add("§7§lПозволяет посмотреть информацию о персонаже");
                meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public  static ItemStack bestiary(){
        ItemStack item = new ItemStack(Material.BOOK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§fБестиарий");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public  static ItemStack tags(){
        ItemStack item = new ItemStack(Material.BOOK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§fМетки");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public  static ItemStack bosses(){
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 2);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§fБоссы");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public  static ItemStack Japan_PVPEvent(){
        ItemStack item = new ItemStack(Material.WOOL, 1, (short) 7);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§5Священная земля");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public  static ItemStack Japan_BLUETeam(){
        ItemStack item = new ItemStack(Material.WOOL, 1, (short) 3);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§9Синяя команда");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public  static ItemStack Japan_REDTeam(){
        ItemStack item = new ItemStack(Material.WOOL, 1, (short) 4);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§4Красная команда");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public  static ItemStack mechanics(){
        ItemStack item = new ItemStack(Material.BOOK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§fМеханики");
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public  static ItemStack spells(){
        ItemStack item = new ItemStack(Material.BOOK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§fСпособности");
        List<String> lore = new ArrayList<>();
        lore.add("§7Все свои способности можно найти в меню крафта");
        lore.add("§7Информация о персонаже -> способности");
        lore.add("");
        lore.add("§7Использование способностей:");
        lore.add("§7Вы можете использовать способность выбраной последовательностью кликов");
        lore.add("§7Бинд на F, вы можете использовать на клавишу смены руки");
        lore.add("");
        lore.add("§7Бинд способностей:");
        lore.add("§7Вы должны выбрать способность, а потом кликнуть на любой бинд, который вам подходит");
        lore.add("");
        lore.add("§7Очистка способности:");
        lore.add("§7Кликните на предмет 'Убрать привязку'");
        lore.add("§7А потом на бинд, который вам не нужен");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public  static ItemStack tags1(){
        ItemStack item = new ItemStack(Material.BOOK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§fМетки §4убийцы §fи §2целителя");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§4Метка убийцы:");
        lore.add("§7Физический урон варьируется между 95-125% от изначальных значений");
        lore.add("§7Магический урон варьируется между 80-95% от изначальных значений");
        lore.add("§7Шанс критического урона варьируется между 110-120% от изначальных значений");
        lore.add("§7Критический урон варьируется между 135-155% от изначальных значений");
        lore.add("§7Вампиризм варьируется между 50-75% от изначальных значений");
        lore.add("§7Выжигание маны варьируется между 85-100% от изначальных значений");
        lore.add("§7Игнорирование брони варьируется между 150-225% от изначальных значений");
        lore.add("§2Метка целителя:");
        lore.add("§7Здоровье варьируется между 200-210% от изначальных значений");
        lore.add("§7Броня варьируется между 75-100% от изначальных значений");
        lore.add("§7Магическая броня варьируется между 75-100% от изначальных значений");
        lore.add("§7Сопротивление варьируется между 50-75% от изначальных значений");
        lore.add("§7Уклонение варьируется между 60-70% от изначальных значений");
        lore.add("§7Регенерация варьируется между 90-110% от изначальных значений");
        lore.add("§7Регенерация варьируется между 90-110% от изначальных значений");
        lore.add("§7Возвращение урона варьируется между 100-110% от изначальных значений");
        lore.add("§7Дополнительный урон варьируется между 100-110% от изначальных значений");
        lore.add("§7Востановление маны варьируется между 100-115% от изначальных значений");

        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public  static ItemStack tags2(){
        ItemStack item = new ItemStack(Material.BOOK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§fМетки §4ярости §fи §2чародея");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§4Метка ярости:");
        lore.add("§7Физический урон варьируется между 105-120% от изначальных значений");
        lore.add("§7Магический урон варьируется между 105-120% от изначальных значений");
        lore.add("§7Шанс критического урона варьируется между 135-175% от изначальных значений");
        lore.add("§7Критический урон варьируется между 100-110% от изначальных значений");
        lore.add("§7Вампиризм варьируется между 85-100% от изначальных значений");
        lore.add("§7Выжигание маны варьируется между 125-155% от изначальных значений");
        lore.add("§7Игнорирование брони варьируется между 120-135% от изначальных значений");
        lore.add("§2Метка чародея:");
        lore.add("§7Здоровье варьируется между 105-120% от изначальных значений");
        lore.add("§7Броня варьируется между 90-100% от изначальных значений");
        lore.add("§7Магическая броня варьируется между 115-130% от изначальных значений");
        lore.add("§7Сопротивление варьируется между 90-100% от изначальных значений");
        lore.add("§7Уклонение варьируется между 115-135% от изначальных значений");
        lore.add("§7Регенерация варьируется между 115-135% от изначальных значений");
        lore.add("§7Допольнительный урон варьируется между 115-145% от изначальных значений");
        lore.add("§7Регенерация маны варьируется между 150-225% от изначальных значений");
        lore.add("§7Возвращение урона варьируется между 100-145% от изначальных значений");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public  static ItemStack tags3(){
        ItemStack item = new ItemStack(Material.BOOK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§fМетки §4солнца §fи §2стойкости");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§4Метка солнца:");
        lore.add("§7Физический урон варьируется между 135-155% от изначальных значений");
        lore.add("§7Магический урон варьируется между 60-85% от изначальных значений");
        lore.add("§7Шанс критического урона варьируется между 89-100% от изначальных значений");
        lore.add("§7Критический урон варьируется между 110-130% от изначальных значений");
        lore.add("§7Вампиризм варьируется между 85-100% от изначальных значений");
        lore.add("§7Выжигание маны варьируется между 85-95% от изначальных значений");
        lore.add("§7Игнорирование брони варьируется между 110-115% от изначальных значений");
        lore.add("§2Метка стойкости:");
        lore.add("§7Здоровье варьируется между 100-120% от изначальных значений");
        lore.add("§7Броня варьируется между 115-135% от изначальных значений");
        lore.add("§7Магическая броня варьируется между 120-135% от изначальных значений");
        lore.add("§7Сопротивление варьируется между 115-150% от изначальных значений");
        lore.add("§7Уклонение варьируется между 40-50% от изначальных значений");
        lore.add("§7Регенерация варьируется между 60-85% от изначальных значений");
        lore.add("§7Допольнительный урон варьируется между 100-115% от изначальных значений");
        lore.add("§7Регенерация маны варьируется между 100-115% от изначальных значений");
        lore.add("§7Возвращение урона варьируется между 100-145% от изначальных значений");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public  static ItemStack tags4(){
        ItemStack item = new ItemStack(Material.BOOK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§fМетки §4разрушителя §fи §2полководца");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§4Метка разрушителя:");
        lore.add("§7Физический урон варьируется между 112-117% от изначальных значений");
        lore.add("§7Магический урон варьируется между 100-120% от изначальных значений");
        lore.add("§7Шанс критического урона варьируется между 100-110% от изначальных значений");
        lore.add("§7Критический урон варьируется между 105-150% от изначальных значений");
        lore.add("§7Вампиризм варьируется между 50-75% от изначальных значений");
        lore.add("§7Выжигание маны варьируется между 105-125% от изначальных значений");
        lore.add("§7Игнорирование брони варьируется между 115-150% от изначальных значений");
        lore.add("§2Метка полководца:");
        lore.add("§7Здоровье варьируется между 100-145% от изначальных значений");
        lore.add("§7Броня варьируется между 95-125% от изначальных значений");
        lore.add("§7Магическая броня варьируется между 95-125% от изначальных значений");
        lore.add("§7Сопротивление варьируется между 75-115% от изначальных значений");
        lore.add("§7Уклонение варьируется между 110-155% от изначальных значений");
        lore.add("§7Регенерация варьируется между 100-155% от изначальных значений");
        lore.add("§7Допольнительный урон варьируется между 95-115% от изначальных значений");
        lore.add("§7Регенерация маны варьируется между 150-175% от изначальных значений");
        lore.add("§7Возвращение урона варьируется между 90-120% от изначальных значений");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public  static ItemStack tags5(){
        ItemStack item = new ItemStack(Material.BOOK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§fМетки §9луны §fи §9духа");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§4Метка луны:");
        lore.add("§7Физический урон варьируется между 60-85% от изначальных значений");
        lore.add("§7Магический урон варьируется между 135-155% от изначальных значений");
        lore.add("§7Шанс критического урона варьируется между 100-120% от изначальных значений");
        lore.add("§7Критический урон варьируется между 100-120% от изначальных значений");
        lore.add("§7Вампиризм варьируется между 90-110% от изначальных значений");
        lore.add("§7Выжигание маны варьируется между 125-155% от изначальных значений");
        lore.add("§7Игнорирование брони варьируется между 100-115% от изначальных значений");
        lore.add("§2Метка духа:");
        lore.add("§7Здоровье варьируется между 100-130% от изначальных значений");
        lore.add("§7Броня варьируется между 105-130% от изначальных значений");
        lore.add("§7Магическая броня варьируется между 105-130% от изначальных значений");
        lore.add("§7Сопротивление варьируется между 100-130% от изначальных значений");
        lore.add("§7Уклонение варьируется между 100-155% от изначальных значений");
        lore.add("§7Регенерация варьируется между 50-125% от изначальных значений");
        lore.add("§7Допольнительный урон варьируется между 115-125% от изначальных значений");
        lore.add("§7Регенерация маны варьируется между 150-175% от изначальных значений");
        lore.add("§7Возвращение урона варьируется между 100-125% от изначальных значений");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public  static ItemStack tags6(){
        ItemStack item = new ItemStack(Material.BOOK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§fМетки §7зверя §fи §9баланса");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§4Метка зверя:");
        lore.add("§7Физический урон варьируется между 122-144% от изначальных значений");
        lore.add("§7Магический урон варьируется между 65-75% от изначальных значений");
        lore.add("§7Шанс критического урона варьируется между 90-130% от изначальных значений");
        lore.add("§7Критический урон варьируется между 90-130% от изначальных значений");
        lore.add("§7Вампиризм варьируется между 50-90% от изначальных значений");
        lore.add("§7Выжигание маны варьируется между 85-100% от изначальных значений");
        lore.add("§7Игнорирование брони варьируется между 105-135% от изначальных значений");
        lore.add("§2Метка баланса:");
        lore.add("§7Все характеристики варьируется между 105-135% от изначальных значений");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }


    public  static ItemStack SecondChance(rpgplayer p){
        ItemStack item = new ItemStack(Material.BOOK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§4Эмблема: Второй шанс");
        List<String> lore = new ArrayList<>();
        lore.add("§eУровень " + p.getTalents().get("SecondChance") + "/6");
        if( p.getTalents().get("SecondChance") != 6){
            lore.add("");
            lore.add(p.getTalents().get("SecondChance") == 6 ? "" : "§7Количество очков для улучшения " + p.getTalentsPoints().get("SecondChance") + "/" + ((int) Math.pow(2, p.getTalents().get("SecondChance"))));
            lore.add("");
        }
        lore.add("§7При получении смертельного урона");
        lore.add("§7вы восстановите " + (p.getTalents().get("SecondChance") * 10) + "% своего здоровья.");
        switch (p.getTalents().get("SecondChance")){
            case 0:
                lore.add("§7Перезарядка: 10 минут");
                break;
            case 1:
                lore.add("§7Перезарядка: 10 минут");
                break;
            case 2:
                lore.add("§7Перезарядка: 9 минут");
                break;
            case 3:
                lore.add("§7Перезарядка: 8 минут");
                break;
            case 4:
                lore.add("§7Перезарядка: 7 минут");
                break;
            case 5:
                lore.add("§7Перезарядка: 6 минут");
                break;
            case 6:
                lore.add("§7Перезарядка: 5 минут");
                break;

        }
        if(p.getActivatedtalents().get("SecondChance")){
            lore.add("§2Эмблема включена!");
        }else {
            lore.add("§4Эмблема выключена!");
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public  static ItemStack survivalinstinct(rpgplayer p){
        ItemStack item = new ItemStack(Material.BOOK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§2Эмблема: Инстинкт выживания");
        List<String> lore = new ArrayList<>();
        lore.add("§eУровень " + p.getTalents().get("Survivalinstinct") + "/10");
        if(p.getTalents().get("Survivalinstinct") != 10){
            lore.add("");
            lore.add(p.getTalents().get("Survivalinstinct") == 10 ? "" : "§7Количество очков для улучшения " + p.getTalentsPoints().get("Survivalinstinct") + "/" +  ((int) Math.pow(2, p.getTalents().get("Survivalinstinct"))));
            lore.add("");
        }
        lore.add("§7Пассивно увеличивает максимальный запас здоровья.");
        lore.add("");
        lore.add("§7+5% к максимальному здоровью");
        lore.add("§7Дополнительно: +10% от показателя силы");
        lore.add("");
        if(p.getActivatedtalents().get("Survivalinstinct")){
            lore.add("§2Эмблема включена!");
        }else {
            lore.add("§4Эмблема выключена!");
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public  static ItemStack magicalfoundation(rpgplayer p){
        ItemStack item = new ItemStack(Material.BOOK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§2Эмблема: Магическая основа");
        List<String> lore = new ArrayList<>();
        lore.add("§eУровень " + p.getTalents().get("Magicalfoundation") + "/10");
        if(p.getTalents().get("Magicalfoundation") != 10){
            lore.add("");
            lore.add(p.getTalents().get("Magicalfoundation") == 10 ? "" : "§7Количество очков для улучшения " + p.getTalentsPoints().get("Magicalfoundation") + "/" + ((int) Math.pow(2, p.getTalents().get("Magicalfoundation"))));
            lore.add("");
        }
        lore.add("§7За каждый уровень увеличивает количество маны на 5%");
        lore.add("");
        if(p.getActivatedtalents().get("Magicalfoundation")){
            lore.add("§2Эмблема включена!");
        }else {
            lore.add("§4Эмблема выключена!");
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public  static ItemStack SunAndMoon(rpgplayer p){
        ItemStack item = new ItemStack(Material.WATCH, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§7Эмблема: Солнце и луна");
        List<String> lore = new ArrayList<>();
        lore.add("§eУровень " + p.getTalents().get("SunAndMoon") + "/10");
        if(p.getTalents().get("SunAndMoon") != 10){
            lore.add("");
            lore.add(p.getTalents().get("SunAndMoon") == 10 ? "" : "§7Количество очков для улучшения " + p.getTalentsPoints().get("SunAndMoon") + "/" + ((int) Math.pow(2, p.getTalents().get("SunAndMoon"))));
            lore.add("");
        }
        lore.add("§7Днем увеличивает восстановление здовья");
        lore.add("§7а ночью восстановление маны");
        lore.add("");
        if(p.getActivatedtalents().get("SunAndMoon")){
            lore.add("§2Эмблема включена!");
        }else {
            lore.add("§4Эмблема выключена!");
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public  static ItemStack precisestrikes(rpgplayer p){
        ItemStack item = new ItemStack(Material.BOOK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§9Эмблема: точные удары");
        List<String> lore = new ArrayList<>();
        lore.add("§eУровень " + p.getTalents().get("Precisestrikes") + "/10");
        lore.add("");
        lore.add("§7Каждая атака может с вероятностью в " + (p.getTalents().get("Precisestrikes") + 3 + "%"));
        lore.add("§7пройти сквозь уклонение и нанести" + (p.getTalents().get("Precisestrikes") + 3 + "% чистого урона"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }


    public static ItemStack BlockCharacterInfo(rpgplayer p) {
        ItemStack item = new ItemStack(Material.NETHER_STAR, 1);
        ItemMeta meta = item.getItemMeta();
        DecimalFormat myFormat = new DecimalFormat("#.##");
        meta.setDisplayName("§5Информация о персонаже");
                List<String> lore = new ArrayList<>();
        lore.add("§7§oИгрок: §6" + p.toPlayer().getName());
                lore.add("");
        lore.add("§7Уровень: §a" + p.getPLevel());
                lore.add( "§7Опыт: §6" + p.getXP() + "§7/§6" + p.getLXp());
        if (p.getXpModifier() > 0.0D)
            lore.add("§7Дополнительный опыт: §6" + (int)(p.getXpModifier() * 100.0D) + "%");
        if (p.getMiningLevel() > 0.0D)
            lore.add("§7Навык вскапывания руд: §6" + p.getMiningLevel());
                    lore.add("§7Очки навыков: §a" + p.getSkillpoint());

        lore.add("§7Сопротивление эффектам: §9" + p.getProtectbyeffect());
        lore.add("§7Дополнительный урон: §9" + p.getDopdamage());
        lore.add("§7Возвращение урона: §9" + p.getReturnDamage());
        lore.add("§7Критический шанс: §9" + p.getCritChance());
        lore.add("§7Критический урон: §9" + p.getCritDamage());
        lore.add("§7Вампиризм: §9" + p.getVampiric());
        lore.add("§7Голда: " + p.getGold());
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack OpenSpellBinding(rpgplayer p) {
        ItemStack item = new ItemStack(Material.BOOK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§5Способности");
                List<String> lore = new ArrayList<>();
        lore.add("§7Нажмите, чтобы открыть меню способностей");
                meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack BindBook(String bind, rpgplayer p) {
        ItemStack item = new ItemStack(Material.BOOK_AND_QUILL, 1);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        if (bind.equals("R"))
            meta.setDisplayName("§6Right");
        if (bind.equals("RR"))
            meta.setDisplayName("§6Right-Right");
        if (bind.equals("RL"))
            meta.setDisplayName("§6Right-Left");
        if (bind.equals("RRR"))
            meta.setDisplayName("§6Right-Right-Right");
        if (bind.equals("RRL"))
            meta.setDisplayName("§6Right-Right-Left");
        if (bind.equals("RLR"))
            meta.setDisplayName("§6Right-Left-Right");
        if (bind.equals("RLL"))
            meta.setDisplayName("§6Right-Left-Left");
        if(bind.equals("F"))
            meta.setDisplayName("§6F");
        if (p.getSpellBind(bind) != 0) {
            meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
            lore.add("§7Заклинание: " + SpellBook(p.getSpellBind(bind)).getItemMeta().getDisplayName());
        } else {
            lore.add("§cЗаклинание отсутствует");
        }
        meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack SpellClear() {
        ItemStack item = new ItemStack(Material.BARRIER, 1);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        meta.setDisplayName("§cУбрать привязку");
                lore.add("§7Убирает способность");
                        meta.setLore(lore);
        item.setItemMeta(meta);
        item = GUIItem(item);
        return item;
    }
    public static ItemStack OpenDataBase() {
        ItemStack item = new ItemStack(Material.NETHER_STAR, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§5Открыть информацию о существах");
                List<String> lore = new ArrayList<>();
        lore.add("§7§lНажмите, чтобы открыть информацию о существах");
                meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static  ItemStack lkForTrade(){
        ItemStack item = new ItemStack(Material.GOLD_BLOCK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6+5");
        List<String> lore = new ArrayList<>();
        lore.add("§2Нажмите, чтобы добавить 5");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static  ItemStack money1(){
        ItemStack item = new ItemStack(Material.EMERALD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Деревянная монетка");
        List<String> lore = new ArrayList<>();
        lore.add("§2Левый клик добавляет");
        lore.add("§4Правый клик убирает");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static  ItemStack money2(){
        ItemStack item = new ItemStack(Material.EMERALD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§2Серебряная монета");
        List<String> lore = new ArrayList<>();
        lore.add("§2Левый клик добавляет");
        lore.add("§4Правый клик убирает");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static  ItemStack money3(){
        ItemStack item = new ItemStack(Material.EMERALD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§8Золотая монета");
        List<String> lore = new ArrayList<>();
        lore.add("§2Левый клик добавляет");
        lore.add("§4Правый клик убирает");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static  ItemStack money4(){
        ItemStack item = new ItemStack(Material.EMERALD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§5Алмазная монета");
        List<String> lore = new ArrayList<>();
        lore.add("§2Левый клик добавляет");
        lore.add("§4Правый клик убирает");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static  ItemStack moneyForTrade(int moneyT1){
        ItemStack item = new ItemStack(Material.EMERALD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Монеты");
        List<String> lore = new ArrayList<>();
        int emerald5 = 0;
        int emerald4 = 0;
        int emerald3 = 0;
        int emerald2 = 0;
        int emerald1 = 0;
        while (moneyT1 >= 16777216) {
            moneyT1 -= 16777216;
            emerald5++;
        }
        while (moneyT1 >= 262144) {
            moneyT1 -= 262144;
            emerald4++;
        }
        while (moneyT1 >= 4096) {
            moneyT1 -= 4096;
            emerald3++;
        }
        while (moneyT1 >= 64) {
            moneyT1 -= 64;
            emerald2++;
        }
        while (moneyT1 > 0 && moneyT1 < 64) {
            moneyT1--;
            emerald1++;
        }
        if(emerald1 != 0){
            lore.add("§7" + emerald1 + " деревянных монет!");
        }
        if(emerald2 != 0){
            lore.add("§2" + emerald2 + " серебрянных монет!");
        }
        if(emerald3 != 0){
            lore.add("§9" + emerald3 + " золотых монет!");
        }
        if(emerald4 != 0){
            lore.add("§5" + emerald4 + " алмазных монет!");
        }

        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }


    public static  ItemStack lk2ForTrade(){
        ItemStack item = new ItemStack(Material.GOLD_BLOCK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6+10");
        List<String> lore = new ArrayList<>();
        lore.add("§2Нажмите, чтобы добавить 10");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static  ItemStack lkTrade(int lk){
        ItemStack item = new ItemStack(Material.GOLD_BLOCK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6LK");
        List<String> lore = new ArrayList<>();
        lore.add("" + lk);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static  ItemStack lkMinusTrade(){
        ItemStack item = new ItemStack(Material.GOLD_BLOCK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6-5");
        List<String> lore = new ArrayList<>();
        lore.add("§cНажмите, чтобы убрать 5");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static  ItemStack lk2MinusTrade(){
        ItemStack item = new ItemStack(Material.PRISMARINE_CRYSTALS);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6-10");
        List<String> lore = new ArrayList<>();
        lore.add("§cНажмите, чтобы убрать 5");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack BlockDataBase(int id, int level, String name, String type, int typeMob, double respawn, String drop) {
        ItemStack item = null;
        RPGMobsContainer mobsContainer = RPGMobsContainer.getRPGMobsContainer(id);
        item = new ItemStack(Material.ROTTEN_FLESH, 1);

        item = getCustomSkull(azerot.getEntitySkinsURL().get(id));


        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§7Существо: §4["+ level + "] " + name);
        if(typeMob == 2){
            meta.setDisplayName("§7Существо: §4§l["+ level + "] " + name + " [Boss]");
        }
        List<String> lore = new ArrayList<>();


         String[] drops = drop.split(";");

        lore.add("");
                lore.add("§aВремя возрождения: §6" + (respawn / 60.0D) + " мин");
         if (!drop.isEmpty())
            lore.add("§aНаграда:");


         int maxDrop = 0;
        for (String i : drops) {
            String[] it = i.split(" ");

                int dropid = Integer.parseInt(it[0]);
                int maxamount = Integer.valueOf(it[1]);
                double chance = Double.valueOf(it[2]);
                RPGItemContainer rpgItemContainer = RPGItemContainer.getRPGItemContainer(dropid);

                ItemStack rpgitem = null;
                if (!ItemStat.hasMark(dropid)) {
                    if(rpgItemContainer.getType() == 2 || rpgItemContainer.getType() == 3){
                        rpgitem = (new RPGItem()).getRPGItem(dropid, 0, 1,  0, 1, 0,0,0,null);
                    }else{
                        rpgitem = (new RPGItem()).getRPGItem(dropid, 0, 1,  0, 0, 0,0,0,null);
                    }
                } else {
                    rpgitem = (new RPGItem()).getRPGItem(dropid, 0, 1,  100, 1,0,0,0,null);
                }
                maxDrop++;
                if(maxDrop == 21){
                    lore.add("§6...");
                    break;
                }
                if (maxamount == 1)
                    lore.add(rpgitem.getItemMeta().getDisplayName() + " §e" + chance + "%");
                if (maxamount > 1)
                    lore.add(rpgitem.getItemMeta().getDisplayName() + " §71-" + maxamount + " §e" + chance + "%");

        }

        lore.add("");
        lore.add("§8" + id);
                meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    private static final Base64 base64 = new Base64();

    public static ItemStack getCustomSkull(String url) {
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        PropertyMap propertyMap = profile.getProperties();
        if (propertyMap == null) {
            throw new IllegalStateException("Profile doesn't contain a property map");
        }

        byte[] encodedData = base64.encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        propertyMap.put("textures", new Property("textures", new String(encodedData)));
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        ItemMeta headMeta = head.getItemMeta();
        Class<?> headMetaClass = headMeta.getClass();
        Reflections.getField(headMetaClass, "profile", GameProfile.class).set(headMeta, profile);
        head.setItemMeta(headMeta);
        return head;
    }
    public static ItemStack EntityStats(int id) {
        RPGMobsContainer mobs = RPGMobsContainer.getRPGMobsContainer(id);
        ItemStack item = null;
        item = new ItemStack(Material.DIAMOND, 1);
        int health = mobs.getHealth();
        int armor = mobs.getArmor();
        int magicarmor = mobs.getMagicarmor();
        int resistance = mobs.getResistance();
        int damage = mobs.getDamage();
        int magicdamage = mobs.getMagicdamage();
        int critchance = mobs.getCritchance();
        int vampiric = mobs.getVampiric();
        int attackspeed = mobs.getAttackspeed();
        int frozen = mobs.getFrozen();
        int bleeding = mobs.getBleeding();
        int curse = mobs.getCurse();
        int stanchik = mobs.getStanchik();
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§5Характеристика существа: " + mobs.getName());
                List<String> lore = new ArrayList<>();
        lore.add("§4❤ §7Здоровье: " + health);
        if(armor != 0){
         lore.add("§7☫ §7Броня: " + armor);
        }
        if(magicarmor != 0){
        lore.add("§b☫ §7Магическая броня: " + magicarmor);
        }
        if(resistance != 0){
        lore.add("§6❂ §7Сопротивление: " + resistance);
        }
        if(damage != 0){
        lore.add("§4⚔ §7Урон: " + damage);
        }
        if(magicdamage != 0){
        lore.add("§9⚔ §7Магический урон: " + magicdamage);
        }
        if(critchance != 0){
        lore.add("§6☤ §7Шанс крита: " +  critchance + "%");
        }
        if(vampiric != 0){
        lore.add("§с☨ §7Вампиризм: " + vampiric + "%");
        }
        if(frozen != 0){
        lore.add("§9❄ §7Заморозка: " +  frozen + "%");
        }
        if(bleeding != 0){
        lore.add("§4⚚ §7Кровотечение: " + bleeding + "%");
        }
        if(stanchik != 0){
        lore.add("§6✺ §7Оглушение: " +  stanchik + "%");
        }
        if(curse != 0){
        lore.add("§0✹ §7Проклятие: " + curse + "%");
        }
        if(attackspeed != 0){
        lore.add("§b♆ §7Скорость атаки: " + attackspeed);
        }
        lore.add("");
        lore.add(""+ id);
                meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack BlockDataBaseNextPage(int id) {
        ItemStack item = new ItemStack(Material.BOOK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6--->");
                List<String> lore = new ArrayList<>();
        lore.add("§7Нажмите, чтобы перейти на следующую страницу");
                lore.add("§8"+ id);
                        lore.add("§8Предмет в меню");
                                meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack BlockDataBaseLastPage(int id) {
        ItemStack item = new ItemStack(Material.BOOK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6<---");
        List<String> lore = new ArrayList<>();
        lore.add("§7Нажмите, чтобы перейти на следующую страницу");
        lore.add("§8"+ id);
        lore.add("§8Предмет в меню");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack OpenAllItems() {
        ItemStack item = new ItemStack(Material.NETHER_STAR, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§5Информация о всех предметах данного дополнения");
                List<String> lore = new ArrayList<>();
        lore.add("§7§lНажмите, чтобы открыть информацию о всех предметах");
                meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }


    public static ItemStack SpellBook(int id) {
        ItemStack item = new ItemStack(Material.BOOK, 1);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        switch (id) {
            case 1:
                meta.setDisplayName("§2Слабый Прыжок");
                        lore.add("§7Толкает вас в направлении взгляда");
                                lore.add("§7Не имеет перезарядки");
                                        lore.add("§8" + id);
                break;
            case 2:
                meta.setDisplayName("§9Прыжок");
                        lore.add("§7Толкает вас в направлении взгляда");
                                lore.add("§7Не имеет перезарядки");
                                        lore.add("§8" + id);
                break;
            case  3:
                meta.setDisplayName("§fЛечение");
                lore.add("§7Излечивает вас на 500 единиц здоровья");
                  lore.add("§7Перезарядка 60 секунд");
                  lore.add("§8" + id);
                break;
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        item = GUIItem(item);
        return item;
    }

    public static ItemStack settings() {
        ItemStack item = new ItemStack(Material.ENDER_PEARL, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§5Настройки");
                List<String> lore = new ArrayList<>();
        lore.add("");
                meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack Donat() {
        ItemStack item = new ItemStack(Material.DIAMOND, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§5Магазин");
        List<String> lore = new ArrayList<>();
        lore.add("");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack RC() {
        ItemStack item = new ItemStack(Material.ENDER_CHEST, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§5Хранилище для рецептов");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§7Позволяет приобрести сундук");
        lore.add("§7Для хранения рецептов");
        lore.add("");
        lore.add("§2Стоимость услуги: 150");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack additionalXP() {
        ItemStack item = new ItemStack(Material.ENDER_CHEST, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§5Допольнительный опыт");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§7Позволяет приобрести глобальный 10% буст опыта");
        lore.add("");
        lore.add("§2Стоимость услуги: 300");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack additionalDrop() {
        ItemStack item = new ItemStack(Material.ENDER_CHEST, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§5Допольнительный дроп");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§7Позволяет приобрести глобальный 10% буст выпадения предметов");
        lore.add("");
        lore.add("§2Стоимость услуги: 300");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack caseOFEmblems() {
        ItemStack item = new ItemStack(Material.SHIELD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§5Кейс эмблем");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§7Позволяет приобрести сундук");
        lore.add("§7В котором можно получить случайную эмблему");
        lore.add("");
        lore.add("§2Стоимость услуги: 100");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack settingsblood(rpgplayer p) {
        ItemStack item = new ItemStack(Material.REDSTONE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Эффекты крови");
                List<String> lore = new ArrayList<>();
        if (p.getSettings().get("blood")) {
            lore.add("§7У вас: §2Включено!");
        } else {
            lore.add("§7У вас: §4Выключено!");
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack settingstrade(rpgplayer p) {
        ItemStack item = new ItemStack(Material.REDSTONE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Обмен");
        List<String> lore = new ArrayList<>();
        if (p.getSettings().get("trade")) {
            lore.add("§7У вас: §2Включено!");
        } else {
            lore.add("§7У вас: §4Выключено!");
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack settingsAutoRC(rpgplayer p) {
        ItemStack item = new ItemStack(Material.ENDER_CHEST, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Автоматическое поднятие рецептов");
        List<String> lore = new ArrayList<>();
        if (p.getSettings().get("autoRC")) {
            lore.add("§7У вас: §2Включено!");
        } else {
            lore.add("§7У вас: §4Выключено!");
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack openingRC() {
        ItemStack item = new ItemStack(Material.ENDER_CHEST, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Хранилище для рецептов");
        List<String> lore = new ArrayList<>();
        lore.add("§7Вы можете посмотреть свои рецепты");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack permessionOne() {
        ItemStack item = new ItemStack(Material.ENDER_CHEST, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§2VIP");
        List<String> lore = new ArrayList<>();
        lore.add("§7Вы можете приобрести данную группу");
        lore.add("");
        lore.add("§2Цена: 200");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack permessionTwo() {
        ItemStack item = new ItemStack(Material.ENDER_CHEST, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§bPREMIUM");
        List<String> lore = new ArrayList<>();
        lore.add("§7Вы можете приобрести данную группу");
        lore.add("");
        lore.add("§2Цена: 450");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack permessionThree() {
        ItemStack item = new ItemStack(Material.ENDER_CHEST, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6HOLY");
        List<String> lore = new ArrayList<>();
        lore.add("§7Вы можете приобрести данную группу");
        lore.add("");
        lore.add("§2Цена: 750");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack permessionFour() {
        ItemStack item = new ItemStack(Material.ENDER_CHEST, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§1RENOWN");
        List<String> lore = new ArrayList<>();
        lore.add("§7Вы можете приобрести данную группу");
        lore.add("");
        lore.add("§2Цена: 1500");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack permessionFive() {
        ItemStack item = new ItemStack(Material.ENDER_CHEST, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§dPARADISE");
        List<String> lore = new ArrayList<>();
        lore.add("§7Вы можете приобрести данную группу");
        lore.add("");
        lore.add("§2Цена: 2500");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack permessionSix() {
        ItemStack item = new ItemStack(Material.ENDER_CHEST, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§5HEAVENLY");
        List<String> lore = new ArrayList<>();
        lore.add("§7Вы можете приобрести данную группу");
        lore.add("");
        lore.add("§2Цена: 3500");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack permessionSeven() {
        ItemStack item = new ItemStack(Material.ENDER_CHEST, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§l§6LEGENDARY");
        List<String> lore = new ArrayList<>();
        lore.add("§7Вы можете приобрести данную группу");
        lore.add("");
        lore.add("§2Цена: 5000");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack settingdrop(rpgplayer p) {
        ItemStack item = new ItemStack(Material.PAPER, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Выпадение предметов");
        List<String> lore = new ArrayList<>();
        lore.add("§7§oПравый клик - открывает фильтр предметов");
        lore.add("§7§oЛевый клик - открывает фильтр сообщений о предметах");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack filterwhite(rpgplayer p) {
        ItemStack item = new ItemStack(Material.REDSTONE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§7Посредственное качество");
        List<String> lore = new ArrayList<>();
        if (p.getSettings().get("dropWhite")) {
            lore.add("§7У вас: §2Включено!");
        } else {
            lore.add("§7У вас: §4Выключено!");
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack filterGreen(rpgplayer p) {
        ItemStack item = new ItemStack(Material.REDSTONE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§2Качественное качество");
        List<String> lore = new ArrayList<>();
        if (p.getSettings().get("dropGreen")) {
            lore.add("§7У вас: §2Включено!");
        } else {
            lore.add("§7У вас: §4Выключено!");
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack filterBlue(rpgplayer p) {
        ItemStack item = new ItemStack(Material.REDSTONE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§9Отменное качество");
        List<String> lore = new ArrayList<>();
        if (p.getSettings().get("dropBlue")) {
            lore.add("§7У вас: §2Включено!");
        } else {
            lore.add("§7У вас: §4Выключено!");
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack filterPurple(rpgplayer p) {
        ItemStack item = new ItemStack(Material.REDSTONE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§5Древнее качество");
        List<String> lore = new ArrayList<>();
        if (p.getSettings().get("dropPurple")) {
            lore.add("§7У вас: §2Включено!");
        } else {
            lore.add("§7У вас: §4Выключено!");
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack whiteitem(rpgplayer p) {
        ItemStack item = new ItemStack(Material.REDSTONE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§7Посредственное качество");
        List<String> lore = new ArrayList<>();
        if (p.getSettings().get("liftingWhite")) {
            lore.add("§7У вас: §2Включено!");
        } else {
            lore.add("§7У вас: §4Выключено!");
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack GreenItem(rpgplayer p) {
        ItemStack item = new ItemStack(Material.REDSTONE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§2Качественное качество");
        List<String> lore = new ArrayList<>();
        if (p.getSettings().get("liftingGreen")) {
            lore.add("§7У вас: §2Включено!");
        } else {
            lore.add("§7У вас: §4Выключено!");
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack chance(int enchantmentLevel, int[] usedStones) {
        ItemStack item = new ItemStack(Material.BOOK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Шанс");
        List<String> lore = new ArrayList<>();
        lore.add("§7Текущий шанс: " + chance.calculateSuccessChance(enchantmentLevel, usedStones));
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }


    public static ItemStack BlueItem(rpgplayer p) {
        ItemStack item = new ItemStack(Material.REDSTONE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§9Отменное качество");
        List<String> lore = new ArrayList<>();
        if (p.getSettings().get("liftingBlue")) {
            lore.add("§7У вас: §2Включено!");
        } else {
            lore.add("§7У вас: §4Выключено!");
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack PurpleItem(rpgplayer p) {
        ItemStack item = new ItemStack(Material.REDSTONE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§5Древнее качество");
        List<String> lore = new ArrayList<>();
        if (p.getSettings().get("liftingPurple")) {
            lore.add("§7У вас: §2Включено!");
        } else {
            lore.add("§7У вас: §4Выключено!");
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }


    public static ItemStack openEmblems(rpgplayer p) {
        ItemStack item = new ItemStack(Material.ENDER_CHEST, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Анимация открытия сундука");
        List<String> lore = new ArrayList<>();
        if (p.getSettings().get("openCases")) {
            lore.add("§7У вас: §2Включено!");
        } else {
            lore.add("§7У вас: §4Выключено!");
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }


    public static ItemStack BlockDataBaseLeave() {
        ItemStack item = new ItemStack(Material.BOOK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Выйти");
                List<String> lore = new ArrayList<>();
        lore.add("");
                meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack completeQuest(String id, rpgplayer p) {
        Quest quest = Quest.getQuest(id);
        ItemStack item = new ItemStack(Material.BOOK);
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
        meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
        meta.setDisplayName("§e" + quest.getName() + " §c#" + id);
        List<String> lore2 = Arrays.asList(quest.getLore().split(";"));
        List<String> lore = new ArrayList<>();
        for (int i = 0; i < lore2.size(); i++) {
            lore.add("");
            lore.set(i, "§f" + ((String)lore2.get(i)).replaceAll("player", "§a" + p.getName() + "§f"));
        }
        lore.remove("");
        lore.add("");
        lore.add("§aЗадание выполнено");
                meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack lockedQuest(String id, rpgplayer p) {
        Quest quest = Quest.getQuest(id);
        ItemStack item = new ItemStack(Material.BOOK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§e" + quest.getName() + " §c#" + id);
        List<String> lore = new ArrayList<>();
        if (p.getPLevel() > quest.rLvl()) {
            lore.add("§4Необходимо выполнить другие задания");
        } else {
            lore.add("§4Необходимый уровень: §c" + quest.rLvl());
        }
        lore.add("§4Задание недоступно");
                meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack unlockedQuest(String id, rpgplayer p) {
        Quest quest = Quest.getQuest(id);
        ItemStack item = new ItemStack(Material.BOOK_AND_QUILL);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§e" + quest.getName() + " §c#" + id);
        List<String> lore = new ArrayList<>();
        List<String> lore2 = Arrays.asList(quest.getLore().split(";"));
        for (int i = 0; i < lore2.size(); i++)
            lore.add("§f" + ((String)lore2.get(i)).replaceAll("player", "§a" + p.getName() + "§f"));
                    lore.add("");
        HashMap<Integer, Integer> mobs = quest.haveNeedMobs() ? quest.getMobs() : new HashMap<>();
        if (!mobs.isEmpty()) {
            lore.add("§7Необходимо убить:");
            for (Iterator<Integer> iterator = mobs.keySet().iterator(); iterator.hasNext(); ) {
                int j = ((Integer)iterator.next()).intValue();
                lore.add("§7-" + String.valueOf(MobSystem.getName(j)) + ": 0/" + mobs.get(Integer.valueOf(j)));
            }
            lore.add("");
        }
        HashMap<Integer, Integer> items = quest.getItems();
        if (!items.isEmpty()) {
            lore.add("§7Необходимо собрать: ");
            for (Iterator<Integer> iterator = items.keySet().iterator(); iterator.hasNext(); ) {
                int j = ((Integer)iterator.next()).intValue();
                int amount = WorldUtils.checkItems(p, j , 0);
                lore.add("§7-" + (new RPGItem()).getRPGItem(j, 0, 1,  0, 0, 0,0,0,null).getItemMeta().getDisplayName() + " x" + items.get(Integer.valueOf(j)));
            }
            lore.add("");
        }
        if (quest.getTaker() != null)
            lore.add("§7Необходимо сдать: §f" + quest.getTaker());
                    lore.add("");
        HashMap<Integer, Integer> reward = quest.getReward();
        if (!reward.isEmpty())
            for (Iterator<Integer> iterator = reward.keySet().iterator(); iterator.hasNext(); ) {
                int j = ((Integer)iterator.next()).intValue();
                lore.add("§7-" + (new RPGItem()).getRPGItem(j, 0, 1,  0, 0, 0,0,0,null).getItemMeta().getDisplayName() + " x" + reward.get(Integer.valueOf(j)));
            }
        if (quest.getXp() > 0)
            lore.add("§7- §6" + quest.getXp() + " опыта" );
        lore.add("");
        lore.add("§eНажмите, чтобы приступить к выполнению");
                meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack Dailies(rpgplayer p) {
        ItemStack item = new ItemStack(Material.BOOK_AND_QUILL);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§eЕжедневное задание");
        List<String> lore = new ArrayList<>();
        HashMap<Integer, Integer> mobs = new HashMap<>();
        Random  r = new Random();
        int randomquest = r.nextInt(2) + 1;
        if(randomquest == 1) {
            switch (p.getLevel()) {
                case 1:
                    mobs.put(1, 5);
                    break;
                case 2:
                    mobs.put(2, 5);
                    break;

            }
            if (!mobs.isEmpty()) {
                lore.add("§7Необходимо убить:");
                for (Iterator<Integer> iterator = mobs.keySet().iterator(); iterator.hasNext(); ) {
                    int j = ((Integer) iterator.next()).intValue();
                    lore.add("§7-" + String.valueOf(MobSystem.getName(j)) + ": 0/" + mobs.get(Integer.valueOf(j)));
                }
                lore.add("");

            }

        }
        if(randomquest == 2) {
            HashMap<Integer, Integer> items = new HashMap<>();
            switch (p.getLevel()){
                case 1:
                    items.put(1, 7);
                    break;
                case 2:
                    items.put(2, 7);
                    break;
            }
            if (!items.isEmpty()) {
                lore.add("§7Необходимо собрать: ");
                for (Iterator<Integer> iterator = items.keySet().iterator(); iterator.hasNext(); ) {
                    int j = ((Integer) iterator.next()).intValue();
                    int amount = WorldUtils.checkItems(p, j, 0);
                    lore.add("§7-" + (new RPGItem()).getRPGItem(j, 0, 1, 0, 0, 0, 0, 0,null).getItemMeta().getDisplayName() + " x" + items.get(Integer.valueOf(j)));
                }
                lore.add("");
            }
        }
        lore.add("§eНажмите, чтобы приступить к выполнению");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack inprocessDailies(rpgplayer p, boolean a, boolean b) {
        ItemStack item = new ItemStack(Material.BOOK_AND_QUILL);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§eЕжедневное задание");
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
        meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
        List<String> lore = new ArrayList<>();
        boolean maypass = a;

        HashMap<Integer, Integer> mobs = new HashMap<>();
        switch (p.getLevel()){
            case 1:
                mobs.put(1, 5);
                break;
            case 2:
                mobs.put(2,5);
                break;
        }
        if (!mobs.isEmpty()) {
            lore.add("§7Необходимо убить: " );
            for (Iterator<Integer> iterator = mobs.keySet().iterator(); iterator.hasNext(); ) {
                int j = ((Integer)iterator.next()).intValue();
                lore.add("§7-" + String.valueOf(MobSystem.getName(j)) + ": " + p.getMob((new StringBuilder(String.valueOf(j))).toString()) + "/" + mobs.get(Integer.valueOf(j)));
                if (p.getMob((new StringBuilder(String.valueOf(j))).toString()) < ((Integer)mobs.get(Integer.valueOf(j))).intValue())
                    maypass = false;
            }
            lore.add("");
        }
        HashMap<Integer, Integer> items = new HashMap<>();
        if (!items.isEmpty()) {
            lore.add("§7Необходимо собрать:");
            for (Iterator<Integer> iterator = items.keySet().iterator(); iterator.hasNext(); ) {
                int j = ((Integer)iterator.next()).intValue();
                int amount = WorldUtils.checkItems(p, j, 0);
                lore.add("§7-" + (new RPGItem()).getRPGItem(j, 0, 1,  0, 0, 0,0,0,null).getItemMeta().getDisplayName() + ": " + amount + "/" + items.get(Integer.valueOf(j)));
                if (amount < ((Integer)items.get(Integer.valueOf(j))).intValue())
                    maypass = false;
            }
            lore.add("");
        }
        if (!b)
            lore.add("");
        if (maypass) {
            lore.add("§aНажмите, чтобы выполнить задание");
        } else if (!b) {
            lore.add("§eВы выполняете это задание");
        }
        if (b) {
            lore.add("");
            lore.add("");
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack book(String id, rpgplayer p) {
        Quest quest = Quest.getQuest(id);
        List<String> lore2 = Arrays.asList(quest.getLore().split(";"));
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) book.getItemMeta();
        List<String> lore = new ArrayList<>();
        for (int i = 0; i < lore2.size(); i++)
            lore.add(i, "§f" + ((String)lore2.get(i)).replaceAll("player", "§a" + p.getName() + "§f"));
        bookMeta.setLore(lore);

        return book;
    }


    public static ItemStack inprocessQuest(String id, rpgplayer p, boolean a, boolean b) {
        Quest quest = Quest.getQuest(id);
        ItemStack item = new ItemStack(Material.BOOK_AND_QUILL);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§e" + quest.getName() + " §e#" + id);
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
        meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
        List<String> lore2 = Arrays.asList(quest.getLore().split(";"));
        List<String> lore = new ArrayList<>();
        boolean maypass = a;
        for (int i = 0; i < lore2.size(); i++)
            lore.add(i, "§f" + ((String)lore2.get(i)).replaceAll("player", "§a" + p.getName() + "§f"));
                    lore.add("");
        HashMap<Integer, Integer> mobs = quest.getMobs();
        if (quest.haveNeedMobs() &&
                !mobs.isEmpty()) {
            lore.add("§7Необходимо убить: " );
            for (Iterator<Integer> iterator = mobs.keySet().iterator(); iterator.hasNext(); ) {
                int j = ((Integer)iterator.next()).intValue();
                lore.add("§7-" + String.valueOf(MobSystem.getName(j)) + ": " + p.getMob((new StringBuilder(String.valueOf(j))).toString()) + "/" + mobs.get(Integer.valueOf(j)));
                if (p.getMob((new StringBuilder(String.valueOf(j))).toString()) < ((Integer)mobs.get(Integer.valueOf(j))).intValue())
                    maypass = false;
            }
            lore.add("");
        }
        HashMap<Integer, Integer> items = quest.getItems();
        if (!items.isEmpty()) {
            lore.add("§7Необходимо собрать:");
            for (Iterator<Integer> iterator = items.keySet().iterator(); iterator.hasNext(); ) {
                int j = ((Integer)iterator.next()).intValue();
                int amount = WorldUtils.checkItems(p, j, 0);
                lore.add("§7-" + (new RPGItem()).getRPGItem(j, 0, 1,  0, 0, 0,0,0,null).getItemMeta().getDisplayName() + ": " + amount + "/" + items.get(Integer.valueOf(j)));
                if (amount < ((Integer)items.get(Integer.valueOf(j))).intValue())
                    maypass = false;
            }
            lore.add("");
        }
        if (quest.getTaker() != null){
            lore.add("§7Необходимо сдать: §f" + quest.getTaker());
            lore.add("");
        }
        HashMap<Integer, Integer> reward = quest.getReward();
        if (!reward.isEmpty())
            for (Iterator<Integer> iterator = reward.keySet().iterator(); iterator.hasNext(); ) {
                int j = ((Integer)iterator.next()).intValue();
                lore.add("§7Награда: " + (new RPGItem()).getRPGItem(j, 0, 1,  0, 0, 0,0,0,null).getItemMeta().getDisplayName() + " x" + reward.get(Integer.valueOf(j)));
            }
        if (quest.getXp() > 0)
            lore.add("§7- §6"+ quest.getXp() + " опыта" );
        if (!b)
            lore.add("");
        if (maypass) {
            lore.add("§aНажмите, чтобы выполнить задание");
        } else if (!b) {
            lore.add("§eВы выполняете это задание");
        }
        if (b) {
            lore.add("");
            lore.add("");
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack canAccept(String id) {
        Quest quest = Quest.getQuest(id);
        ItemStack item = new ItemStack(Material.BOOK_AND_QUILL);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§e" + quest.getName() + " §c#" + id);
        List<String> lore = new ArrayList<>();
        lore.add("§2Вы можете взять это задание");
                lore.add("§7Можно взять: §f" + quest.getGiver());
                        lore.add("§7Координаты: §f" + quest.getGiverXYZ());
                                meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack cantAccept(String id) {
        Quest quest = Quest.getQuest(id);
        ItemStack item = new ItemStack(Material.BOOK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§e" + quest.getName() + " §c#" + id);
        List<String> lore = new ArrayList<>();
        lore.add("§cВы не можете взять это задание");
                meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack corner() {
        ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§r");
                item.setItemMeta(meta);
        return item;
    }

    public static ItemStack tradecomplete() {
        ItemStack item = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§aПодтвердить покупку");
                item.setItemMeta(meta);
        return item;
    }

    public static ItemStack craftcomplete() {
        ItemStack item = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§aПодтвердить создание");
                item.setItemMeta(meta);
        return item;
    }

    public static ItemStack  destroyercomplete() {
        ItemStack item = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§aПодтвердить разрушение");
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack sellcomplete() {
        ItemStack item = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§aПодтвердить продажу");
                item.setItemMeta(meta);
        return item;
    }

    public static ItemStack sellAuc() {
        ItemStack item = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§aПродать предмет");
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack confirm() {
        ItemStack item = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§aПодтвердить");
        item.setItemMeta(meta);
        return item;
    }


    public static ItemStack entityCreator(String id) {
        ItemStack item = new ItemStack(Material.BLAZE_ROD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§bСоздатель существ");
                List<String> lore = new ArrayList<>();
        lore.add("§8" + id);
                lore.add("§8Служебный предмет");
                        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack quest() {
        ItemStack item = new ItemStack(Material.BOOK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§5Поручения");
        List<String> lore = new ArrayList<>();
        lore.add("§7§lПозволяет посмотреть информацию о доступных квестах");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }


    public static ItemStack PlayerNPC(rpgplayer p) {
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        ItemMeta meta = item.getItemMeta();
        SkullMeta meta1 = (SkullMeta) item.getItemMeta();
        meta1.setOwner(p.toPlayer().getName());
        meta1.setDisplayName("§e" + p.toPlayer().getName());
        List<String> lore = new ArrayList<>();
        lore.add("§7§lПозволяет посмотреть информацию о доступных квестах");
        meta.setLore(lore);
        item.setItemMeta(meta1);
        return item;
    }

    public static ItemStack markcomplete(int price) {
        ItemStack item = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("");
                List<String> lore = new ArrayList<>();
        int emerald1 = GenerateInventory.getEmerald(price, 1);
        int emerald2 = GenerateInventory.getEmerald(price, 2);
        int emerald3 = GenerateInventory.getEmerald(price, 3);
        int emerald4 = GenerateInventory.getEmerald(price, 4);
        int emerald5 = GenerateInventory.getEmerald(price, 5);
        lore.add("");
        if (emerald1 > 0)
            lore.add(""+ emerald1);
        if (emerald2 > 0)
            lore.add(""+ emerald2);
        if (emerald3 > 0)
            lore.add(""+ emerald3);
        if (emerald4 > 0)
            lore.add(""+ emerald4);
        if (emerald5 > 0)
            lore.add(""+ emerald5);
                    meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}
