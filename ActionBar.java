package azerot.azerot;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class ActionBar {
    public static void actionbar(Player p, int type, String messageto) {
        rpgplayer player = rpgplayer.getRPGPlayer(p);
        String price = "";
        int emerald5 = 0;
        int emerald4 = 0;
        int emerald3 = 0;
        int emerald2 = 0;
        int emerald1 = 0;
        int money = 0;
        int itemprice = player.getMoney();
        while (itemprice >= 16777216) {
            itemprice -= 16777216;
            emerald5++;
        }
        while (itemprice >= 262144) {
            itemprice -= 262144;
            emerald4++;
        }
        while (itemprice >= 4096) {
            itemprice -= 4096;
            emerald3++;
        }
        while (itemprice >= 64) {
            itemprice -= 64;
            emerald2++;
        }
        while (itemprice > 0 && itemprice < 64) {
            itemprice--;
            emerald1++;
        }
        if(emerald1 >= 1){
            money++;
        }
        if(emerald2 >= 1){
            money++;
        }
        if(emerald3 >= 1){
            money++;
        }
        if(emerald4 >= 1){
            money++;
        }
        if(emerald5 >= 1){
            money++;
        }
        String space = "";
        switch (money){
            case 1:
                space =  "  ";
                break;
            case 2:
                space =  "    ";
                break;
            case 3:
                space =  "      ";
                break;
            case 4:
                space =  "        ";
                break;
            case 5:
                space =  "          ";
                break;
        }
        String message = "";
        if (type == 0)
                message = "§c❤ §c§l"+ ((int) Math.round(player.getHealth())) + "§7§l/§c§l" + ((int) Math.round(player.getMaxHealth())) + "    §6§lXP " + player.getXP() + "§7§l/§6§l" + player.getLXp() + "    §f" + ( emerald1 > 0 &&  emerald1 < 64 ? emerald1 + "§f⛃ §2" : "§2") + ( emerald2 > 0 ? (emerald2) + "§2⛃ §9" : "§9") + ( emerald3 > 0 ? (emerald3) + "§9⛃ §5" : "§5") + ( emerald4 > 0 ? emerald4 + "§5⛃ §6" : "§6") + ( emerald5 > 0 ? emerald5 + "§6⛃ " : "");
        if (type == 1)
            message = messageto;
        if (p.hasPotionEffect(PotionEffectType.UNLUCK) && type == 0)
            return;
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
    }
}
