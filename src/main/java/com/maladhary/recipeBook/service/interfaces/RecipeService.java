package com.maladhary.recipeBook.service.interfaces;

import com.maladhary.recipeBook.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface RecipeService {
    void deleteRecipe(Integer recipeId);
    void updateRecipe(Integer recipeId, Recipe recipe);

    String customUpdateRecipe(Integer recipeId, Map<String, Object> updates);
}
