package com.example.crudroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewRestaurantActivity extends AppCompatActivity {

    private EditText restNameEdt, RestTypeEdt, RestAdressEdt;
    private Button restBtn;

    // creating a constant string variable for our
    // course name, description and duration.
    public static final String EXTRA_ID = "com.gtappdevelopers.gfgroomdatabase.EXTRA_ID";
    public static final String EXTRA_REST_NAME = "com.gtappdevelopers.gfgroomdatabase.EXTRA_REST_NAME";
    public static final String EXTRA_TYPE = "com.gtappdevelopers.gfgroomdatabase.EXTRA_TYPE";
    public static final String EXTRA_ADRESS = "com.gtappdevelopers.gfgroomdatabase.EXTRA_ADRESS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_restaurant);
        // initializing our variables for each view.
        restNameEdt = findViewById(R.id.idEdtRestName);
        RestTypeEdt = findViewById(R.id.idEdtRestType);
        RestAdressEdt = findViewById(R.id.idEdtRestAdress);
        restBtn = findViewById(R.id.idBtnSaveRest);

        // below line is to get intent as we
        // are getting data via an intent.
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            // if we get id for our data then we are
            // setting values to our edit text fields.
            restNameEdt.setText(intent.getStringExtra(EXTRA_REST_NAME));
            RestTypeEdt.setText(intent.getStringExtra(EXTRA_TYPE));
            RestAdressEdt.setText(intent.getStringExtra(EXTRA_ADRESS));
        }
        //restNameEdt, RestTypeEdt, RestAdressEdt;

        // adding on click listener for our save button.
        restBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getting text value from edittext and validating if
                // the text fields are empty or not.
                String courseName = restNameEdt.getText().toString();
                String courseDesc = RestTypeEdt.getText().toString();
                String courseDuration = RestAdressEdt.getText().toString();
                if (courseName.isEmpty() || courseDesc.isEmpty() || courseDuration.isEmpty()) {
                    Toast.makeText(NewRestaurantActivity.this, "Please enter the valid restaurant details.", Toast.LENGTH_SHORT).show();
                    return;
                }
                // calling a method to save our course.
                saveRestaurant(courseName, courseDesc, courseDuration);
            }
        });
    }

    private void saveRestaurant(String courseName, String courseDescription, String courseDuration) {
        // inside this method we are passing
        // all the data via an intent.
        Intent data = new Intent();

        // in below line we are passing all our course detail.
        data.putExtra(EXTRA_REST_NAME, courseName);
        data.putExtra(EXTRA_TYPE, courseDescription);
        data.putExtra(EXTRA_ADRESS, courseDuration);
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            // in below line we are passing our id.
            data.putExtra(EXTRA_ID, id);
        }

        // at last we are setting result as data.
        setResult(RESULT_OK, data);

        // displaying a toast message after adding the data
        Toast.makeText(this, "Restaurant has been saved to Room Database. ", Toast.LENGTH_SHORT).show();
    }
}