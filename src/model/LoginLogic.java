package model;

import DAO.AccountDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LoginLogic {

    public Boolean execute(HttpServletRequest req) {

        return setUserInfoToSession(req);
    }

    private Boolean setUserInfoToSession(HttpServletRequest req) {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Login login = new Login(username, password);
        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.findByLogin(login);

        if (account == null) {
            return false;
        }

        String genderString = account.getGender().getGenderString();
        int age = account.calcAge();
        int activityLevelNumber = account.getActivityLevel().getRegistrationNumber();

        HttpSession session = req.getSession(true);
        session.setAttribute("username", account.getUsername());
        session.setAttribute("gender", genderString);
        session.setAttribute("age", age);
        session.setAttribute("activity_level", activityLevelNumber);

        return true;
    }

}
