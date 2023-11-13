package com.example.foodexplorer.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodexplorer.R;
import com.example.foodexplorer.entity.Recipe;

public class RecipesRVAdapter extends ListAdapter<Recipe, RecipesRVAdapter.ViewHolder> {

    private OnItemClickListener listener;

    public RecipesRVAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Recipe> DIFF_CALLBACK = new DiffUtil.ItemCallback<Recipe>() {
        @Override
        public boolean areItemsTheSame(Recipe oldItem, Recipe newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Recipe oldItem, Recipe newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getCategory().equals(newItem.getCategory()) &&
                    oldItem.getCalories().equals(newItem.getCalories());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipies_rv_item, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recipe model = getRecipeAt(position);
        holder.recipeName.setText("Recipe Name: " +model.getName());
        holder.recipeCategory.setText("Category: " + model.getCategory());
        holder.recipeCalories.setText("Calories: " + model.getCalories());
        holder.recipeDescription.setText("Description: " +model.getDescription());

    }

    public Recipe getRecipeAt(int position) {
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView recipeName, recipeDescription, recipeCategory, recipeCalories;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeName = itemView.findViewById(R.id.idTVRecipeName);
            recipeDescription = itemView.findViewById(R.id.idTVRecipeDescription);
            recipeCategory = itemView.findViewById(R.id.idTVRecipeCategory);
            recipeCalories = itemView.findViewById(R.id.idTVRecipeCaloriese);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Recipe model);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
