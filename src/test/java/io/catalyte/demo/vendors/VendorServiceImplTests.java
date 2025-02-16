package io.catalyte.demo.vendors;
import io.catalyte.demo.util.TimeStamp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VendorServiceImplTests {

    @Mock
    VendorRepository vendorRepository;

    @Mock
    VendorValidator vendorValidator;

    VendorService vendorService;
    Vendor testVendor;
    Vendor testVendor2;
    List<Vendor> myList;

    @BeforeEach
    public void setUp() {
        vendorService = new VendorServiceImpl(vendorRepository, vendorValidator);
        testVendor = new Vendor("Wayne Enterprises",
                "123 Fake St",
                "456 Imaginary Ave",
                "Gotham City",
                "New York",
                "11223",
                "Bruce Wayne",
                "CEO",
                "555-555-5555",
                "b@man.com");
        testVendor.setId(1);
        testVendor2 = new Vendor("Stark Industries",
                "10880 Malibu Point",
                "",
                "Malibu",
                "California",
                "90263",
                "Tony Stark",
                "CEO",
                "424-424-4242",
                "I@mIronman.com");
        myList = new ArrayList<>();
        myList.add(testVendor);
        myList.add(testVendor2);
    }

    @Test
    public void createVendor_withValidVendor_returnsPersistedVendor() {
        doNothing().when(vendorValidator).validate(any(Vendor.class), any(boolean.class));

        TimeStamp dummyDate = new TimeStamp();

        when(vendorRepository.save(any(Vendor.class))).thenAnswer(invocation -> {
            Vendor vendor = invocation.getArgument(0);
            vendor.setId(1);
            return vendor;
        });

        Vendor vendorResult = vendorService.createVendor(testVendor);

        assertNotNull(vendorResult);
        assertEquals(1, vendorResult.getId());
        assertEquals("Wayne Enterprises", vendorResult.getName());
        assertEquals(dummyDate.getTimeStamp(), vendorResult.getCreatedTimestamp());
        assertEquals(dummyDate.getTimeStamp(), vendorResult.getEditedTimestamp());

        verify(vendorRepository, times(1)).save(any(Vendor.class));

    }

    @Test
    public void editVendor_validVendor_returnsPersistedVendor() {
        doNothing().when(vendorValidator).validate(any(Vendor.class), any(boolean.class));

        when(vendorRepository.findById(any(Integer.class))).thenReturn(Optional.ofNullable(testVendor));
        when(vendorRepository.save(any(Vendor.class))).thenReturn(testVendor);

        Vendor result = vendorService.editVendor(testVendor2, testVendor.getId());

        if (result.getId() != testVendor.getId()) fail();
        if (!Objects.equals(result.getName(), testVendor2.getName())) fail();
        if (!Objects.equals(result.getStreet1(), testVendor2.getStreet1())) fail();
        if (!Objects.equals(result.getStreet2(), testVendor2.getStreet2())) fail();
        if (!Objects.equals(result.getCity(), testVendor2.getCity())) fail();
        if (!Objects.equals(result.getState(), testVendor2.getState())) fail();
        if (!Objects.equals(result.getZip(), testVendor2.getZip())) fail();
        if (!Objects.equals(result.getContactName(), testVendor2.getContactName())) fail();
        if (!Objects.equals(result.getContactTitle(), testVendor2.getContactTitle())) fail();
        if (!Objects.equals(result.getContactPhone(), testVendor2.getContactPhone())) fail();
        if (!Objects.equals(result.getContactEmail(), testVendor2.getContactEmail())) fail();



        assertTrue(true);

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

    @Test
    public void editVendor_invalidId_throwsResponseException() {
        when(vendorRepository.findById(any(Integer.class))).thenReturn(Optional.empty());
        boolean exceptionFound = false;
        try {
            vendorService.editVendor(testVendor2, -1);
        } catch (ResponseStatusException e) {
            exceptionFound = true;
        }
        assertTrue(exceptionFound);
    }

    @Test
    public void editVendor_idInBody_throwsResponseException() {
        testVendor2.setId(1);
        boolean exceptionFound = false;
        try {
            vendorService.editVendor(testVendor2, 1);
        } catch (ResponseStatusException e) {
            exceptionFound = true;
        }
        assertTrue(exceptionFound);

    }

    @Test
    public void getVendorByName_name_returnsVendor(){
        when(vendorRepository.findAll()).thenReturn(myList);
        Vendor myVendor = vendorService.getVendorByName(testVendor.getName());
        assertEquals(testVendor.getName(), myVendor.getName());
    }

    @Test
    public void getVendorByName_invalidName_throwsResponseStatusException(){
        when(vendorRepository.findAll()).thenReturn(myList);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            vendorService.getVendorByName("not a valid name");
        });
        assertEquals("404 NOT_FOUND \"The vendor you are looking for was not found\"", exception.getMessage());
    }

    @Test
    public void getVendorByName_name_throwsResponseStatusException(){
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            vendorService.getVendorByName(null);
        });
        assertEquals("400 BAD_REQUEST \"The vendor name you have supplied is invalid\"", exception.getMessage());
    }

    @Test
    public void getAllVendors_noArg_returnsEmptyListOfVendors(){
        List<Vendor> list = vendorService.getAllVendors();
        assertTrue(list.isEmpty());
    }

    @Test
    public void getAllVendors_noArg_returnsListOfVendors(){
        List<Vendor> toReturn = new ArrayList<>();
        toReturn.add(testVendor);
        toReturn.add(testVendor2);
        when(vendorRepository.findAll()).thenReturn(toReturn);
        List<Vendor> list = vendorService.getAllVendors();
        assertEquals(2, list.size());
    }

    @Test
    public void getAllVendors_noArgs_throwsResponseStatusException(){
        when(vendorRepository.findAll()).thenThrow(new ResponseStatusException(HttpStatusCode.valueOf(500)));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            vendorService.getAllVendors();
        });
        assertEquals("500 INTERNAL_SERVER_ERROR \"There was an internal error while fetching your data\"", exception.getMessage() );
    }

}
