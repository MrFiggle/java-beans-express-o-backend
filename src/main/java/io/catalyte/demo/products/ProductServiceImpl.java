package io.catalyte.demo.products;

import io.catalyte.demo.vendors.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Implementation of the ProductService interface.
 * Provides functionality related to managing products.
 */
@Service
public class ProductServiceImpl implements ProductService {
    private final VendorRepository vendorRepository;
    private final ProductRepository productRepository;

    /**
     * Creates an instance of the service with the provided instance of the repository
     * @param productRepository the repository to be used by this service
     * @param vendorRepository the vendor repository to be used by this service
     */
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, VendorRepository vendorRepository) {
        this.productRepository = productRepository;
        this.vendorRepository = vendorRepository;
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
        String errors = "";
        ProductValidator productValidator = new ProductValidator(vendorRepository);

        errors = errors + productValidator.validateProduct(productToCreate);

        if (!errors.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors);
        }

        productToCreate.calculateSalePrice();
        return productRepository.save(productToCreate);
    }

    /**
     * Edits an existing product with new information.
     *
     * @param updatedProduct the updated product information
     * @param id the ID of the product to edit
     * @return the updated product
     * @throws ResponseStatusException if the ID in the updated product is set or the product with the specified ID is not found
     */
    @Override
    public Product editProduct(Product updatedProduct, int id) {
        if (updatedProduct.getId() > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id field forbidden");
        }

        Product savedProduct = getProductById(id);

        String errors = "";
        ProductValidator productValidator = new ProductValidator(vendorRepository);

        errors = errors + productValidator.validateProduct(updatedProduct);

        if (!errors.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors);
        }

        savedProduct.setName(updatedProduct.getName());
        savedProduct.setDescription(updatedProduct.getDescription());
        savedProduct.setActive(updatedProduct.getActive());
        savedProduct.setAllergens(updatedProduct.getAllergens());
        savedProduct.setClassification(updatedProduct.getClassification());
        savedProduct.setVendorId(updatedProduct.getVendorId());
        savedProduct.setIngredients(updatedProduct.getIngredients());
        savedProduct.setDrinkType(updatedProduct.getDrinkType());
        savedProduct.setCost(updatedProduct.getCost());
        savedProduct.setMarkupPercentage(updatedProduct.getMarkupPercentage());

        savedProduct.calculateSalePrice();
        return  productRepository.save(savedProduct);
    }

    /**
     * Retrieves a product with the specified id.
     * @param id the id of the product to search for
     * @return the product with the specified id
     */
    @Override
    public Product getProductById(int id) {
        Optional<Product> potentialProduct = productRepository.findById(id);
        if (potentialProduct.isPresent()) {
            return potentialProduct.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No product found.");
        }
    }

    /**
     * Retrieves a list of all products.
     *
     * @return a list of all products
     */
    @Override
    public List<Product> getAllProducts() {
        try {
            return productRepository.findAll();
        } catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "There was an unexpected internal server issue while fetching your data");
        }
    }
}