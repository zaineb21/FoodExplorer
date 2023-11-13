package com.example.restaurent;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Entity annotation indicates that this class represents a table in the database
// and specifies the table name.
@Entity(tableName = "restaurant_table")
public class RestaurantModal {

    // PrimaryKey annotation marks the 'id' field as the primary key,
    // and autoGenerate=true means it will be auto-incremented.
    @PrimaryKey(autoGenerate = true)
    private long id;

    // Fields representing properties of the restaurant entity.
    private String name;  // Variable for the restaurant name.
    private String cuisineType;  // Variable for the type of cuisine served.
    private String address;  // Variable for the restaurant address.

    // Constructor for initializing a restaurant object.
    // The 'id' field is not passed in the constructor as it will be auto-generated.
    public RestaurantModal(String name, String cuisineType, String address) {
        this.name = name;
        this.cuisineType = cuisineType;
        this.address = address;
    }

    // Getter and setter methods for accessing and modifying the fields.

    // Getter method for retrieving the restaurant name.
    public String getName() {
        return name;
    }

    // Setter method for setting the restaurant name.
    public void setName(String name) {
        this.name = name;
    }

    // Getter method for retrieving the type of cuisine served.
    public String getCuisineType() {
        return cuisineType;
    }

    // Setter method for setting the type of cuisine served.
    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    // Getter method for retrieving the restaurant address.
    public String getAddress() {
        return address;
    }

    // Setter method for setting the restaurant address.
    public void setAddress(String address) {
        this.address = address;
    }

    // Getter method for retrieving the auto-generated id.
    public long getId() {
        return id;
    }

    // Setter method for setting the id (auto-generated).
    public void setId(long id) {
        this.id = id;
    }
}
