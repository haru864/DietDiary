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
    private final double totalDailyEnergyExpenditure;

    public Account(String username, String password, String email,
            java.util.Date updated, Gender gender, java.util.Date birth,
            double height, double weight, ActivityLevel activityLevel, double totalDailyEnergyExpenditure)
            throws NullPointerException {

        if (username == null || password == null || email == null
                || updated == null || gender == null || birth == null) {

            throw new NullPointerException("some parameter of account is null");
        }

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
}
