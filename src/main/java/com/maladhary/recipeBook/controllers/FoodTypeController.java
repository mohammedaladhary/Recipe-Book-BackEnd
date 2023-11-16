package com.maladhary.recipeBook.controllers;

import com.maladhary.recipeBook.model.FoodType;
import com.maladhary.recipeBook.service.impl.FoodTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
