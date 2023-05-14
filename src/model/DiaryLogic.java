package model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DAO.UserIntakeDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class DiaryLogic {

    public Boolean setDietInfoToSession(HttpServletRequest request) {

        String username = request.getParameter("username");
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String day = request.getParameter("day");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(year + "-" + month + "-" + day, formatter);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Map<String, Double> nutritionalIntake = calcTotalNutritionIntake(username, date);
        List<UserIntake> dietList = getDietList(username, date);

        HttpSession session = request.getSession(true);
        session.setAttribute("year", year);
        session.setAttribute("month", month);
        session.setAttribute("day", day);
        session.setAttribute("nutritional_intake", nutritionalIntake);
        session.setAttribute("diet_list", dietList);

        return true;
    }

    private Map<String, Double> calcTotalNutritionIntake(String username, Date date) {

        UserIntakeDAO userIntakeDAO = new UserIntakeDAO();
        Map<String, Double> totalNutritionalIntakeMap = new HashMap<>();

        for (int i = 0; i < NutritionList.NUTRITION_LIST.size(); i++) {
            String nutritionName = NutritionList.NUTRITION_LIST.get(i);
            totalNutritionalIntakeMap.put(nutritionName, 0.0);
        }

        try {

            int numOfDiets = userIntakeDAO.getNumOfDietsRegistered(username, date);
            for (int i = 0; i < numOfDiets; i++) {

            }

        } catch (Exception e) {

            // e.printStackTrace();
        }

        return totalNutritionalIntakeMap;
    }

    private List<UserIntake> getDietList(String username, Date date) {

        UserIntakeDAO userIntakeDAO = new UserIntakeDAO();

    }

}
