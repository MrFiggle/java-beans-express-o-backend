package io.catalyte.demo.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Implementation of the ProductService interface.
 * Provides functionality related to managing products.
 */
@Service
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;
    /**
     * Creates an instance of the service with the provided instance of the repository
     * @param productRepository the repository to be used by this service
     */
    @Autowired
    public ProductServiceImpl( ProductRepository productRepository ) {
        this.productRepository = productRepository;
    }
    /**
     * Retrieves a list of products with the specified name.
     * @param name The name of the products to search for
     * @return a list of products with the specified name
     * @throws ResponseStatusException if name is empty/null, or if no products are found
     */
    @Override
    public List<Product> getProductByName(String name) {
        if (Objects.equals(name, "") || name == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name is required.");
        }
        List<Product> products = productRepository.findAll();
        ArrayList<Product> matchedProducts = new ArrayList<>();
        for (Product product : products) {
            if (Objects.equals(product.getName().toLowerCase(), name.toLowerCase())) {
                matchedProducts.add(product);
            }
        }
        if (matchedProducts.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products found.");
        }
        return matchedProducts;
    }
    /**
     * Creates a new product.
     * @param productToCreate the product to create
     * @return the created product
     */
    @Override
    public Product createProduct(Product productToCreate) {
        return productRepository.save(productToCreate);
    }
}
