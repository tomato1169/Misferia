package azerot.azerot;

import io.netty.util.internal.ThreadLocalRandom;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class ItemStat {
    private static double highenchant01 = 0.075D;

    private static double highenchant005 = 0.037D;

    static File items = new File(azerot.getInstance().getDataFolder() + File.separator + "items.yml");

    static FileConfiguration itemid = (FileConfiguration)YamlConfiguration.loadConfiguration(items);

    static HashMap<Integer, Integer> rollItem = new HashMap<>();

    public static int getID(ItemStack item) {
        if (RPGItem.getInfo(item, "itemInfo", 0) == null)
            return 0;
        try {
            return Integer.valueOf(RPGItem.getInfo(item, "itemInfo", 0)).intValue();
        } catch (Exception e) {
            return 0;
        }
    }

    public static int getGUIID(ItemStack item) {
        try {
            return Integer.valueOf(Objects.requireNonNull(RPGItem.getInfo(item, "itemInfo", 0)).replace("gui", "")).intValue();
        } catch (Exception e) {
            return 0;
        }
    }
    public static int getGUIIDFromLore(ItemStack item) {
        try {
            return Integer.parseInt(((String)item.getItemMeta().getLore().get(item.getItemMeta().getLore().size() - 2)).replace("§8", ""));
        } catch (Exception e) {
            return 0;
        }
    }

    public static int getType(ItemStack item) {
        try {
            return Integer.valueOf(Objects.requireNonNull(RPGItem.getInfo(item, "itemInfo", 1)));
        } catch (Exception e) {
            return 0;
        }
    }

    public static int getMark(ItemStack item) {
        try {
            return Integer.parseInt(Objects.requireNonNull(RPGItem.getInfo(item, "itemInfo", 2)));
        } catch (Exception e) {
            return 0;
        }
    }
    public static int getMarkpower(ItemStack item) {
        try {
            return Integer.parseInt(Objects.requireNonNull(RPGItem.getInfo(item, "itemInfo", 3)));
        } catch (Exception e) {
            return 0;
        }
    }


    public static int getScaleAtStrength(ItemStack item) {
        try {
            return Integer.parseInt(Objects.requireNonNull(RPGItem.getInfo(item, "itemInfo", 5)));
        } catch (Exception e) {
            return 0;
        }
    }
    public static int getScaleAtAgility(ItemStack item) {
        try {
            return Integer.parseInt(Objects.requireNonNull(RPGItem.getInfo(item, "itemInfo", 6)));
        } catch (Exception e) {
            return 0;
        }
    }
    public static int getScaleAtIntelligence(ItemStack item) {
        try {
            return Integer.parseInt(Objects.requireNonNull(RPGItem.getInfo(item, "itemInfo", 7)));
        } catch (Exception e) {
            return 0;
        }
    }

    public static int getEnchant(ItemStack item) {
        try {
            return Integer.parseInt(Objects.requireNonNull(RPGItem.getInfo(item, "itemInfo", 4)));
        } catch (Exception e) {
            return 0;
        }
    }

    public static boolean hasMark(int id) {
        int hasMark = (itemid.getString(String.valueOf(id) + ".hasMark") != null) ? 1 : 0;
        if (hasMark == 1)
            return true;
        return false;
    }



    public static int getSpellID(int id) {
        String name = ".spellid";
        int stat = (itemid.getString(String.valueOf(id) + name) != null) ? itemid.getInt(String.valueOf(id) + name) : 0;
        return stat;
    }



    public static int getEnchantItemPrice(int id, int enchant) {
        RPGItemContainer RPGI = RPGItemContainer.getRPGItemContainer(id);
        String name = ".itemprice";
        int stat = RPGI.getItemPrice();
        stat += (int)Math.max(0.0D, stat * 0.5D * enchant);
        return stat;
    }
    public static int getLevel(int id) {
        String name = ".level";
        int stat = (itemid.getString(String.valueOf(id) + name) != null) ? itemid.getInt(String.valueOf(id) + name) : 0;
        return stat;
    }



    public static int getSoulbound(int id) {
        String name = ".soulbound";
        int stat = (itemid.getString(String.valueOf(id) + name) != null) ? itemid.getInt(String.valueOf(id) + name) : 0;
        return stat;
    }

    public static int getRecipe(int id) {
        String name = ".recipe";
        int stat = (itemid.getString(String.valueOf(id) + name) != null) ? itemid.getInt(String.valueOf(id) + name) : 0;
        return stat;
    }

    public static String[] getTeleport(int id) {
        String[] teleport = (itemid.getString(String.valueOf(id) + ".teleport") != null) ? itemid.getString(String.valueOf(id) + ".teleport").trim().split(" ") : null;
        return teleport;
    }

    public static String getTeleport2(int id) {
        String teleport = (itemid.getString(String.valueOf(id) + ".teleport") != null) ? itemid.getString(String.valueOf(id) + ".teleport") : null;
        return teleport;
    }



    public static boolean canuseitem(rpgplayer p, ItemStack item) {
        int id = getID(item);
        if (item == null || item.getType().equals(Material.AIR) || id == 0)
            return true;
        RPGItemContainer RPGI = RPGItemContainer.getRPGItemContainer(id);
        if (p.toPlayer().getGameMode() == GameMode.CREATIVE)
            return true;
        if (p.getPLevel() < RPGI.getLevel())
            return false;


        if (p.getMiningLevel() < RPGI.getMiningLevel())
            return false;
        return true;
    }
}
