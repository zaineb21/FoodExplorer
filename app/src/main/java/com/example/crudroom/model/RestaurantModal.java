package com.example.crudroom.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "restaurant_table")
public class RestaurantModal {
    @PrimaryKey(autoGenerate = true)

    // variable for our id.
    private int id;
    private String name;
    private String cuisineType;
    private String adress;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public RestaurantModal(String name, String cuisineType, String adress) {
        this.name = name;
        this.cuisineType = cuisineType;
        this.adress = adress;
    }
}
