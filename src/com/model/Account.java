package com.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
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

    public Account(String username, String password, String email,
            Date lastLoginDate, Gender gender, java.util.Date birth,
            double height, double weight) {

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

}
