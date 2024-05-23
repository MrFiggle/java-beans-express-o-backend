package io.catalyte.demo.products;

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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    ProductService productService;
    @Mock
    ProductRepository productRepository;
    ArrayList<Product> productsInDataBase;

    @BeforeEach
    public void setUp(){
        productService = new ProductServiceImpl(productRepository);
        productsInDataBase = new ArrayList<>();
        productsInDataBase.add(new Product(true, "Description", "Name", new ArrayList<String>(), ProductType.DRINK, DrinkType.COFFEE, BigDecimal.valueOf(12.56), new ArrayList<AllergenList>(), BigDecimal.valueOf(5.67)));
        productsInDataBase.add(new Product(true, "hi", "Latte Drink", new ArrayList<String>(), ProductType.DRINK, DrinkType.COFFEE, BigDecimal.valueOf(12.56), new ArrayList<AllergenList>(), BigDecimal.valueOf(5.67)));
        productsInDataBase.add(new Product(false, "hello", "Latte", new ArrayList<String>(), ProductType.DRINK, DrinkType.TEA, BigDecimal.valueOf(12.56), new ArrayList<AllergenList>(), BigDecimal.valueOf(1.23)));
    }
    @Test
    public void getProductByName_withValidName_returnsArrayWith2Products(){
        when(productRepository.findAll()).thenReturn(productsInDataBase);
        productsInDataBase.remove(2);
        productsInDataBase.remove(0);
        List<Product> result = productService.getProductByName("latte drink");
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
        when(productRepository.findAll()).thenReturn(productsInDataBase);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            productService.getProductByName("Candy");
        });
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("No products found.", exception.getReason());
    }
    @Test
    public void createProduct_withValidProduct_returnsPersistedProduct(){
        when(productRepository.save(any(Product.class))).thenReturn(productsInDataBase.get(1));
        Product results = productService.createProduct(productsInDataBase.get(1));
        assertEquals(productsInDataBase.get(1),results);
    }
    @Test
    public void getProductById_withValidId_returnsPersistedProduct(){
      when(productRepository.findById(anyInt())).thenReturn(Optional.of(productsInDataBase.get(1)));
      Product results = productService.getProductById(productsInDataBase.get(1).getId());
      assertEquals(productsInDataBase.get(1),results);
      assertEquals(productsInDataBase.get(1).getName(),results.getName());
    }
    @Test
    public void getProductById_withInvalidId_throwsNotFound(){
        when(productRepository.findById(anyInt())).thenReturn(Optional.empty());
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            productService.getProductById(25);
        });
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("No product found.", exception.getReason());
    }
}
