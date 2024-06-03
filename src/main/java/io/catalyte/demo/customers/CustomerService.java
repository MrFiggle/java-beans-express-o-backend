package io.catalyte.demo.customers;

/**
 * Service interface for handling CRUD operations on Customer entities.
 */
public interface CustomerService {

    /**
     * Creates a new customer
     *
     * @return the created customer
     */
    Customer createCustomer(Customer customerToCreate);
}