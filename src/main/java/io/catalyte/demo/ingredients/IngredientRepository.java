package io.catalyte.demo.ingredients;


import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repository interface for managing ingredients in the database.
 * Provides methods for accessing and manipulating ingredient data.
 */
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
}
