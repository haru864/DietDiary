package model;

import java.util.Date;

public class Account {

    private final String username;
    private final String password;
    private final String email;
    private final Date updated;
    private final Gender gender;
    private final Date birth;
    private final double height;
    private final double weight;
    private final ActivityLevel activityLevel;
    private double totalDailyEnergyExpenditure;

    public Account(String username, String password, String email,
            java.util.Date updated, Gender gender, java.util.Date birth,
            double height, double weight, ActivityLevel activityLevel, double totalDailyEnergyExpenditure)
            throws NullPointerException {

        // if (username == null) {
        //     throw new NullPointerException("username of account is null");
        // }
        // if (password == null) {
        //     throw new NullPointerException("password of account is null");
        // }
        // if (email == null) {
        //     throw new NullPointerException("email of account is null");
        // }
        // if (updated == null) {
        //     throw new NullPointerException("updated of account is null");
        // }
        // if (gender == null) {
        //     throw new NullPointerException("gender of account is null");
        // }
        // if (birth == null) {
        //     throw new NullPointerException("birth of account is null");
        // }
        // if (activityLevel == null) {
        //     throw new NullPointerException("activityLevel of account is null");
        // }

        this.username = new String(username);
        this.password = new String(password);
        this.email = new String(email);
        this.updated = updated;
        this.gender = gender;
        this.birth = birth;
        this.height = height;
        this.weight = weight;
        this.activityLevel = activityLevel;
        this.totalDailyEnergyExpenditure = totalDailyEnergyExpenditure;
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

    public Date getUpdated() {
        return updated;
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

    public double getTotalDailyEnergyExpenditure() {
        return totalDailyEnergyExpenditure;
    }

    public void setTDEE(double totalDailyEnergyExpenditure) {
        this.totalDailyEnergyExpenditure = totalDailyEnergyExpenditure;
    }
}
