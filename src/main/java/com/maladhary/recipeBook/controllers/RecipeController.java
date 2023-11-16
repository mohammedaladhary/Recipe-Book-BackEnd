package com.maladhary.recipeBook.controllers;

import com.maladhary.recipeBook.model.Recipe;
import com.maladhary.recipeBook.model.User;
import com.maladhary.recipeBook.service.impl.RecipeServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class RecipeController {
    @Autowired
    private RecipeServiceImpl recipeServiceImpl;

    public RecipeController(RecipeServiceImpl recipeServiceImpl) {
        this.recipeServiceImpl = recipeServiceImpl;
    }

    @GetMapping("/welcome")
        public String greetingUser(){
            return "Welcome User to the Finance Tracker Management Page...";
        }

    @GetMapping("/recipes")
    public List<Recipe> getAllRecipes() {
        return recipeServiceImpl.getAllRecipes();
    }

//    @PostMapping("/recipes/new")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Recipe addRecipe(@RequestBody Recipe recipe) {
//        return recipeServiceImpl.addRecipe(recipe);
//    }
    @PostMapping("/recipes/new")
    public ResponseEntity<String> addRecipe(@RequestBody @Valid Recipe recipe){
        try {
            //Use the service layer add method to add the course
            recipeServiceImpl.addRecipe(recipe);
            String message = "Recipe added successfully";
            // return the response status
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e){
            // Saving the error message in a variable and calling getMessage Method
            // to get a detailed error message from exception class
            String errorMessage = "Recipe not added successfully" + e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }
}
