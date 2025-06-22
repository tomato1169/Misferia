package azerot.azerot;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.google.gson.Gson;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class CustomChat  {
    static Gson gson = new Gson();
    public static void customChat(String pretext, String text, Player p) throws IOException {
        rpgplayer player = rpgplayer.getRPGPlayer(p);
        sendToBungee(p);
        return;
    }

    public static void sendToBungee(Player player) throws IOException {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        try{
            out.writeUTF("message");
            out.writeUTF(player.getName());
            out.writeInt(rpgplayer.getRPGPlayer(player).getLevel());
            player.sendPluginMessage((Plugin)azerot.getInstance(), "message:main", out.toByteArray());
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }


    public static void customChatVisual(String pretext, ItemStack item, Player p) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        try{
            out.writeUTF("hand");
            out.writeUTF(p.getName());
            out.writeInt(rpgplayer.getRPGPlayer(p).getLevel());
            out.writeUTF(item.getItemMeta().getDisplayName());
            out.writeUTF(gson.toJson(item.getItemMeta().getLore()));
            out.writeInt(item.getAmount());
            p.sendPluginMessage((Plugin)azerot.getInstance(), "message:main", out.toByteArray());
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

}
