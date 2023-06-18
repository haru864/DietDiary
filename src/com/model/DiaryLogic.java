package com.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.DAO.AccountDAO;
import com.DAO.RecommendedIntakeDAO;
import com.DAO.UserIntakeDAO;
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

        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.getAccountByUserName(username);
        if (account == null) {
            return false;
        }
        String genderString = account.getGender().getGenderString();
        int age = account.calcAge();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        LocalDate localDate = LocalDate.parse(year + "-" + month + "-" + day, formatter);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<UserIntakeNutrition> userIntakeNutritionList = listDietAndNutritionOnSpecifiedDate(username, date);
        // 活動レベルは仮設定
        Map<String, Double> recommendedIntakeMap = getRecommendedIntakeMap(age, Gender.valueOf(genderString), 1);

        session.setAttribute("age", age);
        session.setAttribute("year", year);
        session.setAttribute("month", month);
        session.setAttribute("day", day);
        session.setAttribute("user_intake_nutrition_list", userIntakeNutritionList);
        session.setAttribute("recommended_intake_map", recommendedIntakeMap);

        return true;
    }

    public List<UserIntakeNutrition> listDietAndNutritionOnSpecifiedDate(String username, Date date) {

        UserIntakeDAO userIntakeDAO = new UserIntakeDAO();
        List<UserIntakeNutrition> userIntakeNutritionList = new ArrayList<>();

        try {

            int numOfDiets = userIntakeDAO.getNumOfDietsRegistered(username, date);
            if (numOfDiets == 0) {
                return null;
            }

            for (int dietNumber = 1; dietNumber <= numOfDiets; dietNumber++) {
                var userIntake = userIntakeDAO.getSpecifiedUserIntake(username, date, dietNumber);
                UserIntakeNutrition userIntakeNutrition = new UserIntakeNutrition(userIntake);
                userIntakeNutritionList.add(userIntakeNutrition);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return userIntakeNutritionList;
    }

    public Map<String, Double> getRecommendedIntakeMap(int age, Gender gender, int physicalActivityLevel) {

        RecommendedIntakeDAO recommendedIntakeDAO = new RecommendedIntakeDAO();
        Map<String, Double> recommendedIntakeMap = recommendedIntakeDAO.mergeRecommendedIntake(gender, age,
                physicalActivityLevel);
        return recommendedIntakeMap;
    }

}
