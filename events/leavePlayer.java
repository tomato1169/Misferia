package azerot.azerot.events;

import azerot.azerot.*;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.grinderwolf.swm.api.world.SlimeWorld;
import net.minecraft.server.v1_12_R1.Entity;
import net.minecraft.server.v1_12_R1.PacketPlayOutEntityDestroy;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class leavePlayer  implements Listener {

    @EventHandler
    public void leave(PlayerQuitEvent e) throws IOException, SQLException {
        e.setQuitMessage(null);
        if (rpgplayer.playershm.get(e.getPlayer().getName()) != null) {
            Player p = e.getPlayer();
            rpgplayer player = rpgplayer.getRPGPlayer(p);
            if(Objects.equals(p.getWorld().getName(), p.getName())){
                p.teleport(new Location(Bukkit.getWorld("world"),-4640,106, 3336));
                if(player.getStatusQuest(String.valueOf(51)) == 1){
                    player.setStatusQuest(String.valueOf(51), 3);
                }

            }
            if (player.getNowDie() || p.hasPotionEffect(PotionEffectType.LUCK) || p.getGameMode().equals(GameMode.SPECTATOR))
                p.teleport(RPGLocations.nearestLoc(p.getLocation(), player));

            player.setPlusVampiric(0);
            MobSpells.cancellSpells(p, "");
            Scoreboard scoreboard = player.getScoreboard();

            scoreboard.getTeam("green").unregister();
            scoreboard.getTeam("white").unregister();
            scoreboard.getTeam("blue").unregister();
            scoreboard.getTeam("yellow").unregister();
            scoreboard.getTeam("gold").unregister();
            scoreboard.getTeam("purple").unregister();
            scoreboard.getTeam("red").unregister();
            if(!player.getSpawnFakeEntity().isEmpty()){
                for(Entity ent : player.getSpawnFakeEntity().values()){
                    PacketPlayOutEntityDestroy packDelete2 = new PacketPlayOutEntityDestroy(ent.getBukkitEntity().getEntityId());
                    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packDelete2);
                }
            }


            if (rpgplayer.playershm.get(e.getPlayer().getName()) != null) {
                azerot.savePlayer(player);
                player.playershm.remove(p.getName());
            }

        }
    }
}
