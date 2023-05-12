package model;

import java.util.Date;

public class UserIntake {

    public String username;
    public Date intakeDietDate;
    public int dietNumber;
    public int physicalActivityLevel;
    public double energy = 0;
    public double protein = 0;
    public double fat = 0;
    public double fiber = 0;
    public double carbohydrates = 0;
    public double vitamin_a = 0;
    public double vitamin_b1 = 0;
    public double vitamin_b2 = 0;
    public double vitamin_b6 = 0;
    public double vitamin_b12 = 0;
    public double vitamin_c = 0;
    public double vitamin_d = 0;
    public double vitamin_e = 0;
    public double vitamin_k = 0;
    public double niacin_equivalent = 0;
    public double folic_acid = 0;
    public double pantothenic_acid = 0;
    public double biotin = 0;
    public double na = 0;
    public double k = 0;
    public double ca = 0;
    public double mg = 0;
    public double p = 0;
    public double fe = 0;
    public double zn = 0;
    public double cu = 0;
    public double mn = 0;
    public double id = 0;
    public double se = 0;
    public double cr = 0;
    public double mo = 0;

    public UserIntake(String username, Date intakeDietDate, int dietNumber, int physicalActivityLevel) {
        this.username = username;
        this.intakeDietDate = intakeDietDate;
        this.dietNumber = dietNumber;
        this.physicalActivityLevel = physicalActivityLevel;
    }

}
