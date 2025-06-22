package azerot.azerot.mobs;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import azerot.azerot.*;
import azerot.azerot.Quest.Quest;
import azerot.azerot.RPGMob.RPGZombie;
import azerot.azerot.enchants.ScaleDrop;
import azerot.azerot.enchants.WeaponScaling;
import azerot.azerot.events.PVP_JAPAN_Event;
import de.slikey.effectlib.effect.EarthEffect;
import io.netty.util.internal.ThreadLocalRandom;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;



public class MobSystem implements Listener {
    public static HashMap<UUID, List<Player>> damagers = new HashMap<>();

    public static HashMap<UUID, HashMap<Player, Integer>> damagePlayers = new HashMap<>();
    public static  Set<Entity> monsters = new HashSet<>();
    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        e.getDrops().clear();
        e.setDroppedExp(0);
    }
    public static void setMetaData(Entity e, String data, Object dataindb) {
        e.setMetadata(data, (MetadataValue)new FixedMetadataValue((Plugin)azerot.getInstance(), dataindb));
    }
    public static void setData(Entity e, int id, int number, Player visibleTo) {
        RPGMobsContainer monster = RPGMobsContainer.getRPGMobsContainer(id);
        MobInfo mobInfo = MobInfoManager.getMobInfo(e);
        setMetaData(e, "mob", Integer.valueOf(1));
        mobInfo.setId(id);
        mobInfo.setNumber(number);
        int champion = 1;
        if (number != 0 && monster.getTypeMob() == 0) {
            if (Math.random() <= 0.05D)
                champion = 2;
            if (Math.random() <= 0.03D)
                champion = 3;
            if (Math.random() <= 0.01D)
                champion = 4;
        }
        mobInfo.setLevel(monster.getLevel());
        mobInfo.setName(monster.getName());
        mobInfo.setRespawn((int) monster.getRespawn());
        mobInfo.setXp(monster.getXp() * champion);
        double damageModifier = (champion == 1) ? 1.0D : ((champion == 2) ? 1.100000023841858D : (1.0F + 0.1F * (champion - 1)));
        mobInfo.setDamage((int)(monster.getDamage() * damageModifier));
        mobInfo.setMagicDamage((int)(monster.getMagicdamage() * damageModifier));
        mobInfo.setCritChance(monster.getCritchance());
        mobInfo.setVampiric(monster.getVampiric());
        mobInfo.setKnockback(monster.getKnockback());
        mobInfo.setRange(monster.getRange());
        mobInfo.setAttackSpeed(monster.getAttackspeed());
        int randomHealth = monster.getHealth() * champion;
        randomHealth = (int)(randomHealth + monster.getHealth() * Math.random() * 20.0D / 100.0D);
        mobInfo.setHealth(randomHealth);
        mobInfo.setMaxHealth(randomHealth);
        if (monster.getManaMob() > 0) {
            mobInfo.setMana(10 + monster.getLevel());
            mobInfo.setMaxMana(10 + monster.getLevel());
        }
        mobInfo.setArmor(monster.getArmor());
        mobInfo.setMagicArmor(monster.getMagicarmor());
        mobInfo.setResistance(monster.getResistance());
        mobInfo.setDodge(monster.getDodge());
        mobInfo.setMobRange(monster.getMobrange());
        mobInfo.setBlackB(monster.getBlackBlood());
        mobInfo.setManaMob(monster.getManaMob());
        mobInfo.setAggressive(monster.getAggressive());
        mobInfo.setScript(monster.getScript());
        mobInfo.setLeveling(0);
        mobInfo.setChampion(champion);
        if (visibleTo != null)
            mobInfo.setVisibleTo(visibleTo.getName());
        if (monster.getSpeed() > 0)
            ((LivingEntity)e).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2147483647, monster.getSpeed() - 1, false, false));
        if (monster.getSlow() > 0)
            ((LivingEntity)e).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 2147483647, monster.getSlow() - 1, false, false));
        if (monster.getInvisible() > 0)
            ((LivingEntity)e).addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 2147483647, 0, false, false));
    }
    public static void damageMob(LivingEntity entity, int damage, Entity damager, boolean isBow) throws SQLException {
        if (entity == null)
            return;
        MobInfo mobInfo = MobInfoManager.getMobInfo((Entity)entity);
        if (mobInfo.getVisibleTo() != null && !mobInfo.getVisibleTo().equals(damager))
            return;
        List<Player> mobDamagers = (damagers.get(entity.getUniqueId()) == null) ? new ArrayList<>() : damagers.get(entity.getUniqueId());
        int id = mobInfo.getId();
        int number = mobInfo.getNumber();
        int level = mobInfo.getLevel();
        int xp = mobInfo.getXp();
        int health = mobInfo.getHealth();
        if (((Creature)entity).getTarget() == null)
            customTarget((Entity)entity, damager);
        setHealth((Entity)entity, health - damage);
        entity.damage(0.0D);
        DamageSystem.damageParticle(entity.getLocation());
        if (!mobDamagers.contains(damager))
            mobDamagers.add((Player)damager);
        damagers.put(entity.getUniqueId(), mobDamagers);
        ScriptsCheck.ScriptsOnDamaged((Entity)entity, damager, damage, isBow);
        if ((health - damage) <= 0.0D) {
            entity.setHealth(0.0D);
            int respawn = mobInfo.getRespawn();
            if(id == 1000 || id == 1001 || id == 1002){
                respawnMobsJapanEvent(String.valueOf(id), String.valueOf(0), respawn, entity.getLocation());
                Player player = Bukkit.getPlayer(damager.getName());
                rpgplayer pl = rpgplayer.getRPGPlayer(player);
                String team = pl.getPVP_JAPAN_Event_Team();
                if(team.equals("red")){
                    PVP_JAPAN_Event.getGames("1-1").getRedTeam().put(player.getName(), PVP_JAPAN_Event.getGames("1-1").getRedTeam().get(pl.getName()) + 1);
                    PVP_JAPAN_Event.getGames("1-1").setFullPOintsRed(PVP_JAPAN_Event.getGames("1-1").getFullPOintsRed() + 1);
                }
                if(team.equals("blue"))   PVP_JAPAN_Event.getGames("1-1").getBlueTeam().put(player.getName(), PVP_JAPAN_Event.getGames("1-1").getBlueTeam().get(pl.getName()) + 1);
            }else{
                respawnMob(String.valueOf(id), String.valueOf(number), respawn);
            }
            if (mobInfo.getLeveling() == 0) {
                drop(id, entity.getLocation());
                giveXpMob(mobInfo, id, level, xp, entity.getLocation(), damagers.get(entity.getUniqueId()));
            }
            if(id == 100){
                EarthEffect starEffect17 = new EarthEffect(azerot.getEffectManager());
                starEffect17.setLocation(entity.getLocation().add(0,1,0));
                starEffect17.particleLand = Particle.REDSTONE;
                starEffect17.particleOcean = Particle.REDSTONE;
                starEffect17.colorLand = Color.BLACK;
                starEffect17.colorOcean = Color.BLACK;
                starEffect17.iterations = 1;
                starEffect17.duration = 200;
                starEffect17.start();
            }
            mobDamagers.clear();
            damagers.remove(entity.getUniqueId());
            ScriptsCheck.ScriptsOnDeath((Entity)entity, damager);
            customRemove((Entity)entity);
        }
        int targetCount = mobInfo.getTargetCount();
        mobInfo.setTargetCount(targetCount++);
        if (((Creature)entity).getTarget() == damager)
            mobInfo.setTargetCount(0);
        if (targetCount >= 10) {
            customTarget((Entity)entity, damager);
            mobInfo.setTargetCount(0);
        }
    }
    public static void customTarget(Entity e, Entity target) {
        MobInfo mobInfo = MobInfoManager.getMobInfo(e);
        if (mobInfo.getVisibleTo() == null) {
            ((Creature)e).setTarget((LivingEntity)target);
        } else if (target == null) {
            ((Creature)e).setTarget(null);
        } else if (target.equals(mobInfo.getVisibleTo())) {
            ((Creature)e).setTarget((LivingEntity)target);
        }
        if (mobInfo.getHealth() == mobInfo.getMaxHealth())
            mobInfo.setLastAttackTime(System.currentTimeMillis());
        mobInfo.setTargetCount(17);
    }
    public static void customRemove(Entity e) {
        MobInfoManager.removeMobInfo(e);
        monsters.remove(e);
        e.remove();
    }

    public static void setSpawn(){
        (new BukkitRunnable() {
            public void run() {
                try {
                    if (!monsters.isEmpty()){
                        monsters.forEach(entity -> {
                            if(entity == null || entity.isDead()) return;
                            getNearbyPlayers((LivingEntity) entity, 10).forEach(player -> {
                                RPGDamageble.damage(player,  (int)(rpgplayer.getRPGPlayer((Player)player).getMaxHealth() * 0.03D), null, null);
                            });
                        });

                    }


                } catch (IndexOutOfBoundsException exception) {
                    cancel();
                    return;
                }
            }
        }).runTaskTimer((Plugin)azerot.getPlugin(azerot.class), 0L, 10L);
    }


    public static void addHealth(Entity e, int health) {
        MobInfo mobInfo = MobInfoManager.getMobInfo(e);
        health = mobInfo.getHealth() + health;
        setHealth(e, health);
    }


    public static String getLevel(int id) {
        RPGMobsContainer rmc = RPGMobsContainer.getRPGMobsContainer(id);
        int level = rmc.getLevel();
        return String.valueOf(level);
    }
    public static void setHealth(Entity e, int health) {
        MobInfo mobInfo = MobInfoManager.getMobInfo(e);
        health = Math.min(health, mobInfo.getMaxHealth());
        mobInfo.setHealth(health);
        setName(e);
    }

    public static void setMaxHealth(Entity e, int health) {
        MobInfo mobInfo = MobInfoManager.getMobInfo(e);
        mobInfo.setMaxHealth(health);
        setName(e);
    }
    public static void setName(Entity e) {
        if (!Objects.equals(RPGMobsContainer.getRPGMobsContainer(MobInfoManager.getMobInfo(e).getId()).getType(), "zombie")) {
            e.setCustomName(getFullName(e));
        } else {
            MobInfo mobInfo = MobInfoManager.getMobInfo(e);
            if(mobInfo.getId() == 85){
                double percentage = (double) mobInfo.getHealth() / mobInfo.getMaxHealth();
                if(percentage < 0.5D){
                    Disguise disguise = DisguiseAPI.getDisguise((Entity) e);
                    disguise.getWatcher().setCustomName("");
                    disguise.getWatcher().setCustomNameVisible(false);
                }else{
                    e.setCustomName(getFullName(e));
                    Disguise disguise = DisguiseAPI.getDisguise(e);
                    disguise.getWatcher().setCustomName(getFullName(e));
                }
            }else{
                e.setCustomName(getFullName(e));
                Disguise disguise = DisguiseAPI.getDisguise(e);
                disguise.getWatcher().setCustomName(getFullName(e));
            }
        }
        if (!Objects.equals(RPGMobsContainer.getRPGMobsContainer(MobInfoManager.getMobInfo(e).getId()).getType(), "bower")) {
            e.setCustomName(getFullName(e));
        } else {
            e.setCustomName(getFullName(e));
            Disguise disguise = DisguiseAPI.getDisguise(e);
            disguise.getWatcher().setCustomName(getFullName(e));
        }
    }

    public static String getName(int id) {
        RPGMobsContainer monster = RPGMobsContainer.getRPGMobsContainer(id);
        String name = monster.getName();
        int level = monster.getLevel();
        name = "§4[" + level + "§4] §7" + name;
        return name;
    }
    public static String getFullName(Entity e) {
        MobInfo mobInfo = MobInfoManager.getMobInfo(e);
        int id = mobInfo.getId();
        RPGMobsContainer monster = RPGMobsContainer.getRPGMobsContainer(id);
        return String.format("%s§e[%d] §f%s%s%s§r",new Object[] { getBlood(e), Integer.valueOf(mobInfo.getLevel()), monster.getName(), getChampionName(e), getPercentHealth(e) });
    }
    public static String getPercentHealth(Entity e) {
        MobInfo mobInfo = MobInfoManager.getMobInfo(e);
        if (mobInfo.getHealth() == mobInfo.getMaxHealth())
            return "";
        String percent = getColoredPercentHealth(WorldUtils.getPercentByInts(mobInfo.getHealth(), mobInfo.getMaxHealth())) + "%";
        if (mobInfo.getManaMob() == 1 && mobInfo.getMana() != mobInfo.getMaxMana())
            return String.format(" §7[%s§7/§9%s§7]" , new Object[] { percent, Integer.valueOf(mobInfo.getMana()) });
        return String.format(" §7[%s§7]" , new Object[] { percent });
    }
    public static String getColoredPercentHealth(int percent) {
        if (percent >= 66)
            return "§a"+ percent;
        if (percent >= 33)
            return "§e"+ percent;
        return "§c" + percent;
    }
    public static String getBlood(Entity e) {
        MobInfo mobInfo = MobInfoManager.getMobInfo(e);
        String blood = ((mobInfo.getBlackB() == 1) ? "§0❤": "");
        return !blood.equals("") ? (blood + " ") : "";
    }
    public static String getChampionName(Entity e) {
        MobInfo mobInfo = MobInfoManager.getMobInfo(e);
        int champion = mobInfo.getChampion();
        if (champion == 1)
            return "";
        return " §eX" + champion;
    }



    public static void giveXpMob(MobInfo mobInfo, int id, int moblvl, int xp, Location loc, List<Player> players) throws SQLException {
        if (players.size() > 1)
            xp /= players.size();
        for (Player player : players) {
            if (player.isOnline()) {
                rpgplayer p = rpgplayer.getRPGPlayer(player);
                xp = calculate(p.getPLevel(), moblvl, xp);
            }
            if (player.isOnline()) {
                rpgplayer p = rpgplayer.getRPGPlayer(player);
                if (xp > 0)
                    p.addXP(xp);
                if (p.haveMob(String.valueOf(id))){
                    p.addMob(String.valueOf(id), mobInfo.getChampion());
                }



                if(p.getDailies().get("boolean") != null){
                    if(p.getDailies().get("boolean").equals(true)){
                        RPGMobsContainer monster = RPGMobsContainer.getRPGMobsContainer(id);
                        if(p.getDailies().get("dailiestype").equals(1)){
                            if(moblvl - p.getPLevel() >= 2 || p.getPLevel() -moblvl >= 2 ){
                                if(monster.getTypeMob() == 0){
                                    int mobs = Integer.parseInt(p.getDailies().get("mobs").toString());
                                    p.getDailies().put("mobs", mobs + 1);
                                    p.toPlayer().sendMessage(WorldUtils.worldName() + " §2Вы убили " + p.getDailies().get("mobs") + " мобов на ежедневное задание!");
                                    if(p.getDailies().get("mobs").equals(25)){
                                        p.toPlayer().sendTitle("§6Задание выполнено", "", 10, 70, 20);
                                        p.addXP(500);
                                        p.getDailies().put("completeDailies", true);
                                    }
                                }
                            }
                        }
                        if(p.getDailies().get("dailiestype").equals(2)){
                            if(moblvl - p.getPLevel() >= 2 || p.getPLevel() -moblvl >= 2){
                                if(monster.getTypeMob() == 1){
                                    int mobs = Integer.parseInt(p.getDailies().get("redmobs").toString());
                                    p.getDailies().put("redmobs", mobs + 1);
                                    p.toPlayer().sendMessage(WorldUtils.worldName() + " §2Вы убили " + p.getDailies().get("redmobs") + " красных мобов на ежедневное задание!");
                                    if(p.getDailies().get("redmobs").equals(10)){
                                        p.toPlayer().sendTitle("§6Задание выполнено", "", 10, 70, 20);
                                        p.getDailies().put("completeDailies", true);
                                        p.addXP(1500);
                                    }
                                }
                            }
                        }
                        if(p.getDailies().get("dailiestype").equals(3)){
                            if(moblvl - p.getPLevel() >= 2 || p.getPLevel() -moblvl >= 2){
                                if(monster.getTypeMob() == 2){
                                    int mobs = Integer.parseInt(p.getDailies().get("boss").toString());
                                    p.getDailies().put("boss", mobs + 1);
                                    p.toPlayer().sendMessage(WorldUtils.worldName() + " §2Вы убили " + p.getDailies().get("boss") + " боссов на ежедневное задание!");
                                    if(p.getDailies().get("boss").equals(3)){
                                        p.toPlayer().sendTitle("§6Задание выполнено", "", 10, 70, 20);
                                        p.getDailies().put("completeDailies", true);
                                        p.addXP(2500);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if(id == 1000){
            azerot.getInstance().setClonesAlchemist(azerot.getInstance().getClonesAlchemist() - 1);
        }


        if (xp > 0)
            if (players.size() == 1) {
                Hologramm.tempHolo("§6+" + Math.round(xp) + " §3XP" , "§8[§7" + players.get(0).getDisplayName() + "§8]", 2, loc);
            } else {
                Hologramm.tempHolo("§6+" + Math.round(xp) + " §3XP" , "§8[§7Каждому из " + players.size() + "§8]", 2, loc);
            }
    }
    public static int calculate(int playerLevel, int monsterLevel, int exp) {
        int difference = Math.abs(playerLevel - monsterLevel);
        return (difference <= 2) ? exp : ((difference == 3) ? (int)(exp * 0.875F) : ((difference <= 4) ? (int)(exp * 0.75F) : ((difference <= 5) ? (int)(exp * 0.565F) : ((difference <= 6) ? (int)(exp * 0.5F) : ((difference <= 7) ? (int)(exp * 0.435F) : ((difference <= 8) ? (int)(exp * 0.25F) : ((difference <= 9) ? (int)(exp * 0.125F) : 0)))))));
    }

    enum ScalingType {
        STRENGTH,
        AGILITY,
        INTELLIGENCE
    }

    public static List<ScalingType> generateScaling() {
        Random random = new Random();
        List<ScalingType> allScalings = Arrays.asList(ScalingType.STRENGTH, ScalingType.AGILITY, ScalingType.INTELLIGENCE);

        return random.nextDouble() < 0.1
                ? allScalings.stream()
                .distinct()
                .limit(2)
                .collect(Collectors.toList())
                : Arrays.asList(allScalings.get(random.nextInt(3)));
    }

    public static void drop(int id, Location loc) throws SQLException {


            String drop = RPGMobsContainer.getRPGMobsContainer(id).getDrop();
            if(drop.equals("null")){
                return;
            }
            String[] drops = drop.split(";");
            for (String i : drops) {
                String[] it = i.trim().split(" ");
                int dropid = Integer.parseInt(it[0]);
                int maxamount = Integer.valueOf(it[1]);
                double chance = Double.valueOf(it[2]);
                double r = Math.random();
                Random random = new Random();
                int type = RPGItemContainer.getRPGItemContainer(dropid).getType();
                int markpower = (int)(Math.random() * 999.0D + 1.0D);

                if ((float) r <= chance / 100.0D) {
                    ItemStack item = null;
                    if (RPGItemContainer.getRPGItemContainer((int) dropid).getHasMark() == 0) {
                        if(type == 2 || type == 3 ){
                            if(type == 2){
                                if(RPGItemContainer.getRPGItemContainer(dropid).getPatch() >= 3){
                                    int scaleAtStrength = 0;
                                    int scaleAtAgility = 0;
                                    int scaleAtIntelligence = 0;
                                    List<ScalingType> scalings = generateScaling();
                                    for (ScalingType scalingType : scalings) {
                                        switch (scalingType) {
                                            case STRENGTH:
                                                scaleAtStrength = ScaleDrop.getRandomScale();
                                                break;
                                            case AGILITY:
                                               scaleAtAgility = ScaleDrop.getRandomScale();
                                                break;
                                            case INTELLIGENCE:
                                                scaleAtIntelligence = ScaleDrop.getRandomScale();
                                                break;
                                        }
                                    }
                                    item = (new RPGItem()).getRPGItem((int) dropid, 0, maxamount> 1 ? ThreadLocalRandom.current().nextInt(1, maxamount) : 1,  0, markpower,scaleAtStrength,scaleAtAgility, scaleAtIntelligence,null);

                                }else{
                                    item = (new RPGItem()).getRPGItem((int) dropid, 0, maxamount> 1 ? ThreadLocalRandom.current().nextInt(1, maxamount) : 1,  0, markpower,0,0, 0,null);
                                }

                            }else{
                                item = (new RPGItem()).getRPGItem((int) dropid, 0, maxamount> 1 ? ThreadLocalRandom.current().nextInt(1, maxamount) : 1,  0, markpower,0,0, 0,null);
                            }
                        }else{
                            if(dropid == 150){
                                long time = Bukkit.getWorld("world").getTime();
                                if(time < 12300){
                                    item = (new RPGItem()).getRPGItem((int) dropid, 0,maxamount> 1 ? ThreadLocalRandom.current().nextInt(1, maxamount) : 1,  0, 0, 0,0, 0,null);

                                }else{
                                    item = (new RPGItem()).getRPGItem((int) dropid, 0,(maxamount> 1 ? ThreadLocalRandom.current().nextInt(1, maxamount) : 1) * 2,  0, 0, 0,0, 0,null);

                                }

                            }else{
                                item = (new RPGItem()).getRPGItem((int) dropid, 0,maxamount> 1 ? ThreadLocalRandom.current().nextInt(1, maxamount) : 1,  0, 0, 0,0, 0,null);

                            }
                        }
                    } else {
                        item = (new RPGItem()).getRPGItem((int) dropid, 0, maxamount > 1 ? ThreadLocalRandom.current().nextInt(1, maxamount) : 1,  ThreadLocalRandom.current().nextInt(1, 7), markpower, 0,0, 0,null);
                    }
                    RPGItemContainer RPGI = RPGItemContainer.getRPGItemContainer(dropid);
                    int quality = RPGI.getQuality();
                    Item en = loc.getWorld().dropItem(loc, item);
                    Location location = loc;
                    double radius = 15D;

                    for(Entity entity : location.getWorld().getNearbyEntities(location, radius, radius, radius)){
                        if(entity.getType() == EntityType.PLAYER){

                            rpgplayer pl = rpgplayer.getRPGPlayer((Player) entity);
                            Scoreboard scoreboard = pl.getScoreboard();
                            Team white = scoreboard.getTeam("white");
                            Team green = scoreboard.getTeam("green");
                            Team blue = scoreboard.getTeam("blue");
                            Team yellow = scoreboard.getTeam("yellow");
                            Team gold = scoreboard.getTeam("gold");
                            Team purple = scoreboard.getTeam("purple");
                            Team red = scoreboard.getTeam("red");

                            switch (quality){
                                case 1:
                                    white.addEntry(en.getUniqueId().toString());
                                    en.setGlowing(true);
                                    break;
                                case 2:
                                    green.addEntry(en.getUniqueId().toString());
                                    en.setGlowing(true);
                                    break;
                                case 3:
                                    blue.addEntry(en.getUniqueId().toString());
                                    en.setGlowing(true);
                                    break;
                                case 4:
                                    purple.addEntry(en.getUniqueId().toString());
                                    en.setGlowing(true);
                                    break;
                                case 5:
                                    red.addEntry(en.getUniqueId().toString());
                                    en.setGlowing(true);
                                    break;
                                case 6:
                                    yellow.addEntry(en.getUniqueId().toString());
                                    en.setGlowing(true);
                                    break;

                            }

                        }
                    }

                }
            }
    }

    public static void respawnMobsJapanEvent(final String id, final String number, int time, Location loc){
            (new BukkitRunnable() {
                public void run() {
                    try {
                        if(id == "1000"){
                            WorldUtils.spawnMob("1000", loc, "0");
                        }
                        if(id == "1001"){
                            WorldUtils.spawnMob("1001", loc, "0");
                        }
                        if(id == "1002"){
                            WorldUtils.spawnMob("1002", loc, "0");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).runTaskLater((Plugin)azerot.getInstance(), 20L * time);
    }

    public static void respawnMob(final String id, final String number, int time) {
        if (!number.equals("0"))
            (new BukkitRunnable() {
                public void run() {
                    try {

                        WorldUtils.spawnMob(id, number);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).runTaskLater((Plugin)azerot.getInstance(), 20L * time);
    }

    public static void mobToHome(final LivingEntity e) {
        final MobInfo mobInfo = MobInfoManager.getMobInfo((Entity)e);
        (new BukkitRunnable() {
            public void run() {
                if (e.isDead()) {
                    cancel();
                    return;
                }
                if (mobInfo.getSpawnLocation().distance(e.getLocation()) > 17.0D && mobInfo.getLeveling() == 0){
                    if(mobInfo.getId() == 60){
                        Location loc = new Location(e.getWorld(), -4429, 166, 3944);
                        if(loc.distanceSquared(e.getLocation()) <= 5*5){
                            Bukkit.broadcastMessage("Босс находится в комнате");
                            return;
                        }else{
                            MobSystem.customTarget((Entity)e, null);
                            e.teleport(mobInfo.getSpawnLocation());
                        }
                    }else{
                        if(mobInfo.getId() == 85){

                        }else{
                            MobSystem.customTarget((Entity)e, null);
                            e.teleport(mobInfo.getSpawnLocation());
                        }
                    }
                }
            }
        }).runTaskTimer((Plugin)azerot.getInstance(), 0L, 240L);
        (new BukkitRunnable() {
            public void run() {
                if (e.isDead()) {
                    cancel();
                    return;
                }
                List<Player> players = MobSystem.getNearbyPlayers((LivingEntity) e, mobInfo.getMobRange());
                if ((MobSystem.getTarget((Entity)e) == null || mobInfo.getTargetCount() == 17) && !players.isEmpty() && mobInfo.getAggressive() == 0)
                    MobSystem.customTarget((Entity)e, (Entity)players.get(0));
                if (MobSystem.getTarget((Entity)e) != null && !MobSystem.getNearbyPlayers((LivingEntity) e, 17.0D).contains(MobSystem.getTarget((Entity)e))){
                    if(mobInfo.getId() == 85){
                        if(MobSystem.getTarget(e).getLocation().distance(mobInfo.getSpawnLocation()) >= 30 || MobSystem.getTarget(e).getGameMode() == GameMode.CREATIVE){
                            MobSystem.customTarget((Entity)e, null);
                        }

                    }else{
                        MobSystem.customTarget((Entity)e, null);
                    }
                }
                if (mobInfo.getLastAttackTime() != 0L) {
                    if(mobInfo.getId() == 60){
                        if (MobSystem.getTarget((Entity)e) == null && mobInfo.getHealth() != mobInfo.getMaxHealth() &&
                                mobInfo.getLastAttackTime() + 10000L < System.currentTimeMillis())
                            MobSystem.healMob((Entity)e);
                        if (mobInfo.getLastAttackTime() + 50000L < System.currentTimeMillis())
                            MobSystem.healMob((Entity)e);
                    }
                    if (MobSystem.getTarget((Entity)e) == null && mobInfo.getHealth() != mobInfo.getMaxHealth() &&
                            mobInfo.getLastAttackTime() + 3000L < System.currentTimeMillis() && mobInfo.getId() != 60)
                        MobSystem.healMob((Entity)e);
                    if (mobInfo.getLastAttackTime() + 10000L < System.currentTimeMillis() && mobInfo.getId() != 60)
                        MobSystem.healMob((Entity)e);
                }
            }
        }).runTaskTimer((Plugin)azerot.getInstance(), 30L, 30L);
    }
    public static Player getTarget(Entity e) {
        if (!(((Creature)e).getTarget() instanceof Player))
            return null;
        return (Player)((Creature)e).getTarget();
    }


    public static void healMob(Entity e) {
        MobInfo mobInfo = MobInfoManager.getMobInfo(e);
        if (mobInfo.getHealth() == mobInfo.getMaxHealth())
            return;
        damagers.remove(e.getUniqueId());
        mobInfo.setLastAttackTime(0L);
        (new BukkitRunnable() {
            public void run() {
                if(MobSystem.getTarget(e) == null){
                    if(mobInfo.getHealth() == mobInfo.getMaxHealth()){
                        cancel();
                    }else{
                        setHealth(e, mobInfo.getHealth() + Math.round(((float) mobInfo.getMaxHealth() / 100) * 15));
                    }
                 }else{
                    cancel();
                }
                }

        }).runTaskTimer((Plugin)azerot.getInstance(), 0L, 30L);
        setMana(e, mobInfo.getMaxMana());

    }
    public static void setMana(Entity e, int mana) {
        MobInfo mobInfo = MobInfoManager.getMobInfo(e);
        mana = Math.min(mana, mobInfo.getMaxHealth());
        mobInfo.setMana(mana);
        setName(e);
    }

    public static void setMaxMana(Entity e, int mana) {
        MobInfo mobInfo = MobInfoManager.getMobInfo(e);
        mobInfo.setMaxMana(mana);
        setName(e);
    }

    public static List<Player> getNearbyPlayers(LivingEntity entity, double range, boolean a) {
        ArrayList<Player> nearby = new ArrayList<>();
        for (Entity e : entity.getNearbyEntities(range, range, range)) {
            if (!(e instanceof Player) ||
                    !((Player)e).getGameMode().equals(GameMode.SURVIVAL) ||
                    rpgplayer.getRPGPlayer((Player)e) == null)
                continue;
            nearby.add((Player)e);
        }
        return nearby;
    }

    public static List<Player> getNearbyPlayers(LivingEntity entity, double range) {
        return getNearbyPlayers(entity, range, true);
    }
}
