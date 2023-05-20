package com.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CheckAccountLogic {

    /**
     * Accountオブジェクトの登録内容をチェックする
     * 
     * @return 設定値が不正な箇所に関するメッセージのList、異常がなければnullを返す
     */
    public List<String> execute(Account account) {

        List<String> errorMessageList = new ArrayList<>();

        // ユーザー名：10文字以下、半角英数のみ
        final String username = account.getUsername();
        if (username == null || username.length() > 10 || username.matches(("[a-z0-9]+")) == false) {
            errorMessageList.add("ユーザー名が不正です。10文字以下、半角英数のみとしてください。");
        }

        // パスワード：10文字以下、半角英数記号(空白除く)
        final String password = account.getPassword();
        if (username == null || password.length() > 10 || password.matches(("[a-z0-9\\S]+")) == false) {
            errorMessageList.add("パスワードが不正です。10文字以下、半角英数記号(空白除く)としてください。");
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
        LocalDate todayLocalDate = LocalDate.ofInstant(todayDate.toInstant(), ZoneId.systemDefault());
        LocalDate birthLocalDate = LocalDate.ofInstant(birthDate.toInstant(), ZoneId.systemDefault());
        if (Period.between(birthLocalDate, todayLocalDate).getDays() < 0) {
            errorMessageList.add("生年月日が不正です。本日よりも前の日付を設定してください。");
        }

        // 身長・体重・TDEE：負数でないこと
        if (account.getHeight() < 0) {
            errorMessageList.add("身長が不正です。正数を設定してください。");
        }
        if (account.getWeight() < 0) {
            errorMessageList.add("体重が不正です。正数を設定してください。");
        }
        if (account.getWeight() < 0) {
            errorMessageList.add("TDEEが不正です。性別、身長、体重の設定を見直してください。");
        }

        if (errorMessageList.size() > 0) {
            return errorMessageList;
        } else {
            return null;
        }
    }
}
