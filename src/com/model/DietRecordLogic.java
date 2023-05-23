package com.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.DAO.FoodGroupDAO;
import com.DAO.NutritionFactsDAO;

import jakarta.servlet.http.HttpServletRequest;

public class DietRecordLogic {

    public Boolean setSelectElement(HttpServletRequest request) {

        List<FoodGroup> foodGroupList = listFoodGroup();
        Map<Integer, List<String>> foodNameMap = listFoodName();

        // overwrite for debug
        // foodNameMap = new HashMap<>();
        // foodNameMap.put(1, Arrays.asList("じゃがいも", "米", "パン"));
        // foodNameMap.put(2, Arrays.asList("牛肉", "鶏肉", "さかな"));

        request.setAttribute("food_group_list", foodGroupList);
        request.setAttribute("food_name_map", foodNameMap);

        return true;
    }

    public Boolean registerDiet(HttpServletRequest request) {

        // pending

        return true;
    }

    public List<FoodGroup> listFoodGroup() {

        FoodGroupDAO foodGroupDAO = new FoodGroupDAO();
        List<FoodGroup> foodGroupList = foodGroupDAO.listFoodGroups();

        if (foodGroupList == null || foodGroupList.size() == 0) {
            return null;
        }

        return foodGroupList;
    }

    public Map<Integer, List<String>> listFoodName() {

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
