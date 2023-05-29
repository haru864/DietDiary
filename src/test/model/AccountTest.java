package test.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.model.Account;
import com.model.Gender;

public class AccountTest {

    public static void main(String[] args) {

        List<String> failedCasesList = new ArrayList<>();

        final String username = "nanako";
        final String password = "p4ssw0rd";
        final String email = "hogehoge@au.com";
        final Date lastLoginDate = new Date();
        final Gender gender = Gender.women;
        final Date birth = new Date();
        final double height = 163.7;
        final double weight = 45.2;
        Account account = new Account(username, password, email, lastLoginDate, gender, birth, height, weight);

        if (testGetUserInfoMap(account) == true) {
            System.out.println("testGetUserInfoMap ... passed");
        } else {
            System.out.println("testGetUserInfoMap ... FAILED");
            failedCasesList.add("testGetUserInfoMap: case_01-01 failed");
        }

        showFailedCases(failedCasesList);
    }

    private static void showFailedCases(List<String> failedCasesList) {

        if (failedCasesList.size() == 0) {
            System.out.println();
            System.out.println("All cases passed!");
            return;
        }

        System.out.println("================================================");
        System.out.println(failedCasesList.size() + " cases failed");
        System.out.println("------------------------------------------------");

        for (String message : failedCasesList) {
            System.out.println("  " + message);
        }
    }

    private static boolean testGetUserInfoMap(Account account) {

        System.out.println(Account.USER_MODIFIABLE_INFORMATION);

        var userInfoMap = account.getUserInfoMap();
        if (userInfoMap == null) {
            return false;
        }

        for (String key : Account.USER_MODIFIABLE_INFORMATION) {
            System.out.println(key + ": " + userInfoMap.get(key));
        }

        return true;
    }

}
