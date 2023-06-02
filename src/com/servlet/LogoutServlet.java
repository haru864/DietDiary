package com.servlet;

import java.io.IOException;

import com.model.LogoutLogic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    private final String logoutJsp = "/WEB-INF/jsp/logout.jsp";
    private final String unknownErrorJsp = "/WEB-INF/jsp/unknown_error.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LogoutLogic logoutLogic = new LogoutLogic();
        logoutLogic.execute(req);
        req.getRequestDispatcher(logoutJsp).forward(req, resp);
    }

}
