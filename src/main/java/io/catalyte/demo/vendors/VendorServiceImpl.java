package io.catalyte.demo.vendors;

import io.catalyte.demo.util.TimeStamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;


@Service
public class VendorServiceImpl implements VendorService{

    VendorRepository vendorRepository;

    @Autowired
    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public Vendor getVendorById(int id) {
        Optional<Vendor> optional = vendorRepository.findById(id);
        if (optional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found");
        return optional.get();
    }

    @Override
    public Vendor editVendor(Vendor updatedVendor, int id) {
        if (updatedVendor.getId() > 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id field forbidden");
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
        TimeStamp timeStamp = new TimeStamp();
        savedVendor.setEditedTimestamp(timeStamp.getTimeStamp());

        return vendorRepository.save(savedVendor);
    }




}
