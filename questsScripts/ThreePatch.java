package azerot.azerot.questsScripts;

import azerot.azerot.Quest.Quest;
import azerot.azerot.WorldUtils;
import azerot.azerot.azerot;
import azerot.azerot.mobs.MobSystem;
import azerot.azerot.npcSpawnForRegion.npcInitialVillage;
import azerot.azerot.npcSpawnForRegion.spawnWolf;
import azerot.azerot.rpgplayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;

public class ThreePatch {

    public static void ScriptsForThreePatch(Player p, String quest, String idMob, Boolean talkNPC, Integer idNPC) throws SQLException {
        rpgplayer player = rpgplayer.getRPGPlayer(p);
        switch (quest) {
            case "9":
                switch (player.getQuestsStage().get(quest)){
                    case 0:
                        p.sendMessage("§7[1/2] §6" + p.getName() + "§7§o: Черт, что это было?");
                        p.sendMessage("§7[1/2] §6" + p.getName() + "§7§o: Там кажется пещера, мне надо туда");
                        p.sendTitle("§6Задание обновлено!", "", 10, 70, 20);
                        p.sendMessage(WorldUtils.worldName() + " §6Задание " + Quest.getQuest(quest).getName() + " было обновлено!");
                        break;
                    case 1:
                        p.sendMessage("§7[1/1] §6" + p.getName() + "§7§o: Кажется это порт, может тут будет кто-то?");
                        ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 2);
                        break;
                    case 2:
                        //Разговор в порту
                        break;
                    case 3:
                        //Дорого до деревни
                        break;
                    case 4:
                        //Разговор с нпс
                        break;
                    case 5:
                        //Разговор с нпс
                        break;
                    case 6:
                        //Разговор с нпс
                        break;
                    case 7:
                        //Разговор с Главным
                        player.completeQuest(quest);
                        break;
                }
                break;
            case "10":
                switch (player.getQuestsStage().get(quest)){
                    case 0:
                        ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 1);
                        player.getKilledMobs().put("61", 0);
                        break;
                    case 1:
                        if (Objects.equals(idMob, "61")) {
                            player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 1 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                        }
                        if(player.getKilledMobs().get("61") >= 7){
                            player.addXP(5500);
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 2);
                            player.getKilledMobs().put("62", 0);
                            player.getKilledMobs().put("63", 0);
                        }
                        break;
                    case 2:
                        //Сбор мяса
                        break;
                    case 3:
                        switch (idMob) {
                        case "62":
                            if (player.getKilledMobs().get("62") <= 7) {
                                player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 7 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                            }
                            break;
                            case "63":
                                if (player.getKilledMobs().get("63") <= 7) {
                                    player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 7 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                                }
                                break;
                    }
                        if (player.getKilledMobs().get("62") >= 7 && player.getKilledMobs().get("63") >= 7) {
                            player.addXP(3500);
                            updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 4);
                        }
                        break;
                    case 4:
                        //Шкуры с панд
                        break;
                    case 5:
                        if(player.getLevel() >= 31){
                            updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 6);
                            player.getKilledMobs().put("64", 0);
                        }
                        break;
                    case 6:
                        if (Objects.equals(idMob, "64")) {
                            player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 5 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                        }
                        if(player.getKilledMobs().get("64") >= 5 /* чек на дроп с оленя*/ ){
                            player.addXP(5500);
                            ThreePatch.updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 7);

                        }
                        break;
                    case 7:
                       /* if(){
                            player.addXP(5500);
                            player.completeQuest(quest);
                        }*/
                        break;
                }
                break;
            case "12":
                switch (player.getQuestsStage().get(quest)){
                    case 1:
                        break;
                    case 2:
                        p.sendMessage("§7[1/1] §6" + p.getName() + "§7§o: Кажется, он говорил, что гулял вдоль дороги в лесу, может быть удастаться найти хоть какие-то зацепки");
                        p.sendTitle("§6Задание обновлено!", "", 10, 70, 20);
                        p.sendMessage(WorldUtils.worldName() + " §6Задание " + Quest.getQuest(quest).getName() + " было обновлено!");
                        player.getQuestsStage().put(quest, 3);
                        break;
                    case 3:

                        break;
                    case 4:
                        spawnWolf.spawnWolf(p,1); //Тп шарика сделать, точнее удаление и спавн на другом месте, а так же не работает клик по нему
                        (new BukkitRunnable() {
                            public void run() {
                                if(p.getLocation().distance(player.getSpawnFakeNPC().get(25).getBukkitEntity().getLocation()) <= 5){
                                    if(!p.isSneaking()){
                                        if(player.getSpawnFakeNPC().get(25) != null){
                                            npcInitialVillage.deSpawn(p);
                                        }
                                        p.sendMessage("§7[1/1] §6" + p.getName() + "§7§o: Черт, он сбежал");
                                        p.sendTitle("§6Задание обновлено!", "", 10, 70, 20);
                                        p.sendMessage(WorldUtils.worldName() + " §6Задание " + Quest.getQuest(quest).getName() + " было обновлено!");
                                        player.getQuestsStage().put(quest, 5);
                                        try {
                                            ScriptsForThreePatch(p,"9", "0", false, 0);
                                        } catch (SQLException e) {
                                            throw new RuntimeException(e);
                                        }
                                        cancel();
                                    }else{
                                        if(talkNPC){
                                            if(idNPC == 25){
                                                p.sendMessage("§7[1/1] §6" + p.getName() + "§7§o: Иди за мной");
                                                p.sendTitle("§6Задание обновлено!", "", 10, 70, 20);
                                                p.sendMessage(WorldUtils.worldName() + " §6Задание " + Quest.getQuest(quest).getName() + " было обновлено!");
                                                player.getQuestsStage().put(quest, 7);
                                                try {
                                                    ScriptsForThreePatch(p,"9", "0", false, 0);
                                                } catch (SQLException e) {
                                                    throw new RuntimeException(e);
                                                }
                                                cancel();
                                            }
                                        }
                                    }
                                }
                            }
                        }).runTaskTimer((Plugin) azerot.getInstance(), 5L, 5L);

                        break;
                    case 5:
                        spawnWolf.spawnWolf(p,2);
                        (new BukkitRunnable() {
                            public void run() {
                                if(p.getLocation().distance(player.getSpawnFakeNPC().get(25).getBukkitEntity().getLocation()) <= 5){
                                    if(!p.isSneaking()){
                                        if(player.getSpawnFakeNPC().get(25) != null){
                                            npcInitialVillage.deSpawn(p);
                                        }
                                        p.sendMessage("§7[1/1] §6" + p.getName() + "§7§o: Черт, он опять сбежал");
                                        p.sendTitle("§6Задание обновлено!", "", 10, 70, 20);
                                        p.sendMessage(WorldUtils.worldName() + " §6Задание " + Quest.getQuest(quest).getName() + " было обновлено!");
                                        player.getQuestsStage().put(quest, 6);
                                        try {
                                            ScriptsForThreePatch(p,"9", "0", false, 0);
                                        } catch (SQLException e) {
                                            throw new RuntimeException(e);
                                        }
                                        cancel();
                                    }else{
                                        if(talkNPC){
                                            if(idNPC == 25){
                                                p.sendMessage("§7[1/1] §6" + p.getName() + "§7§o: Иди за мной");
                                                p.sendTitle("§6Задание обновлено!", "", 10, 70, 20);
                                                p.sendMessage(WorldUtils.worldName() + " §6Задание " + Quest.getQuest(quest).getName() + " было обновлено!");
                                                player.getQuestsStage().put(quest, 7);
                                                try {
                                                    ScriptsForThreePatch(p,"9", "0", false, 0);
                                                } catch (SQLException e) {
                                                    throw new RuntimeException(e);
                                                }
                                                cancel();
                                            }
                                        }
                                    }
                                }
                            }
                        }).runTaskTimer((Plugin) azerot.getInstance(), 5L, 5L);
                        break;
                    case 6:
                        spawnWolf.spawnWolf(p,3);
                        (new BukkitRunnable() {
                            public void run() {
                                        if(talkNPC){
                                            if(idNPC == 25){
                                                p.sendMessage("§7[1/1] §6" + p.getName() + "§7§o: Иди за мной");
                                                p.sendTitle("§6Задание обновлено!", "", 10, 70, 20);
                                                p.sendMessage(WorldUtils.worldName() + " §6Задание " + Quest.getQuest(quest).getName() + " было обновлено!");
                                                player.getQuestsStage().put(quest, 7);
                                                try {
                                                    ScriptsForThreePatch(p,"9", "0", false, 0);
                                                } catch (SQLException e) {
                                                    throw new RuntimeException(e);
                                                }
                                                cancel();
                                            }}

                            }
                        }).runTaskTimer((Plugin) azerot.getInstance(), 5L, 5L);
                        break;
                    case 7:
                        spawnWolf.WolfFollower(p, player.getSpawnFakeNPC().get(25));
                        break;
                    case 8:
                        player.completeQuest(quest);
                        break;
                }
                break;

            case "13":
                switch (player.getQuestsStage().get(quest)){
                    case 0:

                        break;
                    case 1:
                        updateQuestAndSendMessage(p, quest, null, 2);
                        break;
                    case 2:
                        //Нужно дойти до нпса, прыгать нельзя)
                        break;
                    case 3:
                        switch (idMob) {
                            case "69":
                                if (player.getKilledMobs().get("62") <= 7) {
                                    player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 7 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                                }
                                break;
                            case "70":
                                if (player.getKilledMobs().get("63") <= 7) {
                                    player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 7 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                                }
                                break;
                        }
                        if (player.getKilledMobs().get("69") >= 7 && player.getKilledMobs().get("70") >= 7) {
                            player.addXP(3500);
                            updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 4);
                        }
                        break;
                    case 4:
                        if(player.getLevel() >= 31){
                            updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 5);
                            player.getKilledMobs().put("71", 0);
                        }
                        break;
                    case 5:
                        if (player.getKilledMobs().get("71") <= 5) {
                                    player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 5 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                        }
                        if (player.getKilledMobs().get("71") >= 5) {
                            player.addXP(3500);
                            updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 6);
                        }
                        break;
                    case 6:
                        //Разговор с нпс
                        break;
                    case 7:
                        //Открытие клеток
                        break;
                    case 8:
                        //Нахождение сундука
                        break;

                }
                break;
            case "14":
                switch (player.getQuestsStage().get(quest)){
                    case 0:
                        player.getKilledMobs().put("66", 0);
                        break;
                    case 1:
                        if (player.getKilledMobs().get("66") <= 10) {
                            player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 10 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                        }
                        if (player.getKilledMobs().get("66") >= 10) {
                            player.addXP(3500);
                            updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 2);
                        }
                        break;
                    case 2:
                        if(player.getLevel() >= 31){
                            updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 3);
                            player.getKilledMobs().put("67", 0);
                        }
                        break;
                    case 3:
                        if (player.getKilledMobs().get("67") <= 7) {
                            player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 7 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                        }
                        if (player.getKilledMobs().get("67") >= 7) {
                            player.addXP(3500);
                            player.getKilledMobs().put("68", 0);
                            updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 4);
                        }
                        break;
                    case 4:
                        if (player.getKilledMobs().get("68") <= 1) {
                            player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 5 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                        }
                        if (player.getKilledMobs().get("68") >= 1) {
                            player.addXP(3500);
                            player.completeQuest(quest);
                       }
                        break;
                }
                break;

            case "15":
                switch (player.getQuestsStage().get(quest)){
                    case 0:
                        player.getKilledMobs().put("72", 0);
                        player.getKilledMobs().put("73", 0);
                        player.getKilledMobs().put("74", 0);
                        break;
                    case 1:
                        switch (idMob) {
                            case "72":
                                if (player.getKilledMobs().get("72") <= 10) {
                                    player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 10 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                                }
                                break;
                            case "73":
                                if (player.getKilledMobs().get("73") <= 10) {
                                    player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 10 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                                }
                                break;
                            case "74":
                                if (player.getKilledMobs().get("74") <= 10) {
                                    player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 10 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                                }
                                break;
                        }
                        if (player.getKilledMobs().get("72") >= 10 && player.getKilledMobs().get("73") >= 10 && player.getKilledMobs().get("74") >= 10) {
                            player.addXP(10000);
                            updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 2);
                        }
                        break;
                    case 2:
                        //Диалог
                        break;
                    case 3:
                        //Сбор ключа
                        break;
                    case 4:
                        //Куда ключ вставлять
                        break;
                }
                break;

            case "16":
                switch (player.getQuestsStage().get(quest)){
                    case 0:
                        player.getKilledMobs().put("76", 0);
                        player.getKilledMobs().put("77", 0);
                        break;
                    case 1:
                        switch (idMob) {
                            case "76":
                                if (player.getKilledMobs().get("76") <= 10) {
                                    player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 10 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                                }
                                break;
                            case "77":
                                if (player.getKilledMobs().get("77") <= 10) {
                                    player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 10 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                                }
                                break;
                        }
                        if (player.getKilledMobs().get("76") >= 10 && player.getKilledMobs().get("77") >= 10) {
                            player.addXP(10000);
                            player.getKilledMobs().put("78", 0);
                            player.getKilledMobs().put("79", 0);
                            updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 2);
                        }
                        break;
                    case 2:
                        switch (idMob) {
                            case "78":
                                if (player.getKilledMobs().get("78") <= 10) {
                                    player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 10 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                                }
                                break;
                            case "79":
                                if (player.getKilledMobs().get("79") <= 10) {
                                    player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 10 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                                }
                                break;
                        }
                        if (player.getKilledMobs().get("78") >= 10 && player.getKilledMobs().get("79") >= 10) {
                            player.addXP(10000);
                            updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 3);
                        }
                        break;
                    case 3:
                        //Сбор каких-то ресурсов
                        break;
                    case 4:
                        if(player.getLevel() >= 34){
                            updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 5);
                            player.getKilledMobs().put("80", 0);
                            player.getKilledMobs().put("81", 0);
                        }
                        break;
                    case 5:
                        switch (idMob) {
                            case "80":
                                if (player.getKilledMobs().get("80") <= 7) {
                                    player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 7 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                                }
                                break;
                            case "81":
                                if (player.getKilledMobs().get("77") <= 7) {
                                    player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 7 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                                }
                                break;
                        }
                        if (player.getKilledMobs().get("80") >= 7 && player.getKilledMobs().get("81") >= 7) {
                            player.addXP(10000);
                            player.getKilledMobs().put("82", 0);
                            updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!", 6);
                        }
                        break;
                    case 6:
                        if (player.getKilledMobs().get("82") <= 7) {
                            player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 7 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                        }
                        if (player.getKilledMobs().get("82") >= 7) {
                            player.addXP(3500);
                            player.getKilledMobs().put("83", 0);
                            updateQuestAndSendMessage(p, quest, WorldUtils.worldName() + " §7§oЗадание §6" + Objects.requireNonNull(Quest.getQuest(quest)).getName() + " §7§oбыло обновлено!",7 );
                        }
                        break;
                    case 7:
                        if (player.getKilledMobs().get("83") <= 1) {
                            player.toPlayer().sendMessage(WorldUtils.worldName() + " §7Убит " + MobSystem.getName(Integer.parseInt(idMob)) + " §6" + player.getMob(idMob) + "§7/§6" + 1 + " §7по заданию §e" + Quest.getQuest(quest).getName() + " #" + Quest.getQuest(quest).getId());
                        }
                        if (player.getKilledMobs().get("83") >= 1) {
                            player.addXP(3500);
                            player.completeQuest(quest);
                            Byte i = Byte.valueOf(String.valueOf(3500));

                        }
                        break;
                }
                break;
        }
    }

    public static void updateQuestAndSendMessage(Player p, String quest, String message, int newStage) {
        rpgplayer player = rpgplayer.getRPGPlayer(p);
        if(message != null)
            p.sendMessage(message);
        p.sendTitle("§6Задание обновлено!", "", 10, 70, 20);
        p.sendMessage(WorldUtils.worldName() + " §6Задание " + Quest.getQuest(quest).getName() + " было обновлено!");
        player.getQuestsStage().put(quest, newStage);
    }
}
