package azerot.azerot.utils;

import com.mojang.authlib.BaseUserAuthentication;
import net.minecraft.server.v1_12_R1.Packet;
import net.minecraft.server.v1_12_R1.PacketDataSerializer;
import net.minecraft.server.v1_12_R1.PacketPlayOutCustomPayload;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Collection;

public abstract class CustomPayload {
    private final PacketBuffer buffer = new PacketBuffer();

    public PacketBuffer getBuffer() {
        return this.buffer;
    }

    public abstract void write(PacketBuffer paramPacketBuffer);

    public void writeData() {
        this.buffer.writeShort(getId());
        write(this.buffer);
    }

    public abstract short getId();

    public static void sendCustomPayload(Player p, CustomPayload payload) {
        payload.writeData();
        PacketPlayOutCustomPayload packet = new PacketPlayOutCustomPayload("Misferia", new PacketDataSerializer(payload.getBuffer()));
        (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packet);
    }
    public static void sendCustomPayload(Collection<Player> players, CustomPayload payload) {
        payload.writeData();
        players.stream().map(p -> (((CraftPlayer)p).getHandle()).playerConnection)
                .forEach(pc -> pc.sendPacket(new PacketPlayOutCustomPayload("Misferia", new PacketDataSerializer(payload.getBuffer().copy()))));
    }

    public static void announceCustomPayload(CustomPayload payload) {
        sendCustomPayload((Player) Bukkit.getOnlinePlayers(), payload);
    }
}
