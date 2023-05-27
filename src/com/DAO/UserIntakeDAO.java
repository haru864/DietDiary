package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.exception.LogException;
import com.model.Nutrition;
import com.model.UserIntake;

public class UserIntakeDAO {

    private final String DB_URL = "jdbc:mysql://localhost:3306/DietDiary";
    private final String DB_USER = "admin";
    private final String DB_PASS = "4dm1n";

    public boolean recordNutritionalIntake(UserIntake userIntake) {

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            String sql = "INSERT INTO user_intake values (" +
                    "?, ?, ?, ?, ?, ?" +
                    ")";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, userIntake.username);
            pStmt.setDate(2, new java.sql.Date(userIntake.intakeDietDate.getTime()));
            pStmt.setInt(3, userIntake.dietNumber);
            pStmt.setInt(4, userIntake.foodGroup);
            pStmt.setString(5, userIntake.foodName);
            pStmt.setDouble(6, userIntake.grams);

            int numOfRowsInserted = pStmt.executeUpdate();
            if (numOfRowsInserted != 1) {
                throw new Exception("insert into user_intake failed");
            }

        } catch (Exception e) {

            e.printStackTrace();
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
                throw new Exception("zero or more than one deleted");
            }

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }

        return true;
    }

    public int getNumOfDietsRegistered(String username, Date date) throws Exception {

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            String sql = "SELECT COUNT(*) FROM user_intake WHERE username = ? AND intake_diet_date = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, username);
            pStmt.setDate(2, new java.sql.Date(date.getTime()));

            ResultSet rs = pStmt.executeQuery();
            rs.next();
            int numOfDiets = rs.getInt(1);

            return numOfDiets;

        } catch (Exception e) {

            throw new Exception(e);
        }
    }

    public Map<String, Double> getSpecifiedNumberDiet(String username, Date intakeDietDate, int dietNumber) {

        Map<String, Double> nutritionalIntakeMap = new HashMap<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            String sql = "SELECT * FROM user_intake WHERE username = ? AND intake_diet_date = ? AND diet_number = ? ";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, username);
            pStmt.setDate(2, new java.sql.Date(intakeDietDate.getTime()));
            pStmt.setInt(3, dietNumber);

            ResultSet rs = pStmt.executeQuery();
            rs.next();

            for (int i = 0; i < Nutrition.NUTRITION_LIST.size(); i++) {
                String nutritionName = Nutrition.NUTRITION_LIST.get(i);
                nutritionalIntakeMap.put(nutritionName, rs.getDouble(nutritionName));
            }

        } catch (Exception e) {

            LogException.writeErrorMsgToFile(e);
            return null;
        }

        return nutritionalIntakeMap;
    }

}
