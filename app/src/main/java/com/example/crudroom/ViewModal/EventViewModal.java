package com.example.crudroom.ViewModal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.crudroom.Repository.EventRepository;
import com.example.crudroom.model.EventModal;

import java.util.List;

public class EventViewModal  extends AndroidViewModel {

    // creating a new variable for course repository.
    private EventRepository repository;

    // below line is to create a variable for live
    // data where all the courses are present.
    private LiveData<List<EventModal>> allEventss;

    // constructor for our view modal.
    public EventViewModal(@NonNull Application application) {
        super(application);
        repository = new EventRepository(application);
        allEventss = repository.getallEvents();
    }

    // below method is use to insert the data to our repository.
    public void insert(EventModal model) {
        repository.insert(model);
    }

    // below line is to update data in our repository.
    public void update(EventModal model) {
        repository.update(model);
    }

    // below line is to delete the data in our repository.
    public void delete(EventModal model) {
        repository.delete(model);
    }

    // below method is to delete all the courses in our list.
    public void deleteAllCourses() {
        repository.DeleteallEvents();
    }

    // below method is to get all the courses in our list.
    public LiveData<List<EventModal>> getAllCourses() {
        return allEventss;
    }
}
