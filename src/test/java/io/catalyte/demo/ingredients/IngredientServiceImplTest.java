package io.catalyte.demo.ingredients;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class IngredientServiceImplTest {
    IngredientService ingredientService;
    @Mock
    IngredientRepository ingredientRepository;
    ArrayList<Ingredient> ingredientsInDatabase;

    @BeforeEach
    public void setUp(){
        ingredientService = new IngredientServiceImpl(ingredientRepository);
        ingredientsInDatabase = new ArrayList<>();
        ingredientsInDatabase.add(new Ingredient(false, "TestName",BigDecimal.valueOf(2.25),"kg",BigDecimal.valueOf(0.5),new ArrayList<>()));
        ingredientsInDatabase.add(new Ingredient());
        when(ingredientRepository.findAll()).thenReturn(ingredientsInDatabase);
    }
    @Test
    public void createIngredient_withValidIngredient_returnsPersistedIngredient() {
        Ingredient validTestIngredient = new Ingredient(true, "TestName", BigDecimal.valueOf(1.25),"lbs",BigDecimal.valueOf(1),new ArrayList<>());
        when(ingredientRepository.save(any(Ingredient.class))).thenReturn(validTestIngredient);
        Ingredient results = ingredientService.createIngredient(validTestIngredient);
        assertEquals(validTestIngredient.getName(),results.getName());
    }
    @Test
    public void createIngredient_withInvalidName_throwsBadRequest() {
        Ingredient invalidName = new Ingredient(true,"nolowerCase",BigDecimal.valueOf(1.25),"lbs",BigDecimal.valueOf(1),new ArrayList<>());
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            ingredientService.createIngredient(invalidName);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Each word in name must start with a capital letter. ",exception.getReason());
    }
    @Test
    public void createIngredient_withInvalidCost_throwsBadRequest() {
        Ingredient invalidCost = new Ingredient(true,"Test Name",BigDecimal.valueOf(1),"lbs",BigDecimal.valueOf(1.25),new ArrayList<>());
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            ingredientService.createIngredient(invalidCost);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Cost must have two decimals. ", exception.getReason());
    }
    @Test
    public void createIngredient_withInvalidUnitAmount_throwsBadRequest() {
        Ingredient invalidUnitAmount = new Ingredient(true, "TestNAme",BigDecimal.valueOf(1.25),"lbs",BigDecimal.valueOf(1.234),new ArrayList<>());
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            ingredientService.createIngredient(invalidUnitAmount);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Unit amount can only have up to two decimals. ", exception.getReason());
    }
    @Test
    public void createIngredient_withInvalidUnitOfMeasure_throwsBadRequest() {
        Ingredient invalidUnitOfMeasure = new Ingredient(true, "TestName", BigDecimal.valueOf(1.25),"Lbs",BigDecimal.valueOf(1),new ArrayList<>());
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            ingredientService.createIngredient(invalidUnitOfMeasure);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Unit of measure must be all lowercase. ", exception.getReason());
    }
    @Test
    public void createIngredient_withNonUniqueNameUnitOfMeasure_throwsConflict() {
        Ingredient notUniqueIngredient = new Ingredient(true, "TestName", BigDecimal.valueOf(3.75), "kg",BigDecimal.valueOf(1.87),new ArrayList<>());
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            ingredientService.createIngredient(notUniqueIngredient);
        });
        assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
        assertEquals("Ingredients can not have duplicate name and unit of measure.",exception.getReason());
    }
}
