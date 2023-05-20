package test.DAO;

import java.util.ArrayList;
import java.util.List;

import com.DAO.FoodGroupDAO;
import com.model.FoodGroup;

public class FoodGroupDAOTest {

    public static void main(String[] args) {

        List<String> failedCasesList = new ArrayList<>();

        if (testListFoodGroups() == true) {
            System.out.println("testListFoodGroups ... passed");
        } else {
            System.out.println("testListFoodGroups ... FAILED");
            failedCasesList.add("testListFoodGroups: food_groups has no record?");
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

    private static Boolean testListFoodGroups() {

        FoodGroupDAO foodGroupDAO = new FoodGroupDAO();
        List<FoodGroup> list = foodGroupDAO.listFoodGroups();
        if (list == null) {
            return false;
        }
        System.out.println(list.size());
        return list.size() != 0;
    }
}
