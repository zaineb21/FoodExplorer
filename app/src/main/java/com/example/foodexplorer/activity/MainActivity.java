package com.example.foodexplorer.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.foodexplorer.R;
import com.example.foodexplorer.adapter.RecipesRVAdapter;
import com.example.foodexplorer.entity.Recipe;
import com.example.foodexplorer.viewModel.RecipeViewModal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Variables for RecyclerView and ViewModel
    private RecyclerView recipesRV;
    private static final int ADD_RECIPE_REQUEST = 1;
    private static final int EDIT_RECIPE_REQUEST = 2;
    private RecipeViewModal recipeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView and FloatingActionButton
        recipesRV = findViewById(R.id.rv_recipes);
        FloatingActionButton fab = findViewById(R.id.idFABAdd);

        // Set an OnClickListener for the FloatingActionButton
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start a new activity to add a new recipe
                Intent intent = new Intent(MainActivity.this, AddRecipeActivity.class);
                startActivityForResult(intent, ADD_RECIPE_REQUEST);
            }
        });

        // Set a LayoutManager for the RecyclerView
        recipesRV.setLayoutManager(new LinearLayoutManager(this));
        recipesRV.setHasFixedSize(true);

        // Initialize the adapter for the RecyclerView
        final RecipesRVAdapter adapter = new RecipesRVAdapter();

        // Set the adapter for the RecyclerView
        recipesRV.setAdapter(adapter);

        // Initialize the RecipeViewModel
        recipeViewModel = new ViewModelProvider(this).get(RecipeViewModal.class);

        // Observe changes in the list of recipes from the ViewModel
        recipeViewModel.getAllRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                // Update the data in the adapter
                adapter.submitList(recipes);
            }
        });

        // Add swipe-to-delete functionality to the RecyclerView items
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // Delete the item from the RecyclerView and ViewModel
                recipeViewModel.deleteRecipe(adapter.getRecipeAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Recipe deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recipesRV);

        // Set an item click listener for the RecyclerView items
        adapter.setOnItemClickListener(new RecipesRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Recipe recipe) {
                // Open a new activity and pass the recipe details and ID for editing
                Intent intent = new Intent(MainActivity.this, AddRecipeActivity.class);
                intent.putExtra(AddRecipeActivity.EXTRA_ID, recipe.getId());
                intent.putExtra(AddRecipeActivity.EXTRA_RECIPE_NAME, recipe.getName());
                intent.putExtra(AddRecipeActivity.EXTRA_DESCRIPTION, recipe.getDescription());
                intent.putExtra(AddRecipeActivity.EXTRA_CATEGORY, recipe.getCategory());
                intent.putExtra(AddRecipeActivity.EXTRA_CALORIES, recipe.getCalories());

                startActivityForResult(intent, EDIT_RECIPE_REQUEST);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_RECIPE_REQUEST && resultCode == RESULT_OK) {
            // Extraction des détails de la recette depuis l'intent
            String recipeName = data.getStringExtra(AddRecipeActivity.EXTRA_RECIPE_NAME);
            String recipeDescription = data.getStringExtra(AddRecipeActivity.EXTRA_DESCRIPTION);
            String recipeCategorie = data.getStringExtra(AddRecipeActivity.EXTRA_CATEGORY);
            String recipeCalories = data.getStringExtra(AddRecipeActivity.EXTRA_CALORIES);


            // Extraction des champs supplémentaires comme la catégorie et les calories si nécessaire
            // Création d'un nouvel objet Recipe
            Recipe recipe = new Recipe(recipeName,recipeCategorie,recipeCalories, recipeDescription);
            // Insertion de la recette dans le ViewModel
            recipeViewModel.insertRecipe(recipe);
            Toast.makeText(this, "Recipe saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_RECIPE_REQUEST && resultCode == RESULT_OK) {
            // Le code que j'ai fourni précédemment doit être ajouté ici pour gérer la modification
            // d'une recette existante. Assurez-vous d'ajouter ce code dans cette partie.
        } else {
            Toast.makeText(this, "Recipe not saved", Toast.LENGTH_SHORT).show();
        }
    }

}
