package com.example.foodexplorer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foodexplorer.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddRecipeActivity extends AppCompatActivity {

    // creating a variables for our button and edittext.
    private EditText recipeNameEdt, recipeDescEdt, recipeCaloriesEdt,recipeCategorieEdt;
    private Button addRecipeButton;

    public static final String EXTRA_ID = "com.example.foodexplorer.EXTRA_ID";
    public static final String EXTRA_RECIPE_NAME = "com.example.foodexplorer.EXTRA_RECIPE_NAME";
   // public static final String EXTRA_COOKING_TIME = "com.example.foodexplorer.EXTRA_COOKING_TIME";
    public static final String EXTRA_CALORIES = "com.example.foodexplorer.EXTRA_CALORIES";
    public static final String EXTRA_CATEGORY = "com.example.foodexplorer.EXTRA_CATEGORY";
    public static final String EXTRA_DESCRIPTION = "com.example.foodexplorer.EXTRA_DESCRIPTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        // initializing our variables for each view.
        recipeNameEdt = findViewById(R.id.idEdtRecipeName);
        recipeDescEdt = findViewById(R.id.idEdtRecipeDescription);
        recipeCaloriesEdt = findViewById(R.id.idEdtRecipeCaories);
        recipeCategorieEdt = findViewById(R.id.idEdtRecipeCategory);


        // recipeImageView = findViewById(R.id.img_recipe);
        addRecipeButton = findViewById(R.id.btn_add_recipe);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            recipeNameEdt.setText(intent.getStringExtra(EXTRA_RECIPE_NAME));
            recipeDescEdt.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            recipeCaloriesEdt.setText(intent.getStringExtra(EXTRA_CALORIES));
            recipeCategorieEdt.setText(intent.getStringExtra(EXTRA_CATEGORY));
        }

        addRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recipeName = recipeNameEdt.getText().toString();
                String description = recipeDescEdt.getText().toString();
                String calories = recipeCaloriesEdt.getText().toString();
                String category = recipeCategorieEdt.getText().toString();

                if (recipeName.isEmpty() || description.isEmpty() || calories.isEmpty() || category.isEmpty() || description.isEmpty()) {
                    Toast.makeText(AddRecipeActivity.this, "Please enter all the recipe details.", Toast.LENGTH_SHORT).show();
                    return;
                }

                saveRecipe(recipeName, description, calories, category);
            }
        });
    }

    private void saveRecipe(String recipeName, String calories, String category, String description) {
        Intent data = new Intent();

        data.putExtra(EXTRA_RECIPE_NAME, recipeName);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_CALORIES, calories);
        data.putExtra(EXTRA_CATEGORY, category);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        Toast.makeText(this, "Recipe has been saved.", Toast.LENGTH_SHORT).show();
    }
}
