package com.maladhary.recipeBook.repository;

import com.maladhary.recipeBook.model.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodTypeRepository extends JpaRepository<FoodType, Integer> {
}
