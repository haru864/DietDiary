package model;

public enum Gender {

    MEN("men"),
    WOMEN("women");

    private final String gender;

    private Gender(String gender) {
        this.gender = new String(gender);
    }
}
