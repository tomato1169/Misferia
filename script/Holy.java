package azerot.azerot.script;

import azerot.azerot.MobSpells;
import azerot.azerot.RPGPlayerShoot;
import azerot.azerot.azerot;
import azerot.azerot.mobs.MobInfo;
import azerot.azerot.mobs.MobInfoManager;
import azerot.azerot.mobs.MobSystem;
import azerot.azerot.rpgplayer;
import io.netty.util.internal.ThreadLocalRandom;
import net.minecraft.server.v1_12_R1.EntityLiving;
import net.minecraft.server.v1_12_R1.EntityTippedArrow;
import net.minecraft.server.v1_12_R1.MathHelper;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Holy
{

    static HashMap<String, Integer> attack1 = new HashMap<>();

    static HashMap<Integer, Location> locationBlocks = new HashMap<>();

    static int stage = 0;

    public static void onDamaged(Entity e, Entity t, int damage) {
        Vector velocity;
        MobInfo mobInfo = MobInfoManager.getMobInfo(e);
        int id = mobInfo.getId();
        double percentage = (double) mobInfo.getHealth() / mobInfo.getMaxHealth();
        List<Player> p = MobSystem.getNearbyPlayers((LivingEntity)e, 8.0D);
        if (mobInfo.getHealth() + damage == mobInfo.getMaxHealth()) {
            mobInfo.setCritChance(0);
            attack1.put(e.getUniqueId().toString(), 0);
            stage = 0;
        }

        if (percentage < 0.9D && (Integer) attack1.get(e.getUniqueId().toString()) == 0) {
            message(e, "§4Я есть свет! Я есть истина! Я есть правосудие");
            attack1.put(e.getUniqueId().toString(), 1);
            int finalStage = stage;
            (new BukkitRunnable() {
                public void run() {
                    if(finalStage == 2){
                        cancel();
                    }else{
                        for(Player nearby : p){
                                Vector launchVector = new Vector(0, 7, 0).normalize();
                                nearby.setVelocity(launchVector);
                        }
                    }

                }
            }).runTaskTimer((Plugin) azerot.getInstance(), 100L, 100L);
            int[] limits = {35, 35, 20, 10};
            Random r = new Random();
            // Массив значений
            int[] valuesToDistribute = {1, 2, 3, 4};
            boolean allLimitsReached = false;
            int[] usedCounts = {0, 0, 0, 0};
            List<Integer> values = new ArrayList<>();

            while (values.isEmpty() || values.size() < 100) {
                // Проверяем, есть ли еще свободные места для значений
                boolean hasFreeSpace = false;
                for (int i = 0; i < limits.length; i++) {
                    if (usedCounts[i] < limits[i]) {
                        hasFreeSpace = true;
                        break;
                    }
                }

                if (hasFreeSpace) {
                    // Выбираем случайное значение
                    int randomValueIndex = r.nextInt(valuesToDistribute.length);
                    int value = valuesToDistribute[randomValueIndex];

                    // Проверяем, не превышен ли лимит для этого значения
                    if (usedCounts[randomValueIndex] < limits[randomValueIndex]) {
                        // Добавляем значение в список
                        values.add(value);
                        // Увеличиваем счетчик добавленных значений
                        usedCounts[randomValueIndex]++;
                    }
                } else {
                    // Если все лимиты достигнуты, прерываем цикл
                    break;
                }
            }

            (new BukkitRunnable() {
                public void run() {
                    if(finalStage == 2){
                        cancel();
                    }else{
                        int random = r.nextInt(100);
                        int value = values.get(random);

                        switch (value){
                            case 1:
                                int random1 = ThreadLocalRandom.current().nextInt(1, 3);
                                switch (random1){
                                    case 1:
                                        message(e, "§4Свидетели веры, явитесь!");
                                        break;
                                    case 2:
                                        message(e, "§4Да смирятся грешники пред мощью моей паствы!");
                                        break;
                                    case 3:
                                        message(e, "§4Примите свой долг, мои верные.");
                                        break;
                                }

                                break;
                            case 2:
                                int random2 = ThreadLocalRandom.current().nextInt(1, 3);
                                switch (random2){
                                    case 1:
                                        message(e, "§4Ваша наглость будет наказана!");
                                        break;
                                    case 2:
                                        message(e, "§4Держитесь подальше!");
                                        break;
                                    case 3:
                                        message(e, "§4Во имя справедливости, падите ниц!");
                                        break;
                                }
                                break;
                            case 3:
                                int random3 = ThreadLocalRandom.current().nextInt(1, 3);
                                switch (random3){
                                    case 1:
                                        message(e, "§4Неужели вы настолько ослеплены тьмой, что не видите мой свет?");
                                        break;
                                    case 2:
                                        message(e, "§4Узрите мощь небес");
                                        break;
                                    case 3:
                                        message(e, "§4Небеса, внемлите моей мольбе!");
                                        break;
                                }
                                break;
                            case 4:
                                int random4 = ThreadLocalRandom.current().nextInt(1, 3);
                                switch (random4){
                                    case 1:
                                        message(e, "§4Я - ваш судья, и я - ваш палач!");
                                        break;
                                    case 2:
                                        message(e, "§4Ваша судьба решена");
                                        break;
                                    case 3:
                                        message(e, "§4Я объявляю вас виновными и приговариваю к уничтожению!");
                                        break;
                                }
                                break;
                        }
                    }

                }
            }).runTaskTimer((Plugin) azerot.getInstance(), 100L, 100L);

            stage++;
            for (Player nearby : p){
                rpgplayer pl = rpgplayer.getRPGPlayer(nearby);
                return;
            }
        }

        if (percentage < 0.5D && (Integer) attack1.get(e.getUniqueId().toString()) == 1) {
            message(e, "§4Я есть свет! Я есть истина! Я есть правосудие");
            attack1.put(e.getUniqueId().toString(), 2);
            stage++;
            for (Player nearby : p){
                rpgplayer pl = rpgplayer.getRPGPlayer(nearby);
                MobSpells.curseFear(e, nearby,6000,20);
                return;
            }
        }




    }

    public static void setLocationBlocks(Location loc){
        Location location = new Location(Bukkit.getWorld("realm2"), -1498,256,1808);
        int i = 0;
        for(int ip = -20; ip <= 20 ; ip++){
            for(int j = -24; j <= 24; j++){
                locationBlocks.put(i, new Location(Bukkit.getWorld("realm2"), location.getBlockX() + ip,256, location.getBlockZ() + j));
                i++;
            }
        }

    }

    public static void message(Entity e, String message) {
        MobInfo mobInfo = MobInfoManager.getMobInfo(e);
        int level = mobInfo.getLevel();
        String name = mobInfo.getName();
        List<Player> p = MobSystem.getNearbyPlayers((LivingEntity)e, 8.0D);
        for (Player nearby : p)
            nearby.sendMessage("§4["+ level + "§4] " + name + "§f: " + message);
    }
}
