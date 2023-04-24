package model;

import DAO.AccountDAO;

public class LoginLogic {

    public Boolean execute(Login login) {

        Boolean isLoginSuccess = true;

        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.findByLogin(login);

        if (account == null) {
            isLoginSuccess = false;
        }

        return isLoginSuccess;
    }
}
