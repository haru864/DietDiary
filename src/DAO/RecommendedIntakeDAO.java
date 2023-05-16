package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

import model.Gender;
import model.RecommendedIntake;

public class RecommendedIntakeDAO {

    private final String DB_URL = "jdbc:mysql://localhost:3306/DietDiary";
    private final String DB_USER = "admin";
    private final String DB_PASS = "4dm1n";

    public RecommendedIntake listFoodGroups(String nutritionName, Gender gender, int age, int physicalActivityLevel,
            int pregnancyPeriod, int breastfeeding) {

        // 妊娠・月経の有無による推奨栄養摂取量の変化は後で実装する
        pregnancyPeriod = 0;
        breastfeeding = 0;

        RecommendedIntake recommendedIntake = null;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            String sql = "SELECT * FROM recommended_intake " +
                    "where ";
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
