package azerot.azerot.npcSpawnForRegion;

import azerot.azerot.azerot;
import azerot.azerot.rpgplayer;
import com.comphenix.packetwrapper.WrapperPlayServerEntityHeadRotation;
import com.comphenix.packetwrapper.WrapperPlayServerEntityLook;
import com.comphenix.packetwrapper.WrapperPlayServerEntityTeleport;
import com.comphenix.packetwrapper.WrapperPlayServerRelEntityMoveLook;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftWolf;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class spawnWolf
{

    private static final Map<UUID, Location> wolfLastLocations = new HashMap<>();
    private final int TICK_RATE = 10;
    private static final double FOLLOW_DISTANCE = 2.5;

    private static final double MAX_VERTICAL_SPEED = 0.1;
    private static final double SPEED_MULTIPLIER = 0.1;

    public static void updateRotation(Player p, Location location1, Integer entity) throws InvocationTargetException {
        if(!p.isOnline()){
            return;
        }
        rpgplayer pl = rpgplayer.getRPGPlayer(p);
        Location location = p.getLocation();
        Entity wolf = pl.getSpawnFakeNPC().get(entity);
        if(!p.isSneaking()){
            PacketPlayOutEntityDestroy packDeleteSlime4 = new PacketPlayOutEntityDestroy(pl.getSpawnFakeNPC().get(25).getBukkitEntity().getEntityId());
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packDeleteSlime4);

            PacketPlayOutEntityDestroy packDeleteSlime54 = new PacketPlayOutEntityDestroy(pl.getSpawnFakeNameNPC().get(25).getBukkitEntity().getEntityId());
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packDeleteSlime54);

            PacketPlayOutEntityDestroy packDeleteSlime45 = new PacketPlayOutEntityDestroy(pl.getSpawnFakeNameNPC().get(26).getBukkitEntity().getEntityId());
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packDeleteSlime45);
        }
    }



    public static void WolfFollower(Player p, Entity entity) {
        new BukkitRunnable() {
            @Override
            public void run() {
                updateWolfPositions(p, entity);
            }
        }.runTaskTimer(azerot.getInstance(), 0, 1L);
    }

    private static void updateWolfPositions(Player player, Entity wolf) {
        Location playerLoc = player.getLocation();
        Location wolfLoc = wolf.getBukkitEntity().getLocation();
        Location mobLocation = null;
        if(rpgplayer.getRPGPlayer(player).getLocationWolf() == null){
            mobLocation = wolf.getBukkitEntity().getLocation();
        }else{
            mobLocation = rpgplayer.getRPGPlayer(player).getLocationWolf();
        }
        double SPEED = 0.3;

        double distance = mobLocation.distance(playerLoc);
        if (distance > 2.0D && distance < 5.0D) {
            Vector direction = playerLoc.toVector().subtract(mobLocation.toVector()).normalize();
            direction.multiply(2);


            double newX = playerLoc.getX() - direction.getX();
            double newY = playerLoc.getY();
            double newZ = playerLoc.getZ() - direction.getZ();

            Location newLocation = new Location(mobLocation.getWorld(),newX, newY, newZ);
            rpgplayer.getRPGPlayer(player).setLocationWolf(newLocation);
            wolf.getBukkitEntity().teleport(newLocation);
            WrapperPlayServerEntityTeleport wrapperPlayServerEntityTeleport = new WrapperPlayServerEntityTeleport();
            wrapperPlayServerEntityTeleport.setEntityID(wolf.getBukkitEntity().getEntityId());
            wrapperPlayServerEntityTeleport.setX(newX);
            wrapperPlayServerEntityTeleport.setY(newY);
            wrapperPlayServerEntityTeleport.setZ(newZ);
            wrapperPlayServerEntityTeleport.sendPacket(player);

            Location npcloc = newLocation.setDirection(player.getLocation().subtract(newLocation).toVector());
            float yaw = npcloc.getYaw();
            float pitch = npcloc.getPitch();
            PlayerConnection connection = (((CraftPlayer)player).getHandle()).playerConnection;
            connection.sendPacket((Packet)new PacketPlayOutEntity.PacketPlayOutEntityLook(wolf.getId(), (byte)(yaw % 360.0D * 256.0D / 360.0D), (byte)(int)(pitch % 360.0D * 256.0D / 360.0D), false));
            connection.sendPacket((Packet)new PacketPlayOutEntityHeadRotation((Entity)wolf, (byte)(int)(yaw % 360.0D * 256.0D / 360.0D)));
        }
    }


    public static void spawnWolf(Player p, int stage)
    {
        WorldServer s = ((CraftWorld)p.getWorld()).getHandle();
        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        rpgplayer pl = rpgplayer.getRPGPlayer(p);

        Location location = null;
        if(stage == 1){
            location = new Location(p.getWorld(),-1468, 157,648,-90f,2.0f);
        }
        if(stage == 2){
            location = new Location(p.getWorld(),-1424.7, 142,700,-37.6f,8.6f);
        }
        if(stage == 3){
            location = new Location(p.getWorld(),-1406, 155,789,-60f,20f);
        }


        EntityWolf wolf = new EntityWolf(s);
        assert location != null;
        wolf.setLocation(location.getX(),location.getY(),location.getZ(),location.getYaw(),location.getPitch());
        wolf.setSilent(true);
        wolf.setInvisible(false);
        wolf.setCustomNameVisible(false);
        PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving((EntityLiving) wolf);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
        pl.getSpawnFakeNPC().put(25, wolf);

        EntityArmorStand stand1 = new EntityArmorStand(s);
        stand1.setCustomName("§7Шарик");
        stand1.setLocation(location.getX(),location.getY(),location.getZ(),location.getYaw(),location.getPitch());
        stand1.setCustomNameVisible(true);
        stand1.setNoGravity(true);
        stand1.setInvisible(true);
        stand1.setSilent(true);
        stand1.setSmall(true);
        PacketPlayOutSpawnEntityLiving packet2 = new PacketPlayOutSpawnEntityLiving((EntityLiving) stand1);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet2);

        EntitySlime slime1 = new EntitySlime(s);
        slime1.setLocation(location.getX(),location.getY(),location.getZ(),location.getYaw(),location.getPitch());
        slime1.setCustomNameVisible(false);
        slime1.setNoGravity(true);
        slime1.setInvisible(true);
        slime1.setSilent(true);
        slime1.setSize(-2, false);
        slime1.getBukkitEntity().setPassenger(stand1.getBukkitEntity());
        wolf.getBukkitEntity().setPassenger(slime1.getBukkitEntity());
        PacketPlayOutSpawnEntityLiving packetslime = new PacketPlayOutSpawnEntityLiving((EntityLiving) slime1);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetslime);

        PacketPlayOutMount mount = new PacketPlayOutMount(wolf);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount);
        PacketPlayOutMount mount1 = new PacketPlayOutMount(slime1);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount1);



        pl.getSpawnFakeNameNPC().put(1, slime1);
        pl.getSpawnFakeNameNPC().put(2, stand1);

    }
}
