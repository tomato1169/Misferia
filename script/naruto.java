package azerot.azerot.script;

import azerot.azerot.MobSpells;
import azerot.azerot.WorldUtils;
import azerot.azerot.azerot;
import azerot.azerot.mobs.MobInfo;
import azerot.azerot.mobs.MobInfoManager;
import azerot.azerot.mobs.MobSystem;
import azerot.azerot.rpgplayer;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import net.minecraft.server.v1_12_R1.AttributeInstance;
import net.minecraft.server.v1_12_R1.EntityInsentient;
import net.minecraft.server.v1_12_R1.GenericAttributes;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftLivingEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.sql.SQLException;
import java.util.*;

public class naruto
{
    static HashMap<String, Integer> attack1 = new HashMap<>();

    static List<Location> locationMobs = new ArrayList<>();




    public static void onDamaged(Entity e, Entity t, int damage) throws SQLException {
        Vector velocity;
        MobInfo mobInfo = MobInfoManager.getMobInfo(e);
        int id = mobInfo.getId();
        double percentage = (double) mobInfo.getHealth() / mobInfo.getMaxHealth();
        List<Player> p = MobSystem.getNearbyPlayers((LivingEntity)e, 8.0D);
        final int[] smena = {0};

        if (mobInfo.getHealth() + damage == mobInfo.getMaxHealth()) {
            mobInfo.setCritChance(0);
            attack1.put(e.getUniqueId().toString(), Integer.valueOf(0));
        }

        if (percentage < 0.9D && (Integer) attack1.get(e.getUniqueId().toString()) == 0) {
            message(e, "§4ААГРХХХ");
            attack1.put(e.getUniqueId().toString(), 1);
            ((CraftLivingEntity) e).getEquipment().setItemInMainHand(new ItemStack(Material.IRON_SWORD));
        }

        if (percentage < 0.5D && (Integer) attack1.get(e.getUniqueId().toString()) == 1) {
            message(e, "§4ААГРХХХ");
            attack1.put(e.getUniqueId().toString(), 2);
            mobInfo.setRange(30);
            Disguise disguise = DisguiseAPI.getDisguise((Entity) e);
            disguise.getWatcher().setCustomName("");
            disguise.getWatcher().setCustomNameVisible(false);
            e.setCustomNameVisible(false);
            ((CraftLivingEntity) e).getEquipment().setItemInMainHand(new ItemStack(Material.BOW));
            Double speed = 0.2D;
            Random random = new Random();
            Player pl = MobSystem.getTarget(e);

            (new BukkitRunnable() {
                public void run() {

                    if(MobSystem.getTarget(e) == null || e.isDead()){
                        for(Entity ent : azerot.getEntityForBossInThreePatch()){
                            MobSystem.customRemove(ent);
                        }
                        cancel();
                    }
                }
            }).runTaskTimer((Plugin)azerot.getInstance(), 2L, 2L);

            (new BukkitRunnable() {
                public void run() {
                    for (Location loc : locationMobs) {
                        int randomIndex = random.nextInt(locationMobs.size());
                        if(smena[0] == 0){
                            if(locationMobs.get(randomIndex).getY() == 55){
                                e.teleport(locationMobs.get(randomIndex));
                                smena[0]++;
                                MobSystem.customTarget(e, pl);
                            }
                        }else{
                            try {
                                WorldUtils.spawnMob("100", loc, "0");
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                }
            }).runTaskLater((Plugin)azerot.getInstance(), 10L);


            (new BukkitRunnable() {
                public void run() {

                    if(e.isDead()){
                        cancel();
                    }
                    if(MobSystem.getTarget(e) == null){
                        cancel();
                    }
                    double x = (Math.random() - 0.5) * speed;
                    double z = (Math.random() - 0.5) * speed;

                    // Создаем вектор скорости
                    Vector velocity = new Vector(x, 0, z);
                    e.setVelocity(e.getVelocity().add(velocity));
                }
            }).runTaskTimer((Plugin)azerot.getInstance(), 2L, 2L);
        }
    }




    public static void onVortex(List<Player> p, Entity e){
        for(Player nearby : p){
            MobSpells.curseVortex(e, nearby, 3000,10);
        }
    }

    public static void onBlood(List<Player> p, Entity e){
        for(Player nearby : p){
            MobSpells.curseBlood(e, nearby, 5000, 1);
        }
    }

    public static void onSpawn(Entity entity) {
        World world = Bukkit.getWorld("realm2");  // Получаем мир один раз
        double[][] coordinates = {
                {-1244.5, 67, 1372.5}, {-1236.5, 67, 1365.5}, {-1235.5, 67, 1349.5}, {-1242.5, 67, 1341.5}, {-1258.5, 67, 1340.5},
                {-1266.5, 67, 1347.5}, {-1267.5, 67, 1363.5}, {-1260.5, 67, 1371.5},
                {-1251.5, 55, 1365.5}, {-1260.5, 55, 1356.5}, {-1251.5, 55, 1347.5}, {-1242.5, 55, 1356.5}, {-1233.5, 55, 1356.5},
                {-1240.5, 55, 1369.5}, {-1251.5, 55, 1374.5}, {-1264.5, 55, 1367.5}, {-1269.5, 55, 1356.5},
                {-1262.5, 55, 1343.5}, {-1251.5, 55, 1338.5}, {-1238.5, 55, 1345.5}
        };

        for (double[] coord : coordinates) {
            locationMobs.add(new Location(world, coord[0], coord[1], coord[2]));
        }

    }

    public static void message(Entity e, String message) {
        MobInfo mobInfo = MobInfoManager.getMobInfo(e);
        int level = mobInfo.getLevel();
        String name = mobInfo.getName();
        List<Player> p = MobSystem.getNearbyPlayers((LivingEntity)e, 8.0D);
        for (Player nearby : p)
            nearby.sendMessage("§4["+ level + "§4] " + name + "§f: " + message);
    }
}
