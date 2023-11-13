package com.example.restaurent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NewRestaurantActivity extends AppCompatActivity {

    // Creating variables for our button and edittext.
    private EditText restaurantNameEdt, cuisineTypeEdt, addressEdt;
    private Button restaurantBtn;

    // Creating constant string variables for our
    // restaurant name, cuisine type, and address.
    public static final String EXTRA_ID = "com.example.restaurent.EXTRA_ID";
    public static final String EXTRA_RESTAURANT_NAME = "com.example.restaurent.EXTRA_RESTAURANT_NAME";
    public static final String EXTRA_CUISINE_TYPE = "com.example.restaurent.EXTRA_CUISINE_TYPE";
    public static final String EXTRA_ADDRESS = "com.example.restaurent.EXTRA_ADDRESS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_restaurant);

        // Initializing our variables for each view.
        restaurantNameEdt = findViewById(R.id.idEdtRestaurantName);
        cuisineTypeEdt = findViewById(R.id.idEdtRestaurantCuisineType);
        addressEdt = findViewById(R.id.idEdtRestaurantAddress);
        restaurantBtn = findViewById(R.id.idBtnSaveRestaurant);

        // Getting intent as we are getting data via an intent.
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            // If we get id for our data then we are
            // setting values to our edit text fields.
            restaurantNameEdt.setText(intent.getStringExtra(EXTRA_RESTAURANT_NAME));
            cuisineTypeEdt.setText(intent.getStringExtra(EXTRA_CUISINE_TYPE));
            addressEdt.setText(intent.getStringExtra(EXTRA_ADDRESS));
        }

        // Adding on click listener for our save button.
        restaurantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Getting text value from edittext and validating if
                // the text fields are empty or not.
                String restaurantName = restaurantNameEdt.getText().toString();
                String cuisineType = cuisineTypeEdt.getText().toString();
                String address = addressEdt.getText().toString();
                if (restaurantName.isEmpty() || cuisineType.isEmpty() || address.isEmpty()) {
                    Toast.makeText(NewRestaurantActivity.this, "Please enter valid restaurant details.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Calling a method to save our restaurant.
                saveRestaurant(restaurantName, cuisineType, address);
            }
        });
    }

    private void saveRestaurant(String restaurantName, String cuisineType, String address) {
        // Inside this method, we are passing
        // all the data via an intent.
        Intent data = new Intent();

        // In below line, we are passing all our restaurant details.
        data.putExtra(EXTRA_RESTAURANT_NAME, restaurantName);
        data.putExtra(EXTRA_CUISINE_TYPE, cuisineType);
        data.putExtra(EXTRA_ADDRESS, address);
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            // In below line, we are passing our id.
            data.putExtra(EXTRA_ID, id);
        }

        // At last, we are setting result as data.
        setResult(RESULT_OK, data);

        // Displaying a toast message after adding the data.
        Toast.makeText(this, "Restaurant has been saved to Room Database.", Toast.LENGTH_SHORT).show();
    }
}
