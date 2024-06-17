package io.catalyte.demo.employees;

import io.catalyte.demo.util.Validator;

/**
 * Validator class that checks employee object for errors
 */
public class EmployeeValidator extends Validator {

    /**
     * Creates an instance of Employee Validator
     */
    public EmployeeValidator() {

    }

    /**
     * Checks for errors in the employee
     * @param employeeToValidate the employee that needs to be validated
     * @return String with error messages relating to the employee
     */
    public String validateEmployee(Employee employeeToValidate) {

        String errors = "";

        errors += validateFirstName(employeeToValidate.getFirstName());
        errors += validateLastName(employeeToValidate.getLastName());
        errors += validateEmail(employeeToValidate.getEmail());

        return errors;

    }
}
