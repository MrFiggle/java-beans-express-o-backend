package io.catalyte.demo.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling CRUD operations in Employee entities
 */
@RestController
@RequestMapping(value = "/employees")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {

    EmployeeService employeeService;

    /**
     * Creates an instance of the controller with provided service instance
     * @param employeeService the service to be used by this controller
     */
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Creates a new employee
     *
     * @param employeeToCreate employee to be created
     * @return created employee
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employeeToCreate) {
        return employeeService.createEmployee(employeeToCreate);
    }

}
