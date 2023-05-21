package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NutritionFactsDAO {

    private final String DB_URL = "jdbc:mysql://localhost:3306/DietDiary";
    private final String DB_USER = "admin";
    private final String DB_PASS = "4dm1n";

    public List<String> listRegisteredDiets() {

        List<String> dietsAvailableForDropdown = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            String sql = "SELECT food_name FROM nutrition_facts ORDER BY food_name";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                dietsAvailableForDropdown.add(rs.getString(1));
            }

            if (dietsAvailableForDropdown.size() == 0) {
                return null;
            }

        } catch (Exception e) {

            // LogException.writeErrorMsgToFile(e);
            e.printStackTrace();
            return null;
        }

        return dietsAvailableForDropdown;
    }

    public List<String> listFoodNameByFoodGroupNumber(int foodGroupNumber) {

        List<String> foodNameList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            String sql = "SELECT food_name FROM nutrition_facts WHERE food_group_number = ? ORDER BY food_name";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, foodGroupNumber);
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                foodNameList.add(rs.getString(1));
            }

            if (foodNameList.size() == 0) {
                return null;
            }

        } catch (Exception e) {

            // LogException.writeErrorMsgToFile(e);
            e.printStackTrace();
            return null;
        }

        return foodNameList;
    }

}
