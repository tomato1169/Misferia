package azerot.azerot.command;

import azerot.azerot.Quest.questScript;
import azerot.azerot.RPGItem;
import azerot.azerot.WorldUtils;
import azerot.azerot.rpgplayer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;
import java.util.Map;

public class resetQuest extends AbstractCommand {

    public resetQuest() {
        super("resetQuest");
    }

    public void execute(CommandSender sender, String label, String[] args) {
        try {
            if (!sender.hasPermission("")) {
                sender.sendMessage(WorldUtils.worldName() + "1" );
                return;
            }
            if (args.length == 0) {
                sender.sendMessage(WorldUtils.worldName() + "2" );
                return;
            }

            if (args.length > 0) {
                Player p = Bukkit.getPlayer(args[0]);
                rpgplayer pl = rpgplayer.getRPGPlayer(p);
                if(args[1] != null){
                    pl.getQuests().put(args[1], 3);
                    pl.getQuestsStage().remove(args[1]);
                    for (Map.Entry<String, Integer> i : pl.getDialogueBetweenNPC().entrySet()){
                        String quest = i.getKey();
                        int questInfo = i.getValue();
                        String[] quests = quest.split("-");
                        if(quests[0].equals(args[1])){
                            pl.getDialogueBetweenNPC().put(quest, 0);
                        }
                    }
                }
                return;
            }
        } catch (Exception exception) {
            sender.sendMessage(WorldUtils.worldName() + "3" );
            exception.printStackTrace();
        }
    }
}
