package com.maladhary.recipeBook.service.interfaces;

import com.maladhary.recipeBook.model.FoodType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface FoodTypeService {
    void deleteFoodType(Integer foodTypeId);

    String customUpdateRecipe(Integer foodTypeId, Map<String, Object> updates);

    FoodType getRecipesByFoodTypeId(Integer foodTypeId);
}
