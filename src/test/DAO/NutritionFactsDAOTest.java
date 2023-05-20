package test.DAO;

import java.util.ArrayList;
import java.util.List;

import com.DAO.NutritionFactsDAO;

public class NutritionFactsDAOTest {

    public static void main(String[] args) {

        List<String> failedCasesList = new ArrayList<>();

        if (testListRegisteredDiets() == true) {
            System.out.println("testListRegisteredDiets ... passed");
        } else {
            System.out.println("testListRegisteredDiets ... FAILED");
            failedCasesList.add("testListRegisteredDiets: nutrition_facts has no record?");
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

    private static Boolean testListRegisteredDiets() {

        NutritionFactsDAO nutritionFactsDAO = new NutritionFactsDAO();
        List<String> list = nutritionFactsDAO.listRegisteredDiets();
        if (list == null) {
            return false;
        }
        System.out.println(list.size());
        return list.size() != 0;
    }

}
