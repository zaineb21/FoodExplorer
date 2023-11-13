package com.example.restaurent;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

// Adding annotation
// to our Dao class
@androidx.room.Dao
public interface Dao {

    // Insert a restaurant into the database.
    @Insert
    void insert(RestaurantModal restaurant);

    // Update a restaurant in the database.
    @Update
    void update(RestaurantModal restaurant);

    // Delete a specific restaurant from the database.
    @Delete
    void delete(RestaurantModal restaurant);

    // Delete all restaurants from the database.
    @Query("DELETE FROM restaurant_table")
    void deleteAllRestaurants();



    // below line is to read all the courses from our database.
    // in this we are ordering our courses in ascending order
    // with our course name.
    @Query("SELECT * FROM restaurant_table ORDER BY name ASC")
    LiveData<List<RestaurantModal>> getAllRestaurants();
}
