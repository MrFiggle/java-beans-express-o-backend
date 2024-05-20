package io.catalyte.demo.categories;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categories")
public class CategoriesController {

  private static int idCounter = 1;
  private static List<Product> categories = new ArrayList<>();

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Product> getCategories() {
    return categories;
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Product getCategoryById(@PathVariable int id) {
    return categories.get(id-1);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Product createCategory(@RequestBody Product productToCreate) {
    productToCreate.setId(idCounter++);
    categories.add(productToCreate);

    return productToCreate;
  }

  @PutMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Product editCategory(@RequestBody Product productToEdit, @PathVariable int id) {
    if (categories.size() >= id - 1 && productToEdit.getId() == id) {
      categories.set(id - 1, productToEdit);
    }

    return productToEdit;
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteCategory(@PathVariable int id) {
    categories.remove(id - 1);
  }

}
