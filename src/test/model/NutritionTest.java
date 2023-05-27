package test.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.model.Nutrition;

public class NutritionTest {

    public static void main(String[] args) {

        List<String> failedCasesList = new ArrayList<>();

        if (testSetAndGetNutritionAmount() == true) {
            System.out.println("testSetAndGetNutritionAmount ... passed");
        } else {
            System.out.println("testSetAndGetNutritionAmount ... FAILED");
            failedCasesList.add("testSetAndGetNutritionAmount: case_01-01 failed");
        }

        showFailedCases(failedCasesList);
    }

    private static void showFailedCases(List<String> failedCasesList) {

        if (failedCasesList.size() == 0) {
            System.out.println();
            System.out.println("All cases passed!");
            return;
        }

        System.out.println("================================================");
        System.out.println(failedCasesList.size() + " cases failed");
        System.out.println("------------------------------------------------");

        for (String message : failedCasesList) {
            System.out.println("  " + message);
        }
    }

    private static boolean testSetAndGetNutritionAmount() {

        Nutrition nutrition = new Nutrition();
        List<Double> list = new ArrayList<>();

        for (String nutritionName : Nutrition.NUTRITION_LIST) {
            double randomDouble = generateRandomDouble(1, 10);
            nutrition.setNutritionAmount(nutritionName, randomDouble);
            if (randomDouble != nutrition.getNutritionAmount(nutritionName)) {
                return false;
            }
        }

        return true;
    }

    private static double generateRandomDouble(int min, int max) {

        Random random = new Random();
        double randomDouble = random.nextDouble() * (max - min) + min;
        // System.out.println(randomDouble + " -> " + Math.floor(randomDouble));
        return Math.floor(randomDouble);
    }

}
