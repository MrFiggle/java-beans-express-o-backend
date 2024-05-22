package io.catalyte.demo.vendors;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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

    private String editedTimestamp;

    public Vendor(String name, String street1, String street2, String state, String city, String zip, String contactEmail, String contactName, String contactTitle, String contactPhone, String editedTimestamp) {
        this.name = name;
        this.street1 = street1;
        this.street2 = street2;
        this.state = state;
        this.city = city;
        this.zip = zip;
        this.contactEmail = contactEmail;
        this.contactName = contactName;
        this.contactTitle = contactTitle;
        this.contactPhone = contactPhone;
        this.editedTimestamp = editedTimestamp;
    }

    public Vendor() {
    }

    public String getEditedTimestamp() {return this.editedTimestamp;}
    public void setEditedTimestamp(String newTime) {this.editedTimestamp=newTime;}

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
}
