package io.catalyte.demo.vendors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

/**
 * Implementation of the VendorService interface for handling CRUD operations on Vendor entities.
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
     * Creates a new vendor.
     *
     * @param vendorToCreate the vendor to create
     * @return the created vendor
     */
    public Vendor createVendor(Vendor vendorToCreate) {
        vendorRepository.save(vendorToCreate);
        return vendorToCreate;
    }

    /**
     * Retrieves a vendor by its ID.
     *
     * @param id the ID of the vendor to retrieve
     * @return the vendor with the specified ID
     * @throws ResponseStatusException if the vendor with the specified ID is not found
     */
    @Override
    public Vendor getVendorById(int id) {
        Optional<Vendor> optional = vendorRepository.findById(id);
        if (optional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found");
        return optional.get();
    }

    /**
     * Edits an existing vendor with new information.
     *
     * @param updatedVendor the updated vendor information
     * @param id the ID of the vendor to edit
     * @return the updated vendor
     * @throws ResponseStatusException if the ID in the updated vendor is set or the vendor with the specified ID is not found
     */
    @Override
    public Vendor editVendor(Vendor updatedVendor, int id) {
        if (updatedVendor.getId() > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id field forbidden");
        }
        Vendor savedVendor = getVendorById(id);

        savedVendor.setName(updatedVendor.getName());
        savedVendor.setStreet1(updatedVendor.getStreet1());
        savedVendor.setStreet2(updatedVendor.getStreet2());
        savedVendor.setState(updatedVendor.getState());
        savedVendor.setCity(updatedVendor.getCity());
        savedVendor.setZip(updatedVendor.getZip());
        savedVendor.setContactEmail(updatedVendor.getContactEmail());
        savedVendor.setContactName(updatedVendor.getContactName());
        savedVendor.setContactPhone(updatedVendor.getContactPhone());
        savedVendor.setContactTitle(updatedVendor.getContactTitle());

        return vendorRepository.save(savedVendor);
    }
}
