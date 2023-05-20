package model;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class CalculateTDEELogic {

    public double execute(Date birth, Gender gender, double height, double weight, ActivityLevel activityLevel) {

        if (birth == null || gender == null || activityLevel == null) {
            return -1.0;
        }

        final LocalDate today = LocalDate.now();
        final LocalDate birthLocalDate = LocalDate.ofInstant(birth.toInstant(), ZoneId.systemDefault());
        final int age = Period.between(birthLocalDate, today).getYears();

        final double basalMetabolism = calcBasalMetabolism(gender, age, height, weight);
        final double TDEE = basalMetabolism * activityLevel.getMultiplierToBasalMetabolism();

        return TDEE;
    }

    private double calcBasalMetabolism(Gender gender, int age, double height, double weight) {

        double basalMetabolism = 0.0;

        // 男性： 13.397 × 体重kg + 4.799 × 身長cm − 5.677 × 年齢 + 88.362
        // 女性： 9.247 × 体重kg + 3.098 × 身長cm − 4.33 × 年齢 + 447.593
        if (gender == Gender.men) {
            basalMetabolism = 13.397 * weight + 4.799 * height - 5.677 * age + 88.362;
        } else if (gender == Gender.women) {
            basalMetabolism = 9.2470 * weight + 3.098 * height - 4.330 * age + 88.362;
        }

        return basalMetabolism;
    }
}
