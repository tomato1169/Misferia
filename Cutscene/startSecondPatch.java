package azerot.azerot.Cutscene;

import azerot.azerot.Quest.questScript;
import azerot.azerot.azerot;
import azerot.azerot.mobs.MobSystem;
import azerot.azerot.rpgplayer;
import com.comphenix.packetwrapper.WrapperPlayServerMount;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class startSecondPatch {

    public static void CutSceneStartSecondPatch(Player p) throws SQLException {

        WorldServer s = ((CraftWorld) p.getWorld()).getHandle();
        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        rpgplayer pl = rpgplayer.getRPGPlayer(p);
        pl.setWatchingCutscene(true);
        p.setGameMode(GameMode.SPECTATOR);

        Location endLoc = new Location(Bukkit.getWorld("world"), -4005, 72, 4087);
        final EntityArmorStand camera = new EntityArmorStand(s);
        camera.setLocation(-4083, 110, 3292, -134f, 10f);
        camera.setCustomNameVisible(false);
        camera.setNoGravity(true);
        camera.setInvisible(true);
        PacketPlayOutSpawnEntityLiving packetSpawnCamera = new PacketPlayOutSpawnEntityLiving((EntityLiving) camera);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetSpawnCamera);
        List<Location> cameraStart = new ArrayList<>(azerot.pathRecord.get("CameraStartSecondPatch"));
        List<Location> Elijah = new ArrayList<>(azerot.pathRecord.get("Elijah"));
        List<Location> playerStartSecondPatch1 = new ArrayList<>(azerot.pathRecord.get("playerStartSecondPatch"));
        EntityZombie elijah = new EntityZombie(s);
        elijah.setLocation(Elijah.get(0).getX(), Elijah.get(0).getY(), Elijah.get(0).getZ(), Elijah.get(0).getYaw(), Elijah.get(0).getPitch());
        elijah.setInvisible(false);
        DisguiseAPI.disguiseEntity(elijah.getBukkitEntity(), (Disguise) new PlayerDisguise("LouisVos"));
        Disguise disguise1 = DisguiseAPI.getDisguise(elijah.getBukkitEntity());
        PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving((EntityLiving) elijah);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);

        if(!cameraStart.get(0).getChunk().isLoaded()){
            cameraStart.get(0).getChunk().load();
            Bukkit.broadcastMessage("чанк прогружен");
        }
        if(!Elijah.get(0).getChunk().isLoaded()){
            Elijah.get(0).getChunk().load();
            Bukkit.broadcastMessage("чанк прогружен2");
        }

        EntityZombie playerStartSecondPatch = new EntityZombie(s);
        playerStartSecondPatch.setLocation(playerStartSecondPatch1.get(0).getX(), playerStartSecondPatch1.get(0).getY(), playerStartSecondPatch1.get(0).getZ(), playerStartSecondPatch1.get(0).getYaw(), playerStartSecondPatch1.get(0).getPitch());
        playerStartSecondPatch.setInvisible(false);
        DisguiseAPI.disguiseEntity(playerStartSecondPatch.getBukkitEntity(), (Disguise) new PlayerDisguise(pl.getName()));
        Disguise disguise2 = DisguiseAPI.getDisguise(playerStartSecondPatch.getBukkitEntity());
        // Если убрать дизгуайс то ник норм
        PacketPlayOutSpawnEntityLiving packetplayerStartSecondPatch = new PacketPlayOutSpawnEntityLiving((EntityLiving) playerStartSecondPatch);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetplayerStartSecondPatch);
        pl.getSpawnFakeEntity().put(playerStartSecondPatch.getBukkitEntity().getEntityId(), playerStartSecondPatch);
        pl.getSpawnFakeEntity().put(camera.getBukkitEntity().getEntityId(), camera);
        pl.getSpawnFakeEntity().put(elijah.getBukkitEntity().getEntityId(), elijah);
        p.teleport(cameraStart.get(0));
        camera.getBukkitEntity().setPassenger(p);

        WrapperPlayServerMount mountPacket = new WrapperPlayServerMount();

        mountPacket.setEntityID(camera.getBukkitEntity().getEntityId());
        mountPacket.setPassengerIds(new int[] {p.getEntityId()});
        mountPacket.sendPacket(p);
        final HashMap<Integer, Entity> clone = (HashMap<Integer, Entity>) pl.getSpawnFakeEntity().clone();
        (new BukkitRunnable() {
            public void run() {
                if(!p.isOnline()){
                    cancel();
                }
                if(pl.isSkipCutscene()){
                    if(!pl.getSpawnFakeEntity().isEmpty()){
                        for(Integer value : clone.keySet()){
                            PacketPlayOutEntityDestroy packDeleteSlime4 = new PacketPlayOutEntityDestroy(clone.get(value).getBukkitEntity().getEntityId());
                            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packDeleteSlime4);
                        }
                        pl.getSpawnFakeEntity().clear();
                    }
                    pl.getQuestsStage().put("5", 3);
                    p.setGameMode(GameMode.SURVIVAL);
                    pl.setWatchingCutscene(false);
                    p.teleport(new Location(p.getWorld(), -4452, 105, 3285, 132, 2));
                    p.sendTitle("§6Задание обновлено!", "", 10, 70, 20);
                    pl.getKilledMobs().put("28",0);
                    pl.getKilledMobs().put("29",0);
                    WorldServer s = ((CraftWorld)p.getWorld()).getHandle();
                    EntityZombie elijah = new EntityZombie(s);
                    elijah.setLocation(-4455, 105, 3282, -42F,0F);
                    elijah.setCustomNameVisible(true);
                    elijah.setCustomName("§6Элайя");
                    elijah.setInvisible(false);
                    elijah.getBukkitEntity().setGlowing(true);
                    PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving((EntityLiving) elijah);
                    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
                    pl.getSpawnFakeNPC().put(13, elijah);
                    cancel();
                }
                PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
                if(!Elijah.isEmpty()){
                    elijah.getBukkitEntity().teleport(Elijah.get(0));
                    PacketPlayOutEntityTeleport packetPlayOutEntityTeleport = new PacketPlayOutEntityTeleport(elijah);
                    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetPlayOutEntityTeleport);
                    Location npcloc = elijah.getBukkitEntity().getLocation();
                    float yaw = npcloc.getYaw();
                    float pitch = npcloc.getPitch();
                    connection.sendPacket(new PacketPlayOutEntity.PacketPlayOutEntityLook(elijah.getId(), (byte) ((yaw%360.)*256/360), (byte) ((pitch%360.)*256/360), false));
                    connection.sendPacket(new PacketPlayOutEntityHeadRotation(elijah, (byte) ((yaw%360.)*256/360)));
                    Elijah.remove(0);
                }

                if(!playerStartSecondPatch1.isEmpty()){
                    playerStartSecondPatch.getBukkitEntity().teleport(playerStartSecondPatch1.get(0));
                    Location npcloc =  playerStartSecondPatch.getBukkitEntity().getLocation();
                    float yaw = npcloc.getYaw();
                    float pitch = npcloc.getPitch();
                    CraftEntity entity = playerStartSecondPatch.getBukkitEntity();
                    connection.sendPacket(new PacketPlayOutEntity.PacketPlayOutEntityLook(playerStartSecondPatch.getId(), (byte) ((yaw%360.)*256/360), (byte) ((pitch%360.)*256/360), false));
                    connection.sendPacket(new PacketPlayOutEntityHeadRotation(playerStartSecondPatch, (byte) ((yaw%360.)*256/360)));
                    PacketPlayOutEntityTeleport packetPlayOutEntityTeleport = new PacketPlayOutEntityTeleport(playerStartSecondPatch);
                    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetPlayOutEntityTeleport);
                    playerStartSecondPatch1.remove(0);

                }else{

                    if(!pl.getSpawnFakeEntity().isEmpty()){
                        for(Integer value : clone.keySet()){
                            PacketPlayOutEntityDestroy packDeleteSlime4 = new PacketPlayOutEntityDestroy(clone.get(value).getBukkitEntity().getEntityId());
                            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packDeleteSlime4);
                        }
                        pl.getSpawnFakeEntity().clear();
                    }
                    pl.getQuestsStage().put("5", 3);
                    p.setGameMode(GameMode.SURVIVAL);
                    pl.setWatchingCutscene(false);
                    p.teleport(new Location(p.getWorld(), -4452, 105, 3285, 132, 2));
                    p.sendTitle("§6Задание обновлено!", "", 10, 70, 20);
                    pl.getKilledMobs().put("28",0);
                    pl.getKilledMobs().put("29",0);
                    cancel();
                }
            }
        }).runTaskTimer((Plugin)azerot.getInstance(), 20L, 1L);



    }
}
