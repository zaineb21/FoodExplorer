package com.example.crudroom.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;



import com.example.crudroom.Dao.RestaurantDao;

import com.example.crudroom.Database.RestaurantDatabase;

import com.example.crudroom.model.RestaurantModal;

import java.util.List;

public class RestaurantRepository {
    // below line is the create a variable
    // for dao and list for all courses.
    private RestaurantDao dao;
    private LiveData<List<RestaurantModal>> allRest;

    // creating a constructor for our variables
    // and passing the variables to it.
    public RestaurantRepository(Application application) {
        RestaurantDatabase database = RestaurantDatabase.getInstance(application);
        dao = database.RestaurantDao();
        allRest = dao.getAllRestaurants();
    }

    // creating a method to insert the data to our database.
    public void insertRest(RestaurantModal model) {
        new RestaurantRepository.InsertRestaurantAsyncTask(dao).execute(model);
    }

    // creating a method to update data in database.
    public void updateRest(RestaurantModal model) {
        new RestaurantRepository.UpdateRestaurantAsyncTask(dao).execute(model);
    }

    // creating a method to delete the data in our database.
    public void deleteRest(RestaurantModal model) {
        new RestaurantRepository.DeleteRestaurantAsyncTask(dao).execute(model);
    }

    // below is the method to delete all the courses.
    public void deleteAllRests() {
        new RestaurantRepository.DeleteAllRestsAsyncTask(dao).execute();
    }

    // below method is to read all the courses.
    public LiveData<List<RestaurantModal>> getAllRests() {
        return allRest;
    }


    // we are creating a async task method to insert new course.
    private static class InsertRestaurantAsyncTask extends AsyncTask<RestaurantModal, Void, Void> {
        private RestaurantDao dao;

        private InsertRestaurantAsyncTask(RestaurantDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(RestaurantModal... model) {
            // below line is use to insert our modal in dao.
            dao.insert(model[0]);
            return null;
        }
    }

    // we are creating a async task method to update our course.
    private static class UpdateRestaurantAsyncTask extends AsyncTask<RestaurantModal, Void, Void> {
        private RestaurantDao dao;

        private UpdateRestaurantAsyncTask(RestaurantDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(RestaurantModal... models) {
            // below line is use to update
            // our modal in dao.
            dao.update(models[0]);
            return null;
        }
    }

    // we are creating a async task method to delete course.
    private static class DeleteRestaurantAsyncTask extends AsyncTask<RestaurantModal, Void, Void> {
        private RestaurantDao dao;

        private DeleteRestaurantAsyncTask(RestaurantDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(RestaurantModal... models) {
            // below line is use to delete
            // our course modal in dao.
            dao.delete(models[0]);
            return null;
        }
    }

    // we are creating a async task method to delete all courses.
    private static class DeleteAllRestsAsyncTask extends AsyncTask<Void, Void, Void> {
        private RestaurantDao dao;
        private DeleteAllRestsAsyncTask(RestaurantDao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            // on below line calling method
            // to delete all courses.
            dao.deleteAllRestaurants();
            return null;
        }
    }
}

