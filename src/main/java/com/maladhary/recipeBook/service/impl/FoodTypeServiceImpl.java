package com.maladhary.recipeBook.service.impl;

import com.maladhary.recipeBook.model.FoodType;
import com.maladhary.recipeBook.repository.FoodTypeRepository;
import com.maladhary.recipeBook.service.interfaces.FoodTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
