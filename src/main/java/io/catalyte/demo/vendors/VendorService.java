package io.catalyte.demo.vendors;

import java.util.List;

/**
 * Service interface for handling CRUD operations on Vendor entities.
 */
public interface VendorService {

    /**
     * Retrieves a vendor by its ID.
     *
     * @param id the ID of the vendor to retrieve
     * @return the vendor with the specified ID
     */
    Vendor getVendorById(int id);

    /**
     * Retrieves a vendor by its name.
     *
     * @param name the name of the vendor to retrieve
     * @return the vendor with the specified name
     */
    Vendor getVendorByName(String name);

    /**
     * Updates an existing vendor.
     *
     * @param updatedVendor the updated vendor details
     * @param id            the ID of the vendor to update
     * @return the updated vendor
     */
    Vendor editVendor(Vendor updatedVendor, int id);

    /**
     * Creates a new vendor.
     *
     * @param vendorToCreate the vendor to create
     * @return the created vendor
     */
    Vendor createVendor(Vendor vendorToCreate);

    /**
     * Retrieves all vendors
     *
     * @return a list of vendors
     */
    List<Vendor> getAllVendors();
}
