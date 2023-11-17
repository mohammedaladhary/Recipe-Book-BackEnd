package com.maladhary.recipeBook.repository;

import com.maladhary.recipeBook.model.Recipe;
import com.maladhary.recipeBook.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class RecipeRepositoryTest {

    @Autowired
    private RecipeRepository recipeRepository;

    private Recipe recipe1;
    private Recipe recipe2;
//    private User user1;
    @BeforeEach
    void setUp() {
//        user1 = new User("Mohammed","mohammed@sda.com","Sda@ironhack");

        recipe1 = new Recipe("Pasta", 500, "Delicious pasta dish",null,null);
        recipe2 = new Recipe("Salad", 200, "Healthy salad", null, null);

        recipeRepository.saveAll(List.of(recipe1, recipe2));
    }

    @AfterEach
    void tearDown() {
        recipeRepository.deleteById(recipe1.getRecipeId());
        recipeRepository.deleteById(recipe1.getRecipeId());
    }

    @Test
    void getAllRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();

        assertEquals(2, recipes.size());
    }

    @Test
    public void testUpdateRecipe() {
        recipe1.setCalories(75);
        recipeRepository.save(recipe1);

        Recipe fromRepo = recipeRepository.findById(recipe1.getRecipeId()).get();
        assertEquals(recipe1, fromRepo);
    }
}
