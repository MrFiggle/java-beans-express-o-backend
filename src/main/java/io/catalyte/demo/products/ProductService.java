package io.catalyte.demo.products;

import java.util.List;

/**
 * Service interface for managing products.
 * Defines methods for retrieving, creating, updating, and deleting products.
 */
public interface ProductService {

    /**
     * Retrieves a list of products that match the specified name.
     *
     * @param name the name of the product(s) to retrieve
     * @return a list of products with the specified name
     */
    List<Product> getProductByName(String name);

    /**
     * Creates a new product.
     *
     * @param productToCreate the product to create
     * @return the created product
     */
    Product createProduct(Product productToCreate);

    /**
     * Updates an existing product.
     *
     * @param updatedProduct the updated product details
     * @param id            the ID of the product to update
     * @return the updated product
     */
    Product editProduct(Product updatedProduct, int id);

    /**
     * Retrieves a product by its unique identifier.
     *
     * @param id the unique identifier of the product to retrieve
     * @return the product with the specified id
     */
    Product getProductById(int id);

    /**
     * Retrieves a list of all products.
     *
     * @return a list of all products
     */
    List<Product> getAllProducts();
}