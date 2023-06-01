package com.model;

import com.DAO.AccountDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class UserInfoLogic {

    public boolean setUserInfo(HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute("username");

        AccountDAO accountDAO = new AccountDAO();
        var account = accountDAO.getAccountByUserName(username);

        session.setAttribute("user_info_map", account.getUserInfoMap());

        return true;
    }

    public boolean registerModifiedUserInfo(HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute("username");
        String email = request.getParameter("email");
        double height = Double.parseDouble(request.getParameter("height"));
        double weight = Double.parseDouble(request.getParameter("weight"));

        AccountDAO accountDAO = new AccountDAO();
        var result = accountDAO.changeUpdatableUserInformation(username, email, height, weight);

        return result;
    }
}
