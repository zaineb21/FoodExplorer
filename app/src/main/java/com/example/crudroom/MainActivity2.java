package com.example.crudroom;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.example.crudroom.Adapter.RestaurantRVAdapter;
import com.example.crudroom.ViewModal.RestViewModal;
import com.example.crudroom.ViewModal.ViewModal;
import com.example.crudroom.model.RecipeModal;
import com.example.crudroom.model.RestaurantModal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    // creating a variables for our recycler view.
    private RecyclerView restRV;
    private static final int ADD_COURSE_REQUEST = 1;
    private static final int EDIT_COURSE_REQUEST = 2;
    private RestViewModal viewmodal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // initializing our variable for our recycler view and fab.
        restRV = findViewById(R.id.idRVRests);
        FloatingActionButton fab = findViewById(R.id.idFABAdd2);

        // adding on click listener for floating action button.
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // starting a new activity for adding a new course
                // and passing a constant value in it.
                Intent intent = new Intent(MainActivity2.this, NewRestaurantActivity.class);
                startActivityForResult(intent, ADD_COURSE_REQUEST);
            }
        });

        // setting layout manager to our adapter class.
        restRV.setLayoutManager(new LinearLayoutManager(this));
        restRV.setHasFixedSize(true);

        // initializing adapter for recycler view.
        final RestaurantRVAdapter adapter = new RestaurantRVAdapter();

        // setting adapter class for recycler view.
        restRV.setAdapter(adapter);

        // passing a data from view modal.
            viewmodal = ViewModelProviders.of(this).get(RestViewModal.class);

        // below line is use to get all the courses from view modal.
        viewmodal.getAllRests().observe(this, new Observer<List<RestaurantModal>>() {
            @Override
            public void onChanged(List<RestaurantModal> models) {
                // when the data is changed in our models we are
                // adding that list to our adapter class.
                adapter.submitList(models);
            }
        });
        // below method is use to add swipe to delete method for item of recycler view.
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // on recycler view item swiped then we are deleting the item of our recycler view.
                viewmodal.deleteRest(adapter.getCourseAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity2.this, "Rest deleted", Toast.LENGTH_SHORT).show();
            }
        }).
                // below line is use to attach this to recycler view.
                        attachToRecyclerView(restRV);
        // below line is use to set item click listener for our item of recycler view.
        adapter.setOnItemClickListener(new RestaurantRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RestaurantModal model) {
                // after clicking on item of recycler view
                // we are opening a new activity and passing
                // a data to our activity.
                Intent intent = new Intent(MainActivity2.this, NewRestaurantActivity.class);
                intent.putExtra(NewRestaurantActivity.EXTRA_ID, model.getId());
                intent.putExtra(NewRestaurantActivity.EXTRA_REST_NAME, model.getName());
                intent.putExtra(NewRestaurantActivity.EXTRA_ADRESS, model.getAdress());
                intent.putExtra(NewRestaurantActivity.EXTRA_TYPE, model.getCuisineType());

                // below line is to start a new activity and
                // adding a edit course constant.
                startActivityForResult(intent, EDIT_COURSE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_COURSE_REQUEST && resultCode == RESULT_OK) {
            String courseName = data.getStringExtra(NewRestaurantActivity.EXTRA_REST_NAME);
            String courseDescription = data.getStringExtra(NewRestaurantActivity.EXTRA_ADRESS);
            String courseDuration = data.getStringExtra(NewRestaurantActivity.EXTRA_TYPE);
            RestaurantModal model = new RestaurantModal(courseName, courseDescription, courseDuration);
            viewmodal.insertRest(model);
            Toast.makeText(this, "Rest saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_COURSE_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(NewRestaurantActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Rest can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String courseName = data.getStringExtra(NewRestaurantActivity.EXTRA_REST_NAME);
            String courseDesc = data.getStringExtra(NewRestaurantActivity.EXTRA_ADRESS);
            String courseDuration = data.getStringExtra(NewRestaurantActivity.EXTRA_TYPE);
            RestaurantModal model = new RestaurantModal(courseName, courseDesc, courseDuration);
            model.setId(id);
            viewmodal.updateRest(model);
            Toast.makeText(this, "Rest updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Rest not saved", Toast.LENGTH_SHORT).show();
        }
    }
}