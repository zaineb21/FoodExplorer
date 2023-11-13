package com.example.restaurent;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.List;


public class RestaurantRepository {

    // Below line is to create a variable
    // for dao and list for all restaurants.
    private Dao dao;
    private LiveData<List<RestaurantModal>> allRestaurants;

    // Creating a constructor for our variables
    // and passing the variables to it.
    public RestaurantRepository(Application application) {
        RestaurantDatabase database = RestaurantDatabase.getInstance(application);
        dao = database.dao();
        allRestaurants = dao.getAllRestaurants();
    }

    // Creating a method to insert the data into our database.
    public void insert(RestaurantModal model) {
        new InsertRestaurantAsyncTask(dao).execute(model);
    }

    // Creating a method to update data in the database.
    public void update(RestaurantModal model) {
        new UpdateRestaurantAsyncTask(dao).execute(model);
    }

    // Creating a method to delete the data in our database.
    public void delete(RestaurantModal model) {
        new DeleteRestaurantAsyncTask(dao).execute(model);
    }

    // Below is the method to delete all the restaurants.
    public void deleteAllRestaurants() {
        new DeleteAllRestaurantsAsyncTask(dao).execute();
    }

    // Below method is to read all the restaurants.
    public LiveData<List<RestaurantModal>> getAllRestaurants() {
        return allRestaurants;
    }

    // We are creating an async task method to insert a new restaurant.
    private static class InsertRestaurantAsyncTask extends AsyncTask<RestaurantModal, Void, Void> {
        private Dao dao;

        private InsertRestaurantAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(RestaurantModal... model) {
            // Below line is used to insert our model in dao.
            dao.insert(model[0]);
            return null;
        }
    }

    // We are creating an async task method to update our restaurant.
    private static class UpdateRestaurantAsyncTask extends AsyncTask<RestaurantModal, Void, Void> {
        private Dao dao;

        private UpdateRestaurantAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(RestaurantModal... models) {
            // Below line is used to update
            // our model in dao.
            dao.update(models[0]);
            return null;
        }
    }

    // We are creating an async task method to delete a restaurant.
    private static class DeleteRestaurantAsyncTask extends AsyncTask<RestaurantModal, Void, Void> {
        private Dao dao;

        private DeleteRestaurantAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(RestaurantModal... models) {
            // Below line is used to delete
            // our restaurant model in dao.
            dao.delete(models[0]);
            return null;
        }
    }

    // We are creating an async task method to delete all restaurants.
    private static class DeleteAllRestaurantsAsyncTask extends AsyncTask<Void, Void, Void> {
        private Dao dao;

        private DeleteAllRestaurantsAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // On the below line, calling the method
            // to delete all restaurants.
            dao.deleteAllRestaurants();
            return null;
        }
    }
}
