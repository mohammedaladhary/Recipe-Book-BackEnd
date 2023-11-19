package com.maladhary.recipeBook.controllers;

import com.maladhary.recipeBook.model.Recipe;
import com.maladhary.recipeBook.repository.RecipeRepository;
import com.maladhary.recipeBook.service.impl.RecipeServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class RecipeController {
    @Autowired
    private RecipeServiceImpl recipeServiceImpl;

    @Autowired
    private RecipeRepository recipeRepository;

    public RecipeController(RecipeServiceImpl recipeServiceImpl) {
        this.recipeServiceImpl = recipeServiceImpl;
    }

    @GetMapping("user/welcome")
        public String greetingUser(){
            return "Welcome to the Recipe Management Page...";
        }

    @GetMapping("/recipes/{recipeId}")
    @ResponseStatus(HttpStatus.OK)
    public Recipe getRecipeByRecipeId(@PathVariable(name = "recipeId") Integer recipeId) {
        return recipeRepository.findById(recipeId).get();
    }

    @GetMapping("/recipes")
    public List<Recipe> getAllRecipes() {
        return recipeServiceImpl.getAllRecipes();
    }

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

    @DeleteMapping("/recipes/delete/{recipeId}")
    public ResponseEntity<String> deleteRecipe(@PathVariable Integer recipeId) {
        try {
            recipeServiceImpl.deleteRecipe(recipeId);
            String message = "Recipe deleted successfully";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (ResponseStatusException e) {
                String errorMessage = "Recipe not found";
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @PutMapping("/recipes/update/{recipeId}")
    public ResponseEntity<String> updateRecipe(@PathVariable Integer recipeId, @RequestBody @Valid Recipe recipe) {
        try {
            recipeServiceImpl.updateRecipe(recipeId, recipe);
            String message = "Recipe updated successfully";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (ResponseStatusException e) {
            String errorMessage = "Recipe not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @PatchMapping("/recipes/custom-update/{recipeId}")
    public String customUpdateRecipe(
            @PathVariable Integer recipeId,
            @RequestBody Map<String, Object> updates){
        return recipeServiceImpl.customUpdateRecipe(recipeId, updates);
    }
}
