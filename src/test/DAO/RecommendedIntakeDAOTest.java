package test.DAO;

import java.util.ArrayList;
import java.util.List;

import com.DAO.RecommendedIntakeDAO;
import com.debug.Debugger;
import com.model.Gender;
import com.model.Nutrition;

public class RecommendedIntakeDAOTest {

    public static void main(String[] args) {

        List<String> failedCasesList = new ArrayList<>();
        Gender[] arrGender = new Gender[] { Gender.men, Gender.women };

        // if (testGetRecommendedIntake("energy", Gender.MEN, 10, 1) == false) {
        // failedCasesList.add("case 1");
        // }

        for (int NUTRITION_LIST_i = 0; NUTRITION_LIST_i < Nutrition.NUTRITION_LIST.size(); NUTRITION_LIST_i++) {
            for (int gender_i = 0; gender_i < arrGender.length; gender_i++) {
                for (int physicalActivityLevel = 1; physicalActivityLevel <= 3; physicalActivityLevel++) {
                    var nutritionName = Nutrition.NUTRITION_LIST.get(NUTRITION_LIST_i);
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

        for (int age = 1; age <= 100; age += 5) {
            for (int gender_i = 0; gender_i < arrGender.length; gender_i++) {
                for (int physicalActivityLevel = 1; physicalActivityLevel <= 3; physicalActivityLevel++) {
                    if (testMergeRecommendedIntake(arrGender[gender_i], age, physicalActivityLevel)) {
                        failedCasesList.add(String.format(
                                "gender=%s, age=%d, physicalActivityLevel=%d",
                                arrGender[gender_i], age, physicalActivityLevel));
                    }
                }
            }
        }

        showTestResult(failedCasesList);
    }

    private static void showTestResult(List<String> failedCasesList) {

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
        Debugger.writeObjectToFile(recommendedIntake, "testGetRecommendedIntake");
        return recommendedIntake != null;
    }

    private static Boolean testMergeRecommendedIntake(Gender gender, int age, int physicalActivityLevel) {

        RecommendedIntakeDAO recommendedIntakeDAO = new RecommendedIntakeDAO();
        var recommendedIntakeMap = recommendedIntakeDAO.mergeRecommendedIntake(gender, age, physicalActivityLevel);
        Debugger.writeObjectToFile(recommendedIntakeMap, "testMergeRecommendedIntake");
        return recommendedIntakeMap != null;
    }
}
