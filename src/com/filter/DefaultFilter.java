package com.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class DefaultFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(DefaultFilter.class);
    private final String unknownErrorJsp = "/WEB-INF/jsp/unknown_error.jsp";
    private final List<String> NO_LOGIN_REQUIRED = new ArrayList<>();

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        NO_LOGIN_REQUIRED.add("WelcomeServlet");
        NO_LOGIN_REQUIRED.add("CSS/welcome.css");
        NO_LOGIN_REQUIRED.add("img/salad_bowl.jpg");
        NO_LOGIN_REQUIRED.add("LoginServlet");
        NO_LOGIN_REQUIRED.add("CSS/login.css");
        NO_LOGIN_REQUIRED.add("JavaScript/login.js");
        NO_LOGIN_REQUIRED.add("RegisterServlet");
        NO_LOGIN_REQUIRED.add("CSS/register.css");
        NO_LOGIN_REQUIRED.add("JavaScript/register.js");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String referer = httpServletRequest.getHeader("REFERER");
        String dest = httpServletRequest.getServletPath().substring(1);
        logger.info("referer: " + referer + ", dest: " + dest);

        if (NO_LOGIN_REQUIRED.contains(dest)) {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = httpServletRequest.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher(unknownErrorJsp);
            requestDispatcher.forward(request, response);
            return;
        }

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
        return;
    }

}
