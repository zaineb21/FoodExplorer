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


import com.example.crudroom.Adapter.GaspillageRVAdapter;
import com.example.crudroom.Adapter.RestaurantRVAdapter;
import com.example.crudroom.ViewModal.GaspillageViewModal;
import com.example.crudroom.model.GaspillageModal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    // creating a variables for our recycler view.
    private RecyclerView gaspRV;
    private static final int ADD_COURSE_REQUEST = 1;
    private static final int EDIT_COURSE_REQUEST = 2;
    private GaspillageViewModal viewmodal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // initializing our variable for our recycler view and fab.
        gaspRV = findViewById(R.id.idRVGasps);
        FloatingActionButton fab = findViewById(R.id.idFABAdd3);

        // adding on click listener for floating action button.
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // starting a new activity for adding a new course
                // and passing a constant value in it.
                Intent intent = new Intent(MainActivity3.this, NewGaspillageActivity.class);
                startActivityForResult(intent, ADD_COURSE_REQUEST);
            }
        });

        // setting layout manager to our adapter class.
        gaspRV.setLayoutManager(new LinearLayoutManager(this));
        gaspRV.setHasFixedSize(true);

        // initializing adapter for recycler view.
        final GaspillageRVAdapter adapter = new GaspillageRVAdapter();

        // setting adapter class for recycler view.
        gaspRV.setAdapter(adapter);

        // passing a data from view modal.
        viewmodal = ViewModelProviders.of(this).get(GaspillageViewModal.class);

        // below line is use to get all the courses from view modal.
        viewmodal.getAllCourses().observe(this, new Observer<List<GaspillageModal>>() {
            @Override
            public void onChanged(List<GaspillageModal> models) {
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
                viewmodal.delete(adapter.getCourseAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity3.this, "Gasp deleted", Toast.LENGTH_SHORT).show();
            }
        }).
                // below line is use to attach this to recycler view.
                        attachToRecyclerView(gaspRV);
        // below line is use to set item click listener for our item of recycler view.
        adapter.setOnItemClickListener(new GaspillageRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(GaspillageModal model) {
                // after clicking on item of recycler view
                // we are opening a new activity and passing
                // a data to our activity.
                Intent intent = new Intent(MainActivity3.this, NewGaspillageActivity.class);
                intent.putExtra(NewGaspillageActivity.EXTRA_ID, model.getId());
                intent.putExtra(NewGaspillageActivity.EXTRA_TYPE, model.getTypeDeDechet());
                intent.putExtra(NewGaspillageActivity.EXTRA_QUANTITY, model.getQuantite());
                intent.putExtra(NewGaspillageActivity.EXTRA_LOCATION, model.getLieu());

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
            String courseName = data.getStringExtra(NewGaspillageActivity.EXTRA_TYPE);
            String courseDescription = data.getStringExtra(NewGaspillageActivity.EXTRA_QUANTITY);
            String courseDuration = data.getStringExtra(NewGaspillageActivity.EXTRA_LOCATION);
            GaspillageModal model = new GaspillageModal(courseName, courseDescription, courseDuration);
            viewmodal.insert(model);
            Toast.makeText(this, "Waste saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_COURSE_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(NewGaspillageActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Waste can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String courseName = data.getStringExtra(NewGaspillageActivity.EXTRA_TYPE);
            String courseDesc = data.getStringExtra(NewGaspillageActivity.EXTRA_QUANTITY);
            String courseDuration = data.getStringExtra(NewGaspillageActivity.EXTRA_LOCATION);
            GaspillageModal model = new GaspillageModal(courseName, courseDesc, courseDuration);
            model.setId(id);
            viewmodal.update(model);
            Toast.makeText(this, "Waste updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Waste not saved", Toast.LENGTH_SHORT).show();
        }
    }
}