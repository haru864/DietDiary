package com.model;

import com.DAO.AccountDAO;

public class LoginLogic {

    public Boolean execute(Login login) {

        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.findByLogin(login);

        if (account == null) {
            return false;
        }
        return true;
    }

}
