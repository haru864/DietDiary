package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.startup.Catalina;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
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

        logger.info("LoginServlet doPost start");
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String[] paramValues = req.getParameterValues(paramName);
            for (int i = 0; i < paramValues.length; i++) {
                String paramValue = paramValues[i];
                logger.info("(LoginServlet) request parameter -> " + paramName + ": " + paramValue);
            }
        }

        String action = req.getParameter("action");
        RequestDispatcher requestDispatcher;

        if (action.equals("login")) {

            LoginLogic loginLogic = new LoginLogic();
            Boolean isLoginSuccess = loginLogic.execute(req);

            if (isLoginSuccess) {
                requestDispatcher = req.getRequestDispatcher(mypageJsp);
            } else {
                requestDispatcher = req.getRequestDispatcher(loginErrorJsp);
            }
            requestDispatcher.forward(req, resp);

        } else if (action.equals("preLogin")) {

            LoginLogic loginLogic = new LoginLogic();
            Boolean isLoginSuccess = loginLogic.execute(req);

            Map<String, String> loginResult = new HashMap<>();
            if (isLoginSuccess) {
                loginResult.put("result", "success");
            } else {
                loginResult.put("result", "failure");
            }

            ObjectMapper mapper = new ObjectMapper();
            String loginResultJson = mapper.writeValueAsString(loginResult);

            try (PrintWriter out = resp.getWriter()) {
                out.print(loginResultJson);
            } catch (Exception e) {
                logger.info("(ERROR@LoginServlet) loginResultJson: " + loginResultJson);
                requestDispatcher = req.getRequestDispatcher(unknownErrorJsp);
                requestDispatcher.forward(req, resp);
            }

        } else {

            requestDispatcher = req.getRequestDispatcher(unknownErrorJsp);
            requestDispatcher.forward(req, resp);
        }
    }

}
