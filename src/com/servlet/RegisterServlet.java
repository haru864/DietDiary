package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Account;
import com.model.CheckAccountLogic;
import com.model.Gender;
import com.model.RegisterLogic;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    private final Logger logger = LoggerFactory.getLogger(RegisterServlet.class);
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
        logger.info("(RegisterServlet) action: " + action);

        if (action == null || (action.equals("register") == false && action.equals("validate") == false)) {
            // 共通エラー画面にフォワード
            requestDispatcher = req.getRequestDispatcher(unknownErrorJsp);
            requestDispatcher.forward(req, resp);
            return;
        }

        if (action.equals("register")) {

            Account validAccount = (Account) session.getAttribute("validAccount");

            if (validAccount == null) {
                requestDispatcher = req.getRequestDispatcher(registerErrorJsp);
                requestDispatcher.forward(req, resp);
                return;
            }

            try {
                // データベースに登録
                RegisterLogic registerLogic = new RegisterLogic();
                Boolean isRegisterSuccess = registerLogic.execute(validAccount);

                if (isRegisterSuccess == false) {
                    var list = new ArrayList<>();
                    list.add("登録済みのユーザー名です。");
                    session.setAttribute("errorMessageList", list);
                    requestDispatcher = req.getRequestDispatcher(registerErrorJsp);
                    requestDispatcher.forward(req, resp);
                    return;
                }

                session.setAttribute("username", validAccount.getUsername());

                requestDispatcher = req.getRequestDispatcher(mypageJsp);
                requestDispatcher.forward(req, resp);

                return;

            } catch (Exception e) {

                logger.info(e.getMessage());
                requestDispatcher = req.getRequestDispatcher(registerErrorJsp);
                requestDispatcher.forward(req, resp);
                return;
            }

        } else if (action.equals("validate")) {

            try {
                // ユーザー登録画面の入力値を受け取り、Accountフィールドに設定する
                String username = req.getParameter("username");
                String password = req.getParameter("password");
                String email = req.getParameter("email");
                Date lastLoginDate = new Date();
                String genderString = req.getParameter("gender");
                if (genderString == null ||
                        (genderString.equals("men") == false && genderString.equals("women") == false)) {
                    throw new Exception("invalid gender from register.jsp");
                }
                Gender gender = Gender.valueOf(genderString);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String birthString = req.getParameter("birth");
                Date birth = null;
                if (birthString != null) {
                    birth = sdf.parse(birthString);
                }
                double height = Double.parseDouble(req.getParameter("height"));
                double weight = Double.parseDouble(req.getParameter("weight"));

                // Accountオブジェクトを作成、フィールドの整合性をチェック
                Account account = new Account(username, password, email, lastLoginDate, gender, birth, height, weight);
                CheckAccountLogic checkAccountLogic = new CheckAccountLogic();
                List<String> errorMessageList = checkAccountLogic.execute(account);

                if (errorMessageList == null) {
                    session.setAttribute("validAccount", account);
                }
                logger.info("(Exception in RegisterServlet) errorMessageList: " + errorMessageList);

                ObjectMapper mapper = new ObjectMapper();
                String errorMessageListJson = mapper.writeValueAsString(errorMessageList);

                resp.setContentType("application/json");

                try (PrintWriter out = resp.getWriter()) {
                    out.print(errorMessageListJson);
                } catch (Exception e) {
                    logger.info("(Exception in RegisterServlet) errorMessageListJson: " + errorMessageListJson);
                    requestDispatcher = req.getRequestDispatcher(unknownErrorJsp);
                    requestDispatcher.forward(req, resp);
                }

            } catch (Exception e) {

                logger.info(e.getMessage());
                List<String> exceptionMessage = new ArrayList<>() {
                    {
                        add(e.getMessage());
                    }
                };

                ObjectMapper mapper = new ObjectMapper();
                String exceptionMessageJson = mapper.writeValueAsString(exceptionMessage);

                resp.setContentType("application/json");
                resp.getWriter().println(exceptionMessageJson);
            }

        }

    }

}
