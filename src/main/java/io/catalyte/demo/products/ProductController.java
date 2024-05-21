package io.catalyte.demo.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ( value = "/api/products" )
public class ProductController {
  ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  @ResponseStatus( HttpStatus.OK )
  public List<Product> getProductByName(@RequestParam String name) {
    return productService.getProductByName(name);
  }

}
