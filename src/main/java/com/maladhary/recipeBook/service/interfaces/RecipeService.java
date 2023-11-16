package com.maladhary.recipeBook.service.interfaces;

import com.maladhary.recipeBook.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RecipeService {
//    void updateRecipe(Integer userId, Recipe recipe);

    void deleteRecipe(Integer recipeId);
}
