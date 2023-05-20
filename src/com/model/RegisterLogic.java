package com.model;

import com.DAO.AccountDAO;

public class RegisterLogic {

    public Boolean execute(Account account) {

        Boolean isRegisterSuccess = true;
        AccountDAO accountDAO = new AccountDAO();
        isRegisterSuccess = accountDAO.registerAccount(account);
        return isRegisterSuccess;
    }
}
