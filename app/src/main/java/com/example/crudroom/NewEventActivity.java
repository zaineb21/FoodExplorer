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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crudroom.Adapter.EventAdapter;
import com.example.crudroom.ViewModal.EventViewModal;
import com.example.crudroom.model.EventModal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class NewEventActivity extends AppCompatActivity {
//idEdtEventName idEdtDesc  idEdType  idBtnSaveEvent
    private EditText EventNameEdt, DescEdt, TypeEdt;
    private Button EventBtn;

    // creating a constant string variable for our
    // course name, description and duration.
    public static final String EXTRA_ID = "com.gtappdevelopers.gfgroomdatabase.EXTRA_ID";
    public static final String EXTRA_NAME = "com.gtappdevelopers.gfgroomdatabase.EXTRA_NAME";
    public static final String EXTRA_DESC = "com.gtappdevelopers.gfgroomdatabase.EXTRA_DESC";
    public static final String EXTRA_TYPE = "com.gtappdevelopers.gfgroomdatabase.EXTRA_TYPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
        // initializing our variables for each view.
        EventNameEdt = findViewById(R.id.idEdtEventName);
        DescEdt = findViewById(R.id.idEdtDesc);
        TypeEdt = findViewById(R.id.idEdType);
        EventBtn = findViewById(R.id.idBtnSaveEvent);

        // below line is to get intent as we
        // are getting data via an intent.
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            // if we get id for our data then we are
            // setting values to our edit text fields.
            EventNameEdt.setText(intent.getStringExtra(EXTRA_NAME));
            DescEdt.setText(intent.getStringExtra(EXTRA_DESC));
            TypeEdt.setText(intent.getStringExtra(EXTRA_TYPE));
        }
        //restNameEdt, RestTypeEdt, RestAdressEdt;

        // adding on click listener for our save button.
        EventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getting text value from edittext and validating if
                // the text fields are empty or not.
                String courseName = EventNameEdt.getText().toString();
                String courseDesc = DescEdt.getText().toString();
                String courseDuration = TypeEdt.getText().toString();
                if (courseName.isEmpty() || courseDesc.isEmpty() || courseDuration.isEmpty()) {
                    Toast.makeText(NewEventActivity.this, "Please enter the valid event details.", Toast.LENGTH_SHORT).show();
                    return;
                }
                // calling a method to save our course.
                saveEvent(courseName, courseDesc, courseDuration);
            }
        });
    }

    private void saveEvent(String courseName, String courseDescription, String courseDuration) {
        // inside this method we are passing
        // all the data via an intent.
        Intent data = new Intent();

        // in below line we are passing all our course detail.
        data.putExtra(EXTRA_NAME, courseName);
        data.putExtra(EXTRA_DESC, courseDescription);
        data.putExtra(EXTRA_TYPE, courseDuration);
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            // in below line we are passing our id.
            data.putExtra(EXTRA_ID, id);
        }

        // at last we are setting result as data.
        setResult(RESULT_OK, data);

        // displaying a toast message after adding the data
        Toast.makeText(this, "Event has been saved to Room Database. ", Toast.LENGTH_SHORT).show();
    }}