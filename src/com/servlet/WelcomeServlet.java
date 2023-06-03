package com.servlet;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/WelcomeServlet")
public class WelcomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String path = new File(".").getAbsoluteFile().getParent();
        File dir = new File(path);
        String[] fileList = dir.list();
        URL location = WelcomeServlet.class.getProtectionDomain().getCodeSource().getLocation();
        // log("WelcomeServlet CurrentDir: " + location.toString());
        // log("WelcomeServlet filesLocated: " + Arrays.asList(fileList));
        Logger logger = LoggerFactory.getLogger(WelcomeServlet.class);
        logger.info("WelcomeServlet debug message: " + Arrays.asList(fileList));

        String welcomeJsp = "/WEB-INF/jsp/welcome.jsp";
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(welcomeJsp);
        requestDispatcher.forward(req, resp);
    }
}
