package io.catalyte.demo.products;

import java.util.List;

public interface ProductService {
    List<Product> getProductByName(String name);
}
