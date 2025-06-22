package azerot.azerot.enchants;

public class chance {


    private static final int[][] SUCCESS_CHANCES = {
            {40, 55, 100, 100}, // 0
            {25, 35, 85, 100}, // 1
            {20, 35, 70, 100}, // 2
            {15, 30, 55, 95}, // 3
            {10, 25, 40, 90}, // 4
            {5, 20, 35, 80}, // 5
            {1, 10, 30, 65}, // 6
            {0, 1, 20, 50}, // 7
            {0, 0, 15, 35}, // 8
            {0, 0, 5, 20}  // 9
    };

    public static int calculateSuccessChance(int enchantmentLevel, int[] usedStones) {
        if (usedStones.length > 3) {
            throw new IllegalArgumentException("Ошибка: Слишком много камней!");
        }

        int chance = 0;// Базовый шанс

        for (int stone : usedStones) {
            if (stone < 1 || stone > 4) {
                throw new IllegalArgumentException("Неверный уровень камня: " + stone);
            }
            chance += SUCCESS_CHANCES[enchantmentLevel][stone - 1];
        }
        if(chance > 100){
            chance = 100;
        }

        return chance;
    }


}
