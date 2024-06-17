package io.catalyte.demo.employees;
import java.util.List;

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

    /**
     * Updates a specified employee
     *
     * @param updatedEmployee - employee object with updated info
     * @param idToEdit - id of employee to edit
     * @return - updated employee
     */
    Employee updateEmployee(Employee updatedEmployee, int idToEdit);

    /**
     * Gets a list of all employees
     *
     * @return a list of employees
     */
    public List<Employee> getAllEmployees();

}

