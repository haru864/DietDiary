package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import model.Account;
import model.Gender;
import model.Login;

public class AccountDAO {

    private final String DB_URL = "jdbc:mysql://localhost:3306/DietDiary";
    private final String DB_USER = "admin";
    private final String DB_PASS = "4dm1n";

    public Account findByLogin(Login login) throws Exception {

        String username;
        String password;
        String email;
        Date updated;
        Gender gender;
        Date birth;
        double height;
        double weight;
        double burnedCalories;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            String SQL = "SELECT * FROM account WHERE username = ? AND password = ?";
            PreparedStatement pStmt = conn.prepareStatement(SQL);
            pStmt.setString(1, login.getUsername());
            pStmt.setString(2, login.getPassword());

        } catch (Exception e) {

            throw new Exception(e);
        }
    }
}
