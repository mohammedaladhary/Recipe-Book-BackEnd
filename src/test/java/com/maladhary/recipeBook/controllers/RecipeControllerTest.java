package com.maladhary.recipeBook.controllers;

import com.maladhary.recipeBook.model.Recipe;
import com.maladhary.recipeBook.service.impl.RecipeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RecipeControllerTest {

    private RecipeController recipeController;
    private RecipeServiceImpl recipeService;

    @BeforeEach
    void setUp() {
        recipeService = mock(RecipeServiceImpl.class);
        recipeController = new RecipeController(recipeService);
    }

    @Test
    void testAddRecipe() {
        // Given
        Recipe mockRecipe = new Recipe("TastyBurger", 69, "sdfssdss", null, null);
        when(recipeService.addRecipe(any(Recipe.class))).thenReturn(mockRecipe);

        // When
        ResponseEntity<String> result = recipeController.addRecipe(mockRecipe);

        // Then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Recipe added successfully", result.getBody());

        // Verify that the service method was called
        verify(recipeService, times(1)).addRecipe(any(Recipe.class));
    }

    @Test
    void testDeleteRecipe() {
        // Given
        int recipeId = 1;

        // Mocking the service method to return a success message
        doNothing().when(recipeService).deleteRecipe(recipeId);

        // When
        ResponseEntity<String> result = recipeController.deleteRecipe(recipeId);

        // Then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Recipe deleted successfully", result.getBody());

        // Verify that the service method was called
        verify(recipeService, times(1)).deleteRecipe(recipeId);
    }
}