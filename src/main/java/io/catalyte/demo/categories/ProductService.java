package io.catalyte.demo.categories;

import java.util.List;

public interface ProductService {
    List<Product> getProductByName(String name);
}
