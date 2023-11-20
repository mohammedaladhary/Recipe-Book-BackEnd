package com.maladhary.recipeBook.service.impl;

import com.maladhary.recipeBook.model.FoodType;
import com.maladhary.recipeBook.model.Recipe;
import com.maladhary.recipeBook.repository.FoodTypeRepository;
import com.maladhary.recipeBook.service.interfaces.FoodTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class FoodTypeServiceImpl implements FoodTypeService {
    @Autowired
    private FoodTypeRepository foodTypeRepository;
    public List<FoodType> getAllFoodTypes() {
        return foodTypeRepository.findAll();
    }
    public FoodType addFoodType(FoodType foodType) {
        return foodTypeRepository.save(foodType);
    }
    @Override
    public void deleteFoodType(Integer foodTypeId) {
        FoodType foodTypeFromDB = foodTypeRepository.findById(foodTypeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The Food Type is not found"));
        foodTypeRepository.deleteById(foodTypeId);
    }

    @Override
    public String customUpdateRecipe(Integer foodTypeId, Map<String, Object> updates) {
        FoodType foodType = foodTypeRepository.findById(foodTypeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The Food Type is not found"));

        updateCourseAttributes(foodType, updates);

        foodTypeRepository.save(foodType);
        return "Food Type name updated successfully";
    }

    @Override
    public FoodType getRecipesByFoodTypeId(Integer foodTypeId) {
        return foodTypeRepository.findById(foodTypeId).get();
    }

    private void updateCourseAttributes(FoodType foodType, Map<String, Object> updates) {
        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            //here you can create multiple attributes to update its attributes
            if (key.equals("newFoodTypeName")) {
                foodType.setFoodTypeName(value.toString());
            } else {
                throw new IllegalArgumentException("Invalid attribute: " + key);
            }
        }
    }
}
