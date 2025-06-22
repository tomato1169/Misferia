package azerot.azerot;

import io.netty.util.internal.ThreadLocalRandom;

import java.text.DecimalFormat;

public class RPGLevels {
    public static long RPGLevels(rpgplayer p) {
        long xptolevel = 0L;
        if(azerot.patch >= 1){
            switch (p.getLevel()) {
                case 1:
                    xptolevel = 30L;
                    return xptolevel;
                case 2:
                    xptolevel = 90L;
                    return xptolevel;
                case 3:
                    xptolevel = 154L;
                    return xptolevel;
                case 4:
                    xptolevel = 210L;
                    return xptolevel;
                case 5:
                    xptolevel = 296L;
                    return xptolevel;
                case 6:
                    xptolevel = 326L;
                    return xptolevel;
                case 7:
                    xptolevel = 421L;
                    return xptolevel;
                case 8:
                    xptolevel = 530L;
                    return xptolevel;
                case 9:
                    xptolevel = 690L;
                    return xptolevel;
                case 10:
                    xptolevel = 1050L;
                    return xptolevel;
                case 11:
                    xptolevel = 1500L;
                    return xptolevel;
                case 12:
                    xptolevel = 1870L;
                    return xptolevel;
                case 13:
                    xptolevel = 2105L;
                    return xptolevel;
                case 14:
                    xptolevel = 2434L;
                    return xptolevel;
                case 15:
                    xptolevel = 2670L;
                    return xptolevel;
                case 16:
                    xptolevel = 2960L;
                    return xptolevel;
                case  17:
                    xptolevel = 3500L;
                    return xptolevel;
                case 18:
                    xptolevel = 5570L;
                    return xptolevel;
                case 19:
                    xptolevel = 7070L;
                    return xptolevel;
            }
        }
        if(azerot.patch >= 2){
            switch (p.getLevel()) {
                case 20:
                    xptolevel = 12300L;
                    return xptolevel;
                case 21:
                    xptolevel = 14800L;
                    return xptolevel;
                case 22:
                    xptolevel = 19800L;
                    return xptolevel;
                case 23:
                    xptolevel = 22200L;
                    return xptolevel;
                case 24:
                    xptolevel = 25470L;
                    return xptolevel;
                case 25:
                    xptolevel = 27350L;
                    return xptolevel;
                case 26:
                    xptolevel = 32750L;
                    return xptolevel;
                case 27:
                    xptolevel = 36350L;
                    return xptolevel;
                case 28:
                    xptolevel = 45220L;
                    return xptolevel;
                case 29:
                    xptolevel = 50000L;
                    return xptolevel;

            }
        }
        if(azerot.patch >= 3){
            switch (p.getLevel()) {
                case 30:
                    xptolevel = 65300L;
                    return xptolevel;
                case 31:
                    xptolevel = 85800L;
                    return xptolevel;
                case 32:
                    xptolevel = 105800L;
                    return xptolevel;
                case 33:
                    xptolevel = 135200L;
                    return xptolevel;
                case 34:
                    xptolevel = 160470L;
                    return xptolevel;
            }
        }
        xptolevel = -1L;
        return xptolevel;
    }

    public static int getHealthForLevel(int level) {
        int health = 0;
        int lvl = level;
        for (int lvlp = 2; lvlp <= level; lvlp++) {
            health += getHealthForLevelPlus(lvl);
            lvl--;
        }
        return health;
    }
    public static int getHealthForLevelPlus(int level) {
        if (level < 10)
            return 2;
        if (level < 15)
            return 4;
        if (level < 20)
            return 7;
        if (level < 25)
            return 10;
        if (level < 30)
            return 25;
        if (level < 35)
            return 40;
        if (level < 40)
            return 60;
        if (level < 45)
            return 75;
        return 100;
    }
}
