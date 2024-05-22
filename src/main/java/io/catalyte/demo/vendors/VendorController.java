package io.catalyte.demo.vendors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing vendors.
 * Provides endpoints for creating and retrieving vendor information.
 */
@RestController
@RequestMapping(value = "/vendors")
public class VendorController {

    VendorService vendorService;

    /**
     * Constructs a VendorController with the specified VendorService.
     *
     * @param vendorService the service to be used for vendor operations
     */
    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    /**
     * Creates a new vendor.
     *
     * @param vendorToCreate the vendor to be created
     * @return the created vendor
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vendor createVendor(@RequestBody Vendor vendorToCreate) {
        return vendorService.createVendor(vendorToCreate);
    }


}
