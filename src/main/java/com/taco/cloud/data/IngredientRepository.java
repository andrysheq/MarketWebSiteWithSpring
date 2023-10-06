package com.taco.cloud.data;

import com.taco.cloud.models.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository
        extends CrudRepository<Ingredient, String> {
}

