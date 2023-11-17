package com.maladhary.recipeBook.controllers;

import com.maladhary.recipeBook.model.FoodType;
import com.maladhary.recipeBook.service.impl.FoodTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class FoodTypeController {
    @Autowired
    private FoodTypeServiceImpl foodTypeServiceImpl;

    @GetMapping("/foodtype")
    @ResponseStatus(HttpStatus.OK)
    public List<FoodType> getAllFoodTypes() {
        return foodTypeServiceImpl.getAllFoodTypes();
    }

    @PostMapping("/foodtype/new")
    @ResponseStatus(HttpStatus.CREATED)
    public FoodType addFoodType(@RequestBody FoodType foodType) {
        return foodTypeServiceImpl.addFoodType(foodType);
    }

    @DeleteMapping("/foodtype/delete/{foodTypeId}")
    public ResponseEntity<String> deleteFoodType(@PathVariable Integer foodTypeId) {
        try {
            foodTypeServiceImpl.deleteFoodType(foodTypeId);
            String message = "Food Type deleted successfully";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (ResponseStatusException e) {
            String errorMessage = "Food Type not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @PatchMapping("/foodtype/custom-update/{foodTypeId}")
    public String customUpdateRecipe(
            @PathVariable Integer foodTypeId,
            @RequestBody Map<String, Object> updates){
        return foodTypeServiceImpl.customUpdateRecipe(foodTypeId, updates);
    }
}
