package io.catalyte.demo.util;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    /**
     * Creates an instance of Validator
     */
    public Validator() {
    }

    /**
     * Checks for errors in the price.
     * @param price the price to be validated
     * @param priceName the name of the specific price
     * @return String with error messages relating to the price
     */
    public String validatePrice(BigDecimal price, String priceName) {
        String errorCollector = "";
        if (Objects.equals(price, BigDecimal.valueOf(0))) {
            errorCollector = errorCollector + priceName + " cannot be empty. ";
        } else if (price == null) {
            errorCollector = errorCollector + priceName + " cannot be null. ";
        }  else {
            String costString = price.toString();
            Pattern pattern = Pattern.compile("\\.\\d{2}$");
            Matcher matcher = pattern.matcher(costString);
            if (!matcher.find()) {
                errorCollector = errorCollector + priceName + " must have two decimals. ";
            }
            if (price.compareTo(BigDecimal.ZERO) < 0) {
                errorCollector = errorCollector + priceName + " cannot be negative. ";
            }
        }

        return errorCollector;
    }

    /**
     * Checks for errors in full name
     * @param fullName the full name to be validated
     * @return String with error messages relating to the name
     */
    public String validateFullName(String fullName) {
        String errorCollector = "";
        if (fullName == null) {
            errorCollector = errorCollector + "Name cannot be null. ";
        } else if (fullName.isEmpty()) {
            errorCollector = errorCollector + "Name cannot be empty. ";
        }
        return  errorCollector;
    }

    public String validateFirstName(String firstName) {
        String errorCollector = "";
        if (firstName == null) {
            errorCollector = errorCollector + "First name cannot be null. ";
        } else if (firstName.isEmpty()) {
            errorCollector = errorCollector + "First name cannot be empty. ";
        }
        return  errorCollector;
    }

    public String validateLastName(String firstName) {
        String errorCollector = "";
        if (firstName == null) {
            errorCollector = errorCollector + "Last name cannot be null. ";
        } else if (firstName.isEmpty()) {
            errorCollector = errorCollector + "Last name cannot be empty. ";
        }
        return  errorCollector;
    }

    public String validateEmail(String email) {
        String errorCollector = "";

        if (email == null) {
            errorCollector = errorCollector + "Email cannot be null. ";
        } else if (email.isEmpty()) {
            errorCollector = errorCollector + "Email cannot be empty. ";
        } else {
            String emailPattern = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$";
            Pattern pattern = Pattern.compile(emailPattern);
            Matcher matcher = pattern.matcher(email);
            if (!matcher.find()) {
                errorCollector = errorCollector + "Email must follow x@x.x format. ";
            }
        }
        return errorCollector;

    }
}
