package model;

public enum Gender {

    men("men"),
    women("women");

    private final String genderString;

    private Gender(String genderString) {
        this.genderString = new String(genderString);
    }

    public String getGenderString() {
        return genderString;
    }
}
