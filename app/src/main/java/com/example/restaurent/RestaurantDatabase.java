package com.example.restaurent;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

// Adding annotation for our database entities and db version.
@Database(entities = {RestaurantModal.class}, version = 1)
public abstract class RestaurantDatabase extends RoomDatabase {

    // Below line is to create an instance
    // for our database class.
    private static RestaurantDatabase instance;

    // Below line is to create
    // an abstract variable for dao.
    public abstract Dao dao();

    // On below line, we are getting an instance for our database.
    public static synchronized RestaurantDatabase getInstance(Context context) {
        // Below line is to check if
        // the instance is null or not.
        if (instance == null) {
            // If the instance is null,
            // we are creating a new instance.
            instance =
                    // For creating an instance for our database,
                    // we are creating a database builder and passing
                    // our database class with our database name.
                    Room.databaseBuilder(context.getApplicationContext(),
                                    RestaurantDatabase.class, "restaurant_database")
                            // Below line is used to add fallback to
                            // destructive migration to our database.
                            .fallbackToDestructiveMigration()
                            // Below line is to add a callback
                            // to our database.
                            .addCallback(roomCallback)
                            // Below line is to
                            // build our database.
                            .build();
        }
        // After creating an instance,
        // we are returning our instance.
        return instance;
    }

    // Below line is to create a callback for our room database.
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            // This method is called when the database is created
            // and below line is to populate our data.
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    // We are creating an async task class to perform tasks in the background.
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private Dao dao;

        // Constructor to get Dao instance from RestaurantDatabase.
        PopulateDbAsyncTask(RestaurantDatabase instance) {
            dao = instance.dao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // Background task to insert sample data into the database.
            dao.insert(new RestaurantModal("GoSushi", "Cuisine Japonaise", "3 Av. Youssef Rouissi, Tunis"));
            dao.insert(new RestaurantModal("Slayta", "Bar à salade", "54 avenue de l'environnement, Tunis 2045"));
            dao.insert(new RestaurantModal("Via Mercato", "Cuisine italienne", " Immeuble Cercle des Bureaux, Tunis"));

            // You can add more sample data insertions or additional background tasks here.

            return null;
        }
    }
}
