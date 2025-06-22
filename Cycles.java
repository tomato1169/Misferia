package azerot.azerot;

import azerot.azerot.Quest.Quest;
import azerot.azerot.Quest.questStageForScoreBoard;
import azerot.azerot.events.PVP_JAPAN_Event;
import com.comphenix.packetwrapper.*;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.*;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.PropertyMap;
import com.sk89q.worldguard.internal.flywaydb.core.internal.util.PlaceholderReplacer;

import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;
import org.bukkit.scoreboard.Scoreboard;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Cycles {


    public static void actionbar(final Player player) {
        (new BukkitRunnable() {
            public void run() {
                try {
                    if (!player.isOnline()) {
                        cancel();
                        return;
                    }
                    ActionBar.actionbar(player, 0, null);
                } catch (IndexOutOfBoundsException exception) {
                    cancel();
                    return;
                }
            }
        }).runTaskTimer((Plugin)azerot.getPlugin(azerot.class), 0L, 30L);
    }

    public static void nameforhead(final Player player) {
        Random random = new Random();
        int[] ids = new int[] { random.nextInt(0X7FFFFFFF), random.nextInt(0X7FFFFFFF) };
        int entityID = ids[1];
        int slimeID = ids[0];
        rpgplayer pl = rpgplayer.getRPGPlayer(player);
        Location loc = player.getLocation();
        WrapperPlayServerSpawnEntityLiving spawnPacket = new WrapperPlayServerSpawnEntityLiving();
        WrapperPlayServerSpawnEntityLiving spawnSlimePacket = new WrapperPlayServerSpawnEntityLiving();
        WrapperPlayServerEntityMetadata metadataPacket = new WrapperPlayServerEntityMetadata();
        WrapperPlayServerEntityMetadata metadataSlimePacket = new WrapperPlayServerEntityMetadata();
        WrapperPlayServerEntityDestroy destroyPacket = new WrapperPlayServerEntityDestroy();
        WrapperPlayServerMount mountPacket = new WrapperPlayServerMount();
        WrapperPlayServerMount standMounPacket = new WrapperPlayServerMount();

        spawnSlimePacket.setEntityID(slimeID);
        spawnSlimePacket.setType(EntityType.SLIME);
        spawnSlimePacket.setX(loc.getX());
        spawnSlimePacket.setY(loc.getY());
        spawnSlimePacket.setZ(loc.getZ());

        destroyPacket.setEntityIds(new int[] {entityID, slimeID});

        spawnPacket.setEntityID(entityID);
        spawnPacket.setType(EntityType.ARMOR_STAND);
        spawnPacket.setX(loc.getX());
        spawnPacket.setY(loc.getY());
        spawnPacket.setZ(loc.getZ());


        metadataPacket.setEntityID(entityID);
        metadataSlimePacket.setEntityID(slimeID);

        WrappedDataWatcher dataWatcher = new WrappedDataWatcher(metadataPacket.getMetadata());
        WrappedDataWatcher slimeWatcher = new WrappedDataWatcher(metadataSlimePacket.getMetadata());

        WrappedDataWatcher.WrappedDataWatcherObject isInvisibleIndex = new WrappedDataWatcher.WrappedDataWatcherObject(0, WrappedDataWatcher.Registry.get(Byte.class));
        WrappedDataWatcher.WrappedDataWatcherObject customName = new WrappedDataWatcher.WrappedDataWatcherObject(2, WrappedDataWatcher.Registry.get(String.class));
        WrappedDataWatcher.WrappedDataWatcherObject nameVisible = new WrappedDataWatcher.WrappedDataWatcherObject(3, WrappedDataWatcher.Registry.get(Boolean.class));
        WrappedDataWatcher.WrappedDataWatcherObject small = new WrappedDataWatcher.WrappedDataWatcherObject(11, WrappedDataWatcher.Registry.get(Byte.class));
        WrappedDataWatcher.WrappedDataWatcherObject size = new WrappedDataWatcher.WrappedDataWatcherObject(12, WrappedDataWatcher.Registry.get(Integer.class));

        slimeWatcher.setObject(isInvisibleIndex, (byte) 0x20);
        slimeWatcher.setObject(size, 1);
        metadataSlimePacket.setMetadata(slimeWatcher.getWatchableObjects());



        mountPacket.setEntityID(player.getEntityId());
        mountPacket.setPassengerIds(new int[] {ids[0]});

        standMounPacket.setEntityID(ids[0]);
        standMounPacket.setPassengerIds(new int[] {ids[1]});
       /* for(Player p : Bukkit.getOnlinePlayers()) {
            if(!p.getName().equals(player.getName())){
                spawnPacket.sendPacket(p);
                spawnSlimePacket.sendPacket(p);

                metadataSlimePacket.sendPacket(p);
                metadataPacket.sendPacket(p);

                mountPacket.sendPacket(p);

                standMounPacket.sendPacket(p);
            }
        } */


        (new BukkitRunnable() {
            public void run() {
                try {
                    if (!player.isOnline()) {
                        for(Player ps : Bukkit.getOnlinePlayers()){
                            if(!ps.getName().equals(player.getName())){
                                destroyPacket.sendPacket(ps);
                            }
                        }
                        cancel();
                        return;
                    }
                    dataWatcher.setObject(isInvisibleIndex, (byte) 0x20);
                    dataWatcher.setObject(customName, "§c❤ §c§l"+ Math.round(pl.getHealth()) + "§7§l/§c§l" + Math.round(pl.getMaxHealth()) + " §7" + player.getName());
                    dataWatcher.setObject(nameVisible, true);
                    dataWatcher.setObject(small, (byte) (0x01 | 0x10 | 0x08));
                    metadataPacket.setMetadata(dataWatcher.getWatchableObjects());
                    if(!player.getWorld().getName().equals("world")){
                        return;
                    }
                    for(Player ps : Bukkit.getOnlinePlayers()) {
                        if (player.getWorld().getName().equals("world") || player.getWorld().getName().equals("pvp_event")) {
                            if (!ps.getName().equals(player.getName())) {
                                if (ps.getLocation().distance(pl.toPlayer().getLocation()) <= 50) {
                                    if(player.getGameMode() == GameMode.SPECTATOR){
                                        destroyPacket.sendPacket(ps);
                                        pl.getPacketUse().remove(ps);
                                        return;
                                    }
                                    if (pl.getPacketUse().get(ps) == null) {
                                        spawnPacket.sendPacket(ps);
                                        spawnSlimePacket.sendPacket(ps);
                                        pl.getPacketUse().put(ps, true);
                                    }
                                    metadataSlimePacket.sendPacket(ps);
                                    metadataPacket.sendPacket(ps);

                                    mountPacket.sendPacket(ps);

                                    standMounPacket.sendPacket(ps);
                                } else {
                                    destroyPacket.sendPacket(ps);
                                    pl.getPacketUse().remove(ps);
                                }
                            }
                        }
                    }
                } catch (IndexOutOfBoundsException exception) {
                    cancel();
                    return;
                }
            }
        }).runTaskTimer((Plugin)azerot.getPlugin(azerot.class), 0L, 1L);
    }

    public static void spawnBeaconBeam(Location location, Player player) {
        
        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.END_ROD, true,
                (float) location.getX(), (float) location.getY(), (float) location.getZ(),
                0, 0, 0, 0, 1);

        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }


    public static void updateScoreBoard(Player p, String prefix, String suffix) {
        rpgplayer player = rpgplayer.getRPGPlayer(p);
        Scoreboard sb = player.getScoreboard();
        if(sb.getObjective("test") == null){
            sb.registerNewObjective("test", String.valueOf(new ScoreboardBaseCriteria("test")));
        }
        Objective obj = sb.registerNewObjective("Stats", "dummy");
        obj.setDisplayName("§5Misferia");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        (new BukkitRunnable() {
            public void run() {
                try {
                    if (!p.isOnline()) {
                        cancel();
                        return;
                    }
                    for(String entry : sb.getEntries()){
                        sb.resetScores(entry);
                    }
                    if(player.isPVP_Japan_active()){
                        Score time = obj.getScore("§7       " + LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()),
                                ZoneId.of("Europe/Moscow")).format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                        time.setScore(16);
                        Score space = obj.getScore("");
                        space.setScore(15);
                        Score team = obj.getScore("" + (player.getPVP_JAPAN_Event_Team().equals("red") ? "Красная команда: " + PVP_JAPAN_Event.getGames("1-1").getFullPOintsRed() : "Синяя команда: " + PVP_JAPAN_Event.getGames("1-1").getFullPOintsBlue()));
                        team.setScore(14);
                        if(player.getPVP_JAPAN_Event_Team().equals("red")){
                            Bukkit.broadcastMessage("Инфа по команде: " + PVP_JAPAN_Event.getGames("1-1").getRedTeam().toString());
                            HashMap<String, Integer> listPoints = new HashMap<>(PVP_JAPAN_Event.getGames("1-1").getRedTeam());
                            LinkedHashMap<String, Integer> sortedByPointsDesc = listPoints.entrySet()
                                    .stream()
                                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                                    .collect(Collectors.toMap(
                                            Map.Entry::getKey,
                                            Map.Entry::getValue,
                                            (e1, e2) -> e1,
                                            LinkedHashMap::new
                                    ));

                            int i = 1;
                            for (Map.Entry<String, Integer> entry : sortedByPointsDesc.entrySet()) {
                                Score j = obj.getScore("" + entry.getKey() + ": " + entry.getValue());
                                switch (i){
                                    case 1:
                                        j.setScore(13);
                                        break;
                                    case 2:
                                        j.setScore(12);
                                        break;
                                    case 3:
                                        j.setScore(11);
                                        break;
                                }
                                i++;
                            }

                        }
                    }else{
                        Score time = obj.getScore("§7" + LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()),
                                ZoneId.of("Europe/Moscow")).format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                        time.setScore(16);
                        Score space = obj.getScore("");
                        space.setScore(15);
                        double xp = (double) player.getXP() / player.getLXp();
                        double quest = (double) player.getFinishQuest() / azerot.qsts;
                        String color = "";
                        if(Math.floor(xp * 100) < 30)
                            color = "§c";
                        if(Math.floor(xp * 100) < 60)
                            color = "§e";
                        if(Math.floor(xp * 100) > 60 && Math.floor(xp * 100) < 100)
                            color = "§2";
                        Score space2 = obj.getScore("§2Прогресс:");
                        space2.setScore(14);
                        Score level = obj.getScore("    §7Уровень: " + player.getLevel() + " §7("+ color + Math.floor(xp * 100) + "%)");
                        level.setScore(13);
                        Score XP = obj.getScore("    §7Опыт: " +  player.getXP() + "§7/" + player.getLXp());
                        XP.setScore(12);
                        Score quests = obj.getScore("    §7Задания: " + Math.floor(quest * 100) + "%");
                        quests.setScore(11);
                        Score space1 = obj.getScore("");
                        space1.setScore(10);


                        if(player.getScoreBoardInfo().equals("quests")){
                            List<String> list = new ArrayList<>();
                            list.addAll(questStageForScoreBoard.questStageBoard(p, player.getQuestLook()));
                            if(!list.isEmpty()){
                                for(int i = 1; i < list.size() ; i++){
                                    Score j = obj.getScore(list.get(i));
                                    j.setScore(list.size() - i);
                                }
                            }
                        }

                        if (player.getSpellcooldown().get("SecondChance") != null & player.getSpellcooldown().get("SecondChance") > System.currentTimeMillis()) {
                            int second = (int) ((player.getSpellcooldown().get("SecondChance") - System.currentTimeMillis()) / 1000L);
                            int minutes = second / 60;
                            Score SecondChance = obj.getScore("    §7Второй шанс: " + minutes + "§7:" + (second % 60 > 10 ? second % 60 : "0" + second % 60));
                            SecondChance.setScore(5);

                        }
                    }
                    p.setScoreboard(sb);

                } catch (IndexOutOfBoundsException exception) {
                    cancel();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }).runTaskTimer((Plugin)azerot.getPlugin(azerot.class), 0L, 20L);
    }

    public static void regeneration(final Player player) {
        (new BukkitRunnable() {
            public void run() {
                try {
                    if (!player.isOnline()) {
                        cancel();
                        return;
                    }
                    rpgplayer p = rpgplayer.getRPGPlayer(player);
                    int regeneration = p.getRegeneration();
                    int trueregeneration = p.getTrueRegeneration();
                    int manaregeneration = p.getManaRegeneration();

                    if (p.getBuff().get("CT") == null || p.getBuff().get("CT") <= System.currentTimeMillis()) {
                        RPGDamageble.setHealth((Entity) player, Math.min(Math.round((p.getHealth() + regeneration)), p.getMaxHealth()), null, null);
                    }
                    RPGDamageble.setMana((Entity)player, Math.min(Math.round((p.getMana() + manaregeneration)), p.getMaxMana()));
                    if (p.getTrueRegeneration() > 0)
                        RPGDamageble.setHealth((Entity)player, Math.min(Math.round((p.getHealth() + trueregeneration)), p.getMaxHealth()), null, null);
                    ActionBar.actionbar(player, 0, null);
                } catch (Exception exception ) {
                    exception.printStackTrace();
                    cancel();
                    return;
                }
            }
        }).runTaskTimer((Plugin)azerot.getPlugin(azerot.class), 0L, 30L);
    }

    public static void cycles() {
        (new BukkitRunnable() {
            public void run() {
                for (Player player : Bukkit.getServer().getOnlinePlayers())
                    player.kickPlayer("§cПерезагрузка!");
                            Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "stop");
            }
        }).runTaskLater((Plugin)azerot.getInstance(), 144000L);
        (new BukkitRunnable() {
            public void run() {
                Bukkit.broadcastMessage(WorldUtils.worldName() + " §cСервер будет перезагружен через минуту!" );
            }
        }).runTaskLater((Plugin)azerot.getInstance(), 142800L);
        (new BukkitRunnable() {
            public void run() {
                Bukkit.broadcastMessage(WorldUtils.worldName() + " §cСервер будет перезагружен через 5 минут!" );
            }
        }).runTaskLater((Plugin)azerot.getInstance(), 138000L);
        (new BukkitRunnable() {
            public void run() {
                try {
                    Cycles.saveall();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).runTaskTimer((Plugin)azerot.getInstance(), 0L, 18000L);
        (new BukkitRunnable() {
            public void run() {
                Cycles.broadcast(0);
                Cycles.oreRespawn();
            }
        }).runTaskTimer((Plugin)azerot.getInstance(), 0L, 6000L);
    }

    public static void clearItems() {
        (new BukkitRunnable() {
            public void run() {
                WorldUtils.clearDrop();
                cancel();
                clearItems();
            }
            }).runTaskLater((Plugin)azerot.getInstance(), 18000L);
        (new BukkitRunnable() {
            public void run() {
                Bukkit.broadcastMessage(WorldUtils.worldName() + " §cОчистка будет через минуту!" );
            }
        }).runTaskLater((Plugin)azerot.getInstance(), 16800L);
        (new BukkitRunnable() {
            public void run() {
                Bukkit.broadcastMessage(WorldUtils.worldName() + " §cОчистка будет через 5 минут!" );
            }
        }).runTaskLater((Plugin)azerot.getInstance(), 12000L);
    }

    public static void saveall() throws IOException {
        Bukkit.broadcastMessage(WorldUtils.worldName() + " §aВсё было сохранено!" );
                azerot.savePlayer();
    }
    public static void oreRespawn() {
        File file = new File(azerot.getInstance().getDataFolder() + File.separator + "orerespawns.yml");
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        int list = 1;
        int time = 0;
        while (yamlConfiguration.getString((new StringBuilder(String.valueOf(list))).toString()) != null) {
            List<String> coalore = yamlConfiguration.getStringList(list + ".spots");
            for (String i : coalore) {
                time++;
                String[] it = i.trim().split(" ");
                Location loc = new Location(Bukkit.getServer().getWorld("world"), Integer.valueOf(Integer.valueOf(it[0]).intValue()).intValue(), Integer.valueOf(Integer.valueOf(it[1]).intValue()).intValue(), Integer.valueOf(Integer.valueOf(it[2]).intValue()).intValue(), 0.0F, 0.0F);
                int finalList = list;
                Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)azerot.getInstance(), () -> loc.getBlock().setType(Material.getMaterial(yamlConfiguration.getString(String.valueOf(finalList) + ".block"))), (20 * time));
            }
            list++;
        }
    }

    public static void broadcast(int number) {
        String text;
        File file = new File(azerot.getInstance().getDataFolder() + File.separator + "config.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        if (number == 0) {
            text = randombroadcast();
        } else {
            text = config.getString("broadcast." + number);
        }
        Bukkit.broadcastMessage(WorldUtils.worldName() + " §aСовет: §f" + text);
    }

    public static String randombroadcast() {
        File file = new File(azerot.getInstance().getDataFolder() + File.separator + "config.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        int i = 1;
        while (config.getString("broadcast." + i) != null)
            i++;
        i = (int)(Math.random() * i + 1.0D);
        i = Math.max(1, i - 1);
        return config.getString("broadcast." + i);
    }
}
