package io.catalyte.demo.vendors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VendorValidatorTests {

    VendorValidator vendorValidator;

    @Mock
    VendorRepository vendorRepository;

    Vendor testVendor;

    @BeforeEach
    public void setUp() {

        vendorValidator = new VendorValidator(vendorRepository);

        testVendor = new Vendor("Wayne Enterprises",
                "123 Fake St",
                "Suite 595",
                "Gotham City",
                "New York",
                "11223",
                "Bruce Wayne",
                "CEO",
                "555-555-5555",
                "b@man.com");
    }

    @Test
    public void validate_validVendor_doesNotThrow() {
        assertDoesNotThrow(() -> vendorValidator.validate(testVendor, false));
    }

    @Test
    public void validate_duplicateName_throwsConflict() {
        when(vendorRepository.existsByName("Wayne Enterprises")).thenReturn(true);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            vendorValidator.validate(testVendor, false);
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    public void validate_validateRequiredField_missingName_throwsBadRequest() {
        testVendor.setName(null);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            vendorValidator.validate(testVendor, false);
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    public void validate_validateRequiredField_emptyName_throwsBadRequest() {
        testVendor.setName("");

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            vendorValidator.validate(testVendor, false);
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    public void validate_validateZip_invalidZip_throwsBadRequest() {
        testVendor.setZip("1055");

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            vendorValidator.validate(testVendor, false);
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    public void validate_validateContactInfo_invalidEmail_throwsBadRequest() {
        testVendor.setContactEmail("@a.com");

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            vendorValidator.validate(testVendor, false);
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    public void validate_validateContactInfo_invalidPhone_throwsBadRequest() {
        testVendor.setContactPhone("1234567899");

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            vendorValidator.validate(testVendor, false);
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    public void validate_validateContactInfo_invalidPhone_invalidEmail_throwsBadRequest() {
        testVendor.setContactPhone("1234567899");
        testVendor.setContactEmail("@a.com");


        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            vendorValidator.validate(testVendor, false);
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

}
