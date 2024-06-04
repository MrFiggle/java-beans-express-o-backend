package io.catalyte.demo.customers;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Validation class that provides methods to validate customer data
 */
public class CustomerValidator {

    String NAME_ERROR = "Name fields cannot be empty";
    String EMAIL_FORMAT_ERROR = "Email must follow x@x.x format";
    String BIRTHDAY_FUTURE_ERROR = "Birthday cannot be in the future";
    String BIRTHDAY_FORMAT_ERROR = "Must be valid date format MM/dd/yyyy";

    private List<String> errors;

    /**
     * Validates the provided customer data
     *
     * @param customer - user's input of customer information
     * @return list of errors or an empty life if no errors are found
     */
    public List<String> validateCustomer(Customer customer) {
        this.errors = new ArrayList<>();

        validateName(customer.getFirstName(), customer.getLastName());
        validateEmail(customer.getEmail());
        validateBirthday(customer.getBirthday());
        // Favorite drink is made sure not to have whitespaces in Customer constructor
        // Lifetime spent will be calculated after Purchases entity is created

        return errors;
    }

    /**
     * Validates full name of customer
     *
     * @param firstName - first name of customer
     * @param lastName - last name of customer
     */
    private void validateName(String firstName, String lastName) {
        if (firstName == null || lastName == null || firstName.isBlank() || lastName.isBlank()) {
            errors.add(NAME_ERROR);
        }
    }

    /**
     * Validates email of customer
     *
     * @param email - email of customer
     */
    private void validateEmail(String email) {
        if (email == null || !email.contains("@") || !email.contains(".")) {
            errors.add(EMAIL_FORMAT_ERROR);
            return;
        }

        String[] emailParts = email.split("@");
        if (emailParts.length != 2 || emailParts[0].isBlank()) {
            errors.add(EMAIL_FORMAT_ERROR);
            return;
        }

        String domainPart = emailParts[1];
        String[] domainParts = domainPart.split("\\.");
        if (domainParts.length < 2 || domainParts[0].isBlank() || domainParts[1].isBlank()) {
            errors.add(EMAIL_FORMAT_ERROR);
        }
    }

    /**
     * Validates birthday of customer
     *
     * @param birthday - birthday of customer in MM/dd/yyyy format
     */
    private void validateBirthday(String birthday) {
        if (birthday == null || birthday.isBlank()) {
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(false);

        try {
            Date parsedDate = dateFormat.parse(birthday);
            Date currentDate = new Date();
            if (parsedDate.after(currentDate)) {
                errors.add(BIRTHDAY_FUTURE_ERROR);
            }
        } catch (ParseException e) {
            errors.add(BIRTHDAY_FORMAT_ERROR);
        }
    }
}