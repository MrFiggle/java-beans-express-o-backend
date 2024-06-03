package io.catalyte.demo.customers;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean active;
    private String firstName;
    private String lastName;
    private String email;
    private String birthday;
    private String favoriteDrink;
    private BigDecimal lifetimeSpent;

    public Customer() {
        this.active = true;
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.birthday = "";
        this.favoriteDrink = "";
        this.lifetimeSpent = BigDecimal.valueOf(0);
    }

    public Customer(boolean active, String firstName, String lastName, String email, String birthday, String favoriteDrink, double lifetimeSpent) {
        this.active = active;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.favoriteDrink = favoriteDrink;
        this.lifetimeSpent = BigDecimal.valueOf(lifetimeSpent);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getFavoriteDrink() {
        return favoriteDrink;
    }

    public void setFavoriteDrink(String favoriteDrink) {
        if(favoriteDrink == null || favoriteDrink.isEmpty()){
            this.favoriteDrink = favoriteDrink;
        } else {
            this.favoriteDrink = favoriteDrink.isBlank() ? "" : favoriteDrink;
        }
    }

    public BigDecimal getLifetimeSpent() {
        return lifetimeSpent;
    }

    public void setLifetimeSpent(double lifetimeSpent) {
        this.lifetimeSpent = Objects.requireNonNullElseGet(BigDecimal.valueOf(lifetimeSpent), () -> BigDecimal.valueOf(0));
    }
}