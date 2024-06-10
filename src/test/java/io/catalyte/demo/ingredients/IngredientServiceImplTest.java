package io.catalyte.demo.ingredients;

import org.checkerframework.checker.units.qual.A;
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
        ingredientsInDatabase.add(new Ingredient(false, "TestName", new ArrayList<>()));
        when(ingredientRepository.findAll()).thenReturn(ingredientsInDatabase);
    }
    @Test
    public void createIngredient_withValidIngredient_returnsPersistedIngredient() {
        Ingredient validTestIngredient = new Ingredient(true, "TestName1", new ArrayList<>());
        when(ingredientRepository.save(any(Ingredient.class))).thenReturn(validTestIngredient);
        Ingredient results = ingredientService.createIngredient(validTestIngredient);
        assertEquals(validTestIngredient.getName(),results.getName());
    }
    @Test
    public void createIngredient_withInvalidName_throwsBadRequest() {
        Ingredient invalidName = new Ingredient(true,"nolowerCase", new ArrayList<>());
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            ingredientService.createIngredient(invalidName);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Each word in name must start with a capital letter. ",exception.getReason());

        invalidName.setName("White  Sugar");
        exception = assertThrows(ResponseStatusException.class, () -> {
            ingredientService.createIngredient(invalidName);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Name can not contain double-spaces. ",exception.getReason());

        invalidName.setName("");
        exception = assertThrows(ResponseStatusException.class, () -> {
            ingredientService.createIngredient(invalidName);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Name can not be empty. ",exception.getReason());

        invalidName.setName(null);
        exception = assertThrows(ResponseStatusException.class, () -> {
            ingredientService.createIngredient(invalidName);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Name can not be null. ",exception.getReason());

    }

    @Test
    public void createIngredient_withNonUniqueName_throwsConflict() {
        Ingredient notUniqueIngredient = new Ingredient(true, "TestName",new ArrayList<>());
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            ingredientService.createIngredient(notUniqueIngredient);
        });
        assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
        assertEquals("Ingredients can not have duplicate name.",exception.getReason());
    }
}
