package UnitTest;

import DAO.AccountDAO;
import model.Account;
import model.Login;

public class AccountDAOTest {

    public static void main(String[] args) {

        Login loginExist = new Login("user", "pass");
        Login loginNotExist = new Login("userBoy", "pass");

        if (testFindByLogin(loginExist) == true) {
            System.out.println("PASS: this account has been registered");
        } else {
            System.out.println("FAILURE: login function has been bugged");
        }

        if (testFindByLogin(loginNotExist) == false) {
            System.out.println("PASS: this account has not been registered");
        } else {
            System.out.println("FAILURE: login function has been bugged");
        }
    }

    private static Boolean testFindByLogin(Login login) {

        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.findByLogin(login);

        if (account == null) {
            return false;
        }

        return true;
    }
}
