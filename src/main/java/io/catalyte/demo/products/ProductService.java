package io.catalyte.demo.products;

import java.util.List;
/**
 * Service interface for managing products.
 * Defines methods for retrieving, creating, updating, and deleting products.
 */
public interface ProductService {
    List<Product> getProductByName(String name);
    Product createProduct(Product productToCreate);
    Product getProductById(int id);
}
