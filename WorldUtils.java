package azerot.azerot;

import azerot.azerot.RPGMob.*;
import azerot.azerot.mobs.MobInfo;
import azerot.azerot.mobs.MobInfoManager;
import azerot.azerot.mobs.RPGMobsContainer;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.*;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftLivingEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorldUtils implements Listener {
    public static String worldName() {
        return "§5Misferia §7->";
    }

    public static void updatePlayerTab(Player p) {
        rpgplayer rplayer = rpgplayer.getRPGPlayer(p);
        p.setPlayerListName("§7[§6" + rplayer.getPLevel() + "§7] " + " " + p.getName());
    }
    public static String partyName() {
        return "";
    }

    public static void clearDrop() {
        for (World world : Bukkit.getServer().getWorlds()) {
            List<org.bukkit.entity.Entity> entList = world.getEntities();
            for (org.bukkit.entity.Entity current : entList) {
                if (current instanceof org.bukkit.entity.Item)
                    current.remove();
            }
        }
        Bukkit.broadcastMessage(worldName() + " §aПредметы с земли были удалены!" );
    }


    public static int checkMoney(rpgplayer p, int money){
        int result = 0;
        if(p.getMoney() >= money){
            result = p.getMoney();
        }
        return result;
    }
    public static void removeMoney(rpgplayer p, int money){
        p.setMoney(p.getMoney() - money);
    }

    public static int checkItems(rpgplayer p, int id, int results) {
        int result = 0;
        PlayerInventory playerInventory = p.toPlayer().getInventory();
if(id > 5) {
    for (int i = 0; i < 36; i++) {
        if (playerInventory
                .getItem(i) != null && playerInventory
                .getItem(i).getAmount() != 0 &&
                ItemStat.getID(playerInventory.getItem(i)) == id)
            result += playerInventory.getItem(i).getAmount();
    }
}
  return result;
    }
    public static int getPercentByInts(double int1, double int2) {
        return (int)Math.round(int1 / int2 * 100.0D);
    }

    public static String broadcast() {
        return "§8[§6Объявление§8]";
    }



    public static boolean hasInventory(Player p, int amount) {
        int amount2 = 0;
        for (int i = 0; i < 36; i++) {
            if (p.getInventory().getItem(i) == null || p.getInventory().getItem(i).getType().equals(Material.AIR) || p.getInventory().getItem(i).getAmount() == 0)
                amount2++;
        }
        if (amount2 < amount) {
            p.sendMessage(worldName() + " §cНет места!" );
            return false;
        }
        return true;
    }

    public static void removeItems(rpgplayer p, int id, int amount) {
        PlayerInventory playerInventory = p.toPlayer().getInventory();

        int i = 0;
if(id > 5) {
    while (amount > 0) {
        if (playerInventory
                .getItem(i) != null && playerInventory
                .getItem(i).getAmount() != 0 &&
                ItemStat.getID(playerInventory.getItem(i)) == id) {
            int amount2 = playerInventory.getItem(i).getAmount();
            if (amount >= amount2) {
                playerInventory.setItem(i, new ItemStack(Material.AIR));
                amount -= amount2;
            } else {
                ItemStack item = playerInventory.getItem(i);
                item.setAmount(amount2 - amount);
                amount = 0;
            }
        }
        i++;
    }
}else{
    return;
}



    }

    public static void clearEntity() {
        for (World world : Bukkit.getServer().getWorlds()) {
            List<org.bukkit.entity.Entity> entList = world.getEntities();
            for (org.bukkit.entity.Entity current : entList) {
                if (current.getType().equals(EntityType.ARMOR_STAND)) {
                    if (current.isCustomNameVisible())
                        current.remove();
                    continue;
                }
                if(!current.isCustomNameVisible()){
                    current.remove();
                }
                if (current instanceof org.bukkit.entity.LivingEntity && !(current instanceof Player))
                    current.remove();
            }
        }
    }

    public static void addListLocations() throws Exception {
        File file = new File(azerot.getInstance().getDataFolder() + File.separator + "spawners.yml");
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        List<Location> loc = new ArrayList<>();
        int i = 1;
        int j = 1;
        while (yamlConfiguration.getString((new StringBuilder(String.valueOf(i))).toString()) != null) {
            while (yamlConfiguration.getString(String.valueOf(i) + "." + j) != null) {
                World w = null;
                if(i <= 60){
                    w = Bukkit.getServer().getWorld("world");
                }
                if(i > 60){
                    w = Bukkit.getServer().getWorld("realm2");
                }
                double x = Math.round(yamlConfiguration.getDouble(String.valueOf(i) + "." + j + ".x"));
                double y = yamlConfiguration.getDouble(String.valueOf(i) + "." + j + ".y");
                double z = Math.round(yamlConfiguration.getDouble(String.valueOf(i) + "." + j + ".z"));
                Location location = new Location(w, x + 0.5D, y, z + 0.5D, 0.0F, 0.0F);
                loc.add(location);
                j++;
            }
            azerot.getLocationsMobs().put(i, loc);
            i++;
            j = 1;
        }
    }

    public static void spawnAllMobs() throws Exception {
        try {

            int i = 1;
            while (azerot.getLocationsMobs().get(i) != null) {
                for (int j = 1; j < azerot.getLocationsMobs().get(i).size();) {
                    spawnMob(String.valueOf(i), String.valueOf(j));
                    j++;
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void spawnMob(String id, String number) throws Exception {
        if (number.equals("0"))
            return;

        spawnMob(id, azerot.getLocationsMobs().get(Integer.valueOf(id)).get(Integer.parseInt(number)), number);

    }




    public static Entity spawnMob(String id, Location location, String number) throws SQLException {
        RPGZombie zombie;
        RPGBower bower;
        Bukkit.getLogger().info("" + id);
        RPGWolf wolf;
        RPGWitherSkeleton witherskeleton;
        RPGSpider spider;
        RPGCaveSpider cavespider;
        RPGCow cow;
        RPGGolem golem;
        RPGCat cat;
        RPGBlaze blaze;
        RPGWitch witch;
        RPGMobsContainer rmc = RPGMobsContainer.getRPGMobsContainer(Integer.parseInt(id));
            String type = rmc.getType();
            WorldServer world = ((CraftWorld) location.getWorld()).getHandle();
        Entity entity = null;
            switch (type) {
                case "zombie":
                    zombie = new RPGZombie(location, Integer.parseInt(id), number);
                    entity = spawnEntity(location, (Entity) zombie);
                    break;
                case "bower":
                    bower = new RPGBower(location, Integer.parseInt(id), number);
                    entity = spawnEntity(location, (Entity) bower);
                    break;
                case "wolf":
                    wolf = new RPGWolf(location, Integer.parseInt(id), number);
                    entity = spawnEntity(location, (Entity) wolf);
                    break;
                case "witherskeleton":
                    witherskeleton = new RPGWitherSkeleton(location, Integer.parseInt(id), number);
                    entity = spawnEntity(location, (Entity) witherskeleton);
                    break;
                case "spider":
                    spider = new RPGSpider(location, Integer.parseInt(id), number);
                    entity = spawnEntity(location, (Entity) spider);
                    break;
                case "cavespider":
                    cavespider = new RPGCaveSpider(location, Integer.parseInt(id), number);
                    entity = spawnEntity(location, (Entity) cavespider);
                    break;
                case "cow":
                    cow = new RPGCow(location, Integer.parseInt(id), number);
                    entity = spawnEntity(location, (Entity) cow);
                    break;
                case "golem":
                    golem = new RPGGolem(location, Integer.parseInt(id), number);
                    entity = spawnEntity(location, golem);
                    break;
                case "cat":
                    cat = new RPGCat(location, Integer.parseInt(id), number);
                    entity = spawnEntity(location, cat);
                    break;
                case "blaze":
                    blaze = new RPGBlaze(location, Integer.parseInt(id), number);
                    entity = spawnEntity(location, blaze);
                    break;
                case "witch":
                    witch = new RPGWitch(location, Integer.parseInt(id), number);
                    entity = spawnEntity(location, witch);
                    break;
            }
            if(id.equals("100")){
                assert entity != null;
                if(location.getY() != 55){
                    AttributeInstance ai = ((EntityInsentient)((CraftLivingEntity)entity.getBukkitEntity()).getHandle()).getAttributeInstance(GenericAttributes.MOVEMENT_SPEED);
                    ai.setValue(0.0D);
                }
                azerot.getEntityForBossInThreePatch().add(entity.getBukkitEntity());
            }
        return entity;
        }
    public static Entity spawnEntity(Location loc, Entity e) {
        WorldServer world = ((CraftWorld)loc.getWorld()).getHandle();
        e.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
        MobInfo mobInfo = MobInfoManager.getMobInfo(e.getBukkitEntity());
        mobInfo.setSpawnLocation(loc);
        ScriptsCheck.ScriptsOnSpawn(e.getBukkitEntity());
        world.addEntity(e, CreatureSpawnEvent.SpawnReason.CUSTOM);
        return e.getBukkitEntity().getHandle();
    }

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent e) {
        byte b;
        int i;
        org.bukkit.entity.Entity[] arrayOfEntity;
        for (i = (arrayOfEntity = e.getChunk().getEntities()).length, b = 0; b < i; ) {
            org.bukkit.entity.Entity en = arrayOfEntity[b];
            if (en instanceof org.bukkit.entity.Item)
                en.remove();
            if (en instanceof org.bukkit.entity.ArmorStand && en.getVehicle() == null)
                en.remove();
            b = (byte)(b + 1);
        }
    }

    @EventHandler
    public void onChunkUnload(ChunkUnloadEvent e) {
        byte b;
        int i;
        org.bukkit.entity.Entity[] arrayOfEntity;
        for (i = (arrayOfEntity = e.getChunk().getEntities()).length, b = 0; b < i; ) {
            org.bukkit.entity.Entity entity = arrayOfEntity[b];
            if (entity instanceof org.bukkit.entity.LivingEntity)
                e.setCancelled(true);
            b = (byte)(b + 1);
        }
    }




}
