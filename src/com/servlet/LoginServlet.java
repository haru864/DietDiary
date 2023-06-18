package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

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
import com.model.Login;
import com.model.LoginLogic;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    Logger logger = LoggerFactory.getLogger(LoginServlet.class);
    private final String loginJsp = "/WEB-INF/jsp/login.jsp";
    private final String mypageJsp = "/WEB-INF/jsp/mypage.jsp";
    private final String loginErrorJsp = "/WEB-INF/jsp/login_error.jsp";
    private final String unknownErrorJsp = "/WEB-INF/jsp/unknown_error.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        RequestDispatcher requestDispatcher;

        if (action.equals("display")) {
            requestDispatcher = req.getRequestDispatcher(loginJsp);
        } else {
            requestDispatcher = req.getRequestDispatcher(unknownErrorJsp);
        }
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        RequestDispatcher requestDispatcher;
        HttpSession session = req.getSession(true);

        if (action == null || (action.equals("login") == false && action.equals("transition") == false)) {
            // 共通エラー画面にフォワード
            requestDispatcher = req.getRequestDispatcher(unknownErrorJsp);
            requestDispatcher.forward(req, resp);
            return;
        }

        if (action.equals("login")) {

            Map<String, String> loginResult = new HashMap<>();

            try {

                String username = req.getParameter("username");
                String password = req.getParameter("password");
                Login login = new Login(username, password);

                LoginLogic loginLogic = new LoginLogic();
                Boolean isLoginSuccess = loginLogic.execute(login);

                if (isLoginSuccess) {
                    loginResult.put("result", "success");
                    session.setAttribute("loginResult", "success");
                } else {
                    throw new Exception("(LoginServlet.doPost) login failed");
                }

            } catch (Exception e) {

                logger.info(e.getMessage());
                loginResult.clear();
                loginResult.put("result", "failure");
            }

            ObjectMapper mapper = new ObjectMapper();
            String loginResultJson = mapper.writeValueAsString(loginResult);

            try (PrintWriter out = resp.getWriter()) {
                out.print(loginResultJson);
            } catch (Exception e) {
                logger.info("(LoginServlet.doPost) loginResultJson: " + loginResultJson);
                requestDispatcher = req.getRequestDispatcher(unknownErrorJsp);
                requestDispatcher.forward(req, resp);
            }

        } else if (action.equals("transition")) {

            String loginResult = (String) session.getAttribute("loginResult");
            String username = req.getParameter("username");

            if (loginResult == null
                    || session.getAttribute("loginResult").equals("success") == false) {
                requestDispatcher = req.getRequestDispatcher(loginErrorJsp);
            } else {
                requestDispatcher = req.getRequestDispatcher(mypageJsp);
            }

            session.setAttribute("username", username);

            requestDispatcher.forward(req, resp);
        }

    }

}
