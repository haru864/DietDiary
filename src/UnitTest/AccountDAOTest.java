package UnitTest;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.tomcat.util.buf.UDecoder;

import DAO.AccountDAO;
import model.Account;
import model.ActivityLevel;
import model.Gender;
import model.Login;
import model.UserIntake;

public class AccountDAOTest {

    public static void main(String[] args) {

        // ログイン処理をテスト
        Login loginExist = new Login("user", "pass");
        Login loginNotExist = new Login("userBoy", "pass");

        if (testFindByLogin(loginExist) == true) {
            System.out.println("[PASS] login succeeded");
        } else {
            System.out.println("[FAILURE] login failed");
        }

        if (testFindByLogin(loginNotExist) == false) {
            System.out.println("[PASS] unregistered login failed");
        } else {
            System.out.println("[FAILURE] unregistered login succeeded");
        }

        // ユーザー登録処理をテスト
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date loginDate = new Date();
        Date birth = null;
        try {
            birth = sdf.parse("1990-05-01");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Account registrableAccount = new Account("test", "pass", "test@sample.com",
                loginDate, Gender.MEN, birth, 10, 10, ActivityLevel.HARD_EXERCIS);
        Account unregistrableAccount = new Account(null, null, null,
                null, null, null, 0, 0, null);

        if (testRegisterAccount(registrableAccount) == true) {
            System.out.println("[PASS] register succeeded");
            if (testDeleteAccount(registrableAccount)) {
                System.out.println("test account deleted");
            }
        } else {
            System.out.println("[FAILURE] register failed");
        }

        if (testRegisterAccount(unregistrableAccount) == false) {
            System.out.println("[PASS] unauthorized register failed");
        } else {
            System.out.println("[FAILURE] unauthorized register succeeded");
        }

        // 栄養摂取量の集計・登録・削除処理をテスト
        UserIntake userIntakeRight = new UserIntake("user", new Date(), 1, 1);

    }

    private static Boolean testFindByLogin(Login login) {

        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.findByLogin(login);

        if (account == null) {
            return false;
        }

        return true;
    }

    private static Boolean testRegisterAccount(Account account) {

        AccountDAO accountDAO = new AccountDAO();
        Boolean isRegistered = accountDAO.registerAccount(account);
        return isRegistered;
    }

    private static Boolean testDeleteAccount(Account account) {

        AccountDAO accountDAO = new AccountDAO();
        Boolean isDeleted;
        try {
            isDeleted = accountDAO.deleteAccount(account);
        } catch (Exception e) {
            isDeleted = false;
        }
        return isDeleted;
    }

    private static Boolean testAggregateNutritionalIntake(String username, Date intakeDietDate) {

    }

    private static Boolean testRecordNutritionalIntake(UserIntake userIntake) {

    }

    private static Boolean testDeleteNutritionalIntake(UserIntake userIntake) {

    }

}
