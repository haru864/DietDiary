package com.model;

public class FoodGroup implements Comparable<FoodGroup> {

    public int foodGroupNumber;
    public String foodGroupName;

    public FoodGroup(int foodGroupNumber, String foodGroupName) {
        this.foodGroupNumber = foodGroupNumber;
        this.foodGroupName = foodGroupName;
    }

    @Override
    public int compareTo(FoodGroup foodGroup) {

        return foodGroupNumber - foodGroup.foodGroupNumber;
    }

}
