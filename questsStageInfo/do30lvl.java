package azerot.azerot.questsStageInfo;

import azerot.azerot.RPGItemContainer;
import azerot.azerot.WorldUtils;
import azerot.azerot.mobs.RPGMobsContainer;
import azerot.azerot.rpgplayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class do30lvl {

    public static ItemStack quest1(Player p, Integer stage) {
        ItemStack item = new ItemStack(Material.BOOK_AND_QUILL, 1);
        rpgplayer pl = rpgplayer.getRPGPlayer(p);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        switch (stage){
            case 1:
                meta.setDisplayName("§bСтадия №1");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(1).getName() + ": §70/" + 5);
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(2).getName() + ": §70/" + 5);
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(3).getName() + ": §70/" + 5);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                lore.add("§7- §650 §7опыта" );
                break;
            case 2:
                meta.setDisplayName("§bСтадия №2");
                lore.add("§7Задача:");
                lore.add("  §7-Поговорите с местным жителем");
                break;
            case 3:
                meta.setDisplayName("§bСтадия №3");
                lore.add("§7Этот старик советует найти управляющего соседней деревни.");
                lore.add("§7Говорит, тот может помочь мне");
                lore.add("§7Ну что ж, придется отправляться в путь!");
                lore.add("");
                lore.add("§eНеобходимо сдать: §e§lУправляющий");
                lore.add("  §eКоординаты: §e§l-4033 68 1976");
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                lore.add("§7- §650 §7опыта" );
                break;
            case 4:
                meta.setDisplayName("§bСтадия №4");
                lore.add("§7На пути к деревне наткнулся на заброшенный дом");
                lore.add("§7Может, там что-нибудь полезное найдется?");
                lore.add("");
                lore.add("§7Задача:");
                lore.add("  §7-Обыскать дом");
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                lore.add("§7- §675 §7опыта" );
                break;
            case 5:
                meta.setDisplayName("§bСтадия №5");
                break;
            case 6:
                meta.setDisplayName("§bСтадия №6");
                lore.add("§7Ну вот, пауки! Ненавижу этих тварей!");
                lore.add("§7Придется пробиваться через них, чтобы добраться до управляющего.");
                lore.add("");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(4).getName() + ": §70/" + 5);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                lore.add("§7- §680 §7опыта" );
                break;
            case 7:
                meta.setDisplayName("§bСтадия №7");
                lore.add("§7Ну вот, пауки! Ненавижу этих тварей!");
                lore.add("§7Придется пробиваться через них, чтобы добраться до управляющего.");
                lore.add("");
                lore.add("§4§lНеобходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(5).getName() + ": §70/" + 5);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                lore.add("§7- §6120 §7опыта" );
                break;
            case 8:
                meta.setDisplayName("§bСтадия №8");
                lore.add("§7Ну вот, пауки! Ненавижу этих тварей!");
                lore.add("§7Придется пробиваться через них, чтобы добраться до управляющего.");
                lore.add("");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(6).getName() + ": §70/" + 1);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                lore.add("§7- §6150 §7опыта" );
                break;
            case 9:
                meta.setDisplayName("§bСтадия №9");
                lore.add("§eНеобходимо сдать: §e§lУправляющий");
                lore.add("  §eКоординаты: §e§l-4033 68 1976");
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                lore.add("§7- §675 §7опыта" );
                break;
            case 10:
                meta.setDisplayName("§bСтадия №10");
                break;
            case 11:
                meta.setDisplayName("§bСтадия №11");
                break;
            case 12:
                meta.setDisplayName("§bСтадия №12");
                break;
            case 13:
                meta.setDisplayName("§bСтадия №13");
                lore.add("§7Управляющий просит разобраться с каменными существами, которые нападают на деревню.");
                lore.add("§7Говорит, что они несут угрозу и просит собрать с них чарованные слитки.");
                lore.add("§7Ну что ж, придется поработать");
                lore.add("");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(7).getName() + ": §70/" + 7);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                lore.add("§7- §6150 §7опыта" );
                break;
            case 14:
                meta.setDisplayName("§bСтадия №14");
                break;
            case 15:
                meta.setDisplayName("§bСтадия №15");
                lore.add("§7Управляющий просит разобраться с каменными существами, которые нападают на деревню.");
                lore.add("§7Говорит, что они несут угрозу и просит собрать с них чарованные слитки.");
                lore.add("§7Ну что ж, придется поработать");
                lore.add("");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(8).getName() + " §70:" + 7);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                lore.add("§7- §6150 §7опыта" );
                break;
            case 16:
                meta.setDisplayName("§bСтадия №16");
                break;
            case 17:
                meta.setDisplayName("§bСтадия №17");
                lore.add("§7Управляющий просит разобраться с каменными существами, которые нападают на деревню.");
                lore.add("§7Говорит, что они несут угрозу и просит собрать с них чарованные слитки.");
                lore.add("§7Ну что ж, придется поработать");
                lore.add("");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(9).getName() + ": §70/" + 7);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                lore.add("§7- §6150 §7опыта" );
                break;
            case 18:
                meta.setDisplayName("§bСтадия №18");
                lore.add("§7Я победил всех! Теперь надо собрать чарованные слитки, как и просил управляющий");
                lore.add("");
                lore.add("§7Необходимо собрать:");
                lore.add("  §7-" + RPGItemContainer.getRPGItemContainer(57).getName() + " " + WorldUtils.checkItems(pl,57,0) + "/" + 7);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                lore.add("§7- §6250 §7опыта" );
                break;
            case 19:
                meta.setDisplayName("§bСтадия №19");
                break;
            case 20:
                meta.setDisplayName("§bСтадия №20");
                lore.add("§eСдача: §e§lУправляющий");
                break;
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack quest2(Player p, Integer stage) {
        ItemStack item = new ItemStack(Material.BOOK_AND_QUILL, 1);
        rpgplayer pl = rpgplayer.getRPGPlayer(p);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        switch (stage){
            case 1:
                meta.setDisplayName("§bСтадия №1");
                lore.add("§7Не могу позволить этим тварям уничтожить этот лагерь!");
                lore.add("§7Я должен защитить этих людей, ведь они единственные, кто может помочь мне");
                lore.add("");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(10).getName() + ": §70/" + 10);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                lore.add("§7- §61000 §7опыта" );
                break;
            case 2:
                meta.setDisplayName("§bСтадия №2");
                lore.add("§7Задача:");
                lore.add("  §7-Поговорите с кем-то в лагере");
                lore.add("");
                lore.add("§eНеобходимо сдать: §e§lВанесса");
                lore.add("  §eКоординаты: §e§l-3877 71 2763");
                lore.add("");
                lore.add("§eСдача: §e§lДиалог");
                break;
            case 3:
                meta.setDisplayName("§bСтадия №3");
                lore.add("§7Очистить остров от нежити и найти ингредиенты для зелья…");
                lore.add("§7Не так уж и плохо.");
                lore.add("§7Хотя я бы предпочел разобраться со своей памятью как можно скорее");
                lore.add("");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(11).getName() + ": §70/" + 7);
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(12).getName() + ": §70/" + 7);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                lore.add("§7- §6500 §7опыта" );
                break;
            case 4:
                meta.setDisplayName("§bСтадия №4");
                lore.add("§4Требование: 12 Уровень");
                break;
            case 5:
                meta.setDisplayName("§bСтадия №5");
                lore.add("§7Очистить остров от нежити и найти ингредиенты для зелья…");
                lore.add("§7Не так уж и плохо.");
                lore.add("§7Хотя я бы предпочел разобраться со своей памятью как можно скорее");
                lore.add("");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(13).getName() + ": §70/" + 7);
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(15).getName() + ": §70/" + 7);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                lore.add("§7- §6600 §7опыта" );
                break;
            case 6:
                meta.setDisplayName("§bСтадия №6");
                lore.add("§4Требование: 13 Уровень");
                break;
            case 7:
                meta.setDisplayName("§bСтадия №7");
                lore.add("§7Очистить остров от нежити и найти ингредиенты для зелья…");
                lore.add("§7Не так уж и плохо.");
                lore.add("§7Хотя я бы предпочел разобраться со своей памятью как можно скорее");
                lore.add("");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(16).getName() + ": §70/" + 10);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                lore.add("§7- §6600 §7опыта" );
                break;
            case 8:
                meta.setDisplayName("§bСтадия №8");
                lore.add("§4Требование: 14 Уровень");
                break;
            case 9:
                meta.setDisplayName("§bСтадия №9");
                lore.add("§7Очистить остров от нежити и найти ингредиенты для зелья…");
                lore.add("§7Не так уж и плохо.");
                lore.add("§7Хотя я бы предпочел разобраться со своей памятью как можно скорее");
                lore.add("");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(14).getName() + ": §70/" + 7);
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(19).getName() + ": §70/" + 1);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                lore.add("§7- §61500 §7опыта" );
                break;
            case 10:
                meta.setDisplayName("§bСтадия №10");
                lore.add("§7Надеюсь, она сможет наконец-то приготовить мне зелье.");
                lore.add("§7Не могу больше терпеть эту пустоту в своей голове");
                lore.add("");
                lore.add("§7Необходимо собрать:");
                lore.add("  §7-" + RPGItemContainer.getRPGItemContainer(54).getName() + " " + WorldUtils.checkItems(pl,57,0) + "/" + 5);
                lore.add("  §7-" + RPGItemContainer.getRPGItemContainer(82).getName() + " " + WorldUtils.checkItems(pl,57,0) + "/" + 5);
                lore.add("  §7-" + RPGItemContainer.getRPGItemContainer(79).getName() + " " + WorldUtils.checkItems(pl,57,0) + "/" + 1);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                lore.add("§7- §6500 §7опыта" );
                break;
            case 11:
                meta.setDisplayName("§bСтадия №11");
                lore.add("§7Надеюсь, она сможет наконец-то приготовить мне зелье.");
                lore.add("§7Не могу больше терпеть эту пустоту в своей голове");
                lore.add("");
                lore.add("§eСдача: §e§lВанесса");
                lore.add("§7- §6500 §7опыта" );
                break;
            case 12:
                meta.setDisplayName("§bСтадия №12");
                lore.add("§4Требование: 15 Уровень");
                break;
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack quest4(Player p, Integer stage) {
        ItemStack item = new ItemStack(Material.BOOK_AND_QUILL, 1);
        rpgplayer pl = rpgplayer.getRPGPlayer(p);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        switch (stage){
            case 1:
                meta.setDisplayName("§bСтадия №1");
                lore.add("§7Выпив зелье, я вспомнил только свое имя.");
                lore.add("§7Остальные воспоминания, как будто запечатаны.");
                lore.add("§7Ванесса сказала, что со временем всё вернётся");
                lore.add("§7но пока предложила мне пойти к её подруге Анри");
                lore.add("");
                lore.add("§eНеобходимо сдать: §e§lАнри");
                lore.add("  §eКоординаты: §e§l-4157 161 2893");
                lore.add("");
                lore.add("§eСдача: §e§lДиалог");
                break;
            case 2:
                meta.setDisplayName("§bСтадия №2");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(20).getName() + ": §70/" + 7);
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(21).getName() + ": §70/" + 7);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                lore.add("§7- §6500 §7опыта" );
                break;
            case 3:
                meta.setDisplayName("§bСтадия №3");
                lore.add("§4Требование: 16 Уровень");
                break;
            case 4:
                meta.setDisplayName("§bСтадия №4");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(22).getName() + ": §70/" + 7);
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(23).getName() + ": §70/" + 7);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                lore.add("§7- §6500 §7опыта" );
                break;
            case 5:
                meta.setDisplayName("§bСтадия №5");
                lore.add("§4Требование: 17 Уровень");
                break;
            case 6:
                meta.setDisplayName("§bСтадия №6");
                lore.add("§eНеобходимо сдать: §e§lАнри");
                lore.add("  §eКоординаты: §e§l-4157 161 2893");
                lore.add("");
                lore.add("§eСдача: §e§lДиалог");
                break;
            case 7:
                meta.setDisplayName("§bСтадия №7");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(24).getName() + ": §70/" + 10);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                lore.add("§7- §61600 §7опыта" );
                break;
            case 8:
                meta.setDisplayName("§bСтадия №8");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(25).getName() + ": §70/" + 7);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                lore.add("§7- §61600 §7опыта" );
                break;
            case 9:
                meta.setDisplayName("§bСтадия №9");
                break;
            case 10:
                meta.setDisplayName("§bСтадия №10");
                lore.add("§7Черт, я не могу достать этих призраков!");
                lore.add("§7Они слишком быстрые и неуловимые. Нужно что-то другое");
                lore.add("§7Анри должна знать, что делать с призраками.");
                lore.add("§7Я надеюсь, она сможет мне помочь.");
                lore.add("");
                lore.add("§eНеобходимо сдать: §e§lАнри");
                lore.add("  §eКоординаты: §e§l-4157 161 2893");
                lore.add("");
                lore.add("§eСдача: §e§lДиалог");
                break;
            case 11:
                meta.setDisplayName("§bСтадия №11");
                lore.add("§4Требование: 18 Уровень");
                break;
            case 12:
                meta.setDisplayName("§bСтадия №12");
                lore.add("§7Амулет от призраков? Надеюсь, он сработает");
                lore.add("");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(25).getName() + ": §70/" + 7);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                lore.add("§7- §62800 §7опыта" );
                break;
            case 13:
                meta.setDisplayName("§bСтадия №13");
                lore.add("§4Требование: 19 Уровень");
                break;
            case 14:
                meta.setDisplayName("§bСтадия №14");
                lore.add("§7Амулет от призраков? Надеюсь, он сработает");
                lore.add("");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(26).getName() + ": §70/" + 7);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                lore.add("§7- §62800 §7опыта" );
                break;
            case 15:
                meta.setDisplayName("§bСтадия №15");
                lore.add("§eНеобходимо сдать: §e§lАнри");
                lore.add("  §eКоординаты: §e§l-4262 156 2950");
                lore.add("");
                lore.add("§eСдача: §e§lДиалог");
                break;
            case 16:
                meta.setDisplayName("§bСтадия №16");
                lore.add("§7Анри! Она спустилась сюда!");
                lore.add("§7Что-то здесь не так… Это давление…");
                lore.add("§7Анри сказала, что у нас проблема… Что это значит?");
                lore.add("");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(27).getName() + ": §70/" + 1);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                lore.add("§7- §62800 §7опыта" );
                break;
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack quest5(Player p, Integer stage) {
        ItemStack item = new ItemStack(Material.BOOK_AND_QUILL, 1);
        rpgplayer pl = rpgplayer.getRPGPlayer(p);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        switch (stage){
            case 1:
                meta.setDisplayName("§bСтадия №1");
                lore.add("§7Анри говорит о другой стороне острова?");
                lore.add("§7Интересно, что там может быть?");
                lore.add("§7Может быть, этот человек в черном скрывается там?");
                lore.add("§7Нужно туда сходить, может, там найду ответы на свои вопросы.");
                lore.add("");
                lore.add("§eНеобходимо сдать: §e§lЭлайя");
                lore.add("  §eКоординаты: §e§l-4235 130 3251");
                lore.add("");
                lore.add("§eСдача: §e§lДиалог");
                break;
            case 2:
                meta.setDisplayName("§bСтадия №2");
                lore.add("§7Девушка в этом темном тоннеле?");
                lore.add("§7Странно, но я решил к ней подойти.");
                lore.add("§7Она выглядит испуганной, возможно, ей нужна помощь");
                lore.add("");
                lore.add("§eНеобходимо сдать: §e§lЭлайя");
                lore.add("  §eКоординаты: §e§l-4235 130 3251");
                lore.add("");
                lore.add("§eСдача: §e§lДиалог");
                break;
            case 3:
                meta.setDisplayName("§bСтадия №3");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(28).getName() + ": §70/" + 3);
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(29).getName() + ": §70/" + 3);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                lore.add("§7- §6500 §7опыта" );
                break;
            case 4:
                meta.setDisplayName("§bСтадия №4");
                lore.add("§eНеобходимо сдать: §e§lЭлайя");
                lore.add("  §eКоординаты: §e§l-4455 105 3282");
                lore.add("");
                lore.add("§eСдача: §e§lДиалог");
                break;
            case 5:
                meta.setDisplayName("§bСтадия №5");
                lore.add("§eНеобходимо сдать: §e§lЭлайя");
                lore.add("  §eКоординаты: §e§l-4689 108 3365");
                lore.add("");
                lore.add("§eСдача: §e§lДиалог");
                break;
            case 6:
                meta.setDisplayName("§bСтадия №6");
                lore.add("§eНеобходимо сдать: §e§lКорей");
                lore.add("  §eКоординаты: §e§l-4727 127 3368");
                lore.add("");
                lore.add("§eСдача: §e§lДиалог");
                break;
            case 7:
                meta.setDisplayName("§bСтадия №7");
                lore.add("§eНеобходимо сдать: §e§lАлхимик");
                lore.add("  §eКоординаты: §e§l-4514 105 3236");
                lore.add("");
                lore.add("§eСдача: §e§lДиалог");
                break;
            case 8:
                meta.setDisplayName("§bСтадия №8");
                lore.add("§7Странно, что Алхимик так легко делится информацией о мире.");
                lore.add("§7Его слова звучат убедительно, но все же… в них есть что-то подозрительное.");
                lore.add("§7Нужно обдумать его слова. Может, староста знает о нем больше?");
                lore.add("§7Нужно выполнить задание старосты и после расспросить его об Алхимике.");
                lore.add("");
                lore.add("§7Задача:");
                lore.add("  §7-Покиньте дом");
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                lore.add("§7- §61600 §7опыта" );
                break;
            case 9:
                meta.setDisplayName("§bСтадия №9");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(28).getName() + ": §70/" + 7);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                lore.add("§7- §61650 §7опыта" );
                break;
            case 10:
                meta.setDisplayName("§bСтадия №10");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(29).getName() + ": §70/" + 7);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                lore.add("§7- §61650 §7опыта" );
                break;
            case 11:
                meta.setDisplayName("§bСтадия №11");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(30).getName() + ": §70/" + 7);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                lore.add("§7- §61650 §7опыта" );
                break;
            case 12:
                meta.setDisplayName("§bСтадия №12");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(31).getName() + ": §70/" + 7);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                lore.add("§7- §61650 §7опыта" );
                break;
            case 13:
                meta.setDisplayName("§bСтадия №13");
                lore.add("§eНеобходимо сдать: §e§lКорей");
                lore.add("  §eКоординаты: §e§l-4727 127 3368");
                lore.add("");
                lore.add("§eСдача: §e§lДиалог");
                break;
            case 14:
                meta.setDisplayName("§bСтадия №14");
                lore.add("§7Я все еще сомневаюсь в Алхимике, но его слова заставили меня задуматься.");
                lore.add("§7Может быть, он прав? Нужно вернуться к нему и узнать больше.");
                lore.add("§7Я должен разобраться в этой истории, даже если она опасна");
                lore.add("");
                lore.add("§eНеобходимо сдать: §e§lАлхимик");
                lore.add("  §eКоординаты: §e§l-4514 105 3236");
                lore.add("");
                lore.add("§eСдача: §e§lДиалог");
                lore.add("§7- §62800 §7опыта" );
                break;
            case 15:
                meta.setDisplayName("§bСтадия №15");
                lore.add("§7Задача: Найдите сундук");
                lore.add("");
                lore.add("§eНеобходимо сдать: §e§lАлхимик");
                lore.add("  §eКоординаты: §e§l-4514 105 3236");
                lore.add("");
                lore.add("§eСдача: §e§lДиалог");
                break;
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack quest6(Player p, Integer stage) {
        ItemStack item = new ItemStack(Material.BOOK_AND_QUILL, 1);
        rpgplayer pl = rpgplayer.getRPGPlayer(p);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        switch (stage){
            case 1:
                meta.setDisplayName("§bСтадия №1");
                lore.add("§7Алхимик сказал, что нужно начать с Солнечного региона.");
                lore.add("§7Надеюсь, он прав, что он слабейший.");
                lore.add("§7Но что будет, если я встречусь с чем-то, что я не могу победить?");
                lore.add("");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(32).getName() + ": §70/" + 10);
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(33).getName() + ": §70/" + 10);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                break;
            case 2:
                meta.setDisplayName("§bСтадия №2");
                lore.add("§7Алхимик сказал, что нужно начать с Солнечного региона.");
                lore.add("§7Надеюсь, он прав, что он слабейший.");
                lore.add("§7Но что будет, если я встречусь с чем-то, что я не могу победить?");
                lore.add("");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(35).getName() + ": §70/" + 10);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                break;
            case 3:
                meta.setDisplayName("§bСтадия №3");
                lore.add("§4Требование: 222 Уровень");
                break;
            case 4:
                meta.setDisplayName("§bСтадия №4");
                lore.add("§7Алхимик сказал, что нужно начать с Солнечного региона.");
                lore.add("§7Надеюсь, он прав, что он слабейший.");
                lore.add("§7Но что будет, если я встречусь с чем-то, что я не могу победить?");
                lore.add("");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(34).getName() + ": §70/" + 7);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                break;
            case 5:
                meta.setDisplayName("§bСтадия №5");
                lore.add("§7Алхимик сказал, что нужно начать с Солнечного региона.");
                lore.add("§7Надеюсь, он прав, что он слабейший.");
                lore.add("§7Но что будет, если я встречусь с чем-то, что я не могу победить?");
                lore.add("");
                lore.add("§7Необходимо собрать:");
                lore.add("  §7-" + RPGItemContainer.getRPGItemContainer(148).getName() + " " + WorldUtils.checkItems(pl,148,0) + "/" + 15);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                break;
            case 6:
                meta.setDisplayName("§bСтадия №6");
                lore.add("§4Требование: 23 Уровень");
                break;
            case 7:
                meta.setDisplayName("§bСтадия №7");
                lore.add("§7Солнечный регион позади. Я получил нужный кристалл");
                lore.add("§7Но в нем чувствуется какая-то темная энергия.");
                lore.add("§7Алхимик был прав, этот регион самый слабый.");
                lore.add("§7Но что ждет меня дальше? Регион Грязи… ");
                lore.add("");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(37).getName() + ": §70/" + 10);
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(38).getName() + ": §70/" + 10);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                break;
            case 8:
                meta.setDisplayName("§bСтадия №8");
                lore.add("§4Требование: 24 Уровень");
                break;
            case 9:
                meta.setDisplayName("§bСтадия №9");
                lore.add("§7Солнечный регион позади. Я получил нужный кристалл");
                lore.add("§7Но в нем чувствуется какая-то темная энергия.");
                lore.add("§7Алхимик был прав, этот регион самый слабый.");
                lore.add("§7Но что ждет меня дальше? Регион Грязи… ");
                lore.add("");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(39).getName() + ": §70/" + 10);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                break;
            case 10:
                meta.setDisplayName("§bСтадия №10");
                lore.add("§7Солнечный регион позади. Я получил нужный кристалл");
                lore.add("§7Но в нем чувствуется какая-то темная энергия.");
                lore.add("§7Алхимик был прав, этот регион самый слабый.");
                lore.add("§7Но что ждет меня дальше? Регион Грязи… ");
                lore.add("");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(41).getName() + ": §70/" + 7);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                break;
            case 11:
                meta.setDisplayName("§bСтадия №11");
                lore.add("§4Требование: 25 Уровень");
                break;
            case 12:
                meta.setDisplayName("§bСтадия №12");
                lore.add("§7Солнечный регион позади. Я получил нужный кристалл");
                lore.add("§7Но в нем чувствуется какая-то темная энергия.");
                lore.add("§7Алхимик был прав, этот регион самый слабый.");
                lore.add("§7Но что ждет меня дальше? Регион Грязи… ");
                lore.add("");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(40).getName() + ": §70/" + 7);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                break;
            case 13:
                meta.setDisplayName("§bСтадия №13");
                lore.add("§7Солнечный регион позади. Я получил нужный кристалл");
                lore.add("§7Но в нем чувствуется какая-то темная энергия.");
                lore.add("§7Алхимик был прав, этот регион самый слабый.");
                lore.add("§7Но что ждет меня дальше? Регион Грязи… ");
                lore.add("");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGItemContainer.getRPGItemContainer(149).getName() + " " + WorldUtils.checkItems(pl,149,0) + "/" + 15);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                break;
            case 14:
                meta.setDisplayName("§bСтадия №14");
                lore.add("§4Требование: 26 Уровень");
                break;
            case 15:
                meta.setDisplayName("§bСтадия №15");
                lore.add("§7Регион Ночи… Название само по себе пугающее.");
                lore.add("§7Что скрывается там?");
                lore.add("§7Я уже чувствовал странную энергию в Солнечном и Грязевом регионах.");
                lore.add("§7Но что будет в этом регионе Ночи?");
                lore.add("");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(43).getName() + ": §70/" + 15);
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(46).getName() + ": §70/" + 15);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                break;
            case 16:
                meta.setDisplayName("§bСтадия №16");
                lore.add("§7Регион Ночи… Название само по себе пугающее.");
                lore.add("§7Что скрывается там?");
                lore.add("§7Я уже чувствовал странную энергию в Солнечном и Грязевом регионах.");
                lore.add("§7Но что будет в этом регионе Ночи?");
                lore.add("");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(44).getName() + ": §70/" + 10);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                break;
            case 17:
                meta.setDisplayName("§bСтадия №17");
                lore.add("§7Регион Ночи… Название само по себе пугающее.");
                lore.add("§7Что скрывается там?");
                lore.add("§7Я уже чувствовал странную энергию в Солнечном и Грязевом регионах.");
                lore.add("§7Но что будет в этом регионе Ночи?");
                lore.add("");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(45).getName() + ": §70/" + 10);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                break;
            case 18:
                meta.setDisplayName("§bСтадия №18");
                lore.add("§4Требование: 27 Уровень");
                break;
            case 19:
                meta.setDisplayName("§bСтадия №19");
                lore.add("§7Регион Ночи… Название само по себе пугающее.");
                lore.add("§7Что скрывается там?");
                lore.add("§7Я уже чувствовал странную энергию в Солнечном и Грязевом регионах.");
                lore.add("§7Но что будет в этом регионе Ночи?");
                lore.add("");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(47).getName() + ": §70/" + 7);
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(48).getName() + ": §70/" + 7);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                break;
            case 20:
                meta.setDisplayName("§bСтадия №20");
                lore.add("§7Регион Ночи… Название само по себе пугающее.");
                lore.add("§7Что скрывается там?");
                lore.add("§7Я уже чувствовал странную энергию в Солнечном и Грязевом регионах.");
                lore.add("§7Но что будет в этом регионе Ночи?");
                lore.add("");
                lore.add("§7Необходимо собрать:");
                lore.add("  §7-" + RPGItemContainer.getRPGItemContainer(150).getName() + " " + WorldUtils.checkItems(pl,150,0) + "/" + 32);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                break;
            case 21:
                meta.setDisplayName("§bСтадия №21");
                lore.add("§4Требование: 28 Уровень");
                break;
            case 22:
                meta.setDisplayName("§bСтадия №22");
                lore.add("§7Кровавый регион… Это звучит пугающе.");
                lore.add("§7Но я должен найти последний кристалл.");
                lore.add("§7Надеюсь, все будет в порядке.");
                lore.add("");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(50).getName() + ": §70/" + 10);
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(51).getName() + ": §70/" + 10);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                break;
            case 23:
                meta.setDisplayName("§bСтадия №23");
                lore.add("§7Кровавый регион… Это звучит пугающе.");
                lore.add("§7Но я должен найти последний кристалл.");
                lore.add("§7Надеюсь, все будет в порядке.");
                lore.add("");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(53).getName() + ": §70/" + 10);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                break;
            case 24:
                meta.setDisplayName("§bСтадия №24");
                lore.add("§7Кровавый регион… Это звучит пугающе.");
                lore.add("§7Но я должен найти последний кристалл.");
                lore.add("§7Надеюсь, все будет в порядке.");
                lore.add("");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(55).getName() + ": §70/" + 10);
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(57).getName() + ": §70/" + 10);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                break;
            case 25:
                meta.setDisplayName("§bСтадия №25");
                lore.add("§4Требование: 29 Уровень");
                break;
            case 26:
                meta.setDisplayName("§bСтадия №26");
                lore.add("§7Кровавый регион… Это звучит пугающе.");
                lore.add("§7Но я должен найти последний кристалл.");
                lore.add("§7Надеюсь, все будет в порядке.");
                lore.add("");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(52).getName() + ": §70/" + 7);
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(54).getName() + ": §70/" + 7);
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(58).getName() + ": §70/" + 7);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                break;
            case 27:
                meta.setDisplayName("§bСтадия №27");
                lore.add("§7Кровавый регион… Это звучит пугающе.");
                lore.add("§7Но я должен найти последний кристалл.");
                lore.add("§7Надеюсь, все будет в порядке.");
                lore.add("");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(59).getName() + ": §70/" + 10);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                break;
            case 28:
                meta.setDisplayName("§bСтадия №28");
                lore.add("§7Кровавый регион… Это звучит пугающе.");
                lore.add("§7Но я должен найти последний кристалл.");
                lore.add("§7Надеюсь, все будет в порядке.");
                lore.add("");
                lore.add("§7Необходимо собрать:");
                lore.add("  §7-" + RPGItemContainer.getRPGItemContainer(208).getName() + " " + WorldUtils.checkItems(pl,208,0) + "/" + 32);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                break;
            case 29:
                meta.setDisplayName("§bСтадия №29");
                lore.add("§7Кровавый регион… Это звучит пугающе.");
                lore.add("§7Но я должен найти последний кристалл.");
                lore.add("§7Надеюсь, все будет в порядке.");
                lore.add("");
                lore.add("§eНеобходимо сдать: §e§lАлхимик");
                lore.add("  §eКоординаты: §e§l-4514 105 3236");
                lore.add("");
                lore.add("§eСдача: §e§lДиалог");
                break;
            case 30:
                meta.setDisplayName("§bСтадия №30");
                lore.add("§eСдача: §e§lАвтоматическая");
            case 31:
                meta.setDisplayName("§bСтадия №31");
                lore.add("§7Почему его нет в доме?");
                lore.add("§7Кажется я знаю куда мне идти...");
                lore.add("");
                lore.add("§7Задача: ");
                lore.add("  §7Направляйтесь к большому дереву");
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                break;
            case 32:
                meta.setDisplayName("§bСтадия №32");
                lore.add("§7Я так и знал, что ему не стоит доверять");
                lore.add("");
                lore.add("§7Необходимо убить:");
                lore.add("  §7-" + RPGMobsContainer.getRPGMobsContainer(60).getName() + ": §70/" + 1);
                lore.add("");
                lore.add("§eСдача: §e§lАвтоматическая");
                break;

        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

}
