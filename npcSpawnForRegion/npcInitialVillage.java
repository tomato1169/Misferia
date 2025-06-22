package azerot.azerot.npcSpawnForRegion;

import azerot.azerot.WorldUtils;
import azerot.azerot.azerot;
import azerot.azerot.mobs.MobSystem;
import azerot.azerot.rpgplayer;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.InvocationTargetException;

public class npcInitialVillage
{

    public static void updateRotation(Player p, Location location1, Integer entity) throws InvocationTargetException {
        if(!p.isOnline()){
            return;
        }
        rpgplayer pl = rpgplayer.getRPGPlayer(p);
        Location location = p.getLocation();
        net.minecraft.server.v1_12_R1.Entity elijah = pl.getSpawnFakeNPC().get(entity);
        Location npcloc = elijah.getBukkitEntity().getLocation().setDirection(p.getLocation().subtract(elijah.getBukkitEntity().getLocation()).toVector());
        float yaw = npcloc.getYaw();
        float pitch = npcloc.getPitch();
        PlayerConnection connection = (((CraftPlayer)p).getHandle()).playerConnection;
        connection.sendPacket((Packet)new PacketPlayOutEntity.PacketPlayOutEntityLook(elijah.getId(), (byte)(yaw % 360.0D * 256.0D / 360.0D), (byte)(int)(pitch % 360.0D * 256.0D / 360.0D), false));
        connection.sendPacket((Packet)new PacketPlayOutEntityHeadRotation((Entity)elijah, (byte)(int)(yaw % 360.0D * 256.0D / 360.0D)));

    }


    public static void deSpawn(Player p){
        rpgplayer pl = rpgplayer.getRPGPlayer(p);
        if(!pl.getSpawnFakeNPC().isEmpty()){
            for(Integer value : pl.getSpawnFakeNPC().keySet()){
                PacketPlayOutEntityDestroy packDeleteSlime4 = new PacketPlayOutEntityDestroy(pl.getSpawnFakeNPC().get(value).getBukkitEntity().getEntityId());
                ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packDeleteSlime4);
            }
        }
        if(!pl.getSpawnFakeNameNPC().isEmpty()){
            for(Integer value : pl.getSpawnFakeNameNPC().keySet()){
                PacketPlayOutEntityDestroy packDeleteSlime4 = new PacketPlayOutEntityDestroy(pl.getSpawnFakeNameNPC().get(value).getBukkitEntity().getEntityId());
                ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packDeleteSlime4);
            }
        }
    }

    public static void spawnSkillF(Player p)
    {
        WorldServer s = ((CraftWorld)p.getWorld()).getHandle();
        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        rpgplayer pl = rpgplayer.getRPGPlayer(p);

        EntityZombie elder = new EntityZombie(s);
        elder.setLocation(-3352.8, 55, 1785.09, -90f, 2.9f);
        elder.setSilent(true);
        elder.setInvisible(true);
        elder.setCustomName("§7Для его использования нажмите 'F'");
        elder.setCustomNameVisible(true);
        PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving((EntityLiving) elder);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
        pl.getSpawnFakeEntity().put(1, elder);

        EntityArmorStand stand1 = new EntityArmorStand(s);
        stand1.setCustomName("§7У вас доступна способность 'Прыжок'");
        stand1.setLocation(-3352.8, 55, 1785.09, 9f,9f);
        stand1.setCustomNameVisible(true);
        stand1.setNoGravity(true);
        stand1.setInvisible(true);
        stand1.setSilent(true);
        stand1.setSmall(true);
        PacketPlayOutSpawnEntityLiving packet2 = new PacketPlayOutSpawnEntityLiving((EntityLiving) stand1);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet2);

        EntitySlime slime1 = new EntitySlime(s);
        slime1.setLocation(-3352.8, 55, 1785.09, 9f,9f);
        slime1.setCustomNameVisible(true);
        slime1.setCustomName("§7Для его использования нажмите 'F'");
        slime1.setNoGravity(true);
        slime1.setInvisible(true);
        slime1.setSilent(true);
        slime1.setSize(-2, false);
        slime1.getBukkitEntity().setPassenger(stand1.getBukkitEntity());
        elder.getBukkitEntity().setPassenger(slime1.getBukkitEntity());
        PacketPlayOutSpawnEntityLiving packetslime = new PacketPlayOutSpawnEntityLiving((EntityLiving) slime1);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetslime);

        PacketPlayOutMount mount = new PacketPlayOutMount(elder);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount);
        PacketPlayOutMount mount1 = new PacketPlayOutMount(slime1);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount1);

        pl.getSpawnFakeNameNPC().put(1, slime1);
        pl.getSpawnFakeNameNPC().put(2, stand1);

    }

    public static void spawn(Player p)
    {
        WorldServer s = ((CraftWorld)p.getWorld()).getHandle();
        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        rpgplayer pl = rpgplayer.getRPGPlayer(p);



        EntityZombie elder = new EntityZombie(s);
        elder.setLocation(-3227, 70, 1867, -90f, 2.9f);
        elder.setSilent(true);
        elder.setInvisible(false);
        elder.setCustomNameVisible(false);
        DisguiseAPI.disguiseEntity(elder.getBukkitEntity(), (Disguise) new PlayerDisguise("Rixxz2"));
        Disguise disguise = DisguiseAPI.getDisguise(elder.getBukkitEntity());
        disguise.getWatcher().setCustomNameVisible(false);
        PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving((EntityLiving) elder);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
        pl.getSpawnFakeNPC().put(1, elder);

        EntityArmorStand stand1 = new EntityArmorStand(s);
        stand1.setCustomName("§7Старик");
        stand1.setLocation(-3266,70, 1883, 9f,9f);
        stand1.setCustomNameVisible(true);
        stand1.setNoGravity(true);
        stand1.setInvisible(true);
        stand1.setSilent(true);
        stand1.setSmall(true);
        PacketPlayOutSpawnEntityLiving packet2 = new PacketPlayOutSpawnEntityLiving((EntityLiving) stand1);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet2);

        EntitySlime slime1 = new EntitySlime(s);
        slime1.setLocation(-3266,70, 1883, 9f,9f);
        slime1.setCustomNameVisible(false);
        slime1.setNoGravity(true);
        slime1.setInvisible(true);
        slime1.setSilent(true);
        slime1.setSize(-2, false);
        slime1.getBukkitEntity().setPassenger(stand1.getBukkitEntity());
        elder.getBukkitEntity().setPassenger(slime1.getBukkitEntity());
        PacketPlayOutSpawnEntityLiving packetslime = new PacketPlayOutSpawnEntityLiving((EntityLiving) slime1);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetslime);

        PacketPlayOutMount mount = new PacketPlayOutMount(elder);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount);
        PacketPlayOutMount mount1 = new PacketPlayOutMount(slime1);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount1);



        pl.getSpawnFakeNameNPC().put(1, slime1);
        pl.getSpawnFakeNameNPC().put(2, stand1);


        (new BukkitRunnable() {
            public void run() {
                if(!p.isOnline()){
                    cancel();
                }
                if(pl.getSpawnFakeNPC().get(1) == null){
                    cancel();
                }
                if (Bukkit.getWorld("world") != p.getWorld()){
                    cancel();
                }
                try {
                    if(p.getLocation().distance(((Entity)pl.getSpawnFakeNPC().get(1)).getBukkitEntity().getLocation()) <= 7.0D){
                        updateRotation(p, p.getLocation(), 1);
                    }
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }).runTaskTimer((Plugin)azerot.getInstance(), 5L, 5L);
    }

    public static void spawnGenry(Player p)
    {
        WorldServer s = ((CraftWorld)p.getWorld()).getHandle();
        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        rpgplayer pl = rpgplayer.getRPGPlayer(p);

        EntityZombie genry = new EntityZombie(s);
        genry.setLocation(-3608, 65, 1858, -90f, 2.9f);
        genry.setSilent(true);
        genry.setInvisible(false);
        genry.setCustomNameVisible(false);
        genry.setCustomName("§7Генри");
        genry.getBukkitEntity().setMetadata("id", (MetadataValue)new FixedMetadataValue((Plugin)azerot.getInstance(), 2));
        DisguiseAPI.disguiseEntity(genry.getBukkitEntity(), (Disguise) new PlayerDisguise("omgakorean"));
        Disguise disguise = DisguiseAPI.getDisguise(genry.getBukkitEntity());
        disguise.getWatcher().setCustomNameVisible(false);
        PacketPlayOutSpawnEntityLiving packet1 = new PacketPlayOutSpawnEntityLiving((EntityLiving) genry);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet1);
        pl.getSpawnFakeNPC().put(3, genry);

        EntityArmorStand stand = new EntityArmorStand(s);
        stand.setCustomName("§7Продавец");
        stand.setLocation(-3266,70, 1883, 9f,9f);
        stand.setCustomNameVisible(true);
        stand.setNoGravity(true);
        stand.setInvisible(true);
        stand.setSilent(true);
        stand.setSmall(true);
        PacketPlayOutSpawnEntityLiving packet2 = new PacketPlayOutSpawnEntityLiving((EntityLiving) stand);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet2);

        EntitySlime slime = new EntitySlime(s);
        slime.setLocation(-3266,70, 1883, 9f,9f);
        slime.setCustomNameVisible(false);
        slime.setNoGravity(true);
        slime.setInvisible(true);
        slime.setSilent(true);
        slime.setSize(-2, false);
        slime.getBukkitEntity().setPassenger(stand.getBukkitEntity());
        genry.getBukkitEntity().setPassenger(slime.getBukkitEntity());
        PacketPlayOutSpawnEntityLiving packetslime = new PacketPlayOutSpawnEntityLiving((EntityLiving) slime);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetslime);

        PacketPlayOutMount mount = new PacketPlayOutMount(genry);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount);
        PacketPlayOutMount mount1 = new PacketPlayOutMount(slime);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount1);

        pl.getSpawnFakeNameNPC().put(5, slime);
        pl.getSpawnFakeNameNPC().put(6, stand);

        (new BukkitRunnable() {
            public void run() {
                if(!p.isOnline()){
                    cancel();
                }
                if(pl.getSpawnFakeNPC().get(3) == null){
                    cancel();
                }
                if (Bukkit.getWorld("world") != p.getWorld()){
                    cancel();
                }
                try {
                    if(p.getLocation().distance(((Entity)pl.getSpawnFakeNPC().get(3)).getBukkitEntity().getLocation()) <= 7.0D){
                        updateRotation(p, p.getLocation(), 3);
                    }
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }).runTaskTimer((Plugin)azerot.getInstance(), 5L, 5L);


    }

    public static void spawnRegion3(Player p)
    {
        WorldServer s = ((CraftWorld)p.getWorld()).getHandle();
        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        rpgplayer pl = rpgplayer.getRPGPlayer(p);

        EntityZombie shop = new EntityZombie(s);
        shop.setLocation(-4009, 66, 1941, -90f, 2.9f);
        shop.setSilent(true);
        shop.setInvisible(false);
        shop.setCustomNameVisible(false);
        shop.getBukkitEntity().setMetadata("id", (MetadataValue)new FixedMetadataValue((Plugin)azerot.getInstance(), 2));
        DisguiseAPI.disguiseEntity(shop.getBukkitEntity(), (Disguise) new PlayerDisguise("Zakviel"));
        Disguise disguise4 = DisguiseAPI.getDisguise(shop.getBukkitEntity());
        disguise4.getWatcher().setCustomNameVisible(false);
        PacketPlayOutSpawnEntityLiving packet1 = new PacketPlayOutSpawnEntityLiving((EntityLiving) shop);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet1);
        pl.getSpawnFakeNPC().put(2, shop);

        EntityZombie sell = new EntityZombie(s);
        sell.setLocation(-4004, 66, 1955, -90f, 2.9f);
        sell.setSilent(true);
        sell.setInvisible(false);
        sell.setCustomNameVisible(false);
        pl.getSpawnFakeNPC().put(4, sell);
        sell.getBukkitEntity().setMetadata("id", (MetadataValue)new FixedMetadataValue((Plugin)azerot.getInstance(), 4));
        DisguiseAPI.disguiseEntity(sell.getBukkitEntity(), (Disguise) new PlayerDisguise("Zakviel"));
        Disguise disguise3 = DisguiseAPI.getDisguise(sell.getBukkitEntity());
        disguise3.getWatcher().setCustomNameVisible(false);
        PacketPlayOutSpawnEntityLiving packet2 = new PacketPlayOutSpawnEntityLiving((EntityLiving) sell);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet2);

        EntityZombie capitan = new EntityZombie(s);
        capitan.setLocation(-4015, 70.5, 1885, -91f, 0f);
        capitan.setSilent(true);
        capitan.setInvisible(false);
        capitan.setCustomNameVisible(false);
        capitan.getBukkitEntity().setMetadata("id", (MetadataValue)new FixedMetadataValue((Plugin)azerot.getInstance(), 5));
        DisguiseAPI.disguiseEntity(capitan.getBukkitEntity(), (Disguise) new PlayerDisguise("Hollure"));
        Disguise disguise2 = DisguiseAPI.getDisguise(capitan.getBukkitEntity());
        disguise2.getWatcher().setCustomNameVisible(false);
        PacketPlayOutSpawnEntityLiving packet3 = new PacketPlayOutSpawnEntityLiving((EntityLiving) capitan);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet3);
        pl.getSpawnFakeNPC().put(5, capitan);

        EntityZombie Manager = new EntityZombie(s);
        Manager.setLocation(-4033, 68, 1976, -37f, -1.2f);
        Manager.setSilent(true);
        Manager.setInvisible(false);
        Manager.setCustomNameVisible(false);
        Manager.getBukkitEntity().setMetadata("id", (MetadataValue)new FixedMetadataValue((Plugin)azerot.getInstance(), 7));
        DisguiseAPI.disguiseEntity(Manager.getBukkitEntity(), (Disguise) new PlayerDisguise("johnlmonkey"));
        Disguise disguise = DisguiseAPI.getDisguise(Manager.getBukkitEntity());
        disguise.getWatcher().setCustomNameVisible(false);
        PacketPlayOutSpawnEntityLiving packet5 = new PacketPlayOutSpawnEntityLiving((EntityLiving) Manager);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet5);
        pl.getSpawnFakeNPC().put(7, Manager);

        (new BukkitRunnable() {
            public void run() {
                if(!p.isOnline()){
                    cancel();
                }
                if(pl.getSpawnFakeNPC().get(7) == null && pl.getSpawnFakeNPC().get(5) == null && pl.getSpawnFakeNPC().get(4) == null && pl.getSpawnFakeNPC().get(2) == null){
                    cancel();
                }
                if (Bukkit.getWorld("world") != p.getWorld()){
                    cancel();
                }
                try {
                    if(p.getLocation().distance(((Entity)pl.getSpawnFakeNPC().get(7)).getBukkitEntity().getLocation()) <= 7.0D){
                        updateRotation(p, p.getLocation(), 7);
                    }

                    if(p.getLocation().distance(((Entity)pl.getSpawnFakeNPC().get(5)).getBukkitEntity().getLocation()) <= 7.0D){
                        updateRotation(p, p.getLocation(), 5);
                    }
                    if(p.getLocation().distance(((Entity)pl.getSpawnFakeNPC().get(4)).getBukkitEntity().getLocation()) <= 7.0D){
                        updateRotation(p, p.getLocation(), 4);
                    }
                    if(p.getLocation().distance(((Entity)pl.getSpawnFakeNPC().get(2)).getBukkitEntity().getLocation()) <= 7.0D){
                        updateRotation(p, p.getLocation(), 2);
                    }

                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }).runTaskTimer((Plugin)azerot.getInstance(), 5L, 5L);

        EntityArmorStand stand3 = new EntityArmorStand(s);
        stand3.setCustomName("§7Капитан");
        stand3.setLocation(-4015, 70.5, 1885, 9f,9f);
        stand3.setCustomNameVisible(true);
        stand3.setNoGravity(true);
        stand3.setInvisible(true);
        stand3.setSilent(true);
        stand3.setSmall(true);
        PacketPlayOutSpawnEntityLiving packet233 = new PacketPlayOutSpawnEntityLiving((EntityLiving) stand3);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet233);

        EntitySlime slime3 = new EntitySlime(s);
        slime3.setLocation(-4015, 70.5, 1885, 9f,9f);
        slime3.setCustomNameVisible(false);
        slime3.setNoGravity(true);
        slime3.setInvisible(true);
        slime3.setSilent(true);
        slime3.setSize(-2, false);
        slime3.getBukkitEntity().setPassenger(stand3.getBukkitEntity());
        capitan.getBukkitEntity().setPassenger(slime3.getBukkitEntity());
        PacketPlayOutSpawnEntityLiving packetslime4 = new PacketPlayOutSpawnEntityLiving((EntityLiving) slime3);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetslime4);

        PacketPlayOutMount mount26 = new PacketPlayOutMount(capitan);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount26);
        PacketPlayOutMount mount27 = new PacketPlayOutMount(slime3);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount27);

        pl.getSpawnFakeNameNPC().put(7, slime3);
        pl.getSpawnFakeNameNPC().put(8, stand3);

        EntityArmorStand stand2 = new EntityArmorStand(s);
        stand2.setCustomName("§7Управляющий");
        stand2.setLocation(-4033, 68, 1976, 9f,9f);
        stand2.setCustomNameVisible(true);
        stand2.setNoGravity(true);
        stand2.setInvisible(true);
        stand2.setSilent(true);
        stand2.setSmall(true);
        PacketPlayOutSpawnEntityLiving packet24 = new PacketPlayOutSpawnEntityLiving((EntityLiving) stand2);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet24);

        EntitySlime slime2 = new EntitySlime(s);
        slime2.setLocation(-4033, 68, 1976, 9f,9f);
        slime2.setCustomNameVisible(false);
        slime2.setNoGravity(true);
        slime2.setInvisible(true);
        slime2.setSilent(true);
        slime2.setSize(-2, false);
        slime2.getBukkitEntity().setPassenger(stand2.getBukkitEntity());
        Manager.getBukkitEntity().setPassenger(slime2.getBukkitEntity());
        PacketPlayOutSpawnEntityLiving packetslime2 = new PacketPlayOutSpawnEntityLiving((EntityLiving) slime2);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetslime2);

        PacketPlayOutMount mount24 = new PacketPlayOutMount(Manager);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount24);
        PacketPlayOutMount mount25 = new PacketPlayOutMount(slime2);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount25);

        pl.getSpawnFakeNameNPC().put(5, slime2);
        pl.getSpawnFakeNameNPC().put(6, stand2);



        EntityArmorStand stand1 = new EntityArmorStand(s);
        stand1.setCustomName("§7Барахольщик");
        stand1.setLocation(-4004, 66, 1955, 9f,9f);
        stand1.setCustomNameVisible(true);
        stand1.setNoGravity(true);
        stand1.setInvisible(true);
        stand1.setSilent(true);
        stand1.setSmall(true);
        PacketPlayOutSpawnEntityLiving packet23 = new PacketPlayOutSpawnEntityLiving((EntityLiving) stand1);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet23);



        EntitySlime slime1 = new EntitySlime(s);
        slime1.setLocation(-4004, 66, 1955, 9f,9f);
        slime1.setCustomNameVisible(false);
        slime1.setNoGravity(true);
        slime1.setInvisible(true);
        slime1.setSilent(true);
        slime1.setSize(-2, false);
        slime1.getBukkitEntity().setPassenger(stand1.getBukkitEntity());
        sell.getBukkitEntity().setPassenger(slime1.getBukkitEntity());
        PacketPlayOutSpawnEntityLiving packetslime3 = new PacketPlayOutSpawnEntityLiving((EntityLiving) slime1);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetslime3);

        PacketPlayOutMount mount12 = new PacketPlayOutMount(sell);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount12);
        PacketPlayOutMount mount11 = new PacketPlayOutMount(slime1);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount11);

        pl.getSpawnFakeNameNPC().put(5, slime1);
        pl.getSpawnFakeNameNPC().put(6, stand1);

        EntityArmorStand stand = new EntityArmorStand(s);
        stand.setCustomName("§7Продавец");
        stand.setLocation(-4009, 66, 1941, 9f,9f);
        stand.setCustomNameVisible(true);
        stand.setNoGravity(true);
        stand.setInvisible(true);
        stand.setSilent(true);
        stand.setSmall(true);
        PacketPlayOutSpawnEntityLiving packet22 = new PacketPlayOutSpawnEntityLiving((EntityLiving) stand);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet22);

        EntitySlime slime = new EntitySlime(s);
        slime.setLocation(-4009, 66, 1941, 9f,9f);
        slime.setCustomNameVisible(false);
        slime.setNoGravity(true);
        slime.setInvisible(true);
        slime.setSilent(true);
        slime.setSize(-2, false);
        slime.getBukkitEntity().setPassenger(stand.getBukkitEntity());
        shop.getBukkitEntity().setPassenger(slime.getBukkitEntity());
        PacketPlayOutSpawnEntityLiving packetslime = new PacketPlayOutSpawnEntityLiving((EntityLiving) slime);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetslime);

        PacketPlayOutMount mount = new PacketPlayOutMount(shop);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount);
        PacketPlayOutMount mount1 = new PacketPlayOutMount(slime);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount1);

        pl.getSpawnFakeNameNPC().put(3, slime);
        pl.getSpawnFakeNameNPC().put(4, stand);

    }

    public static void spawnDeadIsland(Player p)
    {
        WorldServer s = ((CraftWorld)p.getWorld()).getHandle();
        rpgplayer pl = rpgplayer.getRPGPlayer(p);

        EntityZombie vanessa = new EntityZombie(s);
        vanessa.setLocation(-3877, 71, 2763 , 89f, 2.9f);
        vanessa.setSilent(true);
        vanessa.setInvisible(false);
        vanessa.setCustomNameVisible(false);
        vanessa.getBukkitEntity().setMetadata("id", (MetadataValue)new FixedMetadataValue((Plugin)azerot.getInstance(), 8));
        DisguiseAPI.disguiseEntity(vanessa.getBukkitEntity(), (Disguise) new PlayerDisguise("omgakorean"));
        Disguise disguise4 = DisguiseAPI.getDisguise(vanessa.getBukkitEntity());
        disguise4.getWatcher().setCustomNameVisible(false);
        PacketPlayOutSpawnEntityLiving packet1 = new PacketPlayOutSpawnEntityLiving((EntityLiving) vanessa);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet1);
        pl.getSpawnFakeNPC().put(8, vanessa);

        EntityZombie petrus = new EntityZombie(s);
        petrus.setLocation(-3864, 68, 2757 , 89f, 2.9f);
        petrus.setSilent(true);
        petrus.setInvisible(false);
        petrus.setCustomNameVisible(false);
        petrus.getBukkitEntity().setMetadata("id", (MetadataValue)new FixedMetadataValue((Plugin)azerot.getInstance(), 8));
        DisguiseAPI.disguiseEntity(petrus.getBukkitEntity(), (Disguise) new PlayerDisguise("omgakorean"));
        Disguise disguise3 = DisguiseAPI.getDisguise(petrus.getBukkitEntity());
        disguise3.getWatcher().setCustomNameVisible(false);
        PacketPlayOutSpawnEntityLiving packet2 = new PacketPlayOutSpawnEntityLiving((EntityLiving) petrus);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet2);
        pl.getSpawnFakeNPC().put(9, petrus);


        EntityZombie shop = new EntityZombie(s);
        shop.setLocation(-3866, 67, 2746, 150f, 2.9f);
        shop.setSilent(true);
        shop.setInvisible(false);
        shop.setCustomNameVisible(false);
        shop.getBukkitEntity().setMetadata("id", (MetadataValue)new FixedMetadataValue((Plugin)azerot.getInstance(), 2));
        DisguiseAPI.disguiseEntity(shop.getBukkitEntity(), (Disguise) new PlayerDisguise("Zakviel"));
        Disguise disguise2 = DisguiseAPI.getDisguise(shop.getBukkitEntity());
        disguise2.getWatcher().setCustomNameVisible(false);
        PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving((EntityLiving) shop);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
        pl.getSpawnFakeNPC().put(2, shop);

        EntityZombie sell = new EntityZombie(s);
        sell.setLocation(-3878, 70, 2756, -88f, 2.9f);
        sell.setSilent(true);
        sell.setInvisible(false);
        sell.setCustomNameVisible(true);
        sell.getBukkitEntity().setMetadata("id", (MetadataValue)new FixedMetadataValue((Plugin)azerot.getInstance(), 4));
        DisguiseAPI.disguiseEntity(sell.getBukkitEntity(), (Disguise) new PlayerDisguise("Zakviel"));
        Disguise disguise1 = DisguiseAPI.getDisguise(sell.getBukkitEntity());
        disguise1.getWatcher().setCustomNameVisible(false);
        PacketPlayOutSpawnEntityLiving packet22 = new PacketPlayOutSpawnEntityLiving((EntityLiving) sell);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet22);
        pl.getSpawnFakeNPC().put(4, sell);

        EntityZombie capitan = new EntityZombie(s);
        capitan.setLocation(-3875.3, 66, 2744.8, -91f, 0f);
        capitan.setSilent(true);
        capitan.setInvisible(false);
        capitan.setCustomNameVisible(false);
        capitan.getBukkitEntity().setMetadata("id", (MetadataValue)new FixedMetadataValue((Plugin)azerot.getInstance(), 10));
        DisguiseAPI.disguiseEntity(capitan.getBukkitEntity(), (Disguise) new PlayerDisguise("Hollure"));
        Disguise disguise = DisguiseAPI.getDisguise(capitan.getBukkitEntity());
        disguise.getWatcher().setCustomNameVisible(false);
        PacketPlayOutSpawnEntityLiving packet3 = new PacketPlayOutSpawnEntityLiving((EntityLiving) capitan);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet3);
        pl.getSpawnFakeNPC().put(10, capitan);

        (new BukkitRunnable() {
            public void run() {
                if(!p.isOnline()){
                    cancel();
                }
                if(pl.getSpawnFakeNPC().get(10) == null && pl.getSpawnFakeNPC().get(9) == null && pl.getSpawnFakeNPC().get(8) == null && pl.getSpawnFakeNPC().get(4) == null && pl.getSpawnFakeNPC().get(2) == null){
                    cancel();
                }
                if (Bukkit.getWorld("world") != p.getWorld()){
                    cancel();
                }
                try {
                    if(p.getLocation().distance(((Entity)pl.getSpawnFakeNPC().get(10)).getBukkitEntity().getLocation()) <= 7.0D){
                        updateRotation(p, p.getLocation(), 10);
                    }
                    if(p.getLocation().distance(((Entity)pl.getSpawnFakeNPC().get(9)).getBukkitEntity().getLocation()) <= 7.0D){
                        updateRotation(p, p.getLocation(), 9);
                    }
                    if(p.getLocation().distance(((Entity)pl.getSpawnFakeNPC().get(8)).getBukkitEntity().getLocation()) <= 7.0D){
                        updateRotation(p, p.getLocation(), 8);
                    }
                    if(p.getLocation().distance(((Entity)pl.getSpawnFakeNPC().get(4)).getBukkitEntity().getLocation()) <= 7.0D){
                        updateRotation(p, p.getLocation(), 4);
                    }
                    if(p.getLocation().distance(((Entity)pl.getSpawnFakeNPC().get(2)).getBukkitEntity().getLocation()) <= 7.0D){
                        updateRotation(p, p.getLocation(), 2);
                    }

                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }).runTaskTimer((Plugin)azerot.getInstance(), 5L, 5L);

        EntityArmorStand stand5 = new EntityArmorStand(s);
        stand5.setCustomName("§7Петрус");
        stand5.setLocation(-3864, 68, 2757, 9f,9f);
        stand5.setCustomNameVisible(true);
        stand5.setNoGravity(true);
        stand5.setInvisible(true);
        stand5.setSilent(true);
        stand5.setSmall(true);
        PacketPlayOutSpawnEntityLiving packet255 = new PacketPlayOutSpawnEntityLiving((EntityLiving) stand5);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet255);

        EntitySlime slime5 = new EntitySlime(s);
        slime5.setLocation(-3864, 68, 2757, 9f,9f);
        slime5.setCustomNameVisible(false);
        slime5.setNoGravity(true);
        slime5.setInvisible(true);
        slime5.setSilent(true);
        slime5.setSize(-2, false);
        slime5.getBukkitEntity().setPassenger(stand5.getBukkitEntity());
        petrus.getBukkitEntity().setPassenger(slime5.getBukkitEntity());
        PacketPlayOutSpawnEntityLiving packetslime55 = new PacketPlayOutSpawnEntityLiving((EntityLiving) slime5);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetslime55);

        PacketPlayOutMount mount55 = new PacketPlayOutMount(petrus);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount55);
        PacketPlayOutMount mount555 = new PacketPlayOutMount(slime5);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount555);

        pl.getSpawnFakeNameNPC().put(11, slime5);
        pl.getSpawnFakeNameNPC().put(12, stand5);

        EntityArmorStand stand3 = new EntityArmorStand(s);
        stand3.setCustomName("§7Капитан");
        stand3.setLocation(-3875.3, 66, 2744.8, 9f,9f);
        stand3.setCustomNameVisible(true);
        stand3.setNoGravity(true);
        stand3.setInvisible(true);
        stand3.setSilent(true);
        stand3.setSmall(true);
        PacketPlayOutSpawnEntityLiving packet233 = new PacketPlayOutSpawnEntityLiving((EntityLiving) stand3);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet233);

        EntitySlime slime3 = new EntitySlime(s);
        slime3.setLocation(-3875.3, 66, 2744.8, 9f,9f);
        slime3.setCustomNameVisible(false);
        slime3.setNoGravity(true);
        slime3.setInvisible(true);
        slime3.setSilent(true);
        slime3.setSize(-2, false);
        slime3.getBukkitEntity().setPassenger(stand3.getBukkitEntity());
        capitan.getBukkitEntity().setPassenger(slime3.getBukkitEntity());
        PacketPlayOutSpawnEntityLiving packetslime4 = new PacketPlayOutSpawnEntityLiving((EntityLiving) slime3);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetslime4);

        PacketPlayOutMount mount26 = new PacketPlayOutMount(capitan);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount26);
        PacketPlayOutMount mount27 = new PacketPlayOutMount(slime3);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount27);

        pl.getSpawnFakeNameNPC().put(9, slime3);
        pl.getSpawnFakeNameNPC().put(10, stand3);

        EntityArmorStand stand2 = new EntityArmorStand(s);
        stand2.setCustomName("§7Ванесса");
        stand2.setLocation(-3877, 71, 2763, 9f,9f);
        stand2.setCustomNameVisible(true);
        stand2.setNoGravity(true);
        stand2.setInvisible(true);
        stand2.setSilent(true);
        stand2.setSmall(true);
        PacketPlayOutSpawnEntityLiving packet24 = new PacketPlayOutSpawnEntityLiving((EntityLiving) stand2);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet24);

        EntitySlime slime2 = new EntitySlime(s);
        slime2.setLocation(-3877, 71, 2763, 9f,9f);
        slime2.setCustomNameVisible(false);
        slime2.setNoGravity(true);
        slime2.setInvisible(true);
        slime2.setSilent(true);
        slime2.setSize(-2, false);
        slime2.getBukkitEntity().setPassenger(stand2.getBukkitEntity());
        vanessa.getBukkitEntity().setPassenger(slime2.getBukkitEntity());
        PacketPlayOutSpawnEntityLiving packetslime2 = new PacketPlayOutSpawnEntityLiving((EntityLiving) slime2);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetslime2);

        PacketPlayOutMount mount24 = new PacketPlayOutMount(vanessa);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount24);
        PacketPlayOutMount mount25 = new PacketPlayOutMount(slime2);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount25);

        pl.getSpawnFakeNameNPC().put(7, slime2);
        pl.getSpawnFakeNameNPC().put(8, stand2);



        EntityArmorStand stand1 = new EntityArmorStand(s);
        stand1.setCustomName("§7Барахольщик");
        stand1.setLocation(-3878, 70, 2756, 9f,9f);
        stand1.setCustomNameVisible(true);
        stand1.setNoGravity(true);
        stand1.setInvisible(true);
        stand1.setSilent(true);
        stand1.setSmall(true);
        PacketPlayOutSpawnEntityLiving packet23 = new PacketPlayOutSpawnEntityLiving((EntityLiving) stand1);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet23);

        EntitySlime slime1 = new EntitySlime(s);
        slime1.setLocation(-3878, 70, 2756, 9f,9f);
        slime1.setCustomNameVisible(false);
        slime1.setNoGravity(true);
        slime1.setInvisible(true);
        slime1.setSilent(true);
        slime1.setSize(-2, false);
        slime1.getBukkitEntity().setPassenger(stand1.getBukkitEntity());
        sell.getBukkitEntity().setPassenger(slime1.getBukkitEntity());
        PacketPlayOutSpawnEntityLiving packetslime3 = new PacketPlayOutSpawnEntityLiving((EntityLiving) slime1);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetslime3);

        PacketPlayOutMount mount12 = new PacketPlayOutMount(sell);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount12);
        PacketPlayOutMount mount11 = new PacketPlayOutMount(slime1);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount11);

        pl.getSpawnFakeNameNPC().put(5, slime1);
        pl.getSpawnFakeNameNPC().put(6, stand1);

        EntityArmorStand stand = new EntityArmorStand(s);
        stand.setCustomName("§7Продавец");
        stand.setLocation(-3866, 67, 2746, 9f,9f);
        stand.setCustomNameVisible(true);
        stand.setNoGravity(true);
        stand.setInvisible(true);
        stand.setSilent(true);
        stand.setSmall(true);
        PacketPlayOutSpawnEntityLiving packet222 = new PacketPlayOutSpawnEntityLiving((EntityLiving) stand);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet222);

        EntitySlime slime = new EntitySlime(s);
        slime.setLocation(-3866, 67, 2746, 9f,9f);
        slime.setCustomNameVisible(false);
        slime.setNoGravity(true);
        slime.setInvisible(true);
        slime.setSilent(true);
        slime.setSize(-2, false);
        slime.getBukkitEntity().setPassenger(stand.getBukkitEntity());
        shop.getBukkitEntity().setPassenger(slime.getBukkitEntity());
        PacketPlayOutSpawnEntityLiving packetslime = new PacketPlayOutSpawnEntityLiving((EntityLiving) slime);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetslime);

        PacketPlayOutMount mount = new PacketPlayOutMount(shop);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount);
        PacketPlayOutMount mount1 = new PacketPlayOutMount(slime);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount1);

        pl.getSpawnFakeNameNPC().put(3, slime);
        pl.getSpawnFakeNameNPC().put(4, stand);

    }

    public static void deSpawnAnri(Player p){
        rpgplayer pl = rpgplayer.getRPGPlayer(p);


        for(Integer value : pl.getSpawnFakeNPC().keySet()){
            PacketPlayOutEntityDestroy packDeleteSlime4 = new PacketPlayOutEntityDestroy(pl.getSpawnFakeNPC().get(value).getBukkitEntity().getEntityId());
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packDeleteSlime4);
        }

        for(Integer value : pl.getSpawnFakeNameNPC().keySet()){
            PacketPlayOutEntityDestroy packDeleteSlime4 = new PacketPlayOutEntityDestroy(pl.getSpawnFakeNameNPC().get(value).getBukkitEntity().getEntityId());
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packDeleteSlime4);
        }
    }

    public static void spawnAnri(Player p){
        WorldServer s = ((CraftWorld)p.getWorld()).getHandle();
        rpgplayer pl = rpgplayer.getRPGPlayer(p);

        EntityZombie shop = new EntityZombie(s);
        shop.setLocation(-4160, 161, 2899, 150f, 2.9f);
        shop.setSilent(true);
        shop.setInvisible(false);
        shop.setCustomNameVisible(false);
        shop.getBukkitEntity().setMetadata("id", (MetadataValue)new FixedMetadataValue((Plugin)azerot.getInstance(), 2));
        DisguiseAPI.disguiseEntity(shop.getBukkitEntity(), (Disguise) new PlayerDisguise("Zakviel"));
        Disguise disguise2 = DisguiseAPI.getDisguise(shop.getBukkitEntity());
        disguise2.getWatcher().setCustomNameVisible(false);
        PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving((EntityLiving) shop);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
        pl.getSpawnFakeNPC().put(2, shop);

        EntityZombie sell = new EntityZombie(s);
        sell.setLocation(-4150.208, 161, 2899, -88f, 2.9f);
        sell.setSilent(true);
        sell.setInvisible(false);
        sell.setCustomNameVisible(false);
        DisguiseAPI.disguiseEntity(sell.getBukkitEntity(), (Disguise) new PlayerDisguise("Zakviel"));
        Disguise disguise1 = DisguiseAPI.getDisguise(sell.getBukkitEntity());
        disguise1.getWatcher().setCustomNameVisible(false);
        PacketPlayOutSpawnEntityLiving packet22 = new PacketPlayOutSpawnEntityLiving((EntityLiving) sell);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet22);
        pl.getSpawnFakeNPC().put(4, sell);

        EntityZombie anri = new EntityZombie(s);
        anri.setLocation(-4157.2, 161, 2893.7, -88f, 2.9f);
        anri.setSilent(true);
        anri.setInvisible(false);
        anri.setCustomNameVisible(false);
        anri.getBukkitEntity().setMetadata("id", (MetadataValue)new FixedMetadataValue((Plugin)azerot.getInstance(), 4));
        DisguiseAPI.disguiseEntity(anri.getBukkitEntity(), (Disguise) new PlayerDisguise("Zakviel"));
        Disguise disguise = DisguiseAPI.getDisguise(anri.getBukkitEntity());
        disguise.getWatcher().setCustomNameVisible(false);
        PacketPlayOutSpawnEntityLiving packe = new PacketPlayOutSpawnEntityLiving((EntityLiving) anri);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packe);
        pl.getSpawnFakeNPC().put(11, anri);

        (new BukkitRunnable() {
            public void run() {
                if(!p.isOnline()){
                    cancel();
                }
                if(pl.getSpawnFakeNPC().get(11) == null && pl.getSpawnFakeNPC().get(4) == null && pl.getSpawnFakeNPC().get(2) == null){
                    cancel();
                }
                if (Bukkit.getWorld("world") != p.getWorld()){
                    cancel();
                }
                try {
                    if(p.getLocation().distance(((Entity)pl.getSpawnFakeNPC().get(11)).getBukkitEntity().getLocation()) <= 7.0D){
                        updateRotation(p, p.getLocation(), 11);
                    }
                    if(p.getLocation().distance(((Entity)pl.getSpawnFakeNPC().get(4)).getBukkitEntity().getLocation()) <= 7.0D){
                        updateRotation(p, p.getLocation(), 4);
                    }
                    if(p.getLocation().distance(((Entity)pl.getSpawnFakeNPC().get(2)).getBukkitEntity().getLocation()) <= 7.0D){
                        updateRotation(p, p.getLocation(), 2);
                    }

                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }).runTaskTimer((Plugin)azerot.getInstance(), 5L, 5L);

        EntityArmorStand stand1 = new EntityArmorStand(s);
        stand1.setCustomName("§7Барахольщик");
        stand1.setLocation(-4150.208, 161, 2899, 9f,9f);
        stand1.setCustomNameVisible(true);
        stand1.setNoGravity(true);
        stand1.setInvisible(true);
        stand1.setSilent(true);
        stand1.setSmall(true);
        PacketPlayOutSpawnEntityLiving packet23 = new PacketPlayOutSpawnEntityLiving((EntityLiving) stand1);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet23);



        EntitySlime slime1 = new EntitySlime(s);
        slime1.setLocation(-4150.208, 161, 2899, 9f,9f);
        slime1.setCustomNameVisible(false);
        slime1.setNoGravity(true);
        slime1.setInvisible(true);
        slime1.setSilent(true);
        slime1.setSize(-2, false);
        slime1.getBukkitEntity().setPassenger(stand1.getBukkitEntity());
        sell.getBukkitEntity().setPassenger(slime1.getBukkitEntity());
        PacketPlayOutSpawnEntityLiving packetslime3 = new PacketPlayOutSpawnEntityLiving((EntityLiving) slime1);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetslime3);

        PacketPlayOutMount mount12 = new PacketPlayOutMount(sell);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount12);
        PacketPlayOutMount mount11 = new PacketPlayOutMount(slime1);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount11);

        pl.getSpawnFakeNameNPC().put(5, slime1);
        pl.getSpawnFakeNameNPC().put(6, stand1);

        EntityArmorStand stand = new EntityArmorStand(s);
        stand.setCustomName("§7Продавец");
        stand.setLocation(-4160, 161, 2899, 9f,9f);
        stand.setCustomNameVisible(true);
        stand.setNoGravity(true);
        stand.setInvisible(true);
        stand.setSilent(true);
        stand.setSmall(true);
        PacketPlayOutSpawnEntityLiving packet222 = new PacketPlayOutSpawnEntityLiving((EntityLiving) stand);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet222);

        EntitySlime slime = new EntitySlime(s);
        slime.setLocation(-4160, 161, 2899, 9f,9f);
        slime.setCustomNameVisible(false);
        slime.setNoGravity(true);
        slime.setInvisible(true);
        slime.setSilent(true);
        slime.setSize(-2, false);
        slime.getBukkitEntity().setPassenger(stand.getBukkitEntity());
        shop.getBukkitEntity().setPassenger(slime.getBukkitEntity());
        PacketPlayOutSpawnEntityLiving packetslime = new PacketPlayOutSpawnEntityLiving((EntityLiving) slime);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetslime);

        PacketPlayOutMount mount = new PacketPlayOutMount(shop);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount);
        PacketPlayOutMount mount1 = new PacketPlayOutMount(slime);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount1);

        pl.getSpawnFakeNameNPC().put(3, slime);
        pl.getSpawnFakeNameNPC().put(4, stand);

        EntityArmorStand stand2 = new EntityArmorStand(s);
        stand2.setCustomName("§7Анри");
        stand2.setLocation(-4157, 161, 2892, 9f,9f);
        stand2.setCustomNameVisible(true);
        stand2.setNoGravity(true);
        stand2.setInvisible(true);
        stand2.setSilent(true);
        stand2.setSmall(true);
        PacketPlayOutSpawnEntityLiving packet24 = new PacketPlayOutSpawnEntityLiving((EntityLiving) stand2);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet24);

        EntitySlime slime2 = new EntitySlime(s);
        slime2.setLocation(-4157, 161, 2892, 9f,9f);
        slime2.setCustomNameVisible(false);
        slime2.setNoGravity(true);
        slime2.setInvisible(true);
        slime2.setSilent(true);
        slime2.setSize(-2, false);
        slime2.getBukkitEntity().setPassenger(stand2.getBukkitEntity());
        anri.getBukkitEntity().setPassenger(slime2.getBukkitEntity());
        PacketPlayOutSpawnEntityLiving packetslime2 = new PacketPlayOutSpawnEntityLiving((EntityLiving) slime2);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetslime2);

        PacketPlayOutMount mount24 = new PacketPlayOutMount(anri);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount24);
        PacketPlayOutMount mount25 = new PacketPlayOutMount(slime2);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount25);

        pl.getSpawnFakeNameNPC().put(7, slime2);
        pl.getSpawnFakeNameNPC().put(8, stand2);
    }
    public static void spawnSaveAnri(Player p){
        WorldServer s = ((CraftWorld)p.getWorld()).getHandle();
        rpgplayer pl = rpgplayer.getRPGPlayer(p);

        EntityZombie anri = new EntityZombie(s);
        anri.setLocation(-4262.5, 156, 2950.5, -88f, 2.9f);
        anri.setSilent(true);
        anri.setInvisible(false);
        DisguiseAPI.disguiseEntity(anri.getBukkitEntity(), (Disguise) new PlayerDisguise("Zakviel"));
        Disguise disguise = DisguiseAPI.getDisguise(anri.getBukkitEntity());
        disguise.getWatcher().setCustomNameVisible(false);
        PacketPlayOutSpawnEntityLiving packe = new PacketPlayOutSpawnEntityLiving((EntityLiving) anri);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packe);
        pl.getSpawnFakeNPC().put(12, anri);

        (new BukkitRunnable() {
            public void run() {
                if(!p.isOnline()){
                    cancel();
                }
                if(pl.getSpawnFakeNPC().get(12) == null){
                    cancel();
                }
                if (Bukkit.getWorld("world") != p.getWorld()){
                    cancel();
                }
                try {
                    if(p.getLocation().distance(((Entity)pl.getSpawnFakeNPC().get(12)).getBukkitEntity().getLocation()) <= 7.0D){
                        updateRotation(p, p.getLocation(), 12);
                    }
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }).runTaskTimer((Plugin)azerot.getInstance(), 5L, 5L);

        EntityArmorStand stand2 = new EntityArmorStand(s);
        stand2.setCustomName("§7Анри");
        stand2.setLocation(-4262.5, 156, 2950.5, 9f,9f);
        stand2.setCustomNameVisible(true);
        stand2.setNoGravity(true);
        stand2.setInvisible(true);
        stand2.setSilent(true);
        stand2.setSmall(true);
        PacketPlayOutSpawnEntityLiving packet24 = new PacketPlayOutSpawnEntityLiving((EntityLiving) stand2);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet24);

        EntitySlime slime2 = new EntitySlime(s);
        slime2.setLocation(-4262.5, 156, 2950.5, 9f,9f);
        slime2.setCustomNameVisible(false);
        slime2.setNoGravity(true);
        slime2.setInvisible(true);
        slime2.setSilent(true);
        slime2.setSize(-2, false);
        slime2.getBukkitEntity().setPassenger(stand2.getBukkitEntity());
        anri.getBukkitEntity().setPassenger(slime2.getBukkitEntity());
        PacketPlayOutSpawnEntityLiving packetslime2 = new PacketPlayOutSpawnEntityLiving((EntityLiving) slime2);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetslime2);

        PacketPlayOutMount mount24 = new PacketPlayOutMount(anri);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount24);
        PacketPlayOutMount mount25 = new PacketPlayOutMount(slime2);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount25);

        pl.getSpawnFakeNameNPC().put(7, slime2);
        pl.getSpawnFakeNameNPC().put(8, stand2);
    }


    public static void spawnElai(Player p){
        WorldServer s = ((CraftWorld)p.getWorld()).getHandle();
        rpgplayer pl = rpgplayer.getRPGPlayer(p);

        EntityZombie elijah = new EntityZombie(s);
        elijah.setLocation(-4235, 130, 3251, -88f, 2.9f);
        elijah.setSilent(true);
        elijah.setInvisible(false);
        DisguiseAPI.disguiseEntity(elijah.getBukkitEntity(), (Disguise) new PlayerDisguise("Zakviel"));
        Disguise disguise = DisguiseAPI.getDisguise(elijah.getBukkitEntity());
        disguise.getWatcher().setCustomNameVisible(false);
        PacketPlayOutSpawnEntityLiving packe = new PacketPlayOutSpawnEntityLiving((EntityLiving) elijah);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packe);
        pl.getSpawnFakeNPC().put(13, elijah);

        (new BukkitRunnable() {
            public void run() {
                if(!p.isOnline()){
                    cancel();
                }
                if(pl.getSpawnFakeNPC().get(13) == null){
                    cancel();
                }
                if (Bukkit.getWorld("world") != p.getWorld()){
                    cancel();
                }
                try {
                    if(p.getLocation().distance(((Entity)pl.getSpawnFakeNPC().get(13)).getBukkitEntity().getLocation()) <= 7.0D){
                        updateRotation(p, p.getLocation(), 13);
                    }
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }).runTaskTimer((Plugin)azerot.getInstance(), 5L, 5L);

        EntityArmorStand stand2 = new EntityArmorStand(s);
        stand2.setCustomName("§7Элайя");
        stand2.setLocation(-4235, 130, 3251, 9f,9f);
        stand2.setCustomNameVisible(true);
        stand2.setNoGravity(true);
        stand2.setInvisible(true);
        stand2.setSilent(true);
        stand2.setSmall(true);
        PacketPlayOutSpawnEntityLiving packet24 = new PacketPlayOutSpawnEntityLiving((EntityLiving) stand2);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet24);

        EntitySlime slime2 = new EntitySlime(s);
        slime2.setLocation(-4235, 130, 3251, 9f,9f);
        slime2.setCustomNameVisible(false);
        slime2.setNoGravity(true);
        slime2.setInvisible(true);
        slime2.setSilent(true);
        slime2.setSize(-2, false);
        slime2.getBukkitEntity().setPassenger(stand2.getBukkitEntity());
        elijah.getBukkitEntity().setPassenger(slime2.getBukkitEntity());
        PacketPlayOutSpawnEntityLiving packetslime2 = new PacketPlayOutSpawnEntityLiving((EntityLiving) slime2);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetslime2);

        PacketPlayOutMount mount24 = new PacketPlayOutMount(elijah);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount24);
        PacketPlayOutMount mount25 = new PacketPlayOutMount(slime2);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount25);

        pl.getSpawnFakeNameNPC().put(7, slime2);
        pl.getSpawnFakeNameNPC().put(8, stand2);
    }

    public static void spawnGrotus(Player p){
        WorldServer s = ((CraftWorld)p.getWorld()).getHandle();
        rpgplayer pl = rpgplayer.getRPGPlayer(p);

        EntityZombie elyja = new EntityZombie(s);
        elyja.setLocation(-4689.5, 108, 3365.5, -88f, 2.9f);
        elyja.setSilent(true);
        elyja.setInvisible(false);
        DisguiseAPI.disguiseEntity(elyja.getBukkitEntity(), (Disguise) new PlayerDisguise("Zakviel"));
        Disguise disguise1 = DisguiseAPI.getDisguise(elyja.getBukkitEntity());
        disguise1.getWatcher().setCustomNameVisible(false);
        PacketPlayOutSpawnEntityLiving packe1 = new PacketPlayOutSpawnEntityLiving((EntityLiving) elyja);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packe1);
        pl.getSpawnFakeNPC().put(14, elyja);

        EntityZombie korey = new EntityZombie(s);
        korey.setLocation(-4727.5, 127, 3368.5, -88f, 2.9f);
        korey.setSilent(true);
        korey.setInvisible(false);
        DisguiseAPI.disguiseEntity(korey.getBukkitEntity(), (Disguise) new PlayerDisguise("Zakviel"));
        Disguise disguise = DisguiseAPI.getDisguise(korey.getBukkitEntity());
        disguise.getWatcher().setCustomNameVisible(false);
        PacketPlayOutSpawnEntityLiving packe = new PacketPlayOutSpawnEntityLiving((EntityLiving) korey);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packe);
        pl.getSpawnFakeNPC().put(15, korey);

        EntityZombie shop = new EntityZombie(s);
        shop.setLocation(-4649.5, 105, 3335.4, 150f, 2.9f);
        shop.setSilent(true);
        shop.setInvisible(false);
        DisguiseAPI.disguiseEntity(shop.getBukkitEntity(), (Disguise) new PlayerDisguise("Zakviel"));
        Disguise disguise2 = DisguiseAPI.getDisguise(shop.getBukkitEntity());
        disguise2.getWatcher().setCustomNameVisible(false);
        PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving((EntityLiving) shop);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
        pl.getSpawnFakeNPC().put(2, shop);

        EntityZombie sell = new EntityZombie(s);
        sell.setLocation(-4649.5, 105, 3339.4, -88f, 2.9f);
        sell.setSilent(true);
        sell.setInvisible(false);
        sell.setCustomNameVisible(false);
        DisguiseAPI.disguiseEntity(sell.getBukkitEntity(), (Disguise) new PlayerDisguise("Zakviel"));
        Disguise disguise23 = DisguiseAPI.getDisguise(sell.getBukkitEntity());
        disguise23.getWatcher().setCustomNameVisible(false);
        PacketPlayOutSpawnEntityLiving packet22 = new PacketPlayOutSpawnEntityLiving((EntityLiving) sell);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet22);
        pl.getSpawnFakeNPC().put(4, sell);


        (new BukkitRunnable() {
            public void run() {
                if(!p.isOnline()){
                    cancel();
                }
                if(pl.getSpawnFakeNPC().get(14) == null && pl.getSpawnFakeNPC().get(15) == null && pl.getSpawnFakeNPC().get(4) == null && pl.getSpawnFakeNPC().get(2) == null){
                    cancel();
                }
                if (Bukkit.getWorld("world") != p.getWorld()){
                    cancel();
                }
                try {
                    if(p.getLocation().distance(((Entity)pl.getSpawnFakeNPC().get(14)).getBukkitEntity().getLocation()) <= 7.0D){
                        updateRotation(p, p.getLocation(), 14);
                    }
                    if(p.getLocation().distance(((Entity)pl.getSpawnFakeNPC().get(15)).getBukkitEntity().getLocation()) <= 7.0D){
                        updateRotation(p, p.getLocation(), 15);
                    }
                    if(p.getLocation().distance(((Entity)pl.getSpawnFakeNPC().get(4)).getBukkitEntity().getLocation()) <= 7.0D){
                        updateRotation(p, p.getLocation(), 4);
                    }
                    if(p.getLocation().distance(((Entity)pl.getSpawnFakeNPC().get(2)).getBukkitEntity().getLocation()) <= 7.0D){
                        updateRotation(p, p.getLocation(), 2);
                    }
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }).runTaskTimer((Plugin)azerot.getInstance(), 5L, 5L);

        EntityArmorStand stand13 = new EntityArmorStand(s);
        stand13.setCustomName("§7Элайя");
        stand13.setLocation(-4689.5, 108, 3365.5, 9f,9f);
        stand13.setCustomNameVisible(true);
        stand13.setNoGravity(true);
        stand13.setInvisible(true);
        stand13.setSilent(true);
        stand13.setSmall(true);
        PacketPlayOutSpawnEntityLiving packet233 = new PacketPlayOutSpawnEntityLiving((EntityLiving) stand13);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet233);

        EntitySlime slime13 = new EntitySlime(s);
        slime13.setLocation(-4689.5, 108, 3365.5, 9f,9f);
        slime13.setCustomNameVisible(false);
        slime13.setNoGravity(true);
        slime13.setInvisible(true);
        slime13.setSilent(true);
        slime13.setSize(-2, false);
        slime13.getBukkitEntity().setPassenger(stand13.getBukkitEntity());
        elyja.getBukkitEntity().setPassenger(slime13.getBukkitEntity());
        PacketPlayOutSpawnEntityLiving packetslime33 = new PacketPlayOutSpawnEntityLiving((EntityLiving) slime13);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetslime33);

        PacketPlayOutMount mount123 = new PacketPlayOutMount(elyja);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount123);
        PacketPlayOutMount mount113 = new PacketPlayOutMount(slime13);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount113);

        pl.getSpawnFakeNameNPC().put(9, slime13);
        pl.getSpawnFakeNameNPC().put(10, stand13);


        EntityArmorStand stand1 = new EntityArmorStand(s);
        stand1.setCustomName("§7Барахольщик");
        stand1.setLocation(-4649.5, 105, 3339.4, 9f,9f);
        stand1.setCustomNameVisible(true);
        stand1.setNoGravity(true);
        stand1.setInvisible(true);
        stand1.setSilent(true);
        stand1.setSmall(true);
        PacketPlayOutSpawnEntityLiving packet23 = new PacketPlayOutSpawnEntityLiving((EntityLiving) stand1);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet23);

        EntitySlime slime1 = new EntitySlime(s);
        slime1.setLocation(-4649.5, 105, 3339.4, 9f,9f);
        slime1.setCustomNameVisible(false);
        slime1.setNoGravity(true);
        slime1.setInvisible(true);
        slime1.setSilent(true);
        slime1.setSize(-2, false);
        slime1.getBukkitEntity().setPassenger(stand1.getBukkitEntity());
        sell.getBukkitEntity().setPassenger(slime1.getBukkitEntity());
        PacketPlayOutSpawnEntityLiving packetslime3 = new PacketPlayOutSpawnEntityLiving((EntityLiving) slime1);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetslime3);

        PacketPlayOutMount mount12 = new PacketPlayOutMount(sell);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount12);
        PacketPlayOutMount mount11 = new PacketPlayOutMount(slime1);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount11);

        pl.getSpawnFakeNameNPC().put(5, slime1);
        pl.getSpawnFakeNameNPC().put(6, stand1);

        EntityArmorStand stand = new EntityArmorStand(s);
        stand.setCustomName("§7Продавец");
        stand.setLocation(-4649.5, 105, 3335.4, 9f,9f);
        stand.setCustomNameVisible(true);
        stand.setNoGravity(true);
        stand.setInvisible(true);
        stand.setSilent(true);
        stand.setSmall(true);
        PacketPlayOutSpawnEntityLiving packet222 = new PacketPlayOutSpawnEntityLiving((EntityLiving) stand);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet222);

        EntitySlime slime = new EntitySlime(s);
        slime.setLocation(-4649.5, 105, 3335.4, 9f,9f);
        slime.setCustomNameVisible(false);
        slime.setNoGravity(true);
        slime.setInvisible(true);
        slime.setSilent(true);
        slime.setSize(-2, false);
        slime.getBukkitEntity().setPassenger(stand.getBukkitEntity());
        shop.getBukkitEntity().setPassenger(slime.getBukkitEntity());
        PacketPlayOutSpawnEntityLiving packetslime = new PacketPlayOutSpawnEntityLiving((EntityLiving) slime);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetslime);

        PacketPlayOutMount mount = new PacketPlayOutMount(shop);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount);
        PacketPlayOutMount mount1 = new PacketPlayOutMount(slime);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount1);

        pl.getSpawnFakeNameNPC().put(3, slime);
        pl.getSpawnFakeNameNPC().put(4, stand);


        EntityArmorStand stand2 = new EntityArmorStand(s);
        stand2.setCustomName("§7Корей");
        stand2.setLocation(-4727.5, 127, 3368.5, 9f,9f);
        stand2.setCustomNameVisible(true);
        stand2.setNoGravity(true);
        stand2.setInvisible(true);
        stand2.setSilent(true);
        stand2.setSmall(true);
        PacketPlayOutSpawnEntityLiving packet24 = new PacketPlayOutSpawnEntityLiving((EntityLiving) stand2);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet24);

        EntitySlime slime2 = new EntitySlime(s);
        slime2.setLocation(-4727.5, 127, 3368.5, 9f,9f);
        slime2.setCustomNameVisible(false);
        slime2.setNoGravity(true);
        slime2.setInvisible(true);
        slime2.setSilent(true);
        slime2.setSize(-2, false);
        slime2.getBukkitEntity().setPassenger(stand2.getBukkitEntity());
        korey.getBukkitEntity().setPassenger(slime2.getBukkitEntity());
        PacketPlayOutSpawnEntityLiving packetslime2 = new PacketPlayOutSpawnEntityLiving((EntityLiving) slime2);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetslime2);

        PacketPlayOutMount mount24 = new PacketPlayOutMount(korey);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount24);
        PacketPlayOutMount mount25 = new PacketPlayOutMount(slime2);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount25);

        pl.getSpawnFakeNameNPC().put(7, slime2);
        pl.getSpawnFakeNameNPC().put(8, stand2);
    }

    public static void spawnAlchemist(Player p)
    {
        WorldServer s = ((CraftWorld)p.getWorld()).getHandle();
        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        rpgplayer pl = rpgplayer.getRPGPlayer(p);

        EntityZombie genry = new EntityZombie(s);
        genry.setLocation(-4514.5, 105, 3236.5, -90f, 2.9f);
        genry.setSilent(true);
        genry.setInvisible(false);
        DisguiseAPI.disguiseEntity(genry.getBukkitEntity(), (Disguise) new PlayerDisguise("omgakorean"));
        Disguise disguise = DisguiseAPI.getDisguise(genry.getBukkitEntity());
        disguise.getWatcher().setCustomNameVisible(false);
        PacketPlayOutSpawnEntityLiving packet1 = new PacketPlayOutSpawnEntityLiving((EntityLiving) genry);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet1);
        pl.getSpawnFakeNPC().put(17, genry);

        EntityArmorStand stand = new EntityArmorStand(s);
        stand.setCustomName("§7Алхимик");
        stand.setLocation(-4514.5, 105, 3236.5, 9f,9f);
        stand.setCustomNameVisible(true);
        stand.setNoGravity(true);
        stand.setInvisible(true);
        stand.setSilent(true);
        stand.setSmall(true);
        PacketPlayOutSpawnEntityLiving packet2 = new PacketPlayOutSpawnEntityLiving((EntityLiving) stand);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet2);

        EntitySlime slime = new EntitySlime(s);
        slime.setLocation(-4514.5, 105, 3236.5, 9f,9f);
        slime.setCustomNameVisible(false);
        slime.setNoGravity(true);
        slime.setInvisible(true);
        slime.setSilent(true);
        slime.setSize(-2, false);
        slime.getBukkitEntity().setPassenger(stand.getBukkitEntity());
        genry.getBukkitEntity().setPassenger(slime.getBukkitEntity());
        PacketPlayOutSpawnEntityLiving packetslime = new PacketPlayOutSpawnEntityLiving((EntityLiving) slime);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetslime);

        PacketPlayOutMount mount = new PacketPlayOutMount(genry);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount);
        PacketPlayOutMount mount1 = new PacketPlayOutMount(slime);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(mount1);

        pl.getSpawnFakeNameNPC().put(5, slime);
        pl.getSpawnFakeNameNPC().put(6, stand);

        (new BukkitRunnable() {
            public void run() {
                if(!p.isOnline()){
                    cancel();
                }
                if(pl.getSpawnFakeNPC().get(17) == null){
                    cancel();
                }
                if (Bukkit.getWorld("world") != p.getWorld()){
                    cancel();
                }
                try {
                    if(p.getLocation().distance(((Entity)pl.getSpawnFakeNPC().get(17)).getBukkitEntity().getLocation()) <= 7.0D){
                        updateRotation(p, p.getLocation(), 17);
                    }
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }).runTaskTimer((Plugin)azerot.getInstance(), 5L, 5L);
    }
}
