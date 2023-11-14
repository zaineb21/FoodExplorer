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


import com.example.crudroom.Adapter.EventAdapter;
import com.example.crudroom.ViewModal.EventViewModal;
import com.example.crudroom.model.EventModal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity4 extends AppCompatActivity {

    // creating a variables for our recycler view.
    private RecyclerView eventRV;
    private static final int ADD_COURSE_REQUEST = 1;
    private static final int EDIT_COURSE_REQUEST = 2;
    private EventViewModal viewmodal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        // initializing our variable for our recycler view and fab.
        eventRV = findViewById(R.id.idRVEvents);
        FloatingActionButton fab = findViewById(R.id.idFABAdd4);

        // adding on click listener for floating action button.
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // starting a new activity for adding a new course
                // and passing a constant value in it.
                Intent intent = new Intent(MainActivity4.this, NewEventActivity.class);
                startActivityForResult(intent, ADD_COURSE_REQUEST);
            }
        });

        // setting layout manager to our adapter class.
        eventRV.setLayoutManager(new LinearLayoutManager(this));
        eventRV.setHasFixedSize(true);

        // initializing adapter for recycler view.
        final EventAdapter adapter = new EventAdapter();

        // setting adapter class for recycler view.
        eventRV.setAdapter(adapter);

        // passing a data from view modal.
        viewmodal = ViewModelProviders.of(this).get(EventViewModal.class);

        // below line is use to get all the courses from view modal.
        viewmodal.getAllCourses().observe(this, new Observer<List<EventModal>>() {
            @Override
            public void onChanged(List<EventModal> models) {
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
                Toast.makeText(MainActivity4.this, "Event deleted", Toast.LENGTH_SHORT).show();
            }
        }).
                // below line is use to attach this to recycler view.
                        attachToRecyclerView(eventRV);
        // below line is use to set item click listener for our item of recycler view.
        adapter.setOnItemClickListener(new EventAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(EventModal model) {
                // after clicking on item of recycler view
                // we are opening a new activity and passing
                // a data to our activity.
                Intent intent = new Intent(MainActivity4.this, NewEventActivity.class);
                intent.putExtra(NewEventActivity.EXTRA_ID, model.getId());
                intent.putExtra(NewEventActivity.EXTRA_NAME, model.getEventName());
                intent.putExtra(NewEventActivity.EXTRA_TYPE, model.getType());
                intent.putExtra(NewEventActivity.EXTRA_DESC, model.getDescription());

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
            String courseName = data.getStringExtra(NewEventActivity.EXTRA_NAME);
            String courseDescription = data.getStringExtra(NewEventActivity.EXTRA_DESC);
            String courseDuration = data.getStringExtra(NewEventActivity.EXTRA_TYPE);
            EventModal model = new EventModal(courseName, courseDescription, courseDuration);
            viewmodal.insert(model);
            Toast.makeText(this, "Waste saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_COURSE_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(NewEventActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Waste can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String courseName = data.getStringExtra(NewEventActivity.EXTRA_NAME);
            String courseDesc = data.getStringExtra(NewEventActivity.EXTRA_DESC);
            String courseDuration = data.getStringExtra(NewEventActivity.EXTRA_TYPE);
            EventModal model = new EventModal(courseName, courseDesc, courseDuration);
            model.setId(id);
            viewmodal.update(model);
            Toast.makeText(this, "Event updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Event not saved", Toast.LENGTH_SHORT).show();
        }
    }
}