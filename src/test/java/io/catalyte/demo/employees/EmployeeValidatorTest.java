package io.catalyte.demo.employees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeValidatorTest {

    Employee employee;
    EmployeeValidator employeeValidator;

    @BeforeEach
    public void setUp() {
        employee = new Employee(true, "John", "Doe", "john@gmail.com");
        employeeValidator = new EmployeeValidator();
    }

    @Test
    public void validateEmployee_withValidEmployee_returnsEmptyErrors() {
        String errors = employeeValidator.validateEmployee(employee);
        assertEquals(errors, "");
    }

    @Test
    public void validateEmployee_withInvalidFirstName_returnErrors() {

        employee.setFirstName(null);
        String errors = employeeValidator.validateEmployee(employee);
        assertEquals(errors, "First name cannot be null. ");

        employee.setFirstName("");
        errors = employeeValidator.validateEmployee(employee);
        assertEquals(errors, "First name cannot be empty. ");
    }

    @Test
    public void validateEmployee_withInvalidLastName_returnErrors() {

        employee.setLastName(null);
        String errors = employeeValidator.validateEmployee(employee);
        assertEquals(errors, "Last name cannot be null. ");

        employee.setLastName("");
        errors = employeeValidator.validateEmployee(employee);
        assertEquals(errors, "Last name cannot be empty. ");
    }

    @Test
    public void validateEmployee_withInvalidEmail_returnErrors() {

        employee.setEmail(null);
        String errors = employeeValidator.validateEmployee(employee);
        assertEquals(errors, "Email cannot be null. ");

        employee.setEmail("");
        errors = employeeValidator.validateEmployee(employee);
        assertEquals(errors, "Email cannot be empty. ");

        employee.setEmail("johngmail");
        errors = employeeValidator.validateEmployee(employee);
        assertEquals(errors, "Email must follow x@x.x format. ");
    }


}
