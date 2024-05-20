package io.catalyte.demo.categories;

import java.util.List;

public interface ProductService {
    void addDummyData();
    List<Product> getProductByName(String name);
}
