package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.model.Account;
import com.model.Gender;
import com.model.Login;

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

            Account account = new Account(username, password, email,
                    updated, gender, birth, height, weight);

            updateLastLoginDate(login);

            return account;

        } catch (Exception e) {

            return null;
        }
    }

    public Account getAccountByUserName(String username) {

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            String SQL = "SELECT * FROM accounts WHERE username = ?";
            PreparedStatement pStmt = conn.prepareStatement(SQL);
            pStmt.setString(1, username);

            ResultSet rs = pStmt.executeQuery();
            rs.next();

            String _username = rs.getString("username");
            String _password = rs.getString("password");
            String _email = rs.getString("email");
            Date _updated = rs.getDate("last_login_date");
            Gender _gender = rs.getString("gender").equals("men") ? Gender.men : Gender.women;
            Date _birth = new java.util.Date(rs.getDate("birth").getTime());
            double _height = rs.getDouble("height");
            double _weight = rs.getDouble("weight");

            Account account = new Account(_username, _password, _email,
                    _updated, _gender, _birth, _height, _weight);

            return account;

        } catch (Exception e) {

            return null;
        }
    }

    public boolean registerAccount(Account account) {

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            String SQL = "INSERT INTO accounts VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
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

            int numOfRowsUpdated = 0;
            numOfRowsUpdated = pStmt.executeUpdate();

            return numOfRowsUpdated == 1;

        } catch (Exception e) {

            return false;
        }
    }

    public boolean changeUpdatableUserInformation(String username, String email, double height, double weight) {

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            String SQL = "UPDATE accounts set email = ?, height = ?, weight = ? WHERE username = ?";

            PreparedStatement pStmt = conn.prepareStatement(SQL);
            pStmt.setString(1, email);
            pStmt.setDouble(2, height);
            pStmt.setDouble(3, weight);
            pStmt.setString(4, username);

            int numOfRowsUpdated = 0;
            numOfRowsUpdated = pStmt.executeUpdate();

            return numOfRowsUpdated == 1;

        } catch (Exception e) {

            return false;
        }
    }

    public boolean deleteAccount(Account account) throws Exception {

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

    private boolean updateLastLoginDate(Login login) throws Exception {

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
