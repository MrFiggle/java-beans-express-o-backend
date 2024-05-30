package io.catalyte.demo.ingredients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * Controller class responsible for handling HTTP requests related to ingredients.
 * Provides endpoints for retrieving, creating, updating, and deleting ingredients.
 */
@RestController
@RequestMapping (value = "/ingredients")
public class IngredientController {
    IngredientService ingredientService;

    /**
     * Creates an instance of the controller with the provided instance of the service
     * @param ingredientService the service to be used by this controller
     */
    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }
    /**
     * Creates a new ingredient.
     * @param ingredientToCreate the ingredient to create.
     * @return the created ingredient
     */
    @PostMapping
    public Ingredient createIngredient(@RequestBody Ingredient ingredientToCreate) {
        return ingredientService.createIngredient(ingredientToCreate);
    }
}
