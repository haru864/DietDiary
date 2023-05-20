package model;

public class RecommendedIntake {

    public String nutritionName;
    public Gender gender;
    public int minAge;
    public int maxAge;
    public int physicalActivityLevel;
    public int pregnancyPeriod;
    public int breastfeeding;
    public Double recommended_intake;
    public Double upper_limit;

    public RecommendedIntake(String nutritionName, Gender gender, int minAge, int maxAge, int physicalActivityLevel,
            int pregnancyPeriod, int breastfeeding, Double recommended_intake, Double upper_limit) {
        this.nutritionName = nutritionName;
        this.gender = gender;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.physicalActivityLevel = physicalActivityLevel;
        this.pregnancyPeriod = pregnancyPeriod;
        this.breastfeeding = breastfeeding;
        this.recommended_intake = recommended_intake;
        this.upper_limit = upper_limit;
    }

}
