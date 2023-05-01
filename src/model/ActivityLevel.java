package model;

import java.util.HashMap;
import java.util.Map;

public enum ActivityLevel {

    ALMOST_NO_EXERCISE(1, 1.2),
    LIGHT_EXERCISE(1, 1.375),
    MODERATE_EXERCISE(1, 1.55),
    HARD_EXERCIS(1, 1.725),
    EXTREME_HARD_EXERCIS(1, 1.9);

    private static final Map<Integer, ActivityLevel> intToActivityLevel = new HashMap<>();
    private final int registrationNumber;
    private final double multiplierToBasalMetabolism;

    static {
        for (ActivityLevel activityLevel : ActivityLevel.values()) {
            intToActivityLevel.put(activityLevel.getRegistrationNumber(), activityLevel);
        }
    }

    private ActivityLevel(int registrationNumber, double multiplierToBasalMetabolism) {
        this.registrationNumber = registrationNumber;
        this.multiplierToBasalMetabolism = multiplierToBasalMetabolism;
    }

    public int getRegistrationNumber() {
        return registrationNumber;
    }

    public double getMultiplierToBasalMetabolism() {
        return multiplierToBasalMetabolism;
    }

    public static ActivityLevel getActivityLevelFromInt(int i) {
        return intToActivityLevel.get(Integer.valueOf(i));
    }
}
