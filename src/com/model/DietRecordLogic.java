package com.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.DAO.FoodGroupDAO;
import com.DAO.NutritionFactsDAO;

import jakarta.servlet.http.HttpServletRequest;

public class DietRecordLogic {

    public Boolean setSelectElement(HttpServletRequest request) {

        Map<String, Integer> foodGroupMap = listFoodGroup();
        Map<Integer, List<String>> foodNameMap = listFoodName();

        request.setAttribute("food_group_map", foodGroupMap);
        request.setAttribute("food_name_map", foodNameMap);

        return true;
    }

    public Boolean registerDiet(HttpServletRequest request) {

        // pending

        return true;
    }

    private Map<String, Integer> listFoodGroup() {

        FoodGroupDAO foodGroupDAO = new FoodGroupDAO();
        List<FoodGroup> foodGroupList = foodGroupDAO.listFoodGroups();

        Map<String, Integer> foodGroupMap = new HashMap<>();
        for (var foodGroup : foodGroupList) {
            foodGroupMap.put(foodGroup.foodGroupName, foodGroup.foodGroupNumber);
        }

        if (foodGroupMap == null || foodGroupMap.size() == 0) {
            return null;
        }

        return foodGroupMap;
    }

    private Map<Integer, List<String>> listFoodName() {

        NutritionFactsDAO nutritionFactsDAO = new NutritionFactsDAO();
        Map<Integer, List<String>> foodNameMap = new HashMap<>();

        FoodGroupDAO foodGroupDAO = new FoodGroupDAO();
        var foodGroupList = foodGroupDAO.listFoodGroups();

        for (var foodGroup : foodGroupList) {
            int foodGroupNumber = foodGroup.foodGroupNumber;
            var list = nutritionFactsDAO.listFoodNameByFoodGroupNumber(foodGroupNumber);
            foodNameMap.put(foodGroupNumber, list);
        }

        if (foodNameMap.size() == 0) {
            return null;
        }

        return foodNameMap;
    }

}
