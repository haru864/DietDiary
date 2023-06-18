package com.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.DAO.AccountDAO;

public class ValidateAccountLogic {

    private static final String USERNAME_PATTERN = "[A-Za-z0-9]+";
    private static final String PASSWORD_PATTERN = "[A-Za-z0-9\\\\S]+";
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     * Accountオブジェクトの登録内容をチェックする
     * 
     * @return 設定値が不正な箇所に関するメッセージのList、異常がなければnullを返す
     */
    public List<String> execute(Account account) {

        List<String> errorMessageList = new ArrayList<>();

        // ユーザー名：10文字以下、半角英数のみ
        final String username = account.getUsername();
        if (username == null || username.length() > 10 || username.matches(USERNAME_PATTERN) == false) {
            errorMessageList.add("ユーザー名が不正です。10文字以下、半角英数のみとしてください。");
        } else {
            AccountDAO accountDAO = new AccountDAO();
            Account sameNameAccount = accountDAO.getAccountByUserName(username);
            if (sameNameAccount != null) {
                errorMessageList.add("入力されたユーザー名はすでに使用されています。");
            }
        }

        // パスワード：10文字以下、半角英数記号(空白除く)
        final String password = account.getPassword();
        if (password == null || password.length() > 20 || password.matches(PASSWORD_PATTERN) == false) {
            errorMessageList.add("パスワードが不正です。20文字以下、半角英数記号(空白除く)としてください。");
        }

        // メールアドレス：
        final String email = account.getEmail();
        if (email == null || email.matches(EMAIL_PATTERN) == false) {
            errorMessageList.add("メールアドレスを正しい形式で入力してください。");
        }

        // 性別："men"または"women"
        Gender gender = account.getGender();
        if (gender == null
                || (gender.getGenderString().equals("men") == false
                        && gender.getGenderString().equals("women") == false)) {
            errorMessageList.add("性別が不正です。男性または女性を選択してください。");
        }

        // 生年月日：システム日付より前の日付
        Date todayDate = account.getLastLoginDate();
        Date birthDate = account.getBirth();
        if (birthDate == null) {
            errorMessageList.add("生年月日を設定してください。");
        } else {
            LocalDate todayLocalDate = LocalDate.ofInstant(todayDate.toInstant(), ZoneId.systemDefault());
            LocalDate birthLocalDate = LocalDate.ofInstant(birthDate.toInstant(), ZoneId.systemDefault());
            if (Period.between(birthLocalDate, todayLocalDate).getDays() < 0) {
                errorMessageList.add("生年月日は本日よりも前の日付を設定してください。");
            }
        }

        // 身長・体重：正数であること
        if (account.getHeight() <= 0) {
            errorMessageList.add("身長が不正です。正数を設定してください。");
        }
        if (account.getWeight() <= 0) {
            errorMessageList.add("体重が不正です。正数を設定してください。");
        }

        return errorMessageList;
    }
}
