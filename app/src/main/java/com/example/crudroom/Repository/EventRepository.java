package com.example.crudroom.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.crudroom.Dao.EventDao;

import com.example.crudroom.Database.EventDatabase;

import com.example.crudroom.model.EventModal;


import java.util.List;

public class EventRepository {
    private EventDao dao;
    private LiveData<List<EventModal>> allEvents;
    // creating a constructor for our variables
    // and passing the variables to it.
    public EventRepository(Application application) {
        EventDatabase database = EventDatabase.getInstance(application);
        dao = database.EventDao();
        allEvents = dao.getAllEvents();
    }

    // creating a method to insert the data to our database.
    public void insert(EventModal model) {
        new EventRepository.InsertEventAsyncTask(dao).execute(model);
    }

    // creating a method to update data in database.
    public void update(EventModal model) {
        new EventRepository.UpdateEventAsyncTask(dao).execute(model);
    }

    // creating a method to delete the data in our database.
    public void delete(EventModal model) {
        new EventRepository.DeleteEventAsyncTask(dao).execute(model);
    }

    // below is the method to delete all the courses.
    public void DeleteallEvents() {
        new EventRepository.DeleteallEventsAsyncTask(dao).execute();
    }

    // below method is to read all the courses.
    public LiveData<List<EventModal>> getallEvents() {
        return allEvents;
    }

    // we are creating a async task method to insert new course.
    private static class InsertEventAsyncTask extends AsyncTask<EventModal, Void, Void> {
        private EventDao dao;

        private InsertEventAsyncTask(EventDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(EventModal... model) {
            // below line is use to insert our modal in dao.
            dao.insertEvent(model[0]);
            return null;
        }
    }

    // we are creating a async task method to update our course.
    private static class UpdateEventAsyncTask extends AsyncTask<EventModal, Void, Void> {
        private EventDao dao;

        private UpdateEventAsyncTask(EventDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(EventModal... models) {
            // below line is use to update
            // our modal in dao.
            dao.updateEvent(models[0]);
            return null;
        }
    }

    // we are creating a async task method to delete course.
    private static class DeleteEventAsyncTask extends AsyncTask<EventModal, Void, Void> {
        private EventDao dao;

        private DeleteEventAsyncTask(EventDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(EventModal... models) {
            // below line is use to delete
            // our course modal in dao.
            dao.deleteEvent(models[0]);
            return null;
        }
    }

    // we are creating a async task method to delete all courses.
    private static class DeleteallEventsAsyncTask extends AsyncTask<Void, Void, Void> {
        private EventDao dao;
        private DeleteallEventsAsyncTask(EventDao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            // on below line calling method
            // to delete all courses.
            dao.deleteAllEvents();
            return null;
        }
    }
}


