package com.model;

import java.util.Date;

public class UserIntake implements Comparable<UserIntake> {

    public String username;
    public Date intakeDietDate;
    public int dietNumber;
    public int foodGroup;
    public String foodName;
    public double grams;

    public UserIntake(String username, Date intakeDietDate, int dietNumber,
            int foodGroup, String foodName, double grams) {

        this.username = username;
        this.intakeDietDate = intakeDietDate;
        this.dietNumber = dietNumber;
        this.foodGroup = foodGroup;
        this.foodName = foodName;
        this.grams = grams;
    }

    @Override
    public int compareTo(UserIntake userIntake) {

        return this.dietNumber - userIntake.dietNumber;
    }

    @Override
    public String toString() {
        return "UserIntake [username=" + username + ", intakeDietDate=" + intakeDietDate + ", dietNumber=" + dietNumber
                + ", foodGroup=" + foodGroup + ", foodName=" + foodName + ", grams=" + grams + "]";
    }

}
