package azerot.azerot.enchants;

import java.util.Random;

public class ScaleDrop {

    private static final int[] SCALES = {1, 2, 3, 4, 5, 6}; // Скейлы E-1,D-2,C-3,B-4,A-5,S-6
    private static final double[] PROBABILITIES = {0.4, 0.25, 0.15, 0.1, 0.05, 0.05}; //Шансы выпадения скейлов
    private static final Random random = new Random();

    // Проверка корректности вероятностей (важно!)
    static {
        double sum = 0;
        for (double prob : PROBABILITIES) {
            sum += prob;
        }
        if (Math.abs(sum - 1.0) > 1e-6) {
            throw new IllegalArgumentException("Вероятности не суммируются до 1.0");
        }
    }

    public static int getRandomScale() {
        double randomValue = random.nextDouble();
        double cumulativeProbability = 0;
        for (int i = 0; i < PROBABILITIES.length; i++) {
            cumulativeProbability += PROBABILITIES[i];
            if (randomValue <= cumulativeProbability) {
                return SCALES[i];
            }
        }
        return SCALES[0]; // Should be unreachable, but included for safety
    }

}