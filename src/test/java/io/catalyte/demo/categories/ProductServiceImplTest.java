package io.catalyte.demo.categories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    ProductService productService;
    @Mock
    ProductRepository productRepository;
    Product testProduct;

    @BeforeEach
    public void setUp(){
        productService = new ProductServiceImpl(productRepository);
        testProduct = new Product();
        testProduct.setName("Test");
        productRepository.save(testProduct);
    }
    @Test
    public void getProductByName(){
        ArrayList<Product> products = new ArrayList<>();
        products.add(testProduct);
        List<Product> result = productService.getProductByName("Taylor");
        assertEquals(products, result);
    }
}
