package com.maladhary.recipeBook.service.impl;

import com.maladhary.recipeBook.model.Recipe;
import com.maladhary.recipeBook.repository.RecipeRepository;
import com.maladhary.recipeBook.service.interfaces.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
