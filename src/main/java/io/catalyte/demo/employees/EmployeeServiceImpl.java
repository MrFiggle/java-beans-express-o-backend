package io.catalyte.demo.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Implementation of the EmployeeService interface.
 * Provides functionality related to managing employees.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Creates and saves employee to the database
     *
     * @param employeeToCreate - employee object to be created
     * @return new created employee
     */
    public Employee createEmployee(Employee employeeToCreate) {
        String errors = "";
        EmployeeValidator employeeValidator = new EmployeeValidator();

        errors += employeeValidator.validateEmployee(employeeToCreate);

        if (!errors.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors);
        }

        return employeeRepository.save(employeeToCreate);
    }
}
