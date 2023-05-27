package com.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.DAO.RecommendedIntakeDAO;
import com.DAO.UserIntakeDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class DiaryLogic {

    public Boolean execute(HttpServletRequest request) {

        return setDietInfoToSession(request);
    }

    public Boolean setDietInfoToSession(HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute("username");
        String genderString = (String) session.getAttribute("gender");
        int age = (Integer) session.getAttribute("age");
        int activityLevelNumber = (Integer) session.getAttribute("activity_level");
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String day = request.getParameter("day");

        if (username == null || year == null || month == null || day == null) {
            return false;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        LocalDate localDate = LocalDate.parse(year + "-" + month + "-" + day, formatter);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Map<String, Double> totalNutritionalIntakeMap = calcTotalNutritionIntake(username, date);
        List<Map<String, Double>> dietList = getDietList(username, date);
        Map<String, Double> recommendedIntakeMap = getRecommendedIntakeMap(age, Gender.valueOf(genderString),
                activityLevelNumber);

        session.setAttribute("year", year);
        session.setAttribute("month", month);
        session.setAttribute("day", day);
        session.setAttribute("total_nutritional_intake_map", totalNutritionalIntakeMap);
        session.setAttribute("diet_list", dietList);
        session.setAttribute("recommended_intake_map", recommendedIntakeMap);

        return true;
    }

    public Map<String, Double> calcTotalNutritionIntake(String username, Date date) {

        UserIntakeDAO userIntakeDAO = new UserIntakeDAO();
        Map<String, Double> totalNutritionalIntakeMap = new HashMap<>();

        for (int i = 0; i < Nutrition.NUTRITION_LIST.size(); i++) {
            String nutritionName = Nutrition.NUTRITION_LIST.get(i);
            totalNutritionalIntakeMap.put(nutritionName, 0.0);
        }

        try {

            int numOfDiets = userIntakeDAO.getNumOfDietsRegistered(username, date);
            if (numOfDiets == 0) {
                return null;
            }

            for (int dietNumber = 1; dietNumber <= numOfDiets; dietNumber++) {
                var nutritionalIntakeMap = userIntakeDAO.getSpecifiedNumberDiet(username, date, dietNumber);
                for (int i = 0; i < Nutrition.NUTRITION_LIST.size(); i++) {
                    String nutritionName = Nutrition.NUTRITION_LIST.get(i);
                    totalNutritionalIntakeMap.put(nutritionName,
                            totalNutritionalIntakeMap.getOrDefault(nutritionName, 0.0)
                                    + nutritionalIntakeMap.get(nutritionName));
                }
            }

        } catch (Exception e) {

            // e.printStackTrace();
        }

        return totalNutritionalIntakeMap;
    }

    public List<Map<String, Double>> getDietList(String username, Date date) {

        List<Map<String, Double>> dietList = new ArrayList<>();
        UserIntakeDAO userIntakeDAO = new UserIntakeDAO();

        try {

            int numOfDiets = userIntakeDAO.getNumOfDietsRegistered(username, date);
            if (numOfDiets == 0) {
                return null;
            }

            for (int dietNumber = 1; dietNumber <= numOfDiets; dietNumber++) {
                var nutritionalIntakeMap = userIntakeDAO.getSpecifiedNumberDiet(username, date, dietNumber);
                dietList.add(nutritionalIntakeMap);
            }

        } catch (Exception e) {

            // e.printStackTrace();
        }

        return dietList;
    }

    public Map<String, Double> getRecommendedIntakeMap(int age, Gender gender, int physicalActivityLevel) {

        RecommendedIntakeDAO recommendedIntakeDAO = new RecommendedIntakeDAO();
        Map<String, Double> recommendedIntakeMap = recommendedIntakeDAO.mergeRecommendedIntake(gender, age,
                physicalActivityLevel);
        return recommendedIntakeMap;
    }

}
