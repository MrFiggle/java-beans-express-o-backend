package io.catalyte.demo.vendors;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.Month;

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
    public void setUp() {

        vendorService = new VendorServiceImpl(vendorRepository);

        testVendor = new Vendor("Wayne Enterprises",
                "123 Fake St",
                "456 Imaginary Ave",
                "Gotham City",
                "New York",
                "11223",
                "Bruce Wayne",
                "b@man.com",
                "CEO",
                "555-555-5555");
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

}
