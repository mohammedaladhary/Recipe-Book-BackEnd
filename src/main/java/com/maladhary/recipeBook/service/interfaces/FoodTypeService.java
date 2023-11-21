package com.maladhary.recipeBook.service.interfaces;

import com.maladhary.recipeBook.model.FoodType;

import java.util.Map;

public interface FoodTypeService {
    void deleteFoodType(Integer foodTypeId);

    String customUpdateFoodType(Integer foodTypeId, Map<String, Object> updates);

    FoodType getRecipesByFoodTypeId(Integer foodTypeId);
}
