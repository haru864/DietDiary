package model;

import java.sql.Date;

public class Account {

    private final String username;
    private final String password;
    private final String email;
    private final Date updated;
    private final Gender gender;
    private final Date birth;
    private final double height;
    private final double weight;
    private final double burnedCalories;

    public Account(String username, String password, String email,
            Date updated, Gender gender, Date birth,
            double height, double weight, double burnedCalories) {

        this.username = new String(username);
        this.password = new String(password);
        this.email = new String(email);
        this.updated = (Date) updated.clone();
        this.gender = gender;
        this.birth = (Date) birth.clone();
        this.height = height;
        this.weight = weight;
        this.burnedCalories = burnedCalories;
    }
}
