package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import model.Account;
import model.ActivityLevel;
import model.Gender;
import model.Login;
import model.UserIntake;

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
            Gender gender = rs.getString("gender").equals("men") ? Gender.MEN : Gender.WOMEN;
            Date birth = rs.getDate("birth");
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

    public Map<String, Double> aggregateNutritionalIntake(String username, Date intakeDietDate) {

        Map<String, Double> nutritionalIntakeMap = new HashMap<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            String sql = "SELECT * FROM user_intake WHERE username = ? AND intake_diet_date = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, username);
            pStmt.setDate(2, new java.sql.Date(intakeDietDate.getTime()));
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                nutritionalIntakeMap.put("energy",
                        nutritionalIntakeMap.getOrDefault("energy", 0.0) + rs.getDouble("energy"));
                nutritionalIntakeMap.put("protein",
                        nutritionalIntakeMap.getOrDefault("protein", 0.0) + rs.getDouble("protein"));
                nutritionalIntakeMap.put("fat", nutritionalIntakeMap.getOrDefault("fat", 0.0) + rs.getDouble("fat"));
                nutritionalIntakeMap.put("fiber",
                        nutritionalIntakeMap.getOrDefault("fiber", 0.0) + rs.getDouble("fiber"));
                nutritionalIntakeMap.put("carbohydrates",
                        nutritionalIntakeMap.getOrDefault("carbohydrates", 0.0) + rs.getDouble("carbohydrates"));
                nutritionalIntakeMap.put("vitamin_a",
                        nutritionalIntakeMap.getOrDefault("vitamin_a", 0.0) + rs.getDouble("vitamin_a"));
                nutritionalIntakeMap.put("vitamin_b1",
                        nutritionalIntakeMap.getOrDefault("vitamin_b1", 0.0) + rs.getDouble("vitamin_b1"));
                nutritionalIntakeMap.put("vitamin_b2",
                        nutritionalIntakeMap.getOrDefault("vitamin_b2", 0.0) + rs.getDouble("vitamin_b2"));
                nutritionalIntakeMap.put("vitamin_b6",
                        nutritionalIntakeMap.getOrDefault("vitamin_b6", 0.0) + rs.getDouble("vitamin_b6"));
                nutritionalIntakeMap.put("vitamin_b12",
                        nutritionalIntakeMap.getOrDefault("vitamin_b12", 0.0) + rs.getDouble("vitamin_b12"));
                nutritionalIntakeMap.put("vitamin_c",
                        nutritionalIntakeMap.getOrDefault("vitamin_c", 0.0) + rs.getDouble("vitamin_c"));
                nutritionalIntakeMap.put("vitamin_d",
                        nutritionalIntakeMap.getOrDefault("vitamin_d", 0.0) + rs.getDouble("vitamin_d"));
                nutritionalIntakeMap.put("vitamin_e",
                        nutritionalIntakeMap.getOrDefault("vitamin_e", 0.0) + rs.getDouble("vitamin_e"));
                nutritionalIntakeMap.put("vitamin_k",
                        nutritionalIntakeMap.getOrDefault("vitamin_k", 0.0) + rs.getDouble("vitamin_k"));
                nutritionalIntakeMap.put("niacin_equivalent",
                        nutritionalIntakeMap.getOrDefault("niacin_equivalent", 0.0)
                                + rs.getDouble("niacin_equivalent"));
                nutritionalIntakeMap.put("folic_acid",
                        nutritionalIntakeMap.getOrDefault("folic_acid", 0.0) + rs.getDouble("folic_acid"));
                nutritionalIntakeMap.put("pantothenic_acid",
                        nutritionalIntakeMap.getOrDefault("pantothenic_acid", 0.0) + rs.getDouble("pantothenic_acid"));
                nutritionalIntakeMap.put("biotin",
                        nutritionalIntakeMap.getOrDefault("biotin", 0.0) + rs.getDouble("biotin"));
                nutritionalIntakeMap.put("na", nutritionalIntakeMap.getOrDefault("na", 0.0) + rs.getDouble("na"));
                nutritionalIntakeMap.put("k", nutritionalIntakeMap.getOrDefault("k", 0.0) + rs.getDouble("k"));
                nutritionalIntakeMap.put("ca", nutritionalIntakeMap.getOrDefault("ca", 0.0) + rs.getDouble("ca"));
                nutritionalIntakeMap.put("mg", nutritionalIntakeMap.getOrDefault("mg", 0.0) + rs.getDouble("mg"));
                nutritionalIntakeMap.put("p", nutritionalIntakeMap.getOrDefault("p", 0.0) + rs.getDouble("p"));
                nutritionalIntakeMap.put("fe", nutritionalIntakeMap.getOrDefault("fe", 0.0) + rs.getDouble("fe"));
                nutritionalIntakeMap.put("zn", nutritionalIntakeMap.getOrDefault("zn", 0.0) + rs.getDouble("zn"));
                nutritionalIntakeMap.put("cu", nutritionalIntakeMap.getOrDefault("cu", 0.0) + rs.getDouble("cu"));
                nutritionalIntakeMap.put("mn", nutritionalIntakeMap.getOrDefault("mn", 0.0) + rs.getDouble("mn"));
                nutritionalIntakeMap.put("id", nutritionalIntakeMap.getOrDefault("id", 0.0) + rs.getDouble("id"));
                nutritionalIntakeMap.put("se", nutritionalIntakeMap.getOrDefault("se", 0.0) + rs.getDouble("se"));
                nutritionalIntakeMap.put("cr", nutritionalIntakeMap.getOrDefault("cr", 0.0) + rs.getDouble("cr"));
                nutritionalIntakeMap.put("mo", nutritionalIntakeMap.getOrDefault("mo", 0.0) + rs.getDouble("mo"));
            }

        } catch (Exception e) {

            return null;
        }

        return nutritionalIntakeMap;
    }

    public boolean recordNutritionalIntake(UserIntake userIntake) {

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            String sql = "INSERT INTO user_intake values (" +
                    "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                    "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                    "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                    "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                    "?" +
                    ")";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, userIntake.username);
            pStmt.setDate(2, new java.sql.Date(userIntake.intakeDietDate.getTime()));
            pStmt.setInt(3, userIntake.dietNumber);
            pStmt.setInt(4, userIntake.physicalActivityLevel);
            pStmt.setDouble(5, userIntake.energy);
            pStmt.setDouble(6, userIntake.protein);
            pStmt.setDouble(7, userIntake.fat);
            pStmt.setDouble(8, userIntake.fiber);
            pStmt.setDouble(9, userIntake.carbohydrates);
            pStmt.setDouble(10, userIntake.vitamin_a);
            pStmt.setDouble(11, userIntake.vitamin_b1);
            pStmt.setDouble(12, userIntake.vitamin_b2);
            pStmt.setDouble(13, userIntake.vitamin_b6);
            pStmt.setDouble(14, userIntake.vitamin_b12);
            pStmt.setDouble(15, userIntake.vitamin_c);
            pStmt.setDouble(16, userIntake.vitamin_d);
            pStmt.setDouble(17, userIntake.vitamin_e);
            pStmt.setDouble(18, userIntake.vitamin_k);
            pStmt.setDouble(19, userIntake.niacin_equivalent);
            pStmt.setDouble(20, userIntake.folic_acid);
            pStmt.setDouble(21, userIntake.pantothenic_acid);
            pStmt.setDouble(22, userIntake.biotin);
            pStmt.setDouble(23, userIntake.na);
            pStmt.setDouble(24, userIntake.k);
            pStmt.setDouble(25, userIntake.ca);
            pStmt.setDouble(26, userIntake.mg);
            pStmt.setDouble(27, userIntake.p);
            pStmt.setDouble(28, userIntake.fe);
            pStmt.setDouble(29, userIntake.zn);
            pStmt.setDouble(30, userIntake.cu);
            pStmt.setDouble(31, userIntake.mn);
            pStmt.setDouble(32, userIntake.id);
            pStmt.setDouble(33, userIntake.se);
            pStmt.setDouble(34, userIntake.cr);
            pStmt.setDouble(35, userIntake.mo);

            int numOfRowsInserted = pStmt.executeUpdate();
            if (numOfRowsInserted != 1) {
                throw new Exception("insert into user_intake failed");
            }

        } catch (Exception e) {

            return false;
        }

        return true;
    }

    public boolean deleteNutritionalIntake(UserIntake userIntake) {

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            String sql = "DELETE FROM user_intake WHERE username = ? AND intake_diet_date = ? AND diet_number = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, userIntake.username);
            pStmt.setDate(2, new java.sql.Date(userIntake.intakeDietDate.getTime()));
            pStmt.setInt(3, userIntake.dietNumber);

            int numOfUpdatedRows = pStmt.executeUpdate();
            if (numOfUpdatedRows != 1) {
                throw new Exception("no or more records have been deleted");
            }

        } catch (Exception e) {

            return false;
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
