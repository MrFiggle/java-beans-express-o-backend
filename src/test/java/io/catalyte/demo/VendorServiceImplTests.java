package io.catalyte.demo;


import io.catalyte.demo.vendors.Vendor;
import io.catalyte.demo.vendors.VendorRepository;
import io.catalyte.demo.vendors.VendorService;
import io.catalyte.demo.vendors.VendorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VendorServiceImplTests {

    VendorService vendorService;

    @Mock
    VendorRepository vendorRepository;

    Vendor testVendor;

    @BeforeEach
    public void setUp(){

        vendorService = new VendorServiceImpl(vendorRepository);

        testVendor = new Vendor();
        testVendor.setName("Wayne Enterprises");
        testVendor.setStreet1("123 Fake St");
        testVendor.setStreet2("456 Imaginary Ave");
        testVendor.setCity("Gotham City");
        testVendor.setState("New York");
        testVendor.setZip("11223");
        testVendor.setContactName("Bruce Wayne");
        testVendor.setContactEmail("b@man.com");
        testVendor.setContactTitle("CEO");
        testVendor.setContactPhone("555-555-5555");
    }

    @Test
    public void createVendor_withValidVendor_returnsPersistedVendor() {


        LocalDateTime dummyDate = LocalDateTime.of(1939, Month.MARCH, 30, 12, 00, 00);

        when(vendorRepository.save(any(Vendor.class))).thenAnswer(invocation -> {
            Vendor vendor = invocation.getArgument(0);
            vendor.setId(1);
            vendor.setCreatedTimestamp(dummyDate);
            vendor.setEditedTimestamp(dummyDate);
            return vendor;
        });

        Vendor vendorResult = vendorService.createVendor(testVendor);

        assertNotNull(vendorResult);
        assertEquals(1, vendorResult.getId());
        assertEquals("Wayne Enterprises", vendorResult.getName());
        assertEquals(dummyDate, vendorResult.getCreatedTimestamp());
        assertEquals(dummyDate, vendorResult.getEditedTimestamp());

        verify(vendorRepository, times(1)).save(any(Vendor.class));
    }

    @Test
    public void getVendorById() {

        testVendor.setId(1);

        when(vendorRepository.findById(testVendor.getId())).thenReturn(Optional.of(testVendor));

        Vendor foundVendor = vendorService.getVendorById(testVendor.getId());

        assertNotNull(foundVendor);
        assertEquals(testVendor.getId(), foundVendor.getId());
        assertEquals(testVendor.getName(), foundVendor.getName());
    }

}
