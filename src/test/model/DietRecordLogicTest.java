package test.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.model.DietRecordLogic;
import com.model.FoodGroup;

public class DietRecordLogicTest {

    public static void main(String[] args) {

        List<String> failedCasesList = new ArrayList<>();

        if (testListFoodGroup() == true) {
            System.out.println("testListFoodGroup ... passed");
        } else {
            System.out.println("testListFoodGroup ... FAILED");
            failedCasesList.add("testListFoodGroup: case_01-01 failed");
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

    private static boolean testListFoodGroup() {

        DietRecordLogic dietRecordLogic = new DietRecordLogic();
        List<FoodGroup> foodGroupList = dietRecordLogic.listFoodGroup();

        foodGroupList.forEach((foodGroup) -> {
            System.out.print(foodGroup.foodGroupNumber + " ");
        });
        System.out.println();

        return foodGroupList != null;
    }

}
