package com.model;

public enum PhysicalActivityLevel {

    ALMOST_NO_EXERCISE(1),
    LIGHT_EXERCISE(2),
    HARD_EXERCIS(3);

    private final int physicalActivityLevel;

    private PhysicalActivityLevel(int physicalActivityLevel) {

        this.physicalActivityLevel = physicalActivityLevel;
    }

    public int getPhysicalActivityLevel() {

        return physicalActivityLevel;
    }

    public static PhysicalActivityLevel fromInt(int physicalActivityLevelNumber) {

        for (PhysicalActivityLevel physicalActivityLevel : PhysicalActivityLevel.values()) {

            if (physicalActivityLevel.getPhysicalActivityLevel() == physicalActivityLevelNumber) {

                return physicalActivityLevel;
            }
        }

        throw new IllegalArgumentException("Invalid physicalActivityLevelNumber: " + physicalActivityLevelNumber);
    }

}
