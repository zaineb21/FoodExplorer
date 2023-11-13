package com.example.crudroom.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.crudroom.model.RestaurantModal;

import java.util.List;

@androidx.room.Dao
public interface RestaurantDao {
    // below method is use to
    // add data to database.
    @Insert
    void insert(RestaurantModal model);

    // below method is use to update
    // the data in our database.
    @Update
    void update(RestaurantModal model);

    // below line is use to delete a
    // specific course in our database.
    @Delete
    void delete(RestaurantModal model);

    // on below line we are making query to
    // delete all courses from our database.
    @Query("DELETE FROM restaurant_table")
    void deleteAllRestaurants();

    // below line is to read all the courses from our database.
    // in this we are ordering our courses in ascending order
    // with our course name.
    @Query("SELECT * FROM restaurant_table ORDER BY Name ASC")
    LiveData<List<RestaurantModal>> getAllRestaurants();
}

