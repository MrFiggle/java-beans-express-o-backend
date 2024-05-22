package io.catalyte.demo.vendors;

/**
 * Service interface for Vendor operations.
 * Provides method declarations for vendor-related operations.
 */
public interface VendorService {

    /**
     * Creates a new vendor.
     *
     * @param vendorToCreate the vendor to be created
     * @return the created vendor
     */
    Vendor createVendor(Vendor vendorToCreate);

}
