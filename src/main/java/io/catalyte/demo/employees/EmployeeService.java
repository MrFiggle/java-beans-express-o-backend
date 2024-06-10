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
     * Gets a list of all employees
     *
     * @return a list of employees
     */
    public List<Employee> getAllEmployees();
}
