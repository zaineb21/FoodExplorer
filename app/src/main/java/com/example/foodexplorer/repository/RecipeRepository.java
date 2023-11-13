package com.example.foodexplorer.repository;
import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.foodexplorer.dao.RecipeDao;
import com.example.foodexplorer.database.RecipeDatabase;
import com.example.foodexplorer.entity.Recipe;

import java.util.List;

public class RecipeRepository {
    private RecipeDao recipeDao;
    private LiveData<List<Recipe>> allRecepies;
    public Recipe getRecipeById(int recipeId) {
        return recipeDao.getRecipeById(recipeId);
    }


    // creating a constructor for our variables
    // and passing the variables to it.
    public RecipeRepository(Application application) {
        RecipeDatabase database = RecipeDatabase.getInstance(application);
        recipeDao = database.RecipeDao();
        allRecepies=recipeDao.getAllRecipes();
    }
    //INSERT
    // creating a method to insert the data to our database.
    public void insertRecipe(Recipe recipe) {
        new InsertRecipeAsyncTask(recipeDao).execute(recipe);
    }
    //UPDATE
    // creating a method to update data in database.
    public void updateRecipe(Recipe recipe) {
        new UpdateRecipeAsyncTask(recipeDao).execute(recipe);
    }

    // creating a method to delete the data in our database.
    public void deleteRecipe(Recipe recipe) {
        new DeleteRecipeAsyncTask(recipeDao).execute(recipe);
    }

    //ALL
    public LiveData<List<Recipe>> getAllRecipes() {
        return allRecepies;
    }
    // we are creating a async task method to insert new course.
    private static class InsertRecipeAsyncTask extends AsyncTask<Recipe, Void, Void> {
        private RecipeDao recipeDao;
        private InsertRecipeAsyncTask(RecipeDao recipeDao) {
            this.recipeDao = recipeDao;
        }

        @Override
        protected Void doInBackground(Recipe... recipes) {
            // below line is use to insert our modal in dao.
            recipeDao.insertRecipe(recipes[0]);
            return null;
        }
    }
    // we are creating a async task method to update our course.
    private static class UpdateRecipeAsyncTask extends AsyncTask<Recipe, Void, Void> {
        private RecipeDao recipeDao;

        private UpdateRecipeAsyncTask(RecipeDao recipeDao) {
            this.recipeDao = recipeDao;
        }

        @Override
        protected Void doInBackground(Recipe... recipes) {
            // below line is use to update
            // our modal in dao.
            recipeDao.updateRecipe(recipes[0]);
            return null;
        }
    }
    // we are creating a async task method to delete course.
    private static class DeleteRecipeAsyncTask extends AsyncTask<Recipe, Void, Void> {
        private RecipeDao recipeDao;

        private DeleteRecipeAsyncTask(RecipeDao recipeDao) {
            this.recipeDao = recipeDao;
        }

        @Override
        protected Void doInBackground(Recipe... recipes) {
            // below line is use to delete
            // our course modal in dao.
            recipeDao.deleteRecipe(recipes[0]);
            return null;
        }
    }



}