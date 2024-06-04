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

    /**
     * Updates a new customer
     *
     * @param customerUpdated - new updated customer information
     * @param idToEdit - id of customer that needs editing
     * @return - updated customer
     */
    Customer updateCustomer(Customer customerUpdated, int idToEdit);
}