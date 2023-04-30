package model;

public enum ActivityLevel {

    ALMOST_NO_EXERCISE(1, 1.2),
    LIGHT_EXERCISE(1, 1.375),
    MODERATE_EXERCISE(1, 1.55),
    HARD_EXERCIS(1, 1.725),
    EXTREME_HARD_EXERCIS(1, 1.9);

    private final int registrationNumber;
    private final double multiplierToBasalMetabolism;

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
}
