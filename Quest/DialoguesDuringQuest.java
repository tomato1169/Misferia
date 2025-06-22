package azerot.azerot.Quest;

import azerot.azerot.rpgplayer;
import org.bukkit.entity.Player;

public class DialoguesDuringQuest {
    public static void dialogue(Player p, String quest){
        rpgplayer player = rpgplayer.getRPGPlayer(p);
        switch (quest){
            case "11":
                switch (player.getQuestsStage().get("11.1")){
                    case 1:
                        p.sendMessage("Это первое сообщение с Элей");
                        player.getDialogueBetweenNPC().put("11.1", 2);
                        break;
                    case 2:
                        p.sendMessage("Это второе сообщение с Элей");
                        player.getDialogueBetweenNPC().put("11.1", 3);
                        break;
                    case 3:
                        p.sendMessage("Это третье сообщение с Элей");
                        player.getDialogueBetweenNPC().put("11.1", 4);
                        break;
                    case 4:
                        p.sendMessage("Это четвертое сообщение с Элей");
                        player.getQuestsStage().put("11", 2);
                        break;

                }
                break;
        }

    }

}
