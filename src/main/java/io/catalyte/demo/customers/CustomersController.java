package io.catalyte.demo.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}