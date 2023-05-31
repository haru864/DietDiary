package test.DAO;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.DAO.AccountDAO;
import com.model.Account;
import com.model.Gender;
import com.model.Login;

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
                loginDate, Gender.men, birth, 10, 10);
        Account unregistrableAccount = new Account(null, null, null,
                null, null, null, 0, 0);

        if (testRegisterAccount(registrableAccount) == true) {
            System.out.println("[PASS] register succeeded");
        } else {
            System.out.println("[FAILURE] register failed");
        }

        if (testRegisterAccount(unregistrableAccount) == false) {
            System.out.println("[PASS] unauthorized register failed");
        } else {
            System.out.println("[FAILURE] unauthorized register succeeded");
        }

        // ユーザー情報の変更をテスト
        if (testChangeUpdatableUserInformation(
                registrableAccount.getUsername(), "hogehoge", 190.45, 74.8) == true) {
            System.out.println("[PASS] change succeeded");
        } else {
            System.out.println("[PASS] change failed");
        }

        // ユーザー情報の削除をテスト
        if (testDeleteAccount(registrableAccount)) {
            System.out.println("[PASS] test account deleted");
        } else {
            System.out.println("[FAILURE] delete failed");
        }
    }

    private static boolean testFindByLogin(Login login) {

        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.findByLogin(login);

        if (account == null) {
            return false;
        }

        return true;
    }

    private static boolean testRegisterAccount(Account account) {

        AccountDAO accountDAO = new AccountDAO();
        boolean isRegistered = accountDAO.registerAccount(account);
        return isRegistered;
    }

    private static boolean testDeleteAccount(Account account) {

        AccountDAO accountDAO = new AccountDAO();
        boolean isDeleted;
        try {
            isDeleted = accountDAO.deleteAccount(account);
        } catch (Exception e) {
            isDeleted = false;
        }
        return isDeleted;
    }

    private static boolean testChangeUpdatableUserInformation(
            String username, String email, double height, double weight) {

        AccountDAO accountDAO = new AccountDAO();
        return accountDAO.changeUpdatableUserInformation(username, email, height, weight);
    }

}
