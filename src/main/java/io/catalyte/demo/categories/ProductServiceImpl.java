package io.catalyte.demo.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl( ProductRepository productRepository ) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProductByName(String name) {
        if (Objects.equals(name, "") || name == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name is required.");
        }

        List<Product> products = productRepository.findAll();
        ArrayList<Product> matchedProducts = new ArrayList<>();
        for (Product product : products) {
            if (Objects.equals(product.getName(), name)) {
                matchedProducts.add(product);
            }
        }
        if (matchedProducts.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products found.");
        }
        return matchedProducts;
    }
}
