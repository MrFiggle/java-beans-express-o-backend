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
        if (name == null) {
            errorCollector = errorCollector + "Name can not be null. ";
        } else if (Objects.equals(name, "")) {
            errorCollector = errorCollector + "Name can not be empty. ";
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

}
