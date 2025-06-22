package azerot.azerot.Cutscene;

import azerot.azerot.Quest.questScript;
import azerot.azerot.WorldUtils;
import azerot.azerot.azerot;
import azerot.azerot.rpgplayer;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Biome;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class DeadIsland {

    public static void CutSceneDeadIsland(Player p) throws SQLException {
        Random random = new Random();
        Location startloc = p.getLocation();
        int[] ids = new int[]{random.nextInt(0X7FFFFFFF), random.nextInt(0X7FFFFFFF)};
        int entityID = ids[1];
        PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
        p.getWorld().setBiome(-125, 41, Biome.PLAINS);
        List<Location> vanessaList = new ArrayList<>(azerot.pathRecord.get("vanessa"));
        List<Location> CameraInDeadIslandFirst = new ArrayList<>(azerot.pathRecord.get("CameraInDeadIslandFirst"));
        List<Location> vanessaList1 = new ArrayList<>(azerot.pathRecord.get("vanessa1"));
        List<Location> mobDeadIsland = new ArrayList<>(azerot.pathRecord.get("mobDeadIsland"));
        List<Location> mobDeadIsland1 = new ArrayList<>(azerot.pathRecord.get("mobDeadIsland1"));
        List<Location> mobDeadIsland2 = new ArrayList<>(azerot.pathRecord.get("mobDeadIsland2"));
        List<Location> CameraInDeadIslandSecond = new ArrayList<>(azerot.pathRecord.get("CameraInDeadIslandSecond"));
        List<Location> vanessaStart = new ArrayList<>(azerot.pathRecord.get("vanessaStart"));
        List<Location> mobActFirst = new ArrayList<>(azerot.pathRecord.get("mobActFirst"));
        List<Location> CameraInDeadIslandThird = new ArrayList<>(azerot.pathRecord.get("CameraInDeadIslandThird"));

        Location loc = new Location(Bukkit.getWorld("world"), -3260, 72, 1813);
        p.teleport(new Location(Bukkit.getWorld("deadIsland"), -127, 105, 40));
        p.setGameMode(GameMode.SPECTATOR);
        final int[] stageCutscene = {0};

        WorldServer s = ((CraftWorld)Bukkit.getWorld("deadIsland")).getHandle();
        EntityArmorStand stand = new EntityArmorStand(s);
        stand.setInvisible(true);
        stand.setLocation(CameraInDeadIslandThird.get(0).getX(), CameraInDeadIslandThird.get(0).getY(), CameraInDeadIslandThird.get(0).getZ(),CameraInDeadIslandThird.get(0).getYaw(),CameraInDeadIslandThird.get(0).getPitch());
        stand.setCustomNameVisible(false);
        stand.setNoGravity(true);
        stand.setSmall(false);
        stand.setInvisible(true);


        EntityZombie vanessa = new EntityZombie(s);
        vanessa.setLocation(vanessaStart.get(0).getX(), vanessaStart.get(0).getY(), vanessaStart.get(0).getZ(), vanessaStart.get(0).getYaw(),vanessaStart.get(0).getPitch() );
        vanessa.setInvisible(false);
        DisguiseAPI.disguiseEntity(vanessa.getBukkitEntity(), (Disguise) new PlayerDisguise("Linss_"));
        Disguise disguiseVanessa = DisguiseAPI.getDisguise(vanessa.getBukkitEntity());
        disguiseVanessa.getWatcher().setCustomNameVisible(false);

        EntityZombie darkKnight = new EntityZombie(s);
        darkKnight.setSilent(true);
        darkKnight.setInvisible(false);
        darkKnight.setLocation(mobActFirst.get(0).getX(), mobActFirst.get(0).getY(), mobActFirst.get(0).getZ(), mobActFirst.get(0).getYaw(), mobActFirst.get(0).getPitch());
        DisguiseAPI.disguiseEntity(darkKnight.getBukkitEntity(), (Disguise) new PlayerDisguise("Jetlag918"));


        p.getWorld().setStorm(true);
        p.getWorld().setTime(17000L);
        p.getWorld().setWeatherDuration(10000000);


        final int[] i = {0};
        rpgplayer pl = rpgplayer.getRPGPlayer(p);
        pl.setWatchingCutscene(true);


        Location startLoc = p.getLocation();
        final EntityZombie darkKnight5 = new EntityZombie((World)s);
        final EntityZombie darkKnight4 = new EntityZombie((World)s);
        final EntityZombie darkKnight3 = new EntityZombie((World)s);
        final EntityZombie darkKnight6 = new EntityZombie((World)s);
        final EntityZombie darkKnight2 = new EntityZombie((World)s);
        final EntityZombie darkKnight1 = new EntityZombie((World)s);

        DisguiseAPI.disguiseEntity(darkKnight1.getBukkitEntity(), (Disguise) new PlayerDisguise("Jetlag918"));
        DisguiseAPI.disguiseEntity(darkKnight2.getBukkitEntity(), (Disguise) new PlayerDisguise("Jetlag918"));

        DisguiseAPI.disguiseEntity(darkKnight3.getBukkitEntity(), (Disguise) new PlayerDisguise("Jetlag918"));

        DisguiseAPI.disguiseEntity(darkKnight4.getBukkitEntity(), (Disguise) new PlayerDisguise("Jetlag918"));
        DisguiseAPI.disguiseEntity(darkKnight5.getBukkitEntity(), (Disguise) new PlayerDisguise("Jetlag918"));

        DisguiseAPI.disguiseEntity(darkKnight6.getBukkitEntity(), (Disguise) new PlayerDisguise("Jetlag918"));


        final EntityZombie tempter = new EntityZombie((World)s);
        DisguiseAPI.disguiseEntity(tempter.getBukkitEntity(), (Disguise) new PlayerDisguise("MagnificentN0ir"));

        final EntityZombie zombie = new EntityZombie((World)s);
        final EntityZombie zombie1 = new EntityZombie((World)s);
        final EntityZombie zombie2 = new EntityZombie((World)s);

        DisguiseAPI.disguiseEntity(zombie1.getBukkitEntity(), (Disguise) new PlayerDisguise("qadrix"));
        DisguiseAPI.disguiseEntity(zombie2.getBukkitEntity(), (Disguise) new PlayerDisguise("qadrix"));
        DisguiseAPI.disguiseEntity(zombie.getBukkitEntity(), (Disguise) new PlayerDisguise("qadrix"));
        try {
            PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving((EntityLiving) stand);
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
            PacketPlayOutSpawnEntityLiving packetVillager = new PacketPlayOutSpawnEntityLiving((EntityLiving) vanessa);
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetVillager);

            PacketPlayOutSpawnEntityLiving packetDarkKnight = new PacketPlayOutSpawnEntityLiving((EntityLiving) darkKnight);
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetDarkKnight);
            pl.getSpawnFakeEntity().put(stand.getBukkitEntity().getEntityId(), stand);
            pl.getSpawnFakeEntity().put(vanessa.getBukkitEntity().getEntityId(), vanessa);
            pl.getSpawnFakeEntity().put(darkKnight.getBukkitEntity().getEntityId(), darkKnight);
        }catch (Exception exception) {
            exception.printStackTrace();
        }

        int ticks[] = {0};
        p.teleport(stand.getBukkitEntity().getLocation());
        p.setSpectatorTarget(stand.getBukkitEntity());

        final HashMap<Integer, Entity> clone = (HashMap<Integer, Entity>) pl.getSpawnFakeEntity().clone();


        (new BukkitRunnable() {
            public void run() {

                if(!p.isOnline()){
                    cancel();
                }
                if(pl.isSkipCutscene()){
                    p.setGameMode(GameMode.SURVIVAL);
                    try {
                        pl.takeQuest("2");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    pl.setWatchingCutscene(false);

                    if(!pl.getSpawnFakeEntity().isEmpty()){
                        for(Integer value : clone.keySet()){
                            PacketPlayOutEntityDestroy packDeleteSlime4 = new PacketPlayOutEntityDestroy(clone.get(value).getBukkitEntity().getEntityId());
                            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packDeleteSlime4);
                        }
                        pl.getSpawnFakeEntity().clear();
                    }

                    p.teleport(new Location(Bukkit.getWorld("world"), -3884,68,2700,2.2F,3.0F));
                    cancel();
                }
                if(stageCutscene[0] == 0){
                    if(ticks[0] == 0){
                        p.sendMessage("§7[§6???§7] Как-то странно...");
                        p.sendMessage("§7[§6???§7] Куда подевались все монстры?");
                    }
                    if (ticks[0] >= 15){
                        if(ticks[0] == 45){
                            p.sendMessage("§7[§6???§7] А вот и монстр, надо проследить за ним");
                        }
                        if(!CameraInDeadIslandThird.isEmpty()){
                            stand.getBukkitEntity().teleport(CameraInDeadIslandThird.get(0));
                            PacketPlayOutEntityTeleport packetPlayOutEntityTeleport = new PacketPlayOutEntityTeleport(stand);
                            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetPlayOutEntityTeleport);

                            CameraInDeadIslandThird.remove(0);
                        }else {
                            if(stageCutscene[0] == 0){
                                stageCutscene[0]++;
                            }
                        }
                        if(!mobActFirst.isEmpty()) {
                            darkKnight.getBukkitEntity().teleport(mobActFirst.get(0));
                            PacketPlayOutEntityTeleport packetPlayOutEntityTeleport = new PacketPlayOutEntityTeleport(darkKnight);
                            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packetPlayOutEntityTeleport);
                            Location npcloc1 = darkKnight.getBukkitEntity().getLocation();
                            float yaw = npcloc1.getYaw();
                            float pitch = npcloc1.getPitch();
                            connection.sendPacket(new PacketPlayOutEntity.PacketPlayOutEntityLook(darkKnight.getId(), (byte) ((yaw%360.)*256/360), (byte) ((pitch%360.)*256/360), false));
                            connection.sendPacket(new PacketPlayOutEntityHeadRotation(darkKnight, (byte) ((yaw%360.)*256/360)));
                            mobActFirst.remove(0);
                        }else {
                            if(stageCutscene[0] == 0){
                                stageCutscene[0]++;
                            }

                        }
                        if(!vanessaStart.isEmpty()){
                            vanessa.getBukkitEntity().teleport(vanessaStart.get(0));
                            PacketPlayOutEntityTeleport packetPlayOutEntityTeleport = new PacketPlayOutEntityTeleport(vanessa);
                            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetPlayOutEntityTeleport);
                            Location npcloc1 = vanessa.getBukkitEntity().getLocation();
                            float yaw = npcloc1.getYaw();
                            float pitch = npcloc1.getPitch();
                            connection.sendPacket(new PacketPlayOutEntity.PacketPlayOutEntityLook(vanessa.getId(), (byte) ((yaw%360.)*256/360), (byte) ((pitch%360.)*256/360), false));
                            connection.sendPacket(new PacketPlayOutEntityHeadRotation(vanessa, (byte) ((yaw%360.)*256/360)));
                            vanessaStart.remove(0);
                        }else {
                            if(stageCutscene[0] == 0){
                                stageCutscene[0]++;
                            }
                        }
                    }
                }
                if(stageCutscene[0] == 1){
                    if(ticks[0] == 386){
                        PacketPlayOutEntityDestroy packDelete3 = new PacketPlayOutEntityDestroy(darkKnight.getBukkitEntity().getEntityId());
                        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packDelete3);

                        vanessa.getBukkitEntity().teleport(vanessaList.get(0));
                        PacketPlayOutEntityTeleport packetPlayOutEntityTeleport = new PacketPlayOutEntityTeleport(vanessa);
                        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetPlayOutEntityTeleport);
                        stand.getBukkitEntity().teleport(CameraInDeadIslandFirst.get(0));
                        PacketPlayOutEntityTeleport packetPlayOutEntityTeleport1 = new PacketPlayOutEntityTeleport(stand);
                        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetPlayOutEntityTeleport1);
                        p.teleport(stand.getBukkitEntity().getLocation());
                        p.setSpectatorTarget(stand.getBukkitEntity());
                        tempter.setSilent(true);
                        tempter.setLocation(-178.0D, 135.0D, 232.0D, -179.8F, -4.0F);
                        tempter.setInvisible(false);


                        darkKnight6.setSilent(true);
                        darkKnight6.setInvisible(false);
                        darkKnight6.setLocation(-181.0D, 135.0D, 232.0D, -82.5F, -0.7F);
                        darkKnight1.setSilent(true);
                        darkKnight1.setInvisible(false);
                        darkKnight1.setLocation(-180.0D, 135.0D, 229.0D, -16.0F, -2.0F);

                        darkKnight2.setSilent(true);
                        darkKnight2.setInvisible(false);
                        darkKnight2.setLocation(-181.0D, 135.0D, 227.0D, -16.0F, -0.2F);

                        darkKnight3.setSilent(true);
                        darkKnight3.setInvisible(false);
                        darkKnight3.setLocation(-176.0D, 135.0D, 226.0D, 23.0F, -0.9F);

                        darkKnight4.setSilent(true);
                        darkKnight4.setInvisible(false);
                        darkKnight4.setLocation(-177.0D, 135.0D, 229.0D, 26.0F, -0.9F);

                        darkKnight5.setSilent(true);
                        darkKnight5.setLocation(-176.0D, 135.0D, 232.0D, 77.0F, -0.9F);
                        darkKnight5.setInvisible(false);
                        DisguiseAPI.disguiseEntity(darkKnight.getBukkitEntity(), (Disguise) new PlayerDisguise("Jetlag918"));
                        DisguiseAPI.disguiseEntity(darkKnight1.getBukkitEntity(), (Disguise) new PlayerDisguise("Jetlag918"));
                        DisguiseAPI.disguiseEntity(darkKnight2.getBukkitEntity(), (Disguise) new PlayerDisguise("Jetlag918"));

                        DisguiseAPI.disguiseEntity(darkKnight3.getBukkitEntity(), (Disguise) new PlayerDisguise("Jetlag918"));

                        DisguiseAPI.disguiseEntity(darkKnight4.getBukkitEntity(), (Disguise) new PlayerDisguise("Jetlag918"));
                        DisguiseAPI.disguiseEntity(darkKnight5.getBukkitEntity(), (Disguise) new PlayerDisguise("Jetlag918"));

                        DisguiseAPI.disguiseEntity(darkKnight6.getBukkitEntity(), (Disguise) new PlayerDisguise("Jetlag918"));

                        DisguiseAPI.disguiseEntity(tempter.getBukkitEntity(), (Disguise) new PlayerDisguise("MagnificentN0ir"));



                        PacketPlayOutSpawnEntityLiving packetTempter = new PacketPlayOutSpawnEntityLiving((EntityLiving)tempter);
                        (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packetTempter);
                        PacketPlayOutSpawnEntityLiving packetDarkKnight1 = new PacketPlayOutSpawnEntityLiving((EntityLiving)darkKnight1);
                        (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packetDarkKnight1);
                        PacketPlayOutSpawnEntityLiving packetDarkKnight2 = new PacketPlayOutSpawnEntityLiving((EntityLiving)darkKnight2);
                        (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packetDarkKnight2);
                        PacketPlayOutSpawnEntityLiving packetDarkKnight3 = new PacketPlayOutSpawnEntityLiving((EntityLiving)darkKnight3);
                        (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packetDarkKnight3);
                        PacketPlayOutSpawnEntityLiving packetDarkKnight4 = new PacketPlayOutSpawnEntityLiving((EntityLiving)darkKnight4);
                        (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packetDarkKnight4);
                        PacketPlayOutSpawnEntityLiving packetDarkKnight5 = new PacketPlayOutSpawnEntityLiving((EntityLiving)darkKnight5);
                        (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packetDarkKnight5);
                        PacketPlayOutSpawnEntityLiving packetDarkKnight6 = new PacketPlayOutSpawnEntityLiving((EntityLiving)darkKnight6);
                        (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packetDarkKnight6);

                        pl.getSpawnFakeEntity().put(darkKnight1.getBukkitEntity().getEntityId(), darkKnight1);
                        pl.getSpawnFakeEntity().put(darkKnight2.getBukkitEntity().getEntityId(), darkKnight2);
                        pl.getSpawnFakeEntity().put(darkKnight3.getBukkitEntity().getEntityId(), darkKnight3);
                        pl.getSpawnFakeEntity().put(darkKnight4.getBukkitEntity().getEntityId(), darkKnight4);
                        pl.getSpawnFakeEntity().put(darkKnight5.getBukkitEntity().getEntityId(), darkKnight5);
                        pl.getSpawnFakeEntity().put(darkKnight6.getBukkitEntity().getEntityId(), darkKnight6);
                        pl.getSpawnFakeEntity().put(tempter.getBukkitEntity().getEntityId(), tempter);

                    }
                    if(ticks[0] > 386){
                        if(ticks[0] == 388){
                            p.sendMessage("§7[§6???§7] Кажется он забежал в эту пещеру...");
                            p.sendMessage("§7[§6???§7] Надо быть осторожней...");
                        }
                        if(ticks[0] >= 407){
                            if(ticks[0] == 540){
                                p.sendMessage("§7[§6???§7] Кажется там стоят монстры");
                                p.sendMessage("§7[§6???§7] Ничего не слышу, надо подойти ближе");
                            }
                            if (!CameraInDeadIslandFirst.isEmpty()) {
                                stand.getBukkitEntity().teleport(CameraInDeadIslandFirst.get(0));
                                PacketPlayOutEntityTeleport packetPlayOutEntityTeleport = new PacketPlayOutEntityTeleport((Entity)stand);
                                (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packetPlayOutEntityTeleport);
                                CameraInDeadIslandFirst.remove(0);
                            }
                            if (!vanessaList.isEmpty()) {
                                vanessa.getBukkitEntity().teleport(vanessaList.get(0));
                                PacketPlayOutEntityTeleport packetPlayOutEntityTeleport = new PacketPlayOutEntityTeleport((Entity)vanessa);
                                (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packetPlayOutEntityTeleport);
                                Location npcloc1 = vanessa.getBukkitEntity().getLocation();
                                float yaw = npcloc1.getYaw();
                                float pitch = npcloc1.getPitch();
                                connection.sendPacket(new PacketPlayOutEntity.PacketPlayOutEntityLook(vanessa.getId(), (byte) ((yaw%360.)*256/360), (byte) ((pitch%360.)*256/360), false));
                                connection.sendPacket(new PacketPlayOutEntityHeadRotation(vanessa, (byte) ((yaw%360.)*256/360)));
                                vanessaList.remove(0);
                            }else {
                                //745
                            }
                            if(ticks[0] == 745){
                                p.sendMessage("§7[§4§l???§7] Хозяину требуются человеческие жизни");
                            }
                            if(ticks[0] == 765){
                                p.sendMessage("§7[§4§l???§7] Нам нужно привести всех людей ему");
                            }
                            if(ticks[0] == 790){
                                p.sendMessage("§7[§4§l???§7] Кажется нас подслушивают, схватить ее");



                                clone.putAll(pl.getSpawnFakeEntity());
                                if(!pl.getSpawnFakeEntity().isEmpty()){
                                    for(Integer value : clone.keySet()){
                                        PacketPlayOutEntityDestroy packDeleteSlime4 = new PacketPlayOutEntityDestroy(clone.get(value).getBukkitEntity().getEntityId());
                                        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packDeleteSlime4);
                                    }
                                    pl.getSpawnFakeEntity().clear();
                                }

                                stand.getBukkitEntity().teleport(CameraInDeadIslandSecond.get(0));
                                vanessa.getBukkitEntity().teleport(vanessaList1.get(0));
                                PacketPlayOutEntityTeleport packetPlayOutEntityTeleport1 = new PacketPlayOutEntityTeleport((Entity)vanessa);
                                (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packetPlayOutEntityTeleport1);
                                PacketPlayOutEntityTeleport packetPlayOutEntityTeleport = new PacketPlayOutEntityTeleport((Entity)stand);
                                (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packetPlayOutEntityTeleport);
                                p.teleport(stand.getBukkitEntity().getLocation());
                                stageCutscene[0]++;

                                zombie.setLocation(mobDeadIsland.get(0).getX(), mobDeadIsland.get(0).getY(), mobDeadIsland.get(0).getZ(), mobDeadIsland.get(0).getYaw(), mobDeadIsland.get(0).getPitch());
                                zombie1.setLocation(mobDeadIsland1.get(0).getX(), mobDeadIsland1.get(0).getY(), mobDeadIsland1.get(0).getZ(), mobDeadIsland1.get(0).getYaw(), mobDeadIsland1.get(0).getPitch());
                                zombie2.setLocation(mobDeadIsland2.get(0).getX(), mobDeadIsland2.get(0).getY(), mobDeadIsland2.get(0).getZ(), mobDeadIsland2.get(0).getYaw(), mobDeadIsland2.get(0).getPitch());
                                zombie.setSilent(true);
                                zombie.setInvisible(false);

                                zombie1.setSilent(true);
                                zombie1.setInvisible(false);

                                zombie2.setSilent(true);
                                zombie2.setInvisible(false);

                                PacketPlayOutSpawnEntityLiving packetZombie = new PacketPlayOutSpawnEntityLiving((EntityLiving)zombie);
                                (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packetZombie);

                                PacketPlayOutSpawnEntityLiving packetZombie1 = new PacketPlayOutSpawnEntityLiving((EntityLiving)zombie1);
                                (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packetZombie1);

                                PacketPlayOutSpawnEntityLiving packetZombie2 = new PacketPlayOutSpawnEntityLiving((EntityLiving)zombie2);
                                (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packetZombie2);

                                pl.getSpawnFakeEntity().put(zombie.getBukkitEntity().getEntityId(), zombie);
                                pl.getSpawnFakeEntity().put(zombie1.getBukkitEntity().getEntityId(), zombie1);
                                pl.getSpawnFakeEntity().put(zombie2.getBukkitEntity().getEntityId(), zombie2);

                            }

                        }

                    }

                }
                if(stageCutscene[0] == 2){
                    if(ticks[0] >= 792){
                        if (!CameraInDeadIslandSecond.isEmpty()) {
                            stand.getBukkitEntity().teleport(CameraInDeadIslandSecond.get(0));
                            PacketPlayOutEntityTeleport packetPlayOutEntityTeleport = new PacketPlayOutEntityTeleport((Entity)stand);
                            (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packetPlayOutEntityTeleport);
                            CameraInDeadIslandSecond.remove(0);
                        }
                        if (!vanessaList1.isEmpty()) {
                            vanessa.getBukkitEntity().teleport(vanessaList1.get(0));
                            PacketPlayOutEntityTeleport packetPlayOutEntityTeleport = new PacketPlayOutEntityTeleport((Entity)vanessa);
                            (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packetPlayOutEntityTeleport);
                            Location npcloc1 = vanessa.getBukkitEntity().getLocation();
                            float yaw = npcloc1.getYaw();
                            float pitch = npcloc1.getPitch();
                            connection.sendPacket(new PacketPlayOutEntity.PacketPlayOutEntityLook(vanessa.getId(), (byte) ((yaw%360.)*256/360), (byte) ((pitch%360.)*256/360), false));
                            connection.sendPacket(new PacketPlayOutEntityHeadRotation(vanessa, (byte) ((yaw%360.)*256/360)));
                            vanessaList1.remove(0);
                        }else{
                            stageCutscene[0]++;
                        }
                        if (!mobDeadIsland.isEmpty()) {
                            zombie.getBukkitEntity().teleport(mobDeadIsland.get(0));
                            PacketPlayOutEntityTeleport packetPlayOutEntityTeleport = new PacketPlayOutEntityTeleport((Entity)zombie);
                            (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packetPlayOutEntityTeleport);
                            Location npcloc1 = zombie.getBukkitEntity().getLocation();
                            float yaw = npcloc1.getYaw();
                            float pitch = npcloc1.getPitch();
                            connection.sendPacket(new PacketPlayOutEntity.PacketPlayOutEntityLook(zombie.getId(), (byte) ((yaw%360.)*256/360), (byte) ((pitch%360.)*256/360), false));
                            connection.sendPacket(new PacketPlayOutEntityHeadRotation(zombie, (byte) ((yaw%360.)*256/360)));
                            mobDeadIsland.remove(0);
                        }
                        if (!mobDeadIsland1.isEmpty()) {
                            zombie1.getBukkitEntity().teleport(mobDeadIsland1.get(0));
                            PacketPlayOutEntityTeleport packetPlayOutEntityTeleport = new PacketPlayOutEntityTeleport((Entity)zombie1);
                            (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packetPlayOutEntityTeleport);
                            Location npcloc1 = zombie1.getBukkitEntity().getLocation();
                            float yaw = npcloc1.getYaw();
                            float pitch = npcloc1.getPitch();
                            connection.sendPacket(new PacketPlayOutEntity.PacketPlayOutEntityLook(zombie1.getId(), (byte) ((yaw%360.)*256/360), (byte) ((pitch%360.)*256/360), false));
                            connection.sendPacket(new PacketPlayOutEntityHeadRotation(zombie1, (byte) ((yaw%360.)*256/360)));
                            mobDeadIsland1.remove(0);
                        }
                        if (!mobDeadIsland2.isEmpty()) {
                            zombie2.getBukkitEntity().teleport(mobDeadIsland2.get(0));
                            PacketPlayOutEntityTeleport packetPlayOutEntityTeleport = new PacketPlayOutEntityTeleport((Entity)zombie2);
                            (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packetPlayOutEntityTeleport);
                            Location npcloc1 = zombie2.getBukkitEntity().getLocation();
                            float yaw = npcloc1.getYaw();
                            float pitch = npcloc1.getPitch();
                            connection.sendPacket(new PacketPlayOutEntity.PacketPlayOutEntityLook(zombie2.getId(), (byte) ((yaw%360.)*256/360), (byte) ((pitch%360.)*256/360), false));
                            connection.sendPacket(new PacketPlayOutEntityHeadRotation(zombie2, (byte) ((yaw%360.)*256/360)));
                            mobDeadIsland2.remove(0);
                        }
                    }

                }
                if(stageCutscene[0] == 3){
                    p.setGameMode(GameMode.SURVIVAL);
                    try {
                        pl.takeQuest("2");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    clone.putAll(pl.getSpawnFakeEntity());
                    if(!pl.getSpawnFakeEntity().isEmpty()){
                        for(Integer value : clone.keySet()){
                            PacketPlayOutEntityDestroy packDeleteSlime4 = new PacketPlayOutEntityDestroy(clone.get(value).getBukkitEntity().getEntityId());
                            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packDeleteSlime4);
                        }
                        pl.getSpawnFakeEntity().clear();
                    }
                    p.teleport(new Location(Bukkit.getWorld("world"), -3884,68,2700,2.2F,3.0F));
                    cancel();

                }
                ticks[0]++;
            }
        }).runTaskTimer((Plugin)azerot.getInstance(), 1L, 1L);

    }
}
