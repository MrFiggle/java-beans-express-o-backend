package io.catalyte.demo.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller class responsible for handling HTTP requests related to products.
 * Provides endpoints for retrieving, creating, updating, and deleting products.
 */
@RestController
@RequestMapping ( value = "/products" )
public class ProductController {
  ProductService productService;

  /**
   * Creates an instance of the controller with the provided instance of the service
   * @param productService the service to be used by this controller
   */
  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Product> getAllProducts(){
    return productService.getAllProducts();
  }

  /**
   * Retrieves a list of products with the specified name.
   * @param name The name of the products to search for
   * @return a list of products with the specified name
   */
  @GetMapping("/name/{name}")
  @ResponseStatus( HttpStatus.OK )
  public List<Product> getProductByName(@PathVariable String name) {
    return productService.getProductByName( name );
  }
  /**
   * Creates a new product.
   * @param productToCreate the product to create
   * @return the created product
   */
  @PostMapping
  @ResponseStatus( HttpStatus.CREATED )
  public Product createProduct( @RequestBody Product productToCreate ) {
    return productService.createProduct( productToCreate );
  }
  /**
   * Updates an existing product.
   *
   * @param updatedProduct the updated product details
   * @param id            the ID of the product to update
   * @return the updated product
   */
  @PutMapping(value="/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Product editVendor(@RequestBody Product updatedProduct, @PathVariable int id) {
    return productService.editProduct(updatedProduct, id);
  }
  /**
   * Retrieves a product with the specified id.
   * @param id the id of the product to search for
   * @return the product with the specified id
   */
  @GetMapping ("/{id}")
  @ResponseStatus( HttpStatus.OK)
  public Product getProductById(@PathVariable int id) {
    return productService.getProductById( id );
  }
}