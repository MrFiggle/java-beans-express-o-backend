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
  private static List<Category> categories = new ArrayList<>();

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Category> getCategories() {
    return categories;
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Category getCategoryById(@PathVariable int id) {
    return categories.get(id-1);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Category createCategory(@RequestBody Category categoryToCreate) {
    categoryToCreate.setId(idCounter++);
    categories.add(categoryToCreate);

    return categoryToCreate;
  }

  @PutMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Category editCategory(@RequestBody Category categoryToEdit, @PathVariable int id) {
    if (categories.size() >= id - 1 && categoryToEdit.getId() == id) {
      categories.set(id - 1, categoryToEdit);
    }

    return categoryToEdit;
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteCategory(@PathVariable int id) {
    categories.remove(id - 1);
  }

}
