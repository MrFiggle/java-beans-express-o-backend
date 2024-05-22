package io.catalyte.demo.vendors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Implementation of the VendorService interface.
 * This service provides CRUD operations for vendors.
 */
@Service
public class VendorServiceImpl implements VendorService {

    VendorRepository vendorRepository;

    /**
     * Constructs a VendorServiceImpl with the specified VendorRepository.
     *
     * @param vendorRepository the repository to be used for vendor operations
     */
    @Autowired
    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    /**
     * Creates a new vendor and saves it to the repository.
     *
     * @param vendorToCreate the vendor to be created and saved
     * @return the created vendor
     */
    @Override
    public Vendor createVendor(Vendor vendorToCreate) {
        return vendorRepository.save(vendorToCreate);
    }
}
