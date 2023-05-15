package model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DAO.UserIntakeDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class DiaryLogic {

    public Boolean setDietInfoToSession(HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute("username");
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

        session.setAttribute("year", year);
        session.setAttribute("month", month);
        session.setAttribute("day", day);
        session.setAttribute("total_nutritional_intake", totalNutritionalIntakeMap);
        session.setAttribute("diet_list", dietList);

        return true;
    }

    public Map<String, Double> calcTotalNutritionIntake(String username, Date date) {

        UserIntakeDAO userIntakeDAO = new UserIntakeDAO();
        Map<String, Double> totalNutritionalIntakeMap = new HashMap<>();

        for (int i = 0; i < NutritionList.NUTRITION_LIST.size(); i++) {
            String nutritionName = NutritionList.NUTRITION_LIST.get(i);
            totalNutritionalIntakeMap.put(nutritionName, 0.0);
        }

        try {

            int numOfDiets = userIntakeDAO.getNumOfDietsRegistered(username, date);
            for (int dietNumber = 1; dietNumber <= numOfDiets; dietNumber++) {
                var nutritionalIntakeMap = userIntakeDAO.getSpecifiedNumberDiet(username, date, dietNumber);
                for (int i = 0; i < NutritionList.NUTRITION_LIST.size(); i++) {
                    String nutritionName = NutritionList.NUTRITION_LIST.get(i);
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
            for (int dietNumber = 1; dietNumber <= numOfDiets; dietNumber++) {
                var nutritionalIntakeMap = userIntakeDAO.getSpecifiedNumberDiet(username, date, dietNumber);
                dietList.add(nutritionalIntakeMap);
            }

        } catch (Exception e) {

            // e.printStackTrace();
        }

        return dietList;
    }

}
