package model;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

public class CheckAccountLogic {

    public List<String> execute(Account account) {

        // List<Map<String, String>> errorMessageList = new ArrayList<>();
        List<String> errorMessageList = new ArrayList<>();

        // ユーザー名：10文字以下、半角英数のみ
        final String username = account.getUsername();
        if (username == null || username.length() > 10 || username.matches(("[a-z0-9]+")) == false) {
            // Map<String, String> usernameError = new HashMap<>();
            // usernameError.put("username", "ユーザー名が不正です。10文字以下、半角英数のみとしてください。");
            // errorMessageList.add(usernameError);
            errorMessageList.add("ユーザー名が不正です。10文字以下、半角英数のみとしてください。");
        }

        // パスワード：10文字以下、半角英数記号(空白除く)
        final String password = account.getPassword();
        if (username == null || password.length() > 10 || password.matches(("[a-z0-9\\S]+")) == false) {
            // Map<String, String> passwordError = new HashMap<>();
            // passwordError.put("password", "パスワードが不正です。10文字以下、半角英数記号(空白除く)としてください。");
            // errorMessageList.add(passwordError);
            errorMessageList.add("パスワードが不正です。10文字以下、半角英数記号(空白除く)としてください。");
        }

        // 性別："men"または"women"
        Gender gender = account.getGender();
        if (gender == null || gender.getGenderString() == null
                || gender.getGenderString().equals("men") == false
                || gender.getGenderString().equals("women") == false) {
            // Map<String, String> genderError = new HashMap<>();
            // genderError.put("gender", "性別が不正です。男性または女性を選択してください。");
            // errorMessageList.add(genderError);
            errorMessageList.add("性別が不正です。男性または女性を選択してください。");
        }

        // 生年月日：システム日付より前の日付
        Date todayDate = account.getUpdated();
        Date birthDate = account.getBirth();
        LocalDate todayLocalDate = LocalDate.ofInstant(todayDate.toInstant(), ZoneId.systemDefault());
        LocalDate birthLocalDate = LocalDate.ofInstant(birthDate.toInstant(), ZoneId.systemDefault());
        if (Period.between(birthLocalDate, todayLocalDate).getDays() < 0) {
            // Map<String, String> birthError = new HashMap<>();
            // birthError.put("birth", "生年月日が不正です。本日よりも前の日付を設定してください。");
            // errorMessageList.add(birthError);
            errorMessageList.add("生年月日が不正です。本日よりも前の日付を設定してください。");
        }

        // 身長・体重：負数でないこと
        if (account.getHeight() < 0) {
            // Map<String, String> heightError = new HashMap<>();
            // heightError.put("height", "身長が不正です。正数を設定してください。");
            // errorMessageList.add(heightError);
            errorMessageList.add("身長が不正です。正数を設定してください。");
        }
        if (account.getWeight() < 0) {
            // Map<String, String> weightError = new HashMap<>();
            // weightError.put("weight", "体重が不正です。正数を設定してください。");
            // errorMessageList.add(weightError);
            errorMessageList.add("体重が不正です。正数を設定してください。");
        }

        if (errorMessageList.size() > 0) {
            return errorMessageList;
        } else {
            return null;
        }
    }
}
