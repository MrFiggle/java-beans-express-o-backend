package io.catalyte.demo.customers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTests {

    @Mock
    CustomerRepository customerRepository;

    CustomerService customerService;

    Customer customer1;
    Customer customer2;
    Customer customer3;
    Customer customer4;
    Customer customer5;
    Customer customer6;
    Customer customer7;
    Customer customer8;
    Customer customer9;
    Customer customer10;
    Customer customer11;

    boolean expectedResult;

    @BeforeEach
    public void setUp(){

        customerService = new CustomerServiceImpl(customerRepository);

        // Empty first name and last name
        customer1 = new Customer(true, "", "", "john@example.com", "01/01/1990", "Tea", 132.45);
        // Email missing '@'
        customer2 = new Customer(true, "John", "Doe", "johnexample.com", "01/01/1990", "Tea", 132.45);
        // Email missing domain extension
        customer3 = new Customer(true, "John", "Doe", "john@examplecom", "01/01/1990", "Tea", 132.45);
        // Email missing username
        customer4 = new Customer(true, "John", "Doe", "@example.com", "01/01/1990", "Tea", 132.45);
        // Email with blank username
        customer5 = new Customer(true, "John", "Doe", " @example.com", "01/01/1990", "Tea", 132.45);
        // Email with blank domain
        customer6 = new Customer(true, "John", "Doe", "john@.com", "01/01/1990", "Tea", 132.45);
        // Email with blank domain extension
        customer7 = new Customer(true, "John", "Doe", "john@example.", "01/01/1990", "Tea", 132.45);
        // Birthday in the future
        customer8 = new Customer(true, "John", "Doe", "john@example.com", "01/01/2090", "Tea", 132.45);
        // Invalid date format for birthday
        customer9 = new Customer(true, "John", "Doe", "john@example.com", "1990/01/01", "Tea", 132.45);
        // Invalid date format for birthday (non-existent date)
        customer10 = new Customer(true, "John", "Doe", "john@example.com", "02/30/1990", "Tea", 132.45);
        // Valid customer input
        customer11 = new Customer(true, "John", "Doe", "john@example.com", "01/01/1990", "Tea", 132.45);

        // Mock repository
        List<Customer> mockRepository = new ArrayList<>();
        mockRepository.add(customer11);

        // Mock repository behavior
        lenient().when(customerRepository.save(customer1)).thenReturn(customer1);
        lenient().when(customerRepository.save(customer2)).thenReturn(customer2);
        lenient().when(customerRepository.save(customer3)).thenReturn(customer3);
        lenient().when(customerRepository.save(customer4)).thenReturn(customer4);
        lenient().when(customerRepository.save(customer5)).thenReturn(customer5);
        lenient().when(customerRepository.save(customer6)).thenReturn(customer6);
        lenient().when(customerRepository.save(customer7)).thenReturn(customer7);
        lenient().when(customerRepository.save(customer8)).thenReturn(customer8);
        lenient().when(customerRepository.save(customer9)).thenReturn(customer9);
        lenient().when(customerRepository.save(customer10)).thenReturn(customer10);
        lenient().when(customerRepository.save(customer11)).thenReturn(customer11);
        lenient().when(customerRepository.getReferenceById(1)).thenReturn(customer11);
        lenient().when(customerRepository.existsById(1)).thenReturn(true);

    }

    @Test
    public void createCustomer_emptyName_returnsError(){
        try {
            customerService.createCustomer(customer1);
            expectedResult = false;
        } catch (ResponseStatusException e){
            boolean error400 = e.getStatusCode().equals(HttpStatus.BAD_REQUEST);
            boolean errorMessage = e.getMessage().contains("Name fields cannot be empty");
            expectedResult = error400 && errorMessage;
        }
        assertTrue(expectedResult);
    }

    @Test
    public void createCustomer_missingAtSymbol_returnsError(){
        try {
            customerService.createCustomer(customer2);
            expectedResult = false;
        } catch (ResponseStatusException e){
            boolean error400 = e.getStatusCode().equals(HttpStatus.BAD_REQUEST);
            boolean errorMessage = e.getMessage().contains("Email must follow x@x.x format");
            expectedResult = error400 && errorMessage;
        }
        assertTrue(expectedResult);
    }

    @Test
    public void createCustomer_missingDomain_returnsError(){
        try {
            customerService.createCustomer(customer3);
            expectedResult = false;
        } catch (ResponseStatusException e){
            boolean error400 = e.getStatusCode().equals(HttpStatus.BAD_REQUEST);
            boolean errorMessage = e.getMessage().contains("Email must follow x@x.x format");
            expectedResult = error400 && errorMessage;
        }
        assertTrue(expectedResult);
    }

    @Test
    public void createCustomer_missingUsername_returnsError(){
        try {
            customerService.createCustomer(customer4);
            expectedResult = false;
        } catch (ResponseStatusException e){
            boolean error400 = e.getStatusCode().equals(HttpStatus.BAD_REQUEST);
            boolean errorMessage = e.getMessage().contains("Email must follow x@x.x format");
            expectedResult = error400 && errorMessage;
        }
        assertTrue(expectedResult);
    }

    @Test
    public void createCustomer_blankUsername_returnsError(){
        try {
            customerService.createCustomer(customer5);
            expectedResult = false;
        } catch (ResponseStatusException e){
            boolean error400 = e.getStatusCode().equals(HttpStatus.BAD_REQUEST);
            boolean errorMessage = e.getMessage().contains("Email must follow x@x.x format");
            expectedResult = error400 && errorMessage;
        }
        assertTrue(expectedResult);
    }

    @Test
    public void createCustomer_blankDomain_returnsError(){
        try {
            customerService.createCustomer(customer6);
            expectedResult = false;
        } catch (ResponseStatusException e){
            boolean error400 = e.getStatusCode().equals(HttpStatus.BAD_REQUEST);
            boolean errorMessage = e.getMessage().contains("Email must follow x@x.x format");
            expectedResult = error400 && errorMessage;
        }
        assertTrue(expectedResult);
    }

    @Test
    public void createCustomer_blankDomainExtension_returnsError(){
        try {
            customerService.createCustomer(customer7);
            expectedResult = false;
        } catch (ResponseStatusException e){
            boolean error400 = e.getStatusCode().equals(HttpStatus.BAD_REQUEST);
            boolean errorMessage = e.getMessage().contains("Email must follow x@x.x format");
            expectedResult = error400 && errorMessage;
        }
        assertTrue(expectedResult);
    }

    @Test
    public void createCustomer_birthdayInFuture_returnsError(){
        try {
            customerService.createCustomer(customer8);
            expectedResult = false;
        } catch (ResponseStatusException e){
            boolean error400 = e.getStatusCode().equals(HttpStatus.BAD_REQUEST);
            boolean errorMessage = e.getMessage().contains("Birthday cannot be in the future");
            expectedResult = error400 && errorMessage;
        }
        assertTrue(expectedResult);
    }

    @Test
    public void createCustomer_invalidBirthdayFormat_returnsError(){
        try {
            customerService.createCustomer(customer9);
            expectedResult = false;
        } catch (ResponseStatusException e){
            boolean error400 = e.getStatusCode().equals(HttpStatus.BAD_REQUEST);
            boolean errorMessage = e.getMessage().contains("Must be valid date format MM/dd/yyyy");
            expectedResult = error400 && errorMessage;
        }
        assertTrue(expectedResult);
    }

    @Test
    public void createCustomer_invalidDate_returnsError(){
        try {
            customerService.createCustomer(customer10);
            expectedResult = false;
        } catch (ResponseStatusException e){
            boolean error400 = e.getStatusCode().equals(HttpStatus.BAD_REQUEST);
            boolean errorMessage = e.getMessage().contains("Must be valid date format MM/dd/yyyy");
            expectedResult = error400 && errorMessage;
        }
        assertTrue(expectedResult);
    }

    @Test
    public void createCustomer_validCustomer_returnsSavedResult(){
        try {
            Customer savedCustomer = customerService.createCustomer(customer11);
            expectedResult = savedCustomer.equals(customer11);
        } catch (ResponseStatusException e){
            expectedResult = false;
        }
        assertTrue(expectedResult);
    }

    @Test
    public void updateCustomer_emptyName_returnsError(){
        try {
            customerService.updateCustomer(customer1, 1);
            expectedResult = false;
        } catch (ResponseStatusException e){
            boolean error400 = e.getStatusCode().equals(HttpStatus.BAD_REQUEST);
            boolean errorMessage = e.getMessage().contains("Name fields cannot be empty");
            expectedResult = error400 && errorMessage;
        }
        assertTrue(expectedResult);
    }

    @Test
    public void updateCustomer_missingAtSymbol_returnsError(){
        try {
            customerService.updateCustomer(customer2, 1);
            expectedResult = false;
        } catch (ResponseStatusException e){
            boolean error400 = e.getStatusCode().equals(HttpStatus.BAD_REQUEST);
            boolean errorMessage = e.getMessage().contains("Email must follow x@x.x format");
            expectedResult = error400 && errorMessage;
        }
        assertTrue(expectedResult);
    }

    @Test
    public void updateCustomer_missingDomain_returnsError(){
        try {
            customerService.updateCustomer(customer3, 1);
            expectedResult = false;
        } catch (ResponseStatusException e){
            boolean error400 = e.getStatusCode().equals(HttpStatus.BAD_REQUEST);
            boolean errorMessage = e.getMessage().contains("Email must follow x@x.x format");
            expectedResult = error400 && errorMessage;
        }
        assertTrue(expectedResult);
    }

    @Test
    public void updateCustomer_missingUsername_returnsError(){
        try {
            customerService.updateCustomer(customer4, 1);
            expectedResult = false;
        } catch (ResponseStatusException e){
            boolean error400 = e.getStatusCode().equals(HttpStatus.BAD_REQUEST);
            boolean errorMessage = e.getMessage().contains("Email must follow x@x.x format");
            expectedResult = error400 && errorMessage;
        }
        assertTrue(expectedResult);
    }

    @Test
    public void updateCustomer_blankUsername_returnsError(){
        try {
            customerService.updateCustomer(customer5, 1);
            expectedResult = false;
        } catch (ResponseStatusException e){
            boolean error400 = e.getStatusCode().equals(HttpStatus.BAD_REQUEST);
            boolean errorMessage = e.getMessage().contains("Email must follow x@x.x format");
            expectedResult = error400 && errorMessage;
        }
        assertTrue(expectedResult);
    }

    @Test
    public void updateCustomer_blankDomain_returnsError(){
        try {
            customerService.updateCustomer(customer6, 1);
            expectedResult = false;
        } catch (ResponseStatusException e){
            boolean error400 = e.getStatusCode().equals(HttpStatus.BAD_REQUEST);
            boolean errorMessage = e.getMessage().contains("Email must follow x@x.x format");
            expectedResult = error400 && errorMessage;
        }
        assertTrue(expectedResult);
    }

    @Test
    public void updateCustomer_blankDomainExtension_returnsError(){
        try {
            customerService.updateCustomer(customer7, 1);
            expectedResult = false;
        } catch (ResponseStatusException e){
            boolean error400 = e.getStatusCode().equals(HttpStatus.BAD_REQUEST);
            boolean errorMessage = e.getMessage().contains("Email must follow x@x.x format");
            expectedResult = error400 && errorMessage;
        }
        assertTrue(expectedResult);
    }

    @Test
    public void updateCustomer_birthdayInFuture_returnsError(){
        try {
            customerService.updateCustomer(customer8, 1);
            expectedResult = false;
        } catch (ResponseStatusException e){
            boolean error400 = e.getStatusCode().equals(HttpStatus.BAD_REQUEST);
            boolean errorMessage = e.getMessage().contains("Birthday cannot be in the future");
            expectedResult = error400 && errorMessage;
        }
        assertTrue(expectedResult);
    }

    @Test
    public void updateCustomer_invalidBirthdayFormat_returnsError(){
        try {
            customerService.updateCustomer(customer9, 1);
            expectedResult = false;
        } catch (ResponseStatusException e){
            boolean error400 = e.getStatusCode().equals(HttpStatus.BAD_REQUEST);
            boolean errorMessage = e.getMessage().contains("Must be valid date format MM/dd/yyyy");
            expectedResult = error400 && errorMessage;
        }
        assertTrue(expectedResult);
    }

    @Test
    public void updateCustomer_invalidDate_returnsError(){
        try {
            customerService.updateCustomer(customer10, 1);
            expectedResult = false;
        } catch (ResponseStatusException e){
            boolean error400 = e.getStatusCode().equals(HttpStatus.BAD_REQUEST);
            boolean errorMessage = e.getMessage().contains("Must be valid date format MM/dd/yyyy");
            expectedResult = error400 && errorMessage;
        }
        assertTrue(expectedResult);
    }

    @Test
    public void updateCustomer_validCustomer_returnsSavedResult(){
        try {
            Customer savedCustomer = customerService.updateCustomer(customer11, 1);
            expectedResult = savedCustomer.equals(customer11);
        } catch (ResponseStatusException e){
            expectedResult = false;
        }
        assertTrue(expectedResult);
    }

    @Test
    public void updateCustomer_invalidId_returnError(){
        try {
            customerService.updateCustomer(customer11, 2);
            expectedResult = false;
        } catch (ResponseStatusException e){
            boolean error400 = e.getStatusCode().equals(HttpStatus.NOT_FOUND);
            boolean errorMessage = e.getMessage().contains("Customer does not exist.");
            expectedResult = error400 && errorMessage;
        }
        assertTrue(expectedResult);
    }


}