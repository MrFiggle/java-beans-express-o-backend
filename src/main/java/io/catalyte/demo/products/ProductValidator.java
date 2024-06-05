package io.catalyte.demo.products;

import io.catalyte.demo.util.Validator;
import io.catalyte.demo.vendors.VendorRepository;

import java.math.BigDecimal;

public class ProductValidator extends Validator {
    private final VendorRepository vendorRepository;

    /**
     * Creates an instance of Product Validator
     * @param vendorRepository the vendor repository where all vendor data lies
     */
    public ProductValidator(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    /**
     * Checks for errors in the product
     * @param productToValidate the product which needs to be validated
     * @return String with error messages relating to the product
     */
    public String validateProduct(Product productToValidate) {
        String errors = "";
        errors += validateFullName(productToValidate.getName());
        errors += validateDescription(productToValidate.getDescription());
        errors += validatePrice(productToValidate.getCost(), "Cost");
        errors += validateMarkUpPercentage(productToValidate.getMarkupPercentage(), productToValidate.getClassification());
        errors += validateVendorId(productToValidate.getVendorId(), productToValidate.getClassification());
        errors += validateDrinkType(productToValidate.getDrinkType(), productToValidate.getClassification());
        errors += validatePrice(productToValidate.getSalePrice(), "Sale price");
        return errors;
    }

    /**
     * Checks for errors in the description
     * @param description the description of the product
     * @return String with error messages relating to the description
     */
    public String validateDescription(String description) {
        String errors = "";
        if (description == null) {
            errors += "Description cannot be null. ";
        } else if (description.isEmpty()) {
            errors += "Description cannot be empty. ";
        }
        return errors;
    }

    /**
     * Checks for errors in the Markup percentage
     * @param markUpPercentage the markup percentage of the product
     * @param classification the classification of the product
     * @return String with error messages relating to the markup percentage
     */
    public String validateMarkUpPercentage(BigDecimal markUpPercentage, ProductType classification) {
        String errors = "";

        if (classification == ProductType.DRINK) {
            if (markUpPercentage != null) {
                errors += "Markup percentage not applicable for drinks. ";
            }
        } else if (markUpPercentage == null) {
            errors += "Markup percentage cannot be null for baked goods. ";
        } else {
            if (markUpPercentage.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) != 0) {
                errors += "Markup percentage should be a whole number. ";
            }
            if (markUpPercentage.compareTo(BigDecimal.ZERO) < 0) {
                errors += "Markup percentage should be non negative. ";
            }
        }

        return errors;
    }

    /**
     * Checks for errors in the vendorId
     * @param vendorId the vendor id of the product
     * @param classification the classification of the product
     * @return String with error messages relating to the vendorId
     */
    public String validateVendorId(Integer vendorId, ProductType classification) {
        String errors = "";
        if (classification == ProductType.BAKEDGOOD) {
            if (vendorId == null) {
                errors += "vendorId cannot be null for baked goods. ";
            } else if (vendorRepository.findById(vendorId).isEmpty()) {
                errors += "Vendor with vendorId does not exist. ";
            }
        } else if (vendorId != null) {
            errors += "vendorId cannot be set for drinks. ";
        }
        return errors;
    }

    /**
     * Checks for errors in the drink type
     * @param drinkType the drink type of the product
     * @param classification the classification of the product
     * @return String with error messages relating to the drink type
     */
    public String validateDrinkType(DrinkType drinkType, ProductType classification) {
        String errors = "";
        if (classification == ProductType.BAKEDGOOD) {
            if (drinkType != null) {
                errors += "Drink type cannot be set for baked goods. ";
            }
        } else if (drinkType == null) {
            errors += "Drink type must be set for drinks. ";
        }
        return errors;
    }
}
