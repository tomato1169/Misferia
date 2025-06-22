package azerot.azerot.Quest;

import azerot.azerot.RPGItem;
import azerot.azerot.RPGItemContainer;
import azerot.azerot.WorldUtils;
import azerot.azerot.mobs.MobSystem;
import azerot.azerot.rpgplayer;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class QuestsStageForChat {
    public static void questStage(Player p, String quest, int Stage) throws SQLException {
        rpgplayer player = rpgplayer.getRPGPlayer(p);
        switch (quest){
            case "2":
                switch (Stage){
                    case 1:
                        Quest q = Quest.getQuest(quest);
                        if (player.getStatusQuest("2") == 1) {
                            p.sendMessage(WorldUtils.worldName() + " §aИнформация о задании §a" + q.getName() + " §c#" + quest);

                                p.sendMessage("§m§fНа деревню напали, над");
                                p.sendMessage("§7Необходимо убить:");

                            p.sendMessage("§7-" + MobSystem.getName(1) + ": §f" + player.getMob("1") + "/" + 5);
                            p.sendMessage("§7-" + MobSystem.getName(2) + ": §f" + player.getMob("2") + "/" + 5);
                            p.sendMessage("§7-" + MobSystem.getName(3) + ": §f" + player.getMob("3") + "/" + 5);

                            p.sendMessage("§7Необходимо сдать: §fСтарик");
                                p.sendMessage("§7Координаты: §f-3327 70 1867");
                            if (q.getXp() > 0)
                                p.sendMessage("§7- §6" + q.getXp() + " опыта");
                        } else {
                            p.sendMessage(WorldUtils.worldName() + " §cДанного квеста не существует!");
                        }
                        break;

                }
                break;
            case "3":
                if (player.getStatusQuest("3") == 1) {
                    Quest q = Quest.getQuest(quest);
                    switch (Stage){
                        case 1:
                            p.sendMessage(WorldUtils.worldName() + " §aИнформация о задании §a" + q.getName() + " §c#" + quest);
                            p.sendMessage("§fМеня направили в деревню!");
                            p.sendMessage("§7Необходимо сдать: §6Управляющий");
                            p.sendMessage("§7Координаты: §f-4033 68 1976");
                            break;
                        case 2:
                            p.sendMessage(WorldUtils.worldName() + " §aИнформация о задании §a" + q.getName() + " §c#" + quest);
                            p.sendMessage("§fКажется в этом доме кто-то есть, может он поможет мне дойти до деревни");

                            break;
                        case 4:
                            p.sendMessage(WorldUtils.worldName() + " §aИнформация о задании §a" + q.getName() + " §c#" + quest);
                            p.sendMessage("§fГенри попросил меня его провести до деревни!");
                            p.sendMessage("§7Необходимо убить:");
                            p.sendMessage("§7-" + MobSystem.getName(4) + ": §f" + player.getMob("4") + "/" + 5);
                            p.sendMessage("§7-" + MobSystem.getName(5) + ": §f" + player.getMob("5") + "/" + 5);
                            p.sendMessage("§7-" + MobSystem.getName(6) + ": §f" + player.getMob("6") + "/" + 1);
                            p.sendMessage("§7- §620 опыта");
                            break;
                        case 5:
                            p.sendMessage(WorldUtils.worldName() + " §aИнформация о задании §a" + q.getName() + " §c#" + quest);
                            p.sendMessage("§fВсех пауков я убил, осталось дойти до деревни");
                            p.sendMessage("§7- §640 опыта");
                            break;

                    }

                } else {
                    p.sendMessage(WorldUtils.worldName() + " §cДанного квеста не существует!");
                }
                break;
            case "4":
                if (player.getStatusQuest("4") == 1) {
                    Quest q = Quest.getQuest(quest);
                    switch (Stage){
                        case 1:
                            p.sendMessage(WorldUtils.worldName() + " §aИнформация о задании §a" + q.getName() + " §c#" + quest);
                            p.sendMessage("§fМеня направили в деревню!");
                            p.sendMessage("§7Необходимо убить:");
                            p.sendMessage("§7-" + MobSystem.getName(7) + ": §f" + player.getMob("7") + "/" + 7);
                            p.sendMessage("§7-" + MobSystem.getName(8) + ": §f" + player.getMob("8") + "/" + 7);
                            p.sendMessage("§7-" + MobSystem.getName(9) + ": §f" + player.getMob("9") + "/" + 7);
                            p.sendMessage("§7- §620 опыта");

                            p.sendMessage("§7Необходимо сдать: §6Управляющий");
                            p.sendMessage("§7Координаты: §f-4033 68 1976");
                            break;
                    }

                } else {
                    p.sendMessage(WorldUtils.worldName() + " §cДанного квеста не существует!");
                }
                break;
            case "5":
                if (player.getStatusQuest("5") == 1) {
                    Quest q = Quest.getQuest(quest);
                    switch (Stage){
                        case 1:
                            p.sendMessage(WorldUtils.worldName() + " §aИнформация о задании §a" + q.getName() + " §c#" + quest);
                            p.sendMessage("§fДевочка, кажется еще зовут, Алиса, попросила меня принести ей блестящие камешки, надо их найти");
                            p.sendMessage("§7Необходимо собрать:");
                            int amount = WorldUtils.checkItems(player, 57, 0);
                            p.sendMessage("§7-" + (new RPGItem()).getRPGItem(57, 0, 1, 0, 0, 0, 0, 0,null).getItemMeta().getDisplayName() + ": " + amount + "/" + "7");
                            p.sendMessage("§7- §620 опыта");
                            p.sendMessage("§7Необходимо сдать: §6Алиса");
                            p.sendMessage("§7Координаты: §f-3998 64 1912");
                            break;
                    }

                } else {
                    p.sendMessage(WorldUtils.worldName() + " §cДанного квеста не существует!");
                }
                break;
            case "6":
                if (player.getStatusQuest("6") == 1) {
                    Quest q = Quest.getQuest(quest);
                    switch (Stage){
                        case 1:
                            p.sendMessage(WorldUtils.worldName() + " §aИнформация о задании §a" + q.getName() + " §c#" + quest);
                            p.sendMessage("§fКакие-то монстры напали, надо помочь");
                            p.sendMessage("§7Необходимо убить:");
                            p.sendMessage("§7-" + MobSystem.getName(10) + ": §f" + player.getMob("10") + "/" + 10);
                            p.sendMessage("§7- §620 опыта");
                            p.sendMessage("§7Необходимо сдать: -");
                            break;
                    }

                } else {
                    p.sendMessage(WorldUtils.worldName() + " §cДанного квеста не существует!");
                }
                break;
            case "7":
                if (player.getStatusQuest("7") == 1) {
                    Quest q = Quest.getQuest(quest);
                    switch (Stage){
                        case 1:
                            p.sendMessage(WorldUtils.worldName() + " §aИнформация о задании §a" + q.getName() + " §c#" + quest);
                            p.sendMessage("§fМонстры напали, надо убить!");
                            p.sendMessage("§7Необходимо убить:");
                            p.sendMessage("§7-" + MobSystem.getName(11) + ": §f" + player.getMob("11") + "/" + 7);
                            p.sendMessage("§7-" + MobSystem.getName(12) + ": §f" + player.getMob("12") + "/" + 7);
                            p.sendMessage("§7- §620 опыта");
                            p.sendMessage("§7Необходимо сдать: -");
                            break;
                        case 2:
                            p.sendMessage(WorldUtils.worldName() + " §4Следующая стадия откроется на 12 уровне!");
                            break;
                        case 3:
                            p.sendMessage(WorldUtils.worldName() + " §aИнформация о задании §a" + q.getName() + " §c#" + quest);
                            p.sendMessage("§fМонстры напали, надо убить!");
                            p.sendMessage("§7Необходимо убить:");
                            p.sendMessage("§7-" + MobSystem.getName(13) + ": §f" + player.getMob("15") + "/" + 7);
                            p.sendMessage("§7-" + MobSystem.getName(15) + ": §f" + player.getMob("15") + "/" + 7);
                            p.sendMessage("§7- §620 опыта");
                            p.sendMessage("§7Необходимо сдать: -");
                            break;
                        case 4:
                            p.sendMessage(WorldUtils.worldName() + " §4Следующая стадия откроется на 13 уровне!");
                            break;
                        case 5:
                            p.sendMessage(WorldUtils.worldName() + " §aИнформация о задании §a" + q.getName() + " §c#" + quest);
                            p.sendMessage("§fМонстры напали, надо убить!");
                            p.sendMessage("§7Необходимо убить:");
                            p.sendMessage("§7-" + MobSystem.getName(16) + ": §f" + player.getMob("16") + "/" + 10);
                            p.sendMessage("§7- §620 опыта");
                            p.sendMessage("§7Необходимо сдать: -");
                            break;
                        case 6:
                            p.sendMessage(WorldUtils.worldName() + " §4Следующая стадия откроется на 14 уровне!");
                            break;
                        case 7:
                            p.sendMessage(WorldUtils.worldName() + " §aИнформация о задании §a" + q.getName() + " §c#" + quest);
                            p.sendMessage("§fМонстры напали, надо убить!");
                            p.sendMessage("§7Необходимо убить:");
                            p.sendMessage("§7-" + MobSystem.getName(14) + ": §f" + player.getMob("14") + "/" + 7);
                            p.sendMessage("§7-" + MobSystem.getName(19) + ": §f" + player.getMob("19") + "/" + 1);
                            p.sendMessage("§7- §620 опыта");
                            p.sendMessage("§7Необходимо сдать: §6Ванесса");
                            p.sendMessage("§7Координаты: §f-3925 118 2875");
                            break;
                    }

                } else {
                    p.sendMessage(WorldUtils.worldName() + " §cДанного квеста не существует!");
                }
                break;
            case "9":
                if (player.getStatusQuest("8") == 1) {
                    Quest q = Quest.getQuest(quest);
                    switch (Stage){
                        case 1:
                            p.sendMessage(WorldUtils.worldName() + " §aИнформация о задании §a" + q.getName() + " §c#" + quest);
                            p.sendMessage("§fНадо найти анри");
                            p.sendMessage("§7- §620 опыта");
                            p.sendMessage("§7Необходимо сдать: §6Анри");
                            p.sendMessage("§7Координаты: §f-4157 161 2892");
                            break;
                    }

                } else {
                    p.sendMessage(WorldUtils.worldName() + " §cДанного квеста не существует!");
                }
                break;
            case "10":
                if (player.getStatusQuest("9") == 1) {
                    Quest q = Quest.getQuest(quest);
                    switch (Stage){
                        case 1:
                            p.sendMessage(WorldUtils.worldName() + " §aИнформация о задании §a" + q.getName() + " §c#" + quest);
                            p.sendMessage("§fАнри попросила убить здешлих обитателей");
                            p.sendMessage("§7Необходимо убить:");
                            p.sendMessage("§7-" + MobSystem.getName(20) + ": §f" + player.getMob("20") + "/" + 7);
                            p.sendMessage("§7-" + MobSystem.getName(21) + ": §f" + player.getMob("21") + "/" + 7);
                            p.sendMessage("§7- §620 опыта");
                            p.sendMessage("§7Необходимо сдать: -");
                            break;
                        case 2:
                            p.sendMessage(WorldUtils.worldName() + " §4Следующая стадия откроется на 16 уровне!");
                            break;
                        case 3:
                            p.sendMessage(WorldUtils.worldName() + " §aИнформация о задании §a" + q.getName() + " §c#" + quest);
                            p.sendMessage("§fАнри попросила убить здешлих обитателей");
                            p.sendMessage("§7Необходимо убить:");
                            p.sendMessage("§7-" + MobSystem.getName(22) + ": §f" + player.getMob("22") + "/" + 7);
                            p.sendMessage( "§7-" + MobSystem.getName(23) + ": §f" + player.getMob("23") + "/" + 7);
                            p.sendMessage("§7- §620 опыта");
                            break;
                        case 4:
                            p.sendMessage(WorldUtils.worldName() + " §4Следующая стадия откроется на 17 уровне!");
                            break;
                        case 5:
                            p.sendMessage(WorldUtils.worldName() + " §aИнформация о задании §a" + q.getName() + " §c#" + quest);
                            p.sendMessage("§fАнри попросила убить здешлих обитателей");
                            p.sendMessage("§7Необходимо убить:");
                            p.sendMessage("§7-" + MobSystem.getName(24) + ": §f" + player.getMob("24") + "/" + 10);
                            p.sendMessage("§7- §620 опыта");
                            break;
                        case 6:
                            p.sendMessage(WorldUtils.worldName() + " §aИнформация о задании §a" + q.getName() + " §c#" + quest);
                            p.sendMessage("§fНадо создать амулет");
                            p.sendMessage("§7Необходимо собрать:");
                            p.sendMessage("§7-" + RPGItemContainer.getRPGItemContainer(107).getName() + " " + WorldUtils.checkItems(player,107, 0) + "/1" );
                            p.sendMessage("§7- §620 опыта");
                            break;
                        case 7:
                            p.sendMessage(WorldUtils.worldName() + " §4Следующая стадия откроется на 18 уровне!");
                            break;
                        case 8:
                            p.sendMessage(WorldUtils.worldName() + " §aИнформация о задании §a" + q.getName() + " §c#" + quest);
                            p.sendMessage("§fТеперь я могу их убить!");
                            p.sendMessage("§7Необходимо убить:");
                            p.sendMessage("§7-" + MobSystem.getName(25) + ": §f" + player.getMob("25") + "/" + 7);
                            p.sendMessage("§7- §620 опыта");
                            break;
                        case 9:
                            p.sendMessage(WorldUtils.worldName() + " §4Следующая стадия откроется на 19 уровне!");
                            break;
                        case 10:
                            p.sendMessage(WorldUtils.worldName() + " §aИнформация о задании §a" + q.getName() + " §c#" + quest);
                            p.sendMessage("§fТеперь я могу их убить!");
                            p.sendMessage("§7Необходимо убить:");
                            p.sendMessage("§7-" + MobSystem.getName(26) + ": §f" + player.getMob("26") + "/" + 7);
                            p.sendMessage("§7- §620 опыта");
                            break;
                        case 11:
                            p.sendMessage(WorldUtils.worldName() + " §aИнформация о задании §a" + q.getName() + " §c#" + quest);
                            p.sendMessage("§fТеперь я могу их убить!");
                            p.sendMessage("§7Необходимо убить:");
                            p.sendMessage("§7-" + MobSystem.getName(27) + ": §f" + player.getMob("27") + "/" + 1);
                            p.sendMessage("§7- §620 опыта");
                            p.sendMessage("§7Награда: " + RPGItemContainer.getRPGItemContainer(6).getName() + "x2");
                            break;
                    }

                } else {
                    p.sendMessage(WorldUtils.worldName() + " §cДанного квеста не существует!");
                }
                break;
        }


    }
}
