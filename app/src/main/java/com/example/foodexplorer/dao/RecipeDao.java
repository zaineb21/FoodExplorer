package com.example.foodexplorer.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.foodexplorer.entity.Recipe;

import java.util.List;

@Dao
public interface RecipeDao {
    @Insert
    void insertRecipe(Recipe recipe);


    @Update
    void updateRecipe(Recipe recipe);


    @Delete
    void deleteRecipe(Recipe recipe);


    @Query("SELECT * FROM recipes")
    LiveData<List<Recipe>> getAllRecipes();

    @Query("SELECT * FROM recipes WHERE id = :recipeId")
    Recipe getRecipeById(int recipeId);
}