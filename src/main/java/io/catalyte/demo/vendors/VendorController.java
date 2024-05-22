package io.catalyte.demo.vendors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/vendors")
public class VendorController {

    VendorService vendorService;

    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vendor createVendor(@RequestBody Vendor vendorToCreate) {
        return vendorService.createVendor(vendorToCreate);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Vendor getVendorById(@PathVariable int id) {
        return vendorService.getVendorById(id);
    }

}
