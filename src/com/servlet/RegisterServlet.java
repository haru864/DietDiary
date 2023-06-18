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
import com.model.Gender;
import com.model.RegisterLogic;
import com.model.ValidateAccountLogic;

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

        if (action.equals("validate")) {

            // ユーザー登録画面の入力値を受け取り、Accountフィールドに設定する
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String email = req.getParameter("email");
            Date lastLoginDate = new Date();
            Gender gender = null;
            try {
                gender = Gender.valueOf(req.getParameter("gender"));
            } catch (Exception e) {
                logger.info("(RegisterServlet.doPost)" + e.getMessage());
            }
            Date birth = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String birthString = req.getParameter("birth");
                birth = sdf.parse(birthString);
            } catch (Exception e) {
                logger.info("(RegisterServlet.doPost)" + e.getMessage());
            }
            double height = 0.0;
            try {
                height = Double.parseDouble(req.getParameter("height"));
            } catch (Exception e) {
                logger.info("(RegisterServlet.doPost)" + e.getMessage());
            }
            double weight = 0.0;
            try {
                weight = Double.parseDouble(req.getParameter("weight"));
            } catch (Exception e) {
                logger.info("(RegisterServlet.doPost)" + e.getMessage());
            }

            // Accountオブジェクトを作成、フィールドの整合性をチェック
            Account account = new Account(username, password, email, lastLoginDate, gender, birth, height, weight);
            ValidateAccountLogic validateAccountLogic = new ValidateAccountLogic();
            List<String> errorMessageList = validateAccountLogic.execute(account);
            logger.info("(RegisterServlet)" + errorMessageList);

            ObjectMapper mapper = new ObjectMapper();
            resp.setContentType("application/json");

            if (errorMessageList.size() > 0) {
                String errorMessageListJson = mapper.writeValueAsString(errorMessageList);
                try (PrintWriter out = resp.getWriter()) {
                    out.print(errorMessageListJson);
                } catch (Exception e) {
                    logger.info("(RegisterServlet.doPost) errorMessageListJson: " + errorMessageListJson);
                    requestDispatcher = req.getRequestDispatcher(unknownErrorJsp);
                    requestDispatcher.forward(req, resp);
                }
            }

            session.setAttribute("validAccount", account);
            String exceptionMessageJson = mapper.writeValueAsString(new ArrayList<>());
            resp.getWriter().println(exceptionMessageJson);

        } else if (action.equals("register")) {

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
                    throw new Exception("ユーザー登録に失敗しました。\nエラーが何度も発生する場合は管理者にお問い合わせください。");
                }

                session.setAttribute("username", validAccount.getUsername());

                requestDispatcher = req.getRequestDispatcher(mypageJsp);
                requestDispatcher.forward(req, resp);

                return;

            } catch (Exception e) {

                logger.info(e.getMessage());

                var list = new ArrayList<>();
                list.add(e.getMessage());
                session.setAttribute("errorMessageList", list);

                requestDispatcher = req.getRequestDispatcher(registerErrorJsp);
                requestDispatcher.forward(req, resp);
                return;

            } finally {

                session.removeAttribute("validAccount");
            }

        }

    }

}
