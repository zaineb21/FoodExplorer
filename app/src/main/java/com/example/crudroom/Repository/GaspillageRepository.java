package com.example.crudroom.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import com.example.crudroom.Dao.GaspillageDao;

import com.example.crudroom.Database.GaspillageDatabase;
import com.example.crudroom.model.GaspillageModal;
import com.example.crudroom.model.RecipeModal;
import com.example.crudroom.model.RestaurantModal;


import java.util.List;

public class GaspillageRepository {
    // below line is the create a variable
    // for dao and list for all courses.
    private GaspillageDao dao;
    private LiveData<List<GaspillageModal>> allGaspss;

    // creating a constructor for our variables
    // and passing the variables to it.
    public GaspillageRepository(Application application) {
        GaspillageDatabase database = GaspillageDatabase.getInstance(application);
        dao = database.GaspillageDao();
        allGaspss = dao.getAllGasps();
    }

    // creating a method to insert the data to our database.
    public void insert(GaspillageModal model) {
        new GaspillageRepository.InsertGaspAsyncTask(dao).execute(model);
    }

    // creating a method to update data in database.
    public void update(GaspillageModal model) {
        new GaspillageRepository.UpdateGaspAsyncTask(dao).execute(model);
    }

    // creating a method to delete the data in our database.
    public void delete(GaspillageModal model) {
        new GaspillageRepository.DeleteGaspAsyncTask(dao).execute(model);
    }

    // below is the method to delete all the courses.
    public void deleteallGaspss() {
        new GaspillageRepository.DeleteallGaspssAsyncTask(dao).execute();
    }

    // below method is to read all the courses.
    public LiveData<List<GaspillageModal>> getallGaspss() {
        return allGaspss;
    }

    // we are creating a async task method to insert new course.
    private static class InsertGaspAsyncTask extends AsyncTask<GaspillageModal, Void, Void> {
        private GaspillageDao dao;

        private InsertGaspAsyncTask(GaspillageDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(GaspillageModal... model) {
            // below line is use to insert our modal in dao.
            dao.insertGasp(model[0]);
            return null;
        }
    }

    // we are creating a async task method to update our course.
    private static class UpdateGaspAsyncTask extends AsyncTask<GaspillageModal, Void, Void> {
        private GaspillageDao dao;

        private UpdateGaspAsyncTask(GaspillageDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(GaspillageModal... models) {
            // below line is use to update
            // our modal in dao.
            dao.updateGasp(models[0]);
            return null;
        }
    }

    // we are creating a async task method to delete course.
    private static class DeleteGaspAsyncTask extends AsyncTask<GaspillageModal, Void, Void> {
        private GaspillageDao dao;

        private DeleteGaspAsyncTask(GaspillageDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(GaspillageModal... models) {
            // below line is use to delete
            // our course modal in dao.
            dao.deleteGasp(models[0]);
            return null;
        }
    }

    // we are creating a async task method to delete all courses.
    private static class DeleteallGaspssAsyncTask extends AsyncTask<Void, Void, Void> {
        private GaspillageDao dao;
        private DeleteallGaspssAsyncTask(GaspillageDao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            // on below line calling method
            // to delete all courses.
            dao.deleteAllGasps();
            return null;
        }
    }
}

