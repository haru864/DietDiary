package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import model.ActivityLevel;
import model.CalculateCaloriesLogic;
import model.CheckAccountLogic;
import model.Gender;
import model.RegisterLogic;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    private final String registerJsp = "/WEB-INF/jsp/register.jsp";
    private final String mypageJsp = "/WEB-INF/jsp/mypage.jsp";
    private final String registerErrorJsp = "/WEB-INF/jsp/register_error.jsp";
    private final String unknownErrorJsp = "/WEB-INF/jsp/unknown_error.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        RequestDispatcher requestDispatcher;

        if (action.equals("display")) {
            requestDispatcher = req.getRequestDispatcher(registerJsp);
        } else {
            requestDispatcher = req.getRequestDispatcher(unknownErrorJsp);
        }

        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        RequestDispatcher requestDispatcher;

        if (action.equals("register")) {

            // Accountフィールド
            // 1.username ユーザー入力値を設定
            // 2.password ユーザー入力値を設定
            // 3.email ユーザー入力値を設定
            // 4.updated システム日付を設定
            // 5.gender ユーザー入力値を設定
            // 6.birth ユーザー入力値を設定
            // 7.height ユーザー入力値を設定
            // 8.weight ユーザー入力値を設定
            // 9.activityLevel ユーザー入力値を設定
            // 10.totalDailyEnergyExpenditure CalculateCaloriesLogicで算出
            String username = req.getParameter("action");
            String password = req.getParameter("action");
            String email = req.getParameter("action");
            Date updated = new Date();
            Gender gender = req.getParameter("action").equals("men") ? Gender.MEN : Gender.WOMEN;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date birth = null;
            try {
                birth = sdf.parse((String) req.getAttribute("birth"));
            } catch (ParseException e) {
                requestDispatcher = req.getRequestDispatcher(registerErrorJsp);
            }
            double height = Double.parseDouble(req.getParameter("height"));
            double weight = Double.parseDouble(req.getParameter("weight"));
            int activityLevelNumber = Integer.parseInt(req.getParameter("activity_level"));

            ActivityLevel activityLevel = ActivityLevel.ALMOST_NO_EXERCISE;
            for (ActivityLevel currentLevel : ActivityLevel.values()) {
                if (activityLevelNumber == currentLevel.getRegistrationNumber()) {
                    activityLevel = currentLevel;
                }
            }

            CalculateCaloriesLogic calcCalories = new CalculateCaloriesLogic();
            double totalDailyEnergyExpenditure = calcCalories.execute(birth, gender, height, weight, activityLevel);

            Account account = new Account(username, password, email, updated,
                    gender, birth, height, weight,
                    activityLevel, totalDailyEnergyExpenditure);
            // CheckAccountLogic checkAccountLogic = new CheckAccountLogic();
            // Boolean isAccountValid = checkAccountLogic.execute(account);
            // if (isAccountValid == false) {
            // requestDispatcher = req.getRequestDispatcher(registerErrorJsp);
            // }

            // RegisterLogic registerLogic = new RegisterLogic();
            // Boolean isRegisterSuccess = registerLogic.execute(account);
            // if (isRegisterSuccess == false) {
            // requestDispatcher = req.getRequestDispatcher(registerErrorJsp);
            // }

            requestDispatcher = req.getRequestDispatcher(mypageJsp);

        } else {

            requestDispatcher = req.getRequestDispatcher(unknownErrorJsp);
        }

        requestDispatcher.forward(req, resp);
    }
}
