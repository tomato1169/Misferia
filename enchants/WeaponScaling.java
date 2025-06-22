package azerot.azerot.enchants;

import java.util.*;
import java.util.stream.Collectors;

public class WeaponScaling {

    enum ScalingType {
        STRENGTH,
        AGILITY,
        INTELLIGENCE
    }

    public static List<ScalingType> generateScaling() {
        Random random = new Random();
        List<ScalingType> allScalings = Arrays.asList(ScalingType.STRENGTH, ScalingType.AGILITY, ScalingType.INTELLIGENCE);

        return random.nextDouble() < 0.1
                ? allScalings.stream()
                .distinct()
                .limit(2)
                .collect(Collectors.toList())
                : Collections.singletonList(allScalings.get(random.nextInt(3)));
    }


    public static void main(String[] args) {
    }
}