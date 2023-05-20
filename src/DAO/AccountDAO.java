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

            String SQL = "SELECT * FROM accounts WHERE username = ? AND password = ?";
            PreparedStatement pStmt = conn.prepareStatement(SQL);
            pStmt.setString(1, login.getUsername());
            pStmt.setString(2, login.getPassword());

            ResultSet rs = pStmt.executeQuery();
            rs.next();

            String username = rs.getString("username");
            String password = rs.getString("password");
            String email = rs.getString("email");
            Date updated = rs.getDate("last_login_date");
            Gender gender = rs.getString("gender").equals("men") ? Gender.men : Gender.women;
            Date birth = new java.util.Date(rs.getDate("birth").getTime());
            double height = rs.getDouble("height");
            double weight = rs.getDouble("weight");
            int activityLevelNumber = rs.getInt("activity_level");
            ActivityLevel activityLevel = ActivityLevel.getActivityLevelFromInt(activityLevelNumber);

            Account account = new Account(username, password, email,
                    updated, gender, birth,
                    height, weight, activityLevel);

            updateLastLoginDate(login);

            return account;

        } catch (Exception e) {

            return null;
        }
    }

    public Boolean registerAccount(Account account) {

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            String SQL = "INSERT INTO accounts VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pStmt = conn.prepareStatement(SQL);
            pStmt.setString(1, account.getUsername());
            pStmt.setString(2, account.getPassword());
            pStmt.setString(3, account.getEmail());
            Date lastLoginDate = account.getLastLoginDate();
            pStmt.setDate(4, new java.sql.Date(lastLoginDate.getTime()));
            pStmt.setString(5, account.getGender().getGenderString());
            Date birthDate = account.getBirth();
            pStmt.setDate(6, new java.sql.Date(birthDate.getTime()));
            pStmt.setDouble(7, account.getHeight());
            pStmt.setDouble(8, account.getWeight());
            pStmt.setInt(9, account.getActivityLevel().getRegistrationNumber());

            int numOfRowsUpdated = 0;
            numOfRowsUpdated = pStmt.executeUpdate();

            return numOfRowsUpdated == 1;

        } catch (Exception e) {

            return false;
        }
    }

    public Boolean deleteAccount(Account account) throws Exception {

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            String sql = "DELETE FROM accounts WHERE username = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, account.getUsername());

            int numOfUpdatedRows = pStmt.executeUpdate();
            if (numOfUpdatedRows != 1) {
                throw new Exception("no or more records have been deleted");
            }

        } catch (Exception e) {

            throw new Exception("delete account failed");
        }

        return true;
    }

    private Boolean updateLastLoginDate(Login login) throws Exception {

        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateString = sdf.format(currentDate);

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            String sql = "UPDATE accounts SET last_login_date = ? WHERE username = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, currentDateString);
            pStmt.setString(2, login.getUsername());

            int numOfUpdatedRows = pStmt.executeUpdate();
            if (numOfUpdatedRows != 1) {
                throw new Exception("no or more records have been updated");
            }

        } catch (Exception e) {

            throw new Exception("update last_login_date failed");
        }

        return true;
    }

}
