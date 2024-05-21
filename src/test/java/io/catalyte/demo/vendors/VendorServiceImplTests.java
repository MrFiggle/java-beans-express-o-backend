package io.catalyte.demo.vendors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VendorServiceImplTests {

    @Mock
    VendorRepository vendorRepository;
    VendorService vendorService;
    Vendor testVendor;
    Vendor testVendor2;

    @BeforeEach
    public void setUp() {
        vendorService = new VendorServiceImpl(vendorRepository);
        testVendor = new Vendor("name", "address street 1", "address street 2", "state", "city","zip", "contact email", "contact name", "contact title", "contact phone","new timestamp");
        testVendor.setId(1);
        testVendor2 = new Vendor("name 2","address 2 street 1", "address 2 street 2", "state 2", "city 2","zip 2", "contact email 2", "contact name 2", "contact title 2", "contact phone 2", "updated timestamp");

    }


    @Test
    public void editVendor_validVendor_returnsPersistedVendor() {
        when(vendorRepository.findById(any(Integer.class))).thenReturn(Optional.ofNullable(testVendor));
        when(vendorRepository.save(any(Vendor.class))).thenReturn(testVendor);

        Vendor result = vendorService.editVendor(testVendor2, testVendor.getId());

        if (result.getId() != testVendor.getId()) fail();
        if (!Objects.equals(result.getName(), testVendor2.getName()))fail();
        if (!Objects.equals(result.getStreet1(), testVendor2.getStreet1()))fail();
        if (!Objects.equals(result.getStreet2(), testVendor2.getStreet2()))fail();
        if (!Objects.equals(result.getCity(), testVendor2.getCity()))fail();
        if (!Objects.equals(result.getState(), testVendor2.getState()))fail();
        if (!Objects.equals(result.getZip(), testVendor2.getZip()))fail();
        if (!Objects.equals(result.getContactName(), testVendor2.getContactName()))fail();
        if (!Objects.equals(result.getContactTitle(), testVendor2.getContactTitle()))fail();
        if (!Objects.equals(result.getContactPhone(), testVendor2.getContactPhone()))fail();
        if (!Objects.equals(result.getContactEmail(), testVendor2.getContactEmail()))fail();


        assertTrue(true);

    }

    @Test
    public void editVendor_invalidId_throwsResponseException() {
        when(vendorRepository.findById(any(Integer.class))).thenReturn(Optional.empty());

        boolean exceptionFound=false;
        try {
            vendorService.getVendorById(1);
        } catch(ResponseStatusException e) {
            exceptionFound=true;
        }
        assertTrue(exceptionFound);
    }

}
