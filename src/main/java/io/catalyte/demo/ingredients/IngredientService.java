package io.catalyte.demo.ingredients;

/**
 * Service interface for managing ingredients.
 * Defines methods for retrieving, creating, updating, and deleting ingredients.
 */
public interface IngredientService {
    Ingredient createIngredient(Ingredient ingredientToCreate);
}
