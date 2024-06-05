package io.catalyte.demo.products;

import io.catalyte.demo.util.AllergenList;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import io.catalyte.demo.util.TimeStamp;

/**
 * Represents a product entity in the application.
 */
@Entity
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private Boolean active;
  private String description;
  private String name;
  private Integer vendorId;
  private ArrayList<String> ingredients;
  private ProductType classification;
  private DrinkType drinkType;
  private BigDecimal cost;
  private ArrayList<AllergenList> allergens;
  private BigDecimal markupPercentage;
  private BigDecimal salePrice;
  private String createdTimestamp;
  private String editedTimestamp;

  /**
   * Creates an instance of the product with no values
   */
  public Product() {
    TimeStamp timeStamp = new TimeStamp();
    String ts = timeStamp.getTimeStamp();
    this.createdTimestamp = ts;
    this.editedTimestamp = ts;
  }
  /**
   * Creates an instance of Product (specifically drinks) with the provided fields
   * @param active Boolean value to determine active status of the product.
   * @param description String value that describes the product.
   * @param name The name of the product
   * @param ingredients A list of ingredients in the product.
   * @param classification An Enum value to determine the type of product. (Drink)
   * @param drinkType An Enum value to determine the type of drink the product is.
   * @param costToProduce BigDecimal value of the cost to make the product.
   * @param allergens A list of allergens that product contains
   */
  public Product(Boolean active, String description, String name, ArrayList<String> ingredients, ProductType classification, DrinkType drinkType, BigDecimal costToProduce, ArrayList<AllergenList> allergens) {
    this.active = active;
    this.description = description;
    this.name = name;
    this.ingredients = ingredients;
    this.drinkType = drinkType;
    this.classification = classification;
    this.cost = costToProduce;
    this.allergens = allergens;
    this.calculateSalePrice();
    TimeStamp timeStamp = new TimeStamp();
    String ts = timeStamp.getTimeStamp();
    this.createdTimestamp = ts;
    this.editedTimestamp = ts;
  }
  /**
   * Creates an instance of Product (specifically Baked goods) with the provided fields
   * @param active Boolean value to determine active status of the product.
   * @param description String value that describes the product.
   * @param name The name of the product
   * @param vendorId Integer id of the vendor of the product.
   * @param ingredients A list of ingredients in the product.
   * @param classification An Enum value to determine the type of product. (Baked Good)
   * @param vendorPrice BigDecimal value of the cost to purchase
   * @param allergens A list of allergens that product contains
   * @param markupPercentage Integer value of the markup percentage
   */
  public Product(Boolean active, String description, String name, Integer vendorId, ArrayList<String> ingredients, ProductType classification, BigDecimal vendorPrice, ArrayList<AllergenList> allergens, BigDecimal markupPercentage) {
    this.active = active;
    this.description = description;
    this.name = name;
    this.vendorId = vendorId;
    this.ingredients = ingredients;
    this.classification = classification;
    this.cost = vendorPrice;
    this.allergens = allergens;
    this.markupPercentage = markupPercentage;
    this.calculateSalePrice();
    TimeStamp timeStamp = new TimeStamp();
    String ts = timeStamp.getTimeStamp();
    this.createdTimestamp = ts;
    this.editedTimestamp = ts;
  }
  /**
   * Retrieves the active status of the product.
   * @return the active status of the product
   */
  public Boolean getActive() {
    return active;
  }
  /**
   * Retrieves the description of the product.
   * @return the description of the product.
   */
  public String getDescription() {
    return description;
  }
  /**
   * Retrieves the name of the product.
   * @return the name of the product.
   */
  public String getName() {
    return name;
  }
  /**
   * Retrieves the vendor id of the product.
   * @return the vendor id of the product.
   */
  public Integer getVendorId() {
    return vendorId;
  }
  /**
   * Retrieves the classification of the product.
   * @return the classification of the product.
   */
  public ProductType getClassification() {
    return classification;
  }
  /**
   * Retrieves the list of ingredients of the product.
   * @return the list of ingredients of the product.
   */
  public ArrayList<String> getIngredients() {
    return ingredients;
  }
  /**
   * Retrieves the drink type of the product.
   * @return the drink type of the product.
   */
  public DrinkType getDrinkType() {
    return drinkType;
  }
  /**
   * Retrieves the cost of the product.
   * @return the cost of the product.
   */
  public BigDecimal getCost() {
    return cost;
  }
  /**
   * Retrieves the allergen list of the product.
   * @return the allergen list  of the product.
   */
  public ArrayList<AllergenList> getAllergens() {
    return allergens;
  }
  /**
   * Retrieves the markup percentage of the product.
   * @return the markup percentage of the product.
   */
  public BigDecimal getMarkupPercentage() {
    return markupPercentage;
  }
  /**
   * Retrieves the sale price of the product.
   * @return the sale price of the product.
   */
  public BigDecimal getSalePrice() {
    return salePrice;
  }
  /**
   * Sets the active status of the product.
   * @param active the active status to be set
   */
  public void setActive(Boolean active) {
    this.active = active;
    this.updateEditTime();
  }
  /**
   * Sets the description of the product.
   * @param description the description to be set
   */
  public void setDescription(String description) {
    this.description = description;
    this.updateEditTime();
  }
  /**
   * Sets the name of the product.
   * @param name the name to be set
   */
  public void setName(String name) {
    this.name = name;
    this.updateEditTime();
  }
  /**
   * Sets the vendor id of the product.
   * @param vendorId the vendor id to be set.
   */
  public void setVendorId(Integer vendorId) {
    this.vendorId = vendorId;
    this.updateEditTime();
  }
  /**
   * Sets the list of ingredients of the product.
   * @param ingredients the list of ingredients to be set.
   */
  public void setIngredients(ArrayList<String> ingredients) {
    this.ingredients = ingredients;
    this.updateEditTime();
  }
  /**
   * Sets the classification of the product.
   * @param classification the classification to be set.
   */
  public void setClassification(ProductType classification) {
    this.classification = classification;
    this.updateEditTime();
  }
  /**
   * Sets the drink type of the product.
   * @param drinkType the drink type to be set.
   */
  public void setDrinkType(DrinkType drinkType) {
    this.drinkType = drinkType;
    this.updateEditTime();
  }
  /**
   * Sets the cost of the product.
   * @param cost the cost to be set.
   */
  public void setCost(BigDecimal cost) {
    this.cost = cost;
    this.calculateSalePrice();
    this.updateEditTime();
  }
  /**
   * Sets the allergens list  of the product
   * @param allergens the allergens list to be set
   */
  public void setAllergens(ArrayList<AllergenList> allergens) {
    this.allergens = allergens;
    this.updateEditTime();
  }
  /**
   * Sets the sale price of the product.
   * @param salePrice the sale price to be set.
   */
  public void setSalePrice(BigDecimal salePrice) {
    this.salePrice = salePrice;
    this.updateEditTime();
  }
  /**
   * Sets the markup percentage of the product
   * @param markupPercentage the markup percentage to be set.
   */
  public void setMarkupPercentage(BigDecimal markupPercentage) {
    this.markupPercentage = markupPercentage;
    this.calculateSalePrice();
    this.updateEditTime();
  }
  /**
   * Retrieves the id of the product
   * @return the is of the product
   */
  public int getId() {
    return id;
  }
  /**
   * Sets the id of the product
   * @param id the id to be set.
   */
  public void setId(int id) {
    this.id = id;
    this.updateEditTime();
  }
  /**
   * Retrieves  the created timestamp of the product
   * @return the created timestamp of the product
   */
  public String getCreatedTimestamp() {
    return createdTimestamp;
  }
  /**
   * Retrieves the edited timestamp of the product
   * @return the edited timestamp of the product
   */
  public String getEditedTimestamp() {
    return editedTimestamp;
  }
  /**
   * Sets the created timestamp of the product
   * @param createdTimestamp the created timestamp of the product
   */
  public void setCreatedTimestamp(String createdTimestamp) {
    this.createdTimestamp = createdTimestamp;
  }
  /**
   * Sets the edited timestamp of the product
   * @param editedTimestamp the edited timestamp of the product
   */
  public void setEditedTimestamp(String editedTimestamp) {
    this.editedTimestamp = editedTimestamp;
  }
  private void updateEditTime() {
    TimeStamp timeStamp = new TimeStamp();
    this.setEditedTimestamp(timeStamp.getTimeStamp());
  }

  /**
   * Calculate the sale price from cost and markup percentage.
   */
  public void calculateSalePrice() {
    if (cost == null) {
      this.setSalePrice(null);
    } else if (markupPercentage != null) {
      this.setSalePrice(cost.add(markupPercentage.divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP).multiply(cost)).setScale(2, RoundingMode.HALF_UP));
    } else {
      this.setSalePrice(cost);
    }
  }
}

