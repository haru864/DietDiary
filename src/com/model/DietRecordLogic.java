package com.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.DAO.FoodGroupDAO;
import com.DAO.NutritionFactsDAO;
import com.DAO.UserIntakeDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class DietRecordLogic {

    public Boolean setSelectElement(HttpServletRequest request) {

        List<FoodGroup> foodGroupList = listFoodGroup();
        Map<Integer, List<String>> foodNameMap = listFoodName();

        request.setAttribute("food_group_list", foodGroupList);
        request.setAttribute("food_name_map", foodNameMap);

        return true;
    }

    public Boolean registerDiet(HttpServletRequest request) {

        try {

            HttpSession session = request.getSession(true);
            String userName = (String) session.getAttribute("username");
            String year = (String) session.getAttribute("year");
            String month = (String) session.getAttribute("month");
            String day = (String) session.getAttribute("day");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
            LocalDate localDate = LocalDate.parse(year + "-" + month + "-" + day, formatter);
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            int foodGroup = Integer.parseInt(request.getParameter("food_group"));
            String foodName = request.getParameter("food_name");
            double foodWeightGram = Double.parseDouble(request.getParameter("food_weight_gram"));

            UserIntakeDAO userIntakeDAO = new UserIntakeDAO();
            int lastDietNumber = userIntakeDAO.getNumOfDietsRegistered(userName, date);
            UserIntake userIntake = new UserIntake(userName, date, lastDietNumber + 1,
                    foodGroup, foodName, foodWeightGram);

            userIntakeDAO.recordNutritionalIntake(userIntake);

            return true;

        } catch (Exception e) {

            return false;
        }
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
