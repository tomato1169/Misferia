package azerot.azerot.Quest;

import azerot.azerot.RPGItem;
import azerot.azerot.RPGItemContainer;
import azerot.azerot.WorldUtils;
import azerot.azerot.mobs.MobSystem;
import azerot.azerot.rpgplayer;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class questStageForScoreBoard {

    public static List<String> questStageBoard(Player p, String quest) throws SQLException {
        rpgplayer player = rpgplayer.getRPGPlayer(p);
        List<String> quests = new ArrayList<>();
        quests.add("");
        int Stage = player.getQuestsStage().get(quest);
        switch (quest){
            case "0":
                return quests;
            case "1":
                if (player.getStatusQuest("1") == 1) {
                    switch (Stage) {
                        case 1:
                            quests.add("§7Задание: §2Начало #1");
                            quests.add("  §7Необходимо убить:");
                            quests.add("    §7-§fРазбойник: §f" + player.getMob("1") + "/" + 5);
                            quests.add("    §7-§fОдноглазый Разбойник: §f" + player.getMob("2") + "/" + 5);
                            quests.add("    §7-§fРазбойник-стрелок: §f" + player.getMob("3") + "/" + 5);
                            break;
                        case 2:
                            quests.add("§7Задание: §2Начало #1");
                            quests.add("§7Задача:");
                            quests.add("    §7Поговорите с местным жителем");
                            break;
                        case 3:
                            quests.add("§7Задание: §2Начало #1");
                            quests.add("§7Необходимо сдать:");
                            quests.add("    §6Управляющий");
                            quests.add("    §7Координаты: §f-4033 68 1976");
                            break;
                        case 4:
                            quests.add("§7Задание: §2Начало #1");
                            quests.add("  §7Задача: ");
                            quests.add("    §7Обыскать дом");
                            break;
                        case 6:
                            quests.add("§7Задание: §2Начало #1");
                            quests.add("§7Необходимо убить:");
                            quests.add("    §7-§fПаучок: " + player.getMob("4") + "/" + 5);
                            break;
                        case 7:
                            quests.add("§7Задание: §2Начало #1");
                            quests.add("§7Необходимо убить:");
                            quests.add("    §7-§fБольшой паук: " + player.getMob("5") + "/" + 5);
                            break;
                        case 8:
                            quests.add("§7Задание: §2Начало #1");
                            quests.add("§7Необходимо убить:");
                            quests.add("    §7-§4Яростная лупоглазка: " + player.getMob("6") + "/" + 1);
                            break;
                        case 9:
                            quests.add("§7Задание: §2Начало #1");
                            quests.add("§7Необходимо сдать:");
                            quests.add("    §6Управляющий");
                            quests.add("    §7Координаты: §f-4033 68 1976");
                            break;
                        case 11:
                            quests.add("§7Задание: §2Начало #1");
                            quests.add("    §7Требования: 7 уровень!");
                            break;
                        case 12:
                            quests.add("§7Задание: §2Начало #1");
                            quests.add("§7Задача:");
                            quests.add("    §7Поговорите с управляющим");
                            break;
                        case 13:
                            quests.add("§7Задание: §2Начало #1");
                            quests.add("§7Необходимо убить:");
                            quests.add("    §7-§fСкалистый воин: §f" + player.getMob("7") + "/" + 7);
                            break;
                        case 14:
                            quests.add("§7Задание: §2Начало #1");
                            quests.add("    §7Требования: 8 уровень!");
                            break;
                        case 15:
                            quests.add("§7Задание: §2Начало #1");
                            quests.add("§7Необходимо убить:");
                            quests.add("    §7-§fКаменная оболочка: §f" + player.getMob("8") + "/" + 7);
                            break;
                        case 16:
                            quests.add("§7Задание: §2Начало #1");
                            quests.add("    §7Требования: 9 уровень!");
                            break;
                        case 17:
                            quests.add("§7Задание: §2Начало #1");
                            quests.add("§7Необходимо убить:");
                            quests.add("    §7-§fБронзовое воплощение: §f" + player.getMob("9") + "/" + 7);
                            break;
                        case 18:
                            quests.add("§7Задание: §2Начало #1");
                            quests.add("  §7Необходимо собрать:");
                            quests.add("    §7-§2Чарованый слиток: §f" + WorldUtils.checkItems(player,57,0) + "/" + 7);
                            break;
                        case 19:
                            quests.add("§7Задание: §2Начало #1");
                            quests.add("    §7Требования: 10 уровень");
                            break;
                        case 20:
                            quests.add("§7Задание: §2Начало #1");
                            quests.add("§7Задача:");
                            quests.add("    §7Поговорите с управляющим");
                            break;

                    }
                }
                break;
            case "2":
                if (player.getStatusQuest("2") == 1) {
                    Quest q = Quest.getQuest(quest);
                    switch (Stage){
                        case 1:
                            quests.add("§7Задание: §2Остров I");
                            quests.add("  §7Необходимо убить:");
                            quests.add("    §7-§fПолый: §f" + player.getMob("10") + "/" + 10);
                            break;
                        case 2:
                            quests.add("§7Задание: §2Остров I");
                            quests.add("§7Задача:");
                            quests.add("    §7Поговорите с кем-то в лагере");
                            break;
                        case 3:
                            quests.add("§7Задание: §2Остров I");
                            quests.add("  §7Необходимо убить:");
                            quests.add("    §7-§fПолый солдат: §f" + player.getMob("11") + "/" + 7);
                            quests.add("    §7-§fПолый лучник: §f" + player.getMob("12") + "/" + 7);
                            quests.add("");
                            break;
                        case 4:
                            quests.add("§7Задание: §2Остров I");
                            quests.add("§7Требования: 12 уровень");
                            break;
                        case 5:
                            quests.add("§7Задание: §2Остров I");
                            quests.add("  §7Необходимо убить:");
                            quests.add("    §7-§fМертвый маг: §f" + player.getMob("13") + "/" + 7);
                            quests.add("    §7-§fИсхудалый пес: §f" + player.getMob("15") + "/" + 7);
                            quests.add("");
                            break;
                        case 6:
                            quests.add("§7Задание: §2Остров I");
                            quests.add("§7Требованиe: 13 уровень");
                            break;
                        case 7:
                            quests.add("§7Задание: §2Остров I");
                            quests.add("  §7Необходимо убить:");
                            quests.add("    §7-§fМертвец: " + player.getMob("16") + "/" + 10);
                            break;
                        case 8:
                            quests.add("§7Задание: §2Остров I");
                            quests.add("§4Требованиe: 14 уровень");
                            break;
                        case 9:
                            quests.add("§7Задание: §2Остров I");
                            quests.add("  §7Необходимо убить:");
                            quests.add("    §7-§4Черный рыцарь: §f" + player.getMob("14") + "/" + 7);
                            quests.add("    §7-§4§lИскуситель: §f" + player.getMob("19") + "/" + 1);
                            break;
                        case 10:
                            quests.add("§7Задание: §2Остров I");
                            quests.add("  §7Необходимо собрать:");
                            quests.add("    §7-" + RPGItemContainer.getRPGItemContainer(54).getName() + "§7: " + WorldUtils.checkItems(player,54,0) + "/" + 5);
                            quests.add("    §7-" + RPGItemContainer.getRPGItemContainer(82).getName() + "§7: " + WorldUtils.checkItems(player,82,0) + "/" + 5);
                            quests.add("    §7-" + RPGItemContainer.getRPGItemContainer(79).getName() + "§7: " + WorldUtils.checkItems(player,79,0) + "/" + 1);
                            break;
                        case 11:
                            quests.add("§7Задание: §2Остров I");
                            quests.add("§7Задача:");
                            quests.add("    §7Поговорите с Ванессой");
                            break;
                        case 12:
                            quests.add("§7Задание: §2Остров I");
                            quests.add("§4Требованиe: 15 уровень");
                            break;

                    }

                }
                break;
                case "3":
                if (player.getStatusQuest("3") == 1) {
                    switch (Stage){
                        case 1:
                            quests.add("§7Задание: §2Исследования");
                            quests.add("  §7Необходимо убить:");
                            quests.add("    §7-§fПорабощенный эльф: §f" + player.getMob("17") + "/" + 7);
                            quests.add("    §7-§fПаразит: §f" + player.getMob("18") + "/" + 7);
                            quests.add("");
                            break;
                        case 2:
                            quests.add("§7Задание: §2Исследования");
                            quests.add("§4Требования: 14 уровень");
                            break;
                        case 3:
                            quests.add("§7Задание: §2Исследования");
                            quests.add("  §7Необходимо собрать:");
                            quests.add("    §7-Внутренности паразита: §f" + WorldUtils.checkItems(player,80,0) + "/" + 9);
                            break;
                    }

                }
                break;
            case "4":
                if (player.getStatusQuest("4") == 1) {
                    switch (Stage){
                        case 1:
                            quests.add("§7Задание: §2Остров II");
                            quests.add("§7Задача:");
                            quests.add("    §7Найдите Анри");
                            break;
                        case 2:
                            quests.add("§7Задание: §2Остров II");
                            quests.add("  §7Необходимо убить:");
                            quests.add("    §7-§fПридворный маг: §f" + player.getMob("20") + "/" + 7);
                            quests.add("    §7-§fВосставший гвардеец: §f" + player.getMob("21") + "/" + 7);
                            quests.add("");
                            break;
                        case 3:
                            quests.add("§7Задание: §2Остров II");
                            quests.add("    §7Требование: 16 уровень");
                            break;
                        case 4:
                            quests.add("§7Задание: §2Остров II");
                            quests.add("  §7Необходимо убить:");
                            quests.add("    §7-§fКровосися: §f" + player.getMob("22") + "/" + 7);
                            quests.add("    §7-§fМертвый рыцарь: §f" + player.getMob("23") + "/" + 7);
                            quests.add("");
                            break;
                        case 5:
                            quests.add("§7Задание: §2Остров II");
                            quests.add("    §7Требование: 17 уровень");
                            break;
                        case 6:
                            quests.add("§7Задание: §2Остров II");
                            quests.add("§7Задача:");
                            quests.add("    §7Поговорите с Анри");
                            break;
                        case 7:
                            quests.add("§7Задание: §2Остров II");
                            quests.add("  §7Необходимо убить:");
                            quests.add("    §7-§fШахтер: §f" + player.getMob("24") + "/" + 10);
                            break;
                        case 8:
                            quests.add("§7Задание: §2Остров II");
                            quests.add("  §7Необходимо убить:");
                            quests.add("    §7-§fПризрак: §f" + player.getMob("25") + "/" + 7);
                            quests.add("");
                            break;
                        case 10:
                            quests.add("§7Задание: §2Остров II");
                            quests.add("§7Задача:");
                            quests.add("    §7Вернитесь к Анри");
                            break;
                        case 11:
                            quests.add("§7Задание: §2Остров II");
                            quests.add("    §7Требования: 18 уровень");
                            break;
                        case 12:
                            quests.add("§7Задание: §2Остров II");
                            quests.add("  §7Необходимо убить:");
                            quests.add("    §7-§fПризрак: §f" + player.getMob("25") + "/" + 7);
                            quests.add("");
                            break;
                        case 13:
                            quests.add("§7Задание: §2Остров II");
                            quests.add("    §7Требования: 19 уровень");
                            break;
                        case 14:
                            quests.add("§7Задание: §2Остров II");
                            quests.add("  §7Необходимо убить:");
                            quests.add("    §7-§cФантомный призрак: §f" + player.getMob("26") + "/" + 7);
                            break;
                        case 15:
                            quests.add("§7Задание: §2Остров II");
                            quests.add("§7Задача:");
                            quests.add("    §7Вернитесь к Анри");
                            break;
                        case 16:
                            quests.add("§7Задание: §2Остров II");
                            quests.add("  §7Необходимо убить:");
                            quests.add("    §7-§4Плеяда душ: §f" + player.getMob("27") + "/" + 1);
                            break;
                    }

                }
                break;
            case "5":
                if (player.getStatusQuest("5") == 1) {
                    switch (Stage){
                        case 1:
                            quests.add("§7Задание: §2Другая сторона #5");
                            quests.add("§7Задача:");
                            quests.add("    §7Перейдите на остров");
                            break;
                        case 2:
                            quests.add("§7Задание: §2Другая сторона #5");
                            quests.add("§7Задача:");
                            quests.add("    §7Поговорите с девушкой");
                            break;
                        case 3:
                            quests.add("§7Задание: §2Другая сторона #5");
                            quests.add("§7Необходимо убить:");
                            quests.add("    §7-Оживший эксперимент: §f" + player.getMob("28") + "/" + 3);
                            quests.add("    §7-Мутировавший лучник: §f" + player.getMob("29") + "/" + 3);
                            break;
                        case 4:
                            quests.add("§7Задание: §2Другая сторона #5");
                            quests.add("§7Задача:");
                            quests.add("    §7Поговорите с Элаей");
                            break;
                        case 5:
                            quests.add("§7Задание: §2Другая сторона #5");
                            quests.add("§7Задача:");
                            quests.add("    §7Поговорите с Элаей");
                            break;
                        case 6:
                            quests.add("§7Задание: §2Другая сторона #5");
                            quests.add("§7Задача:");
                            quests.add("    §7Поговорите с Корейем");
                            break;
                        case 7:
                            quests.add("§7Задание: §2Другая сторона #5");
                            quests.add("§7Задача:");
                            quests.add("    §7Найдите дом Алхимика");
                            break;
                        case 8:
                            quests.add("§7Задание: §2Другая сторона #5");
                            quests.add("§7Задача:");
                            quests.add("    §7Покиньте дом");
                            break;
                        case 9:
                            quests.add("§7Задание: §2Другая сторона #5");
                            quests.add("§7Необходимо убить:");
                            quests.add("    §7-Оживший эксперимент: §f" + player.getMob("28") + "/" + 7);
                            break;
                        case 10:
                            quests.add("§7Задание: §2Другая сторона #5");
                            quests.add("§7Необходимо убить:");
                            quests.add("    §7-Мутировавший лучник: §f" + player.getMob("29") + "/" + 7);
                            break;
                        case 11:
                            quests.add("§7Задание: §2Другая сторона #5");
                            quests.add("§7Необходимо убить:");
                            quests.add("    §7-Неудачный эксперимент: §f" + player.getMob("30") + "/" + 7);
                            break;
                        case 12:
                            quests.add("§7Задание: §2Другая сторона #5");
                            quests.add("§7Необходимо убить:");
                            quests.add("    §7-Измененная душа: §f" + player.getMob("31") + "/" + 7);
                            break;
                        case 13:
                            quests.add("§7Задание: §2Другая сторона #5");
                            quests.add("§7Задача:");
                            quests.add("    §7Поговорите с Корейем");
                            break;
                        case 14:
                            quests.add("§7Задание: §2Другая сторона #5");
                            quests.add("§7Задача:");
                            quests.add("    §7Поговорите с Алхимиком");
                            break;
                        case 15:
                            quests.add("§7Задание: §2Другая сторона #5");
                            quests.add("    §7Найдите сундук");
                            break;
                        case 16:
                            quests.add("§7Задание: §2Другая сторона #5");
                            quests.add("    §7Поговорите с Алхимиком");
                            break;

                    }

                }
                break;
            case "6":
                if (player.getStatusQuest("6") == 1) {
                    switch (Stage){
                        case 1:
                            quests.add("§7Задание: §2Выход?");
                            quests.add("§7Необходимо убить:");
                            quests.add("    §7-Лучник рассвета: §f" + player.getMob("32") + "/" + 10);
                            quests.add("    §7-Копейщик рассвета: §f" + player.getMob("33") + "/" + 10);
                            break;
                        case 2:
                            quests.add("§7Задание: §2Выход?");
                            quests.add("§7Необходимо убить:");
                            quests.add("    §7-Маг рассвета: §f" + player.getMob("35") + "/" + 10);
                            break;
                        case 3:
                            quests.add("§7Задание: §2Выход?");
                            quests.add("    §7Требования: 22 уровень!");
                            break;
                        case 4:
                            quests.add("§7Задание: §2Выход?");
                            quests.add("§7Необходимо убить:");
                            quests.add("    §7-§cСтраж рассвета: §f" + player.getMob("34") + "/" + 7);
                            break;
                        case 5:
                            quests.add("§7Задание: §2Выход?");
                            quests.add("  §7Необходимо собрать:");
                            quests.add("    §7-§2Кристалл солнца: §f" + WorldUtils.checkItems(player,148,0) + "/" + 15);
                            break;
                        case 6:
                            quests.add("§7Задание: §2Выход?");
                            quests.add("    §7Требования: 23 уровень!");
                            break;
                        case 7:
                            quests.add("§7Задание: §2Выход?");
                            quests.add("§7Необходимо убить:");
                            quests.add("    §7-Смолянистый лучник: §f" + player.getMob("37") + "/" + 10);
                            quests.add("    §7-Ходячая Трансмутация: §f" + player.getMob("38") + "/" + 10);
                            break;
                        case 8:
                            quests.add("§7Задание: §2Выход?");
                            quests.add("    §7Требования: 24 уровень!");
                            break;
                        case 9:
                            quests.add("§7Задание: §2Выход?");
                            quests.add("§7Необходимо убить:");
                            quests.add("    §7-Смолянистый маг: §f" + player.getMob("39") + "/" + 10);
                            break;
                        case 10:
                            quests.add("§7Задание: §2Выход?");
                            quests.add("§7Необходимо убить:");
                            quests.add("    §7-Экзекутор душ: §f" + player.getMob("41") + "/" + 7);
                            break;
                        case 11:
                            quests.add("§7Задание: §2Выход?");
                            quests.add("    §7Требования: 25 уровень!");
                            break;
                        case 12:
                            quests.add("§7Задание: §2Выход?");
                            quests.add("§7Необходимо убить:");
                            quests.add("    §7-§cСмолянистый страж: §f" + player.getMob("40") + "/" + 7);
                            break;
                        case 13:
                            quests.add("§7Задание: §2Выход?");
                            quests.add("  §7Необходимо собрать:");
                            quests.add("    §7-§2Кристалл грязи: §f" + WorldUtils.checkItems(player,149,0) + "/" + 15);
                            break;
                        case 14:
                            quests.add("§7Задание: §2Выход?");
                            quests.add("    §7Требования: 26 уровень!");
                            break;
                        case 15:
                            quests.add("§7Задание: §2Выход?");
                            quests.add("§7Необходимо убить:");
                            quests.add("    §7-Сапфировый мученик: §f" + player.getMob("43") + "/" + 15);
                            quests.add("    §7-Сапфировый лучник: §f" + player.getMob("46") + "/" + 15);
                            break;
                        case 16:
                            quests.add("§7Задание: §2Выход?");
                            quests.add("§7Необходимо убить:");
                            quests.add("    §7-Сапфировый голем: §f" + player.getMob("44") + "/" + 10);
                            break;
                        case 17:
                            quests.add("§7Задание: §2Выход?");
                            quests.add("§7Необходимо убить:");
                            quests.add("    §7-Сапфировая душа: §f" + player.getMob("45") + "/" + 10);
                            break;
                        case 18:
                            quests.add("§7Задание: §2Выход?");
                            quests.add("    §7Требования: 27 уровень!");
                            break;
                        case 19:
                            quests.add("§7Задание: §2Выход?");
                            quests.add("§7Необходимо убить:");
                            quests.add("    §7-§cСапфировый зверь: §f" + player.getMob("47") + "/" + 7);
                            quests.add("    §7-§cСапфировый рыцарь: §f" + player.getMob("48") + "/" + 7);
                            break;
                        case 20:
                            quests.add("§7Задание: §2Выход?");
                            quests.add("  §7Необходимо собрать:");
                            quests.add("    §7-§2Кристалл ночи: §f" + WorldUtils.checkItems(player,150,0) + "/" + 32);
                            break;
                        case 21:
                            quests.add("§7Задание: §2Выход?");
                            quests.add("    §7Требования: 28 уровень!");
                            break;
                        case 22:
                            quests.add("§7Задание: §2Выход?");
                            quests.add("§7Необходимо убить:");
                            quests.add("    §7-Багровый страж: §f" + player.getMob("50") + "/" + 10);
                            quests.add("    §7-Багровая сущность: §f" + player.getMob("51") + "/" + 10);
                            break;
                        case 23:
                            quests.add("§7Задание: §2Выход?");
                            quests.add("§7Необходимо убить:");
                            quests.add("    §7-Багровый лучник: §f" + player.getMob("53") + "/" + 10);
                            break;
                        case 24:
                            quests.add("§7Задание: §2Выход?");
                            quests.add("§7Необходимо убить:");
                            quests.add("    §7-Маг крови: §f" + player.getMob("55") + "/" + 10);
                            quests.add("    §7-Паразит крови: §f" + player.getMob("57") + "/" + 10);
                            break;
                        case 25:
                            quests.add("§7Задание: §2Выход?");
                            quests.add("    §7Требования: 29 уровень!");
                            break;
                        case 26:
                            quests.add("§7Задание: §2Выход?");
                            quests.add("§7Необходимо убить:");
                            quests.add("    §7-§4Хранитель могил: §f" + player.getMob("52") + "/" + 7);
                            quests.add("    §7-§4Алый жнец: §f" + player.getMob("54") + "/" + 7);
                            quests.add("    §7-§4Алый паладин: §f" + player.getMob("58") + "/" + 7);
                            break;
                        case 27:
                            quests.add("§7Задание: §2Выход?");
                            quests.add("§7Необходимо убить:");
                            quests.add("    §7-§4Страж древа: §f" + player.getMob("59") + "/" + 10);
                            break;
                        case 28:
                            quests.add("§7Задание: §2Выход?");
                            quests.add("  §7Необходимо собрать:");
                            quests.add("    §7-§2Кристалл крови: §f" + WorldUtils.checkItems(player,208,0) + "/" + 32);
                            break;
                        case 29:
                            quests.add("§7Задание: §2Выход?");
                            quests.add("    §7Вернитесь к Алхимику");
                            break;
                        case 31:
                            quests.add("§7Задание: §2Выход?");
                            quests.add("    §7Направляйтесь к большому дереву");
                            break;
                        case 32:
                            quests.add("§7Задание: §2Выход?");
                            quests.add("§7Необходимо убить:");
                            quests.add("    §7-§l§4FАлхимик: §f" + player.getMob("59") + "/" + 1);
                            break;
                    }

                }
                break;
            case "8":
                if (player.getStatusQuest("8") == 1) {
                    switch (player.getQuestsStage().get("8")){
                        case 1:
                            quests.add("§7Задание: §2Сбор цветов");
                            quests.add("§7Необходимо собрать:");
                            quests.add("    §7-" + RPGItemContainer.getRPGItemContainer(231).getName() + "§7: " + WorldUtils.checkItems(player,231,0) + "/" + 2);
                            break;
                        case 2:
                            quests.add("§7Задание: §2Сбор цветов");
                            quests.add("    §7Требования: 25 уровень!");
                            break;
                        case 3:
                            quests.add("§7Задание: §2Сбор цветов");
                            quests.add("§7Необходимо собрать:");
                            quests.add("    §7-" + RPGItemContainer.getRPGItemContainer(232).getName() + "§7: " + WorldUtils.checkItems(player,232,0) + "/" + 2);
                            break;
                        case 4:
                            quests.add("§7Задание: §2Сбор цветов");
                            quests.add("    §7Требования: 27 уровень!");
                            break;
                        case 5:
                            quests.add("§7Задание: §2Сбор цветов");
                            quests.add("§7Необходимо собрать:");
                            quests.add("    §7-" + RPGItemContainer.getRPGItemContainer(233).getName() + "§7: " + WorldUtils.checkItems(player,233,0) + "/" + 2);
                            break;
                        case 6:
                            quests.add("§7Задание: §2Сбор цветов");
                            quests.add("    §7Требования: 29 уровень!");
                            break;
                        case 7:
                            quests.add("§7Задание: §2Сбор цветов");
                            quests.add("§7Необходимо собрать:");
                            quests.add("    §7-" + RPGItemContainer.getRPGItemContainer(234).getName() + "§7: " + WorldUtils.checkItems(player,234,0) + "/" + 2);
                            break;
                        case 8:
                            quests.add("§7Задание: §2Сбор цветов");
                            quests.add("§7Необходимо собрать:");
                            quests.add("    §7-" + RPGItemContainer.getRPGItemContainer(235).getName() + "§7: " + WorldUtils.checkItems(player,235,0) + "/" + 1);
                            break;
                        case 9:
                            quests.add("§7Задание: §2Сбор цветов");
                            quests.add("§7Задача:");
                            quests.add("    §7Поговорите с Элайя");
                            break;

                    }
                }
                break;
            case "9":
                if (player.getStatusQuest("9") == 1) {
                    switch (player.getQuestsStage().get("9")) {
                        case 1:
                            quests.add("§7Задание: §2Поиск");
                            quests.add("§7Задача:");
                            quests.add("    §7Выйдите из деревни");
                            break;
                        case 2:
                            break;
                        case 3:
                            quests.add("§7Задание: §2Поиск");
                            quests.add("§7Задача:");
                            quests.add("    §7Найдите зацепки в лесу");
                            break;
                        case 4:
                            quests.add("§7Задание: §2Поиск");
                            quests.add("§7Задача:");
                            quests.add("    §7Найдите пса");
                            break;
                        case 5:
                            quests.add("§7Задание: §2Поиск");
                            quests.add("§7Задача:");
                            quests.add("    §7Найдите пса");
                            break;
                        case 6:
                            quests.add("§7Задание: §2Поиск");
                            quests.add("§7Задача:");
                            quests.add("    §7Найдите пса");
                            break;
                        case 7:
                            quests.add("§7Задание: §2Поиск");
                            quests.add("§7Задача:");
                            quests.add("    §7Отведите пса");
                            break;
                        case 8:
                            break;

                    }
                }
                break;
        }

        return quests;
    }
}
