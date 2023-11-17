package com.maladhary.recipeBook.service.interfaces;

import org.springframework.stereotype.Service;

import java.util.Map;

public interface FoodTypeService {
    void deleteFoodType(Integer foodTypeId);

    String customUpdateRecipe(Integer foodTypeId, Map<String, Object> updates);
}
