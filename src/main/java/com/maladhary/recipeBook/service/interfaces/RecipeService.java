package com.maladhary.recipeBook.service.interfaces;

import com.maladhary.recipeBook.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RecipeService {
    void deleteRecipe(Integer recipeId);
    void updateRecipe(Integer recipeId, Recipe recipe);

}
