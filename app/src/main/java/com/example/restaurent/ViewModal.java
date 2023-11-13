package com.example.restaurent;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class ViewModal extends AndroidViewModel {

    // Creating a new variable for restaurant repository.
    private RestaurantRepository repository;

    // Below line is to create a variable for live
    // data where all the restaurants are present.
    private LiveData<List<RestaurantModal>> allRestaurants;

    // Constructor for our view model.
    public ViewModal(@NonNull Application application) {
        super(application);
        repository = new RestaurantRepository(application);
        allRestaurants = repository.getAllRestaurants();
    }

    // Below method is used to insert the data into our repository.
    public void insert(RestaurantModal model) {
        repository.insert(model);
    }

    // Below line is used to update data in our repository.
    public void update(RestaurantModal model) {
        repository.update(model);
    }

    // Below line is used to delete the data in our repository.
    public void delete(RestaurantModal model) {
        repository.delete(model);
    }

    // Below method is used to delete all the restaurants in our list.
    public void deleteAllRestaurants() {
        repository.deleteAllRestaurants();
    }

    // Below method is used to get all the restaurants in our list.
    public LiveData<List<RestaurantModal>> getAllRestaurants() {
        return allRestaurants;
    }
}
