package io.catalyte.demo.products;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.ArrayList;





@Entity
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private Boolean active;
  private String description;
  private String name;
  private String imageURL;
  private int vendorId;
  private ArrayList<String> ingredients;
  private ProductType classification;
  private DrinkType drinkType;
  private BigDecimal cost;
  private ArrayList<AllergenList> allergens;
  private int markupPercentage;
  private BigDecimal salePrice;

  public Product() {
  }
//DRINK CONSTRUCTOR
  public Product(Boolean active, String description, String name, String imageURL, ArrayList<String> ingredients, ProductType classification, DrinkType drinkType, BigDecimal costToProduce, ArrayList<AllergenList> allergens, BigDecimal salePrice) {
    this.active = active;
    this.description = description;
    this.name = name;
    this.imageURL = imageURL;
    this.ingredients = ingredients;
    this.drinkType = drinkType;
    this.classification = classification;
    this.cost = costToProduce;
    this.allergens = allergens;
    this.salePrice = salePrice;
  }
//BAKEDGOOD CONSTRUCTOR
  public Product(Boolean active, String description, String name, String imageURL, int vendorId, ArrayList<String> ingredients, ProductType classification, BigDecimal vendorPrice, ArrayList<AllergenList> allergens, int markupPercentage, BigDecimal salePrice) {
    this.active = active;
    this.description = description;
    this.name = name;
    this.imageURL = imageURL;
    this.vendorId = vendorId;
    this.ingredients = ingredients;
    this.classification = classification;
    this.cost = vendorPrice;
    this.allergens = allergens;
    this.markupPercentage = markupPercentage;
    this.salePrice = salePrice;
  }

  public Boolean getActive() {
    return active;
  }

  public String getDescription() {
    return description;
  }

  public String getName() {
    return name;
  }

  public String getImageURL() {
    return imageURL;
  }

  public int getVendorId() {
    return vendorId;
  }

  public ProductType getClassification() {
    return classification;
  }

  public ArrayList<String> getIngredients() {
    return ingredients;
  }

  public DrinkType getDrinkType() {
    return drinkType;
  }

  public BigDecimal getCost() {
    return cost;
  }

  public ArrayList<AllergenList> getAllergens() {
    return allergens;
  }

  public int getMarkupPercentage() {
    return markupPercentage;
  }

  public BigDecimal getSalePrice() {
    return salePrice;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setImageURL(String imageURL) {
    this.imageURL = imageURL;
  }

  public void setVendorId(int vendorId) {
    this.vendorId = vendorId;
  }

  public void setIngredients(ArrayList<String> ingredients) {
    this.ingredients = ingredients;
  }

  public void setClassification(ProductType classification) {
    this.classification = classification;
  }

  public void setDrinkType(DrinkType drinkType) {
    this.drinkType = drinkType;
  }

  public void setCost(BigDecimal cost) {
    this.cost = cost;
  }

  public void setAllergens(ArrayList<AllergenList> allergens) {
    this.allergens = allergens;
  }

  public void setSalePrice(BigDecimal salePrice) {
    this.salePrice = salePrice;
  }

  public void setMarkupPercentage(int markupPercentage) {
    this.markupPercentage = markupPercentage;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}

