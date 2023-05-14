package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import exception.LogException;
import model.NutritionList;
import model.UserIntake;

public class UserIntakeDAO {

    private final String DB_URL = "jdbc:mysql://localhost:3306/DietDiary";
    private final String DB_USER = "admin";
    private final String DB_PASS = "4dm1n";

    public boolean recordNutritionalIntake(UserIntake userIntake) {

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            String sql = "INSERT INTO user_intake values (" +
                    "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                    "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                    "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                    "?, ?, ?, ?, ?" +
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

    public boolean deleteNutritionalIntake(String username, Date intakeDietDate, int dietNumber) {

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            String sql = "DELETE FROM user_intake WHERE username = ? AND intake_diet_date = ? AND diet_number = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, username);
            pStmt.setDate(2, new java.sql.Date(intakeDietDate.getTime()));
            pStmt.setInt(3, dietNumber);

            int numOfUpdatedRows = pStmt.executeUpdate();
            if (numOfUpdatedRows != 1) {
                throw new Exception("no or more records have been deleted");
            }

        } catch (Exception e) {

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

            for (int i = 0; i < NutritionList.NUTRITION_LIST.size(); i++) {
                String nutritionName = NutritionList.NUTRITION_LIST.get(i);
                nutritionalIntakeMap.put(nutritionName, rs.getDouble(nutritionName));
            }

        } catch (Exception e) {

            LogException.writeErrorMsgToFile(e);
            return null;
        }

        return nutritionalIntakeMap;
    }

}
