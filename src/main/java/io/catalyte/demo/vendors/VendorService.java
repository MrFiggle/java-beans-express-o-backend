package io.catalyte.demo.vendors;

public interface VendorService {
    public Vendor getVendorById(int id);
    public Vendor editVendor(Vendor updatedVendor, int id);

}
