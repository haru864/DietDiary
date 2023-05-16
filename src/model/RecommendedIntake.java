package model;

public class RecommendedIntake {

    private String nutritionName;
    private Gender gender;
    private int minAge;
    private int maxAge;
    private int physicalActivityLevel;
    private int pregnancyPeriod;
    private int breastfeeding;

    public RecommendedIntake(String nutritionName, Gender gender, int minAge, int maxAge, int physicalActivityLevel,
            int pregnancyPeriod, int breastfeeding) {
        this.nutritionName = nutritionName;
        this.gender = gender;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.physicalActivityLevel = physicalActivityLevel;
        this.pregnancyPeriod = pregnancyPeriod;
        this.breastfeeding = breastfeeding;
    }

}
