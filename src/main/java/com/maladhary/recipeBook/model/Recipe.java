package com.maladhary.recipeBook.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recipeId;

    @Column(unique = true)
    private String recipeName;

    private int calories;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "foodTypeId")
    @JsonBackReference // Add this annotation to break the circular reference
    private FoodType foodType;

    public Recipe(String recipeName, int calories, String description, User user, FoodType foodType) {
        this.recipeName = recipeName;
        this.calories = calories;
        this.description = description;
        this.user = user;
        this.foodType = foodType;
    }
}