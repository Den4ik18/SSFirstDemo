package com.model;

import java.util.Objects;


public class Address {
    private Long id;
    private String street;
    private String city;
    private int zipCode;

    public Address() {
    }

    public Address(Long id, String street, String city, int zipCode) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    public Address(String street, String city, int zipCode) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "{" + street + ", " + city + ", " + zipCode + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return zipCode == address.zipCode &&
                Objects.equals(id, address.id) &&
                Objects.equals(street, address.street) &&
                Objects.equals(city, address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, city, zipCode);
    }
}
