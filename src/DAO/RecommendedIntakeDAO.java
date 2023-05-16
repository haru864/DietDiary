package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Gender;
import model.RecommendedIntake;

public class RecommendedIntakeDAO {

    private final String DB_URL = "jdbc:mysql://localhost:3306/DietDiary";
    private final String DB_USER = "admin";
    private final String DB_PASS = "4dm1n";

    public RecommendedIntake getRecommendedIntake(String nutritionName, Gender gender, int age,
            int physicalActivityLevel) {

        // 妊娠・授乳の有無による推奨栄養摂取量の変化は後で実装する
        int pregnancyPeriod = 0;
        int breastFeeding = 0;

        RecommendedIntake recommendedIntake = null;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            String sql = "SELECT * FROM recommended_intake " +
                    "where nutrition_name = ? " +
                    "and gender = ? " +
                    "and min_age <= ? " +
                    "and max_age >= ? " +
                    "and physical_activity_level = ? " +
                    "and pregnancy_period = ? " +
                    "and breastfeeding = ?";

            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, nutritionName);
            pStmt.setString(2, gender.getGenderString());
            pStmt.setInt(3, age);
            pStmt.setInt(4, age);
            pStmt.setInt(5, physicalActivityLevel);
            pStmt.setInt(6, pregnancyPeriod);
            pStmt.setInt(7, breastFeeding);

            ResultSet rs = pStmt.executeQuery();
            rs.next();

            String nutrition_name = rs.getString("nutrition_name");
            String genderString = (rs.getString("gender"));
            Gender genderRegistered = (genderString.equals("men") ? Gender.MEN : Gender.WOMEN);
            int min_age = rs.getInt("min_age");
            int max_age = rs.getInt("max_age");
            int physical_activity_level = rs.getInt("physical_activity_level");
            int pregnancy_period = rs.getInt("pregnancy_period");
            int breastfeeding = rs.getInt("breastfeeding");

            recommendedIntake = new RecommendedIntake(nutrition_name, genderRegistered, min_age, max_age,
                    physical_activity_level, pregnancy_period, breastfeeding);

        } catch (Exception e) {

            // LogException.writeErrorMsgToFile(e);
            e.printStackTrace();
            return null;
        }

        return recommendedIntake;
    }

}
