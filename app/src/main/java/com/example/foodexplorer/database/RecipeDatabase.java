package com.example.foodexplorer.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.foodexplorer.dao.RecipeDao;
import com.example.foodexplorer.entity.Recipe;

@Database(entities = {Recipe.class}, version = 1)
public abstract class RecipeDatabase extends RoomDatabase {
    public abstract RecipeDao RecipeDao();

    private static RecipeDatabase instance;
    // abstract variable for dao.


    public static synchronized RecipeDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            RecipeDatabase.class, "recipes")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
    // below line is to create a callback for our room database.
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            // this method is called when database is created
            // and below line is to populate our data.
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    // we are creating an async task class to perform task in background.
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        PopulateDbAsyncTask(RecipeDatabase instance) {
            RecipeDao recipeDao = instance.RecipeDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}