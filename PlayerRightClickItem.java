package azerot.azerot;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.IntStream;

import azerot.azerot.Quest.questScript;
import azerot.azerot.mobs.MobSystem;
import azerot.azerot.wheel.firstWheel;
import com.google.gson.Gson;
import io.netty.util.internal.ThreadLocalRandom;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.*;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.*;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerRightClickItem implements Listener {

    static Gson gson = new Gson();
    @EventHandler
    public void blockInteract(PlayerInteractEvent e) {
        if (!e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
            return;

        if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
            if (e.getClickedBlock().getType().equals(Material.CHEST))
                e.setCancelled(true);
            if (e.getClickedBlock().getType().equals(Material.TRAPPED_CHEST))
                e.setCancelled(true);
            if (e.getClickedBlock().getType().equals(Material.DROPPER))
                e.setCancelled(true);
            if (e.getClickedBlock().getType().equals(Material.DISPENSER))
                e.setCancelled(true);
            if (e.getClickedBlock().getType().equals(Material.HOPPER))
                e.setCancelled(true);
            if (e.getClickedBlock().getType().equals(Material.FURNACE))
                e.setCancelled(true);
            if (e.getClickedBlock().getType().equals(Material.BURNING_FURNACE))
                e.setCancelled(true);
            if (e.getClickedBlock().getType().equals(Material.ANVIL))
                e.setCancelled(true);
            if (e.getClickedBlock().getType().equals(Material.WATER))
                e.setCancelled(true);
            if (e.getClickedBlock().getType().equals(Material.WORKBENCH))
                e.setCancelled(true);
        }
    }

    @EventHandler
    public void blockInteractFlower(PlayerInteractEvent e) throws SQLException {
        Block block = e.getClickedBlock();
        if(block == null)
            return;


        Player p = e.getPlayer();
        rpgplayer pl = rpgplayer.getRPGPlayer(p);
        ItemStack hand = e.getPlayer().getInventory().getItemInMainHand();
        int id = ItemStat.getID(hand);
        long time = Bukkit.getWorld("world").getTime();
        if(pl.getQuests().get("8") != null && pl.getQuests().get("8") == 1){
            if(block.getType() == Material.YELLOW_FLOWER){
                if(time <= 12300){
                    if(id == 148){
                        if(pl.getFlowerGrowth().get(0) == null){
                            WorldUtils.removeItems(pl,148,1);
                            pl.getFlowerGrowth().put(0,1);
                        }else{
                            pl.getFlowerGrowth().put(0,pl.getFlowerGrowth().get(0) + 1);
                            WorldUtils.removeItems(pl,148,1);
                            if(pl.getFlowerGrowth().get(0) >= 5){
                                p.getInventory().addItem((new RPGItem()).getRPGItem(231, 0, 1,  0, 0,0, 0, 0,pl));
                                pl.getFlowerGrowth().put(0,0);
                                questScript.ScriptsQuest(p, "8", "0", false, 0);
                            }
                        }
                    }else {
                        p.sendMessage(WorldUtils.worldName() + " §4Похоже мне нужно как-то стимулировать цветение");
                    }
                }else{
                    p.sendMessage(WorldUtils.worldName() + " §4Кажется цветок еще не зацвел");
                }
            }
            if(block.getType() == Material.RED_ROSE){
                if(block.getData() == 1){
                    if(time >= 12300){
                        if(id == 150){
                            if(pl.getFlowerGrowth().get(1) == null){
                                pl.getFlowerGrowth().put(1,1);
                                WorldUtils.removeItems(pl,150,1);
                            }else{
                                pl.getFlowerGrowth().put(1,pl.getFlowerGrowth().get(1) + 1);
                                WorldUtils.removeItems(pl,150,1);
                                if(pl.getFlowerGrowth().get(1) >= 5){
                                    p.getInventory().addItem((new RPGItem()).getRPGItem(232, 0, 1,  0, 0,0, 0, 0, pl));
                                    pl.getFlowerGrowth().put(1,0);
                                    questScript.ScriptsQuest(p, "8", "0", false, 0);
                                }
                            }
                        }else {
                            p.sendMessage(WorldUtils.worldName() + " §4Похоже мне нужно как-то стимулировать цветение");
                        }
                    }else{
                        p.sendMessage(WorldUtils.worldName() + " §4Кажется цветок еще не зацвел");
                    }
                }
                if(block.getData() == 4){
                    if(id == 230){
                        if(pl.getFlowerGrowth().get(2) == null){
                            pl.getFlowerGrowth().put(2,1);
                            WorldUtils.removeItems(pl,230,1);
                        }else{
                            pl.getFlowerGrowth().put(2,pl.getFlowerGrowth().get(2) + 1);
                            WorldUtils.removeItems(pl,230,1);
                            if(pl.getFlowerGrowth().get(2) >= 5){
                                p.getInventory().addItem((new RPGItem()).getRPGItem(233, 0, 1,  0, 0,0, 0, 0, pl));
                                pl.getFlowerGrowth().put(2,0);
                                questScript.ScriptsQuest(p, "8", "0", false, 0);
                            }
                        }
                    }else{
                        p.sendMessage(WorldUtils.worldName() + " §4Похоже мне нужно как-то стимулировать цветение");
                    }
                }
                if(block.getData() == 3){
                    if(id == 179){
                        if(pl.getFlowerGrowth().get(3) == null){
                            pl.getFlowerGrowth().put(3,1);
                            WorldUtils.removeItems(pl,179,1);
                        }else{
                            pl.getFlowerGrowth().put(3,pl.getFlowerGrowth().get(3) + 1);
                            WorldUtils.removeItems(pl,179,1);
                            if(pl.getFlowerGrowth().get(3) >= 5){
                                p.getInventory().addItem((new RPGItem()).getRPGItem(234, 0, 1,  0, 0,0, 0, 0, pl));
                                pl.getFlowerGrowth().put(3,0);
                                questScript.ScriptsQuest(p, "8", "0", false, 0);
                            }
                        }
                    }else{
                        p.sendMessage(WorldUtils.worldName() + " §4Похоже мне нужно как-то стимулировать цветение");
                    }
                }
            }
        }
    }


    @EventHandler
    public void interact(PlayerInteractEvent e) {
        if (e.getPlayer().getGameMode() == GameMode.SPECTATOR)
            return;
        ItemStack hand = e.getPlayer().getInventory().getItemInMainHand();
        int handslot = e.getPlayer().getInventory().getHeldItemSlot();
        if (hand.getItemMeta() == null)
            return;
        rpgplayer p = rpgplayer.getRPGPlayer(e.getPlayer());
        int id = ItemStat.getID(hand);
        RPGItemContainer RPGI = RPGItemContainer.getRPGItemContainer(id);
        if (e.getPlayer().getTargetBlock(null, 5).getType() == Material.FIRE && e.getPlayer().getGameMode() != GameMode.CREATIVE)
            e.setCancelled(true);
        if ((e.getItem() != null && e.getItem().equals(e.getPlayer().getInventory().getItemInOffHand())) || e.getAction() == Action.PHYSICAL || e.getItem() == null || e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK)
            return;
        if (!e.getItem().hasItemMeta())
            return;
    if (hand.getItemMeta().getDisplayName().equals(""))
                                                   p.toPlayer().openInventory(p.getRC());
    if (!ItemStat.canuseitem(p, hand)) {
        e.getPlayer().sendMessage(WorldUtils.worldName() + " §cВы не можете использовать этот предмет!" );
                e.setCancelled(true);
        return;
    }
    if (RPGI.getRecipe() > 0){
        p.toPlayer().openInventory(azerot.getInventorys().get("InvReciped: " + RPGI.getRecipe()));
    }
        if (hand.getItemMeta().getDisplayName().contains("Лицензия на расширение хранилища"))
                                                             if (e.getClickedBlock() == null || !e.getClickedBlock().getType().equals(Material.ENDER_CHEST)) {
        if (id == 105 && p.getEcSize() == 1) {
            e.getPlayer().getInventory().getItem(e.getPlayer().getInventory().getHeldItemSlot()).setAmount(e.getPlayer().getInventory().getItem(e.getPlayer().getInventory().getHeldItemSlot()).getAmount() - 1);
            p.EcIncrease(2);
        }
        if (id == 106 && p.getEcSize() == 2) {
            e.getPlayer().getInventory().getItem(e.getPlayer().getInventory().getHeldItemSlot()).setAmount(e.getPlayer().getInventory().getItem(e.getPlayer().getInventory().getHeldItemSlot()).getAmount() - 1);
            p.EcIncrease(3);
        }
        if (id == 107 && p.getEcSize() == 3) {
            e.getPlayer().getInventory().getItem(e.getPlayer().getInventory().getHeldItemSlot()).setAmount(e.getPlayer().getInventory().getItem(e.getPlayer().getInventory().getHeldItemSlot()).getAmount() - 1);
            p.EcIncrease(4);
        }
        if (id == 108 && p.getEcSize() == 4) {
            e.getPlayer().getInventory().getItem(e.getPlayer().getInventory().getHeldItemSlot()).setAmount(e.getPlayer().getInventory().getItem(e.getPlayer().getInventory().getHeldItemSlot()).getAmount() - 1);
            p.EcIncrease(5);
        }
        if (id == 109 && p.getEcSize() == 5) {
            e.getPlayer().getInventory().getItem(e.getPlayer().getInventory().getHeldItemSlot()).setAmount(e.getPlayer().getInventory().getItem(e.getPlayer().getInventory().getHeldItemSlot()).getAmount() - 1);
            p.EcIncrease(6);
        }
    } else {
        p.toPlayer().sendMessage(WorldUtils.worldName() + " §cСынок" );
    }
    if (id == 113) {
        e.getPlayer().getInventory().getItem(e.getPlayer().getInventory().getHeldItemSlot()).setAmount(e.getPlayer().getInventory().getItem(e.getPlayer().getInventory().getHeldItemSlot()).getAmount() - 1);
        p.setXpModifier(p.getXpModifier() + 0.05D);
        p.toPlayer().sendMessage(WorldUtils.worldName() + " §aПолучаемый опыт увеличен на §65%§a!" );
    }
    int spellid = 0;
    if (id == 11) {
        spellid = 1;
        spellLearning(e.getPlayer(), 1);
    }
        if(id == 100001){
            azerot.getInstance().setRouterecording(false);
            p.toPlayer().sendMessage(WorldUtils.worldName() + " §2Запись закончена!");
        }
    if(id == 14) {
        if (p.getSpellcooldown().get("xilka") == null || p.getSpellcooldown().get("xilka") <= System.currentTimeMillis()) {
            RPGDamageble.setHealth(e.getPlayer(), p.getHealth() + 20, null, null);
            p.toPlayer().getInventory().getItemInMainHand().setAmount(p.toPlayer().getInventory().getItemInMainHand().getAmount() - 1);
            p.toPlayer().sendMessage(WorldUtils.worldName() + " §c+20♥");
            p.getSpellcooldown().put("xilka", System.currentTimeMillis() + 15000);

        } else if (p.getSpellcooldown().get("xilka") != null || p.getSpellcooldown().get("xilka") >= System.currentTimeMillis()) {
            p.toPlayer().sendMessage(WorldUtils.worldName() + " §cВы не можете использовать это еще " + ((p.getSpellcooldown().get("xilka") - System.currentTimeMillis())/1000L) + " секунд!");
        }
    }
        if(id == 15) {
            if (p.getSpellcooldown().get("xilka") == null || p.getSpellcooldown().get("xilka") <= System.currentTimeMillis()) {
                RPGDamageble.setHealth(e.getPlayer(), p.getHealth() + 75, null, null);
                p.toPlayer().getInventory().getItemInMainHand().setAmount(p.toPlayer().getInventory().getItemInMainHand().getAmount() - 1);
                p.toPlayer().sendMessage(WorldUtils.worldName() + " §c+75♥");
                p.getSpellcooldown().put("xilka", System.currentTimeMillis() + 15000);

            } else if (p.getSpellcooldown().get("xilka") != null || p.getSpellcooldown().get("xilka") >= System.currentTimeMillis()) {
                p.toPlayer().sendMessage(WorldUtils.worldName() + " §cВы не можете использовать это еще " + ((p.getSpellcooldown().get("xilka") - System.currentTimeMillis())/1000L) + " секунд!");
            }
        }
    if (id == 12) {
        spellLearning(e.getPlayer(), 2);
    }
        if (id == 1038) {
            if(!p.getSettings().get("openCases")) {
                Random r = new Random();
                List<Integer> values = new ArrayList<>();
                int[] limits = {35, 35, 20, 10};

                // Массив значений
                int[] valuesToDistribute = {1, 2, 3, 4};
                boolean allLimitsReached = false;
                int[] usedCounts = {0, 0, 0, 0};


                while (values.isEmpty() || values.size() < 100) {
                    // Проверяем, есть ли еще свободные места для значений
                    boolean hasFreeSpace = false;
                    for (int i = 0; i < limits.length; i++) {
                        if (usedCounts[i] < limits[i]) {
                            hasFreeSpace = true;
                            break;
                        }
                    }

                    if (hasFreeSpace) {
                        // Выбираем случайное значение
                        int randomValueIndex = r.nextInt(valuesToDistribute.length);
                        int value = valuesToDistribute[randomValueIndex];

                        // Проверяем, не превышен ли лимит для этого значения
                        if (usedCounts[randomValueIndex] < limits[randomValueIndex]) {
                            // Добавляем значение в список
                            values.add(value);
                            // Увеличиваем счетчик добавленных значений
                            usedCounts[randomValueIndex]++;
                        }
                    } else {
                        // Если все лимиты достигнуты, прерываем цикл
                        break;
                    }
                }

                int random = r.nextInt(100);
                int value = values.get(random);

                switch (value) {
                    case 1:
                        if (p.getTalents().get("Magicalfoundation") == null || ((Integer) p.getTalents().get("Magicalfoundation")).intValue() == 0) {
                            p.getTalents().put("Magicalfoundation", Integer.valueOf(1));
                            p.getActivatedtalents().put("Magicalfoundation", Boolean.valueOf(false));
                            p.getTalentsPoints().put("Magicalfoundation", Integer.valueOf(1));
                            p.toPlayer().sendMessage(WorldUtils.worldName() + " §2Вы изучили новую эмблему!");
                            break;
                        }
                        p.getTalentsPoints().put("Magicalfoundation", Integer.valueOf(((Integer) p.getTalentsPoints().get("Magicalfoundation")).intValue() + 1));
                        p.toPlayer().sendMessage(WorldUtils.worldName() + " §2Вы получили: §2Эмблема: Выносливость. Всего у вас: " + p.getTalentsPoints().get("Magicalfoundation"));

                        break;
                    case 2:
                        if (p.getTalents().get("Survivalinstinct") == null || ((Integer) p.getTalents().get("Survivalinstinct")).intValue() == 0) {
                            p.getTalents().put("Survivalinstinct", Integer.valueOf(1));
                            p.getActivatedtalents().put("Survivalinstinct", Boolean.valueOf(false));
                            p.getTalentsPoints().put("Survivalinstinct", Integer.valueOf(1));
                            p.toPlayer().sendMessage(WorldUtils.worldName() + " §2Вы изучили новую эмблему!");
                            break;
                        }
                        p.getTalentsPoints().put("Survivalinstinct", Integer.valueOf(((Integer) p.getTalentsPoints().get("Survivalinstinct")).intValue() + 1));
                        p.toPlayer().sendMessage(WorldUtils.worldName() + " §2Вы получили: §2Эмблема: Инстинкт выживания. Всего у вас: " + p.getTalentsPoints().get("Survivalinstinct"));

                        break;
                    case 3:
                        if (p.getTalents().get("SunAndMoon") == null || ((Integer) p.getTalents().get("SunAndMoon")).intValue() == 0) {
                            p.getTalents().put("SunAndMoon", Integer.valueOf(1));
                            p.getActivatedtalents().put("SunAndMoon", Boolean.valueOf(false));
                            p.getTalentsPoints().put("SunAndMoon", Integer.valueOf(1));
                            p.toPlayer().sendMessage(WorldUtils.worldName() + " §2Вы изучили новую эмблему!");
                            break;
                        }
                        p.getTalentsPoints().put("SunAndMoon", Integer.valueOf(((Integer) p.getTalentsPoints().get("SunAndMoon")).intValue() + 1));
                        p.toPlayer().sendMessage(WorldUtils.worldName() + " §2Вы получили: §7Эмблема: Солнце и луна. Всего у вас: " + p.getTalentsPoints().get("SunAndMoon"));

                        break;
                    case 4:
                        if (p.getTalents().get("SecondChance") == null || ((Integer) p.getTalents().get("SecondChance")).intValue() == 0) {
                            p.getTalents().put("SecondChance", Integer.valueOf(1));
                            p.getActivatedtalents().put("SecondChance", Boolean.valueOf(false));
                            p.getTalentsPoints().put("SecondChance", Integer.valueOf(1));
                            p.toPlayer().sendMessage(WorldUtils.worldName() + " §2Вы изучили новую эмблему!");
                            break;
                        }
                        p.getTalentsPoints().put("SecondChance", Integer.valueOf(((Integer) p.getTalentsPoints().get("SecondChance")).intValue() + 1));
                        p.toPlayer().sendMessage(WorldUtils.worldName() + " §2Вы получили: §4Эмблема: Второй шанс. Всего у вас: " + p.getTalentsPoints().get("SecondChance"));
                        break;
                }
                e.getPlayer().getInventory().getItem(handslot).setAmount(e.getPlayer().getInventory().getItem(handslot).getAmount() - 1);
            }
        }
        if (id == 13) {
        e.getPlayer().getInventory().getItem(handslot).setAmount(e.getPlayer().getInventory().getItem(handslot).getAmount() - 1);
        p.clearStats();
        p.clearSpellBinds();
        p.clearLearnedSpells();
        p.toPlayer().sendMessage(WorldUtils.worldName() + " §7Вы сбросили все свои навыки. Хотите попробовать заново?");
    }
    if ((id == 10 || id == 191) &&
            !p.toPlayer().hasPotionEffect(PotionEffectType.LUCK) && p.toPlayer().getVehicle() == null) {
        Horse h = (Horse)p.toPlayer().getWorld().spawn(p.toPlayer().getLocation(), Horse.class);
        h.setCustomName("Скакун");
                h.setCustomNameVisible(false);
        h.setMetadata("mount", (MetadataValue)new FixedMetadataValue((Plugin)azerot.getInstance(), Integer.valueOf(1)));
        h.setOwner((AnimalTamer)p.toPlayer());
        h.setAdult();
        h.setStyle(Horse.Style.NONE);
        h.setColor(Horse.Color.BROWN);
        AttributeInstance ai = ((CraftLivingEntity)h).getHandle().getAttributeInstance(GenericAttributes.MOVEMENT_SPEED);
        if (id == 10)
            ai.setValue(0.5D);
        if (id == 191) {
            ai.setValue(0.65D);
            h.getInventory().setArmor(new ItemStack(Material.IRON_BARDING));
        }
        h.getInventory().setSaddle(new ItemStack(Material.SADDLE));
        h.addPassenger((Entity)p.toPlayer());
        e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 200, 100, true, false), true);
    }
    if (RPGI.getTeleport() != null) {
        if (p.toPlayer().hasPotionEffect(PotionEffectType.LUCK)) {
            e.getPlayer().sendMessage(WorldUtils.worldName() + " §cНельзя использовать в бою!" );
            return;
        }
        World w = Bukkit.getWorld(RPGI.getTeleport()[0]);
        double x = Integer.parseInt(RPGI.getTeleport()[1]) + 0.5D;
        float y = Integer.parseInt(RPGI.getTeleport()[2]);
        double z = Integer.parseInt(RPGI.getTeleport()[3]) + 0.5D;
        float yaw = Integer.parseInt(RPGI.getTeleport()[4]);
        float pitch = Integer.parseInt(RPGI.getTeleport()[5]);
        e.getPlayer().getInventory().getItem(e.getPlayer().getInventory().getHeldItemSlot()).setAmount(e.getPlayer().getInventory().getItem(e.getPlayer().getInventory().getHeldItemSlot()).getAmount() - 1);
        e.getPlayer().teleport(new Location(w, x, y, z, yaw, pitch));
        e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 100, true, false), true);
        e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 200, 100, true, false), true);
    }
}
    public static void removeItemFromHand(Player p, int count) {
        p.getInventory().getItem(p.getInventory().getHeldItemSlot()).setAmount(p.getInventory().getItem(p.getInventory().getHeldItemSlot()).getAmount() - count);
    }

    public static void spellLearning(Player player, int spellid) {
        rpgplayer p = rpgplayer.getRPGPlayer(player);
        if (p.getLearnedSpells().contains(Integer.valueOf(spellid))) {
            player.sendMessage(WorldUtils.worldName() + " §cВы уже выучили это заклинание!" );
            return;
        }
        removeItemFromHand(player, 1);
        p.addLearnedSpells(spellid);
        p.toPlayer().sendMessage(WorldUtils.worldName() + " §aВы выучили новое заклинание " + ItemsForGui.SpellBook(spellid).getItemMeta().getDisplayName() + "§a!");
                p.toPlayer().spawnParticle(Particle.ENCHANTMENT_TABLE, p.toPlayer().getLocation(), 0, 0.0D, 0.0D, 100.0D);
    }
}
