package io.catalyte.demo.products;

import io.catalyte.demo.util.AllergenList;
import io.catalyte.demo.vendors.Vendor;
import io.catalyte.demo.vendors.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    ProductService productService;
    @Mock
    ProductRepository productRepository;
    @Mock
    VendorRepository vendorRepository;
    ArrayList<Product> productsInDataBase;

    @BeforeEach
    public void setUp(){
        productService = new ProductServiceImpl(productRepository, vendorRepository);
        productsInDataBase = new ArrayList<>();
        productsInDataBase.add(new Product(true, "Description", "Name", new ArrayList<String>(), ProductType.DRINK, DrinkType.COFFEE, BigDecimal.valueOf(12.56), new ArrayList<AllergenList>()));
        productsInDataBase.add(new Product(true, "hi", "Latte Drink", new ArrayList<String>(), ProductType.DRINK, DrinkType.COFFEE, BigDecimal.valueOf(12.56), new ArrayList<AllergenList>()));
        productsInDataBase.add(new Product(false, "hello", "Latte", new ArrayList<String>(), ProductType.DRINK, DrinkType.TEA, BigDecimal.valueOf(12.56), new ArrayList<AllergenList>()));
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

    @Test
    public void createProduct_withInvalidVendorId_throwsBadRequest() {
        Product drink = new Product(true, "Description", "Name", new ArrayList<>(), ProductType.DRINK, DrinkType.COFFEE, BigDecimal.valueOf(12.56), new ArrayList<>());
        Product bakedGood = new Product(true, "Description", "Name", 1, new ArrayList<>(), ProductType.BAKEDGOOD, BigDecimal.valueOf(12.56), new ArrayList<>(), BigDecimal.valueOf(20));

        drink.setVendorId(1);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            productService.createProduct(drink);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals(exception.getReason(), "vendorId cannot be set for drinks. ");

        bakedGood.setVendorId(2);
        exception = assertThrows(ResponseStatusException.class, () -> {
            productService.createProduct(bakedGood);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals(exception.getReason(), "Vendor with vendorId does not exist. ");

        bakedGood.setVendorId(null);
        exception = assertThrows(ResponseStatusException.class, () -> {
            productService.createProduct(bakedGood);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals(exception.getReason(), "vendorId cannot be null for baked goods. ");
    }

    @Test
    public void createProduct_withInvalidDrinkType_throwsBadRequest() {
        when(vendorRepository.findById(1)).thenReturn(Optional.of(new Vendor()));
        Product drink = new Product(true, "Description", "Name", new ArrayList<>(), ProductType.DRINK, DrinkType.COFFEE, BigDecimal.valueOf(12.56), new ArrayList<>());
        Product bakedGood = new Product(true, "Description", "Name", 1, new ArrayList<>(), ProductType.BAKEDGOOD, BigDecimal.valueOf(12.56), new ArrayList<>(), BigDecimal.valueOf(20));

        bakedGood.setDrinkType(DrinkType.COFFEE);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            productService.createProduct(bakedGood);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals(exception.getReason(), "Drink type cannot be set for baked goods. ");

        drink.setDrinkType(null);
        exception = assertThrows(ResponseStatusException.class, () -> {
            productService.createProduct(drink);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals(exception.getReason(), "Drink type must be set for drinks. ");
    }

    @Test
    public void createProduct_withInvalidFullName_throwsBadRequest() {
        when(vendorRepository.findById(1)).thenReturn(Optional.of(new Vendor()));
        Product bakedGood = new Product(true, "Description", "Name", 1, new ArrayList<>(), ProductType.BAKEDGOOD, BigDecimal.valueOf(12.56), new ArrayList<>(), BigDecimal.valueOf(20));

        bakedGood.setName("");
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            productService.createProduct(bakedGood);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals(exception.getReason(), "Name cannot be empty. ");

        bakedGood.setName(null);
        exception = assertThrows(ResponseStatusException.class, () -> {
            productService.createProduct(bakedGood);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals(exception.getReason(), "Name cannot be null. ");
    }

    @Test
    public void createProduct_withInvalidDescription_throwsBadRequest() {
        when(vendorRepository.findById(1)).thenReturn(Optional.of(new Vendor()));
        Product bakedGood = new Product(true, "Description", "Name", 1, new ArrayList<>(), ProductType.BAKEDGOOD, BigDecimal.valueOf(12.56), new ArrayList<>(), BigDecimal.valueOf(20));

        bakedGood.setDescription("");
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            productService.createProduct(bakedGood);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals(exception.getReason(), "Description cannot be empty. ");

        bakedGood.setDescription(null);
        exception = assertThrows(ResponseStatusException.class, () -> {
            productService.createProduct(bakedGood);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals(exception.getReason(), "Description cannot be null. ");
    }

    @Test
    public void createProduct_withInvalidCost_throwsBadRequest() {
        when(vendorRepository.findById(1)).thenReturn(Optional.of(new Vendor()));
        Product bakedGood = new Product(true, "Description", "Name", 1, new ArrayList<>(), ProductType.BAKEDGOOD, BigDecimal.valueOf(12.56), new ArrayList<>(), BigDecimal.valueOf(20));

        bakedGood.setCost(BigDecimal.ZERO);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            productService.createProduct(bakedGood);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals(exception.getReason(), "Cost cannot be empty. ");

        bakedGood.setCost(null);
        bakedGood.setSalePrice(BigDecimal.valueOf(23.22));
        exception = assertThrows(ResponseStatusException.class, () -> {
            productService.createProduct(bakedGood);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals(exception.getReason(), "Cost cannot be null. ");

        bakedGood.setCost(BigDecimal.valueOf(2.333));
        exception = assertThrows(ResponseStatusException.class, () -> {
            productService.createProduct(bakedGood);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals(exception.getReason(), "Cost must have two decimals. ");

        bakedGood.setCost(BigDecimal.valueOf(-2.23));
        bakedGood.setSalePrice(BigDecimal.valueOf(2.23));
        exception = assertThrows(ResponseStatusException.class, () -> {
            productService.createProduct(bakedGood);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals(exception.getReason(), "Cost cannot be negative. ");
    }

    @Test
    public void createProduct_withInvalidMarkUpPercentage_throwsBadRequest() {
        when(vendorRepository.findById(1)).thenReturn(Optional.of(new Vendor()));
        Product drink = new Product(true, "Description", "Name", new ArrayList<>(), ProductType.DRINK, DrinkType.COFFEE, BigDecimal.valueOf(12.56), new ArrayList<>());
        Product bakedGood = new Product(true, "Description", "Name", 1, new ArrayList<>(), ProductType.BAKEDGOOD, BigDecimal.valueOf(12.56), new ArrayList<>(), BigDecimal.valueOf(20));

        bakedGood.setMarkupPercentage(null);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            productService.createProduct(bakedGood);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals(exception.getReason(), "Markup percentage cannot be null for baked goods. ");

        bakedGood.setMarkupPercentage(BigDecimal.valueOf(2.33));
        exception = assertThrows(ResponseStatusException.class, () -> {
            productService.createProduct(bakedGood);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals(exception.getReason(), "Markup percentage should be a whole number. ");

        bakedGood.setMarkupPercentage(BigDecimal.valueOf(-5));
        exception = assertThrows(ResponseStatusException.class, () -> {
            productService.createProduct(bakedGood);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals(exception.getReason(), "Markup percentage should be non negative. ");

        drink.setMarkupPercentage(BigDecimal.valueOf(10));
        exception = assertThrows(ResponseStatusException.class, () -> {
            productService.createProduct(drink);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals(exception.getReason(), "Markup percentage not applicable for drinks. ");
    }


    @Test
    public void createProduct_withInvalidSalePrice_throwsBadRequest() {
        when(vendorRepository.findById(1)).thenReturn(Optional.of(new Vendor()));
        Product bakedGood = new Product(true, "Description", "Name", 1, new ArrayList<>(), ProductType.BAKEDGOOD, BigDecimal.valueOf(12.56), new ArrayList<>(), BigDecimal.valueOf(20));

        bakedGood.setSalePrice(BigDecimal.ZERO);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            productService.createProduct(bakedGood);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals(exception.getReason(), "Sale price cannot be empty. ");

        bakedGood.setSalePrice(null);
        exception = assertThrows(ResponseStatusException.class, () -> {
            productService.createProduct(bakedGood);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals(exception.getReason(), "Sale price cannot be null. ");

        bakedGood.setSalePrice(BigDecimal.valueOf(2.333));
        exception = assertThrows(ResponseStatusException.class, () -> {
            productService.createProduct(bakedGood);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals(exception.getReason(), "Sale price must have two decimals. ");

        bakedGood.setSalePrice(BigDecimal.valueOf(-2.23));
        exception = assertThrows(ResponseStatusException.class, () -> {
            productService.createProduct(bakedGood);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals(exception.getReason(), "Sale price cannot be negative. ");
    }

    public void getAllProducts_withNoProducts_emptyList(){
        productsInDataBase = new ArrayList<>();
        lenient().when(productRepository.findAll()).thenReturn(productsInDataBase);
        List<Product> returnedList = productService.getAllProducts();
        assertTrue(returnedList.isEmpty());
    }

    @Test
    public void getAllProducts_withProducts_productsList(){
        lenient().when(productRepository.findAll()).thenReturn(productsInDataBase);
        List<Product> returnedList = productService.getAllProducts();
        assertEquals(returnedList, productsInDataBase);
    }
}
