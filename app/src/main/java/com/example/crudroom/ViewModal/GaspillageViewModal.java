package com.example.crudroom.ViewModal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.crudroom.Repository.GaspillageRepository;
import com.example.crudroom.Repository.RecipeRepository;
import com.example.crudroom.model.GaspillageModal;
import com.example.crudroom.model.RecipeModal;

import java.util.List;

public class GaspillageViewModal extends AndroidViewModel {

    // creating a new variable for course repository.
    private GaspillageRepository repository;

    // below line is to create a variable for live
    // data where all the courses are present.
    private LiveData<List<GaspillageModal>> allGaspss;

    // constructor for our view modal.
    public GaspillageViewModal(@NonNull Application application) {
        super(application);
        repository = new GaspillageRepository(application);
        allGaspss = repository.getallGaspss();
    }

    // below method is use to insert the data to our repository.
    public void insert(GaspillageModal model) {
        repository.insert(model);
    }

    // below line is to update data in our repository.
    public void update(GaspillageModal model) {
        repository.update(model);
    }

    // below line is to delete the data in our repository.
    public void delete(GaspillageModal model) {
        repository.delete(model);
    }

    // below method is to delete all the courses in our list.
    public void deleteAllCourses() {
        repository.deleteallGaspss();
    }

    // below method is to get all the courses in our list.
    public LiveData<List<GaspillageModal>> getAllCourses() {
        return allGaspss;
    }
}