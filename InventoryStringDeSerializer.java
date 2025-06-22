package azerot.azerot;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryStringDeSerializer {

    public static String InventoryToString (Inventory inv)
    {
        StringBuilder serialization = new StringBuilder(inv.getSize() + "#");
        for (int i = 0; i < inv.getSize(); i++)
        {
            RPGItemContainer is = RPGItemContainer.getRPGItemContainer(i);
            ItemStack stack = inv.getItem(i);

                String serializedItemStack = new String();

                int id = ItemStat.getID(inv.getItem(i));
                if(stack == null){
                    serializedItemStack += "0";
                    serializedItemStack += "-" + "0";
                }else{
                    serializedItemStack += "" + id;
                    int amount = stack.getAmount();
                    serializedItemStack += "-" + amount;
                }

                int enchant = ItemStat.getEnchant(inv.getItem(i));
                int mark = ItemStat.getMark(inv.getItem(i));
                int markpower = ItemStat.getMarkpower(inv.getItem(i));
                int strength = ItemStat.getScaleAtStrength(inv.getItem(i));
                int agility = ItemStat.getScaleAtAgility(inv.getItem(i));
                int intelligence = ItemStat.getScaleAtIntelligence(inv.getItem(i));

                serializedItemStack += "-" + enchant;
                serializedItemStack += "-" + mark;
                serializedItemStack += "-" + markpower;
                serializedItemStack += "-" + strength;
                serializedItemStack += "-" + agility;
                serializedItemStack += "-" + intelligence;


                serialization.append(i).append(":").append(serializedItemStack).append(";");

        }
        return serialization.toString();
    }

    public static String ECToString (Inventory inv)
    {
        StringBuilder serialization = new StringBuilder();
        for (int i = 0; i < inv.getSize(); i++)
        {
            RPGItemContainer is = RPGItemContainer.getRPGItemContainer(i);
            ItemStack stack = inv.getItem(i);

            String serializedItemStack = new String();

            int id = ItemStat.getID(inv.getItem(i));
            if(stack == null){
                serializedItemStack += "" + "0";
                serializedItemStack += "-" + "0";
            }else{
                serializedItemStack += "" + id;
                int amount = stack.getAmount();
                serializedItemStack += "-" + amount;
            }

            int enchant = ItemStat.getEnchant(inv.getItem(i));
            int mark = ItemStat.getMark(inv.getItem(i));
            int markpower = ItemStat.getMarkpower(inv.getItem(i));
            int strength = ItemStat.getScaleAtStrength(inv.getItem(i));
            int agility = ItemStat.getScaleAtAgility(inv.getItem(i));
            int intelligence = ItemStat.getScaleAtIntelligence(inv.getItem(i));

            serializedItemStack += "-" + enchant;
            serializedItemStack += "-" + mark;
            serializedItemStack += "-" + markpower;
            serializedItemStack += "-" + strength;
            serializedItemStack += "-" + agility;
            serializedItemStack += "-" + intelligence;


            serialization.append(i).append(":").append(serializedItemStack).append(";");

        }
        return serialization.toString();
    }


    public static String armorToSting(ItemStack[] inv1, Player p){
        Inventory inv = Bukkit.createInventory(p, 9, "");
            inv.setItem(0, p.getInventory().getHelmet());
            inv.setItem(1, p.getInventory().getChestplate());
            inv.setItem(2, p.getInventory().getLeggings());
            inv.setItem(3, p.getInventory().getBoots());
            inv.setItem(4, p.getInventory().getItemInOffHand());

        String serialization = inv.getSize() + ";";
        for (int i = 0; i < inv.getSize(); i++)
        {
            ItemStack stack = inv.getItem(i);

            String serializedItemStack = new String();

            int id = ItemStat.getID(inv.getItem(i));
            if(stack == null){
                serializedItemStack += "" + "0";
                serializedItemStack += "-" + "0";
            }else{
                serializedItemStack += "" + id;
                int amount = stack.getAmount();
                serializedItemStack += "-" + amount;
            }

            int enchant = ItemStat.getEnchant(inv.getItem(i));
            int mark = ItemStat.getMark(inv.getItem(i));
            int markpower = ItemStat.getMarkpower(inv.getItem(i));

            serializedItemStack += "-" + enchant;
            serializedItemStack += "-" + mark;
            serializedItemStack += "-" + markpower;

            serialization += i + ":" + serializedItemStack + ";";

        }
        return serialization;
    }
    public  static Inventory ArmorToInventory (String invString,  Player p){
        String[] serializedBlocks = invString.split(";");
        String invInfo = serializedBlocks[0];
        rpgplayer rpgplaye1 = rpgplayer.getRPGPlayer(p);
        Inventory deserializedInventory = Bukkit.getServer().createInventory(null,  9);

        for (int i = 1; i < serializedBlocks.length; i++) {
            String item = serializedBlocks[i];
            String item1 = item.replace("-", ";");
            String[] item2 = item1.split(":");
            String itemAttribute =  item2[1];
            String[] itemAttribute1 = itemAttribute.split(";");


            ItemStack is = null;
            Boolean createdItemStack = false;
            int id = Integer.parseInt(itemAttribute1[0]);
            int amount = Integer.parseInt(itemAttribute1[1]);
            int enchant = Integer.parseInt(itemAttribute1[2]);
            int mark = Integer.parseInt(itemAttribute1[3]);
            int markpower = Integer.parseInt(itemAttribute1[4]);
            if(id != 0)
                deserializedInventory.setItem(Integer.parseInt(item2[0]), new RPGItem().getRPGItem(id,enchant,1,mark,markpower,0,0,0,rpgplaye1));


        }


        return deserializedInventory;
    }


    public static void StringToInventory (String invString, Player p)
    {
        String[] serializedBlocks = invString.split(";");

        Inventory deserializedInventory = Bukkit.getServer().createInventory(null, 36);
        rpgplayer rpgplaye1 = rpgplayer.getRPGPlayer(p);

        for (int i = 1; i < serializedBlocks.length; i++) {
               String item = serializedBlocks[i];
               String item1 = item.replace("-", ";");
               String[] item2 = item1.split(":");
               String itemAttribute =  item2[1];
               String[] itemAttribute1 = itemAttribute.split(";");


            ItemStack is = null;
            Boolean createdItemStack = false;
            int id = Integer.parseInt(itemAttribute1[0]);
            int amount = Integer.parseInt(itemAttribute1[1]);
            int enchant = Integer.parseInt(itemAttribute1[2]);
            int mark = Integer.parseInt(itemAttribute1[3]);
            int markpower = Integer.parseInt(itemAttribute1[4]);
            int strength = Integer.parseInt(itemAttribute1[5]);
            int agility = Integer.parseInt(itemAttribute1[6]);
            int intelligence = Integer.parseInt(itemAttribute1[7]);


            if(id >0){
                p.getInventory().setItem(Integer.parseInt(item2[0]), (new RPGItem()).getRPGItem(id, enchant, amount, mark, markpower, strength, agility, intelligence, rpgplaye1));
            }
            }


    }
    public static Inventory StringToArts (String invString, Player p)
    {
        String[] serializedBlocks = invString.split(";");
        String invInfo = serializedBlocks[0];
        Inventory deserializedInventory = Bukkit.getServer().createInventory(null, 36);
        rpgplayer rpgplaye1 = rpgplayer.getRPGPlayer(p);

        for (int i = 1; i < serializedBlocks.length; i++) {
            String item = serializedBlocks[i];
            String item1 = item.replace("-", ";");
            String[] item2 = item1.split(":");
            String itemAttribute =  item2[1];
            String[] itemAttribute1 = itemAttribute.split(";");


            ItemStack is = null;
            Boolean createdItemStack = false;
            int id = Integer.parseInt(itemAttribute1[0]);
            int amount = Integer.parseInt(itemAttribute1[1]);
            int enchant = Integer.parseInt(itemAttribute1[2]);
            int mark = Integer.parseInt(itemAttribute1[3]);
            int markpower = Integer.parseInt(itemAttribute1[4]);

            if(id > 0){
                rpgplaye1.getArts().setItem(Integer.parseInt(item2[0]), (new RPGItem()).getRPGItem(id, enchant, amount, mark, markpower, 0, 0, 0, rpgplaye1));
            }
        }


        return deserializedInventory;
    }
}

