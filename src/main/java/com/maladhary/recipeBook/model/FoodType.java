package com.maladhary.recipeBook.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.maladhary.recipeBook.model.Recipe;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int foodTypeId;

    @Column(unique = true)
    private String foodTypeName;

    @OneToMany(mappedBy = "foodType")
    @JsonManagedReference // Add this annotation to break the circular reference
    private List<Recipe> recipes;

    public FoodType(String name) {
        this.foodTypeName = name;
    }
}