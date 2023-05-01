package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Account;
import model.ActivityLevel;
import model.Gender;
import model.Login;

public class AccountDAO {

    private final String DB_URL = "jdbc:mysql://localhost:3306/DietDiary";
    private final String DB_USER = "admin";
    private final String DB_PASS = "4dm1n";

    public Account findByLogin(Login login) {

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            String SQL = "SELECT * FROM account WHERE username = ? AND password = ?";
            PreparedStatement pStmt = conn.prepareStatement(SQL);
            pStmt.setString(1, login.getUsername());
            pStmt.setString(2, login.getPassword());

            ResultSet rs = pStmt.executeQuery();
            rs.next();

            String username = rs.getString("username");
            String password = rs.getString("password");
            String email = rs.getString("email");
            Date updated = rs.getDate("updated");
            Gender gender = rs.getString("gender").equals("men") ? Gender.MEN : Gender.WOMEN;
            Date birth = rs.getDate("birth");
            double height = rs.getDouble("height");
            double weight = rs.getDouble("weight");
            int activityLevelNumber = rs.getInt("activity_level");
            ActivityLevel activityLevel = ActivityLevel.getActivityLevelFromInt(activityLevelNumber);
            double totalDailyEnergyExpenditure = rs.getDouble("total_daily_energy_expenditure");

            Account account = new Account(username, password, email,
                    updated, gender, birth,
                    height, weight, activityLevel, totalDailyEnergyExpenditure);

            updateLastLoginDate(login);

            return account;

        } catch (Exception e) {

            return null;
        }
    }

    public Boolean registerAccount(Account account) {

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            String SQL = "INSERT INTO account VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pStmt = conn.prepareStatement(SQL);
            pStmt.setString(1, account.getUsername());
            pStmt.setString(2, account.getPassword());
            pStmt.setString(3, account.getEmail());
            pStmt.setDate(4, (java.sql.Date) account.getUpdated());
            pStmt.setString(5, account.getGender().getGenderString());
            pStmt.setDate(6, (java.sql.Date) account.getBirth());
            pStmt.setDouble(7, account.getHeight());
            pStmt.setDouble(8, account.getWeight());
            pStmt.setInt(9, account.getActivityLevel().getRegistrationNumber());
            pStmt.setDouble(10, account.getTotalDailyEnergyExpenditure());

            int numOfRowsUpdated = 0;
            numOfRowsUpdated = pStmt.executeUpdate();

            return numOfRowsUpdated == 1;

        } catch (Exception e) {

            return false;
        }
    }

    private Boolean updateLastLoginDate(Login login) throws Exception {

        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateString = sdf.format(currentDate);

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            String sql = "UPDATE account SET updated = ? WHERE username = ? AND password = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, currentDateString);
            pStmt.setString(2, login.getUsername());
            pStmt.setString(3, login.getPassword());

            int numOfUpdatedRows = pStmt.executeUpdate();
            if (numOfUpdatedRows < 1) {
                throw new Exception("no account to update it's last login date");
            }

        } catch (Exception e) {

            throw new Exception(e);
        }

        return true;
    }
}
