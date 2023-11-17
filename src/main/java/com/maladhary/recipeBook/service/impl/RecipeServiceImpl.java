package com.maladhary.recipeBook.service.impl;

import com.maladhary.recipeBook.model.Recipe;
import com.maladhary.recipeBook.repository.RecipeRepository;
import com.maladhary.recipeBook.service.interfaces.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe addRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public void deleteRecipe(Integer recipeId) {
        Recipe recipeFromDB = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The Recipe is not found"));
        recipeRepository.deleteById(recipeId);
    }

    @Override
    public void updateRecipe(Integer recipeId, Recipe recipe) {
        Recipe recipeFromDB = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The Recipe is not found"));
        // Set the updated values to the recipeFromDB
        recipeFromDB.setRecipeName(recipe.getRecipeName());
        recipeFromDB.setCalories(recipe.getCalories());
        recipeFromDB.setDescription(recipe.getDescription());
        // Set other properties as needed
        recipeRepository.save(recipeFromDB);
    }

    @Override
    public String customUpdateRecipe(Integer recipeId, Map<String, Object> updates) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The Recipe is not found"));

        updateCourseAttributes(recipe, updates);

        recipeRepository.save(recipe);
        return "Recipe name updated successfully";
    }

    private void updateCourseAttributes(Recipe recipe, Map<String, Object> updates) {
        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            //here you can create multiple attributes to update its attributes
            if (key.equals("newRecipeName")) {
                recipe.setRecipeName(value.toString());
            } else {
                throw new IllegalArgumentException("Invalid attribute: " + key);
            }
        }
    }
}
