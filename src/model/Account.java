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
            java.util.Date updated2, Gender gender, java.util.Date birth2,
            double height, double weight, double burnedCalories) throws NullPointerException {

        if (username == null || password == null || email == null
                || updated2 == null || gender == null || birth2 == null) {

            throw new NullPointerException("some parameter of account is null");
        }

        this.username = new String(username);
        this.password = new String(password);
        this.email = new String(email);
        this.updated = (Date) updated2.clone();
        this.gender = gender;
        this.birth = (Date) birth2.clone();
        this.height = height;
        this.weight = weight;
        this.burnedCalories = burnedCalories;
    }
}
