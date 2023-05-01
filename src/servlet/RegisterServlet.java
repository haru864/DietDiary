package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.catalina.tribes.group.interceptors.TwoPhaseCommitInterceptor.MapEntry;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.ActivityLevel;
import model.CalculateTDEELogic;
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
            } catch (Exception e) {
                requestDispatcher = req.getRequestDispatcher(registerErrorJsp);
                requestDispatcher.forward(req, resp);
            }
            double height = Double.parseDouble(req.getParameter("height"));
            double weight = Double.parseDouble(req.getParameter("weight"));
            int activityLevelNumber = Integer.parseInt(req.getParameter("activity_level"));
            ActivityLevel activityLevel = ActivityLevel.getActivityLevelFromInt(activityLevelNumber);

            CalculateTDEELogic calcTDEE = new CalculateTDEELogic();
            double TDEE = calcTDEE.execute(birth, gender, height, weight, activityLevel);

            Account account = new Account(username, password, email, updated,
                    gender, birth, height, weight,
                    activityLevel, TDEE);

            CheckAccountLogic checkAccountLogic = new CheckAccountLogic();
            HttpSession session = req.getSession(true);
            // List<Map<String, String>> errorMessageList =
            // checkAccountLogic.execute(account);
            // if (errorMessageList != null) {
            // for (var errorMessageMap : errorMessageList) {
            // for (var entry : errorMessageMap.entrySet()) {
            // session.setAttribute(entry.getKey(), entry.getValue());
            // }
            // }
            // requestDispatcher = req.getRequestDispatcher(registerErrorJsp);
            // requestDispatcher.forward(req, resp);
            // }
            var errorMessageList = checkAccountLogic.execute(account);
            if (errorMessageList != null) {
                // session.setAttribute("errorMessageList", errorMessageList);
                session.setAttribute("error", "true");
                log((String) session.getAttribute("error"));
                requestDispatcher = req.getRequestDispatcher(registerErrorJsp);
                requestDispatcher.forward(req, resp);
            }

            // RegisterLogic registerLogic = new RegisterLogic();
            // Boolean isRegisterSuccess = registerLogic.execute(account);
            // if (isRegisterSuccess == false) {
            // requestDispatcher = req.getRequestDispatcher(registerErrorJsp);
            // }

            requestDispatcher = req.getRequestDispatcher(mypageJsp);
            requestDispatcher.forward(req, resp);

        } else {

            requestDispatcher = req.getRequestDispatcher(unknownErrorJsp);
            requestDispatcher.forward(req, resp);
        }
    }
}
