package UnitTest;

import java.util.ArrayList;
import java.util.List;

import DAO.RecommendedIntakeDAO;
import model.Gender;
import model.NutritionList;

public class RecommendedIntakeDAOTest {

    public static void main(String[] args) {

        List<String> failedCasesList = new ArrayList<>();
        Gender[] arrGender = new Gender[] { Gender.MEN, Gender.WOMEN };

        // if (testGetRecommendedIntake("energy", Gender.MEN, 10, 1) == false) {
        // failedCasesList.add("case 1");
        // }

        for (int NUTRITION_LIST_i = 0; NUTRITION_LIST_i < NutritionList.NUTRITION_LIST.size(); NUTRITION_LIST_i++) {
            for (int gender_i = 0; gender_i < arrGender.length; gender_i++) {
                for (int physicalActivityLevel = 1; physicalActivityLevel <= 3; physicalActivityLevel++) {
                    var nutritionName = NutritionList.NUTRITION_LIST.get(NUTRITION_LIST_i);
                    if (testGetRecommendedIntake(nutritionName, arrGender[gender_i], 20,
                            physicalActivityLevel) == false) {
                        failedCasesList
                                .add(String.format(
                                        "nutririon_name=%s, gender=%s, age=%d, physicalActivityLevel=%d",
                                        nutritionName, arrGender[gender_i], "20", physicalActivityLevel));
                    }
                }
            }
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

    private static Boolean testGetRecommendedIntake(String nutritionName, Gender gender,
            int age, int physicalActivityLevel) {

        RecommendedIntakeDAO recommendedIntakeDAO = new RecommendedIntakeDAO();
        var recommendedIntake = recommendedIntakeDAO.getRecommendedIntake(nutritionName, gender, age,
                physicalActivityLevel);
        System.out.println(recommendedIntake);
        return recommendedIntake != null;
    }

}
