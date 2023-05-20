package com.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.model.LoginLogic;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

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

        if (action.equals("login")) {

            LoginLogic loginLogic = new LoginLogic();
            Boolean isLoginSuccess = loginLogic.execute(req);

            if (isLoginSuccess) {
                requestDispatcher = req.getRequestDispatcher(mypageJsp);
            } else {
                requestDispatcher = req.getRequestDispatcher(loginErrorJsp);
            }
            requestDispatcher.forward(req, resp);

        } else {

            requestDispatcher = req.getRequestDispatcher(unknownErrorJsp);
            requestDispatcher.forward(req, resp);
        }
    }

}