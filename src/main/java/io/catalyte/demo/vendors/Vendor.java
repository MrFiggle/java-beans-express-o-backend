package io.catalyte.demo.vendors;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import io.catalyte.demo.util.TimeStamp;

/**
 * Represents a vendor entity with various attributes such as name, address, contact information,
 * and timestamps for creation and modification.
 */
@Entity
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String street1;
    private String street2;
    private String state;
    private String city;
    private String zip;
    private String contactEmail;
    private String contactName;
    private String contactTitle;
    private String contactPhone;
    private String createdTimestamp;
    private String editedTimestamp;


    public Vendor(String contactPhone, String contactTitle, String contactName, String contactEmail, String zip, String city, String state, String street2, String street1, String name) {
        this.contactPhone = contactPhone;
        this.contactTitle = contactTitle;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.zip = zip;
        this.city = city;
        this.state = state;
        this.street2 = street2;
        this.street1 = street1;
        this.name = name;

        TimeStamp timeStamp = new TimeStamp();
        String ts = timeStamp.getTimeStamp();
        this.createdTimestamp = ts;
        this.editedTimestamp = ts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(String createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public String getEditedTimestamp() {
        return editedTimestamp;
    }

    public void setEditedTimestamp(String editedTimestamp) {
        this.editedTimestamp = editedTimestamp;
    }

}
