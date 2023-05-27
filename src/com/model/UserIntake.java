package com.model;

import java.util.Date;

public class UserIntake implements Comparable<UserIntake> {

    public String username;
    public Date intakeDietDate;
    public int dietNumber;
    public int physicalActivityLevel;
    public Nutrition nutrition;

    public UserIntake(String username, Date intakeDietDate, int dietNumber, int physicalActivityLevel) {
        this.username = username;
        this.intakeDietDate = intakeDietDate;
        this.dietNumber = dietNumber;
        this.physicalActivityLevel = physicalActivityLevel;
        this.nutrition = new Nutrition();
    }

    @Override
    public int compareTo(UserIntake userIntake) {

        return this.dietNumber - userIntake.dietNumber;
    }

}
