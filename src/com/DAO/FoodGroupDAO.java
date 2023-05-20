package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.model.FoodGroup;

public class FoodGroupDAO {

    private final String DB_URL = "jdbc:mysql://localhost:3306/DietDiary";
    private final String DB_USER = "admin";
    private final String DB_PASS = "4dm1n";

    public List<FoodGroup> listFoodGroups() {

        List<FoodGroup> foodGroupList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            String sql = "SELECT * FROM food_group ORDER BY food_group_number";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                int foodGroupNumber = rs.getInt("food_group_number");
                String foodGroupName = rs.getString("food_group_name");
                FoodGroup foodGroup = new FoodGroup(foodGroupNumber, foodGroupName);
                foodGroupList.add(foodGroup);
            }

            if (foodGroupList.size() == 0) {
                return null;
            }

        } catch (Exception e) {

            // LogException.writeErrorMsgToFile(e);
            e.printStackTrace();
            return null;
        }

        return foodGroupList;
    }
}
