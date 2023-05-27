package com.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Nutrition {

    public static final List<String> NUTRITION_LIST = new ArrayList<>();
    private final Map<String, Double> nutritionAmount = new HashMap<>();

    static {
        NUTRITION_LIST.add("energy");
        NUTRITION_LIST.add("protein");
        NUTRITION_LIST.add("fat");
        NUTRITION_LIST.add("fiber");
        NUTRITION_LIST.add("carbohydrates");
        NUTRITION_LIST.add("vitamin_a");
        NUTRITION_LIST.add("vitamin_b1");
        NUTRITION_LIST.add("vitamin_b2");
        NUTRITION_LIST.add("vitamin_b6");
        NUTRITION_LIST.add("vitamin_b12");
        NUTRITION_LIST.add("vitamin_c");
        NUTRITION_LIST.add("vitamin_d");
        NUTRITION_LIST.add("vitamin_e");
        NUTRITION_LIST.add("vitamin_k");
        NUTRITION_LIST.add("niacin_equivalent");
        NUTRITION_LIST.add("folic_acid");
        NUTRITION_LIST.add("pantothenic_acid");
        NUTRITION_LIST.add("biotin");
        NUTRITION_LIST.add("na");
        NUTRITION_LIST.add("k");
        NUTRITION_LIST.add("ca");
        NUTRITION_LIST.add("mg");
        NUTRITION_LIST.add("p");
        NUTRITION_LIST.add("fe");
        NUTRITION_LIST.add("zn");
        NUTRITION_LIST.add("cu");
        NUTRITION_LIST.add("mn");
        NUTRITION_LIST.add("id");
        NUTRITION_LIST.add("se");
        NUTRITION_LIST.add("cr");
        NUTRITION_LIST.add("mo");
    }

    public Nutrition() {

        for (String nutritionName : NUTRITION_LIST) {
            nutritionAmount.put(nutritionName, 0.0);
        }
    }

    public void setNutritionAmount(String nutritionName, double amount) {

        nutritionAmount.put(nutritionName, amount);
    }

    public Double getNutritionAmount(String nutritionName) {

        return nutritionAmount.get(nutritionName);
    }

}
