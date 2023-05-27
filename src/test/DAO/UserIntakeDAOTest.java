package test.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.DAO.UserIntakeDAO;
import com.debug.Debugger;
import com.model.UserIntake;

public class UserIntakeDAOTest {

    public static void main(String[] args) {

        // 栄養摂取量の集計・登録・削除処理をテスト
        UserIntake userIntake01 = new UserIntake("test", new Date(), 1,
                1, "アマランサス　玄穀", 100.0);
        UserIntake userIntake02 = new UserIntake("test", new Date(), 2,
                3, "（その他）　メープルシロップ", 150.0);
        UserIntake userIntake03 = new UserIntake("test", new Date(), 3,
                4, "あずき　あん　こし生あん", 200.0);

        List<UserIntake> userIntakeList = new ArrayList<>() {
            {
                add(userIntake01);
                add(userIntake02);
                add(userIntake03);
            }
        };

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
            }

            for (int i = 0; i < userIntakeList.size(); i++) {

                if (testDeleteNutritionalIntake(userIntakeList.get(i))) {
                    System.out.printf("[PASS] userIntake%02d data deleted\n", i + 1);
                } else {
                    System.out.printf("[FAILURE] userIntake%02d data NOT deleted\n", i + 1);
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

    private static Boolean testDeleteNutritionalIntake(UserIntake userIntake) {

        UserIntakeDAO UserIntakeDAO = new UserIntakeDAO();
        Boolean isSuccess;
        try {
            isSuccess = UserIntakeDAO.deleteNutritionalIntake(userIntake);
        } catch (Exception e) {
            return false;
        }
        return isSuccess;
    }

    private static int testGetNumOfDietsRegistered(String username, Date date) throws Exception {

        UserIntakeDAO UserIntakeDAO = new UserIntakeDAO();
        int numOfDiets = UserIntakeDAO.getNumOfDietsRegistered(username, date);
        return numOfDiets;
    }

    private static Map<String, Double> testGetSpecifiedNumberDiet(String username, Date intakeDietDate,
            int dietNumber) {

        UserIntakeDAO UserIntakeDAO = new UserIntakeDAO();
        return UserIntakeDAO.getSpecifiedNumberDiet(username, intakeDietDate, dietNumber);
    }

}
