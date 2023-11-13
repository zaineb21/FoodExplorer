package com.example.crudroom.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.crudroom.model.GaspillageModal;

import java.util.List;

@androidx.room.Dao

public interface GaspillageDao {
    // below method is use to
    // add data to database.
    @Insert
    void insertGasp(GaspillageModal model);

    // below method is use to update
    // the data in our database.
    @Update
    void updateGasp(GaspillageModal model);

    // below line is use to delete a
    // specific course in our database.
    @Delete
    void deleteGasp(GaspillageModal model);

    // on below line we are making query to
    // delete all courses from our database.
    @Query("DELETE FROM gaspillage_table")
    void deleteAllGasps();

    // below line is to read all the courses from our database.
    // in this we are ordering our courses in ascending order
    // with our course name.
    @Query("SELECT * FROM gaspillage_table ORDER BY typeDeDechet ASC")
    LiveData<List<GaspillageModal>> getAllGasps();
}

