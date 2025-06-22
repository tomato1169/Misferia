package azerot.azerot.Cutscene;

import azerot.azerot.Quest.questScript;
import azerot.azerot.azerot;
import azerot.azerot.rpgplayer;
import com.comphenix.packetwrapper.WrapperPlayServerMount;
import com.mojang.authlib.GameProfile;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
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

public class initial {
    public static void testCutScene(Player p) throws SQLException {
            Random random = new Random();

            Location loc = new Location(Bukkit.getWorld("world"), -3260, 72, 1813);
            Location startloc = p.getLocation();

            List<Location> bandit = new ArrayList<>(azerot.pathRecord.get("bandit"));
            List<Location> Vilnius = new ArrayList<>(azerot.pathRecord.get("Vilnius"));
            List<Location> FirstCamera = new ArrayList<>(azerot.pathRecord.get("firstCamera"));
        p.teleport(FirstCamera.get(0));

            if(!FirstCamera.get(0).getChunk().isLoaded()){
                FirstCamera.get(0).getChunk().load();
                Bukkit.broadcastMessage("чанк прогружен");
            }
            if(!Vilnius.get(0).getChunk().isLoaded()){
                Vilnius.get(0).getChunk().load();
                Bukkit.broadcastMessage("чанк прогружен2");
            }
            final int[] i = {0};
            rpgplayer pl = rpgplayer.getRPGPlayer(p);
            pl.setWatchingCutscene(true);

            p.setGameMode(GameMode.SPECTATOR);
            WorldServer s = ((CraftWorld)p.getWorld()).getHandle();

        final EntityArmorStand camera = new EntityArmorStand(s);
        camera.setLocation(FirstCamera.get(0).getX(), FirstCamera.get(0).getY(), FirstCamera.get(0).getZ(), FirstCamera.get(0).getYaw(), FirstCamera.get(0).getPitch());
        camera.setCustomNameVisible(false);
        camera.setNoGravity(true);
        camera.setInvisible(true);
        PacketPlayOutSpawnEntityLiving packetSpawnCamera = new PacketPlayOutSpawnEntityLiving((EntityLiving) camera);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetSpawnCamera);

            EntityZombie villager = new EntityZombie(s);
            EntityZombie zombie = new EntityZombie(s);
            zombie.setCustomNameVisible(false);
            villager.setCustomNameVisible(false);
            villager.setLocation(-3231, 65, 1808,0,0);

            zombie.setLocation(-3228, 65, 1797, 0,0);

        DisguiseAPI.disguiseEntity(villager.getBukkitEntity(), (Disguise) new PlayerDisguise("Rixxz2"));

        DisguiseAPI.disguiseEntity(zombie.getBukkitEntity(), (Disguise) new PlayerDisguise("oublaz"));

        Disguise disguise = DisguiseAPI.getDisguise(zombie.getBukkitEntity());
        disguise.getWatcher().setCustomNameVisible(false);

        Disguise disguise1 = DisguiseAPI.getDisguise(villager.getBukkitEntity());
        disguise1.getWatcher().setCustomNameVisible(false);
        PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;

            PacketPlayOutSpawnEntityLiving packetVillager = new PacketPlayOutSpawnEntityLiving((EntityLiving) villager);
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetVillager);
            PacketPlayOutSpawnEntityLiving packetZombie = new PacketPlayOutSpawnEntityLiving((EntityLiving) zombie);
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetZombie);
        pl.getSpawnFakeEntity().put(camera.getBukkitEntity().getEntityId(), camera);
        pl.getSpawnFakeEntity().put(villager.getBukkitEntity().getEntityId(), villager);
        pl.getSpawnFakeEntity().put(zombie.getBukkitEntity().getEntityId(), zombie);
        p.teleport(FirstCamera.get(0));
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
                        p.setGameMode(GameMode.SURVIVAL);
                        pl.setWatchingCutscene(false);
                        try {
                            pl.takeQuest("1");
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        p.teleport(startloc);
                        cancel();
                    }
                    if(!bandit.isEmpty()){
                        zombie.getBukkitEntity().teleport(bandit.get(0));
                        PacketPlayOutEntityTeleport packetPlayOutEntityTeleport = new PacketPlayOutEntityTeleport(zombie);
                        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetPlayOutEntityTeleport);
                        bandit.remove(0);
                    }else{
                        i[0] += 1;
                    }
                    if(!Vilnius.isEmpty()){
                        villager.getBukkitEntity().teleport(Vilnius.get(0));
                        PacketPlayOutEntityTeleport packetPlayOutEntityTeleport = new PacketPlayOutEntityTeleport(villager);
                        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetPlayOutEntityTeleport);
                        Vilnius.remove(0);
                    }else{
                        i[0] += 1;

                    }
                    if(i[0] >= 2){
                        if(!pl.getSpawnFakeEntity().isEmpty()){
                            for(Integer value : clone.keySet()){
                                PacketPlayOutEntityDestroy packDeleteSlime4 = new PacketPlayOutEntityDestroy(clone.get(value).getBukkitEntity().getEntityId());
                                ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packDeleteSlime4);
                            }
                            pl.getSpawnFakeEntity().clear();
                        }
                        p.setGameMode(GameMode.SURVIVAL);
                        try {
                            pl.takeQuest("1");
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        p.teleport(startloc);
                        cancel();
                    }

                }
            }).runTaskTimer((Plugin)azerot.getInstance(), 10L, 1L);


    }
}
