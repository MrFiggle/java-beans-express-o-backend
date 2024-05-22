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
   * @param productService the repository to be used by this controller
   */
  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  /**
   * Retrieves a list of products with the specified name.
   * @param name The name of the products to search for
   * @return a list of products with the specified name
   */
  @GetMapping
  @ResponseStatus( HttpStatus.OK )
  public List<Product> getProductByName(@RequestParam String name) {
    return productService.getProductByName(name);
  }

}
