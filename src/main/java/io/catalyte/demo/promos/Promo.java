package io.catalyte.demo.promos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

/**
 * Represents a promotion entity in the application.
 */
@Entity
public class Promo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;
    private String description;
    private String campaignName;
    private PromoType type;
    private BigDecimal rate;
    private String startDate;
    private String endDate;
    private BigDecimal minLifetimeSpent;

    /**
     * Creates an instance of Promo with no values
     */
    public Promo() {
    }

    /**
     * Creates an instance of the promotion with the specified fields
     * @param code The code for the Promotion
     * @param description Sting value that describes the Promotion
     * @param campaignName Sting value of the name of the campaign for the Promotion
     * @param rate BigDecimal value of the flat rate of percentage of the discount for the Promotion
     * @param type Enum value of the type of Promotion
     * @param startDate Sting value of the start date for the Promotion
     * @param endDate Sting value of the end date for the Promotion
     * @param minLifetimeSpent BigDecimal value of the minimum customer lifetime spent to qualify for the Promotion
     */
    public Promo(String code, String description, String campaignName, BigDecimal rate, PromoType type, String startDate, String endDate, BigDecimal minLifetimeSpent) {
        this.code = code;
        this.description = description;
        this.campaignName = campaignName;
        this.rate = rate;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.minLifetimeSpent = minLifetimeSpent;
    }
    /**
     * Retrieves the id of the promotion.
     * @return the id of the promotion
     */
    public int getId() {
        return id;
    }
    /**
     * Sets the id of the promotion.
     * @param id the id to be set
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Retrieves the code of the promotion.
     * @return the code of the promotion
     */
    public String getCode() {
        return code;
    }
    /**
     * Sets the code of the promotion.
     * @param code the code to be set
     */
    public void setCode(String code) {
        this.code = code;
    }
    /**
     * Retrieves the description of the promotion.
     * @return the description of the promotion
     */
    public String getDescription() {
        return description;
    }
    /**
     * Sets the description of the promotion.
     * @param description the description to be set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Retrieves the campaign name of the promotion.
     * @return the campaign name of the promotion
     */
    public String getCampaignName() {
        return campaignName;
    }
    /**
     * Sets the campaign name of the promotion.
     * @param campaignName the campaign name to be set
     */
    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }
    /**
     * Retrieves the type of the promotion.
     * @return the type of the promotion
     */
    public PromoType getType() {
        return type;
    }
    /**
     * Sets the type of the promotion.
     * @param type the type to be set
     */
    public void setType(PromoType type) {
        this.type = type;
    }
    /**
     * Retrieves the rate of the promotion.
     * @return the rate of the promotion
     */
    public BigDecimal getRate() {
        return rate;
    }
    /**
     * Sets the rate of the promotion.
     * @param rate the rate to be set
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
    /**
     * Retrieves the start date of the promotion.
     * @return the start date of the promotion
     */
    public String getStartDate() {
        return startDate;
    }
    /**
     * Sets the start date of the promotion.
     * @param startDate the start date to be set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    /**
     * Retrieves the end date of the promotion.
     * @return the end date of the promotion
     */
    public String getEndDate() {
        return endDate;
    }
    /**
     * Sets the end date of the promotion.
     * @param endDate the end date to be set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    /**
     * Retrieves the minimum customer lifetime spent of the promotion.
     * @return the minimum customer lifetime spent of the promotion
     */
    public BigDecimal getMinLifetimeSpent() {
        return minLifetimeSpent;
    }
    /**
     * Sets the minimum customer lifetime spent of the promotion.
     * @param minLifetimeSpent the minimum customer lifetime spent to be set
     */
    public void setMinLifetimeSpent(BigDecimal minLifetimeSpent) {
        this.minLifetimeSpent = minLifetimeSpent;
    }
}
