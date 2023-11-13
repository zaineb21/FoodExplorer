package com.example.crudroom.ViewModal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.crudroom.Repository.RestaurantRepository;
import com.example.crudroom.model.RestaurantModal;

import java.util.List;

public class RestViewModal extends AndroidViewModel {

    // creating a new variable for course repository.
    private RestaurantRepository repository;

    // below line is to create a variable for live
    // data where all the courses are present.
    private LiveData<List<RestaurantModal>> allRest;

    // constructor for our view modal.
    public RestViewModal(@NonNull Application application) {
        super(application);
        repository = new RestaurantRepository(application);
        allRest = repository.getAllRests();
    }

    // below method is use to insert the data to our repository.
    public void insertRest(RestaurantModal model) {
        repository.insertRest(model);
    }

    // below line is to update data in our repository.
    public void updateRest(RestaurantModal model) {
        repository.updateRest(model);
    }

    // below line is to delete the data in our repository.
    public void deleteRest(RestaurantModal model) {
        repository.deleteRest(model);
    }

    // below method is to delete all the courses in our list.
    public void deleteAllRests() {
        repository.deleteAllRests();
    }

    // below method is to get all the courses in our list.
    public LiveData<List<RestaurantModal>> getAllRests() {
        return allRest;
    }
}
