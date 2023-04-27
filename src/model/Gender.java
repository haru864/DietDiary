package model;

public enum Gender {

    MEN("men"),
    WOMEN("women");

    private final String genderString;

    private Gender(String genderString) {
        this.genderString = new String(genderString);
    }

    public String getGenderString() {
        return genderString;
    }
}
