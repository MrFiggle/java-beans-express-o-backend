package io.catalyte.demo.vendors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    public Vendor createVendor(Vendor vendorToCreate) {

        LocalDateTime now = LocalDateTime.now();
        vendorToCreate.setCreatedTimestamp(now);
        vendorToCreate.setEditedTimestamp(now);
        vendorRepository.save(vendorToCreate);

        return vendorToCreate;
    }

    @Override
    public Vendor getVendorById(int id) {
        Optional<Vendor> optional = vendorRepository.findById(id);
        if (optional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found");
        return optional.get();
    }
}
