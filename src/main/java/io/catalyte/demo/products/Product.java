package io.catalyte.demo.products;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.ArrayList;

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
  private int vendorId;
  private ArrayList<String> ingredients;
  private ProductType classification;
  private DrinkType drinkType;
  private BigDecimal cost;
  private ArrayList<AllergenList> allergens;
  private int markupPercentage;
  private BigDecimal salePrice;
  private int createdTimestamp;
  private int editedTimestamp;

  /**
   * Creates an instance of the product with no values
   */
  public Product() {
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
   * @param salePrice A BigDecimal value of the sale price of the product
   */
  public Product(Boolean active, String description, String name, ArrayList<String> ingredients, ProductType classification, DrinkType drinkType, BigDecimal costToProduce, ArrayList<AllergenList> allergens, BigDecimal salePrice) {
    this.active = active;
    this.description = description;
    this.name = name;
    this.ingredients = ingredients;
    this.drinkType = drinkType;
    this.classification = classification;
    this.cost = costToProduce;
    this.allergens = allergens;
    this.salePrice = salePrice;
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
   * @param salePrice A BigDecimal value of the sale price of the product
   */
  public Product(Boolean active, String description, String name, int vendorId, ArrayList<String> ingredients, ProductType classification, BigDecimal vendorPrice, ArrayList<AllergenList> allergens, int markupPercentage, BigDecimal salePrice) {
    this.active = active;
    this.description = description;
    this.name = name;
    this.vendorId = vendorId;
    this.ingredients = ingredients;
    this.classification = classification;
    this.cost = vendorPrice;
    this.allergens = allergens;
    this.markupPercentage = markupPercentage;
    this.salePrice = salePrice;
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
  public int getVendorId() {
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
  public int getMarkupPercentage() {
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
  }
  /**
   * Sets the description of the product.
   * @param description the description to be set
   */
  public void setDescription(String description) {
    this.description = description;
  }
  /**
   * Sets the name of the product.
   * @param name the name to be set
   */
  public void setName(String name) {
    this.name = name;
  }
  /**
   * Sets the vendor id of the product.
   * @param vendorId the vendor id to be set.
   */
  public void setVendorId(int vendorId) {
    this.vendorId = vendorId;
  }
  /**
   * Sets the list of ingredients of the product.
   * @param ingredients the list of ingredients to be set.
   */
  public void setIngredients(ArrayList<String> ingredients) {
    this.ingredients = ingredients;
  }
  /**
   * Sets the classification of the product.
   * @param classification the classification to be set.
   */
  public void setClassification(ProductType classification) {
    this.classification = classification;
  }
  /**
   * Sets the drink type of the product.
   * @param drinkType the drink type to be set.
   */
  public void setDrinkType(DrinkType drinkType) {
    this.drinkType = drinkType;
  }
  /**
   * Sets the cost of the product.
   * @param cost the cost to be set.
   */
  public void setCost(BigDecimal cost) {
    this.cost = cost;
  }
  /**
   * Sets the allergens list  of the product
   * @param allergens the allergens list to be set
   */
  public void setAllergens(ArrayList<AllergenList> allergens) {
    this.allergens = allergens;
  }
  /**
   * Sets the sale price of the product.
   * @param salePrice the sale price to be set.
   */
  public void setSalePrice(BigDecimal salePrice) {
    this.salePrice = salePrice;
  }
  /**
   * Sets the markup percentage of the product
   * @param markupPercentage the markup percentage to be set.
   */
  public void setMarkupPercentage(int markupPercentage) {
    this.markupPercentage = markupPercentage;
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
  }
  /**
   * Retrieves  the created timestamp of the product
   * @return the created timestamp of the product
   */
  public int getCreatedTimestamp() {
    return createdTimestamp;
  }

  /**
   * Retrieves the edited timestamp of the product
   * @return the edited timestamp of the product
   */
  public int getEditedTimestamp() {
    return editedTimestamp;
  }

  /**
   * Sets the created timestamp of the product
   * @param createdTimestamp the created timestamp of the product
   */
  public void setCreatedTimestamp(int createdTimestamp) {
    this.createdTimestamp = createdTimestamp;
  }

  /**
   * Sets the edited timestamp of the product
   * @param editedTimestamp the edited timestamp of the product
   */
  public void setEditedTimestamp(int editedTimestamp) {
    this.editedTimestamp = editedTimestamp;
  }
}

