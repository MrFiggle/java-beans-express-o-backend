package io.catalyte.demo.categories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    ProductService productService;
    @Mock
    ProductRepository productRepository;
    Product testProduct;

    @BeforeEach
    public void setUp(){
        productService = new ProductServiceImpl(productRepository);
    }
    @Test
    public void getProductByName_withValidName_returnsArrayWith2Products(){
        ArrayList<Product> productsInDataBase = new ArrayList<>();
        productsInDataBase.add(new Product(true, "hi", "hi", "hi", 5, new ArrayList<String>(), "hi", "hi", BigDecimal.valueOf(12.56), new ArrayList<String>(), 45, BigDecimal.valueOf(5.67)));
        productsInDataBase.add(new Product(true, "hi", "Taylor", "hi", 5, new ArrayList<String>(), "hi", "hi", BigDecimal.valueOf(12.56), new ArrayList<String>(), 45, BigDecimal.valueOf(5.67)));
        productsInDataBase.add(new Product(true, "hi", "Taylor", "hi", 5, new ArrayList<String>(), "hi", "hi", BigDecimal.valueOf(12.56), new ArrayList<String>(), 45, BigDecimal.valueOf(5.67)));
        when(productRepository.findAll()).thenReturn(productsInDataBase);
        List<Product> result = productService.getProductByName("Taylor");
        productsInDataBase.remove(0);
        assertEquals(productsInDataBase, result);
    }
    @Test
    public void getProductByName_withNameEmpty_returnsBadRequest(){
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            productService.getProductByName("");
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Name is required.", exception.getReason());
    }
    @Test
    public void getProductByName_withInvalidName_returnsNotFound(){
        ArrayList<Product> productsInDataBase = new ArrayList<>();
        productsInDataBase.add(new Product(true, "hi", "hi", "hi", 5, new ArrayList<String>(), "hi", "hi", BigDecimal.valueOf(12.56), new ArrayList<String>(), 45, BigDecimal.valueOf(5.67)));
        productsInDataBase.add(new Product(true, "hi", "Taylor", "hi", 5, new ArrayList<String>(), "hi", "hi", BigDecimal.valueOf(12.56), new ArrayList<String>(), 45, BigDecimal.valueOf(5.67)));
        productsInDataBase.add(new Product(true, "hi", "Taylor", "hi", 5, new ArrayList<String>(), "hi", "hi", BigDecimal.valueOf(12.56), new ArrayList<String>(), 45, BigDecimal.valueOf(5.67)));
        when(productRepository.findAll()).thenReturn(productsInDataBase);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            productService.getProductByName("Candy");
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("No products found.", exception.getReason());
    }
}
