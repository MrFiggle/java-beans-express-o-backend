package io.catalyte.demo.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Implementation of the CustomerService interface.
 * Provides functionality related to managing customers.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;
    CustomerValidator validation;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
        this.validation = new CustomerValidator();
    }

    /**
     * Creates and saves customer to the database after validating user input
     *
     * @param customerToCreate new customer entity to create
     * @return new created customer
     */
    public Customer createCustomer(Customer customerToCreate) {

        List<String> errors = validation.validateCustomer(customerToCreate);

        if (errors.isEmpty()){

            return customerRepository.save(customerToCreate);
        } else {

            String errorMessage = String.join(", ", errors);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
        }
    }
}