package io.catalyte.demo.ingredients;

import io.catalyte.demo.util.AllergenList;
import io.catalyte.demo.util.TimeStamp;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Represents an ingredient entity in the application.
 */
@Entity
public class Ingredient {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;
    private boolean active;
    private String name;
    private BigDecimal cost;
    private BigDecimal unitAmount;
    private String unitOfMeasure;
    private ArrayList<AllergenList> allergens;
    private String createdTimestamp;
    private String editedTimestamp;
    /**
     * Creates an instance of Ingredient with no values
     */
    public Ingredient() {
        TimeStamp timeStamp = new TimeStamp();
        String ts = timeStamp.getTimeStamp();
        this.createdTimestamp = ts;
        this.editedTimestamp = ts;
    }
    /**
     * Create an instance of Ingredient with the provided fields
     * @param active Boolean value to determine active status of the ingredient
     * @param name Sting value the describes the ingredient.
     * @param cost BigDecimal value of the purchasing cost of the ingredient.
     * @param unitOfMeasure String value of the type of unit used to measure the ingredient.
     * @param unitAmount BigDecimal value of the unit amount of the ingredient.
     * @param allergens A list of allergens that the ingredient contains.
     */
    public Ingredient(boolean active, String name, BigDecimal cost, String unitOfMeasure, BigDecimal unitAmount, ArrayList<AllergenList> allergens) {
        this.active = active;
        this.name = name;
        this.cost = cost;
        this.unitOfMeasure = unitOfMeasure;
        this.unitAmount = unitAmount;
        this.allergens = allergens;
        TimeStamp timeStamp = new TimeStamp();
        String ts = timeStamp.getTimeStamp();
        this.createdTimestamp = ts;
        this.editedTimestamp = ts;
    }
    /**
     * Retrieves the id of the ingredient.
     * @return the id of the ingredient.
     */
    public int getId() {
        return id;
    }
    /**
     * Sets the id of the ingredient and updates edited timestamp.
     * @param id the id to be set
     */
    public void setId(int id) {
        this.id = id;
        this.updateEditTime();
    }
    /**
     * Retrieves the active status of the ingredient.
     * @return the active status of the ingredient.
     */
    public boolean isActive() {
        return active;
    }
    /**
     * Sets the active status of the ingredient and updates edited timestamp.
     * @param active the active status to be set
     */
    public void setActive(boolean active) {
        this.active = active;
        this.updateEditTime();
    }
    /**
     * Retrieves the name of the ingredient.
     * @return the name of the ingredient.
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the name of the ingredient and updates edited timestamp.
     * @param name the name to be set
     */
    public void setName(String name) {
        this.name = name;
        this.updateEditTime();
    }
    /**
     * Retrieves the purchasing cost of the ingredient.
     * @return the purchasing cost of the ingredient.
     */
    public BigDecimal getCost() {
        return cost;
    }
    /**
     * Sets the purchasing cost of the ingredient and updates edited timestamp.
     * @param cost the purchasing cost to be set
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
        this.updateEditTime();
    }
    /**
     * Retrieves the unit amount of the ingredient.
     * @return the unit amount of the ingredient.
     */
    public BigDecimal getUnitAmount() {
        return unitAmount;
    }
    /**
     * Sets the unit amount of the ingredient and updates edited timestamp.
     * @param unitAmount the unit amount to be set
     */
    public void setUnitAmount(BigDecimal unitAmount) {
        this.unitAmount = unitAmount;
        this.updateEditTime();
    }
    /**
     * Retrieves the unit of measure of the ingredient.
     * @return the unit of measure of the ingredient.
     */
    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }
    /**
     * Sets the unit of measure of the ingredient and updates edited timestamp.
     * @param unitOfMeasure the unit of measure to be set
     */
    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
        this.updateEditTime();
    }
    /**
     * Retrieves the list of allergens of the ingredient.
     * @return the list of allergen of the ingredient.
     */
    public ArrayList<AllergenList> getAllergens() {
        return allergens;
    }
    /**
     * Sets the list of allergens of the ingredient and updates edited timestamp.
     * @param allergens the list of allergens to be set
     */
    public void setAllergens(ArrayList<AllergenList> allergens) {
        this.allergens = allergens;
        this.updateEditTime();
    }
    /**
     * Retrieves the created timestamp of the ingredient.
     * @return the created timestamp of the ingredient.
     */
    public String getCreatedTimestamp() {
        return createdTimestamp;
    }
    /**
     * Sets the created timestamp of the ingredient and updates edited timestamp.
     * @param createdTimestamp the created timestamp to be set
     */
    public void setCreatedTimestamp(String createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
        this.updateEditTime();
    }
    /**
     * Retrieves the edited timestamp of the ingredient.
     * @return the edited timestamp of the ingredient.
     */
    public String getEditedTimestamp() {
        return editedTimestamp;
    }
    /**
     * Sets the edited timestamp of the ingredient and updates edited timestamp.
     * @param editedTimestamp the edited timestamp to be set
     */
    public void setEditedTimestamp(String editedTimestamp) {
        this.editedTimestamp = editedTimestamp;
    }
    private void updateEditTime() {
        TimeStamp timeStamp = new TimeStamp();
        this.setEditedTimestamp(timeStamp.getTimeStamp());
    }
}
