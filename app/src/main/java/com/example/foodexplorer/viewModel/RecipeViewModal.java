package com.example.foodexplorer.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.foodexplorer.entity.Recipe;
import com.example.foodexplorer.repository.RecipeRepository;

import java.util.List;

public class RecipeViewModal extends AndroidViewModel {
    // creating a new variable for recipe repository.
    private RecipeRepository repository;

    // below line is to create a variable for live
    // data where all the recipes are present.
    private LiveData<List<Recipe>> allRecipes;

    // constructor for our view model.
    public RecipeViewModal(@NonNull Application application) {
        super(application);
        repository = new RecipeRepository(application);
        allRecipes = repository.getAllRecipes();
    }

    // below method is used to insert the data into our repository.
    public void insertRecipe(Recipe model) {
        repository.insertRecipe(model);
    }

    // below line is used to update data in our repository.
    public void updateRecipe(Recipe model) {
        repository.updateRecipe(model);
    }

    // below line is used to delete the data in our repository.
    public void deleteRecipe(Recipe model) {
        repository.deleteRecipe(model);
    }

    // below method is used to get all the recipes in our list.
    public LiveData<List<Recipe>> getAllRecipes() {
        return allRecipes;
    }
    public Recipe getRecipeById(int recipeId) {
        return repository.getRecipeById(recipeId);
    }

}
