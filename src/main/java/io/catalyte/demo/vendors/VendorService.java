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
    public Vendor getVendorById(int id);

    /**
     * Updates an existing vendor.
     *
     * @param updatedVendor the updated vendor details
     * @param id            the ID of the vendor to update
     * @return the updated vendor
     */
    public Vendor editVendor(Vendor updatedVendor, int id);

    /**
     * Creates a new vendor.
     *
     * @param vendorToCreate the vendor to create
     * @return the created vendor
     */
    public Vendor createVendor(Vendor vendorToCreate);

    /**
     * Retrieves all vendors
     *
     * @return a list of vendors
     */
    public List<Vendor> getAllVendors();
}
