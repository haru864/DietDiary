package com.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.model.Account;
import com.model.ActivityLevel;
import com.model.CheckAccountLogic;
import com.model.Gender;
import com.model.RegisterLogic;

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

        if (action != null && action.equals("display")) {
            requestDispatcher = req.getRequestDispatcher(registerJsp);
        } else {
            requestDispatcher = req.getRequestDispatcher(unknownErrorJsp);
        }

        requestDispatcher.forward(req, resp);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        RequestDispatcher requestDispatcher;
        HttpSession session = req.getSession(true);

        if (action != null && action.equals("register")) {

            try {

                // ユーザー登録画面の入力値を受け取り、Accountフィールドに設定する
                // 1.username ユーザー入力値を設定
                // 2.password ユーザー入力値を設定
                // 3.email ユーザー入力値を設定
                // 4.lastUpdated システム日付を設定
                // 5.gender ユーザー入力値を設定
                // 6.birth ユーザー入力値を設定
                // 7.height ユーザー入力値を設定
                // 8.weight ユーザー入力値を設定
                // 9.activityLevel ユーザー入力値を設定
                // 10.TDEE CalculateCaloriesLogicで算出
                String username = req.getParameter("username");
                String password = req.getParameter("password");
                String email = req.getParameter("email");
                Date updated = new Date();
                String genderString = req.getParameter("gender");
                Gender gender = null;
                if (genderString != null) {
                    if (genderString.equals("men")) {
                        gender = Gender.men;
                    } else if (genderString.equals("women")) {
                        gender = Gender.women;
                    }
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String birthString = req.getParameter("birth");
                Date birth = null;
                if (birthString != null) {
                    birth = sdf.parse(birthString);
                }
                double height = Double.parseDouble(req.getParameter("height"));
                double weight = Double.parseDouble(req.getParameter("weight"));
                int activityLevelNumber = Integer.parseInt(req.getParameter("activity_level"));
                ActivityLevel activityLevel = ActivityLevel.getActivityLevelFromInt(activityLevelNumber);
                log("username=" + username);
                log("password=" + password);
                log("email=" + email);
                log("updated=" + updated);
                log("gender=" + gender);
                log("birth=" + birth);
                log("height=" + height);
                log("weight=" + weight);
                log("activityLevelNumber=" + activityLevelNumber);
                log("activityLevel=" + activityLevel);

                // Accountオブジェクトを作成、フィールドの整合性をチェック
                Account account = new Account(username, password, email, updated,
                        gender, birth, height, weight,
                        activityLevel);
                CheckAccountLogic checkAccountLogic = new CheckAccountLogic();
                var errorMessageList = checkAccountLogic.execute(account);
                if (errorMessageList != null) {
                    session.setAttribute("errorMessageList", errorMessageList);
                    requestDispatcher = req.getRequestDispatcher(registerErrorJsp);
                    requestDispatcher.forward(req, resp);
                    return;
                }

                // データベースに登録
                RegisterLogic registerLogic = new RegisterLogic();
                Boolean isRegisterSuccess = registerLogic.execute(account);
                if (isRegisterSuccess == false) {
                    var list = new ArrayList<>();
                    list.add("登録済みのユーザー名です。");
                    session.setAttribute("errorMessageList", list);
                    requestDispatcher = req.getRequestDispatcher(registerErrorJsp);
                    requestDispatcher.forward(req, resp);
                    return;
                }
                session.setAttribute("username", account.getUsername());

                // マイページ画面にフォワード
                requestDispatcher = req.getRequestDispatcher(mypageJsp);
                requestDispatcher.forward(req, resp);
                return;

            } catch (Exception e) {

                log(e.getMessage());

                // 登録エラー画面にフォワード
                requestDispatcher = req.getRequestDispatcher(registerErrorJsp);
                requestDispatcher.forward(req, resp);
                return;
            }

        } else {

            // その他エラー画面にフォワード
            requestDispatcher = req.getRequestDispatcher(unknownErrorJsp);
            requestDispatcher.forward(req, resp);
            return;
        }
    }
}
