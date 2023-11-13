package com.example.foodexplorer.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipes")
public class Recipe {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String image;
    private String description;
    private String category;
    private String calories;

    public Recipe() {
    }

    public Recipe(int id, String name, String image, String description, String category, String calories) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.category = category;
        this.calories = calories;
    }

    public Recipe(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Recipe(String name, String image, String description, String category, String calories) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.category = category;
        this.calories = calories;
    }

    public Recipe(String recipeName, String recipeDescription) {
    }

    public Recipe(String recipeName, String recipeCategorie, String recipeCalories, String recipeDescription) {
    }

    // Les getters et setters pour id, name et description

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }
}
