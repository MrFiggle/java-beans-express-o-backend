package io.catalyte.demo.ingredients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
/**
 * Implementation of the IngredientService interface.
 * Provides functionality related to managing ingredients.
 */
@Service
public class IngredientServiceImpl implements IngredientService{
    IngredientValidator ingredientValidator = new IngredientValidator();
    IngredientRepository ingredientRepository;
    /**
     * Creates an instance of the service with the provided instance of the repository
     * @param ingredientRepository the repository to be used by this service
     */
    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }
    /**
     * Creates a new product.
     * @param ingredientToCreate the ingredient to create
     * @return the created ingredient
     */
    @Override
    public Ingredient createIngredient(Ingredient ingredientToCreate) {
        String errorCollector = "";
        errorCollector = errorCollector + ingredientValidator.validateName(ingredientToCreate.getName());
        List<Ingredient> ingredients = ingredientRepository.findAll();
        for (Ingredient ingredient: ingredients) {
            if (Objects.equals(ingredientToCreate.getName(), ingredient.getName())){
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Ingredients can not have duplicate name.");
            }
        }
        if(!errorCollector.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorCollector);
        }
        return ingredientRepository.save(ingredientToCreate);
    }
}
