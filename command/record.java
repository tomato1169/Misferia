package azerot.azerot.command;

import azerot.azerot.GenerateInventory;
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
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class record  extends AbstractCommand{
    public record() {
        super("record");
    }

    public void execute(CommandSender sender, String label, String[] args) {
        try {
            Player p = (Player) sender;
            if(p.isOp()){
                String record = args[0];
                List<String> lore = new ArrayList<>();
                rpgplayer player = rpgplayer.getRPGPlayer((Player) sender);
                azerot.getInstance().setRouterecording(true);
                player.toPlayer().sendMessage(WorldUtils.worldName() + " §2Запись начата!");
                if(record.equals("CameraStartSecondPatch")){
                    p.setFlySpeed(0.1F);
                    WorldServer s = ((CraftWorld) p.getWorld()).getHandle();
                    MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();

                    Location endLoc = new Location(Bukkit.getWorld("world"), -4005, 72, 4087);

                    List<Location> Elijah = new ArrayList<>(azerot.pathRecord.get("Elijah"));
                    List<Location> playerStartSecondPatch1 = new ArrayList<>(azerot.pathRecord.get("playerStartSecondPatch"));
                    EntityZombie elijah = new EntityZombie(s);
                    elijah.setLocation(Elijah.get(0).getX(), Elijah.get(0).getY(), Elijah.get(0).getZ(), Elijah.get(0).getYaw(), Elijah.get(0).getPitch());
                    elijah.setInvisible(false);
                    DisguiseAPI.disguiseEntity(elijah.getBukkitEntity(), (Disguise) new PlayerDisguise("LouisVos"));
                    Disguise disguise1 = DisguiseAPI.getDisguise(elijah.getBukkitEntity());
                    PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving((EntityLiving) elijah);
                    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);

                    EntityZombie playerStartSecondPatch = new EntityZombie(s);
                    playerStartSecondPatch.setLocation(playerStartSecondPatch1.get(0).getX(), playerStartSecondPatch1.get(0).getY(), playerStartSecondPatch1.get(0).getZ(), playerStartSecondPatch1.get(0).getYaw(), playerStartSecondPatch1.get(0).getPitch());
                    // Если убрать дизгуайс то ник норм

                    PacketPlayOutSpawnEntityLiving packetplayerStartSecondPatch = new PacketPlayOutSpawnEntityLiving((EntityLiving) playerStartSecondPatch);
                    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetplayerStartSecondPatch);

                    (new BukkitRunnable() {
                        public void run() {
                            if(!p.isOnline()){
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
                                CraftEntity entity = elijah.getBukkitEntity();
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
                                PacketPlayOutEntityDestroy packDelete3 = new PacketPlayOutEntityDestroy(playerStartSecondPatch.getBukkitEntity().getEntityId());
                                ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packDelete3);
                                PacketPlayOutEntityDestroy packDelete = new PacketPlayOutEntityDestroy(elijah.getBukkitEntity().getEntityId());
                                ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packDelete);
                                cancel();
                            }
                        }
                    }).runTaskTimer((Plugin)azerot.getInstance(), 1L, 1L);

                }


                (new BukkitRunnable() {
                    public void run() {
                        if(azerot.getInstance().getRouterecording()){
                            (new BukkitRunnable() {
                                public void run() {
                                    if(!azerot.getInstance().routerecording){
                                        azerot.recordSave.put(record, lore);
                                        List<Location> locations = new ArrayList<>();
                                        for(int i = 0; i < lore.size(); i++){
                                            String loc = lore.get(i).replace("x:", "").replace("y:", "").replace("z:", "").replace("pitch:", "").replace("yaw:", "");
                                            String[] loc1 = loc.split(",");
                                            String world = String.valueOf(loc1[0]);
                                            Double x = Double.valueOf(loc1[1]);
                                            Double y = Double.valueOf(loc1[2]);
                                            Double z = Double.valueOf(loc1[3]);
                                            Float yaw = Float.valueOf(loc1[4]);
                                            Float pitch = Float.valueOf(loc1[5]);

                                            locations.add(new Location(Bukkit.getServer().getWorld(world),x,y,z,yaw,pitch));
                                        }
                                        azerot.pathRecord.put(record, locations);
                                        cancel();
                                    }else{
                                        lore.add(player.toPlayer().getWorld().getName() + ",x:" + player.toPlayer().getLocation().getX() + ",y:" + player.toPlayer().getLocation().getY() + ",z:" + player.toPlayer().getLocation().getZ() + ",yaw:" + player.toPlayer().getLocation().getYaw() + ",pitch:" + player.toPlayer().getLocation().getPitch());
                                    }
                                }
                            }).runTaskTimer((Plugin)azerot.getInstance(), 1L,1L);
                        }
                    }
                }).runTaskLater((Plugin)azerot.getInstance(), 10L);
            }else {
                sender.sendMessage(WorldUtils.worldName() + " §4Недостаточно прав!" );
            }

        } catch (Exception exception) {
            sender.sendMessage(WorldUtils.worldName() + " §Возможно вы ошиблись в команде!" );
            exception.printStackTrace();
        }
    }
}
