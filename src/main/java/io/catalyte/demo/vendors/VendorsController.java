package io.catalyte.demo.vendors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller for handling CRUD operations on Vendor entities.
 */
@RestController
@RequestMapping(value = "/vendors")
@CrossOrigin(origins = "http://localhost:3000")
public class VendorsController {

    VendorService vendorService;

    /**
     * Constructor to initialize VendorsController with the given VendorService.
     *
     * @param vendorService the service to handle vendor operations
     */
    public VendorsController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    /**
     * Creates a new vendor.
     *
     * @param vendorToCreate the vendor to create
     * @return the created vendor
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vendor createVendor(@RequestBody Vendor vendorToCreate) {
        return vendorService.createVendor(vendorToCreate);
    }

    /**
     * Retrieves a vendor by its ID.
     *
     * @param id the ID of the vendor to retrieve
     * @return the vendor with the specified ID
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Vendor getVendorById(@PathVariable int id) {
        return vendorService.getVendorById(id);
    }

    /**
     * Updates an existing vendor.
     *
     * @param updatedVendor the updated vendor details
     * @param id            the ID of the vendor to update
     * @return the updated vendor
     */
    @PutMapping(value="/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Vendor editVendor(@RequestBody Vendor updatedVendor, @PathVariable int id) {
        return vendorService.editVendor(updatedVendor, id);
    }

    /**
     * Retrieves all vendors
     *
     * @return a list of vendors
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Vendor> getAllVendors(){
        return vendorService.getAllVendors();
    }
}
