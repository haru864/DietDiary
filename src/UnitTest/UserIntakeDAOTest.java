package UnitTest;

import java.util.Date;
import java.util.Map;
import java.util.Random;

import DAO.UserIntakeDAO;
import debug.Debugger;
import model.UserIntake;

public class UserIntakeDAOTest {

    public static void main(String[] args) {

        // 栄養摂取量の集計・登録・削除処理をテスト
        UserIntake userIntake01 = generateUserIntake("test", new Date(), 1, 1);
        UserIntake userIntake02 = generateUserIntake("test", new Date(), 2, 1);
        UserIntake userIntake03 = generateUserIntake("test", new Date(), 3, 1);
        // UserIntake userIntake04 = generateUserIntake("user", new Date(), 1, 1);
        // UserIntake userIntake05 = generateUserIntake("user", new Date(), 2, 1);
        // testRecordNutritionalIntake(userIntake04);
        // testRecordNutritionalIntake(userIntake05);

        if (testRecordNutritionalIntake(userIntake01)
                && testRecordNutritionalIntake(userIntake02)
                && testRecordNutritionalIntake(userIntake03)) {

            System.out.println("[PASS] recording nutrition succeeded");

            int numOfDiets = 0;
            try {
                numOfDiets = testGetNumOfDietsRegistered("test", new Date());
                System.out.println("number of records in user_intake: " + numOfDiets);
            } catch (Exception e) {
                e.printStackTrace();
            }

            for (int i = 0; i < numOfDiets; i++) {

                var nutritionalIntakeMap = testGetSpecifiedNumberDiet(userIntake01.username,
                        userIntake01.intakeDietDate, i + 1);
                // System.out.println(nutritionalIntakeMap);
                Debugger.writeObjectToFile(nutritionalIntakeMap, "UserIntakeTest.class");

                if (testDeleteNutritionalIntake(userIntake01.username, userIntake01.intakeDietDate, i + 1)) {
                    System.out.printf("[PASS] %dth userIntake data deleted\n", i + 1);
                } else {
                    System.out.printf("[FAILURE] %dth userIntake data NOT deleted\n", i + 1);
                }

            }

            try {
                numOfDiets = testGetNumOfDietsRegistered("test", new Date());
                System.out.println("number of records in user_intake: " + numOfDiets);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {

            System.out.println("[FAILURE] recording nutrition failed");
        }

    }

    private static Boolean testRecordNutritionalIntake(UserIntake userIntake) {

        UserIntakeDAO UserIntakeDAO = new UserIntakeDAO();
        Boolean isSuccess;
        try {
            isSuccess = UserIntakeDAO.recordNutritionalIntake(userIntake);
        } catch (Exception e) {
            return false;
        }
        return isSuccess;
    }

    private static Boolean testDeleteNutritionalIntake(String username, Date intakeDietDate, int dietNumber) {

        UserIntakeDAO UserIntakeDAO = new UserIntakeDAO();
        Boolean isSuccess;
        try {
            isSuccess = UserIntakeDAO.deleteNutritionalIntake(username, intakeDietDate, dietNumber);
        } catch (Exception e) {
            return false;
        }
        return isSuccess;
    }

    private static UserIntake generateUserIntake(String username, Date intakeDietDate, int dietNumber,
            int physicalActivityLevel) {

        UserIntake userIntake = new UserIntake(username, intakeDietDate, dietNumber, physicalActivityLevel);
        userIntake.energy = generateRandomDouble(1, 10);
        userIntake.protein = generateRandomDouble(1, 10);
        userIntake.fat = generateRandomDouble(1, 10);
        userIntake.fiber = generateRandomDouble(1, 10);
        userIntake.carbohydrates = generateRandomDouble(1, 10);
        userIntake.vitamin_a = generateRandomDouble(1, 10);
        userIntake.vitamin_b1 = generateRandomDouble(1, 10);
        userIntake.vitamin_b2 = generateRandomDouble(1, 10);
        userIntake.vitamin_b6 = generateRandomDouble(1, 10);
        userIntake.vitamin_b12 = generateRandomDouble(1, 10);
        userIntake.vitamin_c = generateRandomDouble(1, 10);
        userIntake.vitamin_d = generateRandomDouble(1, 10);
        userIntake.vitamin_e = generateRandomDouble(1, 10);
        userIntake.vitamin_k = generateRandomDouble(1, 10);
        userIntake.niacin_equivalent = generateRandomDouble(1, 10);
        userIntake.folic_acid = generateRandomDouble(1, 10);
        userIntake.pantothenic_acid = generateRandomDouble(1, 10);
        userIntake.biotin = generateRandomDouble(1, 10);
        userIntake.na = generateRandomDouble(1, 10);
        userIntake.k = generateRandomDouble(1, 10);
        userIntake.ca = generateRandomDouble(1, 10);
        userIntake.mg = generateRandomDouble(1, 10);
        userIntake.p = generateRandomDouble(1, 10);
        userIntake.fe = generateRandomDouble(1, 10);
        userIntake.zn = generateRandomDouble(1, 10);
        userIntake.cu = generateRandomDouble(1, 10);
        userIntake.mn = generateRandomDouble(1, 10);
        userIntake.id = generateRandomDouble(1, 10);
        userIntake.se = generateRandomDouble(1, 10);
        userIntake.cr = generateRandomDouble(1, 10);
        userIntake.mo = generateRandomDouble(1, 10);
        return userIntake;
    }

    private static double generateRandomDouble(int min, int max) {

        Random random = new Random();
        double randomDouble = random.nextDouble() * (max - min) + min;
        return Math.floor(randomDouble);
    }

    private static int testGetNumOfDietsRegistered(String username, Date date) throws Exception {

        UserIntakeDAO UserIntakeDAO = new DAO.UserIntakeDAO();
        int numOfDiets = UserIntakeDAO.getNumOfDietsRegistered(username, date);
        return numOfDiets;
    }

    private static Map<String, Double> testGetSpecifiedNumberDiet(String username, Date intakeDietDate,
            int dietNumber) {

        UserIntakeDAO UserIntakeDAO = new DAO.UserIntakeDAO();
        return UserIntakeDAO.getSpecifiedNumberDiet(username, intakeDietDate, dietNumber);
    }

}
