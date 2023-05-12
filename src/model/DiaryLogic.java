package model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import DAO.AccountDAO;

public class DiaryLogic {

    public Map<String, Double> execute(String username, Date intakeDietDate) {

        AccountDAO accountDAO = new AccountDAO();
        Map<String, Double> nutritionalIntake = accountDAO.aggregateNutritionalIntake(username, intakeDietDate);
        return nutritionalIntake;
    }

}
