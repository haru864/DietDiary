package com.model;

import java.util.Date;

import com.DAO.NutritionFactsDAO;

public class UserIntakeNutrition extends UserIntake {

    private Nutrition userIntakeNutrition = null;

    public UserIntakeNutrition(String username, Date intakeDietDate, int dietNumber, int foodGroup, String foodName,
            double grams) {

        super(username, intakeDietDate, dietNumber, foodGroup, foodName, grams);
        setUserIntakeNutrition();
    }

    public UserIntakeNutrition(UserIntake userIntake) {

        this(userIntake.username, userIntake.intakeDietDate, userIntake.dietNumber,
                userIntake.foodGroup, userIntake.foodName, userIntake.grams);
    }

    private void setUserIntakeNutrition() {

        NutritionFactsDAO nutritionFactsDAO = new NutritionFactsDAO();
        userIntakeNutrition = nutritionFactsDAO.getNutritionOfSpecifiedFood(foodName, grams);
    }

    public Double getUserIntakeNutrition(String nutritionName) {

        return userIntakeNutrition.getNutritionAmount(nutritionName);
    }

    @Override
    public String toString() {
        return "UserIntakeNutrition [" + super.toString() + ", userIntakeNutrition=" + userIntakeNutrition + "]";
    }

}
