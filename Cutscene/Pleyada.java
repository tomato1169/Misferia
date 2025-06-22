package azerot.azerot.Cutscene;

import azerot.azerot.azerot;
import azerot.azerot.mobs.MobSystem;
import azerot.azerot.rpgplayer;
import com.comphenix.packetwrapper.WrapperPlayServerMount;
import de.slikey.effectlib.effect.StarEffect;
import io.netty.util.internal.ThreadLocalRandom;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;

import net.minecraft.server.v1_12_R1.*;
import org.bukkit.*;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.checkerframework.checker.units.qual.C;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Pleyada {
    public static void testCutScene(Player p) throws SQLException {
            List<Location> pleyada = new ArrayList<>((Collection<? extends Location>)azerot.pathRecord.get("pleyada"));
            List<Location> pleyada1 = new ArrayList<>((Collection<? extends Location>)azerot.pathRecord.get("pleyada1"));
            List<Location> pleyada2 = new ArrayList<>((Collection<? extends Location>)azerot.pathRecord.get("pleyada2"));
            List<Location> pleyada3 = new ArrayList<>((Collection<? extends Location>)azerot.pathRecord.get("pleyada3"));


        final Location Centre = new Location(Bukkit.getWorld("world"), -4291.0D, 123.0D, 3170.0D);

        double pX1 = Centre.getBlockX() + 0.5D + 9 * Math.cos(0.01D * 50);
        double pZ1 = Centre.getBlockZ() + 0.5D + 9 * Math.sin(0.01D * 50);

        double dx1 = Centre.getX() - pX1;
        double dz1 = Centre.getZ() - pZ1;
        float yaw1 = (float) -Math.toDegrees(Math.atan2(dx1, dz1));
        float pitch1 = 0;

            Location startloc = p.getLocation();
            WorldServer s = ((CraftWorld)p.getWorld()).getHandle();
            rpgplayer pl = rpgplayer.getRPGPlayer(p);
            pl.setWatchingCutscene(true);
            PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
            final EntityArmorStand stand = new EntityArmorStand(s);
            final EntityArmorStand armor = new EntityArmorStand(s);
            final EntityArmorStand armor1 = new EntityArmorStand(s);
            final EntityArmorStand armor2 = new EntityArmorStand(s);
            final EntityArmorStand armor3 = new EntityArmorStand(s);
            final EntityZombie zombie = new EntityZombie(s);


            final EntityZombie entity = new EntityZombie(s);
            final EntityZombie entity1 = new EntityZombie(s);
            final EntityZombie entity2 = new EntityZombie(s);
            final EntityZombie entity3 = new EntityZombie(s);

            zombie.setLocation(-4298.0D, 122,3170, Centre.getYaw(), Centre.getPitch());
            zombie.setCustomNameVisible(false);
        Location npcloc = zombie.getBukkitEntity().getLocation().setDirection(p.getLocation().subtract(Centre).toVector());
        float yaw = npcloc.getYaw();
        float pitch = npcloc.getPitch();
        connection.sendPacket(new PacketPlayOutEntity.PacketPlayOutEntityLook(zombie.getId(), (byte) ((yaw%360.)*256/360), (byte) ((pitch%360.)*256/360), false));
        connection.sendPacket(new PacketPlayOutEntityHeadRotation(zombie, (byte) ((yaw%360.)*256/360)));

        PacketPlayOutSpawnEntityLiving packetZombie = new PacketPlayOutSpawnEntityLiving((EntityLiving)zombie);
        (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packetZombie);
        pl.getSpawnFakeEntity().put(zombie.getBukkitEntity().getEntityId(), zombie);

            entity.setLocation(pleyada.get(0).getX(), pleyada.get(0).getY(),pleyada.get(0).getZ(), -91.6F, -22F);
            entity.setCustomNameVisible(false);
            entity.setInvisible(false);


        entity.setSilent(true);

            entity1.setLocation(pleyada1.get(0).getX(), pleyada1.get(0).getY(),pleyada1.get(0).getZ(), -91.6F, -22F);
            entity1.setCustomNameVisible(false);
        entity1.setInvisible(false);


        entity1.setSilent(true);

            entity2.setLocation(pleyada2.get(0).getX(), pleyada2.get(0).getY(),pleyada2.get(0).getZ(), -91.6F, -22F);
            entity2.setCustomNameVisible(false);
        entity2.setSilent(true);
        entity2.setInvisible(false);


            entity3.setLocation(pleyada3.get(0).getX(), pleyada3.get(0).getY(),pleyada3.get(0).getZ(), -91.6F, -22F);
            entity3.setCustomNameVisible(false);

            entity3.setSilent(true);
        entity3.setInvisible(false);

            stand.setLocation(pX1, 126.0D, pZ1, yaw1, pitch1);
            stand.setCustomName("");
            stand.setCustomNameVisible(false);
            stand.setNoGravity(true);
            stand.setInvisible(true);

            p.setGameMode(GameMode.SPECTATOR);
            p.teleport(new Location(p.getWorld(), pX1, 126.0D, pZ1));


            armor.setCustomName("");
            armor.setCustomNameVisible(false);
            armor.setNoGravity(true);
            armor.setSmall(true);
            armor.setInvisible(true);

            armor1.setCustomName("");
            armor1.setCustomNameVisible(false);
            armor1.setNoGravity(true);
            armor1.setSmall(true);
            armor1.setInvisible(true);

            armor2.setCustomName("");
            armor2.setCustomNameVisible(false);
            armor2.setNoGravity(true);
            armor2.setSmall(true);
            armor2.setInvisible(true);

            armor3.setCustomName("");
            armor3.setCustomNameVisible(false);
            armor3.setNoGravity(true);
            armor3.setSmall(true);
            armor3.setInvisible(true);
            Location LocDropOneItem = new Location(Bukkit.getWorld("world"), -4291.0D, 125.0D, 3174.0D);
            armor.setSlot(EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(new ItemStack(Material.BEDROCK)));
            armor1.setSlot(EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(new ItemStack(Material.GOLD_BLOCK)));
            armor2.setSlot(EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(new ItemStack(Material.EMERALD_BLOCK)));
            armor3.setSlot(EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(new ItemStack(Material.COBBLESTONE)));
        DisguiseAPI.disguiseEntity(entity.getBukkitEntity(), (Disguise) new PlayerDisguise("LouisVos"));
        Disguise disguise = DisguiseAPI.getDisguise(entity.getBukkitEntity());

        DisguiseAPI.disguiseEntity(entity1.getBukkitEntity(), (Disguise) new PlayerDisguise("LouisVos"));
        Disguise disguise1 = DisguiseAPI.getDisguise(entity1.getBukkitEntity());


        DisguiseAPI.disguiseEntity(entity2.getBukkitEntity(), (Disguise) new PlayerDisguise("LouisVos"));
        Disguise disguise2 = DisguiseAPI.getDisguise(entity2.getBukkitEntity());


        DisguiseAPI.disguiseEntity(entity3.getBukkitEntity(), (Disguise) new PlayerDisguise("LouisVos"));
        Disguise disguise3 = DisguiseAPI.getDisguise(entity3.getBukkitEntity());

                PacketPlayOutSpawnEntityLiving packetStand = new PacketPlayOutSpawnEntityLiving((EntityLiving)stand);
                (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packetStand);

                PacketPlayOutSpawnEntityLiving packetEntity = new PacketPlayOutSpawnEntityLiving((EntityLiving)entity);
                (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packetEntity);
                PacketPlayOutSpawnEntityLiving packetEntity1 = new PacketPlayOutSpawnEntityLiving((EntityLiving)entity1);
                (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packetEntity1);

                PacketPlayOutSpawnEntityLiving packetEntity2 = new PacketPlayOutSpawnEntityLiving((EntityLiving)entity2);
                (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packetEntity2);
                PacketPlayOutSpawnEntityLiving packetEntity3 = new PacketPlayOutSpawnEntityLiving((EntityLiving)entity3);
                (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packetEntity3);
                pl.getSpawnFakeEntity().put(stand.getBukkitEntity().getEntityId(), stand);
        pl.getSpawnFakeEntity().put(entity.getBukkitEntity().getEntityId(), entity);
        pl.getSpawnFakeEntity().put(entity1.getBukkitEntity().getEntityId(), entity1);
        pl.getSpawnFakeEntity().put(entity2.getBukkitEntity().getEntityId(), entity2);
        pl.getSpawnFakeEntity().put(entity3.getBukkitEntity().getEntityId(), entity3);
        stand.getBukkitEntity().setPassenger(p);

        WrapperPlayServerMount mountPacket = new WrapperPlayServerMount();

        mountPacket.setEntityID(stand.getBukkitEntity().getEntityId());
        mountPacket.setPassengerIds(new int[] {p.getEntityId()});
        mountPacket.sendPacket(p);



            Location StartSpiral = new Location(Bukkit.getWorld("world"), -4291.0D, 123.0D, 3174.0D);
            final double[] radius = { 3.0D };

            double[] height = { 4.0D };
            final int[] tick = { 0, 25, 50, 75 };
            final double speed = 0.06D;
            double[] currentAngle = { 0.0D };
            final int[] j = {0};
        final int[] ticks = {50};

        double x = Centre.getBlockX() + 0.5D + radius[0] * Math.cos(speed * tick[0]);
        double y = Centre.getY() + tick[0] * 0.04D;
        double z = Centre.getBlockZ() + 0.5D + radius[0] * Math.sin(speed * tick[0]);

        double x1 = Centre.getBlockX() + 0.5D + radius[0] * Math.cos(speed * tick[1]);
        double y1 = Centre.getY() + tick[0] * 0.04D;
        double z1 = Centre.getBlockZ() + 0.5D + radius[0] * Math.sin(speed * tick[1]);


        double x2 = Centre.getBlockX() + 0.5D + radius[0] * Math.cos(speed * tick[2]);
        double y2 = Centre.getY() + tick[0] * 0.04D;
        double z2 = Centre.getBlockZ() + 0.5D + radius[0] * Math.sin(speed * tick[2]);


        double x3 = Centre.getBlockX() + 0.5D + radius[0] * Math.cos(speed * tick[3]);
        double y3 = Centre.getY() + tick[0] * 0.04D;
        double z3 = Centre.getBlockZ() + 0.5D + radius[0] * Math.sin(speed * tick[3]);

        armor3.setLocation(x3, y3, z3, 0.0F, 0.0F);

        armor2.setLocation(x2, y2, z2, 0.0F, 0.0F);

        armor1.setLocation(x1, y1, z1, 0.0F, 0.0F);

        armor.setLocation(x, y, z, 0.0F, 0.0F);
        final boolean[] spawn = {false, false, false, false};
        final HashMap<Integer, Entity> clone = (HashMap<Integer, Entity>) pl.getSpawnFakeEntity().clone();

            (new BukkitRunnable() {
                public void run() {
                    if(!p.isOnline()){
                        cancel();
                    }
                    if(pl.isSkipCutscene()){
                        p.setGameMode(GameMode.SURVIVAL);
                        p.teleport(startloc);
                        p.sendTitle("§6Задание обновлено!", "", 10, 70, 20);
                        //
                        if(!pl.getSpawnFakeEntity().isEmpty()){
                            for(Integer value : clone.keySet()){
                                PacketPlayOutEntityDestroy packDeleteSlime4 = new PacketPlayOutEntityDestroy(clone.get(value).getBukkitEntity().getEntityId());
                                ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packDeleteSlime4);
                            }
                            pl.getSpawnFakeEntity().clear();
                        }

                        cancel();
                    }

                    double pX = Centre.getBlockX() + 0.5D + 9 * Math.cos(0.01D * ticks[0]);
                    double pZ = Centre.getBlockZ() + 0.5D + 9 * Math.sin(0.01D * ticks[0]);

                    double dx = Centre.getX() - pX;
                    double dz = Centre.getZ() - pZ;
                    float yaw = (float) -Math.toDegrees(Math.atan2(dx, dz));
                    float pitch = 0;
                    Location location = new Location(Bukkit.getWorld("world"), pX, 126, pZ,yaw,pitch);
                    stand.getBukkitEntity().teleport(location);
                    PacketPlayOutEntityTeleport packetPlayOutEntityTeleport1 = new PacketPlayOutEntityTeleport(stand);
                    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetPlayOutEntityTeleport1);

                    ticks[0]++;


                    if(j[0] < 4){
                        if(!pleyada.isEmpty()){
                            entity.getBukkitEntity().teleport(pleyada.get(0));
                            entity.setInvisible(false);
                            PacketPlayOutEntityTeleport packetPlayOutEntityTeleport = new PacketPlayOutEntityTeleport(entity);
                            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetPlayOutEntityTeleport);
                            Location npcloc1 = entity.getBukkitEntity().getLocation();
                            float yaw1 = npcloc1.getYaw();
                            float pitch1 = npcloc1.getPitch();
                            connection.sendPacket(new PacketPlayOutEntity.PacketPlayOutEntityLook(entity.getId(), (byte) ((yaw1%360.)*256/360), (byte) ((pitch1%360.)*256/360), false));
                            connection.sendPacket(new PacketPlayOutEntityHeadRotation(entity, (byte) ((yaw1%360.)*256/360)));
                            pleyada.remove(0);
                        }else{
                            if(!spawn[0]){
                                j[0] += 1;
                                PacketPlayOutSpawnEntityLiving packet1 = new PacketPlayOutSpawnEntityLiving((EntityLiving)armor);
                                (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packet1);
                                PacketPlayOutEntityEquipment pack = new PacketPlayOutEntityEquipment(armor.getBukkitEntity().getEntityId(), EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(new ItemStack(Material.BEDROCK)));
                                (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)pack);
                                pl.getSpawnFakeEntity().put(armor.getBukkitEntity().getEntityId(), armor);
                                spawn[0] = true;
                            }
                        }
                        if(!pleyada1.isEmpty()){
                            entity1.getBukkitEntity().teleport(pleyada1.get(0));
                            entity1.setInvisible(false);
                            PacketPlayOutEntityTeleport packetPlayOutEntityTeleport = new PacketPlayOutEntityTeleport(entity1);
                            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetPlayOutEntityTeleport);
                            pleyada1.remove(0);
                        }else{
                            if(!spawn[1]){
                                j[0] += 1;
                                PacketPlayOutSpawnEntityLiving packet1 = new PacketPlayOutSpawnEntityLiving((EntityLiving)armor1);
                                (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packet1);
                                PacketPlayOutEntityEquipment pack1 = new PacketPlayOutEntityEquipment(armor1.getBukkitEntity().getEntityId(), EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(new ItemStack(Material.GOLD_BLOCK)));
                                (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)pack1);
                                pl.getSpawnFakeEntity().put(armor1.getBukkitEntity().getEntityId(), armor1);
                                spawn[1] = true;
                            }
                        }
                        if(!pleyada2.isEmpty()){
                            entity2.getBukkitEntity().teleport(pleyada2.get(0));
                            entity2.setInvisible(false);
                            PacketPlayOutEntityTeleport packetPlayOutEntityTeleport = new PacketPlayOutEntityTeleport(entity2);
                            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetPlayOutEntityTeleport);
                            pleyada2.remove(0);
                        }else{
                            if(!spawn[2]){
                                j[0] += 1;
                                PacketPlayOutSpawnEntityLiving packet1 = new PacketPlayOutSpawnEntityLiving((EntityLiving)armor2);
                                (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packet1);
                                PacketPlayOutEntityEquipment pack2 = new PacketPlayOutEntityEquipment(armor2.getBukkitEntity().getEntityId(), EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(new ItemStack(Material.EMERALD_BLOCK)));
                                (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)pack2);
                                pl.getSpawnFakeEntity().put(armor2.getBukkitEntity().getEntityId(), armor2);
                                spawn[2] = true;
                            }
                        }
                        if(!pleyada3.isEmpty()){
                            entity3.getBukkitEntity().teleport(pleyada3.get(0));
                            entity3.setInvisible(false);
                            PacketPlayOutEntityTeleport packetPlayOutEntityTeleport = new PacketPlayOutEntityTeleport(entity3);
                            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetPlayOutEntityTeleport);
                            pleyada3.remove(0);
                        }else{
                            if(!spawn[3]){
                                j[0] += 1;
                                PacketPlayOutSpawnEntityLiving packet1 = new PacketPlayOutSpawnEntityLiving((EntityLiving)armor3);
                                (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packet1);
                                PacketPlayOutEntityEquipment pack3 = new PacketPlayOutEntityEquipment(armor3.getBukkitEntity().getEntityId(), EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(new ItemStack(Material.COBBLESTONE)));
                                (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)pack3);
                                pl.getSpawnFakeEntity().put(armor3.getBukkitEntity().getEntityId(), armor3);
                                spawn[3] = true;
                            }
                        }

                    }
                    if(j[0] == 4){
                        double x = Centre.getBlockX() + 0.5D + radius[0] * Math.cos(speed * tick[0]);
                        double y = Centre.getY() + tick[0] * 0.04D;
                        double z = Centre.getBlockZ() + 0.5D + radius[0] * Math.sin(speed * tick[0]);
                        Location teleportLocation = new Location(Bukkit.getWorld("world"), x, y, z);
                        armor.getBukkitEntity().teleport(teleportLocation);
                        tick[0] = tick[0] + 1;
                        double x1 = Centre.getBlockX() + 0.5D + radius[0] * Math.cos(speed * tick[1]);
                        double y1 = Centre.getY() + tick[0] * 0.04D;
                        double z1 = Centre.getBlockZ() + 0.5D + radius[0] * Math.sin(speed * tick[1]);
                        Location teleportLocation1 = new Location(Bukkit.getWorld("world"), x1, y1, z1);
                        armor1.getBukkitEntity().teleport(teleportLocation1);
                        tick[1] = tick[1] + 1;
                        double x2 = Centre.getBlockX() + 0.5D + radius[0] * Math.cos(speed * tick[2]);
                        double y2 = Centre.getY() + tick[0] * 0.04D;
                        double z2 = Centre.getBlockZ() + 0.5D + radius[0] * Math.sin(speed * tick[2]);
                        Location teleportLocation2 = new Location(Bukkit.getWorld("world"), x2, y2, z2);
                        armor2.getBukkitEntity().teleport(teleportLocation2);
                        tick[2] = tick[2] + 1;
                        double x3 = Centre.getBlockX() + 0.5D + radius[0] * Math.cos(speed * tick[3]);
                        double y3 = Centre.getY() + tick[0] * 0.04D;
                        double z3 = Centre.getBlockZ() + 0.5D + radius[0] * Math.sin(speed * tick[3]);
                        Location teleportLocation3 = new Location(Bukkit.getWorld("world"), x3, y3, z3);
                        armor3.getBukkitEntity().teleport(teleportLocation3);

                        float red = (float) 128 / 255;
                        float green = (float) 128 / 255 ;
                        float blue = (float) 0 / 255;

                        PacketPlayOutWorldParticles particles = new PacketPlayOutWorldParticles(EnumParticle.REDSTONE, true, teleportLocation.getBlockX(), teleportLocation.getBlockY(), teleportLocation.getBlockZ(),
                                red, green, blue, 1,0,0);
                        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(particles);

                        float red1 = (float) 154 / 255;
                        float green1 = (float) 205 / 255;
                        float blue1 = (float) 50 / 255;
                        PacketPlayOutWorldParticles particles1 = new PacketPlayOutWorldParticles(EnumParticle.REDSTONE, true, teleportLocation1.getBlockX(), teleportLocation1.getBlockY(), teleportLocation1.getBlockZ(),
                                red1, green1, blue1, 1,0,0);
                        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(particles1);


                        float red2 = (float) 60 / 255;
                        float green2 = (float) 179 / 255;
                        float blue2 = (float) 113 / 255;
                        PacketPlayOutWorldParticles particles2 = new PacketPlayOutWorldParticles(EnumParticle.REDSTONE, true, (float) teleportLocation2.getBlockX(), (float)teleportLocation2.getBlockY(), (float) teleportLocation2.getBlockZ(),
                                red2, green2, blue2, 1,0,0);
                        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(particles2);


                        float red3 = (float) 0 / 255;
                        float green3 = (float) 250 / 255;
                        float blue3 = (float) 154 / 255;
                        PacketPlayOutWorldParticles particles3 = new PacketPlayOutWorldParticles(EnumParticle.REDSTONE, true, (float) teleportLocation3.getBlockX(), (float) teleportLocation3.getBlockY(), (float) teleportLocation3.getBlockZ(),
                                red3, green3, blue3, 1,0,0);
                        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(particles3);



                        PacketPlayOutEntityTeleport packetPlayOutEntityTeleportarmor = new PacketPlayOutEntityTeleport(armor);
                        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetPlayOutEntityTeleportarmor);
                        PacketPlayOutEntityTeleport packetPlayOutEntityTeleportarmor1 = new PacketPlayOutEntityTeleport(armor1);
                        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetPlayOutEntityTeleportarmor1);
                        PacketPlayOutEntityTeleport packetPlayOutEntityTeleportarmor2 = new PacketPlayOutEntityTeleport(armor2);
                        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetPlayOutEntityTeleportarmor2);
                        PacketPlayOutEntityTeleport packetPlayOutEntityTeleportarmor3 = new PacketPlayOutEntityTeleport(armor3);
                        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetPlayOutEntityTeleportarmor3);
                        tick[3] = tick[3] + 1;
                        radius[0] = radius[0] - 0.03D;
                        if (tick[0] == 100){
                            p.setGameMode(GameMode.SURVIVAL);
                            p.teleport(startloc);
                            p.sendTitle("§6Задание обновлено!", "", 10, 70, 20);
                            if(!pl.getSpawnFakeEntity().isEmpty()){
                                for(Integer value : pl.getSpawnFakeEntity().keySet()){
                                    PacketPlayOutEntityDestroy packDeleteSlime4 = new PacketPlayOutEntityDestroy(pl.getSpawnFakeEntity().get(value).getBukkitEntity().getEntityId());
                                    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packDeleteSlime4);
                                    pl.getSpawnFakeEntity().remove(value);
                                }
                            }

                            cancel();
                        }
                    }
                }
            }).runTaskTimer((Plugin)azerot.getInstance(), 20L, 1L);

    }
    

}
