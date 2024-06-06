package io.catalyte.demo.employees;

/**
 * Service interface for handling CRUD operations on Employee entities
 */
public interface EmployeeService {

    /**
     * Creates a new employee
     *
     * @param employeeToCreate - employee object to be created
     * @return created employee
     */
    Employee createEmployee(Employee employeeToCreate);
}
