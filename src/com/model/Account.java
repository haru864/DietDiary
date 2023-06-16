package com.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Account {

    private final String username;
    private final String password;
    private final String email;
    private final Date lastLoginDate;
    private final Gender gender;
    private final Date birth;
    private final double height;
    private final double weight;
    public static List<String> VISIBLE_USER_INFORMATION;
    public static Map<String, Boolean> MODIFIABLE_USER_INFORMATION;

    static {
        VISIBLE_USER_INFORMATION = new ArrayList<>() {
            {
                add("ユーザー名");
                add("パスワード");
                add("メールアドレス");
                add("性別");
                add("誕生日");
                add("身長");
                add("体重");
            }
        };
        MODIFIABLE_USER_INFORMATION = new HashMap<>() {
            {
                put("ユーザー名", false);
                put("パスワード", true);
                put("メールアドレス", true);
                put("性別", false);
                put("誕生日", false);
                put("身長", true);
                put("体重", true);
            }
        };
    }

    public Account(String username, String password, String email,
            Date lastLoginDate, Gender gender, java.util.Date birth,
            double height, double weight) throws Exception {

        if (username == null || password == null || email == null
                || lastLoginDate == null || gender == null || birth == null
                || height < 0 || weight < 0) {

            throw new Exception("invalid account parameter");
        }

        this.username = username;
        this.password = password;
        this.email = email;
        this.lastLoginDate = lastLoginDate;
        this.gender = gender;
        this.birth = birth;
        this.height = height;
        this.weight = weight;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public Gender getGender() {
        return gender;
    }

    public Date getBirth() {
        return birth;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public int calcAge() {

        final LocalDate todayLocalDate = LocalDate.now();
        final LocalDate birthLocalDate = LocalDate.ofInstant(this.birth.toInstant(), ZoneId.systemDefault());
        final int age = Period.between(birthLocalDate, todayLocalDate).getYears();
        return age;
    }

    public Map<String, String> getUserInfoMap() {

        Map<String, String> userInfoMap = new HashMap<>();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime birthLocalDateTime = LocalDateTime.ofInstant(birth.toInstant(), ZoneId.systemDefault());
        String birthString = dtf.format(birthLocalDateTime);

        userInfoMap.put("ユーザー名", username);
        userInfoMap.put("パスワード", password.replaceAll(".", "\\*"));
        userInfoMap.put("メールアドレス", email);
        userInfoMap.put("性別", gender.getGenderString());
        userInfoMap.put("誕生日", birthString);
        userInfoMap.put("身長", String.valueOf(height));
        userInfoMap.put("体重", String.valueOf(weight));

        return userInfoMap;
    }

}
