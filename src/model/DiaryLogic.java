package model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

import DAO.AccountDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class DiaryLogic {

    public Boolean execute(HttpServletRequest request) {

        String username = request.getParameter("username");
            String year = request.getParameter("year");
            String month = request.getParameter("month");
            String day = request.getParameter("day");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(year + "-" + month + "-" + day, formatter);
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            
            Map<String, Double> nutritionalIntake = diaryLogic.execute(request, username, date);

            HttpSession session = request.getSession(true);
            session.setAttribute("year", year);
            session.setAttribute("month", month);
            session.setAttribute("day", day);
            session.setAttribute("nutritional_intake", nutritionalIntake);
    }

}
