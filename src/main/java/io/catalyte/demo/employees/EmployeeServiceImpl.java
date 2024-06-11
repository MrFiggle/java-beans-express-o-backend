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

    /**
     * Updates a specified employee
     *
     * @param updatedEmployee - employee object with updated info
     * @param idToEdit - id of employee to edit
     * @return - updated employee
     */
    public Employee updateEmployee(Employee updatedEmployee, int idToEdit) {
        String errors = "";
        EmployeeValidator employeeValidator = new EmployeeValidator();

        errors += employeeValidator.validateEmployee(updatedEmployee);

        if (errors.isEmpty()) {
            if (employeeRepository.existsById(idToEdit)){
                Employee updated = employeeRepository.getReferenceById(idToEdit);
                updated.setActive(updatedEmployee.isActive());
                updated.setFirstName(updatedEmployee.getFirstName());
                updated.setLastName(updatedEmployee.getLastName());
                updated.setEmail(updatedEmployee.getEmail());
                return employeeRepository.save(updated);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id does not exist.");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors);
        }
    }
}