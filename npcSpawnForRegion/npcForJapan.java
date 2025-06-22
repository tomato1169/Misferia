package azerot.azerot.npcSpawnForRegion;

import azerot.azerot.azerot;
import azerot.azerot.rpgplayer;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.InvocationTargetException;

public class npcForJapan
{
    public static void spawnVillager(Player p)
    {
        WorldServer s = ((CraftWorld)p.getWorld()).getHandle();
        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        rpgplayer pl = rpgplayer.getRPGPlayer(p);



        EntityZombie elder = new EntityZombie(s);
        elder.setLocation(-1735.5, 107, 943.5, -90f, 2.9f);
        elder.setSilent(true);
        elder.setInvisible(false);
        elder.setCustomNameVisible(false);
        PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving((EntityLiving) elder);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
        pl.getSpawnFakeNPC().put(20, elder);

        EntityArmorStand stand1 = new EntityArmorStand(s);
        stand1.setCustomName("§7Шидео");
        stand1.setLocation(-1735.5, 107, 943.5, 9f,9f);
        stand1.setCustomNameVisible(true);
        stand1.setNoGravity(true);
        stand1.setInvisible(true);
        stand1.setSilent(true);
        stand1.setSmall(true);
        PacketPlayOutSpawnEntityLiving packet2 = new PacketPlayOutSpawnEntityLiving((EntityLiving) stand1);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet2);

        EntitySlime slime1 = new EntitySlime(s);
        slime1.setLocation(-1735.5, 107, 943.5, 9f,9f);
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
                if(pl.getSpawnFakeNPC().get(20) == null){
                    cancel();
                }
                if (Bukkit.getWorld("world") != p.getWorld()){
                    cancel();
                }
                try {
                    if(p.getLocation().distance(((Entity)pl.getSpawnFakeNPC().get(20)).getBukkitEntity().getLocation()) <= 7.0D){
                        npcInitialVillage.updateRotation(p, p.getLocation(), 20);
                    }
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }).runTaskTimer((Plugin) azerot.getInstance(), 5L, 5L);
    }
}
