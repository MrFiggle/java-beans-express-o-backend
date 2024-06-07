package io.catalyte.demo.vendors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import java.util.regex.Pattern;

@Component
public class VendorValidator {

    VendorRepository vendorRepository;

    @Autowired
    public VendorValidator(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    private static final Pattern ZIP_CODE_PATTERN = Pattern.compile("^\\d{5}(-\\d{4})?$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[^@]+@[^@]+\\.[^@]+$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\d{3}-\\d{3}-\\d{4}$");

    public void validate(Vendor vendorToValidate, Boolean canUseName) {
        StringBuilder errorMessages = new StringBuilder();

        if (vendorToValidate.getStreet2() == null) vendorToValidate.setStreet2("");

        if (!canUseName) {
            if (vendorRepository.existsByName(vendorToValidate.getName())) {
                errorMessages.append("Vendor with this name already exists. ");
            }
        }

        validateRequiredField(vendorToValidate.getStreet1(), "Vendor must have a Street with at least one character. ", errorMessages);
        validateRequiredField(vendorToValidate.getName(), "Vendor must have a Name with at least one character. ", errorMessages);
        validateRequiredField(vendorToValidate.getState(), "Vendor must have a State with at least one character. ", errorMessages);
        validateRequiredField(vendorToValidate.getCity(), "Vendor must have a City with at least one character. ", errorMessages);
        validateRequiredField(vendorToValidate.getContactName(), "Vendor must have a Contact Name with at least one character. ", errorMessages);
        validateRequiredField(vendorToValidate.getContactTitle(), "Vendor must have a Contact Title with at least one character. ", errorMessages);
        validateZip(vendorToValidate.getZip(), errorMessages);
        validateContactInfo(vendorToValidate.getContactEmail(), vendorToValidate.getContactPhone(), errorMessages);

        if (!errorMessages.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessages.toString().trim());
        }
    }

    private void validateRequiredField(String field, String errorMessage, StringBuilder errorMessages) {
        if (!StringUtils.hasText(field)) {
            errorMessages.append(errorMessage);
        }
    }

    private void validateZip(String zip, StringBuilder errorMessages) {
        if (!StringUtils.hasText(zip) || !ZIP_CODE_PATTERN.matcher(zip).matches()) {
            errorMessages.append("Vendor must have a valid ZIP code (ex. 11022 or 11022-3456). ");
        }
    }

    private void validateContactInfo(String email, String phone, StringBuilder errorMessages) {
        boolean hasValidEmail = StringUtils.hasText(email) && EMAIL_PATTERN.matcher(email).matches();
        boolean hasValidPhone = StringUtils.hasText(phone) && PHONE_PATTERN.matcher(phone).matches();

        if (!hasValidEmail && !hasValidPhone) {
            errorMessages.append("Vendor must provide either a valid email address or a contact phone number. ");
        }

        if (StringUtils.hasText(email) && !hasValidEmail) {
            errorMessages.append("Vendor must provide a valid email address. ");
        }

        if (StringUtils.hasText(phone) && !hasValidPhone) {
            errorMessages.append("Vendor must provide a valid contact phone number (xxx-xxx-xxxx). ");
        }
    }
}
