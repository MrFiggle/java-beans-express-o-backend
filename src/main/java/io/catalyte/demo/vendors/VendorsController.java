package io.catalyte.demo.vendors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/vendors")
@CrossOrigin(origins = "http://localhost:3000")
public class VendorsController {

    VendorService vendorService;

    public VendorsController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @PutMapping(value="/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Vendor editVendor(@RequestBody Vendor updatedVendor, @PathVariable int id) {
        return vendorService.editVendor(updatedVendor, id);
    }
}
