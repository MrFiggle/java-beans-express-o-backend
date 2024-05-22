package io.catalyte.demo.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repository interface for managing products in the database.
 * Provides methods for accessing and manipulating product data.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}