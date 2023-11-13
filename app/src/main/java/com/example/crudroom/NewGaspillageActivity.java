package com.example.crudroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewGaspillageActivity extends AppCompatActivity {
//idEdtTypeGasp idEdtQuantite  idEdtLocation  idBtnSaveGasp
    private EditText TypeGaspEdt, QuantiteEdt, LocationEdt;
    private Button gspBtn;

    // creating a constant string variable for our
    // course name, description and duration.
    public static final String EXTRA_ID = "com.gtappdevelopers.gfgroomdatabase.EXTRA_ID";
    public static final String EXTRA_TYPE = "com.gtappdevelopers.gfgroomdatabase.EXTRA_TYPE";
    public static final String EXTRA_QUANTITY = "com.gtappdevelopers.gfgroomdatabase.EXTRA_QUANTITY";
    public static final String EXTRA_LOCATION = "com.gtappdevelopers.gfgroomdatabase.EXTRA_LOCATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_gaspillage);
        // initializing our variables for each view.
        TypeGaspEdt = findViewById(R.id.idEdtTypeGasp);
        QuantiteEdt = findViewById(R.id.idEdtQuantite);
        LocationEdt = findViewById(R.id.idEdtLocation);
        gspBtn = findViewById(R.id.idBtnSaveGasp);

        // below line is to get intent as we
        // are getting data via an intent.
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            // if we get id for our data then we are
            // setting values to our edit text fields.
            TypeGaspEdt.setText(intent.getStringExtra(EXTRA_TYPE));
            QuantiteEdt.setText(intent.getStringExtra(EXTRA_QUANTITY));
            LocationEdt.setText(intent.getStringExtra(EXTRA_LOCATION));
        }
        //restNameEdt, RestTypeEdt, RestAdressEdt;

        // adding on click listener for our save button.
        gspBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getting text value from edittext and validating if
                // the text fields are empty or not.
                String courseName = TypeGaspEdt.getText().toString();
                String courseDesc = QuantiteEdt.getText().toString();
                String courseDuration = LocationEdt.getText().toString();
                if (courseName.isEmpty() || courseDesc.isEmpty() || courseDuration.isEmpty()) {
                    Toast.makeText(NewGaspillageActivity.this, "Please enter the valid waste details.", Toast.LENGTH_SHORT).show();
                    return;
                }
                // calling a method to save our course.
                saveGaspillage(courseName, courseDesc, courseDuration);
            }
        });
    }

    private void saveGaspillage(String courseName, String courseDescription, String courseDuration) {
        // inside this method we are passing
        // all the data via an intent.
        Intent data = new Intent();

        // in below line we are passing all our course detail.
        data.putExtra(EXTRA_TYPE, courseName);
        data.putExtra(EXTRA_QUANTITY, courseDescription);
        data.putExtra(EXTRA_LOCATION, courseDuration);
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            // in below line we are passing our id.
            data.putExtra(EXTRA_ID, id);
        }

        // at last we are setting result as data.
        setResult(RESULT_OK, data);

        // displaying a toast message after adding the data
        Toast.makeText(this, "Waste has been saved to Room Database. ", Toast.LENGTH_SHORT).show();
    }
}