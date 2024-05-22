package io.catalyte.demo.vendors;


public interface VendorService {

    Vendor createVendor(Vendor vendorToCreate);

    Vendor getVendorById(int id);
}
