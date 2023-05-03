package model;

import java.util.Date;

public class Account {

    private final String username;
    private final String password;
    private final String email;
    private final Date lastLoginDate;
    private final Gender gender;
    private final Date birth;
    private final double height;
    private final double weight;
    private final ActivityLevel activityLevel;
    private double TDEE;

    public Account(String username, String password, String email,
            Date lastLoginDate, Gender gender, java.util.Date birth,
            double height, double weight, ActivityLevel activityLevel, double TDEE) {

        this.username = username;
        this.password = password;
        this.email = email;
        this.lastLoginDate = lastLoginDate;
        this.gender = gender;
        this.birth = birth;
        this.height = height;
        this.weight = weight;
        this.activityLevel = activityLevel;
        this.TDEE = TDEE;
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

    public ActivityLevel getActivityLevel() {
        return activityLevel;
    }

    public double getTDEE() {
        return TDEE;
    }

    public void setTDEE(double TDEE) {
        this.TDEE = TDEE;
    }
}
