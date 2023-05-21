package test.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.DAO.FoodGroupDAO;
import com.DAO.NutritionFactsDAO;
import com.debug.Debugger;

public class NutritionFactsDAOTest {

    public static void main(String[] args) {

        List<String> failedCasesList = new ArrayList<>();

        if (testListRegisteredDiets() == true) {
            System.out.println("testListRegisteredDiets ... passed");
        } else {
            System.out.println("testListRegisteredDiets ... FAILED");
            failedCasesList.add("testListRegisteredDiets failed");
        }

        if (testListFoodNameByFoodGroupNumber() == true) {
            System.out.println("testListFoodNameByFoodGroupNumber ... passed");
        } else {
            System.out.println("testListFoodNameByFoodGroupNumber ... FAILED");
            failedCasesList.add("testListFoodNameByFoodGroupNumber failed");
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

    private static Boolean testListRegisteredDiets() {

        NutritionFactsDAO nutritionFactsDAO = new NutritionFactsDAO();
        List<String> list = nutritionFactsDAO.listRegisteredDiets();
        if (list == null) {
            return false;
        }
        System.out.println(list.size());
        return list.size() != 0;
    }

    private static Boolean testListFoodNameByFoodGroupNumber() {

        FoodGroupDAO foodGroupDAO = new FoodGroupDAO();
        var foodGroupList = foodGroupDAO.listFoodGroups();

        NutritionFactsDAO nutritionFactsDAO = new NutritionFactsDAO();
        Map<Integer, List<String>> foodNameMap = new HashMap<>();

        for (var foodGroup : foodGroupList) {
            int foodGroupNumber = foodGroup.foodGroupNumber;
            var list = nutritionFactsDAO.listFoodNameByFoodGroupNumber(foodGroupNumber);
            System.out.print(foodGroupNumber + " ");
            foodNameMap.put(foodGroupNumber, list);
        }
        System.out.println();

        if (foodNameMap.size() == 0) {
            return false;
        }

        Debugger.writeObjectToFile(foodNameMap, "testListFoodNameByFoodGroupNumber");

        return true;
    }

}
