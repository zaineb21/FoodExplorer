package com.example.restaurent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Creating variables for our recycler view.
    private RecyclerView restaurantsRV;
    private static final int ADD_RESTAURANT_REQUEST = 1;
    private static final int EDIT_RESTAURANT_REQUEST = 2;
    private ViewModal viewModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing our variable for our recycler view and fab.
        restaurantsRV = findViewById(R.id.idRVRestaurants);
        FloatingActionButton fab = findViewById(R.id.idFABAdd);

        // Adding on click listener for floating action button.
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Starting a new activity for adding a new restaurant
                // and passing a constant value in it.
                Intent intent = new Intent(MainActivity.this, NewRestaurantActivity.class);
                startActivityForResult(intent, ADD_RESTAURANT_REQUEST);
            }
        });

        // Setting layout manager to our adapter class.
        restaurantsRV.setLayoutManager(new LinearLayoutManager(this));
        restaurantsRV.setHasFixedSize(true);

        // Initializing adapter for recycler view.
        final RestaurantRVAdapter adapter = new RestaurantRVAdapter();

        // Setting adapter class for recycler view.
        restaurantsRV.setAdapter(adapter);

        // Passing data from view modal.
        viewModal = ViewModelProviders.of(this).get(ViewModal.class);

        // Below line is used to get all the restaurants from view modal.
        viewModal.getAllRestaurants().observe(this, new Observer<List<RestaurantModal>>() {
            @Override
            public void onChanged(List<RestaurantModal> models) {
                // When the data is changed in our models, we are
                // adding that list to our adapter class.
                adapter.submitList(models);
            }
        });

        // Below method is used to add swipe to delete method for items of recycler view.
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // On recycler view item swiped, then we are deleting the item of our recycler view.
                viewModal.delete(adapter.getRestaurantAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Restaurant deleted", Toast.LENGTH_SHORT).show();
            }
        }).

                // Below line is used to attach this to recycler view.
                        attachToRecyclerView(restaurantsRV);

        // Below line is used to set item click listener for our item of recycler view.
        adapter.setOnItemClickListener(new RestaurantRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RestaurantModal model) {
                // After clicking on item of recycler view,
                // we are opening a new activity and passing
                // data to our activity.
                Intent intent = new Intent(MainActivity.this, NewRestaurantActivity.class);
                intent.putExtra(NewRestaurantActivity.EXTRA_ID, model.getId());
                intent.putExtra(NewRestaurantActivity.EXTRA_RESTAURANT_NAME, model.getName());
                intent.putExtra(NewRestaurantActivity.EXTRA_CUISINE_TYPE, model.getCuisineType());
                intent.putExtra(NewRestaurantActivity.EXTRA_ADDRESS, model.getAddress());

                // Below line is to start a new activity and
                // adding an edit restaurant constant.
                startActivityForResult(intent, EDIT_RESTAURANT_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_RESTAURANT_REQUEST && resultCode == RESULT_OK) {
            String restaurantName = data.getStringExtra(NewRestaurantActivity.EXTRA_RESTAURANT_NAME);
            String cuisineType = data.getStringExtra(NewRestaurantActivity.EXTRA_CUISINE_TYPE);
            String address = data.getStringExtra(NewRestaurantActivity.EXTRA_ADDRESS);
            RestaurantModal model = new RestaurantModal(restaurantName, cuisineType, address);
            viewModal.insert(model);
            Toast.makeText(this, "Restaurant saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_RESTAURANT_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(NewRestaurantActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Restaurant can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String restaurantName = data.getStringExtra(NewRestaurantActivity.EXTRA_RESTAURANT_NAME);
            String cuisineType = data.getStringExtra(NewRestaurantActivity.EXTRA_CUISINE_TYPE);
            String address = data.getStringExtra(NewRestaurantActivity.EXTRA_ADDRESS);
            RestaurantModal model = new RestaurantModal(restaurantName, cuisineType, address);
            model.setId(id);
            viewModal.update(model);
            Toast.makeText(this, "Restaurant updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Restaurant not saved", Toast.LENGTH_SHORT).show();
        }
    }
}


