package azerot.azerot.Quest;

import azerot.azerot.Cutscene.Pleyada;
import azerot.azerot.Cutscene.initial;
import azerot.azerot.Cutscene.startSecondPatch;
import azerot.azerot.RPGItem;
import azerot.azerot.WorldUtils;
import azerot.azerot.azerot;
import azerot.azerot.mobs.MobSystem;
import azerot.azerot.questsScripts.ThreePatch;
import azerot.azerot.rpgplayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class questScript
{

    public static void ScriptsQuest(Player p, String quest, String idMob, Boolean talkNPC, Integer idNPC) throws SQLException {
        rpgplayer player = rpgplayer.getRPGPlayer(p);
        switch (quest){
            case "1":
                switch (player.getQuestsStage().get(quest)){
                    case 0:
                        player.getKilledMobs().put(String.valueOf(1), 0);
                        player.getKilledMobs().put(String.valueOf(2), 0);
                        player.getKilledMobs().put(String.valueOf(3), 0);
                        player.getQuestsStage().put(quest, 1);
                        ScriptsQuest(p, quest, String.valueOf(0), false, 0);
                        break;
                    case 1:
                        switch (idMob){
                            case "1":
                                if(player.getKilledMobs().get("1") <= 5){
                                    player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 5 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + quest);
                                }
                                break;
                            case "2":
                                if(player.getKilledMobs().get("2") <= 5){
                                    player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 5 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + quest);
                                }
                                break;
                            case "3":
                                if(player.getKilledMobs().get("3") <= 5){
                                    player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 5 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + quest);
                                }
                                break;

                        }
                        if(player.getKilledMobs().get("1") >= 5 && player.getKilledMobs().get("2") >= 5 && player.getKilledMobs().get("3") >= 5){
                            player.getQuestsStage().put(quest, 2);
                            player.addXP(50);
                            player.getDialogueBetweenNPC().put("1-1", 1);
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 2);
                        }
                        break;
                    case 2:
                        if(talkNPC){
                            if(idNPC == 1){
                                switch (player.getDialogueBetweenNPC().get("1-1")){
                                    case 1:
                                        p.sendMessage("§7§o[1/12] §2Старик§7§o: Ох, кажется у нас снова путник?" +
                                                " Ха-ха, я был прав. Приветствую путник, как добрался? Разбойники не сильно потрепали?");
                                        player.getDialogueBetweenNPC().put("1-1", 2);
                                        break;
                                    case 2:
                                        p.sendMessage("§7§o[2/12] §6" + p.getName() + "§7§o: Приветствую. Нет, слава богу, у меня был с собой меч.");
                                        player.getDialogueBetweenNPC().put("1-1", 3);
                                        break;
                                    case 3:
                                        p.sendMessage("§7§o[3/12] §2Старик§7§o: Меч? Покажи мне его. Хм... Откуда у тебя этот меч?");
                                        player.getDialogueBetweenNPC().put("1-1", 4);
                                        break;
                                    case 4:
                                        p.sendMessage("§7§o[4/12] §6" + p.getName() + "§7§o: Да честно говоря, сам бы хотел знать. А еще у меня есть какой-то знак.");
                                        player.getDialogueBetweenNPC().put("1-1", 5);
                                        break;
                                    case 5:
                                        p.sendMessage("§7§o[5/12] §2Старик§7§o: Впервые вижу такой чистый и искусный орнамент. Похоже на королевский герб… Значит, к нам затесался путник из королевской элиты!");
                                        player.getDialogueBetweenNPC().put("1-1", 6);
                                        break;
                                    case 6:
                                        p.sendMessage("§7§o[6/12] §6" + p.getName() + "§7§o: С чего ты взял?");
                                        player.getDialogueBetweenNPC().put("1-1", 7);
                                        break;
                                    case 7:
                                        p.sendMessage("§7§o[7/12] §2Старик§7§o: Ха-ха-ха, видимо, ты действительно забыл? Королевский меч могут носить только королевские отпрыски, а такой герб получают лишь единицы, и то, лично от короля. Отсюда и вывод: ты не простой путешественник. Поверь мне, я повидал на своем веку немало путников, и все они приходили сюда с кровавыми кулаками, палками… Да вообще с первым попавшимся под руку оружием! А ты единственный, кто пришел с настоящим оружием, пусть и ржавым");
                                        player.getDialogueBetweenNPC().put("1-1", 8);
                                        break;
                                    case 8:
                                        p.sendMessage("§7§o[8/12] §2Старик§7§o: Ну, одно я могу сказать точно: здесь я тебе ничем не помогу. Если хочешь узнать о себе и своем предназначении в этом мире, отправляйся в деревню Элькорн");
                                        player.getDialogueBetweenNPC().put("1-1", 9);
                                        break;
                                    case 9:
                                        p.sendMessage("§7§o[9/12] §6" + p.getName() + "§7§o: А как ту...");
                                        player.getDialogueBetweenNPC().put("1-1", 10);
                                        break;
                                    case 10:
                                        p.sendMessage("§7§o[10/12] §2Старик§7§o: Погоди, дай договорить! В деревню путь будет не близкий и сложный, поэтому слушай внимательно. По пути ты встретишь опасных монстров, будь осторожен. Иди прямо по дороге, главное, не сворачивай с тропы, а иначе… Ха-ха-ха. Шучу, не бойся. ");
                                        player.getDialogueBetweenNPC().put("1-1", 11);
                                        break;
                                    case 11:
                                        p.sendMessage("§7§o[11/12] §2Старик§7§o: Дальше тебе нужно пройти сквозь паучью рощу. Там ты сможешь потренироваться в битвах с монстрами. Опыт тебе пригодится, поверь." +
                                                " По дороге ты увидишь большое строение посреди леса. Если ты его увидел, значит, ты почти добрался.");
                                        player.getDialogueBetweenNPC().put("1-1", 12);
                                        break;
                                    case 12:
                                        p.sendMessage("§7§o[12/12] §2Старик§7§o: Как только пройдешь дальше, ты увидишь небольшую деревеньку с пристанью." +
                                                " Там найди Управляющего и передай ему письмо от меня. А вот еще держи 32 яблока – дорога долгая, и удачи!");
                                        p.getInventory().addItem(new RPGItem().getRPGItem(20,0,32,0,0,0,0,0, player));
                                        ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 3);
                                        break;
                                }
                            }
                        }
                        break;
                    case 3:
                        break;
                    case 4:
                        ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 5);
                        player.addXP(75);
                        player.getDialogueBetweenNPC().put("1-2", 1);
                        break;
                    case 5:
                        if(talkNPC){
                            if(idNPC == 3){
                                switch (player.getDialogueBetweenNPC().get("1-2")){
                                    case 1:
                                        p.sendMessage("§7§o[1/2] §6"  + p.getName() + "§7§o: Простите, сэр, вы не могли бы подсказать, как дойти до деревни? Я немного заблудился.");
                                        player.getDialogueBetweenNPC().put("1-2", 2);
                                        break;
                                    case 2:
                                        p.sendMessage("§7§o[2/2] §2???§7§o: Деревня? О, да, совсем недалеко. Только дорога там непростая. Куча пауков завелась, недавно вот снова набежали.");
                                        ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 6);
                                        player.getKilledMobs().put(String.valueOf(4), 0);
                                        player.getKilledMobs().put(String.valueOf(5), 0);
                                        player.getKilledMobs().put(String.valueOf(6), 0);
                                        break;
                                }
                            }
                        }
                        break;
                    case 6:
                        if (idMob.equals("4")) {
                            if (player.getKilledMobs().get("4") <= 5) {
                                player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 5 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                            }
                            if (player.getKilledMobs().get("4") >= 5) {
                                player.addXP(80);
                                ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 7);
                            }
                        }
                        break;
                    case 7:
                        if (idMob.equals("5")) {
                            if (player.getKilledMobs().get("5") <= 5) {
                                player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 5 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                            }
                            if (player.getKilledMobs().get("5") >= 5) {
                                player.addXP(120);
                                ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 8);
                            }
                        }

                        break;
                    case 8:
                        if (idMob.equals("6")) {
                            if (player.getKilledMobs().get("6") <= 1) {
                                player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 1 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                            }
                            if (player.getKilledMobs().get("6") >= 1) {
                                player.addXP(150);
                                ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 9);
                            }
                        }
                        break;
                    case 10:
                        player.addXP(75);
                        ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 11);
                        break;
                    case 11:
                        if(player.getLevel() >= 7){
                            player.getQuestsStage().put(quest, 12);
                            player.getDialogueBetweenNPC().put("1-3", 1);
                        }
                        break;
                    case 12:
                        if(talkNPC){
                            if(idNPC == 7){
                                switch (player.getDialogueBetweenNPC().get("1-3")){
                                    case 1:
                                        p.sendMessage("§7§o[1/8] §6"  + p.getName() + "§7§o: Здравствуйте, вы управляющий?");
                                        player.getDialogueBetweenNPC().put("1-3", 2);
                                        break;
                                    case 2:
                                        p.sendMessage("§7§o[2/8] §2???§7§o: А ты кого-то другого видишь здесь? Ха-ха-ха. Ладно, что у тебя там? У меня сейчас важные дела, меня ждут в таверне выпить.");
                                        player.getDialogueBetweenNPC().put("1-3", 3);
                                        break;
                                    case 3:
                                        p.sendMessage("§7§o[3/8] §6"  + p.getName() + "§7§o: А точно, Старик просил передать письмо, судя по всему, вам.");
                                        player.getDialogueBetweenNPC().put("1-3", 4);
                                        break;
                                    case 4:
                                        p.sendMessage("§7§o[4/8] §2Управляющий§7§o: Какой старик? Не важно, давай письмо.");
                                        player.getDialogueBetweenNPC().put("1-3", 5);
                                        break;
                                    case 5:
                                        p.sendMessage("§7§o[5/8] §2Управляющий§7§o: Ха-ха-ха, вот же старик, до сих пор не сдох! Так и присылает мне лишних проблем на голову, эх! " +
                                                "Вкратце, ситуация мне понятна, и скажу сразу, что я не тот, кто тебе нужен.");
                                        player.getDialogueBetweenNPC().put("1-3", 6);
                                        break;
                                    case 6:
                                        p.sendMessage("§7§o[6/8] §2Управляющий§7§o: Единственное, что я могу тебе помочь сделать, это направить в место, где тебе помогут немного разобраться, кто ты и откуда ты, возможно. \n" +
                                                "Но учти, это не бесплатно. Поэтому давай сначала ты помогаешь мне, потом я тебе, договорились?");
                                        player.getDialogueBetweenNPC().put("1-3", 7);
                                        break;
                                    case 7:
                                        p.sendMessage("§7§o[7/8] §6"  + p.getName() + "§7§o: Хорошо, а что нужно сделать?");
                                        player.getDialogueBetweenNPC().put("1-3", 8);
                                        break;
                                    case 8:
                                        p.sendMessage("§7§o[8/8] §2Управляющий§7§o: Вообщем, слушай. \n" +
                                                "Через поле идет каменистая тропа к горе. \n" +
                                                "Там обитают твари, которые частенько любят нападать на деревню. Поэтому твоя задача проста - перебить их.");
                                        ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 13);
                                        player.getKilledMobs().put(String.valueOf(7), 0);
                                        break;
                                }
                            }

                        }
                        break;
                    case 13:
                        if (idMob.equals("7")) {
                            if (player.getKilledMobs().get("7") <= 7) {
                                player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 7 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                            }
                            if (player.getKilledMobs().get("7") >= 7) {
                                player.addXP(150);
                                ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 14);
                            }
                        }
                        break;
                    case 14:
                        if(player.getLevel() >= 8){
                            player.getKilledMobs().put(String.valueOf(8), 0);
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 15);
                        }
                        break;
                    case 15:
                        if (idMob.equals("8")) {
                            if (player.getKilledMobs().get("8") <= 7) {
                                player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 7 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                            }
                            if (player.getKilledMobs().get("8") >= 7) {
                                player.addXP(150);
                                ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 16);
                            }
                        }
                        break;
                    case 16:
                        if(player.getLevel() >= 9){
                            player.getKilledMobs().put(String.valueOf(9), 0);
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 17);
                        }
                        break;
                    case 17:
                        if (idMob.equals("9")) {
                            if (player.getKilledMobs().get("9") <= 7) {
                                player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 7 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                            }
                            if (player.getKilledMobs().get("9") >= 7) {
                                player.addXP(150);
                                ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 18);
                            }
                        }
                        break;
                    case 18:
                        int amount = WorldUtils.checkItems(player, 57, 0);
                        if(amount >= 7){
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 19);
                            player.addXP(250);
                        }
                        break;
                    case 19:
                        if(player.getLevel() >= 10){
                            player.getDialogueBetweenNPC().put("1-4", 1);
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 20);
                        }
                        break;
                    case 20:
                        if(talkNPC){
                            if(idNPC == 7){
                                switch (player.getDialogueBetweenNPC().get("1-4")){
                                    case 1:
                                        p.sendMessage("§7§o[1/4] §2Управляющий§7§o: Ну что-то ты не сильно похож на королевского гвардейца, ну не суть. Вижу, что ты справился с заданием. ");
                                        player.getDialogueBetweenNPC().put("1-4", 2);
                                        break;
                                    case 2:
                                        p.sendMessage("§7§o[2/4] §2Управляющий§7§o: Надеюсь, ты их хорошо там потрепал, ха-ха-ха, а то эти каменюки не дают спокойно попить вкуснейшего пива в нашей таверне. Так и наровят испортить мне настроение…");
                                        player.getDialogueBetweenNPC().put("1-4", 3);
                                        break;
                                    case 3:
                                        p.sendMessage("§7§o[3/4] §6"  + p.getName() + "§7§o: Что там с моей наградой?");
                                        player.getDialogueBetweenNPC().put("1-4", 4);
                                        break;
                                    case 4:
                                        p.sendMessage("§7§o[4/4] §2Управляющий§7§o: Ой, какие мы нетерпеливые! Помню, помню я о твоей награде.\n" +
                                                " Вот, держи. Отдай это тому скряге на корабле, прочитав письмо он поймет, куда тебя везти.\n" +
                                                " А и когда прибудешь на остров, найди Ванессу");
                                        player.completeQuest(quest);
                                        break;
                                }
                            }

                        }
                        break;
                }
                break;
            case "2":
                if(player.getQuestsStage().get(quest) != null){
                    switch (player.getQuestsStage().get(quest)){
                        case 0:
                            player.getKilledMobs().put(String.valueOf(10), 0);
                            player.getQuestsStage().put(quest, 1);
                            ScriptsQuest(p, quest, String.valueOf(0), false, 0);
                            break;
                        case 1:
                            if (idMob.equals("10")) {
                                if (player.getKilledMobs().get("10") <= 10) {
                                    player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 10 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                                }
                            }
                            if(player.getKilledMobs().get("10") >= 10){
                                player.addXP(1000);
                                player.getDialogueBetweenNPC().put("2-1", 1);
                                ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 2);
                            }
                            break;
                        case 2:
                            if(talkNPC){
                                if(idNPC == 8){
                                    switch (player.getDialogueBetweenNPC().get("2-1")){
                                        case 1:
                                            p.sendMessage("§7§o[1/11] §6"  + p.getName() + "§7§o: Черт побери, сколько у вас тут этой нежити?!");
                                            player.getDialogueBetweenNPC().put("2-1", 2);
                                            break;
                                        case 2:
                                            p.sendMessage("§7§o[2/11] §2???§7§o: Ха-ха, да, последнюю неделю их развелось как грибов после дождя. Не будем о грустном. Меня зовут Ванесса, а тебя?");
                                            player.getDialogueBetweenNPC().put("2-1", 3);
                                            break;
                                        case 3:
                                            p.sendMessage("§7§o[3/11] §6"  + p.getName() + "§7§o: Я… Я не помню. Я знал, кто я, но теперь всё как туман, пустота. Не знаю, кто я, не помню, почему здесь.");
                                            player.getDialogueBetweenNPC().put("2-1", 4);
                                            break;
                                        case 4:
                                            p.sendMessage("§7§o[4/11] §2Ванесса§7§o: Тяжело, когда даже свое имя не помнишь.");
                                            player.getDialogueBetweenNPC().put("2-1", 5);
                                            break;
                                        case 5:
                                            p.sendMessage("§7§o[5/11] §6"  + p.getName() + "§7§o: Меня сюда привели, сказали, что здесь я смогу вспомнить.");
                                            player.getDialogueBetweenNPC().put("2-1", 6);
                                            break;
                                        case 6:
                                            p.sendMessage("§7§o[6/11] §2Ванесса§7§o: У меня есть одно зелье на примете, могло бы помочь. Но сейчас я его не сварганю, мы в опасности.");
                                            player.getDialogueBetweenNPC().put("2-1", 7);
                                            break;
                                        case 7:
                                            p.sendMessage("§7§o[7/11] §6"  + p.getName() + "§7§o: В опасности? Что случилось?");
                                            player.getDialogueBetweenNPC().put("2-1", 8);
                                            break;
                                        case 8:
                                            p.sendMessage("§7§o[8/11] §2Ванесса§7§o: Долгая история. Не так давно к нам прибыли странствующие люди. \n" +
                                                    "Решили в горы забраться. И… пропали. Я пошла их искать и наткнулась на сектантов. Они людей в жертву приносят, а потом… вот эти “господа” из могил выползают.");
                                            player.getDialogueBetweenNPC().put("2-1", 9);
                                            break;
                                        case 9:
                                            p.sendMessage("§7§o[9/11] §2Ванесса§7§o: Недавно я заметила, что мертвяки куда-то делись. Я решила за одним проследить, а он меня в пещеру привел. Там сектанты обсуждали, что собираются на нас напасть.\n" +
                                                    " Поможешь нам?");
                                            player.getDialogueBetweenNPC().put("2-1", 10);
                                            break;
                                        case 10:
                                            p.sendMessage("§7§o[10/11] §6"  + p.getName() + "§7§o: Ладно. Но взамен ты должна сделать то зелье.");
                                            player.getDialogueBetweenNPC().put("2-1", 11);
                                            break;
                                        case 11:
                                            p.sendMessage("§7§o[11/11] §2Ванесса§7§o: Договорились! Но для него нужны ингредиенты. Ты можешь собрать их? Принеси мне [§2магической пыли§7§o]§7§o х5, [§2эльфийские чётки§7§o] §7§oх1 и [§2внутренности паразита§7§o] §7§oх5");
                                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 3);
                                            player.getKilledMobs().put(String.valueOf(11), 0);
                                            player.getKilledMobs().put(String.valueOf(12), 0);
                                            break;
                                    }
                                }
                            }
                            break;
                        case 3:
                            switch (idMob){
                                case "11":
                                    if(player.getKilledMobs().get("11") <= 7){
                                        player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 7 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                                    }
                                    break;
                                case "12":
                                    if(player.getKilledMobs().get("12") <= 7){
                                        player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 7 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                                    }
                                    break;
                            }
                            if(player.getKilledMobs().get("11") >= 7 && player.getKilledMobs().get("12") >= 7){
                                player.addXP(500);
                                ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 4);
                                ScriptsQuest(p, quest, "", false, 0);
                            }
                            break;
                        case 4:
                            if(player.getLevel() >= 12){
                                player.getKilledMobs().put(String.valueOf(13), 0);
                                player.getKilledMobs().put(String.valueOf(15), 0);
                                ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 5);
                            }
                            break;
                        case 5:
                            switch (idMob) {
                                case "13":
                                    if (player.getKilledMobs().get("13") <= 7) {
                                        player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 7 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                                    }
                                    break;
                                case "15":
                                    if (player.getKilledMobs().get("15") <= 7) {
                                        player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 7 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                                    }
                                    break;
                            }
                            if (player.getKilledMobs().get("13") >= 7 && player.getKilledMobs().get("15") >= 7) {
                                player.addXP(600);
                                ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 6);
                                ScriptsQuest(p, quest, "", false, 0);
                            }

                            break;
                        case 6:
                            if(player.getLevel() >= 13){
                                player.getKilledMobs().put(String.valueOf(16), 0);
                                ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 7);
                            }
                            break;
                        case 7:
                            switch (idMob) {
                                case "16":
                                    if (player.getKilledMobs().get("16") <= 10) {
                                        player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 10 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                                    }
                                    break;
                            }
                            if (player.getKilledMobs().get("16") >= 10) {
                                player.addXP(600);
                                ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 8);
                                ScriptsQuest(p, quest, "",false, 0);
                            }
                            break;
                        case 8:
                            if(player.getLevel() >= 14){
                                player.getKilledMobs().put(String.valueOf(14), 0);
                                player.getKilledMobs().put(String.valueOf(19), 0);
                                ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 9);
                            }
                            break;
                        case 9:
                            switch (idMob) {
                                case "14":
                                    if (player.getKilledMobs().get("14") <= 7) {
                                        player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 7 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                                    }
                                    break;
                                case "19":
                                    if (player.getKilledMobs().get("19") <= 1) {
                                        player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 1 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                                    }
                                    break;
                            }
                            if (player.getKilledMobs().get("14") >= 7 && player.getKilledMobs().get("19") >= 1) {
                                player.addXP(1500);
                                ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 10);
                            }
                            break;
                        case 10:
                            if(WorldUtils.checkItems(player, 79, 0) >= 1 &&  WorldUtils.checkItems(player, 54, 0) >= 5 && WorldUtils.checkItems(player, 82, 0) >= 5){
                                player.getDialogueBetweenNPC().put("2-2", 1);
                                WorldUtils.removeItems(player, 79, 1);
                                WorldUtils.removeItems(player, 54, 5);
                                WorldUtils.removeItems(player, 82, 5);
                                ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 11);
                            }
                            break;
                        case 11:
                            if(talkNPC){
                                if(idNPC == 8){
                                    switch (player.getDialogueBetweenNPC().get("2-2")){
                                        case 1:
                                            p.sendMessage("§7§o[1/7] §6"  + p.getName() + "§7§o: Ванесса, я выполнил задание. Сектанты мертвы, и я принес то, что ты просила.");
                                            player.getDialogueBetweenNPC().put("2-2", 2);
                                            break;
                                        case 2:
                                            p.sendMessage("§7§o[2/7] §2Ванесса§7§o: Отлично! Давай-ка сюда… Вот, держи, выпей это.");
                                            player.getDialogueBetweenNPC().put("2-2", 3);
                                            break;
                                        case 3:
                                            p.sendMessage("§7§o[3/7] §6"  + p.getName() + "§7§o: Кажется, я вспомнил… Меня зовут… " + p.getName());
                                            player.getDialogueBetweenNPC().put("2-2", 4);
                                            break;
                                        case 4:
                                            p.sendMessage("§7§o[4/7] §2Ванесса§7§o: Хорошо, по крайней мере, ты помнишь свое имя. Но это все? Неужели больше ничего не всплывает в памяти?");
                                            player.getDialogueBetweenNPC().put("2-2", 5);
                                            break;
                                        case 5:
                                            p.sendMessage("§7§o[5/7] §6"  + p.getName() + "§7§o: Кажется, да. Кроме имени, я не могу вспомнить ничего. Даже глядя на этот жетон… ничего не приходит на ум.");
                                            player.getDialogueBetweenNPC().put("2-2", 6);
                                            break;
                                        case 6:
                                            p.sendMessage("§7§o[6/7] §2Ванесса§7§o: Не знаю, как тебе помочь. Но прогресс есть. Возможно, со временем ты вспомнишь. Кстати, ты видел то огромное дерево, которое упало и ведет в горы?");
                                            player.getDialogueBetweenNPC().put("2-2", 7);
                                            break;
                                        case 7:
                                            p.sendMessage("§7§o[7/7] §2Ванесса§7§o: Оно ведет в горы. На другой стороне, недалеко от тропы, есть пещера. В ней сейчас находится Анри. Она может дать тебе подсказку, куда двигаться дальше.");
                                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 12);
                                            break;
                                    }
                                }
                            }
                            break;
                        case  12:
                            if(player.getLevel() >= 15){
                                player.completeQuest(quest);
                                (new BukkitRunnable() {
                                    public void run() {
                                        try {
                                            player.takeQuest("4");
                                        } catch (SQLException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                }).runTaskLater((Plugin)azerot.getInstance(), 20L);
                            }
                            break;
                    }
                }
                break;
            case "3":
                switch (player.getQuestsStage().get(quest)){
                    case 0:
                        player.getDialogueBetweenNPC().put("3-1", 1);
                        player.getQuestsStage().put(quest, 1);
                        player.getKilledMobs().put(String.valueOf(17), 0);
                        player.getKilledMobs().put(String.valueOf(18), 0);
                    case 1:
                        switch (idMob){
                            case "17":
                                if(player.getKilledMobs().get("17") <= 7){
                                    player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 7 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                                }
                                break;
                            case "18":
                                if(player.getKilledMobs().get("18") <= 7){
                                    player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 7 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                                }
                                break;
                        }
                        if(player.getKilledMobs().get("17") >= 7 && player.getKilledMobs().get("18") >= 7){
                            player.addXP(600);
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 2);
                            ScriptsQuest(p, quest, "", false, 0);
                        }
                        break;
                    case 2:
                        if(player.getLevel() >= 14){
                            player.getQuestsStage().put(quest, 3);
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 3);
                        }
                        break;
                    case 3:
                            if (WorldUtils.checkItems(player, 80, 0) >= 9) {
                                player.addXP(500);
                                player.completeQuest(quest);
                            }
                        break;
                }
                break;
            case "4":
                switch (player.getQuestsStage().get(quest)){
                    case 0:
                        player.getQuestsStage().put(quest, 1);
                        player.getDialogueBetweenNPC().put("4-1", 1);
                        break;
                    case 1:
                        if(talkNPC){
                            if(idNPC == 11){
                                switch (player.getDialogueBetweenNPC().get("4-1")){
                                    case 1:
                                        p.sendMessage("§7§o[1/14] §6"  + p.getName() + "§7§o: Эй, Анри!");
                                        p.sendMessage("§7§o[2/11] §2Анри§7§o: Да кто там еще? А, это ты. Чего встал как вкопанный? Заходи.");
                                        player.getDialogueBetweenNPC().put("4-1", 2);
                                        break;
                                    case 2:
                                        p.sendMessage("§7§o[3/14] §6"  + p.getName() + "§7§o: Вы обо мне уже знаете?");
                                        player.getDialogueBetweenNPC().put("4-1", 4);
                                        break;
                                    case 4:
                                        p.sendMessage("§7§o[4/14] §2Анри§7§o: Конечно, знаю. Как только ты ступил на этот проклятый остров, Ванесса мне все уши прожужжала");
                                        player.getDialogueBetweenNPC().put("4-1", 5);
                                        break;
                                    case 5:
                                        p.sendMessage("§7§o[5/14] §6"  + p.getName() + "§7§o: Когда же она все успевает...");
                                        player.getDialogueBetweenNPC().put("4-1", 6);
                                        break;
                                    case 6:
                                        p.sendMessage("§7§o[6/14] §2Анри§7§o: Да не волнуйся, у нее много времени. Я так понимаю, она отправила тебя ко мне, чтобы я помогла тебе восстановить память?");
                                        player.getDialogueBetweenNPC().put("4-1", 7);
                                        break;
                                    case 7:
                                        p.sendMessage("§7§o[7/14] §6"  + p.getName() + "§7§o: Да, кстати, а почему тут ещё больше нежити, чем рядом с лагерем?");
                                        player.getDialogueBetweenNPC().put("4-1", 8);
                                        break;
                                    case 8:
                                        p.sendMessage("§7§o[8/14] §2Анри§7§o: Если ты так сильно интересуешься, я тебе расскажу." +
                                                " Те люди, которых вы убили в пещере, были вынуждены стать сектантами. Незадолго до их прибытия на остров, на горе прогремел оглушительный взрыв.");
                                        player.getDialogueBetweenNPC().put("4-1", 9);
                                        break;
                                    case 9:
                                        p.sendMessage("§7§o[9/14] §2Анри§7§o: Испуганные, мы бросились к месту происшествия, но увиденное нас повергло в ужас. Замок пылал, чудовищные существа безжалостно убивали оставшихся в живых. \n" +
                                                "Внезапно мы увидели фигуру, облаченную во все черное. Я хотела подойти к нему, но его взгляд, полный пугающей силы, парализовал меня от страха. ");
                                        player.getDialogueBetweenNPC().put("4-1", 10);
                                        break;
                                    case 10:
                                        p.sendMessage("§7§o[10/14] §2Анри§7§o: Он исчез так же внезапно, как и появился, и мы, не колеблясь, бросились в пещеру, спасаясь от неизвестной угрозы. " +
                                                "Ну вот как-то так. После этого случая появились те сектанты в горе, и тысячи нежити на всем острове. А того человека мы больше не видели.");
                                        player.getDialogueBetweenNPC().put("4-1", 11);
                                        break;
                                    case 11:
                                        p.sendMessage("§7§o[11/14] §6"  + p.getName() + "§7§o: Занятная история.");
                                        player.getDialogueBetweenNPC().put("4-1", 12);
                                        break;
                                    case 12:
                                        p.sendMessage("§7§o[12/14] §2Анри§7§o: Да эта история может показаться выдуманной но поверь мне, на острове появился очень опасный человек!\n" +
                                                "Слушай, ну раз я тебе так много рассказала не хочешь мне помочь?");
                                        p.sendMessage("§7§o[13/14] §6"  + p.getName() + "§7§o: Опять??");
                                        player.getDialogueBetweenNPC().put("4-1", 13);
                                        break;
                                    case 13:
                                        p.sendMessage("§7§o[14/14] §2Анри§7§o: Что за нытье? Ты же был королевским гвардейцев, и ты хочешь оставить все это как есть? Этот человек очень опасен, я не думаю, что он остановится. Надо его остановить!");
                                        ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 2);
                                        player.getKilledMobs().put(String.valueOf(20), 0);
                                        player.getKilledMobs().put(String.valueOf(21), 0);
                                        break;
                                }
                            }
                        }
                        break;
                    case 2:
                        switch (idMob){
                            case "20":
                                if(player.getKilledMobs().get("20") <= 7){
                                    player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 7 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                                }
                                break;
                            case "21":
                                if(player.getKilledMobs().get("21") <= 7){
                                    player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 7 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                                }
                                break;
                        }
                        if(player.getKilledMobs().get("20") >= 7 && player.getKilledMobs().get("21") >= 7){
                            ScriptsQuest(p, quest, "", false,0);
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 3);
                        }
                        break;
                    case 3:
                        if(player.getLevel() >= 16){
                            player.getKilledMobs().put(String.valueOf(22), 0);
                            player.getKilledMobs().put(String.valueOf(23), 0);
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 4);
                        }
                        break;
                    case 4:
                            switch (idMob) {
                                case "22":
                                    if (player.getKilledMobs().get("22") <= 7) {
                                        player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 7 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                                    }
                                    break;
                                case "23":
                                    if (player.getKilledMobs().get("23") <= 7) {
                                        player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 7 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                                    }
                                    break;
                            }
                            if (player.getKilledMobs().get("22") >= 7 && player.getKilledMobs().get("23") >= 7) {
                                ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 5);
                                ScriptsQuest(p, quest, "", false, 0);
                            }

                        break;
                    case 5:
                        if(player.getLevel() >= 17){
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 6);
                            player.getDialogueBetweenNPC().put("4-2", 1);
                        }
                        break;
                    case 6:
                        if(talkNPC){
                            if(idNPC == 11){
                                switch (player.getDialogueBetweenNPC().get("4-2")){
                                    case 1:
                                        p.sendMessage("§7§o[1/5] §6"  + p.getName() + "§7§o: Анри, слушай, я там заметил шахту… Куда она ведет?");
                                        player.getDialogueBetweenNPC().put("4-2", 2);
                                        break;
                                    case 2:
                                        p.sendMessage("§7§o[2/5] §2Анри§7§o: На другую сторону острова, дурак. Мы туда ни ногой. Во время взрыва там было полно шахтеров, и все, кто выжил, превратились в тварей.");
                                        player.getDialogueBetweenNPC().put("4-2", 3);
                                        break;
                                    case 3:
                                        p.sendMessage("§7§o[3/5] §2Анри§7§o: Они до сих пор бродят там. Ты хочешь спуститься в ту могилу?");
                                        player.getDialogueBetweenNPC().put("4-2", 4);
                                        break;
                                    case 4:
                                        p.sendMessage("§7§o[4/5] §6"  + p.getName() + "§7§o: Ну хотелось бы.");
                                        player.getDialogueBetweenNPC().put("4-2", 5);
                                        break;
                                    case 5:
                                        p.sendMessage("§7§o[5/5] §2Анри§7§o: Если ты думаешь, что у тебя есть шансы, тогда вперед. Но если ты не вернешься, мы тебя искать не будем.");
                                        ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 7);
                                        player.getKilledMobs().put(String.valueOf(24), 0);
                                        break;
                                }
                            }
                        }
                        break;
                    case 7:
                            switch (idMob) {
                                case "24":
                                    if (player.getKilledMobs().get("24") <= 10) {
                                        player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 10 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                                    }
                                    break;
                            }
                            if (player.getKilledMobs().get("24") >= 10) {
                                ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 8);
                                player.addXP(1600);
                                ScriptsQuest(p, quest, "", false, 0);
                                player.getKilledMobs().put(String.valueOf(25), 0);
                            }
                        break;
                    case 8:
                        // тут надо ударить призрака
                        break;
                    case 9:
                        ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 10);
                        player.getDialogueBetweenNPC().put("4-3", 1);
                        try {
                            questScript.ScriptsQuest(p, "4", "0", false, 0);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case 10:
                        if(talkNPC){
                            if(idNPC == 11){
                                switch (player.getDialogueBetweenNPC().get("4-3")){
                                    case 1:
                                        p.sendMessage("§7§o[1/5] §2Анри§7§o: Не может быть! Живой?");
                                        player.getDialogueBetweenNPC().put("4-3", 2);
                                        break;
                                    case 2:
                                        p.sendMessage("§7§o[2/5] §6"  + p.getName() + "§7§o: Да, там полно шахтеров, но они не отличаются силой, да и вооружены лишь кирками");
                                        player.getDialogueBetweenNPC().put("4-3", 3);
                                        break;
                                    case 3:
                                        p.sendMessage("§7§o[3/5] §6"  + p.getName() + "§7§o: Пройдя по шахте, я столкнулся с призраками, но как ни старался, не мог их даже задеть");
                                        player.getDialogueBetweenNPC().put("4-3", 4);
                                        break;
                                    case 4:
                                        p.sendMessage("§7§o[4/5] §2Анри§7§o: Вот Значит, так. Вероятно, эта штука действительно создана для этого");
                                        player.getDialogueBetweenNPC().put("4-3", 5);
                                        break;
                                    case 5:
                                        if(WorldUtils.hasInventory(p, 1)){
                                            p.sendMessage("§7§o[5/5] §2Анри§7§o: Вот, держи. Я не уверена, но этот амулет может помочь тебе победить призраков");
                                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 11);
                                            p.getInventory().addItem(new RPGItem().getRPGItem(96,0,1,0,0,0,0,0, player));
                                            player.getKilledMobs().put(String.valueOf(24), 0);
                                        }else {
                                            p.sendMessage(WorldUtils.worldName() + " §4Недостаточно места!");
                                        }
                                        break;
                                }
                            }
                        }
                        break;
                    case 11:
                        if(player.getLevel() >= 18){
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 12);
                        }
                        break;
                    case 12:
                        if (Objects.equals(idMob, "25"))
                            player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 7 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                        if (player.getKilledMobs().get("25") >= 7) {
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 13);
                            player.addXP(2800);
                        }
                        break;
                    case 13:
                        if(player.getLevel() >= 19){
                            player.getKilledMobs().put(String.valueOf(26), 0);
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 14);
                            p.sendTitle("§6Задание обновлено!", "", 10, 70, 20);
                        }
                        break;
                    case 14:
                            if (Objects.equals(idMob, "26")) {
                                player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 7 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                            }
                            if (player.getKilledMobs().get("26") >= 7) {
                                player.getKilledMobs().put(String.valueOf(27), 0);
                                player.getDialogueBetweenNPC().put("4-4", 1);
                                player.addXP(3000);
                                ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 15);
                            }
                        break;
                    case 15:
                        if(talkNPC){
                            if(idNPC == 12){
                                switch (player.getDialogueBetweenNPC().get("4-4")){
                                    case 1:
                                        p.sendMessage("§7§o[1/3] §6"  + p.getName() + "§7§o: Анри? Что ты здесь делаешь?");
                                        player.getDialogueBetweenNPC().put("4-4", 2);
                                        break;
                                    case 2:
                                        p.sendMessage("§7§o[2/3] §2Анри§7§o: Как что? Ты же очистил пещеру, и теперь она безопасна. Я решила спуститься. Вижу, амулет действительно работает.");
                                        player.getDialogueBetweenNPC().put("4-4", 3);
                                        break;
                                    case 3:
                                        p.sendMessage("§7§o[3/3] §2Анри§7§o: Ты чувствуешь это? Кажется, у нас проблема…");
                                        player.getKilledMobs().put(String.valueOf(27), 0);
                                        ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 16);
                                        break;
                                }
                            }
                        }
                        break;
                    case 16:
                        if (Objects.equals(idMob, " 27")) {
                            player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 1 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                        }
                       if(player.getKilledMobs().get("27") >= 1){
                           player.addXP(3500);
                            player.completeQuest(quest);
                        }
                        break;
                }
                break;
            case "5":
                switch (player.getQuestsStage().get(quest)){
                    case 0:
                        player.getQuestsStage().put(quest, 1);
                        break;
                    case 1:
                        break;
                    case 2:
                        if(talkNPC){
                            if(idNPC == 13){
                                switch (player.getDialogueBetweenNPC().get("5-1")){
                                    case 1:
                                        p.sendMessage("§7§o[1/6] §2Девушка§7§o: Эй, путешественник! Подойди ко мне, пожалуйста");
                                        player.getDialogueBetweenNPC().put("5-1", 2);
                                        break;
                                    case 2:
                                        p.sendMessage("§7§o[2/6] §2Девушка§7§o: У меня небольшая проблема… Ты не мог бы помочь мне добраться до деревни?");
                                        player.getDialogueBetweenNPC().put("5-1", 3);
                                        break;
                                    case 3:
                                        p.sendMessage("§7§o[3/6] §6" + p.getName() + "§7§o: А что случилось?");
                                        player.getDialogueBetweenNPC().put("5-1", 4);
                                        break;
                                    case 4:
                                        p.sendMessage("§7§o[4/6] §2Девушка§7§o: Да вот, когда я ходила за травами, была такая чудесная погода, что я совсем потеряла счет времени. Закончила собирать, а уже ночь наступила. \n" +
                                                "А в последнее время по ночам… ну, короче, ходят какие-то страшные твари. Боюсь идти одна. Так что поможешь?");
                                        player.getDialogueBetweenNPC().put("5-1", 5);
                                        break;
                                    case 5:
                                        p.sendMessage("§7§o[5/6] §6" + p.getName() + "§7§o: Не беспокойся, я помогу тебе добраться. У меня есть некоторый опыт в борьбе с всякой нечистью.");
                                        player.getDialogueBetweenNPC().put("5-1", 6);
                                        break;
                                    case 6:
                                        p.sendMessage("§7§o[6/6] §2Девушка§7§o: О, как здорово! Тогда пошли быстрее!");
                                        ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 3);
                                            p.teleport(new Location(p.getWorld(), -4452, 105, 3285, 132, 2));
                                            p.sendTitle("§6Задание обновлено!", "", 10, 70, 20);
                                            player.getKilledMobs().put("28",0);
                                            player.getKilledMobs().put("29",0);
                                        break;
                                }
                            }
                        }
                        break;
                    case 3:
                        switch (idMob) {
                            case "28":
                                if (player.getKilledMobs().get("28") <= 3) {
                                    player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 3 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                                }
                                break;
                            case "29":
                                if (player.getKilledMobs().get("29") <= 3) {
                                    player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 3 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                                }
                                break;
                        }
                        if (player.getKilledMobs().get("28") >= 3 && player.getKilledMobs().get("29") >= 3) {
                            player.addXP(2500);
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 4);
                        }
                        break;
                    case 4:
                        if(talkNPC){
                            if(idNPC == 13){
                                if(player.getSpawnFakeNPC().get(13).getBukkitEntity().getLocation().distance(new Location(p.getWorld(), -4455, 105, 3282)) <= 3){
                                    p.teleport(new Location(p.getWorld(), -4681, 108, 3361, 90f, -2f));
                                    player.getDialogueBetweenNPC().put("5-2", 1);
                                    ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 5);
                                }

                            }
                        }
                        break;
                    case 5:
                        if(talkNPC){
                            if(idNPC == 14){
                                switch (player.getDialogueBetweenNPC().get("5-2")){
                                    case 1:
                                        p.sendMessage("§7§o[1/3] §2Элайя§7§o: Вот мы и в деревне. Как ты видел по пути сюда, стало очень много странных существ. Мы называем их “Эксперимент”. \n" +
                                                "Из-за них наша деревня практически заброшена, осталось всего несколько жителей.");
                                        player.getDialogueBetweenNPC().put("5-2", 2);
                                        break;
                                    case 2:
                                        p.sendMessage("§7§o[2/3] §6" + p.getName() + "§7§o: Действительно, пустовато тут.");
                                        player.getDialogueBetweenNPC().put("5-2", 3);
                                        break;
                                    case 3:
                                        p.sendMessage("§7§o[3/3] §2Элайя§7§o: Да, грустно. Кстати, иди познакомься с моим отцом, его зовут Корей. Он старейшина деревни и живет в доме на вершине холма.");
                                        player.getDialogueBetweenNPC().put("5-3", 1);
                                        ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 6);
                                        break;
                                }
                            }
                        }
                        break;
                    case 6:
                        if(talkNPC){
                            if(idNPC == 15){
                                switch (player.getDialogueBetweenNPC().get("5-3")){
                                    case 1:
                                        p.sendMessage("§7§o[1/10] §2Корей§7§o: Благодарю тебя за помощь, путешественник! Меня зовут Корей, и я старейшина этой деревни.");
                                        player.getDialogueBetweenNPC().put("5-3", 2);
                                        break;
                                    case 2:
                                        p.sendMessage("§7§o[2/10] §6" + p.getName() + "§7§o: Да, Элайя рассказывала про вас. Я бы хотел узнать, вы как глава деревни, не знаете ли странного человека в черном?");
                                        player.getDialogueBetweenNPC().put("5-3", 3);
                                        break;
                                    case 3:
                                        p.sendMessage("§7§o[3/10] §2Корей§7§o: Насчет “в черном” я не знаю, но я думаю, что понимаю, о ком ты говоришь. Возможно, ты имеешь в виду нашего Алхимика.");
                                        player.getDialogueBetweenNPC().put("5-3", 4);
                                        break;
                                    case 4:
                                        p.sendMessage("§7§o[4/10] §2Корей§7§o: Он, подобно тебе, однажды пришел в нашу деревню. " +
                                                "Он был очень добрым и помогал всем жителям, но потом… все изменилось. ");
                                        player.getDialogueBetweenNPC().put("5-3", 5);
                                        break;
                                    case 5:
                                        p.sendMessage("§7§o[5/10] §2Корей§7§o: Рядом с его домом начали появляться разноцветные жижи, повсюду в лесу можно было найти различные следы его изучений, а по ночам стали слышны дикие крики.\n" +
                                                "И после этого появились эти… Эксперименты.");
                                        player.getDialogueBetweenNPC().put("5-3", 6);
                                        break;
                                    case 6:
                                        p.sendMessage("§7§o[6/10] §6" + p.getName() + "§7§o: Эксперименты? Что это такое?");
                                        player.getDialogueBetweenNPC().put("5-3", 7);
                                        break;
                                    case 7:
                                        p.sendMessage("§7§o[7/10] §2Корей§7§o: Да, странные существа, словно выросшие из его экспериментов. Они не агрессивны, но выглядят пугающе.");
                                        player.getDialogueBetweenNPC().put("5-3", 8);
                                        break;
                                    case 8:
                                        p.sendMessage("§7§o[8/10] §2Корей§7§o: Пару дней назад недалеко отсюда мы слышали мощный взрыв, источник которого мы не смогли найти. Но мы уверены, что Алхимик к этому причастен.");
                                        player.getDialogueBetweenNPC().put("5-3", 9);
                                        break;
                                    case 9:
                                        p.sendMessage("§7§o[9/10] §6" + p.getName() + "§7§o: Где находится этот Алхимик?");
                                        player.getDialogueBetweenNPC().put("5-3", 10);
                                        break;
                                    case 10:
                                        p.sendMessage("§7§o[10/10] §2Корей§7§o: Его дом находится слева, у подножия горы. Но не мог бы ты помочь нам с Экспериментами? Их стоило бы уничтожить. \n" +
                                                "Они, конечно, не агрессивны, но кто знает, что произойдет, если их оставить в покое.");
                                        player.getDialogueBetweenNPC().put("5-4", 1);
                                        ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 7);
                                        break;
                                }
                            }
                        }
                        break;
                    case 7:
                        if(talkNPC){
                            if(idNPC == 17){
                                switch (player.getDialogueBetweenNPC().get("5-4")){
                                    case 1:
                                        p.sendMessage("§7§o[1/19] §2Алхимик§7§o: Неужели, сам " + p.getName() + " пожаловал ко мне. Не ожидал.");
                                        player.getDialogueBetweenNPC().put("5-4", 2);
                                        break;
                                    case 2:
                                        p.sendMessage("§7§o[2/19] §6" + p.getName() + "§7§o: Ты знаешь меня?");
                                        player.getDialogueBetweenNPC().put("5-4", 3);
                                        break;
                                    case 3:
                                        p.sendMessage("§7§o[3/19] §2Алхимик§7§o: Ты не единственный, кто перешел с той стороны. Я наблюдал за тобой. Может быть, мы сможем… найти общий язык?");
                                        player.getDialogueBetweenNPC().put("5-4", 4);
                                        break;
                                    case 4:
                                        p.sendMessage("§7§o[4/19] §6" + p.getName() + "§7§o: Не пытайся меня умаслить. Корей сказал, что эти твари – твоё творение. Значит, и то, что случилось на той стороне, тоже твоя вина!");
                                        player.getDialogueBetweenNPC().put("5-4", 5);
                                        break;
                                    case 5:
                                        p.sendMessage("§7§o[5/19] §2Алхимик§7§o: Часть вины, да, лежит на мне. Я пытался их контролировать, но они взбунтовались, и у меня не хватило сил их остановить.");
                                        player.getDialogueBetweenNPC().put("5-4", 6);
                                        break;
                                    case 6:
                                        p.sendMessage("§7§o[6/19] §6" + p.getName() + "§7§o: Но зачем их создавать? Ты решил поиграть в Бога?");
                                        player.getDialogueBetweenNPC().put("5-4", 7);
                                        break;
                                    case 7:
                                        p.sendMessage("§7§o[7/19] §2Алхимик§7§o: Понимаешь, этот остров, как и все близлежащие, – это тюрьма. Не знаю, кто и по каким причинам сюда отправляет, но выбраться отсюда практически невозможно. \n" +
                                                "Я нашел древние записи, в которых говорится… не знаю, правда ли всё это, но я решил попробовать.");
                                        player.getDialogueBetweenNPC().put("5-4", 8);
                                        break;
                                    case 8:
                                        p.sendMessage("§7§o[8/19] §6" + p.getName() + "§7§o: Тюрьма? Я тебя совсем не понимаю…");
                                        player.getDialogueBetweenNPC().put("5-4", 9);
                                        break;
                                    case 9:
                                        p.sendMessage("§7§o[9/19] §2Алхимик§7§o: Это место - не просто остров. Это отдельное пространство, изолированное от всего. Единственный выход – через врата. Об этом тоже написано в записях. Я проводил исследования и понял: чтобы открыть врата, нужны четыре специальных кристалла. \n" +
                                                "А для их создания нужна жизнь. Не желая убивать местных, я решил создать этих монстров… но они вышли из-под контроля.");
                                        player.getDialogueBetweenNPC().put("5-4", 10);
                                        break;
                                    case 10:
                                        p.sendMessage("§7§o[10/19] §6" + p.getName() + "§7§o: И что ты хочешь от меня?");
                                        p.sendMessage("§7§o[11/19] §2Алхимик§7§o: Мы с тобой здесь пленники, " + p.getName() + " Но у нас есть шанс выбраться. С помощью тебя.");
                                        player.getDialogueBetweenNPC().put("5-4", 11);
                                        break;
                                    case 11:
                                        p.sendMessage("§7§o[12/19] §6" + p.getName() + "§7§o: С помощью меня? О чем ты?");
                                        p.sendMessage("§7§o[13/19] §2Алхимик§7§o: Я уже слишком стар для этого, а ты - сильный. Может быть, ты сможешь победить этих тварей и получить кристаллы?");
                                        player.getDialogueBetweenNPC().put("5-4", 12);
                                        break;
                                    case 12:
                                        p.sendMessage("§7§o[14/19] §6" + p.getName() + "§7§o: И что мне это даст?");
                                        p.sendMessage("§7§o[15/19] §2Алхимик§7§o: Свободу. Нам обоим.");
                                        player.getDialogueBetweenNPC().put("5-4", 13);
                                        break;
                                    case 13:
                                        p.sendMessage("§7§o[16/19] §6" + p.getName() + "§7§o: Понятно. А если я просто убью тебя сейчас?");
                                        p.sendMessage("§7§o[17/19] §2Алхимик§7§o: Тогда ты никогда не покинешь это место.");
                                        player.getDialogueBetweenNPC().put("5-4", 14);
                                        break;
                                    case 14:
                                        p.sendMessage("§7§o[18/19] §6" + p.getName() + "§7§o: Справедливо. Но мне нужно время обдумать.");
                                        p.sendMessage("§7§o[19/19] §2Алхимик§7§o: Хорошо. Я буду ждать тебя. Но поторопись. Время работает против нас.");
                                        player.addXP(1500);
                                        ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 8);
                                        break;
                                }
                            }
                        }
                        break;
                    case 8:
                        if(idNPC == 100000){
                            player.getKilledMobs().put("28",0);
                            ThreePatch.updateQuestAndSendMessage(p, quest, "§7[1/1] §6" + p.getName() + "§7§o: Он говорил так убедительно, словно всё это правда, но у меня всё ещё остались сомнения. Надо разобраться с Экспериментами и расспросить Корея об этом мире.", 9);
                        }
                        break;
                    case 9:
                        if (Objects.equals(idMob, "28")) {
                            player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 7 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                        }
                        if (player.getKilledMobs().get("28") >= 7) {
                            player.getKilledMobs().put(String.valueOf(29), 0);
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 10);
                            player.addXP(1650);
                        }
                        break;
                    case 10:
                        if (Objects.equals(idMob, "29")) {
                            player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 7 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                        }
                        if (player.getKilledMobs().get("29") >= 7) {
                            player.getKilledMobs().put(String.valueOf(30), 0);
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 11);
                            player.addXP(1650);
                        }
                        break;
                    case 11:
                        if (Objects.equals(idMob, "30")) {
                            player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 7 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                        }
                        if (player.getKilledMobs().get("30") >= 7) {
                            player.getKilledMobs().put(String.valueOf(31), 0);
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 12);
                            player.addXP(1650);
                        }
                        break;
                    case 12:
                        if (Objects.equals(idMob, "31")) {
                            player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 7 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                        }
                        if (player.getKilledMobs().get("31") >= 7) {
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 13);
                            player.getDialogueBetweenNPC().put("5-5", 1);
                            player.addXP(1650);
                        }
                        break;
                    case 13:
                        if(talkNPC){
                            if(idNPC == 15){
                                switch (player.getDialogueBetweenNPC().get("5-5")){
                                    case 1:
                                        p.sendMessage("§7§o[1/8] §6" + p.getName() + "§7§o: Корей, я уничтожил Экспериментов, как ты просил.");
                                        player.getDialogueBetweenNPC().put("5-5", 2);
                                        break;
                                    case 2:
                                        p.sendMessage("§7§o[2/8] §2Корей§7§o: Спасибо, путешественник.");
                                        player.getDialogueBetweenNPC().put("5-5", 3);
                                        break;
                                    case 3:
                                        p.sendMessage("§7§o[3/8] §6" + p.getName() + "§7§o: Кстати, у меня вопрос, почему ты, да и практически все называете меня \"путешественник\"?");
                                        player.getDialogueBetweenNPC().put("5-5", 4);
                                        break;
                                    case 4:
                                        p.sendMessage("§7§o[4/8] §2Корей§7§o: Ты не отсюда. Твоё существование… вне нашего понимания.");
                                        player.getDialogueBetweenNPC().put("5-5", 5);
                                        break;
                                    case 5:
                                        p.sendMessage("§7§o[5/8] §6" + p.getName() + "§7§o: Сколько таких, как я, ты встречал?");
                                        player.getDialogueBetweenNPC().put("5-5", 6);
                                        break;
                                    case 6:
                                        p.sendMessage("§7§o[6/8] §2Корей§7§o: За всё время… немало. Алхимик тоже такой как ты");
                                        player.getDialogueBetweenNPC().put("5-5", 7);
                                        break;
                                    case 7:
                                        p.sendMessage("§7§o[7/8] §6" + p.getName() + "§7§o: А где остальные?");
                                        player.getDialogueBetweenNPC().put("5-5", 8);
                                        break;
                                    case 8:
                                        p.sendMessage("§7§o[8/8] §2Корей§7§o: Я не знаю. Может, они - просто - ушли.");
                                        ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 14);
                                        player.getDialogueBetweenNPC().put("5-6", 1);
                                        break;
                                }
                            }
                        }
                        break;
                    case 14:
                        if(talkNPC){
                            if(idNPC == 17){
                                switch (player.getDialogueBetweenNPC().get("5-6")){
                                    case 1:
                                        p.sendMessage("§7§o[1/8] §2Алхимик§7§o: Ну что? Ты подумал?");
                                        player.getDialogueBetweenNPC().put("5-6", 2);
                                        break;
                                    case 2:
                                        p.sendMessage("§7§o[2/8] §6" + p.getName() + "§7§o: Я же не первый кто теперь приходил. Сколько было до меня и что с ними стало?");
                                        player.getDialogueBetweenNPC().put("5-6", 3);
                                        break;
                                    case 3:
                                        p.sendMessage("§7§o[3/8] §2Алхимик§7§o: Да, ты не первый. Я уже несколько веков здесь заперт. Сначала они приходили, помогали мне создавать этих существ. Но, к сожалению, они не были бессмертными.");
                                        player.getDialogueBetweenNPC().put("5-6", 4);
                                        break;
                                    case 4:
                                        p.sendMessage("§7§o[4/8] §2Алхимик§7§o: Спустя годы, умирали… от старости, от болезней… А я вот, никак не могу умереть, как бы я ни хотел.");
                                        player.getDialogueBetweenNPC().put("5-6", 5);
                                        break;
                                    case 5:
                                        p.sendMessage("§7§o[5/8] §6" + p.getName() + "§7§o: А что с другими?");
                                        player.getDialogueBetweenNPC().put("5-6", 6);
                                        break;
                                    case 6:
                                        p.sendMessage("§7§o[6/8] §2Алхимик§7§o: Другие погибали от тех существ, другие же пытались сразится со мной. " +
                                                "Понимаешь, я не смогу сам выбраться, я уже не так молод и силен как был, поэтому мне нужна твоя помощь, я буду координировать тебя, а ты собирать. Идет?");
                                        player.getDialogueBetweenNPC().put("5-6", 7);
                                        break;
                                    case 7:
                                        p.sendMessage("§7§o[7/8] §6" + p.getName() + "§7§o: Говоришь ты красиво, конечно. Но ладно, я помогу. Но что мне нужно делать?");
                                        player.getDialogueBetweenNPC().put("5-6", 8);
                                        break;
                                    case 8:
                                        p.sendMessage("§7§o[8/8] §2Алхимик§7§o: Перед тем как пойти на тот остров, мне нужно, чтобы ты принес мне не далеко закопанный сундук. Карту я тебе дам");
                                        if(WorldUtils.hasInventory(p, 1)){
                                            p.getInventory().addItem(new RPGItem().getRPGItem(1000,0,1,0,0,0, 0,0,null));
                                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 15);
                                        }else {
                                            p.sendMessage(WorldUtils.worldName() + " §4Недостаточно места в инвентаре!");
                                            return;
                                        }
                                        break;
                                }
                            }
                        }
                        break;
                    case 15:
                        if(WorldUtils.checkItems(player, 1001,0) >= 1){
                            if(talkNPC){
                                if(idNPC == 17){
                                    WorldUtils.removeItems(player, 1000, 1);
                                    WorldUtils.removeItems(player, 1001, 1);
                                    player.completeQuest(quest);
                                }
                            }
                        }
                        break;
                }
                break;
            case "6":
                switch (player.getQuestsStage().get(quest)){
                    case 0:
                        player.getQuestsStage().put(quest, 1);
                        player.getKilledMobs().put("32",0);
                        player.getKilledMobs().put("33",0);
                    case 1:
                        switch (idMob) {
                            case "32":
                                if (player.getKilledMobs().get("32") <= 10) {
                                    player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 10 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                                }
                                break;
                            case "33":
                                if (player.getKilledMobs().get("33") <= 10) {
                                    player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 10 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                                }
                                break;
                        }
                        if (player.getKilledMobs().get("32") >= 10 && player.getKilledMobs().get("33") >= 10) {
                            player.addXP(2500);
                            player.getKilledMobs().put("35",0);
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 2);
                            ScriptsQuest(p, quest, "", false, 0);
                        }
                        break;
                    case 2:
                        if (idMob.equals("35")) {
                            if (player.getKilledMobs().get("35") <= 10) {
                                player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 10 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                            }
                        }
                        if (player.getKilledMobs().get("35") >= 10) {
                            player.addXP(1250);
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 3);
                            ScriptsQuest(p, quest, "", false, 0);
                        }
                        break;
                    case 3:
                        if(player.getLevel() >= 22){
                            player.getQuestsStage().put(quest, 4);
                            player.getKilledMobs().put("34",0);
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 4);
                        }
                        break;
                    case 4:
                        switch (idMob) {
                            case "34":
                                if (player.getKilledMobs().get("34") <= 7) {
                                    player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 7 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                                }
                                break;
                        }
                        if (player.getKilledMobs().get("34") >= 7) {
                            player.addXP(3200);
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 5);
                        }

                        break;
                    case 5:
                        if(WorldUtils.checkItems(player, 148, 0) >= 15){
                            WorldUtils.removeItems(player, 148, 10);
                            player.addXP(1300);

                            if(WorldUtils.hasInventory(player.toPlayer(), 1)){
                                WorldUtils.removeItems(player, 148, 10);
                                player.getQuestsStage().put(quest, 6);
                                ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 6);
                                player.addXP(1300);
                                p.getInventory().addItem(new RPGItem().getRPGItem(236,0,1,0,0,0, 0,0, null));
                                player.addXP(3500);
                                (new BukkitRunnable() {
                                    public void run() {
                                        try {
                                            player.takeQuest("7");
                                        } catch (SQLException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                }).runTaskLater((Plugin)azerot.getInstance(), 20L);

                            }else {
                                p.sendMessage(WorldUtils.worldName() + " §4Недостаточно места в инвентаре!");
                                return;
                            }
                        }
                        break;
                    case 6:
                        if(player.getLevel() >= 23){
                            player.getKilledMobs().put("37",0);
                            player.getKilledMobs().put("38",0);
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 7);
                        }
                        break;
                    case 7:
                        switch (idMob) {
                            case "37":
                                if (player.getKilledMobs().get("37") <= 10) {
                                    player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 10 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                                }
                                break;
                            case "38":
                                if (player.getKilledMobs().get("38") <= 10) {
                                    player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 10 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                                }
                                break;
                        }
                        if (player.getKilledMobs().get("37") >= 10 && player.getKilledMobs().get("38") >= 10) {
                            player.addXP(5000);
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 8);
                            ScriptsQuest(p, quest, "", false, 0);
                        }
                        break;

                    case 8:
                        if(player.getLevel() >= 24){
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 9);
                            player.getKilledMobs().put("39",0);
                        }
                        break;
                    case 9:
                        switch (idMob) {
                            case "39":
                                if (player.getKilledMobs().get("39") <= 10) {
                                    player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 10 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                                }
                                break;
                        }
                        if (player.getKilledMobs().get("39") >= 10) {
                            player.getKilledMobs().put("41", 0);
                            player.addXP(2700);
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 10);
                        }
                        break;
                    case 10:
                        switch (idMob) {
                            case "41":
                                if (player.getKilledMobs().get("41") <= 7) {
                                    player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 7 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                                }
                                break;
                        }
                        if (player.getKilledMobs().get("41") >= 7) {
                            player.addXP(3500);
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 11);
                            ScriptsQuest(p, quest, "", false, 0);
                        }
                        break;
                    case 11:
                        if(player.getLevel() >= 25){
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 12);
                            player.getKilledMobs().put("40", 0);
                        }
                        break;
                    case 12:
                        if (Objects.equals(idMob, "40")) {
                            player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 7 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                        }
                        if (player.getKilledMobs().get("40") >= 7) {
                            player.addXP(4000);
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 13);
                        }
                        break;
                    case 13:
                        if(WorldUtils.checkItems(player, 149, 0) >= 15){
                            if(WorldUtils.hasInventory(player.toPlayer(), 1)){
                                WorldUtils.removeItems(player, 149, 15);
                                player.addXP(3500);
                                ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 14);
                                ScriptsQuest(p, quest, idMob, false, 0);
                                p.getInventory().addItem(new RPGItem().getRPGItem(237,0,1,0,0,0, 0,0,null));
                                player.addXP(3500);

                            }else {
                                p.sendMessage(WorldUtils.worldName() + " §4Недостаточно места в инвентаре!");
                                return;
                            }
                        }
                        break;
                    case 14:
                        if(player.getLevel() >= 26){
                            player.getKilledMobs().put("43", 0);
                            player.getKilledMobs().put("46", 0);
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 15);
                        }
                        break;
                    case 15:
                        if (Objects.equals(idMob, "43")) {
                            player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 15 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                        }
                        if (Objects.equals(idMob, "46")) {
                            player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 15 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                        }
                        if(player.getKilledMobs().get("43") >= 15 && player.getKilledMobs().get("46") >= 15){
                            player.getKilledMobs().put("44", 0);
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 16);
                            player.addXP(3000);

                        }
                        break;
                    case 16:
                        if (Objects.equals(idMob, "44")) {
                            player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 10 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                        }
                        if(player.getKilledMobs().get("44") >= 10){
                            player.getKilledMobs().put("45", 0);
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 17);
                            player.addXP(3500);
                        }
                        break;
                    case 17:
                        if (Objects.equals(idMob, "45")) {
                            player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 10 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                        }
                        if(player.getKilledMobs().get("45") >= 10){
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 18);
                            player.addXP(3000);
                        }
                        break;
                    case 18:
                        if(player.getLevel() >= 27){
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 19);
                            player.getKilledMobs().put("47", 0);
                            player.getKilledMobs().put("48", 0);
                        }
                        break;
                    case 19:
                        if (Objects.equals(idMob, "47") && Objects.equals(idMob, "48")) {
                            player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 7 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                        }
                        if(player.getKilledMobs().get("47") >= 7 && player.getKilledMobs().get("48") >= 7){
                            if(WorldUtils.hasInventory(p, 1)){
                                ItemStack item = (new RPGItem()).getRPGItem(7, 0, 2,  0, 0,0, 0, 0, player);
                                p.getInventory().addItem(item);
                                ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 20);
                                player.addXP(6000);
                            }else{
                                p.sendMessage(WorldUtils.worldName() + " §4Вы не можете завершить стадию, пока ваш инвентарь полон!");
                            }
                        }
                        break;
                    case 20:
                        if(WorldUtils.checkItems(player, 150, 0) >= 32){
                            if(WorldUtils.hasInventory(player.toPlayer(), 1)){
                                WorldUtils.removeItems(player, 150, 32);
                                ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 21);
                                player.addXP(3500);
                                ScriptsQuest(p, quest, idMob, false, 0);
                                p.getInventory().addItem(new RPGItem().getRPGItem(238,0,1,0,0,0, 0, 0,null));
                                player.addXP(3500);
                            }else {
                                p.sendMessage(WorldUtils.worldName() + " §4Недостаточно места в инвентаре!");
                                return;
                            }


                        }
                        break;
                    case 21:
                        if(player.getLevel() >= 28){
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 22);
                            player.getKilledMobs().put("50", 0);
                            player.getKilledMobs().put("51", 0);
                        }
                        break;
                    case 22:
                        if (Objects.equals(idMob, "50") && Objects.equals(idMob, "51")) {

                            player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 10 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                        }
                        if(player.getKilledMobs().get("50") >= 10 && player.getKilledMobs().get("51") >= 10){
                            player.getKilledMobs().put("53", 0);
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 23);
                            player.addXP(3650);
                        }
                        break;
                    case 23:
                        if (Objects.equals(idMob, "53")) {
                            player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 10 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                        }
                        if(player.getKilledMobs().get("53") >= 10){
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 24);
                            player.getKilledMobs().put("55", 0);
                            player.getKilledMobs().put("57", 0);
                            player.addXP(2000);
                        }
                        break;
                    case 24:
                        if (Objects.equals(idMob, "55") && Objects.equals(idMob, "57")) {
                            player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 10 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                        }
                        if(player.getKilledMobs().get("55") >= 10 && player.getKilledMobs().get("57") >= 10){
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 25);
                            player.addXP(5550);
                            ScriptsQuest(p, quest, idMob, false, 0);
                        }
                        break;
                    case 25:
                        if(player.getLevel() >= 29){
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 26);
                            player.getKilledMobs().put("54", 0);
                            player.getKilledMobs().put("58", 0);
                            player.getKilledMobs().put("52", 0);
                        }
                        break;
                    case 26:
                        if (Objects.equals(idMob, "54") && Objects.equals(idMob, "58") && Objects.equals(idMob, "52")) {
                            player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 7 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                        }
                        if(player.getKilledMobs().get("54") >= 7 && player.getKilledMobs().get("58") >= 7 && player.getKilledMobs().get("52") >= 7){
                            if(WorldUtils.hasInventory(p, 1)){
                                ItemStack item = (new RPGItem()).getRPGItem(7, 0, 3,  0, 0,0, 0, 0, player);
                                p.getInventory().addItem(item);
                                ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 27);
                                player.addXP(10500);
                                player.getKilledMobs().put("59", 0);
                            }else{
                                p.sendMessage(WorldUtils.worldName() + " §4Вы не можете завершить задание, пока ваш инвентарь полон!");
                            }
                        }
                        break;
                    case 27:
                        if (Objects.equals(idMob, "59")) {
                            player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 10 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                        }
                        if(player.getKilledMobs().get("59") >= 10){
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 28);
                            player.addXP(5500);
                        }
                        break;
                    case 28:
                        if(WorldUtils.checkItems(player, 208, 0) >= 32){
                            if(WorldUtils.hasInventory(player.toPlayer(), 1)){
                                WorldUtils.removeItems(player, 208, 32);
                                p.getInventory().addItem(new RPGItem().getRPGItem(239,0,1,0,0,0, 0, 0,null));
                                player.addXP(3500);
                            }else {
                                p.sendMessage(WorldUtils.worldName() + " §4Недостаточно места в инвентаре!");
                                return;
                            }
                        }
                        break;
                    case 29:
                        break;
                    case 30:
                        p.sendMessage("§7[1/1] §6" + p.getName() + "§7§o: Кажется его здесь нету... К дереву");
                        ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 31);
                        player.getDialogueBetweenNPC().put("6.1", 1);
                        break;
                    case 31:
                        if(talkNPC){
                            if(idNPC == 18){
                                switch (player.getDialogueBetweenNPC().get("6.1")){
                                    case 1:
                                        p.sendMessage("§7§o[1/3] §2Алхимик§7§o: Вот и всё! Пазл собран, все на своих местах. Эти старики заплатят за то, что заточили меня здесь!");
                                        player.getDialogueBetweenNPC().put("6.1", 2);
                                        break;
                                    case 2:
                                        p.sendMessage("§7§o[2/3] §6" + p.getName() + "§7§o: О чем ты говоришь?");
                                        player.getDialogueBetweenNPC().put("6.1", 3);
                                        break;
                                    case 3:
                                        p.sendMessage("§7§o[3/3] §2Алхимик§7§o: Не стоит притворяться. Твоя смерть уже предрешена. Помимо тех кристаллов, что ты собрал, для открытия портала нужна мощная душа, которая будет питать его энергией\n" +
                                                "Твоя душа - это топливо, которое освободит меня.");
                                        ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 32);
                                        player.getKilledMobs().put("60",0);
                                        break;
                                }
                            }
                        }
                        break;
                    case 32:
                        if (Objects.equals(idMob, "60")) {
                            player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 1 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                        }
                        if(player.getKilledMobs().get("60") >= 1){
                            player.completeQuest(quest);
                            player.addXP(5500);
                        }
                        break;
                }
                break;
            case "7":
                switch (player.getQuestsStage().get(quest)){
                    case 0:
                        player.getQuestsStage().put(quest, 1);
                        player.getKilledMobs().put("36",0);
                        break;
                    case 1:
                        if (Objects.equals(idMob, "36")) {
                            player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 1 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                        }
                        if(player.getKilledMobs().get("36") >= 1){
                            player.addXP(5500);
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 2);
                        }
                        break;
                    case 2:
                        if(player.getLevel() >= 25){
                            player.getKilledMobs().put("42",0);
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 3);
                        }
                        break;
                    case 3:
                        if (Objects.equals(idMob, "42")) {
                            player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 1 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                        }
                        if(player.getKilledMobs().get("42") >= 1){
                            player.addXP(5500);
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 4);
                        }
                        break;
                    case 4:
                        if(player.getLevel() >= 27){
                            player.getQuestsStage().put(quest, 5);
                            player.getKilledMobs().put("49",0);
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 5);
                        }
                        break;
                    case 5:
                        if (Objects.equals(idMob, "49")) {
                            player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 1 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                        }
                        if(player.getKilledMobs().get("49") >= 1){
                            player.addXP(5500);
                            player.completeQuest(quest);
                        }
                        break;
                }
                break;
            case "8":
                switch (player.getQuestsStage().get(quest)){
                    case 0:
                        player.getQuestsStage().put(quest, 1);
                        break;
                    case 1:
                        if(WorldUtils.checkItems(player, 231, 0) >= 2){
                            player.getQuestsStage().put(quest, 2);
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 2);
                            player.getDialogueBetweenNPC().put("18.1", 1);
                        }
                        break;
                    case 2:
                        if(player.getLevel() >= 25){
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 3);
                        }
                        break;
                    case 3:
                        if(WorldUtils.checkItems(player, 232, 0) >= 2){
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 4);
                        }
                        break;
                    case 4:
                        if(player.getLevel() >= 27){
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 5);
                        }
                        break;
                    case 5:
                        if(WorldUtils.checkItems(player, 233, 0) >= 2){
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 6);
                        }
                        break;
                    case 6:
                        if(player.getLevel() >= 29){
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 7);
                        }
                        break;
                    case 7:
                        if(WorldUtils.checkItems(player, 234, 0) >= 2){
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 8);
                        }
                        break;
                    case 8:
                        if(WorldUtils.checkItems(player, 235, 0) >= 1){
                            player.getQuestsStage().put(quest, 9);
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 9);
                        }
                        break;
                    case 9:
                        if(talkNPC){
                            if(idNPC == 14){
                                p.sendMessage("§7§o[1/1] §2Элайя§7§o: Спасибо");
                                player.completeQuest(quest);
                            }
                        }
                        break;
                }
                break;
        }


    }
}
