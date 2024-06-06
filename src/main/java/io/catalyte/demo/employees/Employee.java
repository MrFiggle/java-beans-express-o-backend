package io.catalyte.demo.employees;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Represents an employee entity in the database
 */
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean active;
    private String firstName;
    private String lastName;
    private String email;

    /**
     * Creates an instance of Employee with no values
     */
    public Employee() {
        this.active = true;
        this.firstName = "";
        this.lastName = "";
        this.email = "";
    }

    /**
     * Create an instance of Employee with the provided fields
     *
     * @param active Boolean value to determine the active status of employee
     * @param firstName String value that stores the first name of the employee
     * @param lastName String value that stores the last name of the employee
     * @param email String value that stores the email of the employee
     */
    public Employee(boolean active, String firstName, String lastName, String email) {
        this.active = active;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     * Retrieves the id of the employee
     * @return the id of the employee
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the employee
     * @param id the id to be set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the status of the employee
     * @return the status of the employee
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the status of the employee
     * @param active the status to be set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Retrieves the first name of the employee
     * @return the first name of the employee
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the employee
     * @param firstName the first name to be set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Retrieves the last name of the employee
     * @return the last name of the employee
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the employee
     * @param lastName the last name to be set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Retrieves the email of the employee
     * @return the email of the employee
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the employee
     * @param email the email to be set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
