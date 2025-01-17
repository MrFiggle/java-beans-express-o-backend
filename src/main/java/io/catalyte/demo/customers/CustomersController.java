package io.catalyte.demo.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling CRUD operations on Customer entities.
 */
@RestController
@RequestMapping(value = "/customers")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomersController {

    @Autowired
    private CustomerService customerService;

    /**
     * Creates a new customer
     *
     * @param customerToCreate customer to be added
     * @return the created customer
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody Customer customerToCreate){
        return  customerService.createCustomer(customerToCreate);
    }

    /**
     * Updates a specific customer
     *
     * @param customerUpdated - customer object with updated info
     * @param id - id of customer to edit
     * @return - updated customer
     */
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer editCustomer(@RequestBody Customer customerUpdated, @PathVariable int id){
        return customerService.updateCustomer(customerUpdated, id);
    }

    /**
     * Retrieves all customers
     *
     * @return a list of customers
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

}