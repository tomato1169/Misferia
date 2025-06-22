package azerot.azerot.command;

import azerot.azerot.*;
import azerot.azerot.Quest.Quest;
import azerot.azerot.Quest.QuestsPath;
import azerot.azerot.Quest.QuestsStageForChat;
import azerot.azerot.mobs.MobSystem;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class QuestCommand extends AbstractCommand {
    public QuestCommand() {
        super("quest");
    }

    public void execute(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player))
            return;
        try {
            rpgplayer p = rpgplayer.getRPGPlayer((Player)sender);
            if (args.length == 0) {
                sender.sendMessage(WorldUtils.worldName() + " §aВозможные команды" );
                                sender.sendMessage(WorldUtils.worldName() + " §7- §f/quest l - узнать список принятых заданий" );
                                        sender.sendMessage(WorldUtils.worldName() + " §7- §f/quest s - узнать информацию о задании (/quest show ')");
                                            sender.sendMessage(WorldUtils.worldName() + " §7- §f/quest i - узнать информацию о доступных заданиях (/quest info ')");
                sender.sendMessage(WorldUtils.worldName() + " §7- §f/quest p - узнать путь до доступного квеста (/quest path ')");
            }
            if (args.length < 1)
                return;
            if (args[0].contains("a")) {
                ((Player)sender).openInventory(GenerateInventory.allPlayerQuest(p));
                return;
            }
            if(args[0].contains("i") || args[0].contains("info")){
                for (int i = 1; i <= azerot.qsts; i++) {
                    if (p.getStatusQuest("" + i) != 1 && p.getStatusQuest("" + i) != 2 && !Quest.getQuest("" + i).isLock(p) && Quest.getQuest("" + i).getType() != 2) {
                        Quest quest = Quest.getQuest(String.valueOf(i));
                        p.toPlayer().spigot().sendMessage(new ComponentBuilder(WorldUtils.worldName() + " §7- §f" + quest.getName() + " §c#" + i)
                                .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/q p " + i))
                                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText((WorldUtils.worldName() + " §7Кликните, чтобы увидеть путь до задания!"))))
                                .create());
                    }
                }

            }
            if (args[0].contains("l")) {
                sender.sendMessage(WorldUtils.worldName() + " §aВы выполняете эти задания: " );
                for (int i = 1; i <= azerot.qsts; i++) {
                    if (p.getStatusQuest(String.valueOf(i)) == 1) {
                        Quest quest = Quest.getQuest(String.valueOf(i));
                        p.toPlayer().spigot().sendMessage(new ComponentBuilder(WorldUtils.worldName() + " §7- §f" + quest.getName() + " §c#" + i)
                                .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/q s " + i))
                                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText((WorldUtils.worldName() + " §7Кликните, чтобы открыть информацию по квесту"))))
                                .create());
                    }
                }
                return;
            }
            if (args[0].contains("s")) {
                p.setQuestLook(args[1]);
            }
            if(args[0].contains("p") || args[0].contains("path")){
                QuestsPath.pathOn(p.getPlayer(), args[1]);
                return;
            }
        } catch (Exception exception) {
            sender.sendMessage(WorldUtils.worldName() + " §cВозможно, вы ошиблись в команде" );
            exception.printStackTrace();
            return;
        }
    }
}
