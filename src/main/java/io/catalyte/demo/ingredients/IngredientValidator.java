package io.catalyte.demo.ingredients;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IngredientValidator {
    /**
     * Creates an instance of IngredientValidator
     */
    public IngredientValidator() {
    }
    /**
     * Checks for errors in the name of the ingredient.
     * @param name the name of the ingredient.
     * @return String containing error messages that relate to ingredient name.
     */
    public String validateName(String name) {
        String errorCollector = "";
        if (Objects.equals(name, "")) {
            errorCollector = errorCollector + "Name can not be empty. ";
        } else if (name == null) {
            errorCollector = errorCollector + "Name can not be null. ";
        } else {
            String[] nameArray = new String[0];
            boolean doubleSpaces = false;
            if (name.contains(" ")){
                nameArray = name.split(" ");
                for (String word : nameArray) {
                    if (word.isEmpty()) {
                        doubleSpaces = true;
                    }
                }
            } else if (!name.isEmpty()) {
                nameArray = name.split("(?=[A-Z])");
            }
            boolean firstLetterError = false;
            boolean restLetterError = false;
            if (doubleSpaces){
                errorCollector = errorCollector + "Name can not contain double-spaces. ";
            } else {
                for (String word : nameArray) {
                    if (!Character.isUpperCase(word.charAt(0))) {
                        firstLetterError = true;
                    }
                    for (int i = 1; i < word.length(); i++) {
                        if (!Character.isLowerCase(word.charAt(i)) && !Character.isDigit(word.charAt(i))) {
                            restLetterError = true;
                        }
                    }
                }
            }
            if (firstLetterError) {
                errorCollector = errorCollector + "Each word in name must start with a capital letter. ";
            }
            if(restLetterError) {
                errorCollector = errorCollector + "Only the first letter of each word in name can be a capital letter. ";
            }
        }
        return errorCollector;
    }
    /**
     * Checks for errors in the purchasing cost of the ingredient.
     * @param cost the purchasing cost of the ingredient.
     * @return String containing error messages that relate to ingredient purchasing cost.
     */
    public String validateCost(BigDecimal cost) {
        String errorCollector = "";
        if (Objects.equals(cost, BigDecimal.valueOf(0))) {
            errorCollector = errorCollector + "Cost can not be empty. ";
        } else if (cost == null) {
            errorCollector = errorCollector + "Cost can not be null. ";
        } else {
            String costString = cost.toString();
            Pattern pattern = Pattern.compile("\\.\\d{2}$");
            Matcher matcher = pattern.matcher(costString);
            if (!matcher.find()) {
                errorCollector = errorCollector + "Cost must have two decimals. ";
            }
        }
        return errorCollector;
    }
    /**
     * Checks for errors in the unit of measure of the ingredient.
     * @param unitOfMeasure the unit of measure of the ingredient.
     * @return String containing error messages that relate to ingredient unit of measure.
     */
    public String validateUnitOfMeasure(String unitOfMeasure) {
        String errorCollector = "";
        if ( Objects.equals( unitOfMeasure, "" )) {
            errorCollector = errorCollector + "Unit of measure can not be empty. ";
            //      unit of measure must be all lower case
        } else if (unitOfMeasure == null) {
            errorCollector = errorCollector + "Unit of measure can not be null. ";
        } else if (!(unitOfMeasure.toLowerCase().equals(unitOfMeasure))){
            errorCollector = errorCollector + "Unit of measure must be all lowercase. ";
        }
        return errorCollector;
    }
    /**
     * Checks for errors in the unit amount of the ingredient.
     * @param unitAmount the unit amount of the ingredient.
     * @return String containing error messages that relate to ingredient unit amount.
     */
    public String validateUnitAmount(BigDecimal unitAmount) {
        String errorCollector = "";
        if ( Objects.equals( unitAmount, BigDecimal.valueOf(0) )) {
            errorCollector = errorCollector + "Unit amount can not be empty. ";
        } else if ( unitAmount == null) {
            errorCollector = errorCollector + "Unit amount can not be null. ";
        } else {
            Pattern pattern = Pattern.compile("^\\d+(\\.\\d{1,2})?$");
            String unitAmountString = unitAmount.toString();
            Matcher matcher = pattern.matcher(unitAmountString);
            if (!matcher.find()) {
                errorCollector = errorCollector + "Unit amount can only have up to two decimals. ";
            }
        }
        return errorCollector;
    }
}
