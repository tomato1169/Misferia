package azerot.azerot.Cutscene;

import azerot.azerot.WorldUtils;
import azerot.azerot.azerot;
import azerot.azerot.rpgplayer;
import de.slikey.effectlib.effect.ColoredImageEffect;
import de.slikey.effectlib.effect.PlotEffect;
import de.slikey.effectlib.effect.StarEffect;
import de.slikey.effectlib.util.ParticleEffect;
import io.netty.util.internal.ThreadLocalRandom;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.*;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.sql.SQLException;

public class FinalSecondPatch {

    public static void CutSceneTP(Player p) throws SQLException {

        WorldServer s = ((CraftWorld)p.getWorld()).getHandle();
        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        rpgplayer pl = rpgplayer.getRPGPlayer(p);
        pl.setWatchingCutscene(true);

        Location endLoc = new Location(Bukkit.getWorld("world"), -4005, 72, 4087);
        final EntityArmorStand stand = new EntityArmorStand(s);

        stand.setLocation(-4042.0D, 72.0D, 4081.0D, -68.1F, 15.5F);
        stand.setCustomName("");
        stand.setCustomNameVisible(false);
        stand.setNoGravity(true);
        stand.setInvisible(true);


        final EntityArmorStand ItemForYellowBoss = new EntityArmorStand(s);
        final EntityArmorStand ItemForDarkBoss = new EntityArmorStand(s);
        final EntityArmorStand ItemForBlueBoss = new EntityArmorStand(s);
        final EntityArmorStand ItemForRedBoss = new EntityArmorStand(s);

        ItemForYellowBoss.setLocation(-4023.0D, 66.0D, 4081.0D, 0.0F, 0.0F);
        ItemForYellowBoss.setCustomName("");
        ItemForYellowBoss.setCustomNameVisible(false);
        ItemForYellowBoss.setNoGravity(true);
        ItemForYellowBoss.setSmall(true);
        ItemForYellowBoss.setInvisible(true);


        ItemForDarkBoss.setLocation(-4014.0D, 66.0D, 4077.0D, 0.0F, 0.0F);
        ItemForDarkBoss.setCustomName("");
        ItemForDarkBoss.setCustomNameVisible(false);
        ItemForDarkBoss.setNoGravity(true);
        ItemForDarkBoss.setSmall(true);
        ItemForDarkBoss.setInvisible(true);


        ItemForBlueBoss.setLocation(-4023.0D, 66.0D, 4092.0D, 0.0F, 0.0F);
        ItemForBlueBoss.setCustomName("");
        ItemForBlueBoss.setCustomNameVisible(false);
        ItemForBlueBoss.setNoGravity(true);
        ItemForBlueBoss.setSmall(true);
        ItemForBlueBoss.setInvisible(true);

        ItemForRedBoss.setLocation(-4014.0D, 66.0D, 4097.0D, 0.0F, 0.0F);
        ItemForRedBoss.setCustomName("");
        ItemForRedBoss.setCustomNameVisible(false);
        ItemForRedBoss.setNoGravity(true);
        ItemForRedBoss.setSmall(true);
        ItemForRedBoss.setInvisible(true);

        ItemForYellowBoss.setSlot(EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(new ItemStack(Material.BEDROCK)));
        ItemForDarkBoss.setSlot(EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(new ItemStack(Material.GOLD_BLOCK)));
        ItemForBlueBoss.setSlot(EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(new ItemStack(Material.EMERALD_BLOCK)));
        ItemForRedBoss.setSlot(EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(new ItemStack(Material.COBBLESTONE)));

        PacketPlayOutSpawnEntityLiving packetStand = new PacketPlayOutSpawnEntityLiving((EntityLiving)stand);
        (((CraftPlayer)p).getHandle()).playerConnection.sendPacket(packetStand);
        pl.getSpawnFakeEntity().put(stand.getBukkitEntity().getEntityId(), stand);

        PacketPlayOutSpawnEntityLiving packetItemForYellowBoss = new PacketPlayOutSpawnEntityLiving((EntityLiving)ItemForYellowBoss);
        (((CraftPlayer)p).getHandle()).playerConnection.sendPacket(packetItemForYellowBoss);
        pl.getSpawnFakeEntity().put(ItemForYellowBoss.getBukkitEntity().getEntityId(), ItemForYellowBoss);

        PacketPlayOutSpawnEntityLiving packetItemForDarkBoss = new PacketPlayOutSpawnEntityLiving((EntityLiving)ItemForDarkBoss);
        (((CraftPlayer)p).getHandle()).playerConnection.sendPacket(packetItemForDarkBoss);
        pl.getSpawnFakeEntity().put(ItemForDarkBoss.getBukkitEntity().getEntityId(), ItemForDarkBoss);

        PacketPlayOutSpawnEntityLiving packetItemForBlueBoss = new PacketPlayOutSpawnEntityLiving((EntityLiving)ItemForBlueBoss);
        (((CraftPlayer)p).getHandle()).playerConnection.sendPacket(packetItemForBlueBoss);
        pl.getSpawnFakeEntity().put(ItemForBlueBoss.getBukkitEntity().getEntityId(), ItemForBlueBoss);

        PacketPlayOutSpawnEntityLiving packetItemForRedBoss = new PacketPlayOutSpawnEntityLiving((EntityLiving)ItemForRedBoss);
        (((CraftPlayer)p).getHandle()).playerConnection.sendPacket(packetItemForRedBoss);
        pl.getSpawnFakeEntity().put(ItemForRedBoss.getBukkitEntity().getEntityId(), ItemForRedBoss);

        PacketPlayOutEntityEquipment packItemYellowBoss = new PacketPlayOutEntityEquipment(ItemForYellowBoss.getBukkitEntity().getEntityId(), EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(new ItemStack(Material.BEDROCK)));
        (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packItemYellowBoss);

        PacketPlayOutEntityEquipment packItemDarkBoss = new PacketPlayOutEntityEquipment(ItemForDarkBoss.getBukkitEntity().getEntityId(), EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(new ItemStack(Material.GOLD_BLOCK)));
        (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packItemDarkBoss);

        PacketPlayOutEntityEquipment packItemBlueBoss = new PacketPlayOutEntityEquipment(ItemForBlueBoss.getBukkitEntity().getEntityId(), EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(new ItemStack(Material.EMERALD_BLOCK)));
        (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packItemBlueBoss);

        PacketPlayOutEntityEquipment packItemRedBoss = new PacketPlayOutEntityEquipment(ItemForRedBoss.getBukkitEntity().getEntityId(), EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(new ItemStack(Material.COBBLESTONE)));
        (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packItemRedBoss);


        Location center = new Location(Bukkit.getWorld("world"), (ItemForYellowBoss.getBukkitEntity().getLocation().getX() + endLoc.getX()), (ItemForYellowBoss.getBukkitEntity().getLocation().getY() + endLoc.getY()) / 2 + 2, (ItemForYellowBoss.getBukkitEntity().getLocation().getZ() + endLoc.getZ()));

        Location start = ItemForYellowBoss.getBukkitEntity().getLocation();
        Location startBlue = ItemForBlueBoss.getBukkitEntity().getLocation();
        Location startDark = ItemForDarkBoss.getBukkitEntity().getLocation();
        Location startRed = ItemForRedBoss.getBukkitEntity().getLocation();

        double deltaXBlue = (endLoc.getX() - startBlue.getX()) / 250;
        double deltaZBlue = (endLoc.getZ() - startBlue.getZ()) / 250;
        double deltaYBlue = (endLoc.getY() - startBlue.getY()) / 250;

        double deltaXRed = (endLoc.getX() - startRed.getX()) / 250;
        double deltaZRed = (endLoc.getZ() - startRed.getZ()) / 250;
        double deltaYRed = (endLoc.getY() - startRed.getY()) / 250;


        double deltaX = (endLoc.getX() - start.getX()) / 250;
        double deltaZ = (endLoc.getZ() - start.getZ()) / 250;
        double deltaY = (endLoc.getY() - start.getY()) / 250;

        double deltaX1 = (endLoc.getX() - startDark.getX()) / 250;
        double deltaZ1 = (endLoc.getZ() - startDark.getZ()) / 250;
        double deltaY1 = (endLoc.getY() - startDark.getY()) / 250;
        final int[] tick= {0,0,0,0};


        final int[] ticks = {0};

        Location endLocOneLeft = new Location(Bukkit.getWorld("world"), endLoc.getX(), endLoc.getY(), endLoc.getZ() - 1);
        Location endLocOneRight  = new Location(Bukkit.getWorld("world"), endLoc.getX(), endLoc.getY(), endLoc.getZ() + 1);
        Location endLocOneRightAndOneUp = new Location(Bukkit.getWorld("world"), endLoc.getX(), endLoc.getY() + 1, endLoc.getZ() + 1);
        Location endLocOneRightAndOneDown = new Location(Bukkit.getWorld("world"), endLoc.getX(), endLoc.getY() - 1, endLoc.getZ() + 1);


        Location endLocOneLeftAndOneUp= new Location(Bukkit.getWorld("world"), endLoc.getX(), endLoc.getY() + 1, endLoc.getZ() - 1);
        Location endLocOneLeftAndOneDown = new Location(Bukkit.getWorld("world"), endLoc.getX(), endLoc.getY() - 1, endLoc.getZ() - 1);

        final double[] radius = { 5.0D, 4, 3, 2};
        final double speed = 0.06D;



        (new BukkitRunnable() {
            public void run() {
                if(ticks[0] == 1500){
                    PacketPlayOutEntityDestroy packDelete = new PacketPlayOutEntityDestroy(stand.getBukkitEntity().getEntityId());
                    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packDelete);
                    PacketPlayOutEntityDestroy packDelete1 = new PacketPlayOutEntityDestroy(ItemForBlueBoss.getBukkitEntity().getEntityId());
                    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packDelete1);
                    PacketPlayOutEntityDestroy packDelete2 = new PacketPlayOutEntityDestroy(ItemForRedBoss.getBukkitEntity().getEntityId());
                    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packDelete2);
                    PacketPlayOutEntityDestroy packDelete3 = new PacketPlayOutEntityDestroy(ItemForDarkBoss.getBukkitEntity().getEntityId());
                    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packDelete3);
                    PacketPlayOutEntityDestroy packDelete4 = new PacketPlayOutEntityDestroy(ItemForYellowBoss.getBukkitEntity().getEntityId());
                    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packDelete4);
                    pl.getSpawnFakeEntity().remove(stand.getBukkitEntity().getEntityId());
                    pl.getSpawnFakeEntity().remove(ItemForBlueBoss.getBukkitEntity().getEntityId());
                    pl.getSpawnFakeEntity().remove(ItemForRedBoss.getBukkitEntity().getEntityId());
                    pl.getSpawnFakeEntity().remove(ItemForDarkBoss.getBukkitEntity().getEntityId());
                    pl.getSpawnFakeEntity().remove(ItemForYellowBoss.getBukkitEntity().getEntityId());
                    cancel();
                }

                if(ticks[0] == 0){
                    endLoc.getWorld().strikeLightning(endLoc);
                }
                if(tick[0] >= 0 && tick[0] <= 250){
                    double progress = (double) tick[0] / 250;
                    double arcHeight = Math.sin(progress * Math.PI) * 2; // Высота дуги
                    Location newLocation = start.clone().add(deltaX * tick[0] , arcHeight, deltaZ * tick[0]);
                    ItemForYellowBoss.getBukkitEntity().teleport(newLocation);
                    PacketPlayOutEntityTeleport packetPlayOutEntityTeleport = new PacketPlayOutEntityTeleport(ItemForYellowBoss);
                    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetPlayOutEntityTeleport);
                    tick[0]++;
                }
                if(ticks[0] >= 250){
                    double y = endLoc.getBlockY() + 0.5D + radius[0] * Math.cos(speed * ticks[0]);
                    double z = endLoc.getBlockZ() + 0.5D + radius[0] * Math.sin(speed * ticks[0]);
                    Location teleportLocation = new Location(Bukkit.getWorld("world"), endLoc.getX(), y, z);
                    float red = (float) 0 / 255;
                    float green = (float) 250 / 255;
                    float blue = (float) 154 / 255;
                    PacketPlayOutWorldParticles particles = new PacketPlayOutWorldParticles(EnumParticle.REDSTONE, true, (float) teleportLocation.getBlockX(), (float) teleportLocation.getBlockY(), (float) teleportLocation.getBlockZ(),
                            red, green, blue, 1,0,0);
                    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(particles);
                }
                if(ticks[0] == 250){
                    endLoc.getWorld().strikeLightning(endLoc);
                    tick[1]++;
                }

                if(tick[1] >= 1 && tick[1] <= 250){
                    double progress1 = (double) tick[0] / 250;
                    double arcHeight1 = Math.sin(progress1 * Math.PI) * 2; // Высота дуги

                    Location newLocation1 = startDark.clone().add(deltaX1 * tick[1] , arcHeight1, deltaZ1 * tick[1]);
                    ItemForDarkBoss.getBukkitEntity().teleport(newLocation1);
                    PacketPlayOutEntityTeleport packetPlayOutEntityTeleport1 = new PacketPlayOutEntityTeleport(ItemForDarkBoss);
                    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetPlayOutEntityTeleport1);
                    tick[1]++;
                }
                if(ticks[0] >= 500){
                    double y1 = endLoc.getBlockY() + 0.5D + radius[1] * Math.cos(speed * ticks[0]);
                    double z1 = endLoc.getBlockZ() + 0.5D + radius[1] * Math.sin(speed * ticks[0]);
                    Location teleportLocation1 = new Location(Bukkit.getWorld("world"), endLoc.getX(), y1, z1);
                    float red = (float) 60 / 255;
                    float green = (float) 179 / 255;
                    float blue = (float) 113 / 255;
                    PacketPlayOutWorldParticles particles = new PacketPlayOutWorldParticles(EnumParticle.REDSTONE, true, (float) teleportLocation1.getBlockX(), (float)teleportLocation1.getBlockY(), (float) teleportLocation1.getBlockZ(),
                            red, green, blue, 1,0,0);
                    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(particles);
                }
                if(ticks[0] == 501){
                    endLoc.getWorld().strikeLightning(endLoc);
                    tick[2]++;
                }
                if(tick[2] >= 1 && tick[2] <= 250){
                    double progressBlue = (double) tick[2] / 250;
                    double arcHeightBlue = Math.sin(progressBlue * Math.PI) * 2; // Высота дуги

                    Location newLocationBlue = startBlue.clone().add(deltaXBlue * tick[2] , arcHeightBlue, deltaZBlue * tick[2]);
                    ItemForBlueBoss.getBukkitEntity().teleport(newLocationBlue);
                    PacketPlayOutEntityTeleport packetPlayOutEntityTeleportBlue = new PacketPlayOutEntityTeleport(ItemForBlueBoss);
                    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetPlayOutEntityTeleportBlue);
                    tick[2]++;
                }
                if(ticks[0] >= 750){
                    double y1 = endLoc.getBlockY() + 0.5D + radius[2] * Math.cos(speed * ticks[0]);
                    double z1 = endLoc.getBlockZ() + 0.5D + radius[2] * Math.sin(speed * ticks[0]);
                    Location teleportLocation1 = new Location(Bukkit.getWorld("world"), endLoc.getX(), y1, z1);
                    float red = (float) 154 / 255;
                    float green = (float) 205 / 255;
                    float blue = (float) 50 / 255;
                    PacketPlayOutWorldParticles particles = new PacketPlayOutWorldParticles(EnumParticle.REDSTONE, true, teleportLocation1.getBlockX(), teleportLocation1.getBlockY(), teleportLocation1.getBlockZ(),
                            red, green, blue, 1,0,0);
                    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(particles);
                }

                if(ticks[0] == 751){
                    endLoc.getWorld().strikeLightning(endLoc);
                    tick[3]++;
                }

                if(tick[3] >= 1 && tick[3] <= 250){
                    double progressRed = (double) tick[3] / 250;
                    double arcHeightRed = Math.sin(progressRed * Math.PI) * 2; // Высота дуги

                    Location newLocationRed = startRed.clone().add(deltaXRed * tick[3] , arcHeightRed, deltaZRed * tick[3]);
                    ItemForRedBoss.getBukkitEntity().teleport(newLocationRed);
                    PacketPlayOutEntityTeleport packetPlayOutEntityTeleportRed = new PacketPlayOutEntityTeleport(ItemForRedBoss);
                    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetPlayOutEntityTeleportRed);
                    tick[3]++;
                }
                if(ticks[0] >= 1000){
                    double y1 = endLoc.getBlockY() + 0.5D + radius[3] * Math.cos(speed * ticks[0]);
                    double z1 = endLoc.getBlockZ() + 0.5D + radius[3] * Math.sin(speed * ticks[0]);
                    Location teleportLocation1 = new Location(Bukkit.getWorld("world"), endLoc.getX(), y1, z1);
                    float red = (float) 128 / 255;
                    float green = (float) 128 / 255 ;
                    float blue = (float) 0 / 255;

                    PacketPlayOutWorldParticles particles = new PacketPlayOutWorldParticles(EnumParticle.REDSTONE, true, teleportLocation1.getBlockX(), teleportLocation1.getBlockY(), teleportLocation1.getBlockZ(),
                            red, green, blue, 1,0,0);
                    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(particles);
                }

                ticks[0]++;
            }
        }).runTaskTimer((Plugin)azerot.getInstance(), 1L, 1L);

    }
}
