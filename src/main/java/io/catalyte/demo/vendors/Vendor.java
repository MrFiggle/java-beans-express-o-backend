package io.catalyte.demo.vendors;

import io.catalyte.demo.util.TimeStamp;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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

    /**
     * Default constructor. Initializes timestamps for creation and modification.
     */
    public Vendor() {
        TimeStamp timeStamp = new TimeStamp();
        this.createdTimestamp = timeStamp.getTimeStamp();
        this.editedTimestamp = timeStamp.getTimeStamp();
    }

    /**
     * Parameterized constructor to initialize a Vendor with the provided details.
     *
     * @param name         the name of the vendor
     * @param street1      the primary street address of the vendor
     * @param street2      the secondary street address of the vendor (optional)
     * @param city         the city of the vendor
     * @param state        the state of the vendor
     * @param zip          the ZIP code of the vendor
     * @param contactName  the name of the primary contact person for the vendor
     * @param contactTitle the title of the primary contact person for the vendor
     * @param contactPhone the phone number of the primary contact person for the vendor
     * @param contactEmail the email address of the primary contact person for the vendor
     */
    public Vendor(String name, String street1, String street2, String city, String state, String zip, String contactName, String contactTitle, String contactPhone, String contactEmail) {
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
        this.createdTimestamp = timeStamp.getTimeStamp();
        this.editedTimestamp = timeStamp.getTimeStamp();
    }

    public int getId() {return id;}
    public String getName() {return name;}
    public String getStreet1() {return street1;}
    public String getStreet2() {return street2;}
    public String getState() {return state;}
    public String getCity() {return city;}
    public String getContactEmail() {return contactEmail;}
    public String getZip() {return zip;}
    public String getContactName() {return contactName;}
    public String getContactTitle() {return contactTitle;}
    public String getContactPhone() {return contactPhone;}
    public String getCreatedTimestamp() {return createdTimestamp;}
    public String getEditedTimestamp() {return editedTimestamp;}

    public void setId(int id) {
        this.id = id;
        this.updateEditTime();
    }

    public void setName(String name) {
        this.name = name;
        this.updateEditTime();
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
        this.updateEditTime();
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
        this.updateEditTime();
    }

    public void setState(String state) {
        this.state = state;
        this.updateEditTime();
    }

    public void setCity(String city) {
        this.city = city;
        this.updateEditTime();
    }

    public void setZip(String zip) {
        this.zip = zip;
        this.updateEditTime();
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
        this.updateEditTime();
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
        this.updateEditTime();
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
        this.updateEditTime();
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
        this.updateEditTime();
    }

    private void setCreatedTimestamp(String createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public void setEditedTimestamp(String editedTimestamp) {
        this.editedTimestamp = editedTimestamp;
    }

    /**
     * Updates the edited timestamp to the current time.
     */
    public void updateEditTime() {
        TimeStamp timeStamp = new TimeStamp();
        this.setEditedTimestamp(timeStamp.getTimeStamp());
    }

}
